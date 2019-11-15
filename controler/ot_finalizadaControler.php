<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/ot_finalizada.php";


if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="ot_finalizada"){
        if(isset($_GET["accion"])){
            set_accion_ot_finalizada($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="ot_finalizada"){
        if(isset($_POST["accion"])){
            set_accion_ot_finalizada($_POST["accion"]);

        }
    }

}
function set_accion_ot_finalizada($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Ot_finalizada();
    switch($accion){
        case "add":
        try {
            $new_obj=getOt_finalizadaPost();
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
            $template = $twig->loadTemplate("ot_finalizadaOne.html");
            $array    = $obj->getOne($id);
            echo $template->render(array("CAMPOS" => $array[0],"ESTADOS"=>get_estados()));
        break;
        case "new":

            $template = $twig->loadTemplate("ot_finalizadaOne.html");
            $array = ["id" => $obj->id ];
            echo $template->render(array("CAMPOS" => $array));
        break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("ot_finalizadaver.html");
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
            $template = $twig->loadTemplate("ot_finalizadaall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;

         case "json":
            echo $obj->getAll_json();

        break;
    }



}function getOt_finalizadaPost(){
   $obj = new Ot_finalizada();
   $obj->id=$_POST["id"];
   $obj->OT=$_POST["OT"];
   $obj->fechainicio=$_POST["fechainicio"];
   $obj->fechafinalizo=$_POST["fechafinalizo"];
   $obj->idmotivofinaliza=$_POST["idmotivofinaliza"];
   $obj->lat=$_POST["lat"];
   $obj->lng=$_POST["lng"];
   $obj->altura=$_POST["altura"];
   $obj->estado=$_POST["estado"];
   $obj->t=$_POST["t"];
   $obj->fch=$_POST["fch"];
   $obj->fchalta=$_POST["fchalta"];
    return $obj;
}
