package gimnasio.modelo;
// Generated 03/12/2018 19:24:10 by Hibernate Tools 4.3.1



/**
 * SaldoCuota generated by hbm2java
 */
public class SaldoCuota  implements java.io.Serializable {


     private int idsaldocuota;
     private CobroCuota cobroCuota;
     private double montosaldo;
     private boolean saldado;

    public SaldoCuota() {
    }

    public SaldoCuota(int idsaldocuota, CobroCuota cobroCuota, double montosaldo, boolean saldado) {
       this.idsaldocuota = idsaldocuota;
       this.cobroCuota = cobroCuota;
       this.montosaldo = montosaldo;
       this.saldado = saldado;
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
    public boolean isSaldado() {
        return this.saldado;
    }
    
    public void setSaldado(boolean saldado) {
        this.saldado = saldado;
    }




}


