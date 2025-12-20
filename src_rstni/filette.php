<?php
error_reporting(0);
require "../../conf/conf.php";   // pastikan ini berisi koneksi DB + fungsi Tambah()

// folder tujuan absolut di server (relatif dari filette.php)
$folder_upload = __DIR__ . "/upload/";

// pastikan folder upload ada dan writable
if (!is_dir($folder_upload)) {
    mkdir($folder_upload, 0777, true);
}

// cek apakah ada file dikirim
if (isset($_FILES['file'])) {

    $no_rawat      = isset($_POST['no_rawat']) ? validTeks($_POST['no_rawat']) : "";
    $kode          = isset($_POST['jns_dokumen']) ? validTeks($_POST['jns_dokumen']) : "";
    $nomr          = isset($_POST['nomr']) ? validTeks($_POST['nomr']) : "";
    $petugas       = isset($_POST['petugas']) ? validTeks($_POST['petugas']) : "";
    $status_rawat  = isset($_POST['status_rawat']) ? validTeks($_POST['status_rawat']) : "";
    $nama_file_modif = isset($_POST['nama_file_modif']) ? validTeks($_POST['nama_file_modif']) : $_FILES['file']['name'];

    // pastikan nama file aman (tanpa karakter aneh)
    $nama_file_modif = preg_replace("/[^A-Za-z0-9._-]/", "_", $nama_file_modif);

    $file_target = $folder_upload . basename($nama_file_modif);
    $rel_path    = "pages/upload/" . basename($nama_file_modif);

    // pindahkan file hasil upload
    if (move_uploaded_file($_FILES['file']['tmp_name'], $file_target)) {

        // simpan ke tabel berkas_digital_perawatan
        if (Tambah("berkas_digital_perawatan", "'$no_rawat','$kode','$rel_path'", "Berkas Digital Perawatan")) {
            http_response_code(200);
            echo json_encode(["status" => true, "msg" => "Upload dan simpan berhasil"]);
        } else {
            http_response_code(200);
            echo json_encode(["status" => false, "msg" => "Upload berhasil tapi gagal simpan DB"]);
        }

    } else {
        http_response_code(200);
        echo json_encode([
            "status" => false,
            "msg" => "Gagal memindahkan file upload ke $file_target",
            "debug" => $_FILES['file']['error']
        ]);
    }

} else {
    http_response_code(200);
    echo json_encode(["status" => false, "msg" => "Tidak ada file yang dikirim"]);
}
?>
