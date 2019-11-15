package  com.aguasnortesalta.ordenes.model;

public class Equipo extends Base{
     int id;
  String descripcion;
  int tipoid;
  String fchalta;
  String geren;

	public Equipo() {
		super();

	}
	public Equipo( int id, String descripcion, int tipoid, String fchalta, String geren) {
		super();
	this.id = id;
this.descripcion = descripcion;
this.tipoid = tipoid;
this.fchalta = fchalta;
this.geren = geren;

	}
	
            public int getId() {
		        return id;
	        }
	        public void setId(int id) {
                this.id = id;
            }
            public String getDescripcion() {
		        return descripcion;
	        }
	        public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
            }
            public int getTipoid() {
		        return tipoid;
	        }
	        public void setTipoid(int tipoid) {
                this.tipoid = tipoid;
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

}