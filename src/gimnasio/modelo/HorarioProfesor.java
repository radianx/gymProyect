/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.util.Date;

/**
 *
 * @author Family
 */
class HorarioProfesor {
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

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public ClaseProfesor getClaseProfesor() {
        return claseProfesor;
    }

    public void setClaseProfesor(ClaseProfesor claseProfesor) {
        this.claseProfesor = claseProfesor;
    }
    
    
}
