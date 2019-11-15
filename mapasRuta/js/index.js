	/*
  var origen_ot_s="http://ot.aguasdelnortesalta.com.ar/wses.php?lot=0";
	var origen_rastreo_s="http://ot.aguasdelnortesalta.com.ar/wses.php?lrastreo=0"; 
	var origen_ot_asignadas_s="http://ot.aguasdelnortesalta.com.ar/wses.php?sh=0"; 
	var origen_ot_bsq_s="http://ot.aguasdelnortesalta.com.ar/wses.php?otbsq="; 
  var origen_ubicacion_actual="http://ot.aguasdelnortesalta.com.ar/wses.php?ubic=0"; 
*/
/*function obtener_equipos(equiposString){
        var pos_coma=equiposString.indexOf(",");
        var equipo1= equiposString.substring(0, pos_coma); 
        equipo1 = (equipo1 !=0)? equipo1 : '-';
        equipos='<b>Moto Bomba: </b>'+equipo1;

        var pos_coma2=equiposString.indexOf(",",pos_coma+1);
        var equipo2=equiposString.substring(pos_coma+1, pos_coma2);
        equipo2 = (equipo2 !=0)? equipo2 : '-';
        equipos=equipos+'</BR><b>Moto Compresor: </b>' + equipo2;

        var equipo3=equiposString.substr(pos_coma2+1);
        equipo3 = (equipo3 !=0)? equipo3 : '-';
        equipos=equipos+'</BR><b>Retro Escavadora: </b>'+equipo3;

        return equipos;
      }*/

function getOtAsignadas(cuadrilla,estadoFinaliza){         
      otAsignadasLayer.removeAllFeatures();      

      f = ($('#fecha').val()).replace('/','-').replace('/','-');        
      if ($('#servicio').val().toUpperCase()=='S') {        
        origen=origen_ot_asignadas+"&pcuadrilla="+cuadrilla+"&pfecha="+f; 
      } else {
        origen=origen_ot_asignadas+"&plegajo="+cuadrilla+"&pfecha="+f+"&servicio="+$('#servicio').val();                 
      }              
     
      dialogo('open'); 
      $.getJSON(origen, function(data){                  
          fechaSeleccionada = $('#fecha').val();
          var value =data ;                                              
          len=value.length;          
          
          /*var projection_4326 = new proj4.Proj('EPSG:4326');      
          proj4.defs('EPSG:22193',"+proj=tmerc +lat_0=-90 +lon_0=-66 +k=1 +x_0=3500000 +y_0=0 +ellps=intl +units=m +no_defs");
          var projection_22193 = new proj4.Proj('EPSG:22193');          
          
          var projection_4326_b   = new OpenLayers.Projection("EPSG:4326");         
          var projection_900913 = new OpenLayers.Projection("EPSG:900913");     */

          var fromProjection = new OpenLayers.Projection("EPSG:4326");   
          var toProjection   = new OpenLayers.Projection("EPSG:900913"); 
          

          for(i=0;i< len ;i++){            
            if ( value[i].NRO_OT!=''
                && (cuadrilla == value[i].CODIGO || cuadrilla == -1
                    || (value[i].CODIGO == 0 && cuadrilla == value[i].LEGAJO )
                  ) /*FILTRO CUADRILLA*/
                && (value[i].LAT!='0.0') 
                      ){
              //console.log("lat "+ value[i].LAT + ","+ value[i].LON );
             /* var lat_22193 = value[i].LAT;
              var lon_22193 = value[i].LON;
              var lonLat       = proj4(projection_22193, projection_4326,[lon_22193,lat_22193]);
          
              var lon_4326=lonLat[0];
              var lat_4326=lonLat[1];*/

              var feature = [];
              /*var pt = new OpenLayers.Geometry.Point(lon_4326 , lat_4326).transform(projection_4326_b, projection_900913);*/

              var pt = new OpenLayers.Geometry.Point(value[i].LON , value[i].LAT).transform(fromProjection, toProjection);

              feature.push(new OpenLayers.Feature.Vector(pt, {labelText: (value[i].CODIGO ==0 ? value[i].LEGAJO : value[i].CODIGO) }));
              feature.push(new OpenLayers.Feature.Vector(pt, {description:'<b>Cuadrilla:</b> '+ value[i].CODIGO +
                           '</BR> <b>Capataz:</b> '+ value[i].CAPATAZ +
                           '</BR> <b>OT:</b> '+ value[i].OT +
                           '</BR> <b>Mot:</b> '+ value[i].MOT_OT +
                           '</BR> <b>Dir:</b> '+ value[i].DIRECCION +   
                           '</BR> <b>Estado:</b> '+ getEstadoOtAsig(value[i].ACTUAL,value[i].MOT)  
                        
                           },
              {externalGraphic: 'icons/'+getIconoOtAsig(value[i].ACTUAL,value[i].MOT,value[i].PRIORIDAD)
              , graphicHeight: 12,  graphicXOffset:0, graphicYOffset:0 }));

              otAsignadasLayer.addFeatures(feature);

            }
          }
        }).done(function() {}).fail(function() {}).always(function() {map.addLayer(otAsignadasLayer); dialogo('close'); });         
      }   
