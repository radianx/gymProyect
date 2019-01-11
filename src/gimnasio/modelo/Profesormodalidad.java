package gimnasio.modelo;
// Generated 03/12/2018 19:24:10 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Profesormodalidad generated by hbm2java
 */
public class Profesormodalidad  implements java.io.Serializable {


     private int idprofesormodalidad;
     private Modalidad modalidad;
     private Profesor profesor;
     private Double preciohora;
     private String estado;
     private Set clases = new HashSet(0);

    public Profesormodalidad() {
    }

	
    
    public Profesormodalidad(Profesor profesor, Modalidad modalidad, Double preciohora, String estado) {
       this.modalidad = modalidad;
       this.profesor = profesor;
       this.preciohora = preciohora;
       this.estado = estado;
    }
   
    public int getIdprofesormodalidad() {
        return this.idprofesormodalidad;
    }
    
    public void setIdprofesormodalidad(int idprofesormodalidad) {
        this.idprofesormodalidad = idprofesormodalidad;
    }
    public Modalidad getModalidad() {
        return this.modalidad;
    }
    
    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }
    public Profesor getProfesor() {
        return this.profesor;
    }
    
    
    
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    public Double getPreciohora() {
        return this.preciohora;
    }
    
    public void setPreciohora(Double preciohora) {
        this.preciohora = preciohora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    public Set getClases() {
        return this.clases;
    }
    
    public void setClases(Set clases) {
        this.clases = clases;
    }

    @Override
    public String toString() {
        return  this.modalidad.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.idprofesormodalidad;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profesormodalidad other = (Profesormodalidad) obj;
        if (!Objects.equals(this.modalidad, other.modalidad)) {
            return false;
        }
        if (!Objects.equals(this.profesor, other.profesor)) {
            return false;
        }
        if (!Objects.equals(this.preciohora, other.preciohora)) {
            return false;
        }
        return true;
    }



    
}


