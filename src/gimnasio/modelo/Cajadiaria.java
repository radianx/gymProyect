package gimnasio.modelo;
// Generated 03/12/2018 19:24:10 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Cajadiaria generated by hbm2java
 */
public class Cajadiaria  implements java.io.Serializable {


     private int idcaja;
     private double montoinicial;
     private Double montofinal;
     private Date fechainicial;
     private Date fechafinal;

    public Cajadiaria() {
    }

	
    public Cajadiaria(int idcaja, double montoinicial) {
        this.idcaja = idcaja;
        this.montoinicial = montoinicial;
    }
    public Cajadiaria(int idcaja, double montoinicial, Double montofinal, Date fechainicial, Date fechafinal) {
       this.idcaja = idcaja;
       this.montoinicial = montoinicial;
       this.montofinal = montofinal;
       this.fechainicial = fechainicial;
       this.fechafinal = fechafinal;
    }
   
    public int getIdcaja() {
        return this.idcaja;
    }
    
    public void setIdcaja(int idcaja) {
        this.idcaja = idcaja;
    }
    public double getMontoinicial() {
        return this.montoinicial;
    }
    
    public void setMontoinicial(double montoinicial) {
        this.montoinicial = montoinicial;
    }
    public Double getMontofinal() {
        return this.montofinal;
    }
    
    public void setMontofinal(Double montofinal) {
        this.montofinal = montofinal;
    }
    public Date getFechainicial() {
        return this.fechainicial;
    }
    
    public void setFechainicial(Date fechainicial) {
        this.fechainicial = fechainicial;
    }
    public Date getFechafinal() {
        return this.fechafinal;
    }
    
    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }




}

