package  com.aguasnortesalta.ordenes.model;

public class Operario extends Base{
     int id;
  String nombre;
  String fchalta;
  String geren;
  int cuadrilla;
  int capataz;
  int password;

	public Operario() {
		super();

	}
	public Operario( int id, String nombre, String fchalta, String geren, int cuadrilla, int capataz, int password) {
		super();
	this.id = id;
this.nombre = nombre;
this.fchalta = fchalta;
this.geren = geren;
this.cuadrilla = cuadrilla;
this.capataz = capataz;
this.password = password;

	}
	
            public int getId() {
		        return id;
	        }
	        public void setId(int id) {
                this.id = id;
            }
            public String getNombre() {
		        return nombre;
	        }
	        public void setNombre(String nombre) {
                this.nombre = nombre;
            }
            public String getFchalta() {
		        return fchalta;
	        }
	        public void setFchalta(String fchalta) {
                this.fchalta = fchalta;
            }
            public String getGeren() {
		        return geren;
	        }
	        public void setGeren(String geren) {
                this.geren = geren;
            }
            public int getCuadrilla() {
		        return cuadrilla;
	        }
	        public void setCuadrilla(int cuadrilla) {
                this.cuadrilla = cuadrilla;
            }
            public int getCapataz() {
		        return capataz;
	        }
	        public void setCapataz(int capataz) {
                this.capataz = capataz;
            }
            public int getPassword() {
		        return password;
	        }
	        public void setPassword(int password) {
                this.password = password;
            }
			@Override
			public String toString() {
				return nombre;
			}
	        

}