<?php
session_start();  

if ($_SESSION['ot'] <> $_GET['ot']){

  	$_SESSION['ot']=$_GET['ot'];								
	$_SESSION['archivo']=1;								
} else {
	$_SESSION['archivo']=$_SESSION['archivo']+1;								
}

include_once('pConnect.class.php') ;
$query=new QueryBind();
$result=$query->parse("SELECT 
               M.ARCHIVO IMAGEN
            FROM MANTENIMIENTO.M_ADJUNTOSXSOLICITUD M
            Where ID_SOLSERV = :OT
            AND ID_ARCHIVO=:ARCHIVO            
                    ");
$query->bindVariable("OT",$_GET['ot']); 
$query->bindVariable("ARCHIVO",$_SESSION['archivo']); 

$result=$query->executeQuery(); 	 
$row = oci_fetch_array($result, OCI_ASSOC+OCI_RETURN_NULLS);
if (!$row) {
	$_SESSION['archivo']=1;								
    $result=$query->parse("SELECT 
               M.ARCHIVO IMAGEN
            FROM MANTENIMIENTO.M_ADJUNTOSXSOLICITUD M
            Where ID_SOLSERV = :OT
            AND ID_ARCHIVO=1
                    ");
	$query->bindVariable("OT",$_GET['ot']); 	
	$result=$query->executeQuery(); 	 
	$row = oci_fetch_array($result, OCI_ASSOC+OCI_RETURN_NULLS);
    if (!$row) {
        header("Content-type: image/jpeg");        
        readfile('sin_imagen.jpg');
    } else {
	   $img = $row['IMAGEN']->load();
        header("Content-type: image/jpeg");
        print $img;
    }

} else {
    $img = $row['IMAGEN']->load();
    header("Content-type: image/jpeg");
    print $img;
}
?>