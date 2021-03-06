/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Modalidad;
import java.util.List;

/**
 *
 * @author Osoft
 */
public class ControladorModalidad {
    ControladorPersistencia miPersistencia;
    List<Modalidad> listaModalidades;
    
    public ControladorModalidad(ControladorPersistencia controlador) throws Notificaciones{
        this.miPersistencia = controlador;
        listaModalidades = miPersistencia.getModalidades();
    }
    
    public void altaModalidad(Modalidad unaModalidad) throws Notificaciones{
        Modalidad miModalidad = buscarModalidad(unaModalidad.getNombremodalidad());
        String estado = "ACTIVO";
        unaModalidad.setEstado(estado);
        if (miModalidad != null) {
            miModalidad.setEstado(estado);
            miModalidad.setNombremodalidad(unaModalidad.getNombremodalidad());
            miModalidad.setDescripcionmodalidad(unaModalidad.getDescripcionmodalidad());
            this.miPersistencia.persistirInstancia(miModalidad);
            this.listaModalidades = miPersistencia.getModalidades();
        }else {
            this.miPersistencia.persistirInstancia(unaModalidad);
            listaModalidades = this.miPersistencia.getModalidades();
        }
    }
    
    public List<Modalidad> getListaModalidades() throws Notificaciones{
        listaModalidades = miPersistencia.getModalidades();
        return this.listaModalidades;
    }
    
    public Modalidad buscarModalidad (String nombreModalidad) throws Notificaciones{
        Modalidad unaModalidad = null;
        for(Modalidad miModalidad : this.listaModalidades){
            if(miModalidad.getNombremodalidad().equalsIgnoreCase(nombreModalidad)){
                unaModalidad = miModalidad;
                break;
            }
        }
        if(unaModalidad != null){
            return unaModalidad;
        }else{
            throw new Notificaciones("No se encontro la modalidad");
        }
    }
    
    public Modalidad buscarModalidad(int idModalidad){
        Modalidad unaModalidad = null;
        for(Modalidad miModalidad: this.listaModalidades){
            if(miModalidad.getIdmodalidad()== idModalidad){
                unaModalidad = miModalidad;
                break;
            }
        }
        return unaModalidad;
    }
    
    public void bajaModalidad(int idModalidad) throws Notificaciones{
        Modalidad unaModalidad = buscarModalidad(idModalidad);
        if(unaModalidad != null) unaModalidad.setEstado("INACTIVO");
        miPersistencia.persistirInstancia(unaModalidad);
        this.listaModalidades = miPersistencia.getModalidades();
    }
}
