/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Personal;
import java.util.List;

/**
 *
 * @author Julien_W
 */
public class ControladorPersonal {

    private ControladorPersistencia persistencia;
    private List<Personal> listaPersonales;
    
    public ControladorPersonal(ControladorPersistencia miPersistencia) throws Notificaciones {
        persistencia = miPersistencia;
        listaPersonales = persistencia.getPersonales();
    }

    public void altaPersonal(Personal unPersonal) throws Notificaciones{
        persistencia.persistirInstancia(unPersonal);
    }
    
}
