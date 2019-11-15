/**
 * Created by jgutierrez on 13/01/18.
 */
//var url_base="../cg/salida/";
var url_base="app/";
/*
 * la validacion http://malsup.com/jquery/form/#ajaxForm
 * */
/*export en pdf y exel
 * https://datatables.net/extensions/buttons/examples/html5/simple.html
 *
 * */

var ajax_activo=true;
var my_time;
$(document).on({
    ajaxStart: function() { if(ajax_activo==true){  $('.page-loader-wrapper').show(); }  },
    ajaxStop:  function() { if(ajax_activo==true){setTimeout(function () { $('.page-loader-wrapper').fadeOut(); }, 1); }}

});
/*permite resaltar los menu*/
$(".menu-navegar").click(function(){
    console.log("activar menu");
    $(".menu-navegar").removeClass( "active" );
    $(this).addClass("active");
});

function myPlayTimer() {
    my_time=setTimeout(function () {
        ajax_activo=false;

        $( "#content" ).load(url_base+"index.php?tabla=mapa&accion=all");
        myPlayTimer();
    }, 30000);
}

function myStopTimer() {
    clearTimeout(my_time);
    ajax_activo=true;
}
function ver_mapa(){
    tabla_actual="mapa";
    myPlayTimer();
    $( "#content" ).load(url_base+"index.php?tabla=mapa&accion=all");

    }
function ver_mapa_filtro(ini){
    myStopTimer();
    if(ini==0){
        var titulo="historial de ubicacion";
        $("#titulo-pagina").html("PANTALLA: "+titulo.toUpperCase());
        $( "#content" ).load(url_base+"index.php?tabla=mapa&accion=filtro");

    }else{
        var dia=$("#fch_dia").val();
        var mes=$("#fch_mes").val();
        var anio=$("#fch_anio").val();
        var fecha=anio +"-"+mes+"-"+dia;

        var usuario=$("#operario").val();
        var titulo="ubicacion actual";
        $("#titulo-pagina").html("PANTALLA: "+titulo.toUpperCase());

        $( "#content" ).load(url_base+"index.php?tabla=mapa&accion=filtro&fch="+fecha+"&usuario="+usuario);
    }


}
function ver_tabla(tabla){
    myStopTimer();
    var titulo=tabla.replace("_"," ").replace("_"," ").replace("view","").toUpperCase();
    switch(tabla) {
        case "ot":
            titulo="Tareas Pendientes";
            break;

        case "ot_finalizar_view":
            titulo="Tareas iniciadas o finalizadas";
            break;
        case "materiales_ot":
            titulo="Materiales usados en las tareas";
            break;
        case "operario":
            titulo="operarios";
            break;
        case "grupo":
            titulo="Grupo";
            break;
    }
    $("#titulo-pagina").html("PANTALLA: "+titulo.toUpperCase());
    $( "#content" ).load( url_base+"index.php?tabla="+tabla+"&accion=all", function() {
        if(tabla=='sincronizar' ||  tabla=='template_view'){
            $('#example').DataTable();
        }else{
            $('#example').DataTable({
                dom: 'Bfrtip',
                buttons: [
                    {
                        text: '<i class="glyphicon glyphicon-plus" aria-hidden="true">&emsp; Nuevo</i>',
                        titleAttr: 'Nuevo',
                        action: function ( e, dt, node, config ) {
                            //alert( 'Button activated' );
                            if(tabla=='grupo'){
                                ver_form_new_grupo();
                            }else{
                                ver_form_new(tabla);
                            }


                        }
                    }
                ]
            });
        }

    });
}
function ver_form(tabla,id){
    ajax_activo=true;
    myStopTimer();
    $( "#content" ).load( url_base+"index.php?tabla="+tabla+"&accion=ver&id="+id, function() {    });
}
function ver_form_grupo(id){
    ajax_activo=false;
    myStopTimer();
    //$( "#content" ).load( url_base+"index.php?tabla=grupo&accion=upload_p1&id="+id, function() {    });

    ver_form_modificar("grupo",id);
}
function form_grupo_add_archivo(id){
    ajax_activo=false;
    myStopTimer();
    $( "#content" ).load( url_base+"index.php?tabla=grupo&accion=upload_p1&id="+id, function() {    });


}
function ver_form_modificar(tabla,id){

    $( "#content" ).load( url_base+"index.php?tabla="+tabla+"&accion=id&id="+id, function() {
        // $('#example').DataTable();
        $('#myForm').ajaxForm( { beforeSubmit: validate } );
        $(".input-control").click(function(){
            if($(".input-control").hasClass( "invalid" )){
                $(".input-control").removeClass("invalid");
                $(".error-control").removeClass("error_show").addClass("error");
            }


        });

    });
}
function add_actividad(id,d,t,o){
    nro=$("#id_lista_activiades").val();
    nro++;
    var id_pasos=$("#MAX_ID").val();
    id_pasos++;
    $("#MAX_ID").val(id_pasos);
    $('#'+id).append('<tr><td>'+id_pasos+'</td><td>'+ d.trim()+'</td><td>'+t+'</td><td>'+o+'</td>' +
        '   <td><select id="activo'+id_pasos+'">'+
        '<option value="1">Activo</option>'+
        '<option value="0">Borrar</option>'+
             '</select></td></tr>');
    $("#id_lista_activiades").val(nro);

}
function show_agregar_actividad(){
    $('#alertaBox1').modal();
}