/*
function obtener_ot_solapadas(lon, lat, i,value,cuadrilla){
      var cadena;
      cadena = ''; 
      for (j=i;j>=0;j--){        
        if (value[j].LON==lon && value[j].LAT==lat && value[j].C== cuadrilla ){          
          cadena=cadena + value[j].OT + ', ';
        } 
      }
      cadena = cadena.substring(0, cadena.length-2); 
      return cadena;

     }  */  
     /* 
function getIconoOtAsig(estado,motivo,prioridad){      
      var icono;      
      switch(estado) {
          case 'P':
              icono = 'circulo_violeta';
              break;
          case 'A':
              icono = 'circulo_azul'
              break;
          case 'F':
              switch(motivo){
                case '1': 
                  icono = 'circulo_verde';
                  break;
                case '2': 
                  icono = 'circulo_rojo';
                  break;
                case '3': 
                  icono = 'circulo_naranja';
                  break;
                case '4': 
                  icono = 'circulo_naranja';
                  break;
                case '5':                   
                  icono = 'circulo_verde';
                  break;        
                default:
                  icono = 'circulo_verde'; // (sin estado)
              }              
              break;          
          default:
               icono = 'circulo_verde'; // (sin estado)
      }
      if (prioridad=='1'){
        icono=icono+'_p.gif';  
      } else {
        icono = icono + '.png';  
      }
      
      return icono;
     } */
     
/*function getEstadoOtAsig(estado,motivo){
      var label;
      switch(estado) {
          case 'N':
              label = 'Pendiente';
              break;
          case 'A':
              label = 'Actual'
              break;
          case 'F':
              switch(motivo){
                case '1': 
                  label = 'Finalizado';
                  break;
                case '2': 
                  label = 'Problema No Encontrado';
                  break;
                case '3': 
                  label = 'Requiere Otra Intervención';
                  break;
                case '4': 
                  label = 'Verificación Comprobada';
                  break;
                case '5':                   
                  label = 'Negativa del Usuario';
                  break;        
                default:
                  label = 'Finalizado'; // (sin estado)
              }
              
              break;          
          default:
               label = 'Pendiente'; // (sin estado)
      }
      return label;
     }   */

