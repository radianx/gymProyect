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
    
    ControladorClaseProfesor(ControladorPersistencia miPersistencia) {
        this.persistencia = miPersistencia;
        listaClaseProfesor = persistencia.getClasesProfesores();
    }

    public void altaClaseProfesor(ClaseProfesor unaClaseProfesor) throws Notificaciones {
        persistencia.persistirInstancia(unaClaseProfesor);
        this.listaClaseProfesor.add(unaClaseProfesor);
    }
    
}
