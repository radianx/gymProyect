package modelonuevo;
// Generated Dec 7, 2018 12:25:40 PM by Hibernate Tools 4.3.1



/**
 * SaldoCuota generated by hbm2java
 */
public class SaldoCuota  implements java.io.Serializable {


     private int idsaldocuota;
     private CobroCuota cobroCuota;
     private double montosaldo;
     private String estado;

    public SaldoCuota() {
    }

    public SaldoCuota(int idsaldocuota, CobroCuota cobroCuota, double montosaldo, String estado) {
       this.idsaldocuota = idsaldocuota;
       this.cobroCuota = cobroCuota;
       this.montosaldo = montosaldo;
       this.estado = estado;
    }
   
    public int getIdsaldocuota() {
        return this.idsaldocuota;
    }
    
    public void setIdsaldocuota(int idsaldocuota) {
        this.idsaldocuota = idsaldocuota;
    }
    public CobroCuota getCobroCuota() {
        return this.cobroCuota;
    }
    
    public void setCobroCuota(CobroCuota cobroCuota) {
        this.cobroCuota = cobroCuota;
    }
    public double getMontosaldo() {
        return this.montosaldo;
    }
    
    public void setMontosaldo(double montosaldo) {
        this.montosaldo = montosaldo;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }




}


