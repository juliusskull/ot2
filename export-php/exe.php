<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 16/07/19
 * Time: 13:29
 */
include_once '../model/materiales_ot.php';
include_once '../model/pasos.php';
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
function exe2($file_name,$id_grupo){
    if (($handle = fopen($file_name, "r")) !== FALSE) {
        $m= new Pasos();
        $m->delete_aux();
        while (($data = fgetcsv($handle, 1000, ";")) !== FALSE) {

            $m->desc_campo=$data[0];
            $m->tipo=$data[1];

            $m->insert_aux($m,$id_grupo);

        }
        fclose($handle);
        echo "end;</p>";
    }
}

