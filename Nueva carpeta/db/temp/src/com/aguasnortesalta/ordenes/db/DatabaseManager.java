package com.aguasnortesalta.ordenes.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aguasnortesalta.ordenes.AperturaFrag;
import com.aguasnortesalta.ordenes.GPSTracker;
import com.aguasnortesalta.ordenes.MainActivity;
import com.aguasnortesalta.ordenes.MyBroadcastReceiver;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.dao.ArchivoDAO;
import com.aguasnortesalta.ordenes.db.dao.Atributos_listasDAO;
import com.aguasnortesalta.ordenes.db.dao.Atributosxtipo_componenteDAO;
import com.aguasnortesalta.ordenes.db.dao.Categorias_tramoDAO;
import com.aguasnortesalta.ordenes.db.dao.ComponentesxatributosDAO;
import com.aguasnortesalta.ordenes.db.dao.ConfiguracionDAO;
import com.aguasnortesalta.ordenes.db.dao.ConfirmacionDAO;

import com.aguasnortesalta.ordenes.db.dao.DerivacionesotDAO;
import com.aguasnortesalta.ordenes.db.dao.EquipoDAO;
import com.aguasnortesalta.ordenes.db.dao.GeometriaDAO;
import com.aguasnortesalta.ordenes.db.dao.MaterialesotDAO;
import com.aguasnortesalta.ordenes.db.dao.OperarioDAO;
import com.aguasnortesalta.ordenes.db.dao.OtDAO;
import com.aguasnortesalta.ordenes.db.dao.Ot_aperturasDAO;
import com.aguasnortesalta.ordenes.db.dao.Ot_aperturas_superficieDAO;
import com.aguasnortesalta.ordenes.db.dao.Ot_finalizadaDAO;
import com.aguasnortesalta.ordenes.db.dao.PasosDAO;
import com.aguasnortesalta.ordenes.db.dao.RespuestasotDAO;
import com.aguasnortesalta.ordenes.db.dao.ServiciosxmotivoDAO;
import com.aguasnortesalta.ordenes.db.dao.SincronizarDAO;
import com.aguasnortesalta.ordenes.db.dao.TemplateDAO;
import com.aguasnortesalta.ordenes.db.dao.Tipo_componenteDAO;
import com.aguasnortesalta.ordenes.db.dao.UsuarioDAO;
import com.aguasnortesalta.ordenes.db.tables.ConfirmacionTable;
import com.aguasnortesalta.ordenes.db.tables.ConfirmacionTable.ConfirmacionColumns;
import com.aguasnortesalta.ordenes.db.tables.GeometriaTable;
import com.aguasnortesalta.ordenes.db.tables.OperarioTable;
import com.aguasnortesalta.ordenes.db.tables.OtTable;
import com.aguasnortesalta.ordenes.db.tables.Ot_aperturasTable;
import com.aguasnortesalta.ordenes.db.tables.SincronizarTable.SincronizarColumns;

import com.aguasnortesalta.ordenes.model.Archivo;
import com.aguasnortesalta.ordenes.model.Atributos_listas;
import com.aguasnortesalta.ordenes.model.Atributosxtipo_componente;
import com.aguasnortesalta.ordenes.model.Categorias_tramo;
import com.aguasnortesalta.ordenes.model.Componentesxatributos;
import com.aguasnortesalta.ordenes.model.ComponentesxatributosList;
import com.aguasnortesalta.ordenes.model.Config;
import com.aguasnortesalta.ordenes.model.ConfigWEB;
import com.aguasnortesalta.ordenes.model.Confirmacion;
import com.aguasnortesalta.ordenes.model.ConfirmacionList;
import com.aguasnortesalta.ordenes.model.Derivacionesot;
import com.aguasnortesalta.ordenes.model.DerivacionesotList;
import com.aguasnortesalta.ordenes.model.Equipo;
import com.aguasnortesalta.ordenes.model.EquipoList;
import com.aguasnortesalta.ordenes.model.Geometria;
import com.aguasnortesalta.ordenes.model.Materialesot;
import com.aguasnortesalta.ordenes.model.MaterialesotList;
import com.aguasnortesalta.ordenes.model.Operario;
import com.aguasnortesalta.ordenes.model.OperarioList;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.Ot_aperturas;
import com.aguasnortesalta.ordenes.model.Ot_aperturas_superficie;
import com.aguasnortesalta.ordenes.model.Ot_finalizada;
import com.aguasnortesalta.ordenes.model.Pasos;
import com.aguasnortesalta.ordenes.model.PasosList;
import com.aguasnortesalta.ordenes.model.Respuestasot;
import com.aguasnortesalta.ordenes.model.RespuestasotList;
import com.aguasnortesalta.ordenes.model.Serviciosxmotivo;
import com.aguasnortesalta.ordenes.model.ServiciosxmotivoList;
import com.aguasnortesalta.ordenes.model.Sincronizar;
import com.aguasnortesalta.ordenes.model.Template;
import com.aguasnortesalta.ordenes.model.Tipo_componente;
import com.aguasnortesalta.ordenes.model.Usuario;
import com.aguasnortesalta.ordenes.model.UsuarioList;
import com.aguasnortesalta.ordenes.model.geometrias.Features;
import com.aguasnortesalta.ordenes.model.geometrias.GeoList;
import com.aguasnortesalta.ordenes.model.geometrias.GeometriaFiltro;
import com.aguasnortesalta.ordenes.model.geometrias.Geometry;
import com.aguasnortesalta.ordenes.model.geometrias.Properties;
import com.aguasnortesalta.ordenes.model.geometrias2.Geometrias2;



import com.aguasnortesalta.ordenes.utils.Util;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.google.android.gms.internal.ca;
//import com.google.android.gms.internal.mg;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.Log;



public class DatabaseManager {

	private Context context;
	private SQLiteDatabase db;	
	private OpenHelper openHelper;
	private Gson gson = new Gson();
	
	private UsuarioDAO usuarioDAO;
	private GeometriaDAO geometriaDAO;
	private RespuestasotDAO respuestasDAO;
	private ServiciosxmotivoDAO serviciosxmotivoDAO;
	private EquipoDAO equipoDAO;
	private OperarioDAO operarioDAO;
	private Ot_finalizadaDAO ot_finalizadaDAO;
	private OtDAO ot_DAO;
	private Ot_aperturas_superficieDAO ot_aperturas_superficieDAO;
	private Ot_aperturasDAO ot_aperturasDAO;
	
