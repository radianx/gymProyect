package gimnasio.modelo;
// Generated Dec 8, 2018 5:14:35 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

public class Clase  implements java.io.Serializable {


     private int idclase;
     private String tipoclase;
     private Integer alumnosmaximo;
     private String descripcionclase;
     private String estado;
//     private Set cuotas = new HashSet(0);
     private Set<ClaseProfesor> claseProfesors = new HashSet(0);

    public Clase() {
    }

	
    public Clase(String tipoclase, String estado) {
        this.tipoclase = tipoclase;
        this.estado = estado;
    }
    public Clase(String tipoclase, Integer alumnosmaximo, String descripcionclase, String estado) {
       this.tipoclase = tipoclase;
       this.alumnosmaximo = alumnosmaximo;
       this.descripcionclase = descripcionclase;
       this.estado = estado;
    }

    public Clase(String text, String text0, int parseInt) {
        this.descripcionclase = text;
        this.tipoclase = text0;
        this.alumnosmaximo = parseInt;
        this.estado = "ACTIVO";
    }
   
    public int getIdclase() {
        return this.idclase;
    }
    
    public void setIdclase(int idclase) {
        this.idclase = idclase;
    }
    public String getTipoclase() {
        return this.tipoclase;
    }
    
    public void setTipoclase(String tipoclase) {
        this.tipoclase = tipoclase;
    }
    public Integer getAlumnosmaximo() {
        return this.alumnosmaximo;
    }
    
    public void setAlumnosmaximo(Integer alumnosmaximo) {
        this.alumnosmaximo = alumnosmaximo;
    }
    public String getDescripcionclase() {
        return this.descripcionclase;
    }
    
    public void setDescripcionclase(String descripcionclase) {
        this.descripcionclase = descripcionclase;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
//    public Set getCuotas() {
//        return this.cuotas;
//    }
//    
//    public void setCuotas(Set cuotas) {
//        this.cuotas = cuotas;
//    }
    public Set<ClaseProfesor> getClaseProfesors() {
        return this.claseProfesors;
    }
    
    public void setClaseProfesors(Set claseProfesors) {
        this.claseProfesors = claseProfesors;
    }

    @Override
    public String toString() {
        return this.descripcionclase;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idclase;
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
        final Clase other = (Clase) obj;
        if( this.idclase != other.idclase){
            return false;
        }
        return true;
    }
    
    
}


