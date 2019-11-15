var inicio={
    loguin: function(server){
            console.log(server);       
        $("#login").on('submit',(function(e) {                                
            f = $('#distrito').val();
            g = $('#gerencia').val();
            m = $('#mapa_id').val();
            console.log("distrito1"+f+"-gerencia1"+g+"-mapa"+m);
            var url= dominio + server;
            console.log(url);       
            $.ajax({
                        url: origen_log, // point to server-side PHP script                         
                        dataType: 'json',  // what to expect back from the PHP script, if anything
                        cache: false,
                        contentType: false,
                        processData: false,
                        data: new FormData(this),                         
                        type: 'post',
                        success: function(php_script_response){                         
                            //console.log("resp"+php_script_response[0].resp);                                                                                       
                            console.log("resp"+php_script_response);                                                                                       
                            if (php_script_response[0].resp =="ok" )
                            {                               
                                window.location.replace( origen_go+"?password1="+$("#password1").val()+"&distrito="+$("#distrito").val()+"&gerencia="+$("#gerencia").val()+"&url="+url);  
                            } else {                                
                                alert('Usuario o Password Incorrectos');                                
                            }
                        },
                        error: function(php_script_response){                            
                            alert('Error al validar el usuario');
                            console.log("error php:"+php_script_response);
                        }
             });
        
            return false;
    }));
}  
}  

function crearListaGerencias(m){
    //f = $('#distrito1').val();
    //g = $('#gerencia1').val();
    //m = $('#mapa_id').val();
    console.log("mapa"+m);
    origen="http://ot.aguasdelnortesalta.com.ar/wses.php?ger=0&pmapa="+m;

/*  f = $('#distrito1').val();
    origen="http://ot.aguasdelnortesalta.com.ar/wses.php?sec=0&pdistrito="+f+"&pgerencia=GC&ptrabaja=OT";      */

    $.ajax({ 
        url:origen, 
        type:'GET',
        dataType:'json', 
        error:function(jqXHR,text_status,strError){ 
            alert('No se pudo establecer conexión con los datos Gerencias.');
        }, 
        timeout:60000, 
        success:function(data){             
            var value =data;                                            
            var fin = value.length;         
            var lista = document.getElementById("gerencia");            
            //removeOptions(lista);
            
            for(i=0 ;i<fin ;i++){               
                    var option = document.createElement("option");
                        option.value = value[i].GERENCIA_ID;
                        option.text = value[i].GERENCIA_NOMBRE;
                        if (i==0){                          
                            option.selected=true;
                        }
                        lista.appendChild(option);                                  
            }           
        } 
        }).done(function() {            
              })
        .fail(function() {
              })
        .always(function() {
              });       
        }   

function crearListaDistritos(m){
    //f = $('#distrito1').val();
    //g = $('#gerencia1').val();
    //m = $('#mapa_id').val();
    console.log("mapa"+m);
    origen="http://ot.aguasdelnortesalta.com.ar/wses.php?dis=0&pmapa="+m;

/*  f = $('#distrito1').val();
    origen="http://ot.aguasdelnortesalta.com.ar/wses.php?sec=0&pdistrito="+f+"&pgerencia=GC&ptrabaja=OT";      */

    $.ajax({ 
        url:origen, 
        type:'GET',
        dataType:'json', 
        error:function(jqXHR,text_status,strError){ 
            alert('No se pudo establecer conexión con los datos Distritos.');
        }, 
        timeout:60000, 
        success:function(data){             
            var value =data;                                            
            var fin = value.length;         
            var lista = document.getElementById("distrito");            
            //removeOptions(lista);
            
            for(i=0 ;i<fin ;i++){               
                    var option = document.createElement("option");
                        option.value = value[i].DISTRITO;
                        option.text = value[i].DESCRIPCION;
                        if (i==0){                          
                            option.selected=true;
                        }
                        lista.appendChild(option);                                  
            }           
        } 
        }).done(function() {            
              })
        .fail(function() {
              })
        .always(function() {
              });       
        }           


