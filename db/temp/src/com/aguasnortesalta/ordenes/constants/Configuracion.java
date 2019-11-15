package com.aguasnortesalta.ordenes.constants;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aguasnortesalta.ordenes.MainActivity;
import com.aguasnortesalta.ordenes.Ot_FinalizarFrag.OnOt_FinalizarClick;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.geometrias.GeometriaFiltro;
import com.aguasnortesalta.ordenes.utils.Util;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class Configuracion {


	public static final String BIBLIOTECA = "biblioteca";

	public static final String TOKEN = "token";

	public static final String USUARIO = "usuario";

	public static final String PASSWOR = "password";

	public static final String OT_ESTADO = "ot_estado";

	public static final String OT_ID_ACTUAL = "ot_id";

	public static final int OPERARIOS_TOTAL_SELECCIONADOS_POSIBLES = 4;
	public static final int RESPUESTA_TOTAL_SELECCIONADOS_POSIBLES = 1;

	public static final String OPERARIOS_ACTUALES = "operarios_actuales";

	public static final String EQUIPOS_ACTUALES = "equipos_actuales";

	public static final String SERVICIOS_ACTUALES = "servicios_actuales";

	public static final String MATERIALES_ACTUALES = "materiales";

	public static final String ULTIMA_FINALIZACION = "ultima_finalizacion";

	public static final String DERIVACIONES_OT_ACTUALES = "derivaciones_actuales";

	public static final String LAT_ULTIMA = "LAT";

	public static final String LNG_ULTIMA = "LNG";

	public static final String FEATID_ULTIMO = "FEATID";

	public static final String ENVIO_OT_EXTRA = "OT_EXTRA";
	public static final String ENVIO_OT= "OT";
	public static final String ENVIO_FEITID= "FEATID";
	public static final String ENVIO_RASTREO = "R";
	public static final String APLICACION = "ordenes";
	public static final String ENVIO_FOTOS = "FOTOS";
	
	public static final double LAT_DEFAULT = -24.8465554209979;
	public static final double LNG_DEFAULT = -65.4637724567982;

	public static final String ULTIMA_PANTALLA = "ultima_pantalla";

	public static final String PENULTIMA_ULTIMA_PANTALLA = "penultima_pantalla";

	public static final double GET_PERIMETRO = 100;
	
	public static final double GET_PERIMETRO_FINALIZACION = 7;	
	
	public static String OT_ACTUAL = "ot_actual";
	
	public static String OT_NRO_ACTUAL = "ot_actual_nro";

	public static boolean produccion=true;
	
	public static final String  URL_EXPORT = "http://ot.aguasdelnortesalta.com.ar/img/index.php";
	
	public static final String  server_produccion="http://ot.aguasdelnortesalta.com.ar/";
	//public static final String  server_produccion="http://sd-1578096-h00001.ferozo.net/prueba/";
		
	public static final String  server_prueba="http://192.168.111.80/ot/";
	
	public static final String  server=server_produccion;
	
/*------------------------------------------------------------*/
	public static final String PAQUETE="com.aguasnortesalta.ordenes";
	//autorizacion---------
	public static final String  service_auth="app/api/v2/web/auth";
	//usuario---------compara la base actual con la de la nube
	public static final String  service_usuario="app/api/v2/web/usuario";
	//envia las ot actuales----------------------------------
	public static final String  service_ot="app/api/v2/web/ot";	
	//envia la sincronizacion actual-------------------------
	public static final String  service_sincronizar="app/api/v2/web/sincronizar";
	//envia la base de datos actual--------------------------
	public static final String  service_donwload ="app/export/ordenes.zip";
	public static final String service_donwload_file="ordenes.zip";
	//envia configuracion------------------------------------
	private static final String service_configuracion = "app/api/v2/web/configuracion";		//----------------------------------
	
	/*public static final String  service_confirmar="app/api/v2/web/otextra";*/

	public static final String MODO_SEGURO = "modo_seguro";		
	public static final String MODO_SEGURO_ACTIVO = "1";	
	public static final String MODO_SEGURO_INACTIVO = "0";

	public static final String TIPO_DE_FINALIZACION = "TIPO_DE_FINALIZACION";

	public static final String GEREN = "GEREN";

	public static final String OT_SELECCIONADA = "OT-SELECCIOANADA";

	public static final String ACTIVAR_BOTON_ASOCIAR_TRAMO = "BOTON_ASOCIADO";

	public static final String CONFIGURACION_WEB = "configuracion_web";

	public static final int OT_INICIO_PERIMETRO = 1;

	public static final int OT_FIN_PERIMETRO = 2;

	public static final String FOTO_INI_ECHO = "false";

	private static final String MIN_INTERVALO = "min_intervalo";

	public static final String OT_FOTO_FINAL = "OT_FOTO_FINAL";
	public static final boolean OT_FOTO_FINAL_ESPERA = false;
	public static final boolean OT_FOTO_FINAL_OK = true;

	public static final String USUARIO_ANTERIOR = "usuario_anterior";

	public static final String ACTUALIZACION_OBLIGATORIA = "ACTUALIZACION_OBLIGATORIA";

	public static final String DISTANCIAS = "DISTANCIA";

	public static final String FIN_DE_DIA = "FIN_DE_DIA";
	
	public static final String INICIO_DE_DIA = "INICIO_DE_DIA";

	public static final String SESSION = "SESSION";

	public static final String SESSION_FECHA = "SESSION_FECHA";

	public static final String PASOS_ACTUALES = "PASOS_ACTUALES";

	public static final String TEMPLATE_ID = "TEMPLATE_ID";

	public static final String ULTIMO_ERROR = "ULTIMO_ERROR";
		
	public static GeometriaFiltro[] FILTROS_GEOMETRIAS = new  GeometriaFiltro[]{new GeometriaFiltro(1,"Tramos") };

	public static String getAuthorizationLoguin() {
		// TODO Auto-generated method stub		
		return server+service_auth;
		
	}

	public static String getToken(Context activity) {
		// TODO Auto-generated method stub
		return Util.SpGet(activity, BIBLIOTECA, TOKEN,"");
		}

	
	public static String get_Url_Usuarios() {
		// TODO Auto-generated method stub
		return server+service_usuario;
	}
	
	public static String get_Url_Usuarios(String usuario) {
		// TODO Auto-generated method stub
		return server+service_usuario+"/usuario/"+usuario;
	}
	public static String get_Url_Editor() {
		// TODO Auto-generated method stub
		return "";
	}	
	
	public static String get_Url_Ot(String s) {
		// TODO Auto-generated method stub
		if(s.startsWith("usuario")){
			return server+service_ot;	
		}else{
			return server+service_ot+"/legajo/"+s;
		}
		
	}

	public static boolean OT_isExists(Activity a) {
		// TODO Auto-generated method stub		
		String s=Util.SpGet(a, BIBLIOTECA, Configuracion.OT_ACTUAL);
		return (s.length()>0)?true:false;	
	}

	public static void Ot_actual_finalizar(Context context) {
		// TODO Auto-generated method stub
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.OT_ESTADO, "");
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.OT_NRO_ACTUAL, "");
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.OT_ID_ACTUAL,"");
		Util.SpSet(context, Configuracion.BIBLIOTECA, Configuracion.OT_ACTUAL, "");		
		
		
	}
	
	public static String[] GET_APERTURA_ESTADO(){
		
		return new String[] {"TAPADA","SEMI-TAPADA", "ABIERTA"};
	}
	
	public static String[] GET_APERTURA_SENIALIZACION(){
		
		return new String[] {"NO","SI"};
	}
	public static String[] GET_TIPO_CALZADA(){
		
		return new String[] {"Calzada","Vereda"};
	}

	public static String get_usuario_actual(Context context) {
		// TODO Auto-generated method stub
		return Util.SpGet(context, BIBLIOTECA, USUARIO, "");
	}
