$('#detalle_demora').hide();
$('#tabla_demora').hide();    
$('#dialogControlCelular').hide();    
$('#dialogCuadrillas').hide();    
$('#dialogReferencia').hide();    
$('#dialogFiltros').hide();    
$('#dialogDemoras').hide(); 
//$('#dialogImagen').hide(); 

var archivo;

var delta =0;
/*function rotateBy10Deg(ele){
    ele.style.webkitTransform="rotate("+delta+"deg)";
    delta+=90;
}*/

function rotateBy10Deg(){
 // $('#imgMarker').rotate(90); 
 var ele =document.getElementById("imgMarker");
    ele.style.webkitTransform="rotate("+delta+"deg)";
    delta+=90;
}

function swipe() {
  console.log("entraaaaaaaaaaa");
   var ele =document.getElementById("imgMarker");

   var largeImage = document.getElementById('imgGrande');
   largeImage.style.display = 'block';
   largeImage.style.width=2000+"px";
   largeImage.style.height=2000+"px";
   largeImage.src=ele.getAttribute('src');
   //window.open(url,'Image','width=largeImage.stylewidth,height=largeImage.style.height,resizable=1');
}

function dialogo(p){
		if(p=='open'){
		$("#espera").dialog();
			$("#espera").dialog("open");
		}else{
			 $("#espera").dialog("close");
		}
}
 map = new OpenLayers.Map("mapdiv",{
        controls:[
          new OpenLayers.Control.Navigation(),
          new OpenLayers.Control.PanZoomBar(),
          new OpenLayers.Control.Attribution()]
        }
        );

    map.addLayer(new OpenLayers.Layer.OSM());    

    var control = new OpenLayers.Control();
    OpenLayers.Util.extend(control, {
          draw: function () {
              this.box = new OpenLayers.Handler.Box( control,
                  {"done": this.notice},
                  {keyMask: OpenLayers.Handler.MOD_SHIFT});
              this.box.activate();
          },

          notice: function (bounds) {
              OpenLayers.Console.userError(bounds);
          }
      });
    map.addControl(control);
    
    var fromProjection = new OpenLayers.Projection("EPSG:4326");   
    var toProjection   = new OpenLayers.Projection("EPSG:900913"); 

    getCentroLocalidad();    

    var vectorLayer = new OpenLayers.Layer.Vector("Vector", 
    {
        styleMap: new OpenLayers.StyleMap(             
        {
            label : "${labelText}",                    
            fontColor: "black",
            fontSize: "14px",
            fontFamily: "Arial",
            fontWeight: "bold",
            labelAlign: "lc",
            labelXOffset: "8",
            labelYOffset: "0"
        })
    });


    map.addLayer(vectorLayer); 
    var controls = {
      selector: new OpenLayers.Control.SelectFeature(vectorLayer, {
       onSelect: createPopup, 
       onUnselect: destroyPopup 
     })
    };

    map.addControl(controls['selector']);
    var lineLayer = new OpenLayers.Layer.Vector("Line Layer"); 
    
    var vectorLayerRoute = new OpenLayers.Layer.Vector("Vector", 
    {
        styleMap: new OpenLayers.StyleMap(             
        {
            label : "${labelText}",                    
            fontColor: "black",
            fontSize: "14px",
            fontFamily: "Arial",
            fontWeight: "bold",
            labelAlign: "lc",
            labelXOffset: "8",
            labelYOffset: "0"
        })
    });

    var controls1 = {
      selector: new OpenLayers.Control.SelectFeature(vectorLayerRoute, {      
       onSelect: createPopup1, 
       onUnselect: destroyPopup1
     })
    };

    map.addControl(controls1['selector']);

    var secuenciaLayer = new OpenLayers.Layer.Vector("Line Layer");
    map.addLayer(secuenciaLayer);

    OpenLayers.Renderer.symbol.arrow = [0,2, 1,0, 2,2, 1,0, 0,2];
    var styleMap = new OpenLayers.StyleMap(OpenLayers.Util.applyDefaults(
        {graphicName:"arrow",rotation : "${angle}",
        strokeColor: '#0000FF', strokeOpacity: 0.5, strokeWidth: 3
      },
        OpenLayers.Feature.Vector.style["default"]));
    var dirLayer = new OpenLayers.Layer.Vector("direction", {styleMap: styleMap});
    map.addLayer(dirLayer);
       
    OpenLayers.Renderer.symbol.arrow1 = [1, 0, 0, -3, -1, 0, 0, -0.5, 0, 3, 0, -0.5];
    var styleMapRoute = new OpenLayers.StyleMap(OpenLayers.Util.applyDefaults(
        {graphicName:"arrow1",rotation : "${angle}",
        strokeColor: '#f41523', strokeOpacity: 1, strokeWidth: 3
      },
        OpenLayers.Feature.Vector.style["default"]));
    var dirLayerRoute = new OpenLayers.Layer.Vector("direction", {styleMap: styleMapRoute});
    map.addLayer(dirLayerRoute);      
    

    var demoraLayer = new OpenLayers.Layer.Vector("Overlay", 
        {
            styleMap: new OpenLayers.StyleMap(             
            {
                strokeColor: '#F85EEE',
                fillColor: '#F85EEE',              
                fillOpacity: 0.4
                            
            })
        });

    var demoraMarcaLayer = new OpenLayers.Layer.Vector("Vector", 
    {
        styleMap: new OpenLayers.StyleMap(             
        {
            label : "${labelText}",                    
            fontColor: "black",
            fontSize: "14px",
            fontFamily: "Arial",
            fontWeight: "bold",
            labelAlign: "lc",
            labelXOffset: "-18",
            labelYOffset: "0"
        })
    });

    /***************************************************          OT ASIGNADAS  ******************  */
    var otAsignadasLayer = new OpenLayers.Layer.Vector("Vector", 
    {
        styleMap: new OpenLayers.StyleMap(             
        {
            label : "${labelText}",                    
            fontColor: "black",
            fontSize: "14px",
            fontFamily: "Arial",
            fontWeight: "bold",
            labelAlign: "lc",
            labelXOffset: "-18",
            labelYOffset: "0"
        })
    });

    var controls2 = {
      selector: new OpenLayers.Control.SelectFeature(otAsignadasLayer, {
       onSelect: createPopup2, 
       onUnselect: destroyPopup2 
     })
    };

    map.addControl(controls2['selector']);

    function createPopup2(feature) {
      feature.popup = new OpenLayers.Popup.FramedCloud("pop",
          feature.geometry.getBounds().getCenterLonLat(),
          null,
          '<div class="markerContent" style="font-size:12px;">'+feature.attributes.description+'</div>',
          null,
          true,
          function() { controls2['selector'].unselectAll(); }
      );      
      map.addPopup(feature.popup);
    }

    function destroyPopup2(feature) {
      feature.popup.destroy();
      feature.popup = null;      
    }

    /************************************************** NUEVO UBICACION ACTUAL ****************************************/
    var ubicacionActualLayer = new OpenLayers.Layer.Vector("Vector", 
    {
        styleMap: new OpenLayers.StyleMap(             
        {
            label : "${labelText}",                    
            fontColor: "black",
            fontSize: "12px",
            fontFamily: "Arial",
            fontWeight: "bold",
            labelAlign: "lc",
            labelXOffset: "5",
            labelYOffset: "5"
        })
    });

    var controls3 = {
      selector: new OpenLayers.Control.SelectFeature(ubicacionActualLayer, {
       onSelect: createPopup3, 
       onUnselect: destroyPopup
     })
    };

    map.addControl(controls3['selector']);

    function createPopup3(feature) {
      feature.popup = new OpenLayers.Popup.FramedCloud("pop",
          feature.geometry.getBounds().getCenterLonLat(),
          null,
          '<div class="markerContent" style="font-size:12px;">'+feature.attributes.description+'</div>',
          null,
          true,
          function() { controls3['selector'].unselectAll(); }
      );      
      map.addPopup(feature.popup);
    }

    /*function destroyPopup3(feature) {
      feature.popup.destroy();
      feature.popup = null;      
    }*/
	
    /***************************************************          OT ASIGNADAS  ******************  */

    function createPopup(feature) {
      archivo=1;
      feature.popup = new OpenLayers.Popup.FramedCloud("pop",
          feature.geometry.getBounds().getCenterLonLat(),
          null,
          '<div class="markerContent" style="font-size:12px;">'+feature.attributes.description+'</div>',
          null,
          true,
          function() { controls['selector'].unselectAll(); }
      );      
      map.addPopup(feature.popup);
    }

    function destroyPopup(feature) {
      feature.popup.destroy();
      feature.popup = null;      
    }

    function createPopup1(feature) {
      feature.popup = new OpenLayers.Popup.FramedCloud("pop",
          feature.geometry.getBounds().getCenterLonLat(),
          null,
          '<div class="markerContent" style="font-size:12px;">'+feature.attributes.description+'</div>',
          null,
          true,
          function() { controls1['selector'].unselectAll(); }
      );      
      map.addPopup(feature.popup);
    }

    function destroyPopup1(feature) {
      feature.popup.destroy();
      feature.popup = null;      
    }
	function getMarketsNumber(origen_ot,vectorLayer,cuadrilla,estadoFinaliza){   

      vectorLayer.removeAllFeatures();

      f = ($('#fecha').val()).replace('/','-').replace('/','-');                
      origen=origen_ot+"&pfecha="+f+'&servicio='+$('#servicio').val();       
      dialogo('open'); 
      $.getJSON(origen, function(data){                  
          fechaSeleccionada = $('#fecha').val();
          var value =data ;                                              
          len=value.length;
          
          for(i=0;i< len ;i++){           
            var fecha_inicio = getFecha(value[i].FECHA_INICIO);
            var fecha_finalizo = getFecha(value[i].FECHA_FINALIZO);
            if (!value[i].FECHA_FINALIZO) {
              fecha_fin_visualizada = '-';                
            } else {
              fecha_fin_visualizada = value[i].FECHA_FINALIZO;
            }
            if ( value[i].OT!=''
              &&
              (cuadrilla == value[i].C || cuadrilla == -1 || (value[i].C == 0 && cuadrilla == value[i].LEGAJO )) /*FILTRO CUADRILLA*/
              && (
                (estadoFinaliza == '-1' && value[i].ACTUAL == 'SI' ) /*ACTUAL*/
                || (estadoFinaliza == value[i].MOT  ) /*PARTICULAR*/
                || estadoFinaliza ==0)  /*TODOS */
                ){
            
              var feature = [];
              var pt = new OpenLayers.Geometry.Point(value[i].LON , value[i].LAT).transform(fromProjection, toProjection);
              /*calculo distancia con punto de inicio*/
              if (!value[i].LAT_INI){
                  dist ='0';  
              } else {
                  var pt_ini = new OpenLayers.Geometry.Point(value[i].LON_INI , value[i].LAT_INI).transform(fromProjection, toProjection);
                  var dist = Math.floor(pt.distanceTo(pt_ini)); 
                
              }              

        		  var ot_solapadas = obtener_ot_solapadas(value[i].LON, value[i].LAT, i-1,value,value[i].C);              	

              feature.push( new OpenLayers.Feature.Vector(pt, {labelText: (value[i].C ==0 ? value[i].LEGAJO : value[i].C )  }));              
              var imagen='<img src="http://gis.spassa.com/mapasCompartido/clases/image.php?ot='+value[i].OT+'" width="256px" height="256px" id="imgMarker" onclick="act('+value[i].OT+')" />';

              feature.push(new OpenLayers.Feature.Vector(pt, {description:                           
                            '<div class="row" ><div class="col-lg-6" >' +imagen +                              
                            '</div> <div class="col-lg-6" >'+
                            '<img src="http://gis.spassa.com/mapasCompartido/img/rotate.png" width="32px" heigth="32px" onclick="rotateBy10Deg()" />  '+                            
                               '</BR><b>Cuadrilla:</b> '+ value[i].C +
                               '</BR> <b>Capataz:</b> '+ value[i].CAPATAZ +
                               '</BR> <b>OT:</b> '+ value[i].OT +
                               '</BR> <b>Mot:</b> '+ value[i].MOT_OT +
                               '</BR> <b>Dir:</b> '+ value[i].DIRECCION +                           
                               '</BR> <b>Estado:</b> '+ getEstado(value[i].MOT) + 
                               '</BR> <b>Inicio:</b> '+ value[i].FECHA_INICIO +                           
                               '</BR> <b>Finalizo:</b> '+ fecha_fin_visualizada +                           
                               '</BR> <b>Distancia al Inicio:</b> '+ dist + ' metros '+
                               '</BR> <b>Obs:</b> '+ value[i].OBS +
                               '</BR> <b>Movil:</b> '+ ((!value[i].MOVIL) ? '-' : value[i].MOVIL) +
                               '</BR> <b>OT solapadas:</b> '+ ot_solapadas +
                            '</div></div>'
                           },
              {externalGraphic: 'icons/'+getIcono(value[i].MOT,value[i].E), graphicHeight: 25,  graphicXOffset:-12, graphicYOffset:-25 }));

              if (value[i].EQUIPOS!='0,0,0') {
                var pt1 = new OpenLayers.Geometry.Point(value[i].LON , value[i].LAT).transform(fromProjection, toProjection);                
                feature.push(new OpenLayers.Feature.Vector(pt1,
                 {description: obtener_equipos(value[i].EQUIPOS)},
                 {externalGraphic: 'icons/maquina.png',
                 graphicHeight: 25,  graphicXOffset:-27, graphicYOffset:-10 }));
              }                

              vectorLayer.addFeatures(feature);

            }
          }
          $('progress').hide();   
           $('#porcent').hide();  
           $('#buscando').hide(); 
       




        }).done(function() {
    console.log( "second success" );
  })
  .fail(function() {
    console.log( "error" );
  })
  .always(function() {
    console.log( "complete" );
	map.addLayer(vectorLayer);
	dialogo('close');
  });  
      }

  function act(ot){            
      $("#imgMarker").attr('src', 'http://gis.spassa.com/mapasCompartido/clases/image.php?ot='+ot);            
    }
	    
  function getBsqOT(){
	  if($("#ot_bsq").val()==''){
  		alert('Debe cargar un  número valido');
  		return null;	  
	  }
    origen=origen_ot_bsq+$("#ot_bsq").val(); 
		  
    dialogo('open'); 
    $.getJSON(origen, function(data){                  
          var value =data ;                                                                            
          var lonLat = new OpenLayers.LonLat(value[0].LNG,  value[0].LAT).transform( fromProjection, toProjection);
            
          var zoom=18;
          map.setCenter (lonLat, zoom);
	  
        $('progress').hide();   
        $('#porcent').hide();  
        $('#buscando').hide();  
      }).done(function() {
      console.log( "second success" );
    })
    .fail(function() {
      console.log( "error" );
    })
    .always(function() {
      console.log( "complete" );

	   map.addLayer(vectorLayer);
	   dialogo('close');
    });  
  }  
