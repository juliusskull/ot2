<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 5/02/19
 * Time: 8:35
 */
require_once "db/database.php";
require_once "model/ot.php";
require_once "model/ot_finalizada.php";
require_once "model/sincronizar.php";

if (isset($_SERVER['HTTP_ORIGIN'])) {
    header("Access-Control-Allow-Origin: {$_SERVER['HTTP_ORIGIN']}");
    header('Access-Control-Allow-Credentials: true');
    header('Access-Control-Max-Age: 86400');
}
if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_METHOD']))
        header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
    if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']))
        header("Access-Control-Allow-Headers: {$_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']}");
}

if(isset($_GET['cen'])){

    if (isset($_GET['pdistrito']) && $_GET['pdistrito']!=null){
        $pdistrito=intval($_GET['pdistrito']);
    } else {
        $pdistrito=1;
    }
/*
    $distrito = new Distrito();
    echo $distrito->getDistrito($pdistrito);
*/
    echo '[{"DISTRITO":"1","DESCRIPCION":"Salta","LAT_CENTRO":"-24.7906758","LNG_CENTRO":"-65.4156012","ZOOM":"12"}]';

}
if(isset($_GET['lot'])){
    $where=null;
    /*
    $cuadrilla=Capataz::getCuadrilla();
    $legajos=Capataz::getLegajos();
    $servicio="c in ".$cuadrilla['s'];
    if (isset($_GET['servicio'])){
        $g=strtolower($_GET['servicio']);


        $servicio=" c  in ". $cuadrilla[$g]. " and legajo in ". $legajos[$g];
    }
*/
    if (isset($_GET['pfecha'])){

        $fecha=$_GET['pfecha'];
        $fecha=str_replace('-','/',$fecha);
        $where="   ((
            '$fecha'=" . "trim(substring(FECHA_FINALIZO,1,10))"."

            )
            or (

            '$fecha'= "."trim(substring(FECHA_INICIO,1,10))"."
            ))";
    }
    if (isset($_GET['pcuadrilla'])){
        $c=$_GET['pcuadrilla'];
        if($where!=null){
            $where.=" and ";
        }
        if(intval($c)==0){
            $legajo=$_GET['plegajo'];
            $where.=" legajo=$legajo";
        }else{
            $where.=" c=".intval($c);
        }
    }


    $ot=new Ot_finalizada();
    echo $ot->getUltimasOtJson($where);
}
if(isset($_GET['ubic'])){

    if (isset($_GET['pfecha']) && $_GET['pfecha']!=null){
        $pfecha=$_GET['pfecha'];
    } else {
        $pfecha='SYSDATE';
    }

    if (isset($_GET['servicio']) && $_GET['servicio']!=null){
        $pservicio=$_GET['servicio'];
    } else {
        $pservicio=null;
    }

    if (isset($_GET['plegajo']) && $_GET['plegajo']!=null){
        $plegajo=$_GET['plegajo'];
    } else {
        $plegajo=null;
    }

    $ubicacion = new Sincronizar();
/*
    if (isset($_GET['pimei']) && $_GET['pimei']!=null){
        $pimei=$_GET['pimei'];
        $ubicacion->setImeiSession($pimei);
    }
*/
    if ((isset($_GET['pimei']) && $_GET['pimei']!=null)
        && (isset($_GET['plegajo']) && $_GET['plegajo']!=null)){
        $pimei=$_GET['pimei'];
        echo $ubicacion->getUbicacionActualImei($pfecha,$pservicio,$plegajo,$pimei);
    } else {
        echo $ubicacion->getUbicacionActual($pfecha,$pservicio,$plegajo);
    }

    //echo $ubicacion->getUbicacionActual($pfecha,$pservicio,$plegajo);

}

if(isset($_GET['lrastreo'])){
    $where=null;
    if (isset($_GET['pfecha'])){
        $fecha=$_GET['pfecha'];
       // $where="trunc(to_date('$fecha','DD/MM/RRRR'))=trunc(to_date(FCH,'DD/MM/RRRR hh24:mi:ss'))";
        $where=" DATE_FORMAT(STR_TO_DATE(fchalta, ".'"%Y-%m-%d"),"%d-%m-%Y"'. ")='$fecha'";
    }
    /*
    if (isset($_GET['pini']) && $_GET['pini']!='-1' ){
        $pini=$_GET['pini'];
        $where.=" and to_date(to_char(to_date(FCH,'DD/MM/RRRR hh24:mi:ss'),'DD/MM/RRRR hh24'),'DD/MM/RRRR hh24') >= to_date('$fecha $pini','DD/MM/RRRR hh24')";
    }
    if (isset($_GET['pfin']) && $_GET['pfin']!='-1'){
        $pfin=$_GET['pfin'];
        $where.=" and to_date(to_char(to_date(FCH,'DD/MM/RRRR hh24:mi:ss'),'DD/MM/RRRR hh24'),'DD/MM/RRRR hh24') <= to_date('$fecha $pfin','DD/MM/RRRR hh24')";
    }
*/

    if (isset($_GET['pcuadrilla'])){

        $c=$_GET['pcuadrilla'];

      if($where!=null){
          $where.=" and ";
      }/*
      if(intval($c)==0){
          $legajo= $_GET['plegajo'];
          $where.=" usuario=".intval($legajo) ;

      }else{
          //plegajo
          $where.=" c=".intval($c) ;
      }*/
        $where.=" usuario='$c'";

    }else{
        if($where!=null){
            $where.=" and ";
        }
        $legajo= $_GET['plegajo'];
        $where.=" usuario=".intval($legajo) ;
    }
   // $legajo= $_GET['plegajo'];
   // $where.=" usuario=".intval($legajo) ;
    $rastreo=new Sincronizar();
    echo $rastreo->getAllJson2($where);
}

if(isset($_GET['sec'])){

    echo '[{
	"GEREN": "S",
	"DESCRIPCION": "PRUEBA",
	"GERENCIA": "GS",
	"DISTRITO": "1",
	"DESCRIP_DISTRITO": "SALTA",
	"LOCALIDAD_CABECERA": "1",
	"TRABAJA_CON": "OT",
	"SECCION": "0",
	"CUADRILLA": "S"
}]';
}
    if(isset($_GET['lex'])){

        echo '[{
	"ID": "USUARIO",
	"NOMBRE": "USUARIO",
	"CODIGO": "0",
	"NOMBRE_CUADRILLA": "-",
	"PIN": "123",
	"GEREN": "S"
    }]';

}