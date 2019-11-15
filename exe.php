<?php

require_once 'db/database.php';
require_once 'model/template.php';
require_once 'model/pasos.php';
require_once 'model/pasosxtemplate.php';
include_once('zip.php');
/**
 * Created by PhpStorm.
 * User: pc
 * Date: 22/01/19
 * Time: 17:02
 */

/*
include_once('zip.php');
try {
    $files = array(
        'ordenes/ordenes.db');
    create_zip($files,'ordenes.zip');


    $output = array('status' => true, 'massage' => 'Se realizo con exito');

} catch (Exception $e) {

    $output = array('status' => true, 'massage' => err.message);
}
*/


$bd = new SQLite3('ordenes/ordenes.db');


$template= new Template();
$template_row=$template->getAll();

$bd->query('delete FROM template');
for($i=0;$i<count($template_row);$i++){
    $sql="insert into template(id_template, desc_template, observacion, titulo, _id)
        values(".$template_row[$i]["id_template"]
        .", '".$template_row[$i]["desc_template"]."'"
        .", '".$template_row[$i]["observacion"]."'"
        .", '".$template_row[$i]["titulo"]."'"
        .", ".$template_row[$i]["_id"]." )";

    ECHO "$sql</p>";
    $bd->query($sql);
}
echo"--------------</p>";
$results = $bd->query('SELECT * FROM template');
while ($row = $results->fetchArray()) {
    var_dump($row);
}
echo"</p>";

$pasos= new Pasos();
$pasos_row=$pasos->getAll();
var_dump($pasos_row);

$bd->query('delete FROM pasos');
for($i=0;$i<count($pasos_row);$i++){
    $sql="insert into pasos(id_paso, desc_campo, tipo, foto, obligatorio, _id)
        values(".$pasos_row[$i]["id_paso"]
        .", '".$pasos_row[$i]["desc_campo"]."'"
        .", '".$pasos_row[$i]["tipo"]."'"
        .", ".$pasos_row[$i]["foto"].""
        .", ".$pasos_row[$i]["obligatorio"].""
        .", ".$pasos_row[$i]["_id"]." )";

    ECHO "$sql</p>";
    $bd->query($sql);
}
echo"--------------</p>";
$results = $bd->query('SELECT * FROM pasos');
while ($row = $results->fetchArray()) {
    var_dump($row);
}
echo"</p>";

$pasosxtemplate= new Pasosxtemplate();
$pasosxtemplate_row=$pasosxtemplate->getAll();
var_dump($pasosxtemplate_row);

$bd->query('delete FROM pasosxtemplate');
for($i=0;$i<count($pasosxtemplate_row);$i++){
    $sql="insert into pasosxtemplate(id_pasosxtemplate, id_paso, id_template)
        values(".$pasosxtemplate_row[$i]["id_pasosxtemplate"]
        .", ".$pasosxtemplate_row[$i]["id_paso"].""
        .", ".$pasosxtemplate_row[$i]["id_template"].""
        ." )";

    ECHO "$sql</p>";
    $bd->query($sql);
}
echo"--------------</p>";
$results = $bd->query('SELECT * FROM pasosxtemplate');
while ($row = $results->fetchArray()) {
    var_dump($row);
}
echo"</p>";

try {
    $files = array(
        'ordenes/ordenes.db');
    create_zip($files,'ordenes.zip');


    $output = array('status' => true, 'massage' => 'Se realizo con exito');

} catch (Exception $e) {

    $output = array('status' => true, 'massage' => err.message);
}