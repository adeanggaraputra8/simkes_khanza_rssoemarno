<?php

$server1 = 'localhost';
$username1 = 'root';
$password1 = '';
$database1 = 'sik';

$connection1 = mysqli_connect($server1,$username1,$password1,$database1);

if(!$connection1)
{
	echo "Koneksi gagal!" . mysqli_connect_error();
	die();
} 
?>
