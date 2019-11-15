<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 11/01/18
 * Time: 13:11
 */
require_once "util.php";
require_once "../model/grupo.php";
require_once "../model/grupo_x_ot.php";
require_once "../model/ot.php";
require_once "../model/materiales_ot.php";
if(isset($_GET["tabla"])){
    if($_GET["tabla"]=="grupo"){
        if(isset($_GET["accion"])){
            set_accion_grupo($_GET["accion"]);
        }
    }
}

if(isset($_POST["tabla"])){
    if($_POST["tabla"]=="grupo"){
        if(isset($_POST["accion"])){
            set_accion_grupo($_POST["accion"]);

        }
    }

}
function set_accion_grupo($accion){
    Twig_Autoloader::register();
    $loader = new Twig_Loader_String();
    $loader = new Twig_Loader_Filesystem("../views/");
    $twig = new Twig_Environment($loader);

    $obj = new Grupo();
    switch($accion){
        case "add":
        try {
            $new_obj=getGrupoPost();
            $obj=$new_obj;
            $obj->commit();
             $output = array('status' => true, 'massage' =>'','id'=>$obj->id_grupo );
                echo json_encode($output);
            }
               catch(Exception $e) {
                $output = array('status' => false, 'massage' =>'', 'error' =>$e->getMessage());
                echo json_encode($output);
            }
        break;
        case "id":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("grupoOne.html");
            $array    = $obj->getOne($id);
            $otList= new Grupo_x_ot();
            $ot= new Ot();
            $materiales_ot= new Materiales_ot();
            echo $template->render(array("CAMPOS" => $array[0],"ESTADOS"=>get_estados(),"LISTA_OT"=>$otList->getAll(" id_grupo=$id"),"LISTA_OT_NEW"=>$ot->getAll()
                ,"LISTA_MATERIALES_OT"=>$materiales_ot->getAll("id_grupo=$id")
            ));
        break;
        case "new":

            $template = $twig->loadTemplate("grupoOne.html");
            $array = ["id_grupo" => $obj->id_grupo ];
            echo $template->render(array("CAMPOS" => $array));
        break;
        case "new_cavecera":

            $template = $twig->loadTemplate("grupoOneCavecera.html");
            $array = ["id_grupo" => $obj->id_grupo ];
            echo $template->render(array("CAMPOS" => $array));
            break;
        case "ver":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("grupover.html");
            $otList= new Grupo_x_ot();
            $array    = $obj->getOne($id);

            echo $template->render(array("CAMPOS" => $array[0],"LISTA_OT"=>$otList->getAll(" id_grupo=$id"),"IMAGENES"=>get_imagenes_ot_grupo2(1)));
        break;
        case "upload_p1":
            $id=$_GET["id"];
            $template = $twig->loadTemplate("grupo_upload.html");
            $array    = $obj->getOne($id);
            echo $template->render(array("CAMPOS" => $array[0],"ID_GRUPO"=>$id));
            break;

        case "delete":
            $id=$_GET["id"];

            if($obj->delete("id_grupo", $id)){
                $output = array('status' => true, 'massage' =>"borrar-usuario" );
            }else{
                $output = array('status' => false, 'massage' =>"modificacion-error" , 'error' =>"");
            }
            echo json_encode($output);
            break;

        case "all":
            $template = $twig->loadTemplate("grupoall.html");
            $array    = $obj->getAll();
            echo $template->render(array("CAMPOS" => $array));
        break;

         case "json":
            echo $obj->getAll_json();

        break;
    }



}function getGrupoPost(){
   $obj = new Grupo();
   if(isset($_POST["id_grupo"])){
       $obj->id_grupo=$_POST["id_grupo"];
   }

   $obj->descripcion=$_POST["descripcion"];
   if(isset($_POST["fchalta"])){
        $obj->fchalta=$_POST["fchalta"];
    }
    return $obj;
}
function get_imagenes_ot_grupo2($id_grupo){
    $otList= new Grupo_x_ot();
    $lista= $otList->getAll(" id_grupo=$id_grupo");
    $ficheros[]=null;
    foreach ($lista as $valor){
        $ficheros[]=get_imagenes_ot_grupo(1);
    }
    retun
}
function get_imagenes_ot_grupo($ot){
    //$directorio = "./img/";
    $directorio = "../img/";
    $gestor_dir = opendir($directorio);

    while (false !== ($nombre_fichero = readdir($gestor_dir))) {
        $array_fichero=explode(".",$nombre_fichero);
        //echo("$nombre_fichero</p>");
        if(strtoupper($array_fichero[1])=="JPG"){
            $array_fichero2=explode("_",$nombre_fichero);
            if($array_fichero2[0]==$ot){

                $ficheros[] = array("OT"=>$ot,"NOMBRE"=>$nombre_fichero);
            }

        }


    }
    return $ficheros;
}