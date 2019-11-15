window.onload = function() {
    animateprogress("#buscar",0);
  }  
  document.querySelector ('#cuadrilla_btn').addEventListener ('click', function() {
      $('progress').show();                   
      $('#porcent').show();  
      $('#buscando').show();    
      animateprogress("#buscar",97);
  });