/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.AsistenciaAlumno;
import java.util.List;
import gimnasio.herramientas.excepciones.Notificaciones;

/**
 *
 * @author Family
 */
public class ControladorAsistenciaAlumno {

    private ControladorPersistencia persistencia;
    private List<AsistenciaAlumno> listaAsistenciasAlumnos;
    
    public ControladorAsistenciaAlumno(ControladorPersistencia miPersistencia) throws Notificaciones{
        this.persistencia = miPersistencia;
        this.listaAsistenciasAlumnos = persistencia.getAsistenciaAlumno();
    }
    
    public void altaAsistenciaAlumno(AsistenciaAlumno asistencia) throws Notificaciones{
        persistencia.persistirInstancia(asistencia);
    }
    
    
}
