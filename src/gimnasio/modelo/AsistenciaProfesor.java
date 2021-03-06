package gimnasio.modelo;
// Generated Dec 8, 2018 5:14:35 PM by Hibernate Tools 4.3.1


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * AsistenciaProfesor generated by hbm2java
 */
public class AsistenciaProfesor  implements java.io.Serializable, Comparable {

     private int idAsistenciaProfesor;
     private ClaseProfesor claseProfesor;
     private Date ingreso;
     private Date salida;
     private String estado;

    public AsistenciaProfesor() {
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public AsistenciaProfesor(ClaseProfesor claseProfesor, Date ingreso, String estado) {
        this.claseProfesor = claseProfesor;
        this.claseProfesor.setIdclaseprofesor(claseProfesor.getIdclaseprofesor());
        this.ingreso = ingreso;
        this.estado = estado;
    }
	
    public AsistenciaProfesor(int idAsistenciaProfesor, ClaseProfesor claseProfesor, String estado) {
        this.idAsistenciaProfesor = idAsistenciaProfesor;
        this.claseProfesor = claseProfesor;
        this.claseProfesor.setIdclaseprofesor(claseProfesor.getIdclaseprofesor());
        this.estado = estado;
    }
    public AsistenciaProfesor(int idAsistenciaProfesor, ClaseProfesor claseProfesor, Date salida, String estado) {
       this.idAsistenciaProfesor = idAsistenciaProfesor;
       this.claseProfesor = claseProfesor;
       this.claseProfesor.setIdclaseprofesor(claseProfesor.getIdclaseprofesor());
       this.salida = salida;
       this.estado = estado;
    }

    public int getIdAsistenciaProfesor() {
        return idAsistenciaProfesor;
    }

    public void setIdAsistenciaProfesor(int idAsistenciaProfesor) {
        this.idAsistenciaProfesor = idAsistenciaProfesor;
    }
    
    public ClaseProfesor getClaseProfesor() {
        return this.claseProfesor;
    }
    
    public void setClaseProfesor(ClaseProfesor claseProfesor) {
        this.claseProfesor = claseProfesor;
    }
    public Date getSalida() {
        return this.salida;
    }
    
    public void setSalida(Date salida) {
        this.salida = salida;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(this.ingreso);
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.idAsistenciaProfesor;
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
        final AsistenciaProfesor other = (AsistenciaProfesor) obj;
        if (!Objects.equals(this.claseProfesor, other.claseProfesor)) {
            return false;
        }
        if (!Objects.equals(this.ingreso, other.ingreso)) {
            return false;
        }
        
        LocalDateTime fechaYhoraThis = LocalDateTime.ofInstant(this.ingreso.toInstant(), ZoneId.systemDefault());
        LocalDateTime fechaYhoraOther = LocalDateTime.ofInstant(other.ingreso.toInstant(), ZoneId.systemDefault());
        if(fechaYhoraThis.isAfter(fechaYhoraOther.plusMinutes(15))){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public int compareTo(Object o) {
        int i = 0;
        AsistenciaProfesor other = (AsistenciaProfesor) o;
        if(this.ingreso.before(other.ingreso)){
            i = -1;
        }
        if(this.ingreso.after(other.ingreso)){
            i = 1;
        }
        return i;
    }




}