	private MaterialesotDAO materialesotDAO;
	private DerivacionesotDAO derivacionesotDAO;
	private ConfirmacionDAO confirmacionDAO;
	private Categorias_tramoDAO categorias_tramoDAO;
	private Atributos_listasDAO atributos_listaDAO;
	private Atributosxtipo_componenteDAO atributosxtipo_componenteDAO;
	private Tipo_componenteDAO tipo_componenteDAO;
	private ComponentesxatributosDAO componentesxatributosDAO;
	private SincronizarDAO sincronizarDAO;	
	private ArchivoDAO archivoDAO;
	private GPSTracker location;
	private ConfiguracionDAO configuracionDAO; 
	private TemplateDAO templateDAO;
	private PasosDAO pasosDAO;
	
	public static final String ESTADO_ACTIVO="A"; 
	public static final String ESTADO_FINALIZAR="F";
	public static final String ESTADO_ENVIADO="E";
	
	public DatabaseManager(Context context,String pantalla) {
		
		 ini(context);
		 add_confirmacion_pantalla( pantalla);
	}
	
	public DatabaseManager(Context context) {
		ini(context);
        
	}
	private void ini(Context context){
		this.context = context;
		openHelper = new OpenHelper(this.context);
		db = openHelper.getWritableDatabase();
		
        usuarioDAO = new UsuarioDAO(db);
        geometriaDAO = new GeometriaDAO(db);
        respuestasDAO= new RespuestasotDAO(db);
        serviciosxmotivoDAO= new ServiciosxmotivoDAO(db);
        equipoDAO= new EquipoDAO(db);
        operarioDAO= new OperarioDAO(db); 
        ot_DAO = new OtDAO(db);
        ot_finalizadaDAO= new Ot_finalizadaDAO(db);
        ot_aperturas_superficieDAO= new Ot_aperturas_superficieDAO(db);
        ot_aperturasDAO= new  Ot_aperturasDAO(db);
        materialesotDAO= new  MaterialesotDAO(db);
        derivacionesotDAO= new  DerivacionesotDAO(db);
        confirmacionDAO= new ConfirmacionDAO(db);
        categorias_tramoDAO = new Categorias_tramoDAO(db);
        atributos_listaDAO= new Atributos_listasDAO(db);
        atributosxtipo_componenteDAO= new Atributosxtipo_componenteDAO(db);
        tipo_componenteDAO= new Tipo_componenteDAO(db);
        componentesxatributosDAO= new ComponentesxatributosDAO(db);
        sincronizarDAO= new SincronizarDAO(db);
        archivoDAO= new ArchivoDAO(db);  
        configuracionDAO= new ConfiguracionDAO(db);
        templateDAO=new TemplateDAO(db);
        pasosDAO = new PasosDAO(db);
         try {
			location = new GPSTracker(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log("error(gps)=>"+e.getMessage());
		}
         List<Tipo_componente> componentes  = this.get_tipo_componente();
         Configuracion.FILTROS_GEOMETRIAS   = new  GeometriaFiltro[componentes.size()+2];         
         Configuracion.FILTROS_GEOMETRIAS[0]= new  GeometriaFiltro(-1,"Todos" ) ;
         Configuracion.FILTROS_GEOMETRIAS[1]= new  GeometriaFiltro( 1,"Tramos") ;
         
         for(int i=0; i<componentes.size();i++){
        	 Configuracion.FILTROS_GEOMETRIAS[i+2]=new GeometriaFiltro(componentes.get(i).getId_tipo_componente(),componentes.get(i).getDesc_tipo_componente()) ;
         }
         
	}
	public  void loadContentExt(){}

	public SQLiteDatabase gedDb() {
		return db;
	}

	public Usuario getUsuario(long id) {
		Usuario res = usuarioDAO.get(id);
		return res;
	}
	public Config get_ultima_actualizacion(){
		return configuracionDAO.getConfiguracion();
	} 
	
	
	public void geometrias_actualizar(GeoList geolist ){
		List<Features> features = geolist.features;
		for (Features features2 : features) {
		   if(!Exists(GeometriaTable.TABLE_NAME ,GeometriaTable.GeometriaColumns.FEATID,  String.valueOf(features2.featid)) ){
			  
			   Double lng = features2.geometry.getCoordinates().get(0).get(0).get(0);
			   Double lat = features2.geometry.getCoordinates().get(0).get(0).get(1);
			   Util.Log("json=>lat:"+lat+",lng:"+lng);
			   			 
			   geometriaDAO.insert(new String[]{
							 String.valueOf(features2.featid)
							,String.valueOf(features2.featid)
							,features2.type_geometry
							,features2.s_properties
							,features2.s_geometry
							,String.valueOf(features2.ACTUALIZADO)	
							,String.valueOf(lat)	
							,String.valueOf(lng)	
					}
					
			); 
		   }
			
			
		}
		
	}
	public List<Respuestasot> getRespuestas(){
		List<Respuestasot> features = new ArrayList<Respuestasot>();
		features=null;
		String geren=Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.GEREN, "");
		Respuestasot[] spam = respuestasDAO.get("geren=?", new String[]{geren});
		features=Arrays.asList(spam);
		Util.Log("size=>"+features.size());
		return features;
	}

	
	public List<Componentesxatributos> getComponentesxAtributos_view(long id_tipo_componente,long id_componente){
		List<Componentesxatributos> features = new ArrayList<Componentesxatributos>();
		features=null;
		Componentesxatributos[] spam = componentesxatributosDAO.get_view("id_tipo_componente=? and id_componente=?", new String[]{String.valueOf(id_tipo_componente),String.valueOf(id_componente)});
		features=Arrays.asList(spam);
		Util.Log("size=>"+features.size());
		
		return features;
	}
	public List<Componentesxatributos> getComponentesxAtributos_view(String featid){	
		List<Componentesxatributos> features = new ArrayList<Componentesxatributos>();
		features=null;
		Componentesxatributos[] spam = componentesxatributosDAO.get_view("featid=?", new String[]{featid});
		features=Arrays.asList(spam);
		Util.Log("size(featid)=>"+features.size());
		
		return features;
	}
	public long getComponentesxAtributos_cant(String featid){	
	
		return componentesxatributosDAO.get_view_cant("featid=?", new String[]{featid});
	}
	public Ot get_ultima_ot(){		
		Ot ot= gson.fromJson(Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.OT_ACTUAL,"" ),Ot.class);
	    return ot;
	} 
	public void ot_seleccionada(Ot ot){
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.OT_SELECCIONADA   , gson.toJson(ot,Ot.class));
		
	}
	public  Ot ot_get_seleccionada(){
		Ot ot= gson.fromJson(Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.OT_SELECCIONADA,"" ),Ot.class);	
		return ot;
		
	}
	public static boolean OT_selecionada_isExists(Activity a) {
	// TODO Auto-generated method stub		
		String s=Util.SpGet(a, Configuracion.BIBLIOTECA, Configuracion.OT_SELECCIONADA);
		return (s.length()>0)?true:false;	
	}
	
	public Respuestasot getRespuestaOne(long id_res){
		Respuestasot[] spam = respuestasDAO.get("id_res=?", new String[]{String.valueOf(id_res)});
		if(spam!=null){
			return spam[0];
		}
		return null;
		
	}
	public List<Serviciosxmotivo> getServiciosxmotivo() {
		// TODO Auto-generated method stub
		List<Serviciosxmotivo> features = new ArrayList<Serviciosxmotivo>();
		features=null;
		Serviciosxmotivo[] spam = serviciosxmotivoDAO.get("", null);
		features=Arrays.asList(spam);
		return features;
	}
	public List<Equipo> getEquipos() {
		// TODO Auto-generated method stub
		List<Equipo> features = new ArrayList<Equipo>();
		features=null;
		Equipo[] spam = equipoDAO.get("", null);
		features=Arrays.asList(spam);
		return features;
	}
	public Ot_aperturas_superficie[] getOT_Aperturas_superficie(String tipo){
		return ot_aperturas_superficieDAO.get("tipo_apertura=?", new String[]{tipo});
	}
	public Ot_aperturas_superficie[] getOT_Aperturas_superficieAll(){
		return ot_aperturas_superficieDAO.get("", null);
	}
	public List<Operario> getOperarios() {
		// TODO Auto-generated method stub
		List<Operario> features = new ArrayList<Operario>();
		features=null;
		String geren= Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.GEREN, "");
		
		Operario[] spam = operarioDAO.get("geren=?",new String[]{geren});
		
		features=Arrays.asList(spam);
		return features;
	}
	public Operario[] getOperariosArray() {
		// TODO Auto-generated method stub
		
		return  operarioDAO.get("", null);
	}
	public List<Ot_finalizada> getOt_finalizadas() {
		// TODO Auto-generated method stub
		List<Ot_finalizada> features = new ArrayList<Ot_finalizada>();
		features=null;
		
		Ot_finalizada[] spam = ot_finalizadaDAO.get("", null);
		if(spam!=null){
			features=Arrays.asList(spam);
		}
		
		
		return features;
	}
	public static void RESPUESTA_ULTIMA_SET(Context context, long id_res){
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.ULTIMA_FINALIZACION, String.valueOf(id_res));
	}
	public static long RESPUESTA_ULTIMA_GET(Context context){
		return Long.parseLong(Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.ULTIMA_FINALIZACION,"-1"));
	}

	public Confirmacion getObjectConfirmacion(){
		
		Confirmacion cof= new Confirmacion();
		String json=Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.OT_ACTUAL,"");
		Ot ot= gson.fromJson(json, Ot.class);
		Ot_aperturas[] aperturas = ot_aperturasDAO.get("",null);
		if(aperturas!=null && aperturas.length>0)
			cof.setAperturas_calzada(Arrays.asList(aperturas));
		
		
		cof.setOt_relacionada(ot);
		//getOperariosActuales();
		OperarioList operarios_actuales = getOperariosActuales();
		if(operarios_actuales.data!=null){
			cof.setOperarios(operarios_actuales.data);
		}
		EquipoList	equipo_list= getEquipoActuales();
		
		if(equipo_list.data!=null){
			cof.setEquipos(equipo_list.data);
		}
		
		ServiciosxmotivoList servicios_list= getServiciosxmotivoActuale();
		if(servicios_list.data!=null){
			cof.setTrabajos_ejecutados(servicios_list.data);
		}
		
		MaterialesotList materiales_list= getMaterialesotActuales();
		if(materiales_list.data!=null){
			cof.setMaterialesotList(materiales_list.data);
		}
		cof.setId_res(Long.parseLong(Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.ULTIMA_FINALIZACION, "0")));
		
		DerivacionesotList derivaciones_ot_list= getDerivacionesotActuales();
		
		if(derivaciones_ot_list.data!=null){
			cof.setDerivacionesot(derivaciones_ot_list.data);
		}
		
		return cof;
	}
	
	public void setOTaddList(List<Ot> datos) {
		// TODO Auto-generated method stub
		delete_table(OtTable.TABLE_NAME );
		for(int i=0; i<datos.size();i++){	
			Ot o=datos.get(i);
			ot_DAO.insert(o.getStringArray());
			
		}
		
	}
	public void OT_FINALIZAR_ADD(Ot ot, String observacion, String ESTADO, int ID_MOTIVO_FINALIZAR, LatLng latlng ){
		String t=Configuracion.get_usuario_actual(context);	
		
		Ot_finalizada ot_finalizar= new Ot_finalizada();
	
		if(DatabaseManager.ESTADO_ACTIVO.equals(ESTADO)){
			ot_finalizar.set_id( ot.get_id());
			ot_finalizar.setOt( ot.getNro_ot());
			ot_finalizar.setFechainicio( Ot_finalizada.GET_INI_FECHA_ACTUAL_INI());		
			ot_finalizar.setFechafinalizo( Ot_finalizada.GET_INI_FECHA_ACTUAL_FIN());		
			ot_finalizar.setIdmotivofinaliza(Ot_finalizada.GET_INI_ID_MOTIVO_FINALIZAR());		
			ot_finalizar.setLat(String.valueOf(latlng.latitude));
			ot_finalizar.setLng(String.valueOf(latlng.longitude));		
			ot_finalizar.setEstado(ESTADO);
			ot_finalizar.setT(t);
			ot_finalizar.setFch(Util.getDateNowFormat());
			ot_finalizar.setObservacion(observacion);		
			
			ot_finalizadaDAO.insert3(ot_finalizar);
		}else{
			ot_finalizar.set_id( ot.get_id());
			ot_finalizar.setOt( ot.getNro_ot());			
			ot_finalizar.setFechafinalizo( Ot_finalizada.GET_FIN_FECHA_ACTUAL_FIN());		
			ot_finalizar.setIdmotivofinaliza(ID_MOTIVO_FINALIZAR);		
			ot_finalizar.setLat(String.valueOf(latlng.latitude));
			ot_finalizar.setLng(String.valueOf(latlng.longitude));		
			ot_finalizar.setEstado(ESTADO);
			ot_finalizar.setT(t);
			ot_finalizar.setFch(Util.getDateNowFormat());
			ot_finalizar.setObservacion(observacion);
			
			ot_finalizadaDAO.update(ot_finalizar);
			Configuracion.Ot_actual_finalizar(context);
			set_borrar_confirmacion(context);
			RESPUESTA_ULTIMA_SET(context,-1);
			
		}
		
		add_confirmacion( ot_finalizar);	
		
	} 
	public void Ot_finalizarAdd2(Ot ot, String observacion, String ESTADO, int ID_MOTIVO_FINALIZAR ){
		String t=Configuracion.get_usuario_actual(context);	
		
		Ot_finalizada ot_finalizar= new Ot_finalizada();
		location.getLocation();		
	
		
		if(DatabaseManager.ESTADO_ACTIVO.equals(ESTADO)){
			ot_finalizar.set_id( ot.get_id());
			ot_finalizar.setOt( ot.getNro_ot());
			ot_finalizar.setFechainicio( Ot_finalizada.GET_INI_FECHA_ACTUAL_INI());		
			ot_finalizar.setFechafinalizo( Ot_finalizada.GET_INI_FECHA_ACTUAL_FIN());		
			ot_finalizar.setIdmotivofinaliza(Ot_finalizada.GET_INI_ID_MOTIVO_FINALIZAR());		
			ot_finalizar.setLat(String.valueOf(location.getLatitude()));
			ot_finalizar.setLng(String.valueOf(location.getLongitude()));		
			ot_finalizar.setEstado(ESTADO);
			ot_finalizar.setT(t);
			ot_finalizar.setFch(Util.getDateNowFormat());
			ot_finalizar.setObservacion(observacion);		
			
			ot_finalizadaDAO.insert3(ot_finalizar);
		}else{
			ot_finalizar.set_id( ot.get_id());
			ot_finalizar.setOt( ot.getNro_ot());
			/*ot_finalizar.setFechainicio( Ot_finalizada.GET_INI_FECHA_ACTUAL_INI());*/		
			ot_finalizar.setFechafinalizo( Ot_finalizada.GET_FIN_FECHA_ACTUAL_FIN());		
			ot_finalizar.setIdmotivofinaliza(ID_MOTIVO_FINALIZAR);		
			ot_finalizar.setLat(String.valueOf(location.getLatitude()));
			ot_finalizar.setLng(String.valueOf(location.getLongitude()));		
			ot_finalizar.setEstado(ESTADO);
			ot_finalizar.setT(t);
			ot_finalizar.setFch(Util.getDateNowFormat());
			ot_finalizar.setObservacion(observacion);
			
			ot_finalizadaDAO.update(ot_finalizar);
			Configuracion.Ot_actual_finalizar(context);
			set_borrar_confirmacion(context);
			RESPUESTA_ULTIMA_SET(context,-1);
			
		}
		
		//add_confirmacion( ot_finalizar);	
		
	}
	public void Ot_finalizarAdd(Ot ot, String observacion, String ESTADO, int ID_MOTIVO_FINALIZAR ){
		String t=Configuracion.get_usuario_actual(context);	
		
		Ot_finalizada ot_finalizar= new Ot_finalizada();
		location.getLocation();		
	
		
		if(DatabaseManager.ESTADO_ACTIVO.equals(ESTADO)){
			ot_finalizar.set_id( ot.get_id());
			ot_finalizar.setOt( ot.getNro_ot());
			ot_finalizar.setFechainicio( Ot_finalizada.GET_INI_FECHA_ACTUAL_INI());		
			ot_finalizar.setFechafinalizo( Ot_finalizada.GET_INI_FECHA_ACTUAL_FIN());		
			ot_finalizar.setIdmotivofinaliza(Ot_finalizada.GET_INI_ID_MOTIVO_FINALIZAR());		
			ot_finalizar.setLat(String.valueOf(location.getLatitude()));
			ot_finalizar.setLng(String.valueOf(location.getLongitude()));		
			ot_finalizar.setEstado(ESTADO);
			ot_finalizar.setT(t);
			ot_finalizar.setFch(Util.getDateNowFormat());
			ot_finalizar.setObservacion(observacion);		
			
			ot_finalizadaDAO.insert3(ot_finalizar);
		}else{
			ot_finalizar.set_id( ot.get_id());
			ot_finalizar.setOt( ot.getNro_ot());
			/*ot_finalizar.setFechainicio( Ot_finalizada.GET_INI_FECHA_ACTUAL_INI());*/		
			ot_finalizar.setFechafinalizo( Ot_finalizada.GET_FIN_FECHA_ACTUAL_FIN());		
			ot_finalizar.setIdmotivofinaliza(ID_MOTIVO_FINALIZAR);		
			ot_finalizar.setLat(String.valueOf(location.getLatitude()));
			ot_finalizar.setLng(String.valueOf(location.getLongitude()));		
			ot_finalizar.setEstado(ESTADO);
			ot_finalizar.setT(t);
			ot_finalizar.setFch(Util.getDateNowFormat());
			ot_finalizar.setObservacion(observacion);
			
			ot_finalizadaDAO.update(ot_finalizar);
			Configuracion.Ot_actual_finalizar(context);
			set_borrar_confirmacion(context);
			RESPUESTA_ULTIMA_SET(context,-1);
			
		}
		
		add_confirmacion( ot_finalizar);	
		
	}
	public List<Ot> getOTList(){
		List<Ot> lista = new ArrayList<Ot>();
		try {
			Ot[] datos=ot_DAO.get_sin_finalizar();
			//Ot[] datos=ot_DAO.get("",null);
			
			Util.Log("ot cant=>"+datos.length);
			
			if(datos!=null){
				lista =Arrays.asList(datos);
			}
			Util.Log("ot cant=>(s)"+lista.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;

	}
	
	public long getOTList_cant(){
		return ot_DAO.get_cant("", null);
		
	}
	
	public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radio de la tierra en  kilómetros
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
       /* DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        */
        double meter = valueResult % 1000;
        /*
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);
*/
        return Radius * c;
    }
	
	public List<Features> getGeometrias(double lat,double lng,long tipo){		
		List<Features> features = new ArrayList<Features>();
		Geometria[] geometrias = geometriaDAO.get(lat,lng,tipo);
		if(geometrias!=null){
			for (int i = 0; i < geometrias.length; i++) {			
			try {
				Geometry geometry = gson.fromJson(geometrias[i].getGeometry(),Geometry.class);
				Properties properties = gson.fromJson(geometrias[i].getProperties(),Properties.class);
				Features f=new Features(
						 geometrias[i].getFeatid()
						,geometrias[i].getType()
						,geometrias[i].getProperties()
						,geometrias[i].getGeometry()
						,geometry
						,properties
						,geometrias[i].getLat()
						,geometrias[i].getLng()
						
						);
				LatLng inicio= new LatLng(lat, lng);
				LatLng fin=new LatLng(Double.parseDouble(geometrias[i].getLat()), Double.parseDouble(geometrias[i].getLng()));
				
				if(Util.getDistanceBetween(inicio, fin)<200){
					features.add(f);
				}
					
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				Util.Log("Error Featid=>"+ geometrias[i].getFeatid() +"e="+e.getMessage());
				e.printStackTrace();
			}
			}
		}
		
		
		return features;
		
	}
	public String get_tramos_descripicion(String featid){
		
		try {
			Componentesxatributos[] cc = componentesxatributosDAO.get_view("featid=? and (id_tipo_atributo =? or id_tipo_atributo =?)",new String[]{featid,"15","64"});
			return cc[0].getValor()+"-"+cc[1].getValor();/*material-diametro*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log("Error tramos=>"+featid +"-e->"+e.getMessage());
			return "sin datos";
		}
	}
	public List<Features> getGeometrias(String featid){		
		List<Features> features = new ArrayList<Features>();
		Geometria[] geometrias = geometriaDAO.get("featid=?",new String[]{featid});
		if(geometrias!=null){
			for (int i = 0; i < geometrias.length; i++) {				
			try {
				Geometry geometry = gson.fromJson(geometrias[i].getGeometry(),Geometry.class);
				Properties properties = gson.fromJson(geometrias[i].getProperties(),Properties.class);
				Features f=new Features(
						 geometrias[i].getFeatid()
						,geometrias[i].getType()
						,geometrias[i].getProperties()
						,geometrias[i].getGeometry()
						,geometry
						,properties
						,geometrias[i].getLat()
						,geometrias[i].getLng()
						
						);
				features.add(f);
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				Util.Log("Error Featid=>"+ geometrias[i].getFeatid() +"e="+e.getMessage());
				e.printStackTrace();
			}
			}
		}
		
		
		return features;
		
	}
	public long probarGeometrias(){			
		 return geometriaDAO.cant_total();	
	}
	
	public boolean Exists(String TABLE_NAME ,String COLUMN_NAME, String searchItem) {

	    String[] columns = { COLUMN_NAME };
	    String selection = COLUMN_NAME + " =?";
	    String[] selectionArgs = { searchItem };
	    String limit = "1";

	    Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);
	    boolean exists = (cursor.getCount() > 0);
	    cursor.close();
	    return exists;
	}
	public boolean delete_table(String TABLE_NAME ) {

	   db.delete(TABLE_NAME, "", null);
	   return true;
	
	}
	public void delete_apertura(long id){
		db.delete(Ot_aperturasTable.TABLE_NAME, Ot_aperturasTable.Ot_aperturasColumns._ID+"=?",  new String[]{String.valueOf(id)});
	}
	public void delete_operario(long id){
		db.delete(OperarioTable.TABLE_NAME, OperarioTable.OperarioColumns._ID+"=?",  new String[]{String.valueOf(id)});
	}
	public void setNuevaApertura(Ot_aperturas ot_apertura) {
		// TODO Auto-generated method stub
		ot_aperturasDAO.insert3(ot_apertura);
	}


	public void setOperariosActuales(OperarioList operarios_list) {
		// TODO Auto-generated method stub
		//ot_aperturasDAO.insert3(ot_apertura);
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.OPERARIOS_ACTUALES, gson.toJson(operarios_list, OperarioList.class));
		
	}
	public static ConfigWEB GET_ULTIMA_CONFIG(Context context){
		// TODO utilizo la gson declarado para poder hacer el procedimiento independiente
		Gson gson = new Gson();
		String json=Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.CONFIGURACION_WEB, "");
		if(json.length()>0){			
			ConfigWEB c = gson.fromJson(json, ConfigWEB.class);
			return c;
			
		}
		return new ConfigWEB(1,"",-1,-1,-1,-1);

	}
	public static void SET_ULTIMA_CONFIG(Context context, ConfigWEB config){
		// TODO utilizo la gson declarado para poder hacer el procedimiento independiente
		Gson gson = new Gson();
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.CONFIGURACION_WEB, gson.toJson(config, ConfigWEB.class));
        DatabaseManager.SET_MODO_SEGURO(context, String.valueOf(config.modo_seguro));

	}
	
	public static boolean  IS_OPERARIOS_ACTUALES(Context context) {
		// TODO Auto-generated method stub
		
		return (Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.OPERARIOS_ACTUALES, "")).equals("")?false:true;
		
	}
	public OperarioList getOperariosActuales() {
		// TODO Auto-generated method stub		
		OperarioList operarios_list =new OperarioList();
		String json=Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.OPERARIOS_ACTUALES, "");
		if(json.length()>0){			
			operarios_list = gson.fromJson(json, OperarioList.class);
			
		}
	
		
		return operarios_list;
	}
	
	public void setEquiposActuales(EquipoList equipo_list) {
		// TODO Auto-generated method stub
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.EQUIPOS_ACTUALES, gson.toJson(equipo_list, EquipoList.class));
		
	}
	public EquipoList getEquipoActuales() {
		// TODO Auto-generated method stub		
		EquipoList _list =new EquipoList();
		String json=Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.EQUIPOS_ACTUALES, "");
		if(json.length()>0){			
			_list = gson.fromJson(json, EquipoList.class);
			
		}
	
		
		return _list;
	}
	public void setServiciosxmotivoActuales(ServiciosxmotivoList _list) {
		// TODO Auto-generated method stub
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.SERVICIOS_ACTUALES, gson.toJson(_list, ServiciosxmotivoList.class));
	}
	public ServiciosxmotivoList getServiciosxmotivoActuale() {
		// TODO Auto-generated method stub		
		ServiciosxmotivoList _list =new ServiciosxmotivoList();
		String json=Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.SERVICIOS_ACTUALES, "");
		if(json.length()>0){			
			_list = gson.fromJson(json, ServiciosxmotivoList.class);
			
		}
	
		
		return _list;
	}
	public List<Materialesot> getMaterialesots() {
		// TODO Auto-generated method stub
		Materialesot[] datos = materialesotDAO.get("",null);
		Util.Log("ot cant=>"+datos.length);
		List<Materialesot> lista = new ArrayList<Materialesot>();
		if(datos!=null){
			lista =Arrays.asList(datos);
		}
		return lista ;
	}
	public void setMaterialesotActuales(MaterialesotList _list) {
		// TODO Auto-generated method stub
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.MATERIALES_ACTUALES, gson.toJson(_list, MaterialesotList.class));

	}
	public MaterialesotList getMaterialesotActuales() {
		// TODO Auto-generated method stub		
		MaterialesotList _list =new MaterialesotList();
		String json=Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.MATERIALES_ACTUALES, "");
		if(json.length()>0){			
			_list = gson.fromJson(json, MaterialesotList.class);
			
		}
	
		
		return _list;
	}
	public List<Derivacionesot> getDerivacionesot() {
		// TODO Auto-generated method stub
		long id_res = RESPUESTA_ULTIMA_GET(context);
		
		Util.Log("=>Derivacionesot---------");
		List<Derivacionesot> lista = new ArrayList<Derivacionesot>();
		
		try {
			Derivacionesot[] datos = derivacionesotDAO.get("id_res=? and geren=?", new String[]{ String.valueOf(id_res), Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.GEREN, "")});
			if(datos!=null){
				return Arrays.asList(datos);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista ;
		
	}
	public void setDerivacionesotActuales(DerivacionesotList _list) {
		// TODO Auto-generated method stub
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.DERIVACIONES_OT_ACTUALES, gson.toJson(_list, DerivacionesotList.class));

	}
	public DerivacionesotList getDerivacionesotActuales() {
		// TODO Auto-generated method stub
		DerivacionesotList _list =new DerivacionesotList();
		String json=Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.DERIVACIONES_OT_ACTUALES, "");
		if(json.length()>0){			
			_list = gson.fromJson(json, DerivacionesotList.class);			
		}	
		
		return _list;
	}

	public Map<String,String> get_parameter_confirmacion(Confirmacion conf){
		 Map<String,String> params = new HashMap<String, String>();
         params.put("ot",ConfirmacionColumns.NRO_OT);
         params.put("valor",gson.toJson(conf, Confirmacion.class));
         return params;
		
	}
	public Map<String,String> get_parameter_sincronizacion(Sincronizar conf){
		 Map<String,String> params = new HashMap<String, String>();
        params.put(SincronizarColumns.ID,conf.getId());       
        params.put(SincronizarColumns.TIPO,conf.getTipo());
        params.put("valor",conf.getValor());
        return params;
		
	}
	public List<Confirmacion> getConfirmacions() {
		// TODO Auto-generated method stub
		List<Confirmacion> c= new ArrayList<Confirmacion>();		
		Confirmacion[] spam = confirmacionDAO.get("", null);
		if(spam!=null){
			c=Arrays.asList(spam);
		}
		
		return c;		
	}
	
	public List<Categorias_tramo> getCategorias_tramo() {
		// TODO Auto-generated method stub
		List<Categorias_tramo> c= new ArrayList<Categorias_tramo>();		
		Categorias_tramo[] spam = categorias_tramoDAO.get("", null);
		if(spam!=null){
			c=Arrays.asList(spam);
		}
		
		return c;		
	}
	
	public List<Atributos_listas> getAtributos_lista(long id_tipo_atributo) {
		// TODO Auto-generated method stub
		List<Atributos_listas> c= new ArrayList<Atributos_listas>();		
		Atributos_listas[] spam = atributos_listaDAO.get("id_tipo_atributo=?", new String[]{String.valueOf(id_tipo_atributo)});
		if(spam!=null){
			c=Arrays.asList(spam);
		}
		
		return c;		
	}
	
	public void set_ultima_posicion(LatLng placePosition) {
		// TODO Auto-generated method stub
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.LAT_ULTIMA, String.valueOf(placePosition.latitude));
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.LNG_ULTIMA, String.valueOf(placePosition.longitude));

	}
	public LatLng get_ultima_posicion() {
		// TODO Auto-generated method stub
		try {
			return new LatLng(
					 Double.parseDouble(Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.LAT_ULTIMA, "0"))
					,Double.parseDouble(Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.LNG_ULTIMA, "0"))
			);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return new LatLng(0,0);
			
		}

	}
	public void set_ultimo_featid(String featid) {
		// TODO Auto-generated method stub
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.FEATID_ULTIMO, featid);
		
	}
	
	public String get_ultimo_featid() {
		// TODO Auto-generated method stub
		return Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.FEATID_ULTIMO, "0");
		
	}
	public List<Sincronizar> getSincronizars() {
		// TODO Auto-generated method stub sincronizarDAO
		
		List<Sincronizar> c= new ArrayList<Sincronizar>();		
		Sincronizar[] spam = sincronizarDAO.get("", null);
		if(spam!=null){
			c=Arrays.asList(spam);
		}
		return c;
	}
	public LatLng get_pto_actual(){
		try {
			if(location.isGPSEnabled){
				Util.Log("distancia error=>gps");
				location.getLocation();		
				if(location.getLatitude()!=0.0){
					Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.LAT_ULTIMA, String.valueOf(location.getLatitude()));
					Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.LNG_ULTIMA, String.valueOf(location.getLongitude()));
					return new LatLng(location.getLatitude(),location.getLongitude());
				}else{
					
					return new LatLng(Configuracion.LAT_DEFAULT,Configuracion.LNG_DEFAULT);
				}
			}else{
				return new LatLng(Configuracion.LAT_DEFAULT,Configuracion.LNG_DEFAULT);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log("distancia error=>"+e.getMessage());
			return new LatLng(Configuracion.LAT_DEFAULT,Configuracion.LNG_DEFAULT);
			
		}
		
		
	}

/*---------------------------------------------------------------------------*/		

	public void add_sincronisacion(Sincronizar sincronizar) {
		// TODO Auto-generated method stub			
		try {
			location.getLocation();		
			sincronizar.setLat(String.valueOf(location.getLatitude()));
			sincronizar.setLng(String.valueOf(location.getLongitude()));
			sincronizar.setPrecision(String.valueOf(location.precision));		
			sincronizar.setGps(location.isGPSEnabled?1:0);
			sincronizar.setRed(location.isNetworkEnabled?1:0);
		} catch (Exception e) {
			// TODO Auto-generated catch block			
			sincronizar.setLat("0.0");
			sincronizar.setLng("0.0");
			sincronizar.setPrecision("0.0");		
			sincronizar.setGps(0);
			sincronizar.setRed(0);
		}
		Util.Log("ini_dia=>"+sincronizar.toString());
		sincronizarDAO.insert2(sincronizar);	
		
	}
	public void add_sincronisacion(String valueOf, String valueOf2, List<Componentesxatributos> _atributos) {
		// TODO Auto-generated method stub
		ComponentesxatributosList componentesxatributosList= new ComponentesxatributosList();
		componentesxatributosList.data=_atributos;		
		String json = gson.toJson(componentesxatributosList, ComponentesxatributosList.class);
		Sincronizar sincronizar = new Sincronizar(valueOf, valueOf2, json); 
		//Util.Log("insert=>"+sincronizarDAO.insert2(sincronizar));		
		add_sincronisacion(sincronizar);
		
	}
	public void add_confirmacion_parcial(Confirmacion conf) {
		// TODO Auto-generated method stub
		String json = gson.toJson(conf, Confirmacion.class);

		
	}
	public void add_confirmacion(Confirmacion conf) {
		// TODO Auto-generated method stub
		String json = gson.toJson(conf, Confirmacion.class);
		Sincronizar sincronizar = new Sincronizar(conf.getNro_ot(), Configuracion.ENVIO_OT_EXTRA, json); 
		//Util.Log("insert=>"+sincronizarDAO.insert2(sincronizar));
		add_sincronisacion(sincronizar);
		
	}
	public void add_confirmacion( Ot_finalizada conf) {
		// TODO Auto-generated method stub
		String json = gson.toJson(conf, Ot_finalizada.class);	
		Sincronizar sincronizar = new Sincronizar(String.valueOf(conf.getOt()), Configuracion.ENVIO_OT, json); 
		//Util.Log("insert=>"+sincronizarDAO.insert2(sincronizar));
		add_sincronisacion(sincronizar) ;
	}
	
	public void add_confirmacion( ) {		
		// TODO Auto-generated method stub
		String json = "";	
		Sincronizar sincronizar = new Sincronizar("", Configuracion.ENVIO_RASTREO, json);		
		add_sincronisacion(sincronizar) ;
	}
	public void add_confirmacion_foto(String s ,String observacion) {		
		// TODO Auto-generated method stub
		String json =  "{\"fotos\": \""+s+"\", \"observacion\":\""+observacion+"\"  }";	;	
		Sincronizar sincronizar = new Sincronizar("", Configuracion.ENVIO_FOTOS, json);		
		add_sincronisacion(sincronizar) ;
	}
	public void add_distancia(String featid ,String tipo,String valor) {		
		// TODO Auto-generated method stub
		String json =  "{\"featid\": \""+featid+"\", \"tipo\":\""+tipo+"\", \"tipo\":\""+valor+"\" }";	;	
		Sincronizar sincronizar = new Sincronizar("", Configuracion.DISTANCIAS, json);		
		add_sincronisacion(sincronizar) ;
	}
	public void add_fin_de_dia() {		
		// TODO Auto-generated method stub
		String json =  "{\"fecha\": \""+Util.getFechaActualFormat()+" "+Util.getHoraActualFormat()+"\" }";	;	
		Sincronizar sincronizar = new Sincronizar("", Configuracion.FIN_DE_DIA, json);		
		add_sincronisacion(sincronizar) ;
	}
	public void add_ini_de_dia() {		
		// TODO Auto-generated method stub
		String json =  "{\"fecha\": \""+Util.getFechaActualFormat()+" "+Util.getHoraActualFormat()+"\" }";	;	
		Sincronizar sincronizar = new Sincronizar("", Configuracion.INICIO_DE_DIA, json);	
		
		add_sincronisacion(sincronizar) ;
	}
	public void add_confirmacion_pantalla( String s) {		
		// TODO Auto-generated method stub
		String json = "{\"pantalla\": \""+s+"\" }";	
		Sincronizar sincronizar = new Sincronizar("", Configuracion.ENVIO_RASTREO, json);		
		add_sincronisacion(sincronizar) ;
	}
	public void add_ot_asociado_featid( String ot,String featid,boolean valor) {
		// TODO Auto-generated method stub
		String json = "{ \"ot_asociado\": \""+ot+"\",\"featid\":\" "+featid+"  \", \"valor\":\" "+String.valueOf(valor)+" \"  }";	
		Sincronizar sincronizar = new Sincronizar("", Configuracion.ENVIO_RASTREO, json);		
		add_sincronisacion(sincronizar) ;
	}
	public void add_confirmacion_distancia( String s,String ot) {		
		// TODO Auto-generated method stub
		String json = "{\"distancia\": \""+s+"\",\"ot\": \""+ot+"\" }";	
		Sincronizar sincronizar = new Sincronizar("", Configuracion.ENVIO_RASTREO, json);		
		add_sincronisacion(sincronizar) ;
	}
/*---------------------------------------------------------------------------*/		
	public static void ini_alarma(Context context,String u) {
		// TODO Auto-generated method stub
		Log.v("alarma", "----------inicio alarma----------");
		
		int min=Configuracion.GET_MIN_INTERVALO(context);
		
		try {
			ConfigWEB config = GET_ULTIMA_CONFIG(context);
			min=config.intervalo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log("Error get_ultima_config:"+e.getMessage());
		}
		Util.Log("Alarma "+Util.getDateNowFormat()+" min->"+min);
		Intent intent = new Intent(context, MyBroadcastReceiver.class);
		intent.putExtra("legajo", "50441");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				context.getApplicationContext(), 234324243, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);	
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),
				min * 60* 1000,pendingIntent);		
	
	}
	
	public List<Sincronizar> get_sin_sincronizar() {
		// TODO Auto-generated method stub		
		//List<Sincronizar> s =sincronizarDAO.get(SincronizarColumns.ENVIADO+ "=?", new String[]{"0"});
		List<Sincronizar> c= new ArrayList<Sincronizar>();
		Sincronizar[] spam = sincronizarDAO.get(SincronizarColumns.ENVIADO+ "=?", new String[]{"0"});
		if(spam!=null){
			c=Arrays.asList(spam);
		}
		return c;
	}
	
	public void sincronizar_set_enviado(Sincronizar conf){
		sincronizarDAO.set_enviado(conf.get_id());
	}
	
	public List<Tipo_componente> get_tipo_componente() {
		// TODO Auto-generated method stub
		List<Tipo_componente> c= new ArrayList<Tipo_componente>();
		Tipo_componente[] spam = tipo_componenteDAO.get("", null);
		if(spam!=null){
			c=Arrays.asList(spam);
		}
		return c;
	}
	
	public List<Archivo> getArchivos() {
		// TODO Auto-generated method stub
		List<Archivo> c= new ArrayList<Archivo>();
		Archivo[] spam = archivoDAO.get("", null);
		if(spam!=null){
			c=Arrays.asList(spam);
		}
		return c;
	}
	public Archivo getArchivo(long _id) {
		// TODO Auto-generated method stub
		try {
			List<Archivo> c= new ArrayList<Archivo>();
			Archivo[] spam = archivoDAO.get("_id", new String[]{String.valueOf(_id)});
			
			return spam[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}


	public void archivos_enviado(Archivo fotos) {
		// TODO Auto-generated method stub
		archivoDAO.set_enviado(fotos.get_id());
	}

	public void componentesxAtributos_actualizar(Componentesxatributos a) {
		// TODO Auto-generated method stub
		componentesxatributosDAO.delete(a);
		componentesxatributosDAO.insert_componentesxatributos(a);
	}

	public static void SET_MODO_SEGURO(Context context,
			String modoSeguroActivo) {
		// TODO Auto-generated method stub
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.MODO_SEGURO, modoSeguroActivo);

	}
	public static boolean GET_MODO_SEGURO_ACTIVADO(Context activity) {
		// TODO Auto-generated method stub
		 String s=Util.SpGet(activity, Configuracion.BIBLIOTECA, Configuracion.MODO_SEGURO, Configuracion.MODO_SEGURO_INACTIVO);
		    
		 return s.equals(Configuracion.MODO_SEGURO_ACTIVO)?true:false;
	     
	        
	}

	public String get_cant_geometrias(long id) {
		// TODO Auto-generated method stub
		
		return String.valueOf(geometriaDAO.cant_total(id));
	}

	public static void SET_ADD_TIPO_FINALIZACION(Context context,
			long id_mot_prox) {
		// TODO Auto-generated method stub
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.TIPO_DE_FINALIZACION, String.valueOf(id_mot_prox));
	}
	public static boolean GET_TIPO_FINALIZACION(Context context){
			
		return (Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.DERIVACIONES_OT_ACTUALES, "")).equals("")?false:true;		
		
	}

	public  void set_borrar_confirmacion(Context context) {
		// TODO Auto-generated method stub
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.DERIVACIONES_OT_ACTUALES, "");
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.MATERIALES_ACTUALES, "");
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.SERVICIOS_ACTUALES, "");
		ot_aperturasDAO.delete();
		
		
	}

	public static void ACTIVAR_BOTON_ASOCIAR(Context a,
			boolean b) {
		// TODO Auto-generated method stub
		Util.SpSet(a, Configuracion.BIBLIOTECA, Configuracion.ACTIVAR_BOTON_ASOCIAR_TRAMO, String.valueOf(b));
		
	}
	public static boolean IsACTIVAR_BOTON_ASOCIAR(Context a) {
		// TODO Auto-generated method stub
		String s=Util.SpGet(a, Configuracion.BIBLIOTECA, Configuracion.ACTIVAR_BOTON_ASOCIAR_TRAMO, "false");
		return s.equals("true");
		
	}
	public boolean  get_en_el_perimetro(Ot ot, Context context, int tipo	){
		/*se valida si la distancia es <0 esta inactivo*/
		if (DatabaseManager.GET_MODO_SEGURO_ACTIVADO(context)){
			return true;
		}
		
		try {
			Util.Log("distancia->LAT=>"+ot.getLat());
			
			Util.Log("distancia->(2)");
			final LatLng latlng= get_pto_actual();
			Util.Log("distancia->(3)");
			LatLng ot_pto= new LatLng(Double.parseDouble(ot.getLat()),Double.parseDouble(ot.getLng()));
			Util.Log("distancia->(4)");
			ConfigWEB conf=DatabaseManager.GET_ULTIMA_CONFIG(context);
			double distancia = Util.getDistanceBetween(ot_pto.latitude, ot_pto.longitude, latlng.latitude, latlng.longitude);
			if(tipo == Configuracion.OT_INICIO_PERIMETRO){
				if(conf.metros_ini<0){
					return true;
				}
			}
			if(tipo == Configuracion.OT_FIN_PERIMETRO){
				if(conf.metros_fin<0){
					return true;
				}
			}
			
			double PERIMETRO = conf.fotos_ini;
			
			if(tipo==Configuracion.OT_FIN_PERIMETRO){				
				PERIMETRO=conf.metros_fin;
			}
			
			Util.Log("distancia=>"+distancia);
			if(distancia<PERIMETRO){
				add_confirmacion_distancia(String.valueOf(distancia),String.valueOf(ot.getNro_ot()));
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log("error=>"+e.getMessage());
			return false;
		}
	}

	public static boolean IS_TOMAR_FOTO_INI(Context context) {
		// TODO Auto-generated method stub
		if (DatabaseManager.GET_MODO_SEGURO_ACTIVADO(context)){
			return true;
		}
		if(Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.FOTO_INI_ECHO, String.valueOf(false)).equals("true")){
			return true;
		}
		ConfigWEB conf=DatabaseManager.GET_ULTIMA_CONFIG(context);
		if(conf.fotos_ini<0){
			return true;
		}
		
		return false;
	}
	public static boolean IS_TOMAR_FOTO_FIN(Context context) {
		// TODO Auto-generated method stub
		if (DatabaseManager.GET_MODO_SEGURO_ACTIVADO(context)){
			return true;
		}
		ConfigWEB conf=DatabaseManager.GET_ULTIMA_CONFIG(context);
		if(conf.fotos_fin<0){
			return false;
		}
		return true;
	}

	public Template get_template(int tEMPLATE_ID) {
		// TODO Auto-generated method stub
		//template =;
		return templateDAO.getTemplate(tEMPLATE_ID);
	}
	public Pasos[] get_pasosxtemplate(long template_id) {
		// TODO Auto-generated method stub
		//template =;
		return pasosDAO.getPasosXTemplate(template_id);
	}

	public boolean IS_PASOS_ACTUALES() {
		// TODO Auto-generated method stub
		String s=Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.PASOS_ACTUALES, "");
		if(s.equals("")){
			return false;
		}
		return true;
	}
	public PasosList getPasosActualesObject() {
		// TODO Auto-generated method stub
		String json=Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.PASOS_ACTUALES, "");
		PasosList pasoslist= gson.fromJson(json, PasosList.class);		
		return pasoslist;
	}
	public void setPasosActualesObject( Pasos[] pasos_actuales){
		
		PasosList pasoslist= new PasosList();
		pasoslist.data=Arrays.asList(pasos_actuales);		
		
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.PASOS_ACTUALES   , gson.toJson(pasoslist,PasosList.class));
		
	}
public void setPasosActualesObject( PasosList pasoslist){
		
	
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.PASOS_ACTUALES   , gson.toJson(pasoslist,PasosList.class));
		
	}

public static int GET_TEMPLATE_ACTUAL(Context context) {
	// TODO Auto-generated method stub
	Util.SpGet(context, Configuracion.BIBLIOTECA, Configuracion.TEMPLATE_ID, 1);
	return 0;
}
public static  void SET_TEMPLATE_ACTUAL(Context context,int TEMPLATE_ID) {
	// TODO Auto-generated method stub
	Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.TEMPLATE_ID, TEMPLATE_ID);

}
}
