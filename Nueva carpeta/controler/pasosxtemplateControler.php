<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/pasosxtemplate.php";


if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="pasosxtemplate"){
        if(isset($_GET["accion"])){
            set_accion_pasosxtemplate($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="pasosxtemplate"){
        if(isset($_POST["accion"])){
            set_accion_pasosxtemplate($_POST["accion"]);

        }
    }

}
function set_accion_pasosxtemplate($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Pasosxtemplate();
    switch($accion){
        case "add":
        try {
            $new_obj=getPasosxtemplatePost();
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
            $template_one = new Template();
            $pasos_one= new Pasos();
            $template = $twig->loadTemplate("pasosxtemplateOne.html");
            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0],"TEMPLATES"=>$template_one->getAll(),"PASOS"=>$pasos_one->getAll()));
        break;
        case "new":
            $template_one = new Template();
            $pasos_one= new Pasos();
            $template = $twig->loadTemplate("pasosxtemplateOne.html");
            $array = ["id_pasosxtemplate" => $obj->id_pasosxtemplate ];
            echo $template->render(array("CAMPOS" => $array,"TEMPLATES"=>$template_one->getAll(),"PASOS"=>$pasos_one->getAll()));
        break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("pasosxtemplatever.html");


            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0]));
        break;
             case "delete":
            $id=$_GET["id"];

            if($obj->delete("id_pasosxtemplate", $id)){
                $output = array('status' => true, 'massage' =>get_message("borrar-usuario") );
            }else{
                $output = array('status' => false, 'massage' =>get_message("modificacion-error") , 'error' =>"");
            }
            echo json_encode($output);
            break;
        case "all":
            $template = $twig->loadTemplate("pasosxtemplateall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;

         case "json":
            echo $obj->getAll_json();

        break;
    }



}function getPasosxtemplatePost(){
   $obj = new Pasosxtemplate();
   $obj->id_pasosxtemplate=$_POST["id_pasosxtemplate"];
   $obj->id_paso=$_POST["id_paso"];
   $obj->id_template=$_POST["id_template"];
    return $obj;
}
