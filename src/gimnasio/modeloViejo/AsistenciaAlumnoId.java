package gimnasio.modelo;
// Generated Dec 7, 2018 12:25:40 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * AsistenciaAlumnoId generated by hbm2java
 */
public class AsistenciaAlumnoId  implements java.io.Serializable {


     private int idclasealumno;
     private Date ingreso;

    public AsistenciaAlumnoId() {
    }

    public AsistenciaAlumnoId(int idclasealumno, Date ingreso) {
       this.idclasealumno = idclasealumno;
       this.ingreso = ingreso;
    }
   
    public int getIdclasealumno() {
        return this.idclasealumno;
    }
    
    public void setIdclasealumno(int idclasealumno) {
        this.idclasealumno = idclasealumno;
    }
    public Date getIngreso() {
        return this.ingreso;
    }
    
    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }




}


