<?php 
/*
$zip = new ZipArchive();
$zip->open("comprimido.zip",ZipArchive::CREATE);
$zip->addFile("salida/index.html","salida/index.html");
$zip->close();
*/

function create_zip($files=array(),$zip_name='',$overwrite = false){

    $valid_files = array();

   
      foreach($files as $file) {
		//echo"Entro<p/>";
        if(file_exists($file)) {
		  //echo"Entro 1<p/>";
          $valid_files[] = $file;
        }
      }
   

    if(count($valid_files)) {
      $zip = new ZipArchive();
	  //echo"Entro crate :$zip_name<p/>";
      if($zip->open($zip_name, ZIPARCHIVE::CREATE)!==TRUE){
		 //echo"Entro false<p/>";
        //return false;
      }
      foreach($valid_files as $file) {
	    //echo"Entro add<p/>";
        $zip->addFile($file,$file);
      }
      
	  $zip->close();
	  
	  if(file_exists($zip_name)){
		
		/*header('Content-type: application/zip');
		header('Content-Disposition: attachment; filename="'.$zip_name.'"');*/
		readfile($zip_name);
		
		/*unlink($zip_name);*/
	  }
	  	  
    }else{
        return false;
    }
}
/*
$files = array(
  'salida/d269.exe'
  ,'salida/portable/index.html'
  ,'salida/portable/css/app.css'
  ,'salida/portable/css/blog-post.css'
  ,'salida/portable/css/bootstrap.css'
 ,'salida/portable/css/appie.css'
  
   ,'salida/portable/libs/ie-emulation-modes-warning.js'   
   ,'salida/portable/libs/jquery.js'
   ,'salida/portable/libs/jquery.mask.min.js'   
  ,'salida/portable/js/app.js' 
  	 ,'salida/portable/js/ctrl.js'
	 ,'salida/portable/js/config.js'
	 ,'salida/portable/js/jspdf.debug.js'
	 ,'salida/portable/js/index_pdf.js'	
 
 
  
);
create_zip($files,'salida/app.zip');
*/

?>