function getRoute(cuadrilla,pfecha,pini,pfin){ 
      lineLayer.removeAllFeatures();  
      dirLayerRoute.removeAllFeatures();    
      vectorLayerRoute.removeAllFeatures();  
	  console.log('entro--rastreo');	
      f = pfecha.replace('/','-').replace('/','-');
	  pp_servicio=$('#servicio').val().toUpperCase();	
//      if (pp_servicio=='S'||pp_servicio=='D') {
      if (pp_servicio=='S') {
        origen=origen_rastreo+"&pfecha="+f+"&pcuadrilla="+cuadrilla+"&pini="+pini+"&pfin="+pfin;                            
      } else {
        origen=origen_rastreo+"&pfecha="+f+"&pcuadrilla=0&plegajo="+cuadrilla+"&pini="+pini+"&pfin="+pfin;             
      }      
    
  	  dialogo('open');
      $.getJSON(origen, function(data){                  
          var point_pre, point_pos, point_ult;
          var value =data;                                              
          len=value.length;
          var style = { 
                    strokeColor: '#f41523', 
                    strokeOpacity: 0.5,
                    strokeWidth: 3
                  };
          console.log("len=>"+len);
          for(i=0;i< len ;i++){                 
            /*obtengo y formateo fechas*/
            var fecha_inicio = getFecha(value[i].FCH);

            if (/*(cuadrilla == value[i].C  || (value[i].C == 0 && cuadrilla == value[i].LEGAJO ))
                && (fecha_inicio==$('#fecha').val())
                && (value[i].LON!=0)  */true
             ) 
              {
                if (!point_pre){                  
                  point_pre= new OpenLayers.Geometry.Point(value[i].LON , value[i].LAT).transform(fromProjection, toProjection);                  
                  point_pre2= new OpenLayers.Geometry.Point(value[i].LON , value[i].LAT).transform(fromProjection, toProjection);
                  var featureIcon2 = [];                  
                  featureIcon2.push(new OpenLayers.Feature.Vector(point_pre, {description:'Hora: '+ getHora(value[i].FCH)},
                  {externalGraphic: 'icons/punto_azul.png', graphicHeight: 9,  graphicXOffset:-7, graphicYOffset:-5 }));
                  vectorLayerRoute.addFeatures(featureIcon2);                  
                  var feature = [];
                  feature.push(new OpenLayers.Feature.Vector(point_pre2,null ,
                    {externalGraphic: 'icons/ini1.png', graphicHeight: 15,  graphicXOffset:-7, graphicYOffset:3 }));
                  lineLayer.addFeatures(feature);

                } else {         
                  point_ult= new OpenLayers.Geometry.Point(value[i].LON , value[i].LAT).transform(fromProjection, toProjection);

                  point_pos= new OpenLayers.Geometry.Point(value[i].LON , value[i].LAT).transform(fromProjection, toProjection);
                  
                  /*agrego icono del punto ******************************************/
                  var featureIcon = [];                                    
                  featureIcon.push(new OpenLayers.Feature.Vector(point_pos,
                   {description:'<b>Hora:</b> '+ getHora(value[i].FCH) +
                   '</BR> <b>Version:</b> '+ value[i].VERSION +
                   '</BR> <b>Ubicación por:</b> '+ ((value[i].LAT).length > 16 ? 'GPS' : 'RED')  +
                   '</BR> <b>GPS Habilitado:</b> '+ getEnabledProvider(value[i].GPS) +
                   '</BR> <b>Red Habilitado:</b> '+ getEnabledProvider(value[i].RED)
                    },
                  {externalGraphic: 'icons/punto_azul.png', graphicHeight: 9,  graphicXOffset:-7, graphicYOffset:-5
                   }));

                  vectorLayerRoute.addFeatures(featureIcon);

                  /*agrego linea entre 2 puntos*/
                  var points = new Array(point_pre ,point_pos);
                  var line = new OpenLayers.Geometry.LineString(points);

                  var lineFeature = new OpenLayers.Feature.Vector(line, null, style);
                  lineLayer.addFeatures([lineFeature]);

                  /*flecha de direccion*/
                  var pointsDireccion=[];
                  var linePoints = createDirection(lineFeature.geometry,"middle",true) ;                                      
                  pointsDireccion =pointsDireccion.concat(linePoints);                  
                  dirLayerRoute.addFeatures(pointsDireccion);

                  point_pre=point_pos;
                }
            }
          }
          /*icono de fin*/
          var feature1 = [];                  
          feature1.push(new OpenLayers.Feature.Vector(point_ult,null ,
            {externalGraphic: 'icons/fin1.png', graphicHeight: 15,  graphicXOffset:-7, graphicYOffset:3 }));
          lineLayer.addFeatures(feature1);
        }).done(function() {}).fail(function(){}).always(function() {
		map.addLayer(lineLayer);                    
        map.addControl(new OpenLayers.Control.DrawFeature(lineLayer, OpenLayers.Handler.Path));      
        map.addLayer(vectorLayerRoute); 
		dialogo('close');
		});    
     
  }   

