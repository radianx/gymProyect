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
     private String estado;
     private Set asistenciaProfesors = new HashSet(0);
     private Set sectorClases = new HashSet(0);
     private Set<HorarioProfesor> horarios= new HashSet(0);
     private Set<ClaseAlumno> claseAlumnos = new HashSet(0);

    public ClaseProfesor() {
    }

	
    public ClaseProfesor(Clase clase, Modalidad modalidad, Profesor profesor,Set horarios, String estado) {
        this.clase = clase;
        this.modalidad = modalidad;
        this.profesor = profesor;
        this.horarios = horarios;
        this.estado = estado;
    }

    public ClaseProfesor(int idclaseprofesor, Clase clase, Modalidad modalidad, Profesor profesor, String estado, Set<HorarioProfesor> horarios) {
        this.idclaseprofesor = idclaseprofesor;
        this.clase = clase;
        this.modalidad = modalidad;
        this.profesor = profesor;
        this.estado = estado;
        this.horarios = horarios;
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

    public Set<HorarioProfesor> getHorarios() {
        return horarios;
    }

    public void setHorarios(Set<HorarioProfesor> horarios) {
        this.horarios = horarios;
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
    public String toString() {
        return clase.toString();
    }
    
}
