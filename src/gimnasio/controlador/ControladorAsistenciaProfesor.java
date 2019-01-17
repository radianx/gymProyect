/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.AsistenciaProfesor;
import java.util.List;
import gimnasio.herramientas.excepciones.Notificaciones;

/**
 *
 * @author Family
 */
public class ControladorAsistenciaProfesor {

    private ControladorPersistencia persistencia;
    private List<AsistenciaProfesor> listaAsistenciasProfesor;
    
    public ControladorAsistenciaProfesor(ControladorPersistencia miPersistencia) throws Notificaciones{
        this.persistencia = miPersistencia;
        this.listaAsistenciasProfesor = persistencia.getAsistenciaProfesor();
    }
    
    public void altaAsistenciaProfesor(AsistenciaProfesor asistencia) throws Notificaciones{
        persistencia.persistirInstancia(asistencia);
    }
}
