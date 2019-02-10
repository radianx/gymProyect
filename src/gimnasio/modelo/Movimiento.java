/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Family
 */
public class Movimiento implements Serializable, Comparable {

    private Integer idmovimiento;

    private Producto producto;

    private Double monto;

    private Double montoCliente;

    private Double vuelto;

    private String detalle;

    private Date hora;

    private String estado;

    private Cajadiaria caja;

    private Usuario usuario;

    private Set<Movimiento> movimientoSet;

    public Movimiento() {
    }

    public Movimiento(Double monto, Double montoCliente, Double vuelto, String detalle, Date hora, String estado, Cajadiaria caja, Usuario usuario) {
        this.monto = monto;
        this.montoCliente = montoCliente;
        this.vuelto = vuelto;
        this.detalle = detalle;
        this.hora = hora;
        this.estado = estado;
        this.caja = caja;
        this.usuario = usuario;
    }

    
    
    public Movimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getMontoCliente() {
        return montoCliente;
    }

    public void setMontoCliente(Double montoCliente) {
        this.montoCliente = montoCliente;
    }

    public Double getVuelto() {
        return vuelto;
    }

    public void setVuelto(Double vuelto) {
        this.vuelto = vuelto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cajadiaria getCaja() {
        return caja;
    }

    public void setCaja(Cajadiaria caja) {
        this.caja = caja;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Movimiento> getMovimientoSet() {
        return movimientoSet;
    }

    public void setMovimientoSet(Set<Movimiento> movimientoSet) {
        this.movimientoSet = movimientoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovimiento != null ? idmovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.idmovimiento == null && other.idmovimiento != null) || (this.idmovimiento != null && !this.idmovimiento.equals(other.idmovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(idmovimiento);
    }

    @Override
    public int compareTo(Object o) {
        int i = 0;
        Movimiento other = (Movimiento) o;
        LocalDate fechaThis = Instant.ofEpochMilli(this.getHora().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaOther = Instant.ofEpochMilli(other.getHora().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        if(fechaThis.isBefore(fechaOther)){
            i = -1;
        }
        if(fechaThis.isAfter(fechaOther)){
            i = 1;
        }
        return i;
    }

    public String getHoraString() {
        String text = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        text = sdf.format(this.hora);
        return text;
    }
    
}
