<?php

include_once('pConnect2.class.php') ;
$query=new QueryBind();
$result=$query->parse("SELECT A.IMAGEN FROM GESTION.ARCHIVOS_ADJUNTOS A
                Where a.TIPO_ARCHIVO = 5 
                and A.ID_ARCHIVO = :P_ARCHIVO
                and A.FCH_CAD IS NULL
                    ");
$query->bindVariable("P_ARCHIVO",$_GET['p_archivo']); 
//$query->bindVariable("ARCHIVO",49131); 

$result=$query->executeQuery(); 	  
$row = oci_fetch_array($result, OCI_ASSOC+OCI_RETURN_NULLS);
    if (!$row) {
        header("Content-type: image/jpeg");        
        readfile('sin_imagen256.jpg');
    } else {
	   $img = $row['IMAGEN']->load();
        header("Content-type: image/jpeg");
        print $img;
    }


?>