/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Family
 */
public class TipoMovimiento implements Serializable {

    private Integer idtipomovimiento;
    @Column(name = "nombremovimiento")
    private String nombremovimiento;

    public TipoMovimiento() {
    }

    public TipoMovimiento(Integer idtipomovimiento) {
        this.idtipomovimiento = idtipomovimiento;
    }

    public Integer getIdtipomovimiento() {
        return idtipomovimiento;
    }

    public void setIdtipomovimiento(Integer idtipomovimiento) {
        this.idtipomovimiento = idtipomovimiento;
    }

    public String getNombremovimiento() {
        return nombremovimiento;
    }

    public void setNombremovimiento(String nombremovimiento) {
        this.nombremovimiento = nombremovimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipomovimiento != null ? idtipomovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMovimiento)) {
            return false;
        }
        TipoMovimiento other = (TipoMovimiento) object;
        if ((this.idtipomovimiento == null && other.idtipomovimiento != null) || (this.idtipomovimiento != null && !this.idtipomovimiento.equals(other.idtipomovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gimnasio.imagenes.TipoMovimiento[ idtipomovimiento=" + idtipomovimiento + " ]";
    }
    
}
