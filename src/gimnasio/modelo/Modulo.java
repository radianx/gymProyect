package gimnasio.modelo;
// Generated Dec 8, 2018 5:14:35 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Modulo generated by hbm2java
 */
public class Modulo  implements java.io.Serializable {


     private int idmodulo;
     private Sistema sistema;
     private String nombremodulo;
     private String estado;
     private Set usuarioModulos = new HashSet(0);

    public Modulo() {
    }

	
    public Modulo(int idmodulo, Sistema sistema, String nombremodulo, String estado) {
        this.idmodulo = idmodulo;
        this.sistema = sistema;
        this.nombremodulo = nombremodulo;
        this.estado = estado;
    }
    public Modulo(int idmodulo, Sistema sistema, String nombremodulo, String estado, Set usuarioModulos) {
       this.idmodulo = idmodulo;
       this.sistema = sistema;
       this.nombremodulo = nombremodulo;
       this.estado = estado;
       this.usuarioModulos = usuarioModulos;
    }
   
    public int getIdmodulo() {
        return this.idmodulo;
    }
    
    public void setIdmodulo(int idmodulo) {
        this.idmodulo = idmodulo;
    }
    public Sistema getSistema() {
        return this.sistema;
    }
    
    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }
    public String getNombremodulo() {
        return this.nombremodulo;
    }
    
    public void setNombremodulo(String nombremodulo) {
        this.nombremodulo = nombremodulo;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set getUsuarioModulos() {
        return this.usuarioModulos;
    }
    
    public void setUsuarioModulos(Set usuarioModulos) {
        this.usuarioModulos = usuarioModulos;
    }

    @Override
    public String toString() {
        return nombremodulo;
    }



}