function getSecuencia(cuadrilla){            
      secuenciaLayer.removeAllFeatures();   
      dirLayer.removeAllFeatures();            
      
      f = ($('#fecha').val()).replace('/','-').replace('/','-');                
      origen=origen_ot+"&pfecha="+f+'&servicio='+$('#servicio').val(); 
     
	    dialogo('open');
      $.getJSON(origen, function(data){                  
          var point_pre, point_pos, point_ult;
          var value =data;                                              
          len=value.length;
          var style = { 
                    strokeColor: '#0000FF', 
                    strokeOpacity: 0.5,
                    strokeWidth: 3
                  };

          for(i=0;i< len ;i++){            
            
            /*obtengo y formateo fechas*/
              var fecha_finalizo = getFecha(value[i].FECHA_FINALIZO);
              var fecha_inicio = getFecha(value[i].FECHA_INICIO);              
            if ( (cuadrilla == value[i].C
                || (value[i].C ==0 && cuadrilla == value[i].LEGAJO)
              )
                && (value[i].LON!=0)
                 && value[i].OT != ''                 
             ) 
              {

                if (!point_pre){                  
                  point_pre= new OpenLayers.Geometry.Point(value[i].LON , value[i].LAT).transform(fromProjection, toProjection);                  
                  
                  /*icono de inicio*/
                  var feature = [];                  
                  feature.push(new OpenLayers.Feature.Vector(point_pre,null ,
                    {externalGraphic: 'icons/ini.png', graphicHeight: 15,  graphicXOffset:-7, graphicYOffset:3 }));
                  secuenciaLayer.addFeatures(feature);

                } else {   
                  point_ult= new OpenLayers.Geometry.Point(value[i].LON , value[i].LAT).transform(fromProjection, toProjection);

                  point_pos= new OpenLayers.Geometry.Point(value[i].LON , value[i].LAT).transform(fromProjection, toProjection);
                  
                  var points = new Array(point_pre ,point_pos);
                  var line = new OpenLayers.Geometry.LineString(points);

                  var secuenciaFeature = new OpenLayers.Feature.Vector(line, null, style);
                  secuenciaLayer.addFeatures([secuenciaFeature]);

                  map.addLayer(secuenciaLayer);                    
                  
                  var pointsDireccion=[];
                  var linePoints = createDirection(secuenciaFeature.geometry,"middle",true) ;                    
                  
                  pointsDireccion =pointsDireccion.concat(linePoints);                  
                  dirLayer.addFeatures(pointsDireccion);

                  point_pre=point_pos;
                }
            }
            
          }
          /*icono de fin*/
          var feature1 = [];                  
          feature1.push(new OpenLayers.Feature.Vector(point_ult,null ,
            {externalGraphic: 'icons/fin.png', graphicHeight: 15,  graphicXOffset:-7, graphicYOffset:3 }));
          secuenciaLayer.addFeatures(feature1);
        }).done(function() {}).fail(function() {}).always(function() {dialogo('close'); });         
                            
      } 

function getDemora(cuadrilla,pfecha,ptiempoMin,pdistancia){   

      deleteRow("tabla_demora");

      demoraLayer.removeAllFeatures();  
      demoraMarcaLayer.removeAllFeatures();  
      f = pfecha.replace('/','-').replace('/','-');   

      if ($('#servicio').val().toUpperCase()=='S') {        
        origen=origen_rastreo+"&pfecha="+f+"&pcuadrilla="+cuadrilla;               
      } else {        
        origen=origen_rastreo+"&pfecha="+f+"&plegajo="+cuadrilla;               
      }

      ptiempo=ptiempoMin * 60; /*calculo cantidad de segundos*/
      console.log(origen);
	    dialogo('open');
      $.getJSON(origen, function(data){                  
          var point_pre, point_pos, point_base,point_ult_marcado;
          var fecha, fecha_pre, fecha_pos, fecha_ini, fecha_fin;
          var value =data;                                              
          len=value.length;          
          var i=0,j=0;
          var letra_demora='A';
          
          while(i<=len ){ 
			try {
            fecha = getFecha(value[i].FCH);                        

            if (
                (cuadrilla == value[i].C
                  || (value[i].C ==0 && cuadrilla == value[i].LEGAJO)
                )
                && (fecha==pfecha)                
                && (value[i].LON!=0) 
                )
            {
              /*obtengo punto base*/
              point_base= new OpenLayers.Geometry.Point(value[i].LON , value[i].LAT).transform(fromProjection, toProjection);                                                  
              fecha = getFechaHora(value[i].FCH);                           
              fecha_pre = new Date(fecha);            
              fecha_ini=value[i].FCH;                    
              acumuladoTiempo=0;
              acumuladoDistancia=0;
              j = i + 1;
              sw=true;
              encontro=false;
              point_pre = point_base;          

              while(j<=len && sw==true ){                    
                  fecha = getFecha(value[j].FCH);                  
                  if (
                      (cuadrilla == value[j].C
                        || (value[j].C ==0 && cuadrilla == value[j].LEGAJO)
                      )                                  
                      && (fecha==pfecha)                
                      && (value[j].LON!=0) 
                      )
                  {          
                    /*obtengo y formateo fechas*/
                    point_pos = new OpenLayers.Geometry.Point(value[j].LON , value[j].LAT).transform(fromProjection, toProjection);                  
                    fecha = getFechaHora(value[j].FCH);                                  
                    fecha_pos = new Date(fecha); 
                    fecha_fin=value[j].FCH;                               
                    
                    /*distancia en Metros */                   
                    distancia = point_pre.distanceTo(point_pos);
                    timeDiff = Math.abs(fecha_pos.getTime() - fecha_pre.getTime());
                    console.log("timeDiff: "+ timeDiff);                         
                    tiempo = Math.ceil(timeDiff / (1000));                   
                    
                    acumuladoTiempo = acumuladoTiempo + tiempo;                                  
                    acumuladoDistancia = acumuladoDistancia + distancia;

                    if ( /*si esta en una cuadra y paso el tiempo determinado, dibujo circulo*/
                      acumuladoTiempo >= ptiempo /*900 segundos, 15 minutos*/
                       && acumuladoDistancia < pdistancia /*metros,en un rango de una cuadra*/
                        ){
                     
                          encontro=true;                     
                          console.log("encontro");        

                          } else { /*si me pase de una cuadra ya no evaluo mas para este punto*/
                            if (encontro==true){
                              if (!point_ult_marcado){                               
                                           
                                    marco_demora(point_pre,value[i].LAT, value[i].LON,acumuladoDistancia- distancia,acumuladoTiempo-tiempo,letra_demora,fecha_ini, value[j-1].FCH);
                                    point_ult_marcado = point_pre;                          
                                  } else {
                                    if (point_ult_marcado.distanceTo(point_pre)>pdistancia ){                                
                                      letra_demora=letra_demora.substring(0,letra_demora.length-1)+String.fromCharCode(letra_demora.charCodeAt(letra_demora.length-1)+1);
                                         
                                      marco_demora(point_pre,value[i].LAT, value[i].LON,acumuladoDistancia- distancia,acumuladoTiempo-tiempo,letra_demora,fecha_ini, value[j-1].FCH);
                                      point_ult_marcado = point_pre;                                
                                    }  
                                  }
                              sw=false;
                            }
                        if(acumuladoDistancia > pdistancia /*en un rango de una cuadra*/ )
                              {                          
                                sw=false;
                              }
                          }                  
                  } 
                  j=++j;                  
                  fecha_pre=fecha_pos;
                  point_pre=point_pos;                  
              }
            
            }  
			}catch(err) {
					console.log(err.message);
				}            
            i=++i;            
        }                       
      }).done(function() {}).fail(function() {}) .always(function() {
   map.addLayer(demoraLayer);    
  map.addLayer(demoraMarcaLayer);    
	dialogo('close');
  });         
           
  }    

