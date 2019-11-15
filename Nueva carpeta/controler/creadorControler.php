<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";

if(isset($_GET["tabla"])){

    if($_GET["tabla"]=="creador"){
        if(isset($_GET["accion"])){

            set_accion_creador($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="creador"){
        if(isset($_POST["accion"])){
            set_accion_creador($_POST["accion"]);

        }
    }

}
function set_accion_creador($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    switch($accion){
        case "add":

            break;
        case "id":
           break;
        case "new":
            echo"dsdff";
            $template = $twig->loadTemplate("creador.html");
            $array = ["id" => 1 ];
            echo $template->render(array("CAMPOS" => $array));
            break;
        case "ver":

            break;
        case "delete":

            break;
        case "all":

            break;

        case "json":

            break;
    }



}
function getCreadorPost(){
    /*
    $obj = new Ot();
    $obj->id=$_POST["id"];
    $obj->nro_ot=$_POST["nro_ot"];
    $obj->id_loc=$_POST["id_loc"];
    $obj->localidad=$_POST["localidad"];
    $obj->zona=$_POST["zona"];
    $obj->id_bar=$_POST["id_bar"];
    $obj->barrio=$_POST["barrio"];
    $obj->id_cal=$_POST["id_cal"];
    $obj->calle=$_POST["calle"];
    $obj->altura=$_POST["altura"];
    $obj->id_motivo=$_POST["id_motivo"];
    $obj->motivo=$_POST["motivo"];
    $obj->cod_empleado_asig=$_POST["cod_empleado_asig"];
    $obj->nombre_empleado_asig=$_POST["nombre_empleado_asig"];
    $obj->cod_cuadrilla_asig=$_POST["cod_cuadrilla_asig"];
    $obj->contratista_asig=$_POST["contratista_asig"];
    $obj->fchalta=$_POST["fchalta"];
    return $obj;
    */
}
