package gimnasio.modelo;
// Generated 03/12/2018 19:24:10 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Inscripcion generated by hbm2java
 */
public class Inscripcion  implements java.io.Serializable {


     private int idincripcion;
     private Alumno alumno;
     private Date fechainscripcion;
     private Double cuotainicial;

    public Inscripcion() {
    }

	
    public Inscripcion(int idincripcion, Alumno alumno) {
        this.idincripcion = idincripcion;
        this.alumno = alumno;
    }
    public Inscripcion(int idincripcion, Alumno alumno, Date fechainscripcion, Double cuotainicial) {
       this.idincripcion = idincripcion;
       this.alumno = alumno;
       this.fechainscripcion = fechainscripcion;
       this.cuotainicial = cuotainicial;
    }
   
    public int getIdincripcion() {
        return this.idincripcion;
    }
    
    public void setIdincripcion(int idincripcion) {
        this.idincripcion = idincripcion;
    }
    public Alumno getAlumno() {
        return this.alumno;
    }
    
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    public Date getFechainscripcion() {
        return this.fechainscripcion;
    }
    
    public void setFechainscripcion(Date fechainscripcion) {
        this.fechainscripcion = fechainscripcion;
    }
    public Double getCuotainicial() {
        return this.cuotainicial;
    }
    
    public void setCuotainicial(Double cuotainicial) {
        this.cuotainicial = cuotainicial;
    }




}


