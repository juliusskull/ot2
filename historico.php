<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 7/11/19
 * Time: 10:46
 */

require_once 'Twig/Autoloader.php';
 require_once 'db/database.php';
require_once "model/grupo.php";
require_once "model/grupo_x_ot.php";
require_once "model/ot.php";
require_once "model/materiales_ot.php";
Twig_Autoloader::register();
$loader = new Twig_Loader_String();
$loader = new Twig_Loader_Filesystem("pages/");
$twig = new Twig_Environment($loader);
$obj = new Grupo();
$otList= new Grupo_x_ot();
$template = $twig->loadTemplate("historico.html");
if(isset($_GET["id"])){

    $id=$_GET["id"];
    $id_grupo=$otList->get_grupo_id($id)[0];
    $otList= new Grupo_x_ot();
    $array    = $obj->getOne($id);
    $materiales_ot= new Materiales_ot();
    echo $template->render(array("CAMPOS" => $array[0],"LISTA_OT"=>$otList->getAll(" id_grupo=$id_grupo"),"IMAGENES"=>get_imagenes_ot_grupo2($id_grupo)
    ,"LISTA_MATERIALES_OT"=>$materiales_ot->getAll("id_grupo=$id_grupo")
    ,"PASOS"=>$otList->getAllPasos(" id_grupo=$id_grupo")
    ));
}
function get_imagenes_ot_grupo2($id_grupo){
    $otList= new Grupo_x_ot();
    $lista= $otList->getAll(" id_grupo=$id_grupo");
    $ficheros[]=null;
    foreach ($lista as $valor){
        $ficheros[]=get_imagenes_ot_grupo($valor["nro_ot"]);
    }
    return $ficheros;
}
function get_imagenes_ot_grupo($ot){
    //$directorio = "./img/";
    $directorio = "img/";
    $gestor_dir = opendir($directorio);

    while (false !== ($nombre_fichero = readdir($gestor_dir))) {
        $array_fichero=explode(".",$nombre_fichero);
        //echo("$nombre_fichero</p>");
        if(strtoupper($array_fichero[1])=="JPG"){
            $array_fichero2=explode("_",$nombre_fichero);
            if($array_fichero2[0]==$ot){

                $ficheros[] = array("OT"=>$ot,"NOMBRE"=>$nombre_fichero);
            }

        }


    }
    return $ficheros;
}