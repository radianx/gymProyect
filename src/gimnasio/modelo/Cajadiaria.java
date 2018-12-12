package gimnasio.modelo;
// Generated Dec 7, 2018 12:25:40 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Cajadiaria generated by hbm2java
 */
public class Cajadiaria  implements java.io.Serializable {


     private int idcaja;
     private double montoactual;
     private Personal personal;
     private Date apertura;
     private Date cierre;
     private String estado;

    public Cajadiaria() {
    }

	
    public Cajadiaria(Personal personal, Double montoactual, Date apertura, Date cierre, String estado) {
       this.personal = personal;
       this.montoactual = montoactual;;
       this.apertura = apertura;
       this.cierre = cierre;
       this.estado = estado;
    }
   
    public int getIdcaja() {
        return this.idcaja;
    }
    
    public void setIdcaja(int idcaja) {
        this.idcaja = idcaja;
    }
    public double getMontoactual() {
        return this.montoactual;
    }
    
    public void setMontoactual(double montoactual) {
        this.montoactual = montoactual;
    }
     
    public void setApertura(Date apertura) {
        this.apertura = apertura;
    }
    public Date getApertura() {
        return this.apertura;
    }
    
      public void setCierre(Date cierre) {
        this.cierre = cierre;
    }
    public Date getCierre() {
        return this.cierre;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}


