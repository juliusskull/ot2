<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/template_view.php";
require_once "../model/pasos.php";

if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="template_view"){
        if(isset($_GET["accion"])){
            set_accion_template_view($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="template_view"){
        if(isset($_POST["accion"])){
            set_accion_template_view($_POST["accion"]);

        }
    }

}
function set_accion_template_view($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Template_view();
    switch($accion){
        case "add":
        try {
            $new_obj=getTemplate_viewPost();
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
            $template = $twig->loadTemplate("template_viewOne.html");
            $array    = $obj->getOne($id);
            echo $template->render(array("CAMPOS" => $array[0],"ESTADOS"=>get_estados()));
        break;
        case "new":

            $template = $twig->loadTemplate("template_viewOne.html");
            $array = ["id" => 1 ];
            echo $template->render(array("CAMPOS" => $array));
        break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("template_viewver.html");
            $array    = $obj->getOne($id);
            $pasos=new Pasos();
            $array_pasos=$pasos->getAll("ot=$id");

            echo $template->render(array("CAMPOS" => $array[0],"PASOS"=>$array_pasos));
        break;
        case "copiar":
            $id=$_GET["id"];
            $t= new Template_view();
            $r=$t->copiar($id);
            $output = array('status' => true, 'massage' =>"" , 'ot'=>$r);
            echo json_encode($output);
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
            $template = $twig->loadTemplate("template_viewall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;

         case "json":
            echo $obj->getAll_json();

        break;
    }



}function getTemplate_viewPost(){
   $obj = new Template_view();
   $obj->id=$_POST["id"];
   $obj->motivo=$_POST["motivo"];
   $obj->cantidad_pasos=$_POST["cantidad_pasos"];
    return $obj;
}
