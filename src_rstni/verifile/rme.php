<?php
require "inc/fungsi.inc.php";
require "inc/configdb.inc.php";

if (!isset($_GET["cek"])) {
    echo "<pre>Unknown.</pre>";
    exit;
}

$id = escape($connection1, $_GET["cek"]);

$file = fetch_array($connection1,"
    SELECT r.id_file, r.nama_file_modif, r.direktori, r.mime_type,
           r.jenis_pemeriksaan, r.petugas, r.tgl_upload
    FROM rme_file_upload r
    WHERE r.id_file='$id'
");

if ($file == 0) {
    echo "<pre>Dokumen tidak ditemukan.</pre>";
    exit;
}
?>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Verifikasi Dokumen TTE</title>
<style>
body { font-family: Arial; background:#f4f6f9; }
.box {
    max-width: 600px; margin: 50px auto;
    background: #fff; padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0,0,0,.1);
}
.ok { color: green; font-weight: bold; }
.btn {
    display:inline-block;
    margin-top:15px;
    padding:10px 15px;
    background:#0069d9;
    color:#fff;
    text-decoration:none;
    border-radius:5px;
}
</style>
</head>
<body>

<div class="box">
    <h3>VERIFIKASI DOKUMEN ELEKTRONIK</h3>
    <hr>

    <p>Status Dokumen: <span class="ok">SAH ✔</span></p>
    <p>ID Dokumen: <b><?=htmlspecialchars($file['nama_file_modif'])?></b></p>
    <p>Penandatangan: <?=htmlspecialchars($file['petugas'])?></p>
    <p>Tanggal TTE: <?=htmlspecialchars($file['tgl_upload'] ?? '-')?></p>

    <a class="btn" href="rme2.php?cek=<?=urlencode($file['id_file'])?>" target="_blank">
        Lihat Dokumen
    </a>
</div>

</body>
</html>
