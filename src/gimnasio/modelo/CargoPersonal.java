package gimnasio.modelo;
// Generated Dec 8, 2018 5:14:35 PM by Hibernate Tools 4.3.1



/**
 * CargoPersonal generated by hbm2java
 */
public class CargoPersonal  implements java.io.Serializable {


     private int idcargopersonal;
     private Cargo cargo;
     private Personal personal;
     private String estado;

    public CargoPersonal() {
    }

    public CargoPersonal(int idcargopersonal, Cargo cargo, Personal personal, String estado) {
       this.idcargopersonal = idcargopersonal;
       this.cargo = cargo;
       this.personal = personal;
       this.estado = estado;
    }
   
    public int getIdcargopersonal() {
        return this.idcargopersonal;
    }
    
    public void setIdcargopersonal(int idcargopersonal) {
        this.idcargopersonal = idcargopersonal;
    }
    public Cargo getCargo() {
        return this.cargo;
    }
    
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    public Personal getPersonal() {
        return this.personal;
    }
    
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }




}


