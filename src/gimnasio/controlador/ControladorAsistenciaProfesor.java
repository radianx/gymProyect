/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.AsistenciaProfesor;
import java.util.List;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.AsistenciaAlumno;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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
        listaAsistenciasProfesor = persistencia.getAsistenciaProfesor();
        if(listaAsistenciasProfesor.contains(asistencia)){
            asistencia.setSalida(new Date());
            persistencia.persistirInstancia(asistencia);
        }else{
            persistencia.persistirInstancia(asistencia);
        }
    }

    public List<AsistenciaProfesor> getAsistenciasDeHoy() throws Notificaciones {
        listaAsistenciasProfesor = persistencia.getAsistenciaProfesor();
        List<AsistenciaProfesor> retorno = new ArrayList<>();
        for(AsistenciaProfesor asistProfe:listaAsistenciasProfesor){
            LocalDate fecha = asistProfe.getIngreso().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(fecha.isEqual(LocalDate.now())){
                retorno.add(asistProfe);
            }
        }
        return retorno;
    }
}
