package gimnasio.modelo;
// Generated Dec 7, 2018 12:25:40 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CobroCuota generated by hbm2java
 */
public class CobroCuota  implements java.io.Serializable {


     private int idcobrocuota;
     private Cuota cuota;
     private double montocobro;
     private Date fechapago;
     private Set saldoCuotas = new HashSet(0);
     private Set saldoPagoProfesors = new HashSet(0);

    public CobroCuota() {
    }

	
    public CobroCuota(int idcobrocuota, Cuota cuota, double montocobro, Date fechapago) {
        this.idcobrocuota = idcobrocuota;
        this.cuota = cuota;
        this.montocobro = montocobro;
        this.fechapago = fechapago;
    }
    public CobroCuota(int idcobrocuota, Cuota cuota, double montocobro, Date fechapago, Set saldoCuotas, Set saldoPagoProfesors) {
       this.idcobrocuota = idcobrocuota;
       this.cuota = cuota;
       this.montocobro = montocobro;
       this.fechapago = fechapago;
       this.saldoCuotas = saldoCuotas;
       this.saldoPagoProfesors = saldoPagoProfesors;
    }
   
    public int getIdcobrocuota() {
        return this.idcobrocuota;
    }
    
    public void setIdcobrocuota(int idcobrocuota) {
        this.idcobrocuota = idcobrocuota;
    }
    public Cuota getCuota() {
        return this.cuota;
    }
    
    public void setCuota(Cuota cuota) {
        this.cuota = cuota;
    }
    public double getMontocobro() {
        return this.montocobro;
    }
    
    public void setMontocobro(double montocobro) {
        this.montocobro = montocobro;
    }
    public Date getFechapago() {
        return this.fechapago;
    }
    
    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }
    public Set getSaldoCuotas() {
        return this.saldoCuotas;
    }
    
    public void setSaldoCuotas(Set saldoCuotas) {
        this.saldoCuotas = saldoCuotas;
    }
    public Set getSaldoPagoProfesors() {
        return this.saldoPagoProfesors;
    }
    
    public void setSaldoPagoProfesors(Set saldoPagoProfesors) {
        this.saldoPagoProfesors = saldoPagoProfesors;
    }




}