function getUbicacionActual(){            
    ubicacionActualLayer.removeAllFeatures();    
    f = $('#fecha').val().replace('/','-').replace('/','-');   
    cuadrilla=$('#cuadrilla').val();
    origen=origen_ubicacion_actual+"&pfecha="+f+"&servicio="+$('#servicio').val();
    
    $.getJSON(origen, function(data){                            
          var value =data ;                                              
          len=value.length;          

          for(i=0;i< len ;i++){  
             if (cuadrilla==-1 || cuadrilla == ((value[i].CUADRILLA == 0) ? value[i].LEGAJO : value[i].CUADRILLA)){           
              var feature = [];              
              var pt = new OpenLayers.Geometry.Point(value[i].LNG , value[i].LAT).transform(fromProjection, toProjection);

              feature.push(new OpenLayers.Feature.Vector(pt, {labelText: (value[i].CUADRILLA ==0 ? value[i].LEGAJO : value[i].CUADRILLA) }));
              feature.push(new OpenLayers.Feature.Vector(pt,
                   {description:'<b>Hora:</b> '+ getHora(value[i].FCH) +                   
                   '</BR> <b>Operario:</b> '+ value[i].NOMBRE +
                   '</BR> <b>Ubicación por:</b> '+ ((value[i].LAT).length > 16 ? 'GPS' : 'RED')                     
                    },
                  {externalGraphic: 'icons/ubicacion_actual4.png', graphicHeight: 21,  graphicXOffset:0, graphicYOffset:0
                   }));


              ubicacionActualLayer.addFeatures(feature);

           }
        }
        }).done(function() {}).fail(function() {}).always(function() {map.addLayer(ubicacionActualLayer); dialogo('close'); });                 
                            
      } 

