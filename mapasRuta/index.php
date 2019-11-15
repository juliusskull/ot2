<?php
/*
session_start();  
if (!(isset($_SESSION['pass']) || isset($_SESSION['distrito']))){
  header('Location: login.php');  
}
*/
?>
<input type="text" id="distrito1" style="display: none;" value=1  >
<input type="text" id="gerencia1" style="display: none;" value=GS  >
<input type="text" id="mapa_id" style="display: none;" value="1" />

<html lang="en">
  <head><title>Mapa de OT</title>
<meta charset="utf-8">     
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 

<link href="../mapasCompartido/css/mapas.css" rel="stylesheet"/> 
<link href="../mapasCompartido/js/jquery-ui-1.11.2/jquery-ui.css" rel="stylesheet">
<link href="../mapasCompartido/css/bootstrap.min.css" rel="stylesheet"> 
<link href="../mapasCompartido/css/estilo.css" rel="stylesheet"> 
<link href="../mapasCompartido/css/style.css" rel="stylesheet" />

<script src="../mapasCompartido/js/OpenLayers/proj4js/dist/proj4-src.js"></script>   
<script src="../mapasCompartido/js/jquery-1.11.1.min.js"></script>
<script src="../mapasCompartido/js/OpenLayers/OpenLayers.js"></script>   
<script src="../mapasCompartido/js/jquery-ui-1.11.2/jquery-ui.js"></script>
<script src="../mapasCompartido/js/animateprogress.js"></script>
<script src="../mapasCompartido/js/inicio.js"></script>

</head>
<body>   
<nav id="navi" class="navbar navbar-fixed-top " role="navigation" >

  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target=".navbar-ex1-collapse">
      <span class="sr-only">Desplegar navegación</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>           
    <a class="navbar-brand" href="#">Mapa de OT</a> 
    
  </div>
  
  <div class="collapse navbar-collapse navbar-ex1-collapse">
    <ul class="nav navbar-nav">
      <li><a href="#" id="actualizar">Actualizar Mapa</a></li>      
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
          Filtros <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="#" id="verFiltros">Modificar Filtro</a></li>
          <li><a href="#" id="verBsqOt">Buscar OT</a></li>       
        </ul>
      </li>
    </ul>

    <ul class="nav navbar-nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
          Opciones <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li> <a href="#" id="verSecuencia"><img src="icons/check.png" style="display:none" id="image_check_secuencia">&nbsp;Ver Secuencia OT</a>              
            <li><a href="#" id="verRutaReal"><img src="icons/check.png" style="display:none" id="image_check_ruta">&nbsp;Ver Ruta Real</a></li>
            <li><a href="#" id="verInmuebles"><img src="icons/check.png" style="display:none" id="image_check_inmueble">&nbsp;Ver Inmueble OT</a></li>
            <li><a href="#" id="verDemoras"><img src="icons/check.png" style="display:none" id="image_check_demoras">&nbsp;Ver Demoras</a></li>
            <li><a href="#" id="verUbicacion"><img src="icons/check.png" style="display:none" id="image_check_ubicacion">&nbsp;Ver Ubicacion Actual</a></li>
          <li><a href="#" id="verDistancias"><img src="icons/check.png" style="display:none" id="image_check_demoras">&nbsp;Calculo de Distancias</a></li>          
        </ul>
      </li>
    </ul>

    <!-- <ul class="nav navbar-nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
          Control <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="#" id="verControlCelular">Estado de Celulares</a></li>            
            <li><a href="#" id="verCuadrillas">Recursos de Cuadrillas</a></li>            
        </ul>
      </li>
    </ul>-->

    <ul class="nav navbar-nav ">      
      <li><a href="#" id="verReferencia">Referencia</a></li>
      <li><a href="javascript:window.print()" id="imprimir">Imprimir</a></li>      

    </ul>

  </div>  
  
  </nav>

  <div id="mapdiv">
  </div>

  <div id="ruteo"  style="display:none">
    Ver Secuencia OT: <input type="checkbox" id="secuencia_ot_check">   
    Ver Ruta Real: <input type="checkbox" id="ruta_check"> &nbsp; &nbsp;
    Ver Inmueble OT: <input type="checkbox" id="ot_check"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
    Ver Demoras: <input type="checkbox" id="demora_check">    
    Ver Ubicacion: <input type="checkbox" id="ubicacion_check">    
  </div>  

    
<div id="detalle_demora">
<h4>Detalle de Demoras</h4>  
<TABLE id="tabla_demora" align="center" >  
  <TR> 
    <TH>REFERENCIA</TH><TH>LATITUD</TH><TH>LONGITUD</TH><TH>DISTANCIA(metros)</TH><TH>INICIO</TH><TH>FIN</TH><TH>DURACION(minutos)</TH>
  </TR> 
</TABLE>  
</div>  

