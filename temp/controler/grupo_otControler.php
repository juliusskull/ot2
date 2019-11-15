<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/grupo_ot.php";


if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="grupo_ot"){
        if(isset($_GET["accion"])){
            set_accion_grupo_ot($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="grupo_ot"){
        if(isset($_POST["accion"])){
            set_accion_grupo_ot($_POST["accion"]);

        }
    }

}
function set_accion_grupo_ot($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Grupo_ot();
    switch($accion){
        case "add":
        try {
            $new_obj=getGrupo_otPost();
            $obj=$new_obj;
            $obj->commit();
            $output = array('status' => true, 'massage' =>"modificacion" );
                echo json_encode($output);
            }
               catch(Exception $e) {
                $output = array('status' => false, 'massage' =>"modificacion-error", 'error' =>$e->getMessage());
                echo json_encode($output);
            }
        break;
        case "id":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("grupo_otOne.html");
            $array    = $obj->getOne($id);
            echo $template->render(array("CAMPOS" => $array[0],"ESTADOS"=>get_estados()));
        break;
        case "new":

            $template = $twig->loadTemplate("grupo_otOne.html");
            $array = ["" => $obj->id_grupo_ot ];
            echo $template->render(array("CAMPOS" => $array));
        break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("grupo_otver.html");
            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0]));
        break;
             case "delete":
            $id=$_GET["id"];

            if($obj->delete("id_grupo_ot", $id)){
                $output = array('status' => true, 'massage' =>'' );
            }else{
                $output = array('status' => false, 'massage' =>'' , 'error' =>"");
            }
            echo json_encode($output);
            break;
        case "all":
            $template = $twig->loadTemplate("grupo_otall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;

         case "json":
            echo $obj->getAll_json();

        break;
    }



}function getGrupo_otPost(){
   $obj = new Grupo_ot();
   //$obj->id_grupo_ot=$_POST["id_grupo_ot"];
   $obj->ot=$_POST["ot"];
   $obj->id_grupo=$_POST["id_grupo"];
   //$obj->fchalta=$_POST["fchalta"];
    return $obj;
}
