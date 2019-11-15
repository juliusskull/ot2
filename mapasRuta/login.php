<?php
$server = $_SERVER['PHP_SELF'];
$server = substr_replace($server, '', strrpos($server, "/")+1, strlen($server));
?>
<html>
<head>


<link rel="stylesheet" type="text/css" href="themes/mapas.css">
<link href="themes/bootstrap.min.css" rel="stylesheet"> 
<body>
 </head>
 <div id="titulo_img" align="center" >
	<img src="img/logoagua.gif">
 </div>
 <div id="titulo" >
	<h4 >Login</h4>
 </div>
 <div id="contenedor" align="center">  

<form  method="post" class="form-horizontal" role="form" id="login">
	

	<input type="text" id="mapa_id" name="mapa_id" style="display: none;" value="1" />
	
	<div class="form-group">		
		<div class="col-lg-4">			
		</div>
		<label for="user" class="col-lg-2 control-label">Usuario</label>
	    <div class="col-lg-2">
	    	<input type="text" id="user"  name="user" />	      
	    </div>
	    <div class="col-lg-4">			
		</div>
	</div>    
	<div class="form-group">			    
		<div class="col-lg-4">			
		</div>		
		<label for="password1" class="col-lg-2 control-label">Password</label>		
	    <div class="col-lg-2">
	    	<input type="password" id="password1"  name="password1" />	      
	    </div>
	    <div class="col-lg-4">			
		</div>
	</div>    

	<div class="form-group" >		
		<div class="col-lg-4">			
		</div>
	    <label for="distrito" class="col-lg-2 control-label">Distrito</label>
	    <div class="col-lg-2">
	      <select class="form-control" id="distrito" name="distrito" style="width: 200px">
	        <!-- <option value="1" selected>Salta</option>
	        <option value="2" >Or&aacute;n</option>  -->
	      </select>    
	    </div>
	    <div class="col-lg-4">			
		</div>
  	</div>
  	<div class="form-group" >		
		<div class="col-lg-4">			
		</div>
	    <label for="gerencia" class="col-lg-2 control-label">Gerencia</label>
	    <div class="col-lg-2">
	      <select class="form-control" id="gerencia" name="gerencia" style="width: 200px">
	        <!-- <option value="GS" selected>Servicios</option>
	        <option value="GT" >T&eacute;cnica</option>  -->
	      </select>    
	    </div>
	    <div class="col-lg-4">			
		</div>
  	</div>
	
	<input type="submit" value="Conectar"  class="btn btn-primary"/>
	
</form>
 </div> 
<script src="../mapasCompartido/js/jquery-1.11.1.min.js"></script>
<script src="../mapasCompartido/js/jquery-ui-1.11.2/jquery-ui.js"></script>

<script src="../mapasCompartido/js/config.js"></script> 
<script src="../mapasCompartido/js/login.js"></script> 

<script type="text/javascript">
$(document).ready(function() {
	crearListaGerencias($('#mapa_id').val());
	crearListaDistritos($('#mapa_id').val());
	inicio.loguin("<?php echo $server ?>");	
}
);
</script>  

</body>
</html>
