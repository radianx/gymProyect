/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.HorarioProfesor;
import java.util.List;

/**
 *
 * @author Family
 */
public class ControladorHorarioProfesor {

    private ControladorPersistencia miPersistencia;
    private List<HorarioProfesor> listaHorariosProfesor;
    
    public ControladorHorarioProfesor(ControladorPersistencia miPersistencia) throws Notificaciones {
        this.miPersistencia = miPersistencia;
        this.listaHorariosProfesor = this.miPersistencia.getHorariosProfesor();
    }
    
    public void altaHorarioProfesor(HorarioProfesor unHorarioProfesor) throws Notificaciones{
        this.miPersistencia.persistirInstancia(unHorarioProfesor);
    }

    public List<HorarioProfesor> getListaHorarios() throws Notificaciones {
        listaHorariosProfesor = miPersistencia.getHorariosProfesor();
        return listaHorariosProfesor;
    }

    void bajaHorarioProfesor(HorarioProfesor horarioProfesor) throws Notificaciones {
        miPersistencia.eliminarInstancia(horarioProfesor);
    }
    
}