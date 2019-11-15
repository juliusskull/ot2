/*function removeOptions(selectbox)
{
    var i;
    for(i=selectbox.options.length-1;i>=0;i--)
    {
        selectbox.remove(i);
    }
}*/
function crearListaSectores(){
	f = $('#distrito1').val();
	g = $('#gerencia1').val();
	m = $('#mapa_id').val();
	console.log("distrito1"+distrito1+"-gerencia1"+gerencia1+"-mapa"+m);
	origen="../wses.php?sec=0&pdistrito="+f+"&pgerencia="+g+"&pmapa="+m;
	$.ajax({ 
		url:origen, 
		type:'GET',
		dataType:'json', 
		error:function(jqXHR,text_status,strError){ 
			alert('No se pudo establecer conexión con los datos Sectores.');
		}, 
		timeout:60000, 
		success:function(data){ 			
			var value =data;	                 						
			var fin = value.length;			
			var lista = document.getElementById("servicio");			
			removeOptions(lista);
			
			for(i=0 ;i<fin ;i++){				
					var option = document.createElement("option");
					    option.value = value[i].GEREN;
					    option.text = value[i].DESCRIPCION;
					    if (i==0){
					    	console.log("selected:"+value[i].GEREN);
					    	$('#servicio').val(value[i].GEREN);
					    	option.selected=true;
						}
					    lista.appendChild(option);									
			}			
		} 
		}).done(function() {
			crearListaCuadrilla2();
		      })
		.fail(function() {
			  })
		.always(function() {
			  });		
		}		

function crearListaCuadrilla2(){	
	$.ajax({ 
		url:'../wses.php?lex=0',
                //url:'http://192.168.111.80/ot/ws/v1/web/lex', 
		type:'GET',
		dataType:'json', 
		error:function(jqXHR,text_status,strError){ 
			alert('No se pudo establecer conexión con los datos.');
		}, 
		timeout:60000, 
		success:function(data){ 			
			var value =data;	                 						
			var fin = value.length;			
			var lista = document.getElementById("cuadrilla");
			removeOptions(lista);
			
			//inserto opcion Todos
			var option2 = document.createElement("option");
					    option2.value = "-1";
					    option2.text = "Todos";
					    lista.appendChild(option2);
			
			for(i=0 ;i<fin ;i++){					
				if ((value[i].GEREN).toUpperCase()==($('#servicio').val()).toUpperCase()){	
					var option = document.createElement("option");
					    option.value = (value[i].CODIGO == 0) ? value[i].ID : value[i].CODIGO ;
					    option.text = ((value[i].CODIGO == 0) ? value[i].ID : value[i].CODIGO) +' - '+value[i].NOMBRE;
					    lista.appendChild(option);					
				}
			}			
		} 
		}).done(function() {
			activarMarketsNumber();
			getMarketsNumber(origen_ot,vectorLayer,$('#cuadrilla').val(),$('#estadoFinaliza').val()); 
				}).fail(function() {
			  })
			  .always(function() {
			  });		
		}
/*
function obtenerTablaControlCelular(){
	$.ajax({ 
		url:'http://ot.aguasdelnortesalta.com.ar/wses.php?ctrl=0&servicio='+$('#servicio').val(),
		type:'GET',
		dataType:'json', 
		error:function(jqXHR,text_status,strError){ 
			alert('No se pudo establecer conexión con los datos.');
		}, 
		timeout:60000, 
		success:function(data){ 			
			var value =data;	                 						
			var fin = value.length;			
			var table = document.getElementById("tabla_control_celular");			
			for(i=0 ;i<fin ;i++){					 					  
                      var rowCount = table.rows.length;                      
                      var row = table.insertRow(rowCount);
                      
                      var cell0 = row.insertCell(0);
                      cell0.innerHTML = value[i].LEGAJO + ' - ' + value[i].CAPATAZ_NOMBRE ;

                      var cell1 = row.insertCell(1);
                      cell1.innerHTML = value[i].CODIGO_CUADRILLA;

                      var cell2 = row.insertCell(2);
                      cell2.innerHTML = value[i].VERSION;

                      var cell3 = row.insertCell(3);
                      cell3.innerHTML = value[i].GPS_HABILITADO;

                      var cell4 = row.insertCell(4);
                      cell4.innerHTML = value[i].RED_HABILITADO;				

                      var cell5 = row.insertCell(5);
                      cell5.innerHTML = value[i].ULTIMA_FECHA;

                      var cell6 = row.insertCell(6);
                      cell6.innerHTML = (!value[i].DIA_INICIADO) ? '-' : value[i].DIA_INICIADO ;                      

                      var cell7 = row.insertCell(7);
                      cell7.innerHTML = (!value[i].LINEA) ? '-' : value[i].LINEA ;                                            

                      var cell8 = row.insertCell(8);
                      cell8.innerHTML = value[i].HORA_ACTUAL_CELULAR;								
			}			
		} 
		}).done(function() {
				  
				}).fail(function() {
				  
			  })
			  .always(function() {
				  
			  });		
		}	
*/
/*
function obtenerTablaControlCuadrilla(){
	$.ajax({ 
		url:'http://ot.aguasdelnortesalta.com.ar/wses.php?equipoallactual=0&servicio='+$('#servicio').val(),
		type:'GET',
		dataType:'json', 
		error:function(jqXHR,text_status,strError){ 
			alert('No se pudo establecer conexión con los datos.');
		}, 
		timeout:60000, 
		success:function(data){ 			
			var value =data;	                 						
			var fin = value.length;			
			var table = document.getElementById("tabla_control_cuadrilla");			
			for(i=0 ;i<fin ;i++){					 					  
              var rowCount = table.rows.length;                      
              var row = table.insertRow(rowCount);
              
              var cell0 = row.insertCell(0);
              cell0.innerHTML = value[i].LEGAJO + ' - ' + value[i].NOMBRE;

              var cell1 = row.insertCell(1);
              cell1.innerHTML = value[i].C ;

              var cell2 = row.insertCell(2);
              cell2.innerHTML = (!value[i].MOTO_BOMBA) ? '-' : value[i].MOTO_BOMBA ;

              var cell3 = row.insertCell(3);
              cell3.innerHTML = (!value[i].MOVILIDAD) ? '-' : value[i].MOVILIDAD ; 

              var cell4 = row.insertCell(4);
              cell4.innerHTML = (!value[i].MOTO_COMPRESOR) ? '-' : value[i].MOTO_COMPRESOR ; 

              var cell5 = row.insertCell(5);
              cell5.innerHTML = (!value[i].OPERARIO1) ? '-' : value[i].OPERARIO1 ;

              var cell6 = row.insertCell(6);
              cell6.innerHTML = (!value[i].OPERARIO2) ? '-' : value[i].OPERARIO2 ;

              var cell7 = row.insertCell(7);
              cell7.innerHTML = (!value[i].OPERARIO3) ? '-' : value[i].OPERARIO3 ;
			}			
		} 
		}).done(function() {
				  
				}).fail(function() {
				  
			  })
			  .always(function() {
				  
			  });		
		}	*/			


