/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.AsistenciaAlumno;
import java.util.List;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.ClaseAlumno;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
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
    
    public void altaAsistenciaAlumno(ClaseAlumno claseAlumno, Date fecha) throws Notificaciones{
        listaAsistenciasAlumnos = persistencia.getAsistenciaAlumno();
        persistencia.actualizarInstancias();
        //ver si no existe una Asistencia entrada dentro del rango de 2 horas en el pasado
        //y si existe pero esta fuera del rango entonces marcar salida
        AsistenciaAlumno actualizar = null;
        boolean primeraVez = true;
        for(AsistenciaAlumno asistenciaBD : listaAsistenciasAlumnos) {
            if (claseAlumno.getIdclasealumno() == asistenciaBD.getClaseAlumno().getIdclasealumno()) {
                if (asistenciaBD.getSalida() == null) {
                    asistenciaBD.setSalida(new Date());
                    actualizar = asistenciaBD;
                } else {
                    LocalTime hora = Instant.ofEpochMilli(asistenciaBD.getIngreso().getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalTime();
                    //si vino hace dos horas
                    if (hora.plusHours(2).isAfter(LocalTime.now())) {
                        AsistenciaAlumno asistencia = new AsistenciaAlumno(claseAlumno, fecha, "ACTIVO");
                        persistencia.persistirInstancia(asistencia);
                    }
                }
            }else{
                AsistenciaAlumno asistencia = new AsistenciaAlumno(claseAlumno, fecha, "ACTIVO");
                persistencia.persistirInstancia(asistencia);
            }
            primeraVez = false;
        }
        if(primeraVez){
            AsistenciaAlumno asistencia = new AsistenciaAlumno(claseAlumno, fecha, "ACTIVO");
            persistencia.persistirInstancia(asistencia);
        }
        
        if(actualizar !=null){
            persistencia.persistirInstancia(actualizar);
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

    public List<AsistenciaAlumno> getListaAsistenciaAlumno() throws Notificaciones {
        listaAsistenciasAlumnos = persistencia.getAsistenciaAlumno();
        persistencia.actualizarInstancias();
        return listaAsistenciasAlumnos;
    }
    
    
}
