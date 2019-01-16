/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Family
 */
public class HorarioProfesor {
    private int idHorarioProfesor;
    private ClaseProfesor claseProfesor;
    private Date inicio;
    private Date fin;

    public HorarioProfesor() {
    }

    public HorarioProfesor(int idHorarioProfesor, ClaseProfesor claseProfesor, Date inicio, Date fin) {
        this.idHorarioProfesor = idHorarioProfesor;
        this.claseProfesor = claseProfesor;
        this.inicio = inicio;
        this.fin = fin;
    }

    public HorarioProfesor(Date inicio, Date fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    
    
    public HorarioProfesor(ClaseProfesor claseProfesor, Date inicio, Date fin) {
        this.claseProfesor = claseProfesor;
        this.inicio = inicio;
        this.fin = fin;
    }
    
    

    public int getIdHorarioProfesor() {
        return idHorarioProfesor;
    }

    public void setIdHorarioProfesor(int idHorarioProfesor) {
        this.idHorarioProfesor = idHorarioProfesor;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public String getFinString() {
        String text = new SimpleDateFormat("HH:mm").format(this.fin);
        return text;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public ClaseProfesor getClaseProfesor() {
        return claseProfesor;
    }

    public void setClaseProfesor(ClaseProfesor claseProfesor) {
        this.claseProfesor = claseProfesor;
    }

    @Override
    public String toString() {
        String text = new SimpleDateFormat("HH:mm").format(this.inicio);
        return text;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idHorarioProfesor;
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
        final HorarioProfesor other = (HorarioProfesor) obj;
        if (!Objects.equals(this.inicio, other.inicio)) {
            return false;
        }
        if (!Objects.equals(this.fin, other.fin)) {
            return false;
        }
        
        if (this.inicio.after(other.inicio) && this.inicio.before(other.fin)){
            return true;
        }
        
        return true;
    }
    
    
    
}