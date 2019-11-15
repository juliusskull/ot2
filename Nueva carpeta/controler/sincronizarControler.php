<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/sincronizar.php";


if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="sincronizar"){
        if(isset($_GET["accion"])){
            set_accion_sincronizar($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="sincronizar"){
        if(isset($_POST["accion"])){
            set_accion_sincronizar($_POST["accion"]);

        }
    }

}
function set_accion_sincronizar($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Sincronizar();
    switch($accion){
        case "add":
        try {
            $new_obj=getSincronizarPost();
            $obj=$new_obj;
            $obj->commit();
             $output1 = array('status' => true, 'massage' =>"OK" );
               echo json_encode($output1);
            }
               catch(Exception $e) {
                $output = array('status' => false, 'massage' =>"error" , 'error' =>"error");
                echo json_encode($output);
            }
        break;
        case "id":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("sincronizarOne.html");
            $array    = $obj->getOne($id);
            echo $template->render(array("CAMPOS" => $array[0],"ESTADOS"=>get_estados()));
        break;
        case "new":

            $template = $twig->loadTemplate("sincronizarOne.html");
            $array = ["" => $obj->id ];
            echo $template->render(array("CAMPOS" => $array));
        break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("sincronizarver.html");
            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0]));
        break;
             case "delete":
            $id=$_GET["id"];

            if($obj->delete("", $id)){
                $output = array('status' => true, 'massage' =>get_message("borrar-usuario") );
            }else{
                $output = array('status' => false, 'massage' =>get_message("modificacion-error") , 'error' =>"");
            }
            echo json_encode($output);
            break;
        case "all":
            $template = $twig->loadTemplate("sincronizarall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;

         case "json":
            echo $obj->getAll_json();

        break;
    }



}function getSincronizarPost(){
   $obj = new Sincronizar();
   $obj->id=$_POST["id"];
   $obj->envio=$_POST["envio"];
   $obj->tipo=$_POST["tipo"];
   $obj->valor=$_POST["valor"];
   $obj->lat=$_POST["lat"];
   $obj->lng=$_POST["lng"];
   $obj->fchalta=$_POST["fchalta"];
   $obj->usuario=$_POST["usuario"];
   $obj->imei=$_POST["imei"];
   $obj->precision=$_POST["precision"];
   $obj->gps=$_POST["gps"];
   $obj->red=$_POST["red"];
   $obj->version=$_POST["version"];
   $obj->aplicacion=$_POST["aplicacion"];
   $obj->actualizada=$_POST["actualizada"];
    return $obj;
}
