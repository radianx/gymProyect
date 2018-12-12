package gimnasio.modelo;
// Generated Dec 8, 2018 5:14:35 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * ClaseAlumno generated by hbm2java
 */
public class ClaseAlumno  implements java.io.Serializable {


     private int idclasealumno;
     private Alumno alumno;
     private ClaseProfesor claseProfesor;
     private String estado;
     private Set asistenciaAlumnos = new HashSet(0);

    public ClaseAlumno() {
    }

	
    public ClaseAlumno(Alumno alumno, ClaseProfesor claseProfesor, String estado) {
        this.alumno = alumno;
        this.claseProfesor = claseProfesor;
        this.estado = estado;
    }
    public ClaseAlumno(int idclasealumno, Alumno alumno, ClaseProfesor claseProfesor, String estado, Set asistenciaAlumnos) {
       this.idclasealumno = idclasealumno;
       this.alumno = alumno;
       this.claseProfesor = claseProfesor;
       this.estado = estado;
       this.asistenciaAlumnos = asistenciaAlumnos;
    }
   
    public int getIdclasealumno() {
        return this.idclasealumno;
    }
    
    public void setIdclasealumno(int idclasealumno) {
        this.idclasealumno = idclasealumno;
    }
    public Alumno getAlumno() {
        return this.alumno;
    }
    
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    public ClaseProfesor getClaseProfesor() {
        return this.claseProfesor;
    }
    
    public void setClaseProfesor(ClaseProfesor claseProfesor) {
        this.claseProfesor = claseProfesor;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set getAsistenciaAlumnos() {
        return this.asistenciaAlumnos;
    }
    
    public void setAsistenciaAlumnos(Set asistenciaAlumnos) {
        this.asistenciaAlumnos = asistenciaAlumnos;
    }

    @Override
    public String toString() {
        return claseProfesor.getClase().getTipoclase();
    }

    

}