<div id="dialogControlCelular" title="Estado de Celulares" style="width:auto; text-align:center" >
<TABLE id="tabla_control_celular" align="center" style="font-size:12px; width:auto" >  
  <TR> 
    <TH>Capataz</TH><TH>Cuadrilla</TH><TH>Version</TH><TH>Ubic. GPS</TH><TH>Ubic. RED</TH><TH>Ultima Fecha</TH><TH>Ult Día Iniciado</TH><TH>Nro Línea</TH><TH>Hora Celular</TH>
  </TR> 
</TABLE>  
</div>   

<div id="dialogCuadrillas" title="Recursos de Cuadrillas" style="width:auto; text-align:center" >
<TABLE id="tabla_control_cuadrilla" align="center" style="font-size:12px; width:auto" >  
  <TR> 
    <TH>Capataz</TH><TH>Cuadrilla</TH><TH>Motobomba</TH><TH>Movilidad</TH><TH>Moto Compresor</TH><TH>Operario 1</TH><TH>Operario 2</TH><TH>Operario 3</TH>
  </TR> 
</TABLE>  
</div> 

<img id="espera" title="Buscando.." src="icons/barra.gif" width="203" height="105"/>

<!--     NUEVO     -->
<div id="dialogFiltros" title="Filtros de Busqueda" class="small"  >
  <div class="form-group">
    <label for="servicio" class="col-lg-3 control-label">Servicio</label>
    <div class="col-lg-9">
      <select class="form-control" id="servicio" name="servicio" onchange="crearListaCuadrilla()">        
      </select>    
    </div>
  </div>

  <div class="form-group">
    <label for="cuadrilla" class="col-lg-3 control-label">Cuadrilla</label>
    <div class="col-lg-9">
      <select class="form-control" id="cuadrilla" name="cuadrilla" >
        <option value="-1" selected>Todos</option>
      </select>    
    </div>
  </div>

  <div class="form-group">
    <label for="estadoFinaliza" class="col-lg-3 control-label">Estado</label>
    <div class="col-lg-9">
      <select class="form-control" id="estadoFinaliza" name="estado" >        
        <option value="1">Trabajo Terminado</option>
          <option value="2">Problema No Encontrado</option>
          <option value="3">Requiere Otra Intervención</option>
          <option value="-1">Trabajo Actual</option>
          <option value="0" selected>Todos</option>
      </select>    
    </div>
  </div>

  <div class="form-group">
    <label for="fecha" class="col-lg-3 control-label">Fecha</label>
    <div class="col-lg-9">
      <input class="form-control" id="fecha" name="fecha" size="10" >        
    </div>
  </div>

  <div class="form-inline">
    <label for="hora_ini" class="col-lg-3 control-label">Hora Inicio</label>
    
      <select class="form-control col-lg-3 col-xs-3" id="hora_ini" name="hora_ini" >        
          <option value="0">00</option>          
          <option value="1">01</option>
          <option value="2">02</option>
          <option value="3">03</option>
          <option value="4">04</option>
          <option value="5">05</option>
          <option value="6">06</option>          
          <option value="7">07</option>
          <option value="8">08</option>
          <option value="9">09</option>
          <option value="10">10</option>
          <option value="11">11</option>
          <option value="12">12</option>          
          <option value="13">13</option>
          <option value="14">14</option>
          <option value="15">15</option>
          <option value="16">16</option>
          <option value="17">17</option>
          <option value="18">18</option>          
          <option value="19">19</option>
          <option value="20">20</option>
          <option value="21">21</option>
          <option value="22">22</option>
          <option value="23">23</option>          
          <option value="-1" selected>-</option>
      </select>    
    
    
    <label for="hora_fin" class="col-lg-3 control-label">Hora Fin</label>
    
        <select class="form-control col-lg-3 col-xs-3" id="hora_fin" name="hora_fin" >        
          <option value="0">00</option>          
          <option value="1">01</option>
          <option value="2">02</option>
          <option value="3">03</option>
          <option value="4">04</option>
          <option value="5">05</option>
          <option value="6">06</option>          
          <option value="7">07</option>
          <option value="8">08</option>
          <option value="9">09</option>
          <option value="10">10</option>
          <option value="11">11</option>
          <option value="12">12</option>          
          <option value="13">13</option>
          <option value="14">14</option>
          <option value="15">15</option>
          <option value="16">16</option>
          <option value="17">17</option>
          <option value="18">18</option>          
          <option value="19">19</option>
          <option value="20">20</option>
          <option value="21">21</option>
          <option value="22">22</option>
          <option value="23">23</option>          
          <option value="-1" selected>-</option>
      </select>    
    
  </div>

  <div class="form-group">
    <div class="col-lg-offset-8 col-lg-4">      
      <button class="btn btn-primary" id="cuadrilla_btn">Filtrar</button> 
    </div>
  </div>  

</div>  