function marco_demora(point,lat, lon, distancia,tiempo,letra_demora,fecha_ini, fecha_fin){
                      
                      var circle = OpenLayers.Geometry.Polygon.createRegularPolygon
                        (
                          point, 100, 30, 0
                        );
                      var featurecircle = new OpenLayers.Feature.Vector(circle);
                      demoraLayer.addFeatures([featurecircle]);
                      
                      /*Letra como marca de referencia*/
                      var feature = [];
                      feature.push(new OpenLayers.Feature.Vector(point, {labelText: letra_demora}));
                      demoraMarcaLayer.addFeatures(feature);
                      
                      var table = document.getElementById("tabla_demora");
 
                      var rowCount = table.rows.length;
                      var row = table.insertRow(rowCount);
                      
                      var cell0 = row.insertCell(0);
                      cell0.innerHTML = letra_demora;

                      var cell1 = row.insertCell(1);
                      cell1.innerHTML = lat;

                      var cell2 = row.insertCell(2);
                      cell2.innerHTML = lon;

                      var cell3 = row.insertCell(3);
                      cell3.innerHTML = Math.floor(distancia);

                      var cell4 = row.insertCell(4);
                      cell4.innerHTML = fecha_ini;

                      var cell5 = row.insertCell(5);
                      cell5.innerHTML = fecha_fin;

                      var cell6 = row.insertCell(6);
                      cell6.innerHTML = Math.floor(tiempo/60);

                    }

/*function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
  }  

function deleteRow(tableID) {            
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
 
            for(var i=1; i<rowCount; i++) {
                var row = table.rows[i];                
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
  }   */         
  
function filtrarMapa() {
    if ($('#fecha').val()!=''){                 
        activarMarketsNumber();
        getMarketsNumber(origen_ot,vectorLayer,$('#cuadrilla').val(),$('#estadoFinaliza').val());
        
        /*filtro Secuencia de OT si esta tildado*/
        var y = document.getElementById("secuencia_ot_check").checked;   
        if (y==true && $('#cuadrilla').val()!=-1){               
          activarSecuencia();
        } else {          
          desactivarSecuencia();
        }

        /*filtro demoras de rastreo si esta tildado*/
        var y = document.getElementById("demora_check").checked;   
        if (y==true){
          if ($('#cuadrilla').val()!=-1 && isNumber($('#tiempo').val())==true && isNumber($('#distancia').val())==true)
          {               
            getDemora($('#cuadrilla').val(),$('#fecha').val(),$('#tiempo').val(), $('#distancia').val());         
          } else {
            alert("Para ver las demoras es necesario especificar Cuadrilla, Fecha, Distancia y Tiempo correctamente");
            document.getElementById("demora_check").checked=false;
          }
        } else {
          demoraLayer.removeAllFeatures();
          demoraMarcaLayer.removeAllFeatures(); 
        }

        /*filtro UBICACION ACTUAL si esta tildado*/
        var y = document.getElementById("ubicacion_check").checked;   
        if (y==true ){                         
          activarUbicacionActual();    
        } else {          
          //ubicacionActualLayer.removeAllFeatures();         
          desactivarUbicacionActual();
        }

        /*filtro OT ASIGNADAS si esta tildado*/
        var y = document.getElementById("ot_check").checked;   
        if (y==true ){               
          activarOtAsignada();
        } else {
          //otAsignadasLayer.removeAllFeatures(); 
          desactivarOtAsignada();         
        }

        /*filtro ruta real si esta tildado*/
        var x = document.getElementById("ruta_check").checked;   
        if (x==true && $('#cuadrilla').val()!=-1 ){                   
          activarRoute();                    
        } else {                    
          desactivarRoute();                    
        }

      } else {
        alert('Es obligatorio ingresar una Fecha Válida');
      }  
    };
  
