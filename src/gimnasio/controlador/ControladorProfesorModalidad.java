/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Profesormodalidad;
import java.util.List;

/**
 *
 * @author Julien_W
 */
public class ControladorProfesorModalidad {

    ControladorPersistencia miPersistencia;
    List<Profesormodalidad> listaProfesorModalidad;
    
    public ControladorProfesorModalidad(ControladorPersistencia miPersistencia) throws Notificaciones {
        this.miPersistencia = miPersistencia;
        this.listaProfesorModalidad = miPersistencia.getProfesorModalidad();
    }
    
    public List<Profesormodalidad> getListaProfesorModalidad(){
        return this.listaProfesorModalidad;
    }
    
    public void bajaProfesorModalidad(Profesormodalidad profesorModalidad) throws Notificaciones{
        this.listaProfesorModalidad.remove(profesorModalidad);
        profesorModalidad.setEstado("INACTIVO");
        miPersistencia.persistirInstancia(profesorModalidad);
    }
    
    public void altaProfesorModalidad(Profesormodalidad profesorModalidad) throws Notificaciones{
        int i = listaProfesorModalidad.lastIndexOf(profesorModalidad);
        if(i>=0){
            throw new Notificaciones(profesorModalidad.getProfesor()+" ya tiene asignado/a la modalidad "+profesorModalidad.getModalidad());
        }else{
            miPersistencia.persistirInstancia(profesorModalidad);
            listaProfesorModalidad.add(profesorModalidad);
        }
    }
    
}
