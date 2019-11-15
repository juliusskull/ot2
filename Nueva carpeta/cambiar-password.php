<?php
/**
 * Created by PhpStorm.
 * User: pc
 * Date: 27/05/18
 * Time: 21:49
 */
require_once 'Twig/Autoloader.php';

require_once 'db/database.php';
require_once 'model/usuario.php';
Twig_Autoloader::register();
$loader = new Twig_Loader_String();
$loader = new Twig_Loader_Filesystem("pages/");
$twig = new Twig_Environment($loader);
$json_url = "asset/config.json";
$json = file_get_contents($json_url);
$sistema = json_decode($json, TRUE);
$template = $twig->loadTemplate("cambiar_password.html");
$error="";
$error_valor=0;
$usuario= new Usuario();
if(isset($_POST['login'])){
    $error_valor=1;
    if(isset($_POST['username']) && $_POST['username']==''){
        $error='Error Usuario Invalido';
        $error_valor=2;
    }
        if($error_valor==1 && isset($_POST['username']) ){
           if(!$usuario->isExisteUsuario($_POST['username'])){
               $error='Error Usuario Invalido '.$_POST['username'];
               $error_valor=2;
           }
            if(!$usuario->isPassword($_POST['username'],$_POST['password'])){
                $error='Error Password Incorrecto';
                $error_valor=2;
            }


        }
    if($error_valor==1){
        if(!isset($_POST['password'])) {
            $error='Error password Invalido';
            $error_valor=2;
        }
    }

    if($error_valor==1){
        if(isset($_POST['username'])){
        $usuario->cambiar_password($_POST['username'],$_POST['password2']);
        $error='Se actualizo la contraseña';
        $error_valor=1;
        }
    }



}



    echo $template->render(
        array("CAMPOS" => "","SISTEMA"=>$sistema,"CARTEL"=>$error,"CARTEL_VALOR"=>$error_valor

        )
    );