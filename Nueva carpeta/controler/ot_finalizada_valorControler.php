<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/ot_finalizada_valor.php";


if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="ot_finalizada_valor"){
        if(isset($_GET["accion"])){
            set_accion_ot_finalizada_valor($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="ot_finalizada_valor"){
        if(isset($_POST["accion"])){
            set_accion_ot_finalizada_valor($_POST["accion"]);

        }
    }

}
function set_accion_ot_finalizada_valor($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Ot_finalizada_valor();
    switch($accion){
        case "add":
        try {
            $new_obj=getOt_finalizada_valorPost();
            $obj=$new_obj;
            $obj->commit();
             $output = array('status' => true, 'massage' =>get_message("modificacion") );
                echo json_encode($output);
            }
               catch(Exception $e) {
                $output = array('status' => false, 'massage' =>get_message("modificacion-error") , 'error' =>$e->getMessage());
                echo json_encode($output);
            }
        break;
        case "id":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("ot_finalizada_valorOne.html");
            $array    = $obj->getOne($id);
            echo $template->render(array("CAMPOS" => $array[0]));
        break;
        case "new":

            $template = $twig->loadTemplate("ot_finalizada_valorOne.html");
            $array = ["id" => $obj->id ];
            echo $template->render(array("CAMPOS" => $array));
        break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("ot_finalizada_valorver.html");
            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0]));
        break;
             case "delete":
            $id=$_GET["id"];

            if($obj->delete("id", $id)){
                $output = array('status' => true, 'massage' =>get_message("borrar-usuario") );
            }else{
                $output = array('status' => false, 'massage' =>get_message("modificacion-error") , 'error' =>"");
            }
            echo json_encode($output);
            break;
        case "all":
            $template = $twig->loadTemplate("ot_finalizada_valorall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;

         case "json":
            echo $obj->getAll_json();

        break;
    }



}function getOt_finalizada_valorPost(){
   $obj = new Ot_finalizada_valor();
   $obj->id=$_POST["id"];
   $obj->OT=$_POST["OT"];
   $obj->valor=$_POST["valor"];
   $obj->fchalta=$_POST["fchalta"];
   $obj->paso_id=$_POST["paso_id"];
    return $obj;
}
