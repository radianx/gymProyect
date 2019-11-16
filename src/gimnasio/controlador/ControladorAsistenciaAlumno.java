/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.AsistenciaAlumno;
import java.util.List;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.AsistenciaProfesor;
import gimnasio.modelo.ClaseAlumno;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

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
    
    public void altaAsistenciaAlumno(boolean manual,ClaseAlumno claseAlumno, Date fecha) throws Notificaciones{
//       listaAsistenciasAlumnos = persistencia.getAsistenciaAlumno();
        //ver si no existe una Asistencia entrada dentro del rango de 2 horas en el pasado
        //y si existe pero esta fuera del rango entonces marcar salida
        persistencia.actualizarInstancias();
        if (manual) {
            AsistenciaAlumno asistencia = new AsistenciaAlumno(claseAlumno, fecha, "ACTIVO");
            System.out.println("Agregando nueva entrada solamente");
            persistencia.persistirInstancia(asistencia);
        } else {
            //ver si no existe una Asistencia entrada dentro del rango de 2 horas en el pasado
            //y si existe pero esta fuera del rango entonces marcar salida
//        AsistenciaProfesor actualizar = null;
//        boolean primeraVez = false;
//        boolean encontrado = false;
            Set<AsistenciaAlumno> listaAsistencias = claseAlumno.getAsistenciaAlumnos();
            //Si el man tiene asistencias
            if (!listaAsistencias.isEmpty()) {
                List<AsistenciaAlumno> lista = new ArrayList<>();
                lista.addAll(listaAsistencias);
                Collections.sort(lista);
                AsistenciaAlumno ultimaAsistencia = lista.get(lista.size() - 1);
                LocalTime hora = Instant.ofEpochMilli(ultimaAsistencia.getIngreso().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalTime();
                LocalTime fechaConvertida = Instant.ofEpochMilli(fecha.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalTime();
                //si vino hace mas de dos horas
                if (hora.plusHours(2).isBefore(fechaConvertida)) {
                    if (ultimaAsistencia.getSalida() == null) {
                        ultimaAsistencia.setSalida(fecha);
                        AsistenciaAlumno asistencia = new AsistenciaAlumno(claseAlumno, fecha, "ACTIVO");
                        System.out.println("Colocando Ultima Salida y agregando nueva entrada");
                        persistencia.persistirInstancia(asistencia);
                        persistencia.persistirInstancia(ultimaAsistencia);
                        claseAlumno.getAsistenciaAlumnos().add(asistencia);
                        persistencia.persistirInstancia(claseAlumno);
                    } else {
                        AsistenciaAlumno asistencia = new AsistenciaAlumno(claseAlumno, fecha, "ACTIVO");
                        System.out.println("Agregando nueva entrada solamente de alumno");
                        persistencia.persistirInstancia(asistencia);
                        claseAlumno.getAsistenciaAlumnos().add(asistencia);
                        persistencia.persistirInstancia(claseAlumno);
                    }
                }else{
                    if(ultimaAsistencia.getSalida()==null){
                        ultimaAsistencia.setSalida(fecha);
                        System.out.println("Marcando salida de asistencia");
                        persistencia.persistirInstancia(ultimaAsistencia);
                    }
                }

            } else {
                AsistenciaAlumno asistencia = new AsistenciaAlumno(claseAlumno, fecha, "ACTIVO");
                System.out.println("Primer registro de asistencia");
                persistencia.persistirInstancia(asistencia);
                claseAlumno.getAsistenciaAlumnos().add(asistencia);
                persistencia.persistirInstancia(claseAlumno);
            }
        }

    }

    public List<AsistenciaAlumno> getAsistenciasDeHoy() throws Notificaciones {
        listaAsistenciasAlumnos = persistencia.getAsistenciaAlumno();
        List<AsistenciaAlumno> retorno = new ArrayList<>();
        for(AsistenciaAlumno asistAlumno:listaAsistenciasAlumnos){
            if (asistAlumno.getIngreso() != null) {
                LocalDate fecha = asistAlumno.getIngreso().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (fecha.isEqual(LocalDate.now())) {
                    retorno.add(asistAlumno);
                }
            }
        }
        return retorno;
    }

    public List<AsistenciaAlumno> getListaAsistenciaAlumno() throws Notificaciones {
        listaAsistenciasAlumnos = persistencia.getAsistenciaAlumno();
        persistencia.actualizarInstancias();
        return listaAsistenciasAlumnos;
    }

    public void marcarSalidas() throws Notificaciones {
        listaAsistenciasAlumnos = persistencia.getAsistenciaAlumno();
        for(AsistenciaAlumno asistencia:listaAsistenciasAlumnos){
            if(asistencia.getSalida()==null){
                asistencia.setSalida(new Date());
            }
        }
    }
    
    
}
