/**
 * Created by pc on 21/01/18.
 */

function create_modal(){
    var s= '<div id="alertaBox" class="modal fade" role="dialog">';
    s+='    <div class="modal-dialog">';
    s+='';
    s+='   <!-- Modal content-->';
    s+='    <div class="modal-content">';
    s+='        <div class="modal-header">';
    s+='            <button type="button" class="close" data-dismiss="modal">&times;</button>';
    s+='            <h4 class="modal-title" id="alerta-titulo">Modal Header</h4>';
    s+='        </div>';
    s+='        <div class="modal-body">';
    s+='            <p id="alerta-desc"></p>';
    s+='        </div>';
    s+='        <div class="modal-footer">';
    s+='            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>';
    s+='        </div>';
    s+='    </div>';
    s+='';
    s+='    </div>';
    s+='</div>';
    return s;
}
function create_loader_ajax(){
    var s= '<div id="loading" class="modal-loading"></div>';

    return s;
}
//-----------------------------------------------DOM
function validate(formData, jqForm, options) {
    console.log("entro validate");

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


function showResponse1(responseText, statusText, xhr, $form)  {
    console.log("entro respose");

    var obj = JSON.parse(responseText);
    if(obj.status){
        show_message("OK",obj.massage,"ok");
    }else{
        show_message("Error",obj.massage,"error");
    }
    /*
     alert('status: ' + statusText + '\n\nresponseText: \n' + responseText +
     '\n\nThe output div should have already been updated with the responseText.');
     */
}
//-----------------------------------------------MESSAGE
function show_message(t,d,tipo=""){
    switch(tipo){
        case "ok":
            swal(t, d, "success");
            break;
        case "error":
           C
            break;
        default:
            swal(d);

    }

}




//These codes takes from http://t4t5.github.io/sweetalert/
function showBasicMessage() {
    swal("Here's a message!");
}

function showWithTitleMessage() {
    swal("Here's a message!", "It's pretty, isn't it?");
}

function showSuccessMessage() {
    swal("Good job!", "You clicked the button!", "success");
}

function showConfirmMessage() {
    swal({
        title: "Are you sure?",
        text: "You will not be able to recover this imaginary file!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        closeOnConfirm: false
    }, function () {
        swal("Deleted!", "Your imaginary file has been deleted.", "success");
    });
}

function showCancelMessage() {
    swal({
        title: "Are you sure?",
        text: "You will not be able to recover this imaginary file!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        cancelButtonText: "No, cancel plx!",
        closeOnConfirm: false,
        closeOnCancel: false
    }, function (isConfirm) {
        if (isConfirm) {
            swal("Deleted!", "Your imaginary file has been deleted.", "success");
        } else {
            swal("Cancelled", "Your imaginary file is safe :)", "error");
        }
    });
}

function showWithCustomIconMessage() {
    swal({
        title: "Sweet!",
        text: "Here's a custom image.",
        imageUrl: "../../images/thumbs-up.png"
    });
}

function showHtmlMessage() {
    swal({
        title: "HTML <small>Title</small>!",
        text: "A custom <span style=\"color: #CC0000\">html<span> message.",
        html: true
    });
}

function showAutoCloseTimerMessage() {
    swal({
        title: "Auto close alert!",
        text: "I will close in 2 seconds.",
        timer: 2000,
        showConfirmButton: false
    });
}

function showPromptMessage() {
    swal({
        title: "An input!",
        text: "Write something interesting:",
        type: "input",
        showCancelButton: true,
        closeOnConfirm: false,
        animation: "slide-from-top",
        inputPlaceholder: "Write something"
    }, function (inputValue) {
        if (inputValue === false) return false;
        if (inputValue === "") {
            swal.showInputError("You need to write something!"); return false
        }
        swal("Nice!", "You wrote: " + inputValue, "success");
    });
}

function showAjaxLoaderMessage() {
    swal({
        title: "Ajax request example",
        text: "Submit to run ajax request",
        type: "info",
        showCancelButton: true,
        closeOnConfirm: false,
        showLoaderOnConfirm: true
    }, function () {
        setTimeout(function () {
            swal("Ajax request finished!");
        }, 2000);
    });
}
function show_input(){

    $('.form-control').focus(function () {
        $(this).parent().addClass('focused');
    });

    //On focusout event
    $('.form-control').focusout(function () {
        var $this = $(this);
        if ($this.parents('.form-group').hasClass('form-float')) {
            if ($this.val() == '') { $this.parents('.form-line').removeClass('focused'); }
        }
        else {
            $this.parents('.form-line').removeClass('focused');
        }
    });

    //On label click
    $('body').on('click', '.form-float .form-line .form-label', function () {
        $(this).parent().find('input').focus();
    });

    //Not blank form
    $('.form-control').each(function () {
        if ($(this).val() !== '') {
            $(this).parents('.form-line').addClass('focused');
        }
    });

}
function form_format(){
    show_input();
    $('.date').inputmask('dd/mm/yyyy', { placeholder: '__/__/____' });
    $('#myForm').ajaxForm( { beforeSubmit: validate,success:showResponse1 } );
	//omar: esta linea es para trabajar en el formulario de viajes (se tiene mas de un formulario)
    $('#myFormDet').ajaxForm( { beforeSubmit: validate,success:showResponse1 } );	
    $(".input-control").click(function(){
        if($(".input-control").hasClass( "invalid" )){
            $(".input-control").removeClass("invalid");
            $(".error-control").removeClass("error_show").addClass("error");
        }
    });
}
function validate_success(){
    $('#myForm').ajaxForm( { beforeSubmit: validate,success:showResponse1} );
	//omar: esta linea es para trabajar en el formulario de viajes (se tiene mas de un formulario)
	$('#myFormDet').ajaxForm( { beforeSubmit: validate,success:showResponse1} );
    $(".input-control").click(function(){
        if($(".input-control").hasClass( "invalid" )){
            $(".input-control").removeClass("invalid");
            $(".error-control").removeClass("error_show").addClass("error");
        }


    });
}
function activar_grilla2(tabla){

    var dt =$('#example').DataTable({

        initComplete: function () {
            console.log("Entro=>initComplete");
            this.api().columns().every( function () {
                var column = this;
                var select = $('<select><option value=""></option></select>')
                    .appendTo( $(column.footer()).empty() )
                    .on( 'change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );

                        column.search( val ? '^'+val+'$' : '', true, false ).draw();
                    } );

                column.data().unique().sort().each( function ( d, j ) {
                    select.append( '<option value="'+d+'">'+d+'</option>' )
                } );
            } );
        },
        dom: 'Bfrtip',
        buttons: [
            {
                text: '<i class="glyphicon glyphicon-plus" aria-hidden="true">&emsp; Nuevo</i>',
                titleAttr: 'Nuevo',
                action: function ( e, dt, node, config ) {

                    ver_form_new(tabla);
                }
            }
        ]

    });


}
function activar_grilla(tabla){
    var visible="";
    if(tabla=='v_localidades'){
        visible='oculto';
    }
    var dt =$('#example').DataTable({

        initComplete: function () {
            console.log("Entro=>initComplete");

            this.api().columns().every( function () {
                var column = this;
                var select = $('<select><option value=""></option></select>')
                    .appendTo( $(column.footer()).empty() )
                    .on( 'change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );

                        column.search( val ? '^'+val+'$' : '', true, false ).draw();
                    } );

                column.data().unique().sort().each( function ( d, j ) {
                    select.append( '<option value="'+d+'">'+d+'</option>' )
                } );
            } );
        },
            "iDisplayLength": 5,
        dom: 'Bfrtip',
        buttons: [
            {
                text: '<i class="glyphicon glyphicon-plus '+visible+'" aria-hidden="true">&emsp; Nuevo</i>',
                titleAttr: 'Nuevo',
                action: function ( e, dt, node, config ) {
					//omar: esto lo puse para agegar mis funciones y tratar de no modificar las funciones que ya estan definidas
					if ( tabla == 'v_viajes_activos' ) {
						ver_form_viaje(tabla);
					}else{
                    ver_form_new(tabla);}
                }
            }
        ]

    });


    var detailRows = [];
    $('#example tbody').on( 'click', 'tr td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = dt.row( tr );
        var idx = $.inArray( tr.attr('id'), detailRows );

        if ( row.child.isShown() ) {
            tr.removeClass( 'details' );
            row.child.hide();

            // Remove from the 'open' array
            detailRows.splice( idx, 1 );
        }
        else {
            tr.addClass( 'details' );
            row.child( format( row.data() ) ).show();

            // Add to the 'open' array
            if ( idx === -1 ) {
                detailRows.push( tr.attr('id') );
            }
        }
    } );
    new $.fn.dataTable.Responsive( dt );
    dt.on( 'draw', function () {
        $.each( detailRows, function ( i, id ) {
            $('#'+id+' td.details-control').trigger( 'click' );
        } );
    } );

}

