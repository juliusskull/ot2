<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/materiales.php";


if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="materiales"){
        if(isset($_GET["accion"])){
            set_accion_materiales($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="materiales"){
        if(isset($_POST["accion"])){
            set_accion_materiales($_POST["accion"]);

        }
    }

}
function set_accion_materiales($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Materiales();
    switch($accion){
        case "add":
        try {
            $new_obj=getMaterialesPost();
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
            $template = $twig->loadTemplate("materialesOne.html");
            $array    = $obj->getOne($id);
            echo $template->render(array("CAMPOS" => $array[0],"ESTADOS"=>get_estados()));
        break;
        case "new":

            $template = $twig->loadTemplate("materialesOne.html");
            $array = ["id_material" => $obj->id_material ];
            echo $template->render(array("CAMPOS" => $array));
        break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("materialesver.html");
            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0]));
        break;
        case "all":
            $template = $twig->loadTemplate("materialesall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;

         case "json":
            echo $obj->getAll_json();

        break;
    }



}function getMaterialesPost(){
   $obj = new Materiales();
   $obj->id_material=$_POST["id_material"];
   $obj->codigo=$_POST["codigo"];
   $obj->desc_material=$_POST["desc_material"];
   $obj->observacion=$_POST["observacion"];
   $obj->umb=$_POST["umb"];
    return $obj;
}
