/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author Family
 */
public class HorarioProfesor {

    private int idHorario;
    private ClaseProfesor claseProfesor;
    private Date inicio;
    private Date fin;
    private String estado;
    private String promocion;

    public HorarioProfesor() {
    }

    public HorarioProfesor(int idHorario, ClaseProfesor claseProfesor, Date inicio, Date fin) {
        this.idHorario = idHorario;
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

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
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
        SimpleDateFormat formato = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
        String fecha = formato.format(this.inicio);
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public String getInicioString() {
        String text = new SimpleDateFormat("HH:mm").format(this.inicio);
        return text;
    }

}