$(document).ready(function(){ 
    crearListaSectores(); //desde esta funcion llama a getNumberMarkets
       
    $('#cuadrilla_btn').unbind("click");    
    $('#cuadrilla_btn').click(function(){ 
      filtrarMapa();      
      
    });
	
	 /*FILTRO SI HAY DEMORAS*/
    $('#verDistancias').click(function(){   
	   $("#dialogDistancias").dialog();
       init_m();
	 
    });

	  $('#refrecar_pagina').click(function(){   
      location.reload();
    });

    $('#verControlCelular').click(function() { 
      deleteRow("tabla_control_celular")
      obtenerTablaControlCelular();    
      $("#dialogControlCelular").dialog();    
      $("#dialogControlCelular").parent( "div" ).css( "width", "auto" );   
      $("#dialogControlCelular").parent( "div" ).css( "align-items", "center" );   
      $("#dialogControlCelular").parent( "div" ).css( "align-self", "center" );   
      $("#dialogControlCelular").parent( "div" ).css( "text-align", "center" );       
      $("#dialogControlCelular").parent( "div" ).css( "position", "absolute" );       
    });

    $('#verCuadrillas').click(function() { 
      deleteRow("tabla_control_cuadrilla")
      obtenerTablaControlCuadrilla();    
      $("#dialogCuadrillas").dialog();    
      $("#dialogCuadrillas").parent( "div" ).css( "width", "auto" );   
      $("#dialogCuadrillas").parent( "div" ).css( "align-items", "center" );   
      $("#dialogCuadrillas").parent( "div" ).css( "align-self", "center" );   
      $("#dialogCuadrillas").parent( "div" ).css( "text-align", "center" );       
      $("#dialogCuadrillas").parent( "div" ).css( "position", "absolute" );       
    });

    $('#verFiltros').click(function() {             
      $("#dialogFiltros").dialog();            
      $("#dialogFiltros").parent( "div" ).css( "align-items", "center" );   
      $("#dialogFiltros").parent( "div" ).css( "align-self", "center" );   
      $("#dialogFiltros").parent( "div" ).css( "text-align", "center" );       
      $("#dialogFiltros").parent( "div" ).css( "position", "absolute" ); 
      $('progress').hide();   
      $('#porcent').hide();   
      animateprogress("#buscar",0);      
    });
	
	  $('#verBsqOt').click(function() {             
      $("#dialogBuscar").dialog();
	  $("#dialogBuscar").dialog("open");  
    });
	
	  $('#btn_bsq_ot').click(function() { 
      getBsqOT();   
    });	
	
    $('#verReferencia').click(function() {             
      $("#dialogReferencia").dialog();    
      $("#dialogReferencia").parent( "div" ).css( "width", "auto" );   
      $("#dialogReferencia").parent( "div" ).css( "align-items", "center" );   
      $("#dialogReferencia").parent( "div" ).css( "align-self", "center" );   
      $("#dialogReferencia").parent( "div" ).css( "text-align", "center" );       
      $("#dialogReferencia").parent( "div" ).css( "position", "absolute" );       
    });

    $('#verDemoras').click(function() { 
      var x = document.getElementById("demora_check").checked;   
      if (x==false ){                      
        $("#dialogDemoras").dialog();    
        $("#dialogDemoras").parent( "div" ).css( "width", "400px" );   
        //$("#dialogDemoras").parent( "div" ).css( "height", "90px" );        
        $("#dialogDemoras").parent( "div" ).css( "position", "absolute" );       
      } else {
        $("#dialogDemoras").dialog('close'); 
        $('#detalle_demora').hide();
        $('#tabla_demora').hide();        
        demoraLayer.removeAllFeatures(); 
        demoraMarcaLayer.removeAllFeatures(); 
        controls['selector'].deactivate();
        controls2['selector'].deactivate();
        controls1['selector'].activate();    
        document.getElementById("demora_check").checked=false;
        $('#image_check_demoras').hide();           
      }
    });

    
    $('#actualizar').click(function(){                
      filtrarMapa();
    });    

/*******************************************************************/
   $(function () {
    /* Next part of code handles hovering effect and submenu appearing */
    $('.nav li').hover(
      function (){ $('ul', this).fadeIn(); },
      function (){$('ul', this).fadeOut(); }
    );
  }
  );
    /************************************************************************/
    /*opcion menu
      Ver Secuencia*/
    $('#verSecuencia').click(function() {    
      var x = document.getElementById("secuencia_ot_check").checked;         

      if (x==false ){          
        if ($('#cuadrilla').val()!=-1)
          { 
            activarSecuencia();      
            document.getElementById("secuencia_ot_check").checked=true; 
            $('#image_check_secuencia').show();                    
           }
        else {
            alert("Para ver la ruta es necesario especificar Cuadrilla y Fecha");            
        }
      } 
      else {
          desactivarSecuencia();          
          document.getElementById("secuencia_ot_check").checked=false;         
          $('#image_check_secuencia').hide();                    
        } 
    });

    /*Ver Inmueble*/
    $('#verInmuebles').click(function() {    
      var x = document.getElementById("ot_check").checked;         
      if (x==false ){                              
          activarOtAsignada();
          document.getElementById("ot_check").checked=true; 
          $('#image_check_inmueble').show();                            
        }       
      else {          
          desactivarOtAsignada();
          document.getElementById("ot_check").checked=false;         
          $('#image_check_inmueble').hide();                    
        } 
    });

    /*Ver Ruta*/
    $('#verRutaReal').click(function() {    
      var x = document.getElementById("ruta_check").checked;    
      if (x==false ){          
        if ($('#cuadrilla').val()!=-1)
           {             
            activarRoute();
            document.getElementById("ruta_check").checked=true;  
            $('#image_check_ruta').show();                                                    
           }
          else {
            alert("Para ver la ruta es necesario especificar Cuadrilla y Fecha");
            document.getElementById("ruta_check").checked=false;
          }
      } else {        
        desactivarRoute();
        document.getElementById("ruta_check").checked=false;
        $('#image_check_ruta').hide();        
      }
    });

    /*Ver Demoras*/
    $('#btn_demora').click(function() {          
        if ($('#cuadrilla').val()!=-1 && isNumber($('#tiempo').val())==true && isNumber($('#distancia').val())==true)
          { 
            $('#detalle_demora').show();
            $('#tabla_demora').show();
            getDemora($('#cuadrilla').val(),$('#fecha').val(),$('#tiempo').val(), $('#distancia').val());         
            controls['selector'].deactivate();
            controls2['selector'].deactivate();
            controls1['selector'].activate();
            document.getElementById("demora_check").checked=true;
            $('#image_check_demoras').show();           
           }
          else {
            alert("Para ver las demoras es necesario especificar Cuadrilla, Fecha, Distancia y Tiempo correctamente");
            document.getElementById("demora_check").checked=false;
            $('#image_check_demoras').hide();        
          }      
      });

    /*Ver Ubicacion Actual*/
    $('#verUbicacion').click(function() { 
    console.log("entro a ubicacion")   ;
      var x = document.getElementById("ubicacion_check").checked;    
      if (x==false ){                                      
            activarUbicacionActual(); 
            document.getElementById("ubicacion_check").checked=true;  
            $('#image_check_ubicacion').show();                                                    
           
      } else {        
        desactivarUbicacionActual()
        document.getElementById("ubicacion_check").checked=false;
        $('#image_check_ubicacion').hide();        
      }
    });

    $('#imgMarker2').click(function(){                
      console.log("imgMarker "+$('#imgMarker2').val() )   ;
    });

    /*$('#imgMarker2').click(function(){                
      console.log("imgMarker "+$('#imgMarker2').src() )   ;
    });
*/

    

    $('body').on('click','img',function(){alert('it works');})

});

