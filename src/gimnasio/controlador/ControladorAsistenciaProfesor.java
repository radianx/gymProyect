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
import gimnasio.modelo.ClaseProfesor;
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
public class ControladorAsistenciaProfesor {

    private ControladorPersistencia persistencia;
    private List<AsistenciaProfesor> listaAsistenciasProfesor;
    
    public ControladorAsistenciaProfesor(ControladorPersistencia miPersistencia) throws Notificaciones{
        this.persistencia = miPersistencia;
        this.listaAsistenciasProfesor = persistencia.getAsistenciaProfesor();
    }
    
    public void altaAsistenciaProfesor(boolean manual, ClaseProfesor claseProfesor, Date fecha) throws Notificaciones {
//        this.listaAsistenciasProfesor = persistencia.getAsistenciaProfesor();
        persistencia.actualizarInstancias();
        if (manual) {
            AsistenciaProfesor asistencia = new AsistenciaProfesor(claseProfesor, fecha, "ACTIVO");
            System.out.println("Agregando nueva entrada solamente");
            persistencia.persistirInstancia(asistencia);
        } else {
            //ver si no existe una Asistencia entrada dentro del rango de 2 horas en el pasado
            //y si existe pero esta fuera del rango entonces marcar salida
//        AsistenciaProfesor actualizar = null;
//        boolean primeraVez = false;
//        boolean encontrado = false;
            Set<AsistenciaProfesor> listaAsistencias = claseProfesor.getAsistenciaProfesors();
            //Si el man tiene asistencias
            if (!listaAsistencias.isEmpty()) {
                List<AsistenciaProfesor> lista = new ArrayList<>();
                lista.addAll(listaAsistencias);
                Collections.sort(lista);
                AsistenciaProfesor ultimaAsistencia = lista.get(lista.size() - 1);
                System.out.println("La hora del ultimo ingreso de la ultima asistencia es: "+ultimaAsistencia.getIngreso());
                LocalTime hora = Instant.ofEpochMilli(ultimaAsistencia.getIngreso().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalTime();
                LocalTime fechaConvertida = Instant.ofEpochMilli(fecha.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalTime();
                System.out.println("Va a ser comparada con la hora: "+fecha);
                //si vino hace mas de dos horas
                if (hora.plusHours(2).isBefore(fechaConvertida)) {
                    if (ultimaAsistencia.getSalida() == null) {
                        ultimaAsistencia.setSalida(fecha);
                        AsistenciaProfesor asistencia = new AsistenciaProfesor(claseProfesor, fecha, "ACTIVO");
                        System.out.println("Colocando Ultima Salida y agregando nueva entrada");
                        persistencia.persistirInstancia(asistencia);
                        persistencia.persistirInstancia(ultimaAsistencia);
                        claseProfesor.getAsistenciaProfesors().add(asistencia);
                        persistencia.persistirInstancia(claseProfesor);
                    } else {
                        System.out.println("La hora de salida de la ultima asistencia fue: "+ultimaAsistencia.getSalida());
                        AsistenciaProfesor asistencia = new AsistenciaProfesor(claseProfesor, fecha, "ACTIVO");
                        System.out.println("Agregando nueva entrada solamente de profesor");
                        persistencia.persistirInstancia(asistencia);
                        claseProfesor.getAsistenciaProfesors().add(asistencia);
                        persistencia.persistirInstancia(claseProfesor);
                    }
                }else{
                    if(ultimaAsistencia.getSalida()==null){
                        ultimaAsistencia.setSalida(fecha);
                        System.out.println("Marcando salida de asistencia");
                        persistencia.persistirInstancia(ultimaAsistencia);
                    }
                }

            } else {
                AsistenciaProfesor asistencia = new AsistenciaProfesor(claseProfesor, fecha, "ACTIVO");
                System.out.println("Primer registro de asistencia");
                persistencia.persistirInstancia(asistencia);
                claseProfesor.getAsistenciaProfesors().add(asistencia);
                persistencia.persistirInstancia(claseProfesor);
            }
        }
//        for(AsistenciaProfesor asistenciaBD : listaAsistenciasProfesor) {
//            if (claseProfesor.getIdclaseprofesor()== asistenciaBD.getClaseProfesor().getIdclaseprofesor()) {
//                if (asistenciaBD.getSalida() == null) {
//                    asistenciaBD.setSalida(new Date());
//                    actualizar = asistenciaBD;
//                    persistencia.persistirInstancia(actualizar);
//                    System.out.println("SALIDA ERA NULL, persisto");
//                    primeraVez = true;
//                    break;
//                } else {
//                    LocalTime hora = Instant.ofEpochMilli(asistenciaBD.getIngreso().getTime())
//                            .atZone(ZoneId.systemDefault())
//                            .toLocalTime();
//                    //si vino hace dos horas o mas
//                    LocalTime fechaConvertida = Instant.ofEpochMilli(fecha.getTime())
//                            .atZone(ZoneId.systemDefault())
//                            .toLocalTime();
//                    System.out.println(LocalTime.now()+" es la hora actual\n"+hora.plusHours(2)+" es la hora despues de dos horas");
//                    if (hora.plusHours(2).isBefore(fechaConvertida)) {
//                        AsistenciaProfesor asistencia = new AsistenciaProfesor(claseProfesor, fecha, "ACTIVO");
//                        System.out.println("PERSISTO UNA NUEVA ASISTENCIA PORQUE YA HABIA UNA VIEJA DE HACE DOS HORAS");
//                        persistencia.persistirInstancia(asistencia);
//                        primeraVez = true;
//                        break;
//                    }
//                }
//                primeraVez = true;
//            }
//        }
//        if(!primeraVez){
//            AsistenciaProfesor asistencia = new AsistenciaProfesor(claseProfesor, fecha, "ACTIVO");
//            System.out.println("Primer registro de asistencia");
//            persistencia.persistirInstancia(asistencia);
//            primeraVez = false;
//        }
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
