package gimnasio.modelo;
// Generated 03/12/2018 19:24:10 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Sistema generated by hbm2java
 */
public class Sistema  implements java.io.Serializable {


     private String idsistema;
     private String propietario;
     private String codigosistema;
     private Date fechacaducidad;
     private String contraseniasistema;
     private Set modulos = new HashSet(0);

    public Sistema() {
    }

	
    public Sistema(String idsistema, String propietario, String codigosistema, String contraseniasistema) {
        this.idsistema = idsistema;
        this.propietario = propietario;
        this.codigosistema = codigosistema;
        this.contraseniasistema = contraseniasistema;
    }
    public Sistema(String idsistema, String propietario, String codigosistema, Date fechacaducidad, String contraseniasistema, Set modulos) {
       this.idsistema = idsistema;
       this.propietario = propietario;
       this.codigosistema = codigosistema;
       this.fechacaducidad = fechacaducidad;
       this.contraseniasistema = contraseniasistema;
       this.modulos = modulos;
    }
   
    public String getIdsistema() {
        return this.idsistema;
    }
    
    public void setIdsistema(String idsistema) {
        this.idsistema = idsistema;
    }
    public String getPropietario() {
        return this.propietario;
    }
    
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    public String getCodigosistema() {
        return this.codigosistema;
    }
    
    public void setCodigosistema(String codigosistema) {
        this.codigosistema = codigosistema;
    }
    public Date getFechacaducidad() {
        return this.fechacaducidad;
    }
    
    public void setFechacaducidad(Date fechacaducidad) {
        this.fechacaducidad = fechacaducidad;
    }
    public String getContraseniasistema() {
        return this.contraseniasistema;
    }
    
    public void setContraseniasistema(String contraseniasistema) {
        this.contraseniasistema = contraseniasistema;
    }
    public Set getModulos() {
        return this.modulos;
    }
    
    public void setModulos(Set modulos) {
        this.modulos = modulos;
    }




}


