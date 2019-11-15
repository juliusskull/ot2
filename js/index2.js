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
$(document).on({
    ajaxStart: function() {  $('.page-loader-wrapper').show(); },
    ajaxStop:  function() { setTimeout(function () { $('.page-loader-wrapper').fadeOut(); }, 1);}
});
/*permite resaltar los menu*/
$(".menu-navegar").click(function(){
    console.log("activar menu");
    $(".menu-navegar").removeClass( "active" );
    $(this).addClass("active");
});
function ver_mapa(){
    $( "#content" ).load(url_base+"index.php?tabla=mapa&accion=all");

    }
function ver_mapa_filtro(ini){
    if(ini==0){
        $( "#content" ).load(url_base+"index.php?tabla=mapa&accion=filtro");

    }else{
        var dia=$("#fch_dia").val();
        var mes=$("#fch_mes").val();
        var anio=$("#fch_anio").val();
        var fecha=anio +"-"+mes+"-"+dia;

        var usuario=$("#operario").val();

        $( "#content" ).load(url_base+"index.php?tabla=mapa&accion=filtro&fch="+fecha+"&usuario="+usuario);
    }


}
function ver_tabla(tabla){

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
                            ver_form_new(tabla);
                        }
                    }
                ]
            });
        }

    });
}
function ver_form(tabla,id){

    $( "#content" ).load( url_base+"index.php?tabla="+tabla+"&accion=ver&id="+id, function() {

    });
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
    $('#'+id).append('<tr><td>'+2+'</td><td>'+ d.trim()+'</td><td>'+t+'</td><td>'+o+'</td></tr>');
    $("#id_lista_activiades").val(nro);

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
            if(tipo="FOTO"){
                tiene_foto=1;
            }
            $.post( "app/index.php?tabla=pasos&accion=add",{id_paso:0,desc_campo:actividad,tipo:tipo_actividad,foto:tiene_foto,obligatorio:obligatorio_actividad,_id:0,ot:my_ot}, function(result){
                console.log("result:"+result);
                var myArray = JSON.parse(result);


            });

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

        $('#myForm').ajaxForm( { beforeSubmit: validate } );
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
            swal("Deleted!", "Se borro la fila seleccionada", "success");
            ver_tabla(tabla);

        });

    });
}
function descargar(id){
    $.get( "app/index.php?tabla=ot_finalizar_view&accion=descarga&id="+id, function(result){


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