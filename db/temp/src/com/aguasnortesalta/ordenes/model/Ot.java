package  com.aguasnortesalta.ordenes.model;

import java.lang.reflect.Field;

import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.utils.Util;
import com.google.android.gms.maps.model.LatLng;

public class Ot extends Base{
  private int id;
  private int nro_ot;
  private int id_loc;
  private int id_bar;
  private String barrio;
  private int id_cal;
  private String calle;
  private String altura;
  private int id_motivo;
  private String motivo;
  private int cod_empleado_asig;
  private String nombre_empleado_asig;
  private int cod_cuadrilla_asig;
  private String fchalta;
  private String lat;
  private String lng;
  private int prioridad;
  private int id_seg;
  private int nro_sec;
  private int id_txc;
  private int id_inm;
  private int nro_form;
  private String fchregistracion;
  private String observacion;
  private String geren;

	public Ot() {
		super();

	}
	
	public Ot( int id, int nro_ot, int id_loc, int id_bar, String barrio, int id_cal, String calle, String altura, int id_motivo, String motivo, int cod_empleado_asig, String nombre_empleado_asig, int cod_cuadrilla_asig, String fchalta, String lat, String lng, int prioridad, int id_seg, int nro_sec, int id_txc, int id_inm, int nro_form, String fchregistracion, String observacion, String geren) {
		super();
	this.id = id;
	this.nro_ot = nro_ot;
	this.id_loc = id_loc;
	this.id_bar = id_bar;
	this.barrio = barrio;
	this.id_cal = id_cal;
	this.calle = calle;
	this.altura = altura;
	this.id_motivo = id_motivo;
	this.motivo = motivo;
	this.cod_empleado_asig = cod_empleado_asig;
	this.nombre_empleado_asig = nombre_empleado_asig;
	this.cod_cuadrilla_asig = cod_cuadrilla_asig;
	this.fchalta = fchalta;
	this.lat = lat;
	this.lng = lng;
	this.prioridad = prioridad;
	this.id_seg = id_seg;
	this.nro_sec = nro_sec;
	this.id_txc = id_txc;
	this.id_inm = id_inm;
	this.nro_form = nro_form;
	this.fchregistracion = fchregistracion;
	this.observacion = observacion;
	this.geren = geren;

	}
	
	
            public int getId() {
		        return id;
	        }
	        public void setId(int id) {
                this.id = id;
            }
            public int getNro_ot() {
		        return nro_ot;
	        }
	        public void setNro_ot(int nro_ot) {
                this.nro_ot = nro_ot;
            }
            public int getId_loc() {
		        return id_loc;
	        }
	        public void setId_loc(int id_loc) {
                this.id_loc = id_loc;
            }
            public int getId_bar() {
		        return id_bar;
	        }
	        public void setId_bar(int id_bar) {
                this.id_bar = id_bar;
            }
            public String getBarrio() {
		        return barrio;
	        }
	        public void setBarrio(String barrio) {
                this.barrio = barrio;
            }
            public int getId_cal() {
		        return id_cal;
	        }
	        public void setId_cal(int id_cal) {
                this.id_cal = id_cal;
            }
            public String getCalle() {
		        return calle;
	        }
	        public void setCalle(String calle) {
                this.calle = calle;
            }
            public String getAltura() {
		        return altura;
	        }
	        public void setAltura(String altura) {
                this.altura = altura;
            }
            public int getId_motivo() {
		        return id_motivo;
	        }
	        public void setId_motivo(int id_motivo) {
                this.id_motivo = id_motivo;
            }
            public String getMotivo() {
		        return motivo;
	        }
	        public void setMotivo(String motivo) {
                this.motivo = motivo;
            }
            public int getCod_empleado_asig() {
		        return cod_empleado_asig;
	        }
	        public void setCod_empleado_asig(int cod_empleado_asig) {
                this.cod_empleado_asig = cod_empleado_asig;
            }
            public String getNombre_empleado_asig() {
		        return nombre_empleado_asig;
	        }
	        public void setNombre_empleado_asig(String nombre_empleado_asig) {
                this.nombre_empleado_asig = nombre_empleado_asig;
            }
            public int getCod_cuadrilla_asig() {
		        return cod_cuadrilla_asig;
	        }
	        public void setCod_cuadrilla_asig(int cod_cuadrilla_asig) {
                this.cod_cuadrilla_asig = cod_cuadrilla_asig;
            }
            public String getFchalta() { 	 
            	  return (fchalta==null)?Util.getFechaActualFormat()+Util.getHoraActualFormat():fchalta;
	        }
	        public void setFchalta(String fchalta) {
                this.fchalta = fchalta;
            }
            public String getLat() {
		        return lat;
	        }
	        public void setLat(String lat) {
                this.lat = lat;
            }
            public String getLng() {
		        return lng;
	        }
	        public void setLng(String lng) {
                this.lng = lng;
            }
            public int getPrioridad() {
		        return prioridad;
	        }
	        public void setPrioridad(int prioridad) {
                this.prioridad = prioridad;
            }
            public int getId_seg() {
		        return id_seg;
	        }
	        public void setId_seg(int id_seg) {
                this.id_seg = id_seg;
            }
            public int getNro_sec() {
		        return nro_sec;
	        }
	        public void setNro_sec(int nro_sec) {
                this.nro_sec = nro_sec;
            }
            public int getId_txc() {
		        return id_txc;
	        }
	        public void setId_txc(int id_txc) {
                this.id_txc = id_txc;
            }
            public int getId_inm() {
		        return id_inm;
	        }
	        public void setId_inm(int id_inm) {
                this.id_inm = id_inm;
            }
            public int getNro_form() {
		        return nro_form;
	        }
	        public void setNro_form(int nro_form) {
                this.nro_form = nro_form;
            }
            public String getFchregistracion() {
		        return fchregistracion;
	        }
	        public void setFchregistracion(String fchregistracion) {
                this.fchregistracion = fchregistracion;
            }
            public String getObservacion() {
		        return observacion;
	        }
	        public void setObservacion(String observacion) {
                this.observacion = observacion;
            }
            public String getGeren() {
		        return geren;
	        }
	        public void setGeren(String geren) {
                this.geren = geren;
            }
			public String getTitulo() {
				// TODO Auto-generated method stub
				return String.valueOf(nro_ot);
			}
			public CharSequence getSubTitulo() {
				// TODO Auto-generated method stub
			
				return  calle+ " n°"+altura+ ", "+barrio ;
				
				
			}
			public String  getContent() {
				// TODO Auto-generated method stub
			
				return  "OT:"+nro_ot +"," +calle+ " n°"+altura+ ", "+barrio ;
				
				
			}
			@Override
			public String toString() {
				return "Ot [nro_ot=" + nro_ot + ", id_loc=" + id_loc
						+ ", id_bar=" + id_bar + ", barrio=" + barrio
						+ ", id_cal=" + id_cal + ", calle=" + calle
						+ ", altura=" + altura + ", id_motivo=" + id_motivo
						+ ", motivo=" + motivo + ", cod_empleado_asig="
						+ cod_empleado_asig + ", nombre_empleado_asig="
						+ nombre_empleado_asig + ", cod_cuadrilla_asig="
						+ cod_cuadrilla_asig + ", fchalta=" + fchalta
						+ ", lat=" + lat + ", lng=" + lng + ", prioridad="
						+ prioridad + ", id_seg=" + id_seg + ", nro_sec="
						+ nro_sec + ", id_txc=" + id_txc + ", id_inm=" + id_inm
						+ ", nro_form=" + nro_form + ", fchregistracion="
						+ fchregistracion + ", observacion=" + observacion
						+ ", geren=" + geren + "]";
			}
			public String getStringPrecentacionReducido() {
				String  htmlcolor="";//"<font color=\"#c5c5c5\">";
				String  htmlcolor_close="</font>";
				return "<h3>OT:"+getNro_ot()+"</h3>"
						+"<br><b>Calle:</b>"+htmlcolor+getCalle()+htmlcolor_close+"</br>"
						+"<br><b>N°:</b>"+htmlcolor+getAltura()+htmlcolor_close+"</br>";
				
			}
			public String getStringPrecentacion() {
				// TODO Auto-generated method stub
				String  htmlcolor="";
				String  htmlcolor_close="</font>";
					return "<h3>OT:"+getNro_ot()+"</h3>"
							+"<b>Lat:</b>"+htmlcolor+getLat()+"," +getLng() +htmlcolor_close+"</br>"	
							+"<b>Motivo:</b>"+htmlcolor+getMotivo()+htmlcolor_close+"</br>"					
							+"<br><b>Barrio:</b>"+htmlcolor+getBarrio()+htmlcolor_close+"</br>"
							+"<br><b>Calle:</b>"+htmlcolor+getCalle()+htmlcolor_close+"</br>"
							+"<br><b>N°:</b>"+htmlcolor+getAltura()+htmlcolor_close+"</br>"
							+"<br><b>Observacion:</b>"+htmlcolor+getObservacion()+htmlcolor_close+"</br>";
					
		
				 
			}
			public String getStringPrecentacion(LatLng pto) {
				// TODO Auto-generated method stub
				String  htmlcolor="";
				LatLng ot_pto= new LatLng(Double.parseDouble(getLat()),Double.parseDouble(getLng()));
				String  htmlcolor_close="</font>";
					return "<h3>OT:"+getNro_ot()+"</h3>"
							+"<b>A "+htmlcolor+ Configuracion.get_distancia(pto, ot_pto) +htmlcolor_close+" del lugar </b></br>"	
							+"<b>Motivo:</b>"+htmlcolor+getMotivo()+htmlcolor_close+"</br>"					
							+"<br><b>Barrio:</b>"+htmlcolor+getBarrio()+htmlcolor_close+"</br>"
							+"<br><b>Calle:</b>"+htmlcolor+getCalle()+htmlcolor_close+"</br>"
							+"<br><b>N°:</b>"+htmlcolor+getAltura()+htmlcolor_close+"</br>"
							+"<br><b>Observacion:</b>"+htmlcolor+getObservacion()+htmlcolor_close+"</br>";
					
		
				 
			}
			public String[] getStringArray(){
			
				return new String[]{
								"0",
							   String.valueOf(id),
							  String.valueOf(nro_ot),
							  String.valueOf(id_loc),
							  String.valueOf(id_bar),
							  barrio,
							  String.valueOf(id_cal),
							  calle,
							  altura,
							  String.valueOf(id_motivo),
							  motivo,
							  String.valueOf(cod_empleado_asig),
							  nombre_empleado_asig,
							  String.valueOf(cod_cuadrilla_asig),
							  getFchalta(),
							  lat,
							  lng,
							  String.valueOf(prioridad),
							  String.valueOf(id_seg),
							  String.valueOf(nro_sec),
							  String.valueOf(id_txc),
							  String.valueOf(id_inm),
							  String.valueOf(nro_form),
							  fchregistracion,
							  observacion,
							  geren
						
				};
				
			}


}