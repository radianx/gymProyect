package modelonuevo;
// Generated Dec 7, 2018 12:25:40 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cargo generated by hbm2java
 */
public class Cargo  implements java.io.Serializable {


     private int idcargo;
     private String nombrecargo;
     private String descripcioncargo;
     private String estado;
     private Set cargoPersonals = new HashSet(0);

    public Cargo() {
    }

	
    public Cargo(int idcargo, String nombrecargo, String estado) {
        this.idcargo = idcargo;
        this.nombrecargo = nombrecargo;
        this.estado = estado;
    }
    public Cargo(int idcargo, String nombrecargo, String descripcioncargo, String estado, Set cargoPersonals) {
       this.idcargo = idcargo;
       this.nombrecargo = nombrecargo;
       this.descripcioncargo = descripcioncargo;
       this.estado = estado;
       this.cargoPersonals = cargoPersonals;
    }
   
    public int getIdcargo() {
        return this.idcargo;
    }
    
    public void setIdcargo(int idcargo) {
        this.idcargo = idcargo;
    }
    public String getNombrecargo() {
        return this.nombrecargo;
    }
    
    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }
    public String getDescripcioncargo() {
        return this.descripcioncargo;
    }
    
    public void setDescripcioncargo(String descripcioncargo) {
        this.descripcioncargo = descripcioncargo;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set getCargoPersonals() {
        return this.cargoPersonals;
    }
    
    public void setCargoPersonals(Set cargoPersonals) {
        this.cargoPersonals = cargoPersonals;
    }




}


