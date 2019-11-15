<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/materiales_ot.php";


if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="materiales_ot"){
        if(isset($_GET["accion"])){
            set_accion_materiales_ot($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="materiales_ot"){
        if(isset($_POST["accion"])){
            set_accion_materiales_ot($_POST["accion"]);

        }
    }

}
function set_accion_materiales_ot($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Materiales_ot();
    switch($accion){
        case "add":
        try {
            $new_obj=getMateriales_otPost();
            $obj=$new_obj;
            $obj->commit();
            $output = array('status' => true, 'massage' =>"ok",'id'=>$obj->id_material_ot );
                echo json_encode($output);
            }
               catch(Exception $e) {
                $output = array('status' => false, 'massage' =>"error" , 'error' =>$e->getMessage());
                echo json_encode($output);
            }
        break;
        case "id":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("materiales_otOne.html");
            $array    = $obj->getOne($id);
            echo $template->render(array("CAMPOS" => $array[0],"ESTADOS"=>get_estados()));
        break;
        case "new":

            $template = $twig->loadTemplate("materiales_otOne.html");
            $array = ["id_material_ot" => $obj->id_material_ot ];
            echo $template->render(array("CAMPOS" => $array));
        break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("materiales_otver.html");
            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0]));
        break;
        case "all":
            $template = $twig->loadTemplate("materiales_otall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;
        case "delete":
            $id=$_GET["id"];

            if($obj->delete("id_material_ot", $id)){
                $output = array('status' => true, 'massage' =>'' );
            }else{
                $output = array('status' => false, 'massage' =>'' , 'error' =>"");
            }
            echo json_encode($output);
            break;
         case "json":
            echo $obj->getAll_json();

        break;
    }



}function getMateriales_otPost(){
   $obj = new Materiales_ot();
   if(isset($_POST["id_material_ot"])){
       $obj->id_material_ot=$_POST["id_material_ot"];
   }
   $obj->codigo=$_POST["codigo"];
   $obj->ot=$_POST["ot"];
   $obj->cantidad=$_POST["cantidad"];
   $obj->usuario=$_POST["usuario"];
   if(isset($_POST["fchalta"])){
        $obj->fchalta=$_POST["fchalta"];
   }
   if(isset($_POST["id_grupo"])){
        $obj->id_grupo=$_POST["id_grupo"];
    }

    return $obj;
}