/*
	 function getFecha(fechaLarga){
        var fechaCorta;
              if (fechaLarga!=null){
                fechaCorta = fechaLarga.substring(0,10);
              } else {
                 fechaCorta = '';
              }  
        return fechaCorta;              
     } 
    function getHora(fechaLarga){
        var hora;
              if (fechaLarga!=null){
                hora = fechaLarga.substring(11);
              } else {
                 hora = '';
              }  
        return hora;              
     }  

     function getFechaHora(fechaLarga){
        var fecha;
        if (fechaLarga!=null){
          dia = fechaLarga.substring(0,2);
          mes = fechaLarga.substring(3,5);
          anio = fechaLarga.substring(6,10);
          hora = fechaLarga.substring(11,13);
          mi = fechaLarga.substring(14,16);
          ss = fechaLarga.substring(17,19);
          fecha=anio+'-'+mes+'-'+dia+'T'+hora+':'+mi+':'+ss;
        } else {
           fecha = '';
        }  
        return fecha;              
     }	

     function getIcono(motivo, estado ){      
      var icono;      
      switch(Number(motivo)) {
          case 1:
              icono = 'verde';
              break;
          case 2:
              icono = 'rojo';
              break;
          case 3:
              icono = 'naranja';
              break;          
           case 4:
              icono = 'naranja';
              break;         

           case 5:
              icono = 'verde';
              break;          

          default:
               icono = 'azul'; //actual (sin motivo)
      }
      icono = icono + '.png';
      return icono;
     } 

	  function getEstado(motivo){
      var label;
      switch(Number(motivo)) {
          case 1:
              label= 'Trabajo Terminado';
              break;
          case 2:
              label = 'Problema No Encontrado';
              break;
          case 3:
              label = 'Requiere Otra Intervención';
              break;
          case 4:
              label = 'Verificación Comprobada';
              break;
          case 5:
              label = 'Negativa del Usuario';
              break;
          default:
               label= 'Trabajo Actual';
      }
      return label;
     }   
    
     function getEnabledProvider(estado){
      var label;
      switch(Number(estado)) {
          case 2:
              label= 'Si';
              break;
          case 1:
              label = 'No';
              break;
          case 0:
              label = 'Info No Disponible';
              break;
          default:
               label = 'Info No Disponible';
      }
      return label;
     } 
	 */
	/*-------------------------------------*/


 var  measureControls;
        function init_m(){
           
            map.addControl(new OpenLayers.Control.LayerSwitcher());
            map.addControl(new OpenLayers.Control.MousePosition());            
          
            var sketchSymbolizers = {
                "Point": {
                    pointRadius: 4,
                    graphicName: "square",
                    fillColor: "white",
                    fillOpacity: 1,
                    strokeWidth: 1,
                    strokeOpacity: 1,
                    strokeColor: "#333333"
                },
                "Line": {
                    strokeWidth: 3,
                    strokeOpacity: 1,
                    strokeColor: "#666666",
                    strokeDashstyle: "dash"
                },
                "Polygon": {
                    strokeWidth: 2,
                    strokeOpacity: 1,
                    strokeColor: "#666666",
                    fillColor: "white",
                    fillOpacity: 0.3
                }
            };
            var style = new OpenLayers.Style();
            style.addRules([
                new OpenLayers.Rule({symbolizer: sketchSymbolizers})
            ]);
            var styleMap = new OpenLayers.StyleMap({"default": style});
          
            var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
            renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;

            measureControls = {
                line: new OpenLayers.Control.Measure(
                    OpenLayers.Handler.Path, {
                        persist: true,
                        handlerOptions: {
                            layerOptions: {
                                renderers: renderer,
                                styleMap: styleMap
                            }
                        }
                    }
                ),
                polygon: new OpenLayers.Control.Measure(
                    OpenLayers.Handler.Polygon, {
                        persist: true,
                        handlerOptions: {
                            layerOptions: {
                                renderers: renderer,
                                styleMap: styleMap
                            }
                        }
                    }
                )
            };
            
            var control;
            for(var key in measureControls) {
                control = measureControls[key];
                control.events.on({
                    "measure": handleMeasurements,
                    "measurepartial": handleMeasurements
                });
                map.addControl(control);
            }
            
           
            
            document.getElementById('noneToggle').checked = true;
        }
        
        function handleMeasurements(event) {
            var geometry = event.geometry;
            var units = event.units;
            var order = event.order;
            var measure = event.measure;
            var element = document.getElementById('output');
            var out = "";
            if(order == 1) {
                out += "measure: " + measure.toFixed(3) + " " + units;
            } else {
                out += "measure: " + measure.toFixed(3) + " " + units + "<sup>2</" + "sup>";
            }
            element.innerHTML = out;
        }

        function toggleControl(element) {
            for(key in measureControls) {
                var control = measureControls[key];
                if(element.value == key && element.checked) {
                    control.activate();
                } else {
                    control.deactivate();
                }
            }
        }
        
        function toggleGeodesic(element) {
            for(key in measureControls) {
                var control = measureControls[key];
                control.geodesic = element.checked;
            }
        }
        
        function toggleImmediate(element) {
            for(key in measureControls) {
                var control = measureControls[key];
                control.setImmediate(element.checked);
            }
        }

function getCentroLocalidad(){  
  var origen_ot_centro_s="../wses.php?cen=0";
  f = $('#distrito1').val();
  origen=origen_ot_centro_s+"&pdistrito="+f;      
  $.getJSON(origen, function(data){                            
      var value =data;                                                        
      var lonLat = new OpenLayers.LonLat(value[0].LNG_CENTRO, value[0].LAT_CENTRO).transform( fromProjection, toProjection);
      var zoom=value[0].ZOOM;
      map.setCenter (lonLat, zoom);
    }
    ).done(function() {}).fail(function() {}).always(function() {});         
}