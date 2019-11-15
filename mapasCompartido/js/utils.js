function removeOptions(selectbox)
{
    var i;
    for(i=selectbox.options.length-1;i>=0;i--)
    {
        selectbox.remove(i);
    }
}

function obtener_equipos(equiposString){
    /*
        var pos_coma=equiposString.indexOf(",");
        var equipo1 = (equiposString.substr(0, pos_coma) =='0') ? '-' : equiposString.substr(0, pos_coma);
        equipos='<b>Moto Bomba: </b>'+equipo1;        

        var pos_coma2=equiposString.indexOf(",",pos_coma+1);
        var equipo2= (equiposString.substr(pos_coma+1, pos_coma2-pos_coma-1) =='0') ? '-' : equiposString.substr(pos_coma+1, pos_coma2-pos_coma-1);
        equipos=equipos+'</BR><b>Moto Compresor: </b>' + equipo2;
        console.log("pos_coma2 "+ pos_coma2);
        
        var equipo3= (equiposString.substr(pos_coma2+1) =='0') ? '-' : equiposString.substr(pos_coma2+1);
        equipos=equipos+'</BR><b>Retro Escavadora: </b>'+equipo3;        
        return equipos;
    */
      }

function obtener_ot_solapadas(lon, lat, i,value){
      var cadena;
      cadena = ''; 
      for (j=i;j>=0;j--){        
        if (value[j].LON==lon && value[j].LAT==lat){          
          cadena=cadena + value[j].OT + ', ';
        } 
      }
      cadena = cadena.substring(0, cadena.length-1); 
      return cadena;
     }   

function getIconoOtAsig(estado,motivo,prioridad){      
      var icono;      /*
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
	        case '8': 
                  icono = 'circulo_rojo';
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
      }*/
    icono = 'circulo_azul';
    icono = icono + '.png';
    return icono;
     } 

function getEstadoOtAsig(estado,motivo){
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
     }

function getPrioridadOt(prioridad){
      var label;
      switch(prioridad) {
          case '1':
              label = 'Alta';
              break;
          case '2':
              label = 'Media'
              break;
          case '3':
              label = 'Baja'
              break;
          default:
               label = 'Baja'; // (sin estado)
      }
      return label;
     } 

/*function getIcono(motivo, estado ){      
      var icono;      
      switch(Number(motivo)) {
          case 1:
              icono = 'verde';
              break;
          case 2:
              icono = 'rojo'
              break;
          case 3:
              icono = 'naranja'
              break;          
          default:
               icono = 'azul'; //actual (sin motivo)
      }
      icono = icono + '.png';
      return icono;
     } */

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

/*function getEstado(motivo){
      var label;
      switch(Number(motivo)) {
          case 1:
              label= 'Trabajo Terminado';
              break;
          case 2:
              label = 'Problema No Encontrado'
              break;
          case 3:
              label = 'Requiere Otra Intervención'
              break;
          default:
               label= 'Trabajo Actual';
      }
      return label;
     } */
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
		  case 46:
			  label = 'REHABILITACION CLANDESTINA - RECORTE';
			  break;
		  case 42:	label = 'LLAVE NO VISIBLE';break;
		case 44:	 label = 'SIN CONEXION';break;
		case 248: label = '	RESTRICCION NO REALIZADA POR DECISION COMERCIAL';break;
		case 315: label = '	BARRIO CON PROBLEMAS DE SERVICIO';break;
		case 21:	 label = ' NO SE UBICA DOMICILIO';break;
		case 316: label = ' 	LLAVE MAESTRA PROFUNDA';break;
		case 55:	 label = 'REHABILITACION NO REALIZADA';break;
		case 43:	 label = 'CONDOMINIO SIN LLAVE INDEPENDIENTE';break;
		case 246: label = '	LLAVE ESFERICA/ESCLUSA';break;
		case 247: label = '	GABINETE SIN KIT';break;
		case 314: label = '	CLIENTE CON SUBSIDIO POR INDIGENCIA';break;
		case 362: label = '	GABINETE DENTRO DE DOMICILIO';break;
		case 435: label = '	CONEXIÓN INACCESIBLE';break;
		case 54:	 label = 'REHABILITACION AGUA  REALIZADA';break;
		case 27:	 label = 'RESTRICCION REALIZADA';break;
		case 427: label = '	CORTE REALIZADO';break;
		case 511: label = ' 	CONTINUA CORTADO HABITADO';break;
		case 512: label = '	CONTINUA CORTADO DESHABITADO';break;
		 
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

function isNumber(n) {
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
  }                                                     
