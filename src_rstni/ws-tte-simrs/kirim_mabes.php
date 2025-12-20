<?php



//error cek
ini_set('display_errors', 0);
ini_set('display_startup_errors', 0);
error_reporting(-1);

require "inc/configdb.inc.php";
require "inc/fungsi.inc.php";
require "inc/set.inc.php";
require "inc/phpqrcode/qrlib.php"; 


//
$get_json=file_get_contents("php://input");
$arr = json_decode($get_json);
//

/* {
  "metadata": {
    "method": "kirim_berkas"
  },
  "data": {
    "no_rawat": "2024/01/31/0000001",
    "nik": "1704R0040123V005686",
    "password": "0001908100866",
    "jns_dokumen": "RSM1",
    "nama_file":"resume_medis_20202.pdf",
    "petugas":"kode_petugas",
    "status_rawat":"Ranap",
    "nomr":"123456",
    "file_base64": "KPj4Kc3RhcnR4cmVmCjg0NDc5CiUlRU9GCg================"
  }
} */

if (!empty($arr) and $arr->metadata->method=="kirim_berkas"){
	// variable data adalah base64 dari file pdf
	$file=base64_decode($arr->data->file_base64);

	if(preg_match("/^[A-Za-z0-9 ._-]+$/",$arr->data->nama_file)){
		
		// hasilnya adalah berupa binary string $pdf, untuk disimpan:
		$nm_file_lengkap=str_replace(" ","_",$arr->data->nama_file);
		file_put_contents($folder_simpan.$nm_file_lengkap,$file);
		
		//cek format file
		$format_file=mime_content_type($folder_simpan.$nm_file_lengkap);
		//
		//echo "Debug: ".$folder_simpan.$nm_file_lengkap." => ".mime_content_type($folder_simpan.$nm_file_lengkap);
	   //  if(file_exists($folder_simpan.$nm_file_lengkap) and $format_file=="application/pdf"){
			//generate id_file
			$id_file=date("Ymdhms").bin2hex(random_bytes(3));			
			$isi_qr=$link_qr."rme.php?cek=".$id_file;		
			//$isi_qr=$link_qr.$id_file;	
			// create qrcode
			$qrcode_png=$folder_simpan.$id_file.".png";
			QRcode::png($isi_qr, $qrcode_png,  QR_ECLEVEL_L, 3, 1); 
			//
			$target_url="http://10.0.8.200/api/sign/pdf";
			//$api_key="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJlbWFpbCI6ImFkbWluQHR0ZS5jb20iLCJuaWsiOiIxMTIyMzMxMTIyMzM1NTIyIiwiZXhwIjoxNzA1NDk3NTU0fQ.rvhu_9pZEzIodlv20iHSra0KYDt0szNRAsQr_kuTnsQ";
			$api_key="esign";
			$password="qwerty";

       $cfile=new CURLFile($folder_simpan.$nm_file_lengkap,"application/pdf",$nm_file_lengkap);
			$cfile_ttd=new CURLFile($qrcode_png,"image/png","20220621100829.png");
			$post=array(
				"file"=>$cfile,
				"image"=>"true",
				"imageTTD"=>$cfile_ttd,
				"nik"=>$arr->data->nik,
				"passphrase"=>$arr->data->password,
				"jenis_response"=>"BASE64",
				"tampilan"=>"visible",
				"linkQR"=>$isi_qr,
				"width"=>"55",
				"height"=>"55",
				"tag_koordinat"=>"#"
				);
			//
		   $ch = curl_init();
			curl_setopt($ch, CURLOPT_URL, $target_url);
			curl_setopt($ch, CURLOPT_POST, 1);
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
			curl_setopt($ch, CURLOPT_HEADER, 1);
			curl_setopt($ch, CURLOPT_HTTPHEADER, array("Content-Type:multipart/form-data"));
			curl_setopt($ch, CURLOPT_USERPWD, $api_key.":".$password);
			curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
			curl_setopt($ch, CURLOPT_POSTFIELDS, $post);

			$response = curl_exec($ch);
			$info = curl_getinfo($ch);
			$err = curl_error($ch);
			curl_close($ch);

			$header_size = $info['header_size'];
			$header = substr($response, 0, $header_size);
			$body = substr($response, $header_size);



			// cari id_dokumen dari header
			$id_dokumen = null;
			if (preg_match('/id_dokumen:\s*([^\r\n]+)/i', $header, $matches)) {
				$id_dokumen = trim($matches[1]);
            }

        if ($id_dokumen && !empty($body)) {

            // file hasil TTE (BASE64)
            $nama_file_modif = $id_dokumen . ".pdf";
            $file_path = $folder_simpan . $nama_file_modif;
            file_put_contents($file_path, $body);

          // kirim hasil TTE ke server tujuan
           $ch2 = curl_init();
            $cfile2 = new CURLFile($file_path, "application/pdf", $nama_file_modif);
            $post2 = [
                "file" => $cfile2,
                "no_rawat" => $arr->data->no_rawat,
                "jns_dokumen" => $arr->data->jns_dokumen,
                "nomr" => $arr->data->nomr,
                "petugas" => $arr->data->petugas,
                "status_rawat" => $arr->data->status_rawat,
                //"nama_file_ori" => $arr->data->nama_file
                "nama_file_modif" => $nama_file_modif
            ];

            curl_setopt_array($ch2, [
                CURLOPT_URL => $folder_tte,
                CURLOPT_POST => 1,
                CURLOPT_RETURNTRANSFER => 1,
                CURLOPT_POSTFIELDS => $post2,
                CURLOPT_TIMEOUT => 30,
                CURLOPT_CONNECTTIMEOUT => 10,
            ]);

            $upload_response = curl_exec($ch2);
            $upload_info = curl_getinfo($ch2);
            $upload_err  = curl_error($ch2);
            curl_close($ch2);

            // coba baca hasil JSON-nya
            $upload_clean = preg_replace('/<[^>]+>/', '', $upload_response);

            // Ambil hanya bagian JSON terakhir jika ada teks campur
            if (preg_match('/\{.*\}$/s', $upload_clean, $matches)) {
                $upload_clean = $matches[0];
            }

            // Decode hasil bersih
            $upload_json = json_decode(trim($upload_clean), true);

            // simpan ke DB lokal
            $sql = "INSERT INTO rme_file_upload(
                        id_file, no_rawat, nama_file_ori, nama_file_modif, direktori, mime_type,
                        jenis_pemeriksaan, petugas, status_rawat, nomr
                    ) VALUES(
                        '" . escape($connection1, $id_dokumen) . "',
                        '" . escape($connection1, $arr->data->no_rawat) . "',
                        '" . escape($connection1, $arr->data->nama_file) . "',
                        '" . escape($connection1, $nama_file_modif) . "',
                        '" . escape($connection1, $folder_tte_upload) . "',
                        'application/pdf',
                        '" . escape($connection1, $arr->data->jns_dokumen) . "',
                        '" . escape($connection1, $arr->data->petugas) . "',
                        '" . escape($connection1, $arr->data->status_rawat) . "',
                        '" . escape($connection1, $arr->data->nomr) . "'
                    )";

            $exec = affected_rows($connection1, $sql);

           if ($exec == 1 && $upload_info['http_code'] == 200 && isset($upload_json['status']) && $upload_json['status'] === true) {
                $status = true;
                $pesan = "Berkas berhasil di-TTE dan dikirim ke server tujuan. ID: " . $id_dokumen;
            } else {
                $status = false;
                $pesan = "TTE berhasil, tapi gagal upload ke server tujuan. "
                    . "HTTP=" . ($upload_info['http_code'] ?? 'N/A')
                    . " | CURL=" . $upload_err
                    . " | RESP=" . $upload_response;
            }

            // hapus semua file di temp
            $files = glob($folder_simpan . '*');
            foreach ($files as $f) {
                if (is_file($f)) @unlink($f);
            }

        } else {
            $status = false;
            $pesan = "Gagal proses TTE. Response tidak valid.";
        }

    } else {
        $status = false;
        $pesan = "Nama file hanya boleh karakter alfanumerik.";
    }
} else {
    $status = false;
    $pesan = "Instruksi tidak dikenal.";
}

echo json_encode(["status" => $status, "msg" => $pesan]);
?>
