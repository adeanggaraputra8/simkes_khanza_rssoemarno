<?php
require "inc/fungsi.inc.php";
require "inc/configdb.inc.php";
require "inc/set.inc.php";

if (!isset($_GET["cek"])) {
    echo "<pre>Unknown.</pre>";
    exit;
}

$id = escape($connection1, $_GET["cek"]);

$file = fetch_array(
    $connection1,
    "SELECT r.id_file, r.nama_file_modif, r.direktori, r.mime_type
     FROM rme_file_upload r
     WHERE r.id_file='$id'"
);

if ($file == 0) {
    echo "<pre>File tidak ditemukan.</pre>";
    exit;
}

/*
 * Karena file disimpan & diakses via URL (http://localhost/...),
 * JANGAN pakai file_exists() dan JANGAN copy().
 * Langsung tampilkan via PDF.js
 */

// URL PDF final
$nm_file = $BASE_FILE_URL . basename($file["nama_file_modif"]);

// OPTIONAL: cek URL hidup (HTTP 200)
// bisa dikomentari kalau mau lebih ringan
$headers = @get_headers($nm_file);
if ($headers === false || strpos($headers[0], '200') === false) {
    echo "<pre>Dokumen tidak ditemukan.</pre>";
    exit;
}
?>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Preview Dokumen</title>
<style>
html, body {
    margin: 0;
    padding: 0;
    height: 100%;
}
iframe {
    border: none;
    width: 100%;
    height: 100%;
}
</style>
</head>
<body>

<iframe
    src="inc/pdfjs/web/viewer.html?file=<?=urlencode($nm_file)?>"
    allowfullscreen
    webkitallowfullscreen>
</iframe>

</body>
</html>
