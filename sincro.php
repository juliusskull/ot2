<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 24/01/19
 * Time: 12:02
 */

require_once "model/sincronizar.php";
require_once 'db/database.php';
require_once "model/ot_finalizada.php";
require_once "model/ot_finalizada_valor.php";
require_once "model/materiales_ot.php";
/**/
/*
 * {"altura":"","codigo_cuadrilla":0,"estado":"A","fch":"31/01/2019 13:20:42","fchalta":"","fechafinalizo":"","fechainicio":"31/01/2019 13:20:42","id":0,"id_inm":0,"id_seg":0,"id_txc":0,"idmotivofinaliza":0,"lat":"-24.788725","legajo":0,"lng":"-65.41490999999999","nro_form":0,"nro_sec":0,"observacion":"","ot":1023093,"t":"usuario","_id":22855}
 * */
$data = json_decode(file_get_contents('php://input'), true);
//print_r($data);

$obj = new Sincronizar();
//$obj->id=$data["id"];

if(isset($data["envio"])){
    $obj->envio=$data["envio"];
}else{
    $obj->envio=".";
}
//$obj->envio=".";
$obj->tipo=$data["tipo"];

if(isset($data["valor"])){
    $obj->valor=$data["valor"];
}else{
    $obj->valor=".";
}
//$obj->valor=".";

$obj->lat=$data["lat"];
$obj->lng=$data["lng"];
//$obj->fchalta=$data["fchalta"];
$obj->usuario=$data["usuario"];
$obj->imei=$data["imei"];
$obj->precision=".";//$data["precision"];
$obj->gps=$data["gps"];
$obj->red=$data["red"];
$obj->version=$data["version"];
$obj->aplicacion=$data["aplicacion"];
//$obj->actualizada=$data["actualizada"];

$sincro = new Sincronizar();
$sincro->insert($obj);

if($obj->tipo=='OT'  ){
    $json= json_decode ($obj->valor);
    $ot = new Ot_finalizada();
    $ot->OT =$obj->envio;
    $ot->fechainicio=$json->fechainicio;
    $ot->fechafinalizo=$json->fechafinalizo;
    $ot->idmotivofinaliza=$json->idmotivofinaliza;
    $ot->lat=$json->lat;
    $ot->lng = $json-> lng;
    $ot->altura='';
    $ot->estado=$json->estado;
    $ot->t=$json->t;
    $ot->fch=$json->fch;

    $ot->insert($ot);


    $output = array('status' => true, 'massage' =>"OK", 'ot' =>$ot->OT,'ot2'=>$obj->envio );
    echo  json_encode($output);
}
//echo $obj->tipo."</p>";
if($obj->tipo=='MATERIALES'){
    $parsed_json= json_decode($obj->valor);
   // echo  '{"codigo":"1","ot":"","cantidad":"1","usuario":"usuario"}';
    $fecha=strftime( "%Y-%m-%d-%H-%M-%S", time() );
    $materiales_ot= new Materiales_ot();
    $materiales_ot->codigo=$parsed_json->codigo;
    $materiales_ot->ot=$parsed_json->ot;
    $materiales_ot->cantidad=$parsed_json->cantidad;
    $materiales_ot->usuario=$parsed_json->usuario;
    $materiales_ot->insert2($materiales_ot,$fecha);
    $materiales_ot->update2($materiales_ot);
    $output = array('status' => true, 'massage' =>"OK" );
    echo  json_encode($output);

}
if($obj->tipo=='PASOS'){
    //echo("ddd".$obj->valor);

    $parsed_json= json_decode($obj->valor);
    //$parsed_json = json_decode($json);
    //print_r($parsed_json);

    for($i=0;$i<count($parsed_json->data);$i++){
        $p= new Ot_finalizada_valor();
        $p->OT=$obj->envio;
        $p->valor=$parsed_json->data[$i]->valor;
        $p->paso_id=$parsed_json->data[$i]->id_paso;
        $p->insert($p);



    }
    $output = array('status' => true, 'massage' =>"OK" );
    echo  json_encode($output);


}



//echo $data["operacion"];
/*
 * {"envio":"1023093","tipo":"OT","valor":"{\"altura\":\"\",\"codigo_cuadrilla\":0,\"estado\":\"A\",\"fch\":\"31\/01\/2019 13:20:42\",\"fchalta\":\"\",\"fechafinalizo\":\"\",\"fechainicio\":\"31\/01\/2019 13:20:42\",\"id\":0,\"id_inm\":0,\"id_seg\":0,\"id_txc\":0,\"idmotivofinaliza\":0,\"lat\":\"-24.788725\",\"legajo\":0,\"lng\":\"-65.41490999999999\",\"nro_form\":0,\"nro_sec\":0,\"observacion\":\"\",\"ot\":1023093,\"t\":\"usuario\",\"_id\":22855}","lat":"-24.78872833333333","lng":"-65.41491166666667","usuario":"usuario","imei":"353108080913514","gps":0,"red":0,"version":"1.9","aplicacion":"ordenes"}
 *
 *
 * */