function activarUbicacionActual(){
    controls['selector'].deactivate();
    controls2['selector'].deactivate();
    controls1['selector'].deactivate();
    controls3['selector'].activate();
    getUbicacionActual();          
}
function desactivarUbicacionActual(){
    controls1['selector'].deactivate();
    controls2['selector'].deactivate();
    controls3['selector'].deactivate();
    controls['selector'].activate();        
    ubicacionActualLayer.removeAllFeatures();  
}

function activarRoute(){
	console.log('>>activarRoute');
    controls['selector'].deactivate();
    controls2['selector'].deactivate();
    controls3['selector'].deactivate();
    controls1['selector'].activate();
    getRoute($('#cuadrilla').val(),$('#fecha').val(),$('#hora_ini').val(),$('#hora_fin').val());                        
}
function desactivarRoute(){
  controls1['selector'].deactivate();
  controls2['selector'].deactivate();
  controls3['selector'].deactivate();
  controls['selector'].activate();
  lineLayer.removeAllFeatures();
  dirLayerRoute.removeAllFeatures();                    
  vectorLayerRoute.removeAllFeatures();
}

function activarSecuencia(){
  controls1['selector'].deactivate();
  controls2['selector'].deactivate();
  controls3['selector'].deactivate();
  controls['selector'].activate();    
  getSecuencia($('#cuadrilla').val());
}
function desactivarSecuencia(){
  secuenciaLayer.removeAllFeatures();
  dirLayer.removeAllFeatures();
}

function activarOtAsignada(){
  controls3['selector'].deactivate();
  controls1['selector'].deactivate();
  controls['selector'].deactivate(); 
  controls2['selector'].activate();             
  getOtAsignadas($('#cuadrilla').val(),$('#fecha').val());
}          
function desactivarOtAsignada(){
  controls3['selector'].deactivate();
  controls2['selector'].deactivate();
  controls1['selector'].deactivate();
  controls['selector'].activate();        
  otAsignadasLayer.removeAllFeatures();   
}          

function activarMarketsNumber(){
  controls1['selector'].deactivate();
  controls2['selector'].deactivate();
  controls3['selector'].deactivate();
  controls['selector'].activate();   
}
