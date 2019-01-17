/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.Cuota;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Julien_W
 */
public class ControladorCuota {

    ControladorPersistencia miPersistencia;
    List<Cuota> listaCuotas;
    
    public ControladorCuota(ControladorPersistencia miPersistencia) throws Notificaciones {
        this.miPersistencia = miPersistencia;
        this.listaCuotas = miPersistencia.getCuotas();
    }

    public List<Cuota> getListaCuotasAVencer() {
        List<Cuota> cuotasAVencer = new ArrayList<>();
        LocalDate hoy = LocalDate.now(ZoneId.systemDefault());
        LocalDate semanaDespuesDeHoy = hoy.plusDays(7);
        for(Cuota unaCuota:this.listaCuotas){
            LocalDate vencimiento = Instant.ofEpochMilli(unaCuota.getVencimiento().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            if((vencimiento.isAfter(hoy) || vencimiento.isEqual(hoy)) &&
               (vencimiento.isBefore(semanaDespuesDeHoy) || vencimiento.isEqual(semanaDespuesDeHoy)))
                cuotasAVencer.add(unaCuota);
        }
        return cuotasAVencer;
    }

    public List<Cuota> getListaCuotasVencidas() {
        List<Cuota> cuotasVencidas = new ArrayList<>();
        LocalDate hoy = LocalDate.now(ZoneId.systemDefault());
        for(Cuota unaCuota:this.listaCuotas){
            LocalDate vencimiento =Instant.ofEpochMilli(unaCuota.getVencimiento().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            if(vencimiento.isBefore(hoy)){
                cuotasVencidas.add(unaCuota);
            }
        }
        return cuotasVencidas;
    }

    public void altaCuota(Cuota unaCuota) throws Notificaciones {
            miPersistencia.persistirInstancia(unaCuota);
    }

    public void actualizarEstadoCuota(Cuota cuota) throws Notificaciones{
        miPersistencia.persistirInstancia(cuota);
    }
    
}
