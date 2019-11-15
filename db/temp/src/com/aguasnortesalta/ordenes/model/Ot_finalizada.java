package  com.aguasnortesalta.ordenes.model;

import com.aguasnortesalta.ordenes.utils.Util;

public class Ot_finalizada extends Base{

	 
     long id;
     long ot;
	  String fechainicio;
	  String fechafinalizo;
	  int idmotivofinaliza;
	  String lat;
	  String lng;
	  String altura="";
	  String estado="";
	  String t;
	  String fch;
	  String fchalta="";
	  int legajo;
	  int codigo_cuadrilla=0;
	  String observacion="";
	  int id_seg=0;
	  int nro_sec=0;
	  int id_txc=0;
	  int id_inm=0;
	  int nro_form=0;

	public Ot_finalizada() {
		super();

	}
	public static String GET_INI_FECHA_ACTUAL_INI(){
		return Util.getDateNowFormat();// Util.getFechaActualFormat()+" "+ Util.getHoraActualFormat();
	}
	public static String GET_INI_FECHA_ACTUAL_FIN(){
		return  "";
	}
	public static String GET_FIN_FECHA_ACTUAL_FIN(){
		return  Util.getDateNowFormat();
	}
	public Ot_finalizada( int id, int ot, String fechainicio, String fechafinalizo, int idmotivofinaliza, String lat, String lng, String altura, String estado, String t, String fch, String fchalta, int legajo, int codigo_cuadrilla, String observacion, int id_seg, int nro_sec, int id_txc, int id_inm, int nro_form) {
		super();
		this.id = id;
		this.ot = ot;
		this.fechainicio = fechainicio;
		this.fechafinalizo = fechafinalizo;
		this.idmotivofinaliza = idmotivofinaliza;
		this.lat = lat;
		this.lng = lng;
		this.altura = altura;
		this.estado = estado;
		this.t = t;
		this.fch = fch;
		this.fchalta = fchalta;
		this.legajo = legajo;
		this.codigo_cuadrilla = codigo_cuadrilla;
		this.observacion = observacion;
		this.id_seg = id_seg;
		this.nro_sec = nro_sec;
		this.id_txc = id_txc;
		this.id_inm = id_inm;
		this.nro_form = nro_form;

	}
	
            public long getId() {
		        return id;
	        }
	        public void setId(long id) {
                this.id = id;
            }
            public long getOt() {
		        return ot;
	        }
	        public void setOt(long ot) {
                this.ot = ot;
            }
            public String getFechainicio() {
		        return fechainicio;
	        }
	        public void setFechainicio(String fechainicio) {
                this.fechainicio = fechainicio;
            }
            public String getFechafinalizo() {
		        return fechafinalizo;
	        }
	        public void setFechafinalizo(String fechafinalizo) {
                this.fechafinalizo = fechafinalizo;
            }
            public int getIdmotivofinaliza() {
		        return idmotivofinaliza;
	        }
	        public void setIdmotivofinaliza(int idmotivofinaliza) {
                this.idmotivofinaliza = idmotivofinaliza;
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
            public String getAltura() {
		        return altura;
	        }
	        public void setAltura(String altura) {
                this.altura = altura;
            }
            public String getEstado() {
		        return estado;
	        }
	        public void setEstado(String estado) {
                this.estado = estado;
            }
            public String getT() {
		        return t;
	        }
	        public void setT(String t) {
                this.t = t;
            }
            public String getFch() {
		        return fch;
	        }
	        public void setFch(String fch) {
                this.fch = fch;
            }
            public String getFchalta() {
		        return fchalta;
	        }
	        public void setFchalta(String fchalta) {
                this.fchalta = fchalta;
            }
            public int getLegajo() {
		        return legajo;
	        }
	        public void setLegajo(int legajo) {
                this.legajo = legajo;
            }
            public int getCodigo_cuadrilla() {
		        return codigo_cuadrilla;
	        }
	        public void setCodigo_cuadrilla(int codigo_cuadrilla) {
                this.codigo_cuadrilla = codigo_cuadrilla;
            }
            public String getObservacion() {
		        return observacion;
	        }
	        public void setObservacion(String observacion) {
                this.observacion = observacion;
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
	        public String getRepresentacion() {
                return String.valueOf(ot);
            }
			public static int GET_INI_ID_MOTIVO_FINALIZAR() {
				// TODO Auto-generated method stub
				return 0;
			}
			public String[] getStringArray(){
				
				return new String[]{ 
						 "",
						  String.valueOf(id),
					      String.valueOf(ot),
						  fechainicio,
						  fechafinalizo,
						  String.valueOf(idmotivofinaliza),
						  lat,
						  lng,
						  altura,
						  estado,
						  t,
						  fch,
						  fchalta,
						  String.valueOf(legajo),
						  String.valueOf(codigo_cuadrilla),
						  observacion,
						  String.valueOf(id_seg),
						  String.valueOf(nro_sec),
						  String.valueOf(id_txc),
						  String.valueOf(id_inm),
						  String.valueOf(nro_form)};
				
			
			}

}