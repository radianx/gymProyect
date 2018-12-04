package gimnasio.modelo;
// Generated 03/12/2018 19:24:10 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * AsistenciaProfesor generated by hbm2java
 */
public class AsistenciaProfesor  implements java.io.Serializable {


     private AsistenciaProfesorId id;
     private Clase clase;
     private Date salida;

    public AsistenciaProfesor() {
    }

	
    public AsistenciaProfesor(AsistenciaProfesorId id, Clase clase) {
        this.id = id;
        this.clase = clase;
    }
    public AsistenciaProfesor(AsistenciaProfesorId id, Clase clase, Date salida) {
       this.id = id;
       this.clase = clase;
       this.salida = salida;
    }
   
    public AsistenciaProfesorId getId() {
        return this.id;
    }
    
    public void setId(AsistenciaProfesorId id) {
        this.id = id;
    }
    public Clase getClase() {
        return this.clase;
    }
    
    public void setClase(Clase clase) {
        this.clase = clase;
    }
    public Date getSalida() {
        return this.salida;
    }
    
    public void setSalida(Date salida) {
        this.salida = salida;
    }




}

