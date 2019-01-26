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
public class IngresosPuerta implements java.io.Serializable, Comparable{
    private int idIngresoPuerta;
    private Date horaIngreso;
    private Date horaEgreso;
    private Usuario usuario;

    public IngresosPuerta() {
    }

    public IngresosPuerta(int idIngresoPuerta, Date horaIngreso, Date horaEgreso, Usuario usuario) {
        this.idIngresoPuerta = idIngresoPuerta;
        this.horaIngreso = horaIngreso;
        this.horaEgreso = horaEgreso;
        this.usuario = usuario;
    }

    public IngresosPuerta(int idIngresoPuerta, Date horaIngreso, Usuario usuario) {
        this.idIngresoPuerta = idIngresoPuerta;
        this.horaIngreso = horaIngreso;
        this.usuario = usuario;
    }

    public IngresosPuerta(Date horaIngreso, Usuario usuario) {
        this.horaIngreso = horaIngreso;
        this.usuario = usuario;
    }

    public int getIdIngresoPuerta() {
        return idIngresoPuerta;
    }

    public void setIdIngresoPuerta(int idIngresoPuerta) {
        this.idIngresoPuerta = idIngresoPuerta;
    }

    public Date getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Date horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Date getHoraEgreso() {
        return horaEgreso;
    }

    public void setHoraEgreso(Date horaEgreso) {
        this.horaEgreso = horaEgreso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int compareTo(Object o) {
        int i=0;
        IngresosPuerta other = (IngresosPuerta) o;
        if(this.horaIngreso.before(other.horaIngreso)){
            i = -1;
        }else{
            i = 1;
        }
        return i;
    }

    
}
