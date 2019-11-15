<?php
/**
 * Created by PhpStorm.
 * User: pc
 * Date: 10/03/19
 * Time: 20:56
 */
require_once "util.php";
require_once "../model/sincronizar.php";
if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="mapa"){
        if(isset($_GET["accion"])){
            set_accion_mapa($_GET["accion"]);
        }
    }
}
function set_accion_mapa($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);
    switch($accion){
        case "add":

            break;
        case "all":
            $template = $twig->loadTemplate("mapa.html");
            $sincronizar= new Sincronizar();
            $array    = $sincronizar->get_ultima_ubicacion();
            echo $template->render(array("CAMPOS" => $array));
        break;
    }
}