function activar_grilla_sum(t){
    var columna=0;
    var tabla=t;
    var table =$('#example').DataTable( {
        initComplete: function () {
            console.log("Entro=>initComplete-1");
            //-----------------------------------
            /*
            this.api().columns([1]).every( function () {
                var column = this;
                console.log(column);
                var select = $("#officeFltr");
                column.data().unique().sort().each( function ( d, j ) {
                    select.append( '<option value="'+d+'">'+d+'</option>' )
                } );
            } );
            */
            //-----------------------------------------
            this.api().columns().every( function () {
                var column = this;
                if(columna!=6){
                    var select = $('<select><option value=""></option></select>')
                        .appendTo( $(column.footer()).empty() )
                        .on( 'change', function () {
                            var val = $.fn.dataTable.util.escapeRegex(
                                $(this).val()
                            );

                            column.search( val ? '^'+val+'$' : '', true, false ).draw();
                        } );

                    column.data().unique().sort().each( function ( d, j ) {
                        select.append( '<option value="'+d+'">'+d+'</option>' )
                    } );
                }
                columna++;


            } );
        },
        "footerCallback": function ( row, data, start, end, display ) {
            var api = this.api(), data;

            // Remove the formatting to get integer data for summation
            var intVal = function ( i ) {
                return typeof i === 'string' ?
                    i.replace(/[\$,]/g, '')*1 :
                    typeof i === 'number' ?
                        i : 0;
            };

            // Total over all pages
            var columna_totalizar=6;
            total = api
                .column( columna_totalizar )
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                }, 0 );

            // Total over this page
            pageTotal = api
                .column( columna_totalizar, { page: 'current'} )
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                }, 0 );

            // Update footer
            $( api.column( columna_totalizar ).footer() ).html(
                '$'+pageTotal +' ( $'+ total +' total)'
            );
        }
         ,dom: 'Bfrtip'
       ,
        buttons: [
            {
                text: '<i class="glyphicon glyphicon-plus" aria-hidden="true">&emsp; Nuevo</i>',
                titleAttr: 'Nuevo',
                action: function ( e, dt, node, config ) {

                    ver_form_new_mov_x_viaje(tabla);
                }
            }
        ]
    } );
    $("#chofer_select").selectpicker('refresh');
    //---------------------------------------
    $('#chofer_select').on('change', function(){
        var search = [];

        $.each($('#chofer_select option:selected'), function(){
            search.push($(this).val());
        });

        search = search.join('|');
        table.column(1).search(search, true, false).draw();

    });
    //---------------------------------------------
    var detailRows = [];
    $('#example tbody').on( 'click', 'tr td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = dt.row( tr );
        var idx = $.inArray( tr.attr('id'), detailRows );

        if ( row.child.isShown() ) {
            tr.removeClass( 'details' );
            row.child.hide();

            // Remove from the 'open' array
            detailRows.splice( idx, 1 );
        }
        else {
            tr.addClass( 'details' );
            row.child( format( row.data() ) ).show();

            // Add to the 'open' array
            if ( idx === -1 ) {
                detailRows.push( tr.attr('id') );
            }
        }
    } );
    new $.fn.dataTable.Responsive( table );
    table.on( 'draw', function () {
        $.each( detailRows, function ( i, id ) {
            $('#'+id+' td.details-control').trigger( 'click' );
        } );
    } );
}
function ver_form_new_mov_x_viaje(tabla){
    if($('#chofer_select').val()!=''){
        ver_form_new(tabla);
    }else{
        swal("Error", "Debe seleccionar un fletero para realizar un movimiento ", "error");
    }

}
function format ( d ) {
    return 'Full name: '+d.first_name+' '+d.last_name+'<br>'+
        'Salary: '+d.salary+'<br>'+
        'The child row can contain any data you wish, including links, images, inner tables etc.';
}
function set_date_piket(){
    $(".date").datetimepicker({
        format: "dd/MM/yyyy - hh:ii",
        autoclose: true,
        todayBtn: true,
        language:'es',
        locale:'es',
        startDate: "2018-02-25 10:00",
        minuteStep: 10
    });
}