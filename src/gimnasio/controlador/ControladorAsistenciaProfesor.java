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
    
    public void altaAsistenciaProfesor(ClaseProfesor claseProfesor, Date fecha) throws Notificaciones{
        this.listaAsistenciasProfesor = persistencia.getAsistenciaProfesor();
        persistencia.actualizarInstancias();
        //ver si no existe una Asistencia entrada dentro del rango de 2 horas en el pasado
        //y si existe pero esta fuera del rango entonces marcar salida
        AsistenciaProfesor actualizar = null;
        boolean primeraVez = true;
        for(AsistenciaProfesor asistenciaBD : listaAsistenciasProfesor) {
            if (claseProfesor.getIdclaseprofesor()== asistenciaBD.getClaseProfesor().getIdclaseprofesor()) {
                if (asistenciaBD.getSalida() == null) {
                    asistenciaBD.setSalida(new Date());
                    actualizar = asistenciaBD;
                } else {
                    LocalTime hora = Instant.ofEpochMilli(asistenciaBD.getIngreso().getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalTime();
                    //si vino hace dos horas
                    if (hora.plusHours(2).isAfter(LocalTime.now())) {
                        AsistenciaProfesor asistencia = new AsistenciaProfesor(claseProfesor, fecha, "ACTIVO");
                        persistencia.persistirInstancia(asistencia);
                    }
                }
            }else{
                AsistenciaProfesor asistencia = new AsistenciaProfesor(claseProfesor, fecha, "ACTIVO");
                persistencia.persistirInstancia(asistencia);
            }
            primeraVez = false;
        }
        if(primeraVez){
            AsistenciaProfesor asistencia = new AsistenciaProfesor(claseProfesor, fecha, "ACTIVO");
            persistencia.persistirInstancia(asistencia);
        }
        
        if(actualizar !=null){
            persistencia.persistirInstancia(actualizar);
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