/*
	public static String get_Url_Confirmar() {
		// TODO Auto-generated method stub
		//"http://192.168.111.80/ot/app/api/v2/web/otextra";
	
		return server+service_confirmar;
	}
	*/
	
	public static String get_Url_Post() {
		// TODO Auto-generated method stub
	
		return server+service_sincronizar;
		
	}
	public static String get_Url_config_db(String usuario) {
		// TODO Auto-generated method stub
	
		return server+service_configuracion +"/usuario/"+usuario;
		
	}
	public static File extractLogToFileAndWeb(Context context){
        //set a file
        Date datum = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String fullName = df.format(datum)+"appLog.log";
        File file = new File (Environment.getExternalStorageDirectory(), fullName);

        //clears a file
        if(file.exists()){
            file.delete();
        }


        //write log to file
        int pid = android.os.Process.myPid();
        try {
            String command = String.format("logcat -d -v threadtime *:*");        
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String currentLine = null;

            while ((currentLine = reader.readLine()) != null) {
                   if (currentLine != null && currentLine.contains(String.valueOf(pid))) {
                       result.append(currentLine);
                       result.append("\n");
                    }
            }
            FileWriter out = new FileWriter(file);
            out.write(result.toString());
            out.close();
            //Runtime.getRuntime().exec("logcat -d -v time -f "+file.getAbsolutePath());
        } catch (IOException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
        //clear the log
        try {
            Runtime.getRuntime().exec("logcat -c");
        } catch (IOException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }

        return file;
    }
	public static String get_distancia(LatLng a,LatLng b){
		DecimalFormat df = new DecimalFormat("0.00");
		double x= Util.getDistanceBetween(a.latitude, a.longitude, b.latitude, b.longitude);
		if(x>1000){
			return df.format(x/1000) +" km";
		}else{
			return  df.format(x) +" metros";
		}
	}

	public static int GET_MIN_INTERVALO(Context context) {
		// TODO Auto-generated method stub
		return Util.SpGet(context,Configuracion.BIBLIOTECA, Configuracion.MIN_INTERVALO, 5);
		
	}
	
	


}