<div id="dialogReferencia" title="Referencia" style="width:auto; text-align:left;font-size:12px" >
<ul style="list-style: none;">
               <li><img src="icons/verde.png" width="20" height="20">Trabajo Terminado  (Punto GPS)</li> 
               <li><img src="icons/rojo.png" width="20" height="20">Problema No Encontrado  (Punto GPS)</li>  
               <li><img src="icons/naranja.png" width="20" height="20">Requiere Otra Intervención  (Punto GPS)</li> 
               <li><img src="icons/naranja.png" width="20" height="20">Verificación Comprobada (Punto GPS)</li> 
               <li><img src="icons/verde.png" width="20" height="20">Negativa del Usuario  (Punto GPS)</li> 
               <li><img src="icons/azul.png" width="20" height="20">Trabajo Actual  (Punto GPS)</li>
               <li><img src="icons/ini.png" width="20" height="20"> OT Inicial</li>
               <li><img src="icons/fin.png" width="20" height="20"> OT Final</li>
               <li>&nbsp;<img src="icons/circulo_verde.png" width="12" height="12">&nbsp; &nbsp; Inmueble de OT - Trabajo Terminado</li>               
               <li>&nbsp;<img src="icons/circulo_rojo.png" width="12" height="12">&nbsp; &nbsp; Inmueble de OT - Problema No Encontrado</li>
               <li>&nbsp;<img src="icons/circulo_naranja.png" width="12" height="12">&nbsp; &nbsp; Inmueble de OT - Requiere Otra Intervención</li>
               <li>&nbsp;<img src="icons/circulo_naranja.png" width="12" height="12">&nbsp; &nbsp; Inmueble de OT - Verificación Comprobada</li>
               <li>&nbsp;<img src="icons/circulo_verde.png" width="12" height="12">&nbsp; &nbsp; Inmueble de OT - Negativa del Usuario</li>               
               <li>&nbsp;<img src="icons/circulo_azul.png" width="12" height="12">&nbsp; &nbsp; Inmueble de OT - Trabajo Actual</li>
               <li>&nbsp;<img src="icons/circulo_violeta.png" width="12" height="12">&nbsp; &nbsp; Inmueble de OT - Trabajo Pendiente</li>
               </ul>      
</div> 

<div id="dialogDemoras" title="Parametros de Demoras" style="text-align:center;font-size:12px" >
  <div class="form-group" >
    <label data-inline="true"  for="ot_bsq" class="col-lg-4 control-label">Distancia(metros): </label>
    <div class="col-lg-8">
      <input class="form-control"  type="text" id="distancia" size="3" value="100">        
    </div>
  </div>
  <div class="form-group" >
    <label data-inline="true" for="ot_bsq" class="col-lg-4 control-label">Tiempo(minutos): </label>
    <div class="col-lg-8">
      <input class="form-control"  type="text" id="tiempo" size="3" value="15">        
    </div>
  </div>

  <div class="form-group">
    <div class="col-lg-offset-8 col-lg-4">
      <button id="btn_demora" class="btn btn-primary">Aceptar</button>
    </div>
  </div>

</div> 

<div id="dialogBuscar" title="Buscar OT" style="text-align:center;display:none;" class="small"  >
  <div class="form-group">
    <label for="ot_bsq" class="col-lg-3 control-label">OT</label>
    <div class="col-lg-9">
      <input class="form-control"  type="text" id="ot_bsq" value="" size="8">        
    </div>
  </div>
  <div class="form-group">
    <div class="col-lg-offset-8 col-lg-4">
      <button id="btn_bsq_ot" class="btn btn-primary">Buscar</button>
    </div>
  </div>

</div> 

<div id="dialogDistancias" title="Buscar OT" style="text-align:center;font-size:12px;display:none;" >
 <ul id="controlToggle">
            <li>
                <input type="radio" name="type" value="none" id="noneToggle"
                       onclick="toggleControl(this);" checked="checked" />
                <label for="noneToggle">Navegacion</label>
            </li>
            <li>
                <input type="radio" name="type" value="line" id="lineToggle" onclick="toggleControl(this);" />
                <label for="lineToggle">Calculo de Distancias</label>
            </li>
            <li>
                <input type="radio" name="type" value="polygon" id="polygonToggle" onclick="toggleControl(this);" />
                <label for="polygonToggle">Calculo de Areas</label>
            </li>
            <li style="display:none;">
                <input type="checkbox" name="geodesic" id="geodesicToggle" onclick="toggleGeodesic(this);" />
                <label for="geodesicToggle">use geodesic measures</label>
            </li>
            <li  style="display:none;">
                <input type="checkbox" name="immediate" id="immediateToggle" onclick="toggleImmediate(this);" />
                <label for="immediateToggle">use immediate measures</label>
            </li>
        </ul>
		<div id="output">
        </div>
    <div class="form-group">
    <div class="col-lg-offset-8 col-lg-4">
      <button id="refrecar_pagina" class="btn btn-primary">Salir</button>
    </div>
    </div>    
		<!--
    <button id="refrecar_pagina" >Salir</button>
    -->
</div>

</div> 
<script src="../mapasCompartido/js/utils.js"></script> 
<script src="../mapasCompartido/js/config.js"></script> 
<script src="js/geo.js"></script> 
<script src="js/elementos.js"></script> 

<script src="js/index.js"></script> 
<script src="../mapasCompartido/js/handler.js"></script> 
<script src="../mapasCompartido/js/direction.js"></script> 
<script src="../mapasCompartido/js/buscarIniciar.js"></script>

</body>
</html>
