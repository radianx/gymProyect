package gimnasio.modelo;
// Generated 03/12/2018 19:24:10 by Hibernate Tools 4.3.1



/**
 * Lectorhuella generated by hbm2java
 */
public class Lectorhuella  implements java.io.Serializable {


     private int idlectohuella;
     private String estadolector;

    public Lectorhuella() {
    }

    public Lectorhuella(int idlectohuella, String estadolector) {
       this.idlectohuella = idlectohuella;
       this.estadolector = estadolector;
    }
   
    public int getIdlectohuella() {
        return this.idlectohuella;
    }
    
    public void setIdlectohuella(int idlectohuella) {
        this.idlectohuella = idlectohuella;
    }
    public String getEstadolector() {
        return this.estadolector;
    }
    
    public void setEstadolector(String estadolector) {
        this.estadolector = estadolector;
    }




}


