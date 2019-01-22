/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Movimiento;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Family
 */
public class ControladorMovimiento {

    ControladorPersistencia miPersistencia;
    List<Movimiento> listaMovimientos;
    
    public ControladorMovimiento(ControladorPersistencia miPersistencia) throws Notificaciones{
        this.miPersistencia = miPersistencia;
        listaMovimientos = miPersistencia.getMovimientos();
    }

    public List<Movimiento> getListaMovimientos() throws Notificaciones{
        listaMovimientos = miPersistencia.getMovimientos();
        return listaMovimientos;
    }

    public void altaMomiviento(Movimiento unMovimiento) throws Notificaciones{
        miPersistencia.persistirInstancia(unMovimiento);
        listaMovimientos.add(unMovimiento);
    }

    public List<Movimiento> getListaMovimientosDeHoy() throws Notificaciones{
        listaMovimientos = miPersistencia.getMovimientos();
        List<Movimiento> retorno = new ArrayList<>();
        for(Movimiento unMov:listaMovimientos){
            LocalDate fecha = LocalDate.now();
            LocalDate fechaMov = Instant.ofEpochMilli(unMov.getHora().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            if(fecha.isEqual(fechaMov)){
                retorno.add(unMov);
            }
        }
        return retorno;
    }

    public List<Movimiento> getListaMovimientosDe(LocalDate desde, LocalDate hasta) throws Notificaciones {
        listaMovimientos = miPersistencia.getMovimientos();
        Collections.sort(listaMovimientos);
        List<Movimiento> retorno = new ArrayList<>();
        for(Movimiento unMov:listaMovimientos){
            LocalDate fechaMov = Instant.ofEpochMilli(unMov.getHora().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            if(fechaMov.isAfter(desde.minusDays(1)) && fechaMov.isBefore(hasta.plusDays(1))){
                retorno.add(unMov);
            }
        }
        return retorno;
    }
    
    
}
