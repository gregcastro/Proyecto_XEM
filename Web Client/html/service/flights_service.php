<?php

/* Redirect to the login page - EL  */

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, 'http://www.airportaruba.com/times/get.php');
// echo "Esto es ch1: ". $ch;
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_TIMEOUT, 30);
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, ['key' => 'M5W9%Dg^WEf43']);

$response = curl_exec($ch);
echo $response;

?>
