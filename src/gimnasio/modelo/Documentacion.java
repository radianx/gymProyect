package modelonuevo;
// Generated Dec 7, 2018 12:25:40 PM by Hibernate Tools 4.3.1



/**
 * Documentacion generated by hbm2java
 */
public class Documentacion  implements java.io.Serializable {


     private int iddocumentacion;
     private byte[] documento;

    public Documentacion() {
    }

	
    public Documentacion(int iddocumentacion) {
        this.iddocumentacion = iddocumentacion;
    }
    public Documentacion(int iddocumentacion, byte[] documento) {
       this.iddocumentacion = iddocumentacion;
       this.documento = documento;
    }
   
    public int getIddocumentacion() {
        return this.iddocumentacion;
    }
    
    public void setIddocumentacion(int iddocumentacion) {
        this.iddocumentacion = iddocumentacion;
    }
    public byte[] getDocumento() {
        return this.documento;
    }
    
    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }




}


