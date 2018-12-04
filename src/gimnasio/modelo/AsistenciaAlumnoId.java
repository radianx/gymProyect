package gimnasio.modelo;
// Generated 03/12/2018 19:24:10 by Hibernate Tools 4.3.1


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


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AsistenciaAlumnoId) ) return false;
		 AsistenciaAlumnoId castOther = ( AsistenciaAlumnoId ) other; 
         
		 return (this.getIdclasealumno()==castOther.getIdclasealumno())
 && ( (this.getIngreso()==castOther.getIngreso()) || ( this.getIngreso()!=null && castOther.getIngreso()!=null && this.getIngreso().equals(castOther.getIngreso()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdclasealumno();
         result = 37 * result + ( getIngreso() == null ? 0 : this.getIngreso().hashCode() );
         return result;
   }   


}

