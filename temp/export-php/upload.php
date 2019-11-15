<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 4/11/19
 * Time: 8:30
 */
include_once 'exe.php';
if(isset($_FILES['uploadedFile'])){
    $errors= array();
    $file_name = $_FILES['uploadedFile']['name'];
    $file_size =$_FILES['uploadedFile']['size'];
    $file_tmp =$_FILES['uploadedFile']['tmp_name'];
    $file_type=$_FILES['uploadedFile']['type'];
    $file_ext=strtolower(end(explode('.',$_FILES['uploadedFile']['name'])));
    $id_grupo=$_POST['id_grupo'];
    $extensions= array("csv");

    if(in_array($file_ext,$extensions)=== false){
        $errors[]="La extencion debe ser .csv";
    }

    if($file_size > 2097152){
        $errors[]='File size must be excately 2 MB';
    }
    $file_name_final="archivos/".$file_name;

    if(empty($errors)==true){
        move_uploaded_file($file_tmp,$file_name_final);
        if (($handle = fopen($file_name_final, "r")) !== FALSE) {
            exe($file_name_final,$id_grupo);
        }else{
            $errors[]="El archivo no tiene el formato correcto";
        }

        echo "Success";
    }else{
        print_r($errors);
    }
}