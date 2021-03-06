package gimnasio.modelo;
// Generated Dec 8, 2018 5:14:35 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * SectorClase generated by hbm2java
 */
public class SectorClase  implements java.io.Serializable {


     private SectorClaseId id;
     private ClaseProfesor claseProfesor;
     private Sector sector;
     private Date inicio;
     private Date fin;
     private String estado;

    public SectorClase() {
    }

	
    public SectorClase(ClaseProfesor claseProfesor, Sector sector, String estado) {
        this.claseProfesor = claseProfesor;
        this.sector = sector;
        this.estado = estado;
    }
    public SectorClase(ClaseProfesor claseProfesor, Sector sector, Date inicio, Date fin, String estado) {
       this.claseProfesor = claseProfesor;
       this.sector = sector;
       this.inicio = inicio;
       this.fin = fin;
       this.estado = estado;
    }
   
    public SectorClaseId getId() {
        return this.id;
    }
    
    public void setId(SectorClaseId id) {
        this.id = id;
    }
    public ClaseProfesor getClaseProfesor() {
        return this.claseProfesor;
    }
    
    public void setClaseProfesor(ClaseProfesor claseProfesor) {
        this.claseProfesor = claseProfesor;
    }
    public Sector getSector() {
        return this.sector;
    }
    
    public void setSector(Sector sector) {
        this.sector = sector;
    }
    public Date getInicio() {
        return this.inicio;
    }
    
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }
    public Date getFin() {
        return this.fin;
    }
    
    public void setFin(Date fin) {
        this.fin = fin;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return sector.getNombresector();
    }




}


