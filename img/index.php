<?php
 function redimensionar_jpeg($img_original, $img_nueva, $img_nueva_anchura, $img_nueva_altura, $img_nueva_calidad)
 {
	// crear una imagen desde el original 
	$img = ImageCreateFromJPEG($img_original);

	// crear una imagen nueva 
	$thumb = imagecreatetruecolor($img_nueva_anchura,$img_nueva_altura);

	// redimensiona la imagen original copiandola en la imagen 
	ImageCopyResized($thumb,$img,0,0,0,0,$img_nueva_anchura,$img_nueva_altura,ImageSX($img),ImageSY($img));

	// guardar la nueva imagen redimensionada donde indicia $img_nueva 
	ImageJPEG($thumb,$img_nueva,$img_nueva_calidad);
	ImageDestroy($img);
 }
 $hoy = date("Ymdhms");


error_reporting(E_ALL);
if(isset($_POST['ImageName'])){
	$imgname = $_POST['ImageName'];
	$imsrc = base64_decode($_POST['base64']);
	$fp = fopen($imgname, 'w');
	fwrite($fp, $imsrc);
	
	$myfile = fopen("log.txt", "a");	           
	$txt = "$hoy img: $imgname  \n";
	fwrite($myfile, $txt);
	fclose($myfile);

	if(fclose($fp)){
		// se redimensiona la imagen
		$origen=$imgname;
		list($ancho, $alto) = getimagesize($origen);
	    $fileSize = filesize($origen);
	    $destino=$imgname;
	    $destino_temporal=tempnam("tmp/","tmp");
	    if ($fileSize >= 2097152)// tamaño > 2 MB
	    {   
	        $porcentaje = 0.3;
	        $nuevo_ancho = $ancho * $porcentaje;
	        $nuevo_alto = $alto * $porcentaje;
	        redimensionar_jpeg($origen, $destino_temporal, $nuevo_ancho, $nuevo_alto, 20);
	    }elseif ($fileSize >= 1048576 and  $fileSize < 2097152) // 1 MB < tamaño < 2 MB
	    {
	        $porcentaje = 0.4;
	        $nuevo_ancho = $ancho * $porcentaje;
	        $nuevo_alto = $alto * $porcentaje;
	        redimensionar_jpeg($origen, $destino_temporal, $nuevo_ancho, $nuevo_alto, 30);
	    }elseif ($fileSize >= 524288 and  $fileSize < 1048576) // 500 KB < tamaño < 1 MB
	    {
	        $porcentaje = 0.6;
	        $nuevo_ancho = $ancho * $porcentaje;
	        $nuevo_alto = $alto * $porcentaje;
	        redimensionar_jpeg($origen, $destino_temporal, $nuevo_ancho, $nuevo_alto, 40);
	    }elseif ($fileSize >= 104858 and $fileSize < 524288) //  100 KB < tamaño < 500 KB
	    {
	        $porcentaje = 0.7;
	        $nuevo_ancho = $ancho * $porcentaje;
	        $nuevo_alto = $alto * $porcentaje;
	        redimensionar_jpeg($origen, $destino_temporal, $nuevo_ancho, $nuevo_alto, 50);
	    }
	    // guardamos la imagen
	    $fp=fopen($destino,"w");
	    fputs($fp,fread(fopen($destino_temporal,"r"),filesize($destino_temporal)));
	    fclose($fp);    

	    echo "Image uploaded";
	}else{
	 echo "Error uploading image";

	 	     $myfile = fopen("log.txt", "a");// or die("Unable to open file!");
	           
	            $txt = "Error uploading image \n";

	            fwrite($myfile, $txt);
	            fclose($myfile);
	}
}
?>