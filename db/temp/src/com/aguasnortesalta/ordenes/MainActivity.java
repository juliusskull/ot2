package com.aguasnortesalta.ordenes;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.ConfigWEB;
import com.aguasnortesalta.ordenes.model.Editor;
import com.aguasnortesalta.ordenes.model.Operario;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.SincronizarList;
import com.aguasnortesalta.ordenes.model.UsuarioList;
import com.aguasnortesalta.ordenes.sync.SincronizarService;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.aguasnortesalta.ordenes.ConfirmarFrag.OnOtClickOpciones;
import com.aguasnortesalta.ordenes.ConfirmarFrag2.OnOtClickOpciones2;
import com.aguasnortesalta.ordenes.OTFrag.OnOtClick;
import com.aguasnortesalta.ordenes.Principal.OnListaSelect;
import com.aguasnortesalta.ordenes.OTListFrag.OnListaSelectOt;
import com.aguasnortesalta.ordenes.Ot_FinalizarFrag.OnOt_FinalizarClick;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.google.android.gms.internal.mg;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;


import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	private int requestCode_new=123;
	private int requestCode_new_download=124;
	private static final int CAMERA_IMAGE_REQUEST=125;
	private static final int CAMERA_IMAGE_REQUEST_OT=126;
	private Principal principal= new Principal();
	private OTListFrag principal_ot= new OTListFrag();
	
	private EditorFragment editor= new EditorFragment();
	private OTFrag  ot_pantalla= new  OTFrag();
	private RespuestaListFrag respuestas_list= new RespuestaListFrag();
	private ServiciosxmotivoListFrag servicios_list= new ServiciosxmotivoListFrag();
	private OperarioListFrag operarios_list= new OperarioListFrag();
	private EquipoListFrag equipos_list= new EquipoListFrag();
	private Ot_FinalizarFrag ot_pantalla_fin= new Ot_FinalizarFrag();
	private Ot_finalizadaListFrag ot_pantalla_finalizada= new Ot_finalizadaListFrag();
	private AperturaFrag apertura_pantalla = new AperturaFrag();
	private ConfirmarFrag  confirmar_pantalla = new ConfirmarFrag();
	private ConfirmarFrag2 confirmar_pantalla2 = new ConfirmarFrag2();
	
	private UsuarioList usuariolist= new UsuarioList();
	private DerivacionesotListFrag derivaciones_ot_list= new DerivacionesotListFrag();
	private ConfirmacionListFrag confirmacion_list= new ConfirmacionListFrag();
	
	private MaterialesotListFrag materiales_list= new MaterialesotListFrag();
	
	private GeometriaFrag     geometria_one= new  GeometriaFrag();
	private ArchivoListFrag   archivo_list= new  ArchivoListFrag();
	private ConfiguracionFrag configuracionFrag= new ConfiguracionFrag();
	
	private MenuItem searchItem;
	private MenuItem actualizarItem;
	private MenuItem mapaItem;
	private MenuItem fotoItem;
	//private DatabaseManager dm;	
	public final static String VER_MAPA_LAT="com.aguasnortesalta.ordenes.VER_MAPA_LAT";
	public final static String VER_MAPA_LNG="com.aguasnortesalta.ordenes.VER_MAPA_LNG";
	public final static String VER_MAPA_OT="com.aguasnortesalta.ordenes.VER_MAPA_OT";
		
	public static final int PANTALLA_PRINCIPAL=0;
	public static final int PANTALLA_PRINCIPAL_LISTA=1;
	public static final int PANTALLA_ACTUALIZAR=2;
	public static final int PANTALLA_about=3;
	public static final int PANTALLA_LOGOUT=4;
	public static final int PANTALLA_EDITOR=22;
	
	public static final int PANTALLA_OT = 23;
	public static final int PANTALLA_MAPA = 1;
	public static final int PANTALLA_SERVICIOS_LIST=24;
	public static final int PANTALLA_RESPUESTAS_LIST=25;
	public static final int PANTALLA_OPERARIOS_LIST=26;
	public static final int PANTALLA_EQUIPOS_LIST=27;
	public static final int PANTALLA_OT_FINALIZAR=28;
	public static final int PANTALLA_OT_FINALIZADA=29;
	public static final int PANTALLA_APERTURA=30;
	public static final int PANTALLA_CONFIRMAR=31;
	public static final int PANTALLA_MATERIALES=32;
	public static final int PANTALLA_DERIVACIONES_OT=33;
	public static final int PANTALLA_GEOMETRIAS_ONE=34;
	public static final int PANTALLA_CONFIRMAR2=35;
	public static final int PANTALLA_ARCHIVO_LIST=7;
	
	public static final int PANTALLA_CONFIRMACION_LIST=6;
	
	public static final int PANTALLA_CONFIGURACION=5;

	public static final int requestCode_new_mapa = 6;	
	
	public  int PANTALLA_ACTUAL=0;

	protected Editor objEditor;
	protected Ot ot_actual;

	private SearchView searchView;

	private int tipo_content=PANTALLA_PRINCIPAL_LISTA;
	private Gson gson = new Gson();	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//dm= new DatabaseManager(this);
		
		if(Configuracion.OT_isExists(this)){
			Util.Log("entro finalizar");
			ir(PANTALLA_OT_FINALIZAR);
		}
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		
		mTitle = getTitle();
		/*
		 * Extrae el logcat a un archivo, solo en caso de prueba
		Configuracion.extractLogToFileAndWeb(this);
		*/
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		principal.setOnEventListener(accionListaSelect);
		principal_ot.setOnEventListener(accionLista_select);		
		ot_pantalla.setOnOtClick(accionOT);
		ot_pantalla_fin.setOnOt_FinalizarClick(accionOT_Finalizar);		
		confirmar_pantalla.setOnOtClickOpciones(accionConfirmar);
		confirmar_pantalla2.setOnOtClickOpciones2(accionConfirmar2);	
		Intent intent = new Intent(this, SincronizarService.class);
		startService(intent);
		
		DatabaseManager.ini_alarma(this, "");
		
		
		
	}	
	/*
	private void createPDF(FileOutputStream outputStream) throws IOException {
	    PrintedPdfDocument document = new PrintedPdfDocument(this,
	            getPrintAttributes());

	    // start a page
	    PdfDocument.Page page = document.startPage(1);

	    // draw something on the page
	    View content = this.getContentView();
	    content.draw(page.getCanvas());

	    // finish the page
	    document.finishPage(page);
	    //. . .
	    // add more pages
	    //. . .
	    // write the document content
	    document.writeTo(outputStream);

	    //close the document
	    document.close();
	}
	*/
	OnOtClickOpciones2 accionConfirmar2= new OnOtClickOpciones2(){

		@Override
		public void onClickOpcion(int opcion) {
			
		}
	};
	OnOtClickOpciones accionConfirmar= new OnOtClickOpciones(){

		@Override
		public void onClickOpcion(int opcion) {
			// TODO Auto-generated method stub
			switch (opcion) {
			case ConfirmarFrag.OPCIONES_TRABAJOS_EJECUTADOS:
				Utils.showDialog(MainActivity.this, "trabajos ejecutados");
				ir(PANTALLA_SERVICIOS_LIST);
				break;
			case ConfirmarFrag.OPCIONES_APERTURA_DE_CALZADA:
				
				ir(PANTALLA_APERTURA);
				//Utils.showDialog(MainActivity.this, "trabajos calzada");			
				break;
			case ConfirmarFrag.OPCIONES_MANO_DE_OBRA:
				//Utils.showDialog(MainActivity.this, "trabajos vereda");
				ir(PANTALLA_OPERARIOS_LIST);
				break;	
				
			case ConfirmarFrag.OPCIONES_EQUIPOS:
				//Utils.showDialog(MainActivity.this, "trabajos vereda");
				ir(PANTALLA_EQUIPOS_LIST);
				break;	
			case ConfirmarFrag.OPCIONES_MATERIALES:
				//Utils.showDialog(MainActivity.this, "trabajos vereda");
				ir(PANTALLA_MATERIALES);
				break;	
			case ConfirmarFrag.OPCIONES_RESPUESTAS:
				//Utils.showDialog(MainActivity.this, "trabajos vereda");
				okItem.setVisible(true);
				ir(PANTALLA_RESPUESTAS_LIST);
				break;
			case ConfirmarFrag.OPCIONES_DERIVACIONES_OT:
				//Utils.showDialog(MainActivity.this, "trabajos vereda");
				ir(PANTALLA_DERIVACIONES_OT);
				break;
			case ConfirmarFrag.OPCIONES_SACAR_FOTO:
				
				Util.SpSet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.OT_FOTO_FINAL,  String.valueOf(Configuracion.OT_FOTO_FINAL_OK));
				MainActivity.this.captureImage();
				break;
			default:
				break;
			}
			
		}
		
	};
	OnListaSelect accionListaSelect= new OnListaSelect() {
		
		@Override
		public void onClickList(int er) {
			// TODO Auto-generated method stub
			Util.Log("=>"+er);
			
			objEditor= new Editor(1, "prueba", "<b>Prueba</b>:Prueba", "ot","", 1);
			
			ir(PANTALLA_EDITOR);
		}
	};
	OnListaSelectOt accionLista_select=new OnListaSelectOt() {
		
		@Override
		public void onClickList(Ot er) {
			// TODO Auto-generated method stub
			ot_actual=er;
			ir(PANTALLA_OT);
		}
	};
	OnOt_FinalizarClick accionOT_Finalizar =new OnOt_FinalizarClick(){

		@Override
	public void onClickMap(Ot ot) {
			// TODO Auto-generated method stub
			String s_lat=Util.SpGet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.LAT_ULTIMA);
			String s_lng=Util.SpGet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.LNG_ULTIMA);
			LatLng latlng= new LatLng(Double.parseDouble(s_lat), Double.parseDouble(s_lng));
			ConfigWEB conf=DatabaseManager.GET_ULTIMA_CONFIG(MainActivity.this);
			//ir(PANTALLA_CONFIRMAR2);
			
			if(ot_pantalla_fin.get_en_el_perimetro(ot,MainActivity.this,latlng,conf.metros_fin)){				
			    ir(PANTALLA_CONFIRMAR);
			}else{
				if(conf.metros_fin<0){ 
					Utils.showDialog(MainActivity.this, "Error: Perimetro no valido");
				}else{
					Utils.showDialog(MainActivity.this, "Error: se encuentra lejos del perimetro de "+conf.metros_fin +"metros de la ot. Debe acercarse a la OT");	
				}
				
			}
			
			
		}};
	OnOtClick accionOT= new OnOtClick() {
		
		@Override
		public void onClickMap(final Ot ot,final int estado) {
			// TODO Auto-generated method stub
			 
			switch (estado) {
			case OTFrag.OT_CANCELAR:
				 ir(PANTALLA_PRINCIPAL);
				break;
			case OTFrag.OT_INICIALIZAR:		
				String s_lat=Util.SpGet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.LAT_ULTIMA);
				String s_lng=Util.SpGet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.LNG_ULTIMA);
				LatLng latlng= new LatLng(Double.parseDouble(s_lat), Double.parseDouble(s_lng));
				ConfigWEB conf=DatabaseManager.GET_ULTIMA_CONFIG(MainActivity.this);
								
				LatLng ot_pto= new LatLng(Double.parseDouble(ot.getLat()),Double.parseDouble(ot.getLng()));				
				
				double distancia = Util.getDistanceBetween(ot_pto.latitude, ot_pto.longitude, latlng.latitude, latlng.longitude);
				
				if(!ot_pantalla.get_en_el_perimetro(ot,MainActivity.this,latlng,conf.metros_ini)){
					
					Utils.showDialog(MainActivity.this,"No puede iniciar la ot se encuentra a mas de "+conf.metros_ini +" metros de la OT. Distancia "+distancia+ "m");
					
					Util.Log("No puede iniciar la ot se encuentra a mas de "+conf.metros_ini +" metros de la OT");
						break;
				}
				
				
				if(!DatabaseManager.IS_TOMAR_FOTO_INI(MainActivity.this)){
					captureImage();
					break;
				}
				
				
				Utils.showDialog(MainActivity.this, "Esta por iniciar esta OT", "Ok", "Cancelar"
						 ,new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub							
							    Util.SpSet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.OT_ACTUAL    , gson.toJson(ot,Ot.class));						     
								Util.SpSet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.OT_ESTADO    , String.valueOf(estado));
								Util.SpSet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.OT_NRO_ACTUAL, String.valueOf(ot.getNro_ot()));
								Util.SpSet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.OT_ID_ACTUAL , String.valueOf(ot.get_id()));
								
								Util.SpSet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.OT_FOTO_FINAL , String.valueOf(Configuracion.OT_FOTO_FINAL_ESPERA));
							//-----------------------------------------------------------------
								DatabaseManager mg= new  DatabaseManager(MainActivity.this);
								mg.Ot_finalizarAdd(ot_actual,  "", DatabaseManager.ESTADO_ACTIVO,-1);
								//-----------------------------------------------------------------	
								ir(PANTALLA_OT_FINALIZAR);
								
						}
				});				
						
				
			break;
			default:
				break;
			}
			
		
		}
	};

	private MenuItem okItem;

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments	
		if(position==PANTALLA_MAPA){
			DatabaseManager.ACTIVAR_BOTON_ASOCIAR(this, false);
		}
		ir(position);
	}

	public void onSectionAttached(int number) {
	
	}

	public void restoreActionBar() {

		if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          getSupportActionBar().setHomeButtonEnabled(true);
          getSupportActionBar().setTitle(mTitle);
	}
