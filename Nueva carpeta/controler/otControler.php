<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/ot.php";


if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="ot"){
        if(isset($_GET["accion"])){
            set_accion_ot($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="ot"){
        if(isset($_POST["accion"])){
            set_accion_ot($_POST["accion"]);

        }
    }

}
function set_accion_ot($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Ot();
    switch($accion){
        case "add":
        try {
            $new_obj=getOtPost();
            $obj=$new_obj;
            $last_id=$obj->commit();
             $output = array('status' => true, 'massage' =>"Ok","id"=>$last_id );
                echo json_encode($output);
            }
               catch(Exception $e) {
                $output = array('status' => false,  'massage' =>"Error" , 'error' =>$e->getMessage());
                echo json_encode($output);
            }
        break;
        /*
        case "id":
            $template_one = new Template();
            $id=$_GET["id"];
            $template = $twig->loadTemplate("otOne.html");
            $array    = $obj->getOne($id);
            echo $template->render(array("CAMPOS" => $array[0],"TEMPLATES"=>$template_one->getAll()));
        break;
        */
        case "id":

            $id=$_GET["id"];
            $template = $twig->loadTemplate("creador.html");
            $array    = $obj->getOne($id);
           // $array_empleados = [["id" => "0","nombre" => "USUARIO" ]];
            $op= new Operario();
            $array_empleados = $op->getAll();//["id" => "0","nombre" => "USUARIO" ];

            $pasos=new Pasos();
            $array_pasos=$pasos->getAll("ot=$id");

            echo $template->render(array("CAMPOS" => $array[0],"cod_empleado_asig"=>'0',"nombre_empleado_asig"=>"USUARIO", "EMPLEADOS"=>$array_empleados, "PASOS"=>$array_pasos,"MAX_ID"=>$pasos->get_max_id()));
            break;
        case "new":
            $template_one = new Template();
            $template = $twig->loadTemplate("creador.html");
            $op= new Operario();
            $array_empleados = $op->getAll();//["id" => "0","nombre" => "USUARIO" ];

            $pasos=new Pasos();
            $array = ["id" => $obj->id,"cod_empleado_asig"=>'0',"nombre_empleado_asig"=>"USUARIO", "EMPLEADOS"=>$array_empleados ];
            //$array_usuario = ["id" => $obj->id ];

            echo $template->render(array("CAMPOS" => $array,"TEMPLATES"=>$template_one->getAll(), "EMPLEADOS"=>$array_empleados,"MAX_ID"=>$pasos->get_max_id()));
        break;
        /*case "new":
            $template_one = new Template();
            $template = $twig->loadTemplate("otOne.html");
            $array = ["id" => $obj->id ];
            echo $template->render(array("CAMPOS" => $array,"TEMPLATES"=>$template_one->getAll()));
            break;*/
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("otver.html");
            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0]));
        break;
             case "delete":
            $id=$_GET["id"];

            if($obj->delete("id", $id)){
                $output = array('status' => true, 'massage' =>"Se borro la ot correctamente" );
            }else{
                $output = array('status' => false, 'massage' =>"No se pudo borrar la ot" , 'error' =>"No se pudo borrar la ot");
            }
            echo json_encode($output);
            break;
        case "all":
            $template = $twig->loadTemplate("otall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;


         case "json":

            echo $obj->getAll_json();

        break;
    }



}function getOtPost(){
   $obj = new Ot();
   $obj->id=$_POST["id"];
   $obj->nro_ot=$_POST["id"];
   $obj->id_loc=$_POST["id_loc"];
   $obj->localidad=$_POST["localidad"];
   $obj->zona=$_POST["zona"];
   $obj->id_bar=$_POST["id_bar"];
   $obj->barrio=$_POST["barrio"];
   $obj->id_cal=$_POST["id_cal"];
   $obj->calle=$_POST["calle"];
   $obj->altura=$_POST["altura"];
   $obj->id_motivo=$_POST["id_motivo"];
   $obj->motivo=$_POST["motivo"];
   $obj->cod_empleado_asig=$_POST["cod_empleado_asig"];
   $obj->nombre_empleado_asig=$_POST["nombre_empleado_asig"];
   $obj->cod_cuadrilla_asig=$_POST["cod_cuadrilla_asig"];
   $obj->contratista_asig=$_POST["contratista_asig"];
   $obj->fchalta=$_POST["fchalta"];
   if(isset($_POST["observacion"])){
       $obj->observacion=$_POST["observacion"];

   } else{
       $obj->observacion="";
   }


    return $obj;
}
