/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Family
 */
public class Rubro implements Serializable {

    private Integer idrubro;

    private String nombrerubro;

    private String estado;

    private Set<Producto> productoSet;

    public Rubro() {
    }

    public Rubro(Integer idrubro) {
        this.idrubro = idrubro;
    }

    public Integer getIdrubro() {
        return idrubro;
    }

    public void setIdrubro(Integer idrubro) {
        this.idrubro = idrubro;
    }

    public String getNombrerubro() {
        return nombrerubro;
    }

    public void setNombrerubro(String nombrerubro) {
        this.nombrerubro = nombrerubro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<Producto> getProductoSet() {
        return productoSet;
    }

    public void setProductoSet(Set<Producto> productoSet) {
        this.productoSet = productoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubro != null ? idrubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubro)) {
            return false;
        }
        Rubro other = (Rubro) object;
        if ((this.idrubro == null && other.idrubro != null) || (this.idrubro != null && !this.idrubro.equals(other.idrubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gimnasio.imagenes.Rubro[ idrubro=" + idrubro + " ]";
    }
    
}