//--------------------------------------------------------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
				Util.Log("=>secrearon los botones----");
				getMenuInflater().inflate(R.menu.main2, menu);	
				searchItem = menu.findItem(R.id.action_search);
				actualizarItem = menu.findItem(R.id.action_actualizar2);				
				mapaItem= menu.findItem(R.id.action_mapa);
				fotoItem= menu.findItem(R.id.action_foto);
				okItem= menu.findItem(R.id.action_ok);				
				
				ocultar_menu_botones();	

				searchView =  (SearchView) MenuItemCompat.getActionView(searchItem);		
				searchView.setOnQueryTextListener(onQueryTextListener);
				mapaItem.setOnMenuItemClickListener(onMapaMenuItemClickListener);
				fotoItem.setOnMenuItemClickListener(onFotoMenuItemClickListener);
				okItem.setOnMenuItemClickListener(onOkMenuItemClickListener);
				actualizarItem.setOnMenuItemClickListener(onActualizarMenuItemClickListener);
								
				activar_menu_botones();
						
		return super.onCreateOptionsMenu(menu);
	}  
	
	OnMenuItemClickListener onOkMenuItemClickListener=new OnMenuItemClickListener(){

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			// TODO Auto-generated method stub
			
			switch (PANTALLA_ACTUAL) {			
			case PANTALLA_OPERARIOS_LIST:
				operarios_list.total_selecionados();
				ir(PANTALLA_CONFIRMAR);
				break;
			case PANTALLA_APERTURA:
				 apertura_pantalla.add_apertura();
				 ir(PANTALLA_CONFIRMAR);
				break;
			case PANTALLA_EQUIPOS_LIST:
				 equipos_list.total_selecionados();
				 ir(PANTALLA_CONFIRMAR);
				break;
			case PANTALLA_SERVICIOS_LIST:
				 servicios_list.total_selecionados();
				 ir(PANTALLA_CONFIRMAR);
				break;
			case PANTALLA_MATERIALES:
				 materiales_list.total_selecionados();
				 ir(PANTALLA_CONFIRMAR);
				break;
			case PANTALLA_RESPUESTAS_LIST:	
				
				 respuestas_list.total_selecionados();
				 
				 ir(PANTALLA_DERIVACIONES_OT);
				break;
			case PANTALLA_DERIVACIONES_OT:
				 derivaciones_ot_list.total_selecionados();		 
				 
				 ir(PANTALLA_CONFIRMAR);
				break;
			case PANTALLA_CONFIRMAR2:
				if(confirmar_pantalla2.confirmacion_guardar_controlar()){
					Utils.showDialog(MainActivity.this, "Esta por finalizar la OT", "Ok", "Cancelar"
							 ,new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub							
								 confirmar_pantalla2.confirmacion_guardar_fin();
								 Utils.showDialog(MainActivity.this, "Ot Guardada");
								 ir(PANTALLA_PRINCIPAL);
							}
						} );
				}else{
					
					 String error= Util.SpGet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.ULTIMO_ERROR, "");
					
					 Utils.showDialog(MainActivity.this, "Error al cargar:"+error);
				}
				break;
			case PANTALLA_CONFIRMAR:
				
					if(!DatabaseManager.IS_OPERARIOS_ACTUALES(MainActivity.this)){
					 Utils.showDialog(MainActivity.this, "Debe cargar los operarios ");
					 break;
				}
				
				boolean saco_foto=Boolean.parseBoolean(Util.SpGet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.OT_FOTO_FINAL));
							
				if(!DatabaseManager.GET_TIPO_FINALIZACION(MainActivity.this)){
					try {
						if(Integer.parseInt(Util.SpGet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.TIPO_DE_FINALIZACION))!=220
							&&	Integer.parseInt(Util.SpGet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.TIPO_DE_FINALIZACION))!=171	
						)
						{
							Utils.showDialog(MainActivity.this, "Debe cargar el tipo de finalizacion "+Util.SpGet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.TIPO_DE_FINALIZACION));
							break;
						}
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
				}
				
				 Utils.showDialog(MainActivity.this, "Esta por finalizar la OT", "Ok", "Cancelar"
						 ,new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub							
							 confirmar_pantalla.confirmacion_guardar();
							 Utils.showDialog(MainActivity.this, "Ot Guardada");
							 ir(PANTALLA_PRINCIPAL);
						}
					} );
					
				
				
				
				break;
			case PANTALLA_ARCHIVO_LIST:
				archivo_list.enviar_foto_all();
				break;	
			default:
				break;
			}
			
			
		
		
			
			return true;
		}
		};
	OnMenuItemClickListener onMapaMenuItemClickListener=new OnMenuItemClickListener(){

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			// TODO Auto-generated method stub
			
			 if(Configuracion.OT_isExists(MainActivity.this)){
				 Ot ot= gson.fromJson(Util.SpGet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.OT_ACTUAL ),Ot.class);				
				// Utils.showDialog(MainActivity.this, "nro ot:"+ot.getNro_ot());
				 Intent intent = new Intent(MainActivity.this, MapActivity.class);
				 intent.putExtra(VER_MAPA_LAT, ot.getLat());
				 intent.putExtra(VER_MAPA_LNG, ot.getLng());
				 intent.putExtra(VER_MAPA_OT,  String.valueOf(ot.getNro_ot()));		
				 DatabaseManager.ACTIVAR_BOTON_ASOCIAR(MainActivity.this,true);
				 startActivityForResult(intent, requestCode_new_download); 
			 }else{
				    // Utils.showDialog(MainActivity.this, "ot seleccionada "+DatabaseManager.OT_selecionada_isExists(MainActivity.this) );
					 if(DatabaseManager.OT_selecionada_isExists(MainActivity.this)){
						 Ot ot= gson.fromJson(Util.SpGet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.OT_SELECCIONADA ),Ot.class);				
						 //Utils.showDialog(MainActivity.this, "Nro Ot Seleccionada:"+ot.getNro_ot() + "("+ot.getLat()+","+ot.getLng()+")");
						 Intent intent = new Intent(MainActivity.this, MapActivity.class);
						 intent.putExtra(VER_MAPA_LAT, ot.getLat());
						 intent.putExtra(VER_MAPA_LNG, ot.getLng());
						 
						 intent.putExtra(VER_MAPA_OT,  String.valueOf(ot.getNro_ot()));	
						 DatabaseManager.ACTIVAR_BOTON_ASOCIAR(MainActivity.this,true);
						 startActivityForResult(intent, requestCode_new_download);
						
						 
					 }else{
						 DatabaseManager.ACTIVAR_BOTON_ASOCIAR(MainActivity.this,false);
						 ir(PANTALLA_MAPA); 	
					 }
				 
				 
				 
			 }
			
			 
			
			return false;
		}
		};
	OnMenuItemClickListener onActualizarMenuItemClickListener=new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				//Utils.showDialog(MainActivity.this, "Click en foto");		
		    	switch (PANTALLA_ACTUAL) {
				case PANTALLA_PRINCIPAL:
						principal_ot.exec_lista();
					break;
				default:
					break;
				}
				
				//adaptador.filter(arg0);
				return false;
			}};
	OnMenuItemClickListener onFotoMenuItemClickListener=new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				//Utils.showDialog(MainActivity.this, "Click en foto");		
				if(!DatabaseManager.GET_MODO_SEGURO_ACTIVADO(MainActivity.this)){
					captureImage();	
				}
				
				return false;
			}};
	OnQueryTextListener onQueryTextListener =new OnQueryTextListener() {				
		@Override
		public boolean onQueryTextSubmit(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("TextSubmit:"+arg0 );
			
			return false;
			
		}
		
		@Override
		public boolean onQueryTextChange(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("TextChange:"+arg0 );
			
		
			switch (PANTALLA_ACTUAL) {
			
			case PANTALLA_PRINCIPAL:
					//principal.setFiltro(arg0);
					principal_ot.setFiltro(arg0);
				break;
			case PANTALLA_SERVICIOS_LIST:
				servicios_list.setFiltro(arg0);
			break;
			case PANTALLA_RESPUESTAS_LIST:
				servicios_list.setFiltro(arg0);
			break;
			case PANTALLA_OPERARIOS_LIST:
				operarios_list.setFiltro(arg0);
			break;
			case PANTALLA_EQUIPOS_LIST:
				equipos_list.setFiltro(arg0);
			break;
			case PANTALLA_MATERIALES:
				materiales_list.setFiltro(arg0);
			break;
			default:
				break;
			}
			
			//adaptador.filter(arg0);
			return false;
		}
	};
	public void captureImage() {	   
	    // fetching the root directory
	     String root = Environment.getExternalStorageDirectory().getAbsolutePath();
	    /* + "/Your_Folder";*/

	     // Creating folders for Image
	     String imageFolderPath = root + "/ot_img";
	     File imagesFolder = new File(imageFolderPath);
	     imagesFolder.mkdirs();

	    // Generating file name
	   // imageName += "_"+Util.getDateNow()+".png";
	     String user=Util.SpGet(this, Configuracion.BIBLIOTECA, Configuracion.USUARIO);
	     String ot_nro=Util.SpGet(this, Configuracion.BIBLIOTECA, Configuracion.OT_NRO_ACTUAL);
	     String imageName = ot_nro+"_"+Util.getDateNow()+"_"+user+"_"+".jpg";

	    // Creating image here

	    File image = new File(imageFolderPath, imageName);
	    Uri fileUri = Uri.fromFile(image);
	    
/*
	    imageView.setTag(imageFolderPath + File.separator + imageName);
	    Util.SpSet(this, "Otfotos", "ultimafoto", imageName);
*/
	    
		Util.SpSet(MainActivity.this, Configuracion.BIBLIOTECA, Configuracion.FOTO_INI_ECHO, String.valueOf(false));
		
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	    startActivityForResult(takePictureIntent,  CAMERA_IMAGE_REQUEST);
	}
	
	//--------------------------------------------------------------------------------------------	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

	private void ir(int position){
		//Intent intent=null;		
		Util.Log("posicion:"+position);
		Intent intent;
		PANTALLA_ACTUAL=position;
		switch (position) {
	
		case PANTALLA_PRINCIPAL:
			Util.Log("posicion:=>ppal");
			mTitle="principal";	
			/*por regla si existe ot pendiente va a la pantalla de finalizacion*/
			if(!Configuracion.OT_isExists(this)){
				getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, principal_ot).commit();
			}else{
				ir(PANTALLA_OT_FINALIZAR);
			}			
			break;
		case PANTALLA_EDITOR:
			Util.Log("posicion:=>editor");
			mTitle="editor";
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, editor.newInstance(position,objEditor)).commit();
			break;	
		case PANTALLA_OT:
			Util.Log("posicion:=>ot");
			mTitle="OT";					
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, ot_pantalla.newInstance(position,ot_actual)).commit();
			break;	
		case PANTALLA_ACTUALIZAR:
			intent = new Intent(MainActivity.this, MainActivityDownload.class);
			startActivityForResult(intent, requestCode_new_download);
		break;
		case PANTALLA_about:
			mTitle="About";			
			getSupportActionBar().setSubtitle(mTitle);
			Utils.showDialog(this, "cosaysa sistemas desarrollo 2019");	
		break;
		case PANTALLA_LOGOUT:
			Util.SpSet(this, Configuracion.BIBLIOTECA,
					Configuracion.SESSION,Configuracion.FIN_DE_DIA);
				
				Util.SpSet(this, Configuracion.BIBLIOTECA,
						Configuracion.SESSION_FECHA,Util.getFechaActualFormat()+" "+Util.getHoraActualFormat());
			finish();
		break;
		
		case PANTALLA_MAPA:		
			intent = new Intent(MainActivity.this, MapActivity.class);
			startActivityForResult(intent, requestCode_new_mapa);			
			//ir(PANTALLA_GEOMETRIAS_ONE);
		break;
		case	PANTALLA_CONFIRMACION_LIST:
			mTitle="Confirmacion";			
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, confirmacion_list).commit();
		break;		
		
		case PANTALLA_CONFIGURACION:
			mTitle="Configuracion";
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, configuracionFrag).commit();
			break;
		case PANTALLA_SERVICIOS_LIST:
			mTitle="Servicios";
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, servicios_list).commit();
			break;
		case PANTALLA_RESPUESTAS_LIST:
			
			mTitle="Respuestas";
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, respuestas_list).commit();
			break;
		case PANTALLA_OPERARIOS_LIST:
			mTitle="Operarios";
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, operarios_list).commit();
			break;
		case PANTALLA_EQUIPOS_LIST:
			mTitle="Equipos";
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, equipos_list).commit();
			break;
		case PANTALLA_OT_FINALIZAR:
			mTitle="OT Finalizar";			
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, ot_pantalla_fin).commit();
			break;
		case PANTALLA_OT_FINALIZADA:	
			mTitle="OT Finalizada";			
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, ot_pantalla_finalizada).commit();
			
			break;
		case PANTALLA_APERTURA:
			mTitle="aperturas";
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, apertura_pantalla).commit();
			break;
		case PANTALLA_CONFIRMAR:
			mTitle="Confirmar";
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, confirmar_pantalla).commit();
			break;
		case PANTALLA_MATERIALES:
			mTitle="Materiales";
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, materiales_list).commit();
			break;	
		case PANTALLA_DERIVACIONES_OT:
			mTitle="Derivaciones";			
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, derivaciones_ot_list).commit();
			
			break;
		case PANTALLA_GEOMETRIAS_ONE:
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, geometria_one).commit();
			break;	
		case PANTALLA_ARCHIVO_LIST:
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, archivo_list).commit();
			break;
		case PANTALLA_CONFIRMAR2:
			mTitle="Confirmar";			
			getSupportActionBar().setSubtitle(mTitle);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, confirmar_pantalla2).commit();
			break;
		default:
			break;
		}
		activar_menu_botones();
		// PrintedPdfDocument document = new PrintedPdfDocument(this,   printAttributes);

				
	}
	/*
	public void printPDF(View view) {
	    PrintManager printManager = (PrintManager) getSystemService(PRINT_SERVICE);
	    printManager.print("print_any_view_job_name", new ViewPrintAdapter(this,
	            findViewById(R.id.relativeLayout)), null);
	}
*/

	
	private void ocultar_menu_botones() {
		// TODO Auto-generated method stub
		searchItem.setVisible(false);
		actualizarItem.setVisible(false);
		mapaItem.setVisible(false);
		fotoItem.setVisible(false);
		okItem.setVisible(false);
	}
	private void activar_menu_botones() {
		// TODO Auto-generated method stub
		Util.Log("posicion:=>"+"->"+PANTALLA_ACTUAL+"->"+PANTALLA_ACTUAL);
		
		if (searchItem != null) {
			ocultar_menu_botones();
			switch (PANTALLA_ACTUAL) {
			case PANTALLA_PRINCIPAL:
				actualizarItem.setVisible(true);	
				searchItem.setVisible(true);
				break;			
			case PANTALLA_OT:
				mapaItem.setVisible(true);
				fotoItem.setVisible(true);	
				break;
			case PANTALLA_OT_FINALIZAR:				
				mapaItem.setVisible(true);
				fotoItem.setVisible(true);	
				break;
			case PANTALLA_OPERARIOS_LIST:
				searchItem.setVisible(true);
				okItem.setVisible(true);
				break;
			case PANTALLA_CONFIGURACION:
				/*okItem.setVisible(true);*/
				break;
			case PANTALLA_APERTURA:
				okItem.setVisible(true);
				break;
			case PANTALLA_EQUIPOS_LIST:
				searchItem.setVisible(true);
				okItem.setVisible(true);
				break;
			case PANTALLA_SERVICIOS_LIST:
				searchItem.setVisible(true);
				okItem.setVisible(true);
				break;
			case PANTALLA_MATERIALES:
				searchItem.setVisible(true);
				actualizarItem.setVisible(true);
				okItem.setVisible(true);
				break;
			case PANTALLA_DERIVACIONES_OT:
				searchItem.setVisible(true);	
				okItem.setVisible(true);
				break;
			case PANTALLA_CONFIRMAR:
				okItem.setVisible(true);
				break;
			case PANTALLA_RESPUESTAS_LIST:
				okItem.setVisible(true);
				break;
			case PANTALLA_GEOMETRIAS_ONE:
				okItem.setVisible(true);
				break;	
			case PANTALLA_ARCHIVO_LIST:
				okItem.setVisible(true);
				break;	
			case PANTALLA_CONFIRMAR2:
				okItem.setVisible(true);
				break;		
			default:
				Util.Log("pantalla=>default"+"->"+PANTALLA_ACTUAL);
				break;
			}
			
		}
	}
	@Override
	public void onBackPressed() {
			
		Util.SpSet(this, Configuracion.BIBLIOTECA, Configuracion.ULTIMA_PANTALLA, String.valueOf(PANTALLA_ACTUAL));
		if(Configuracion.OT_isExists(this)){
			ir(PANTALLA_OT_FINALIZAR);
		}else{
			switch (PANTALLA_ACTUAL) {
			  
				case PANTALLA_EQUIPOS_LIST:
					ir(PANTALLA_CONFIRMAR);
					break;
				case PANTALLA_OPERARIOS_LIST:
					ir(PANTALLA_CONFIRMAR);
					break;
				case PANTALLA_APERTURA:
					ir(PANTALLA_CONFIRMAR);
					break;	
				case PANTALLA_SERVICIOS_LIST:
					ir(PANTALLA_CONFIRMAR);
					break;		
				case PANTALLA_MATERIALES:
					ir(PANTALLA_CONFIRMAR);
					break;		
				case PANTALLA_DERIVACIONES_OT:
					ir(PANTALLA_CONFIRMAR);
					break;		
				case PANTALLA_RESPUESTAS_LIST:
					ir(PANTALLA_CONFIRMAR);
					break;		
				default:
					if(PANTALLA_ACTUAL!=PANTALLA_PRINCIPAL){
						ir(PANTALLA_PRINCIPAL);
					}else{
						finish();
					}
		}
		}
		
		
	}
		
	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
	    super.onSaveInstanceState(outState);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    //if (requestCode == requestCode_new_mapa) {
	       // if(resultCode == Activity.RESULT_OK){
	          //  String featid=data.getStringExtra("featid");
	          //  ir(PANTALLA_GEOMETRIAS_ONE);
	        //}
	       
	    //}
		
		if (requestCode == CAMERA_IMAGE_REQUEST_OT) {
			//Utils.showDialog(this,"viene de la Camara");
			
			if(!Util.SpGet(this, Configuracion.BIBLIOTECA, Configuracion.FOTO_INI_ECHO, String.valueOf(false)).equals("true")){
				Util.SpSet(this, Configuracion.BIBLIOTECA, Configuracion.FOTO_INI_ECHO, String.valueOf(true));
			}
			
			try {
				boolean foto_fin=Boolean.getBoolean(Util.SpGet(this,  Configuracion.BIBLIOTECA, Configuracion.OT_FOTO_FINAL));
				if(foto_fin==Configuracion.OT_FOTO_FINAL_ESPERA){
					Util.SpSet(this, Configuracion.BIBLIOTECA, Configuracion.OT_FOTO_FINAL, String.valueOf(Configuracion.OT_FOTO_FINAL_OK));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}
