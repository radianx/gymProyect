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
public class HorarioAlumno {
    private int idHorarioAlumno;
    private ClaseAlumno claseAlumno;
    private Date inicio;
    private Date fin;
    private String estado;

    public HorarioAlumno() {
    }

    public HorarioAlumno(int idHorarioAlumno, ClaseAlumno claseAlumno, Date inicio, Date fin) {
        this.idHorarioAlumno = idHorarioAlumno;
        this.claseAlumno = claseAlumno;
        this.inicio = inicio;
        this.fin = fin;
    }

    public HorarioAlumno(Date inicio, Date fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    
    
    public HorarioAlumno(ClaseAlumno claseAlumno, Date inicio, Date fin) {
        this.claseAlumno = claseAlumno;
        this.inicio = inicio;
        this.fin = fin;
        claseAlumno.getHorarios().add(this);
    }

    public int getIdHorarioAlumno() {
        return idHorarioAlumno;
    }

    public void setIdHorarioAlumno(int idHorarioAlumno) {
        this.idHorarioAlumno = idHorarioAlumno;
    }

    public ClaseAlumno getClaseAlumno() {
        return claseAlumno;
    }

    public void setClaseAlumno(ClaseAlumno claseAlumno) {
        this.claseAlumno = claseAlumno;
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


    @Override
    public String toString() {
        String text = new SimpleDateFormat("HH:mm").format(this.inicio);
        return text;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idHorarioAlumno;
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
        final HorarioAlumno other = (HorarioAlumno) obj;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getInicioString() {
        String text = new SimpleDateFormat("HH:mm").format(this.inicio);
        return text;
    }
    
    
    
}
