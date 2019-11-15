<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/ot_finalizar_view.php";
require_once "../model/ot_finalizada_valor.php";

if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="ot_finalizar_view"){
        if(isset($_GET["accion"])){
            set_accion_ot_finalizar_view($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="ot_finalizar_view"){
        if(isset($_POST["accion"])){
            set_accion_ot_finalizar_view($_POST["accion"]);

        }
    }

}
function set_accion_ot_finalizar_view($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Ot_finalizar_view();
    switch($accion){
        case "add":
        try {
            $new_obj=getOt_finalizar_viewPost();
            $obj=$new_obj;
            $obj->commit();
             $output = array('status' => true, 'massage' =>"" );
                echo json_encode($output);
            }
               catch(Exception $e) {
                $output = array('status' => false, 'massage' =>"ok" , 'error' =>$e->getMessage());
                echo json_encode($output);
            }
        break;
        case "id":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("ot_finalizar_viewOne.html");
            $array    = $obj->getOne($id);
            $pasos=new Ot_finalizada_valor();
            $array_pasos=$pasos->getAll("ot=$id");

            //echo $template->render(array("CAMPOS" => $array[0],"ESTADOS"=>get_estados(),"PASOS"=>$array_pasos));
            echo $template->render(array("CAMPOS" => $array[0],"ESTADOS"=>get_estados(),"PASOS"=>$array_pasos,"IMAGENES"=>get_imagenes_ot($id)));
        break;
        case "new":

            $template = $twig->loadTemplate("ot_finalizar_viewOne.html");
            $array = ["id" => 0];
            echo $template->render(array("CAMPOS" => $array));

        break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("ot_finalizar_viewver.html");
            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0]));
        break;
        case "delete":
            $id=$_GET["id"];
            $obj->delete("ot", $id);
            $output = array('status' => true, 'massage' =>"se borro" );
            echo json_encode($output);

            break;
        case "all":
            $template = $twig->loadTemplate("ot_finalizar_viewall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;
        case "descarga":
            $id=$_GET["id"];
            $array    = $obj->getOne($id);

            $pasos=new Ot_finalizada_valor();
            $array_pasos=$pasos->getAll("ot=$id");


            $nombre_file=get_file_ot($id,$array,$array_pasos);

            create_zip(get_imagenes_ot($id),'ot'.$id.'.zip',$nombre_file);

            $output = array('status' => true, 'massage' =>"ok" );
            echo json_encode($output);

            break;

         case "json":
            echo $obj->getAll_json();

        break;
    }



}
function getOt_finalizar_viewPost(){
    $obj = new Ot_finalizar_view();
    $obj->ot=$_POST["ot"];
    $obj->FECHA_INICIO=$_POST["FECHA_INICIO"];
    $obj->FECHA_FINALIZO=$_POST["FECHA_FINALIZO"];
    $obj->MOT=$_POST["MOT"];
    $obj->LAT=$_POST["LAT"];
    $obj->LON=$_POST["LON"];
    $obj->LAT_INI=$_POST["LAT_INI"];
    $obj->LON_INI=$_POST["LON_INI"];
    $obj->C=$_POST["C"];
    $obj->OBS=$_POST["OBS"];
    $obj->direccion=$_POST["direccion"];
    $obj->ACTUAL=$_POST["ACTUAL"];
    $obj->MOT_OT=$_POST["MOT_OT"];
    $obj->capataz=$_POST["capataz"];
    $obj->equipos=$_POST["equipos"];
    $obj->legajo=$_POST["legajo"];
    $obj->movil=$_POST["movil"];
    return $obj;
}
function get_file_ot($ot,$ot_array,$array_pasos){

    $text="";
    foreach ($ot_array[0] as $clave=>$valor)
    {

        $text.="$clave; $valor; "."\n";
    }
    $text.="Pasos:;;"."\n";
    //$text.="Paso;Valor;"."\n";
    //var_dump($array_pasos);
    for($i=0;$i<sizeof($array_pasos);$i++){
        foreach ($array_pasos[$i] as $clave=>$valor)
        {
            if($clave!="OT" && $clave!="fchalta"){
                $nombre=str_replace("_"," ",$clave);

                $text.=" $nombre; $valor;"."\n";
            }

        }
    }

    $fecha_actual=date("YmdHis");
    $nombre_file="../archivos/reporte_ot$ot"."_"."$fecha_actual.csv";
    $fp = fopen($nombre_file,"w");
    fwrite($fp,$text);
    fclose($fp);
    return $nombre_file;
/*
    if(file_exists($nombre_file)){

        header("Content-Disposition: attachment; filename = $nombre_file");
        header ("Content-Type: application/force-download");
        header ("Content-Length: ".filesize($nombre_file));
    }
*/
//}
}
function get_imagenes_ot($ot){
    //$directorio = "./img/";
    $directorio = "../img/";
    $gestor_dir = opendir($directorio);

    while (false !== ($nombre_fichero = readdir($gestor_dir))) {
        $array_fichero=explode(".",$nombre_fichero);
        //echo("$nombre_fichero</p>");
        if(strtoupper($array_fichero[1])=="JPG"){
            $array_fichero2=explode("_",$nombre_fichero);
            if($array_fichero2[0]==$ot){

                $ficheros[] = $nombre_fichero;
            }

        }


    }
    return $ficheros;
}

function create_zip($files=array(),$zip_name='',$nombre_file,$overwrite = false){

    $valid_files = array();


    foreach($files as $file) {
       // echo"Entro<p/>";
        if(file_exists("../img/".$file)) {
            //echo"Entro 1<p/>";
            $valid_files[] = "../img/".$file;
            //echo "../img/".$file. "</p>";
        }
    }
    $valid_files[] = $nombre_file;


    if(count($valid_files)) {
        $zip = new ZipArchive();
        //echo"Entro crate :$zip_name<p/>";
        if($zip->open($zip_name, ZIPARCHIVE::CREATE)!==TRUE){
            //echo"Entro false<p/>";
            //return false;
        }
        foreach($valid_files as $file) {
            //echo"Entro add<p/>";
            $zip->addFile($file,$file);
        }

        $zip->close();

        if(file_exists($zip_name)){

            header('Content-type: application/zip');
            header('Content-Disposition: attachment; filename="'.$zip_name.'"');
            readfile($zip_name);

            unlink($zip_name);

        }

    }else{
        return false;
    }
}