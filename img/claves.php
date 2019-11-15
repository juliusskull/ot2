<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 26/01/18
 * Time: 11:01
 */


if(isset($_POST['clave'])){
    log1($_POST['clave']);
    if($_POST['clave']=='123'){
        $output = array('status' => true, 'message' =>"Se valido la clave correctamente" );
    }else{
        $output = array('status' => false, 'message' =>"la clave ingresada es incorrecta" );
    }

    echo json_encode($output);
}



 function log1($us){
    $ip ="";
    if (!empty($_SERVER['HTTP_CLIENT_IP'])) {
        $ip = $_SERVER['HTTP_CLIENT_IP'];
    } elseif (!empty($_SERVER['HTTP_X_FORWARDED_FOR'])) {
        $ip = $_SERVER['HTTP_X_FORWARDED_FOR'];
    } else {
        $ip = $_SERVER['REMOTE_ADDR'];
    }

    if(isset($us)){
        $myfile = fopen("log.txt", "a");// or die("Unable to open file!");

        $txt = $us.";".$ip.";".date("d/m/Y H:i:s").";"."\n";
        fwrite($myfile, $txt);
        fclose($myfile);
    }
}