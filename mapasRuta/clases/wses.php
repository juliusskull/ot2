<?php 
include_once('pConnect.class.php');

ini_set('display_errors', 1);
error_reporting(E_ALL);

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

if(isset($_GET['lot'])){	
	$query=new QueryBind();
    $result=$query->parse("SELECT 
	   R.ID_RUTA, R.PERIODO, R.NRO_RUTAXPERIODO,
	   DECODE(R.TIPO_RUTA,'C','Control','N','Normal','Normal') TIPO_RUTA, 
	   R.ID_INM, R.ID_CONEXION, nvl(R.LAT,'0.0') LAT, 
	   nvl(R.LNG,'0.0') LNG, R.FCH_ALTA, R.ID_USU_ALTA, 
	   R.ORDEN, to_char(R.FCH_TOMA,'dd/mm/yyyy hh24:mi:ss') FCH_TOMA, R.ID_USU_TOMA, 
	   NVL(R.LECTURA,-1) LECTURA, R.ID_TIPO_MEDIDOR, NVL(R.NRO_MEDIDOR_REAL,-1) NRO_MEDIDOR_REAL,
	   R.ID_TIPO_ESTADO, NVL(R.DESC_TIPO_ESTADO,' ') DESC_TIPO_ESTADO, NVL(R.OBSERVACION,' ') OBSERVACION, 
	   R.CLASE_ESTADO, nvl(R.FOTO,'-') FOTO, 
	   R.LEIDO, R.DOMICILIO, R.ID_UOP, 
	   R.ID_LOC, R.DESC_LOC, R.LNG_INM, 
	   R.LAT_INM           
	FROM DATASTORE5.RUTASXPERIODO_LEIDOS R
	    where 
	    (R.ID_LOC=:PLOC OR nvl(:PLOC,-1) =-1)
	    AND (R.ID_RUTA=:PRUTA OR :PRUTA=-1 )
	    AND (R.PERIODO=:PPERIODO OR nvl(:PPERIODO,-1) =-1 )
	    AND (R.LEIDO=:PMOSTRAR OR nvl(:PMOSTRAR,-1) =-1 )
	    AND (R.TIPO_RUTA=nvl(:PTIPO,'N'))
	    
	    ORDER BY R.FCH_TOMA"); 

    if(isset($_GET['ploc'])){ 		
 		$query->bindVariable("PLOC",$_GET['ploc']); 
 	 }	
 	if(isset($_GET['pruta'])){ 		
 		$query->bindVariable("PRUTA",$_GET['pruta']); 
 	 }	 
 	if(isset($_GET['pperiodo'])){ 		
 		$query->bindVariable("PPERIODO",$_GET['pperiodo']); 
 	 }

 	if(isset($_GET['pmostrar'])){ 		
 		$query->bindVariable("PMOSTRAR",$_GET['pmostrar']); 
 	 }
 	if(isset($_GET['ptipo'])){ 		
 		$query->bindVariable("PTIPO",$_GET['ptipo']); 
 	 } 

 	 $result=$query->executeQuery(); 	 

	$return_arr = Array();
	while ($row = oci_fetch_array($result, OCI_ASSOC)) {			
		$array2=array_map('utf8_encode', $row);
		array_push($return_arr,$array2);		
	}

	$salida= json_encode($return_arr);
	echo str_replace( "'","",str_replace("\/","/", $salida));
 }

 if(isset($_GET['med'])){	
	$query=new QueryBind();
    $result=$query->parse("SELECT 
	   R.ID_RUTA, R.PERIODO, R.NRO_RUTAXPERIODO,
	   DECODE(R.TIPO_RUTA,'C','Control','N','Normal','Normal') TIPO_RUTA,  
	   R.ID_INM, R.ID_CONEXION, nvl(R.LAT,'0.0') LAT, 
	   nvl(R.LNG,'0.0') LNG, R.FCH_ALTA, R.ID_USU_ALTA, 
	   R.ORDEN, to_char(R.FCH_TOMA,'dd/mm/yyyy hh24:mi:ss') FCH_TOMA, R.ID_USU_TOMA, 
	   NVL(R.LECTURA,-1) LECTURA, R.ID_TIPO_MEDIDOR, NVL(R.NRO_MEDIDOR_REAL,-1) NRO_MEDIDOR_REAL,
	   R.ID_TIPO_ESTADO, NVL(R.DESC_TIPO_ESTADO,' ') DESC_TIPO_ESTADO, NVL(R.OBSERVACION,' ') OBSERVACION, 
	   R.CLASE_ESTADO, nvl(R.FOTO,'-') FOTO, 
	   R.LEIDO, R.DOMICILIO, R.ID_UOP, 
	   R.ID_LOC, R.DESC_LOC, R.LNG_INM, 
	   R.LAT_INM           
	FROM DATASTORE5.RUTASXPERIODO_LEIDOS R
	    where 
	    (R.ID_INM=:ID_INM OR nvl(:ID_INM,-1) =-1)
	    AND (R.NRO_MEDIDOR_REAL=:NRO_MEDIDOR_REAL OR nvl(:NRO_MEDIDOR_REAL,-1)=-1 )	    
	    "); 

    if(isset($_GET['inmueble'])){ 		
 		$query->bindVariable("ID_INM",$_GET['inmueble']); 
 	}
	else 		{
		$query->bindVariable("ID_INM",-1); 
	}

 	if(isset($_GET['medidor'])){ 		
 		$query->bindVariable("NRO_MEDIDOR_REAL",$_GET['medidor']); 
 		}
	else 		{
		$query->bindVariable("NRO_MEDIDOR_REAL",-1); 
 	}

 	$result=$query->executeQuery(); 	 

	$return_arr = Array();
	while ($row = oci_fetch_array($result, OCI_ASSOC)) {			
		$array2=array_map('utf8_encode', $row);
		array_push($return_arr,$array2);		
	}

	$salida= json_encode($return_arr);
	echo str_replace( "'","",str_replace("\/","/", $salida));
 }

 if(isset($_GET['rutconfig'])){	
	$query=new QueryBind();
    $result=$query->parse("SELECT 
		R.ID_INM, R.ID_RUTA, R.DESC_RUTA, 
		   R.ORDEN, R.ID_TIPO_MEDIDOR, R.NRO_MEDIDOR_REAL, 
		   R.ID_UOP, R.ID_LOC, R.DESC_LOC, 
		   R.DOMICILIO, R.LNG_INM LNG, R.LAT_INM LAT
		FROM DATASTORE5.RUTAS_MEDIDORES R
		where 
			    (R.ID_LOC=:PLOC OR :PLOC =-1)
			    AND (R.ID_RUTA=:PRUTA OR :PRUTA=-1)	    
			    ORDER BY R.ORDEN"); 

    if(isset($_GET['ploc'])){ 		
 		$query->bindVariable("PLOC",$_GET['ploc']); 
 	 }	
 	if(isset($_GET['pruta'])){ 		
 		$query->bindVariable("PRUTA",$_GET['pruta']); 
 	 }	 

 	$result=$query->executeQuery(); 	 

	$return_arr = Array();
	while ($row = oci_fetch_array($result, OCI_ASSOC)) {			
		$array2=array_map('utf8_encode', $row);
		array_push($return_arr,$array2);		
	}

	$salida= json_encode($return_arr);
	echo str_replace( "'","",str_replace("\/","/", $salida));
 }

 if(isset($_GET['loc'])){
	//echo "tiene lot";
	 $query=new Query();
     $result=$query->executeQuery("SELECT   rutas.id_loc, l.desc_loc
		    FROM (SELECT DISTINCT r.id_loc
		                     FROM gestion.rutas r
		                    WHERE r.fch_cad IS NULL) rutas,
		         gestion.localidades l
		   WHERE rutas.id_loc = l.id_loc
		ORDER BY l.desc_loc"); 

	$return_arr = Array();
	while ($row = oci_fetch_array($result, OCI_ASSOC)) {
		//echo "1";
		$array2=array_map('utf8_encode', $row);
		array_push($return_arr,$array2);			
	}

	$salida= json_encode($return_arr);
	echo str_replace( "'","",str_replace("\/","/", $salida));
 }

 if(isset($_GET['rut'])){
 	$query=new QueryBind();
 	$query->parse("SELECT r.id_ruta,(r.id_ruta||' - '|| r.desc_ruta) desc_ruta
	  FROM gestion.rutas r
	 WHERE r.fch_cad IS NULL AND (r.id_loc = :id_loc OR :id_loc = -1)
	 ORDER BY r.id_ruta"); 

 	if(isset($_GET['id'])){ 		
 		$query->bindVariable("id_loc",$_GET['id']); 
 	}
 	else{
 		$query->bindVariable("id_loc",-1); 
 	}	
	
    $result=$query->executeQuery(); 


	$return_arr = Array();
	while ($row = oci_fetch_array($result, OCI_ASSOC)) {
	
		$array2=array_map('utf8_encode', $row);
		array_push($return_arr,$array2);			
	}

	$salida= json_encode($return_arr);
	echo str_replace( "'","",str_replace("\/","/", $salida));
 }

 if(isset($_GET['per'])){
 	$query=new QueryBind();
 	$query->parse("SELECT DISTINCT r.periodo
           FROM gestion.rutasxperiodo r
          WHERE r.tipo_ruta = 'N'
            AND r.fch_cad IS NULL
            AND (r.id_ruta = :id OR :id = -1)
            AND r.periodo >= 201603
       ORDER BY r.periodo DESC"); 

 	if(isset($_GET['id'])){ 		
 		$query->bindVariable("id",$_GET['id']); 
 	}
	
    $result=$query->executeQuery(); 

	$return_arr = Array();
	while ($row = oci_fetch_array($result, OCI_ASSOC)) {
	
		$array2=array_map('utf8_encode', $row);
		array_push($return_arr,$array2);			
	}

	$salida= json_encode($return_arr);
	echo str_replace( "'","",str_replace("\/","/", $salida));
 }


?>