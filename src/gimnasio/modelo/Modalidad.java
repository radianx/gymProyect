package gimnasio.modelo;
// Generated 03/12/2018 19:24:10 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Modalidad generated by hbm2java
 */
public class Modalidad  implements java.io.Serializable {


     private int idmodalidad;
     private String nombremodalidad;
     private String descripcionmodalidad;
     private String estado;
     private Set profesormodalidads = new HashSet(0);

    public Modalidad() {
    }

	
    public Modalidad(String nombremodalidad, String estado) {
        this.nombremodalidad = nombremodalidad;
        this.estado= estado;
    }
    public Modalidad(String nombremodalidad, String descripcionmodalidad, String estado) {
       this.nombremodalidad = nombremodalidad;
       this.descripcionmodalidad = descripcionmodalidad;
       this.estado = estado;
    }
   
    public int getIdmodalidad() {
        return this.idmodalidad;
    }
    
    public void setIdmodalidad(int idmodalidad) {
        this.idmodalidad = idmodalidad;
    }
    public String getNombremodalidad() {
        return this.nombremodalidad;
    }
    
    public void setNombremodalidad(String nombremodalidad) {
        this.nombremodalidad = nombremodalidad;
    }
    public String getDescripcionmodalidad() {
        return this.descripcionmodalidad;
    }
    
    public void setDescripcionmodalidad(String descripcionmodalidad) {
        this.descripcionmodalidad = descripcionmodalidad;
    }
    public Set getProfesormodalidads() {
        return this.profesormodalidads;
    }
    
    public void setProfesormodalidads(Set profesormodalidads) {
        this.profesormodalidads = profesormodalidads;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}


