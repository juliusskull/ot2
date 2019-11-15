<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 16/07/19
 * Time: 13:29
 */
include_once '../model/materiales_ot.php.php';

function exe($file_name,$id_grupo){
    if (($handle = fopen($file_name, "r")) !== FALSE) {

        while (($data = fgetcsv($handle, 1000, ";")) !== FALSE) {
                $m= new Materiales_ot();
                $m->codigo=$data[0];
                $m->cantidad=$data[1];
                $m->id_grupo=$id_grupo;
                $m->insert($m);

        }
        fclose($handle);
        echo "end;</p>";
    }
}