function crear_tarea(){
    /*
    nro=$("#id_lista_activiades").val();
    nro++;
    $('#'+id).append('<tr><td>'+2+'</td><td>'+ d.trim()+'</td><td>'+t+'</td><td>'+o+'</td></tr>');
    $("#id_lista_activiades").val(nro);

    $.post( "test.php", { name: "John", time: "2pm" } );
    */


    $.post( "app/index.php?tabla=ot&accion=add", { motivo: $("#motivo").val().trim()
    , id_motivo: "0", observacion: $("#observacion").val().trim()
    ,cod_empleado_asig:$("#cod_empleado_asig").val()
    ,nombre_empleado_asig:$( "#cod_empleado_asig option:selected" ).text()
     ,id:$("#id").val()
    ,id_loc:"0"
    ,localidad:""
    ,zona:""
    ,id_bar:"0"
    ,barrio:""
    ,id_cal:""
    ,calle:""
    ,altura:""
    ,cod_cuadrilla_asig:"0"
    ,contratista_asig:""
    ,contratista_asig:""
    ,fchalta:""
    } , function(result){
        console.log("result:"+result);
        var myArray = JSON.parse(result);
        console.log("id:"+myArray.id);
        enviar_actividades(myArray.id);

    });


//$( "#cod_empleado_asig option:selected" ).text()
}
function enviar_actividades(my_ot){


    $('#lista_actividades tr').each(function () {
        var pk = $(this).find("td").eq(0).html();
        var actividad = $(this).find("td").eq(1).html();
        var tipo_actividad = $(this).find("td").eq(2).html();
        var obligatorio_actividad = $(this).find("td").eq(3).html();
        if(pk!='#'){
            console.log(pk +","+ actividad +","+ tipo_actividad +","+ obligatorio_actividad +",");
            tiene_foto=0;
            if(tipo_actividad=="FOTO"){
                tiene_foto=1;
            }
            var accion=$("#activo"+pk).val();
            if(accion==1){
                $.post( "app/index.php?tabla=pasos&accion=add",{id_paso:0,desc_campo:actividad,tipo:tipo_actividad,foto:tiene_foto,obligatorio:obligatorio_actividad,_id:pk,ot:my_ot}, function(result){
                    console.log("result:"+result);
                    var myArray = JSON.parse(result);


                });
            }else{

                $.get( "app/index.php?tabla=pasos&accion=delete&id="+pk, function(result){
                    });
            }



        }

    }).promise().done(function () {
        alert("se envio correctamente");
        ver_tabla('ot');
    });
}
function ver_form_new(tabla){

    $( "#content" ).load( url_base+"index.php?tabla="+tabla+"&accion=new", function() {
        // $('#example').DataTable();
        show_input();
        validate_success();

        $('#myForm').ajaxForm( { beforeSubmit: validate
            ,success: function(res) {
                ver_tabla(tabla);
            }
        } );
        $(".input-control").click(function(){
            if($(".input-control").hasClass( "invalid" )){
                $(".input-control").removeClass("invalid");
                $(".error-control").removeClass("error_show").addClass("error");

            }


        });

    });
}
function ver_form_new_grupo(){

    $( "#content" ).load( url_base+"index.php?tabla=grupo&accion=new_cavecera", function() {
        // $('#example').DataTable();
        show_input();
        validate_success();

        $('#myForm').ajaxForm( { beforeSubmit: validate
            ,success: function(res) {
                ver_tabla("grupo");
            }
        } );
        $(".input-control").click(function(){
            if($(".input-control").hasClass( "invalid" )){
                $(".input-control").removeClass("invalid");
                $(".error-control").removeClass("error_show").addClass("error");

            }


        });

    });
}
function borrar(tabla,id){

    //alert("Esta por borrar este campo");
    swal({
        title: "Esta por eliminar la fila seleccionada?",
        text: "Se borrará definitivamente de la base de datos ",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si",
        closeOnConfirm: false
    }, function () {

        $.get( "app/index.php?tabla="+tabla+"&accion=delete&id="+id, function(result){
            var myArray = JSON.parse(JSON.stringify(result));
            swal("Deleted!", "La accion se ejecuto correctamente", "success");
            ver_tabla(tabla);

        });

    });
}
function borrar_grupo_ot(id){

    //alert("Esta por borrar este campo");
    swal({
        title: "Esta por eliminar la relacion con la ot?",
        text: "Se borrará definitivamente de la base de datos ",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si",
        closeOnConfirm: false
    }, function () {
        /*agregar app/*/
        ajax_activo=false;
        $.get( "app/index.php?tabla=grupo_ot&accion=delete&id="+id, function(result){
            var myArray = JSON.parse(JSON.stringify(result));
            swal("Deleted!", "La accion se ejecuto correctamente", "success");
            $("#fila"+id).hide();
            //ver_tabla(tabla);
            ajax_activo=true;
        });

    });
}
function borrar_grupo_ot_material(id){

    //alert("Esta por borrar este campo");
    swal({
        title: "Esta por eliminar la relacion con la ot?",
        text: "Se borrará definitivamente de la base de datos ",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si",
        closeOnConfirm: false
    }, function () {
        /*agregar app/*/
        ajax_activo=false;
        $.get( "app/index.php?tabla=materiales_ot&accion=delete&id="+id, function(result){
            var myArray = JSON.parse(JSON.stringify(result));
            swal("Deleted!", "La accion se ejecuto correctamente", "success");
            $("#filaMateriales"+id).hide();
            //ver_tabla(tabla);
            ajax_activo=true;
        });

    });
}
function add_grupo(){

    $.post("app/index.php?tabla=grupo&accion=add", {descripcion: $('#descripcion').val()}, function(result){
        //ver_form('grupo',p_id_grupo);

        //ver_form_grupo(p_id_grupo);
        var myArray = JSON.parse(result);

        swal("Ok!", "Se creo el nuevo grupo nro"+myArray.id , "success");
        ver_form_grupo(myArray.id);
    });
}
function add_grupo_ot(p_id_grupo){
    var nro_ot=$("#lista_ot_nueva").val();
    //alert("Esta por borrar este campo");
    swal({
        title: "Esta por agregar la ot "+nro_ot+"",
        text: "desea continuar ",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si",
        closeOnConfirm: false
    }, function () {
        /*agregar app/*/

        $.post("app/index.php?tabla=grupo_ot&accion=add", {ot: nro_ot, id_grupo: p_id_grupo}, function(result){
            //ver_form('grupo',p_id_grupo);

            ver_form_grupo(p_id_grupo);
            swal("Ok!", "Se agrego la OT nro"+nro_ot+ " al grupo nro"+p_id_grupo , "success");
        });

        /*
        $.get( "index.php?tabla=grupo_ot&accion=delete&id="+ot, function(result){
            var myArray = JSON.parse(JSON.stringify(result));
            swal("Deleted!", "La accion se ejecuto correctamente", "success");
            ver_tabla(tabla);

        });
        */

    });
}
function add_grupo_ot_material(p_id_grupo){
    var descripcion_material=$("#descripcion_material").val();
    var cantidad_material=$("#cantidad_material").val();
    //alert("Esta por borrar este campo");
    swal({
        title: "Esta por agregar el material "+descripcion_material+"",
        text: "desea continuar ",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si",
        closeOnConfirm: false
    }, function () {
        /*agregar app/*/
        ajax_activo=false;
        $.post("app/index.php?tabla=materiales_ot&accion=add", {codigo: descripcion_material, ot: '0',cantidad:cantidad_material,usuario:'admin',id_grupo:p_id_grupo}, function(result){
            //ver_form('grupo',p_id_grupo);
            var myArray = JSON.parse(result);
            //ver_form_grupo(p_id_grupo);
            swal("Ok!", "Se agrego "+descripcion_material+ " al grupo nro"+p_id_grupo +"#"+myArray.id , "success");
            var bb='<button  class="btn btn-primary" onclick="borrar_grupo_ot('+myArray.id+')">Eliminar</button></select>'

            $('#lista_materiales_ot tr:last').after('<tr><td>'+myArray.id+'</td><td>'+descripcion_material+'</td><td>'+cantidad_material+'</td><td>'+bb+'</td></tr>');

            ajax_activo=true;
        });

    });
}

