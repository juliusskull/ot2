//para Referencia
$(function() {
$('.list').click(function() {
  $(this).parent().find('ul').toggle();
	if ($("#expande").attr("src")=="img/zoom-plus-mini.png"){
		$("#expande").attr("src","img/zoom-minus-mini.png");
	}else{
		$("#expande").attr("src","img/zoom-plus-mini.png");
	}
});

  $('.list ul').hide();
}
);

$.datepicker.regional['es'] = {
                closeText: 'Cerrar',
                prevText: ' nextText: Sig>',
                currentText: 'Hoy',
                monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
                'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
                'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
                dayNames: ['Domingo', 'Lunes', 'Martes', 'Mi?rcoles', 'Jueves', 'Viernes', 'S?bado'],
                dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mi?;', 'Juv', 'Vie', 'Sab'],
                dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
                weekHeader: 'Sm',
                dateFormat: 'dd/mm/yy',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: false,
                yearSuffix: ''
                };

$.datepicker.setDefaults($.datepicker.regional['es']);

//para Fecha
$(function() {
  $("#fecha").val(getTiempoActual());
  $("#fecha").datepicker();
});

function getTiempoActual(){
    var f = new Date();
    var mes = f.getMonth() +1;	
    return ''+((f.getDate()<10)?'0'+f.getDate():f.getDate()) +'/'+((mes<10)?'0'+mes:mes)+'/'+f.getFullYear();
}