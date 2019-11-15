<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/pasos.php";


if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="pasos"){
        if(isset($_GET["accion"])){
            set_accion_pasos($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="pasos"){
        if(isset($_POST["accion"])){
            set_accion_pasos($_POST["accion"]);

        }
    }

}
function set_accion_pasos($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Pasos();
    switch($accion){
        case "add":
        try {
            $new_obj=getPasosPost();
            $obj=$new_obj;
            $obj->commit();
             $output = array('status' => true, 'massage' =>"Ok" );
                echo json_encode($output);
            }
               catch(Exception $e) {
                $output = array('status' => false, 'massage' =>"Error" , 'error' =>$e->getMessage());
                echo json_encode($output);
            }
        break;
        case "id":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("pasosOne.html");
            $array    = $obj->getOne($id);
            echo $template->render(array("CAMPOS" => $array[0],"ESTADOS"=>get_estados()));
        break;
        case "new":

            $template = $twig->loadTemplate("pasosOne.html");
            $array = ["_id" => $obj->_id ];
            echo $template->render(array("CAMPOS" => $array));
        break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("pasosver.html");
            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0]));
        break;
             case "delete":
            $id=$_GET["id"];

            if($obj->delete("_id", $id)){
                $output = array('status' => true, 'massage' =>get_message("borrar-usuario") );
            }else{
                $output = array('status' => false, 'massage' =>get_message("modificacion-error") , 'error' =>"");
            }
            echo json_encode($output);
            break;
        case "all":
            $template = $twig->loadTemplate("pasosall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;

         case "json":
            if(isset($_GET["ot"])){
                echo $obj->getAll_json("ot=".intval($_GET["ot"]));
            }


        break;
    }



}function getPasosPost(){
   $obj = new Pasos();
   $obj->id_paso=0;/*$_POST["id_paso"]*/
   $obj->desc_campo=$_POST["desc_campo"];
   $obj->tipo=$_POST["tipo"];
   $obj->foto=$_POST["foto"];
   $obj->obligatorio=$_POST["obligatorio"];
   $obj->_id=$_POST["_id"];
   $obj->ot=$_POST["ot"];
    return $obj;
}
