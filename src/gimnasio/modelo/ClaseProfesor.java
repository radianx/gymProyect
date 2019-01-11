package gimnasio.modelo;
// Generated Dec 8, 2018 5:14:35 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * ClaseProfesor generated by hbm2java rearranged by Osoft
 */
public class ClaseProfesor  implements java.io.Serializable {

     private int idclaseprofesor;
     private Clase clase;
     private Modalidad modalidad;
     private Profesor profesor;
     private Date inicio;
     private Date fin;
     private String estado;
     private Set asistenciaProfesors = new HashSet(0);
     private Set sectorClases = new HashSet(0);
     private Set<ClaseAlumno> claseAlumnos = new HashSet(0);

    public ClaseProfesor() {
    }

	
    public ClaseProfesor(Clase clase, Modalidad modalidad, Profesor profesor, Date inicio, Date fin, String estado) {
        this.clase = clase;
        this.modalidad = modalidad;
        this.profesor = profesor;
        this.inicio = inicio;
        this.fin = fin;
        this.estado = estado;
    }
    public ClaseProfesor(Clase clase, Modalidad modalidad, Profesor profesor, Date inicio, Date fin, String estado, Set asistenciaProfesors, Set sectorClases, Set claseAlumnos) {
       this.clase = clase;
       this.modalidad = modalidad;
       this.profesor = profesor;
       this.inicio = inicio;
       this.fin = fin;
       this.estado = estado;
       this.asistenciaProfesors = asistenciaProfesors;
       this.sectorClases = sectorClases;
       this.claseAlumnos = claseAlumnos;
    }
   
    public int getIdclaseprofesor() {
        return this.idclaseprofesor;
    }
    
    public void setIdclaseprofesor(int idclaseprofesor) {
        this.idclaseprofesor = idclaseprofesor;
    }
    public Clase getClase() {
        return this.clase;
    }
    
    public void setClase(Clase clase) {
        this.clase = clase;
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
    public Date getInicio() {
        return this.inicio;
    }
    
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }
    public Date getFin() {
        return this.fin;
    }
    
    public void setFin(Date fin) {
        this.fin = fin;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set getAsistenciaProfesors() {
        return this.asistenciaProfesors;
    }
    
    public void setAsistenciaProfesors(Set asistenciaProfesors) {
        this.asistenciaProfesors = asistenciaProfesors;
    }
    public Set getSectorClases() {
        return this.sectorClases;
    }
    
    public void setSectorClases(Set sectorClases) {
        this.sectorClases = sectorClases;
    }
    public Set<ClaseAlumno> getClaseAlumnos() {
        return this.claseAlumnos;
    }
    
    public void setClaseAlumnos(Set claseAlumnos) {
        this.claseAlumnos = claseAlumnos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.idclaseprofesor;
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
        final ClaseProfesor other = (ClaseProfesor) obj;
        if (this.idclaseprofesor != other.idclaseprofesor) {
            return false;
        }
        if (!Objects.equals(this.clase, other.clase)) {
            return false;
        }
        if (!Objects.equals(this.modalidad, other.modalidad)) {
            return false;
        }
        if (!Objects.equals(this.profesor, other.profesor)) {
            return false;
        }
        return true;
    }






}