function descargar(id){
    $.get( "app/index.php?tabla=ot_finalizar_view&accion=descarga&id="+id, function(result){


    });
}
function copiar(id){
    swal({
        title: "Copiar la Tarea Nro"+id,
        text: "Se creará la nueva tarea copiando la tarea nro "+id,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si",
        closeOnConfirm: false
    }, function () {
    $.get( "app/index.php?tabla=template_view&accion=copiar&id="+id, function(result){
        var myArray = JSON.parse(result);

        swal("Ok!", "Se agrego la nueva tarea nro"+myArray.ot, "success");

    });
    });
}
function guardar(tabla,id){

    alert("se realizaron los cambios");
}

function validate(formData, jqForm, options) {
    // formData is an array of objects representing the name and value of each field
    // that will be sent to the server;  it takes the following form:
    //
    // [
    //     { name:  username, value: valueOfUsernameInput },
    //     { name:  password, value: valueOfPasswordInput }
    // ]
    //
    // To validate, we can examine the contents of this array to see if the
    // username and password fields have values.  If either value evaluates
    // to false then we return false from this method.

    for (var i=0; i < formData.length; i++) {
        console.log("v="+formData[i].value+ " val="+formData[i].required);

        if (formData[i].value.trim()=="" && formData[i].required) {
            //alert('Please enter a value for both Username and Password');
            var input=$("#"+formData[i].name);
            input.removeClass("valid").addClass("invalid");
            var span=$("#"+formData[i].name+"_error");
            span.removeClass("error").addClass("error_show");
            return false;
        }

    }
    // alert('Both fields contain values.');
}

function show_message(t,d){
    $("#alertaBox").modal();
    $("#alerta-titulo").html(t+" " +'<span class=" glyphicon glyphicon-alert"></span> ');
    $("#alerta-desc").html(d);
}