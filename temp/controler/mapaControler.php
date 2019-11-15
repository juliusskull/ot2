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
        case "filtro":
            $template = $twig->loadTemplate("mapa2.html");
            $sincronizar= new Sincronizar();
            $fch=null;
            $DIA=date("d");
            $MES=date("m");

            if(isset($_GET['fch'])){
                $fch=$_GET['fch'];
                $array_fecha=explode("-",$fch);
                $DIA=$array_fecha[2];
                $MES=$array_fecha[1];
            }
            $usuario=null;
            if(isset($_GET['usuario'])){
                $usuario=$_GET['usuario'];
            }
            $filtrar=0;
            if(isset($usuario) || isset($fch)){
                $filtrar=1;
                $array    = $sincronizar->get_filtrar_ubicacion($fch,$usuario);

            }else{
                $array=[];
            }


            $operario = new Operario();
            $operario_array    = $operario->getAll();



            echo $template->render(array("CAMPOS" => $array,"DIA"=>$DIA,"MES"=>$MES,"OPERARIOS" =>$operario_array,"FILTRAR"=>$filtrar));
            break;
    }
}