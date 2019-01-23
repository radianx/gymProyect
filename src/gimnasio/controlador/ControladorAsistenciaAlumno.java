/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.AsistenciaAlumno;
import java.util.List;
import gimnasio.herramientas.excepciones.Notificaciones;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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
        listaAsistenciasAlumnos = persistencia.getAsistenciaAlumno();
        if(listaAsistenciasAlumnos.contains(asistencia)){
            int i = listaAsistenciasAlumnos.indexOf(asistencia);
            AsistenciaAlumno unaAsistencia = listaAsistenciasAlumnos.get(i);
            unaAsistencia.setSalida(new Date());
            persistencia.persistirInstancia(unaAsistencia);
        }else{
            persistencia.persistirInstancia(asistencia);
        }
    }

    public List<AsistenciaAlumno> getAsistenciasDeHoy() throws Notificaciones {
        listaAsistenciasAlumnos = persistencia.getAsistenciaAlumno();
        List<AsistenciaAlumno> retorno = new ArrayList<>();
        for(AsistenciaAlumno asistAlumno:listaAsistenciasAlumnos){
            LocalDate fecha = asistAlumno.getIngreso().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(fecha.isEqual(LocalDate.now())){
                retorno.add(asistAlumno);
            }
        }
        return retorno;
    }
    
    
}
