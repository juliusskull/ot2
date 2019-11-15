<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
session_start();
require_once "util.php";
require_once "../model/operario.php";


if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="operario"){
        if(isset($_GET["accion"])){
            set_accion_operario($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="operario"){
        if(isset($_POST["accion"])){
            set_accion_operario($_POST["accion"]);

        }
    }

}
function set_accion_operario($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Operario();
    switch($accion){
        case "add":
        try {
            $new_obj=getOperarioPost();

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
            $template = $twig->loadTemplate("operarioOne.html");
            $array    = $obj->getOne($id);
            echo $template->render(array("CAMPOS" => $array[0],"ESTADOS"=>get_estados()));
        break;
        case "new":
            $template = $twig->loadTemplate("operarioOne.html");
            $array = ["id" => $obj->id ,"geren"=>$_SESSION['usuario']];
            echo $template->render(array("CAMPOS" => $array));

        break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("operariover.html");
            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0]));
        break;
             case "delete":
            $id=$_GET["id"];

            if($obj->delete("id", $id)){
                $output = array('status' => true, 'massage' =>"Se borro el operario");
            }else{
                $output = array('status' => false, 'massage' =>"Se se pudo borrar el operario", 'error' =>"");
            }
            echo json_encode($output);
            break;
        case "all":
            $template = $twig->loadTemplate("operarioall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;

         case "json":
            echo $obj->getAll_json();

        break;
    }



}function getOperarioPost(){
   $obj = new Operario();
   $obj->id=$_POST["id"];
   $obj->nombre=str_replace(' ', '', $_POST["nombre"]);
   $obj->geren=$_POST["geren"];
   $obj->password=$_POST["password"];
   $obj->imei=$_POST["imei"];
  // $obj->fchalta=$_POST["fchalta"];
    return $obj;
}
