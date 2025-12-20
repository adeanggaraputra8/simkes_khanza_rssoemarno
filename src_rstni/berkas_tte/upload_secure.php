<?php
header("Content-Type: application/json");

$data = json_decode(file_get_contents("php://input"));
//sesuaikan $basedir dengan direktory
$baseDir = "/opt/lampp/htdocs/berkas_tte/temp/";

if (empty($data->nama_file) || empty($data->file_base64)) {
    echo json_encode(["status"=>false, "msg"=>"Parameter tidak lengkap"]);
    exit;
}

$filePath = $baseDir . basename($data->nama_file);
file_put_contents($filePath, base64_decode($data->file_base64));

echo json_encode(["status"=>true, "msg"=>"File tersimpan di temp"]);
?>
