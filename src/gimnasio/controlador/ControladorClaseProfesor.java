/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.ClaseProfesor;
import java.util.List;

/**
 *
 * @author Julien_W
 */
public class ControladorClaseProfesor {

    ControladorPersistencia persistencia;
    List<ClaseProfesor> listaClaseProfesor;
    
    public ControladorClaseProfesor(ControladorPersistencia miPersistencia) throws Notificaciones {
        this.persistencia = miPersistencia;
        listaClaseProfesor = persistencia.getClasesProfesores();
    }

    public void altaClaseProfesor(ClaseProfesor unaClaseProfesor) throws Notificaciones {
        persistencia.persistirInstancia(unaClaseProfesor);
        this.listaClaseProfesor.add(unaClaseProfesor);
    }

    public void bajaClaseProfesor(ClaseProfesor unaClase) throws Notificaciones {
        listaClaseProfesor.remove(unaClase);
        unaClase.setEstado("INACTIVO");
        persistencia.persistirInstancia(unaClase);
    }
    
}
