package gimnasio.modelo;
// Generated Dec 8, 2018 5:14:35 PM by Hibernate Tools 4.3.1


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Cuota generated by hbm2java
 */
public class Cuota  implements java.io.Serializable, java.lang.Comparable<Cuota> {


     private int idcuota;
     private Alumno alumno;
     private ClaseProfesor claseProfesor;
     private Double monto;
     private Date altacuota;
     private Date vencimiento;
     private String estado;
     private Set<CobroCuota> cobroCuotas = new HashSet(0);

    public Cuota() {
    }

    public Cuota(Alumno alumno, ClaseProfesor claseProfesor, Double monto, Date altacuota, Date vencimiento) {
        this.alumno = alumno;
        this.claseProfesor = claseProfesor;
        this.monto = monto;
        this.altacuota = altacuota;
        this.vencimiento = vencimiento;
    }

    public Cuota(Alumno alumno, ClaseProfesor claseProfesor, Double monto, Date altacuota, Date vencimiento, String estado) {
        this.alumno = alumno;
        this.claseProfesor = claseProfesor;
        this.monto = monto;
        this.altacuota = altacuota;
        this.vencimiento = vencimiento;
        this.estado = estado;
    }
	
    public Cuota(int idcuota, Alumno alumno, ClaseProfesor claseProfesor, Date altacuota, Date vencimiento, String estado) {
        this.idcuota = idcuota;
        this.alumno = alumno;
        this.claseProfesor = claseProfesor;
        this.altacuota = altacuota;
        this.vencimiento = vencimiento;
        this.estado = estado;
    }
    public Cuota(int idcuota, Alumno alumno, ClaseProfesor claseProfesor, Double monto, Date altacuota, Date vencimiento, String estado, Set cobroCuotas) {
       this.idcuota = idcuota;
       this.alumno = alumno;
       this.claseProfesor = claseProfesor;
       this.monto = monto;
       this.altacuota = altacuota;
       this.vencimiento = vencimiento;
       this.estado = estado;
       this.cobroCuotas = cobroCuotas;
    }
   
    public int getIdcuota() {
        return this.idcuota;
    }
    
    public void setIdcuota(int idcuota) {
        this.idcuota = idcuota;
    }
    public Alumno getAlumno() {
        return this.alumno;
    }
    
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Double getMonto() {
        return this.monto;
    }
    
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    public Date getAltacuota() {
        return this.altacuota;
    }
    
    public void setAltacuota(Date altacuota) {
        this.altacuota = altacuota;
    }
    public Date getVencimiento() {
        return this.vencimiento;
    }
    
    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set<CobroCuota> getCobroCuotas() {
        return this.cobroCuotas;
    }
    
    public void setCobroCuotas(Set cobroCuotas) {
        this.cobroCuotas = cobroCuotas;
    }

    public ClaseProfesor getClaseProfesor() {
        return claseProfesor;
    }

    public void setClaseProfesor(ClaseProfesor claseProfesor) {
        this.claseProfesor = claseProfesor;
    }    
    
    @Override
    public String toString() {
        String date = new SimpleDateFormat("dd/MM/yyyy").format(this.getAltacuota());
        return date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Cuota other = (Cuota) obj;
        
        LocalDate fechaThis = Instant.ofEpochMilli(this.getVencimiento().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        Month mesThis = fechaThis.getMonth();
        
        LocalDate fechaOther = Instant.ofEpochMilli(other.getVencimiento().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        Month mesOther = fechaOther.getMonth();
        
        if(mesThis.getValue()==mesOther.getValue()){
            return true;
        }
        if (!Objects.equals(this.alumno, other.alumno)) {
            return false;
        }
        if (!Objects.equals(this.claseProfesor, other.claseProfesor)) {
            return false;
        }
        if (!Objects.equals(this.altacuota, other.altacuota)) {
            return false;
        }
        if (!Objects.equals(this.vencimiento, other.vencimiento)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Cuota o) {
        int i = -1;
        if(this.equals(o)){
            i = 0;
        }else if(this.vencimiento.before(o.getVencimiento()) && this.monto < o.getMonto()){
            i = -1;
        }else if(this.vencimiento.after(o.getVencimiento()) && this.monto > o.getMonto()){
            i = 1;
        }
        return i;
    }

}


