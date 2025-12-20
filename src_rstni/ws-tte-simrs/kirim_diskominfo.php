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
			// ===== GENERATE QR CODE DENGAN LOGO DI TENGAH (FIX) =====

// path logo (logo Anda yang sekarang)
$logo_path = __DIR__ . "/logo.png";

if (!file_exists($logo_path)) {
    $status = false;
    $pesan = "Logo QR tidak ditemukan";
    echo json_encode(["status"=>$status,"msg"=>$pesan]);
    exit;
}

// buat QR dasar (ECC HIGH)
$temp_qr = $folder_simpan . "qr_temp.png";
QRcode::png($isi_qr, $temp_qr, QR_ECLEVEL_H, 8, 2);

// load QR
$qr_img = imagecreatefrompng($temp_qr);
imagealphablending($qr_img, true);
imagesavealpha($qr_img, true);

$qr_w = imagesx($qr_img);
$qr_h = imagesy($qr_img);

// ===== BUAT KOTAK PUTIH DI TENGAH QR =====
$box_size = (int)($qr_w * 0.22); // 22% aman untuk scan
$box_x = (int)(($qr_w - $box_size) / 2);
$box_y = (int)(($qr_h - $box_size) / 2);

// warna putih
$white = imagecolorallocate($qr_img, 255, 255, 255);
imagefilledrectangle(
    $qr_img,
    $box_x,
    $box_y,
    $box_x + $box_size,
    $box_y + $box_size,
    $white
);

// ===== LOAD LOGO (SAFE MODE, FIX) =====
$logo_data = file_get_contents($logo_path);
if ($logo_data === false) {
    die("Gagal membaca file logo");
}

$logo_src = imagecreatefromstring($logo_data);
if ($logo_src === false) {
    die("Logo tidak bisa dirender GD");
}

$logo_w = imagesx($logo_src);
$logo_h = imagesy($logo_src);

// paksa ke truecolor
$logo_img = imagecreatetruecolor($logo_w, $logo_h);

// aktifkan alpha
imagealphablending($logo_img, false);
imagesavealpha($logo_img, true);

// background transparan
$transparent = imagecolorallocatealpha($logo_img, 0, 0, 0, 127);
imagefilledrectangle($logo_img, 0, 0, $logo_w, $logo_h, $transparent);

// copy logo asli ke canvas truecolor
imagecopy(
    $logo_img,
    $logo_src,
    0,
    0,
    0,
    0,
    $logo_w,
    $logo_h
);

imagedestroy($logo_src);

// logo = 80% dari kotak putih
$logo_target = (int)($box_size * 0.95);
$scale = min($logo_w, $logo_h) / $logo_target;
$logo_new_w = (int)($logo_w / $scale);
$logo_new_h = (int)($logo_h / $scale);

// posisi logo di tengah kotak
$logo_x = (int)($box_x + ($box_size - $logo_new_w) / 2);
$logo_y = (int)($box_y + ($box_size - $logo_new_h) / 2);

// tempel logo
imagecopyresampled(
    $qr_img,
    $logo_img,
    $logo_x,
    $logo_y,
    0,
    0,
    $logo_new_w,
    $logo_new_h,
    $logo_w,
    $logo_h
);

// simpan QR final
imagepng($qr_img, $qrcode_png);

// bersihkan
imagedestroy($qr_img);
imagedestroy($logo_img);
@unlink($temp_qr);


			//
			$target_url="http://bsre.labuhanbatukab.go.id/api/sign/pdf";
			//$api_key="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJlbWFpbCI6ImFkbWluQHR0ZS5jb20iLCJuaWsiOiIxMTIyMzMxMTIyMzM1NTIyIiwiZXhwIjoxNzA1NDk3NTU0fQ.rvhu_9pZEzIodlv20iHSra0KYDt0szNRAsQr_kuTnsQ";
			$api_key="rsudrp";
			$password="rsudrp#@123456$$";

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
			// ===== PARSE RESPONSE JSON DARI BSrE =====
            $json = json_decode(trim($body), true);

            if (
                json_last_error() === JSON_ERROR_NONE &&
                isset($json['id_dokumen']) &&
                isset($json['base64_signed_file'])
            ) {

                $id_dokumen = $json['id_dokumen'];

                // Decode base64 PDF hasil TTE
                $pdf_binary = base64_decode($json['base64_signed_file']);

                if ($pdf_binary === false) {
                    $status = false;
                    $pesan = "Gagal decode base64 PDF hasil TTE.";
                    echo json_encode(["status"=>$status,"msg"=>$pesan]);
                    exit;
                }

                // Simpan PDF hasil TTE
                $nama_file_modif = $id_dokumen . ".pdf";
                $file_path = $folder_simpan . $nama_file_modif;
                file_put_contents($file_path, $pdf_binary);

            } else {
                $status = false;
                $pesan = "Gagal proses TTE. JSON response tidak valid.";
                echo json_encode(["status"=>$status,"msg"=>$pesan]);
                exit;
            }


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
                        '" . escape($connection1, $id_file) . "',
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
        $pesan = "Nama file hanya boleh karakter alfanumerik.";
    }
} else {
    $status = false;
    $pesan = "Instruksi tidak dikenal.";
}

echo json_encode(["status" => $status, "msg" => $pesan]);
?>
