/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Cajadiaria;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Family
 */
public class ControladorCaja {

    private ControladorPersistencia miPersistencia;
    private List<Cajadiaria> listaCajaDiaria;
    
    public ControladorCaja(ControladorPersistencia miPersistencia) throws Notificaciones {
        this.miPersistencia = miPersistencia;
        listaCajaDiaria = this.miPersistencia.getCajaDiarias();
    }
    
    public void altaCaja(Cajadiaria unaCaja) throws Notificaciones{
        miPersistencia.persistirInstancia(unaCaja);
    }

    public boolean hayCajaAbiertaHoy() throws Notificaciones{
        boolean retorno = false;
        listaCajaDiaria = miPersistencia.getCajaDiarias();
        LocalDate fecha = LocalDate.now();
        LocalDate fechaCaja;
        for(Cajadiaria caja:listaCajaDiaria){
            fechaCaja = ((java.sql.Date) caja.getApertura()).toLocalDate();
            if(fecha.isEqual(fechaCaja)){
                retorno = true;
                break;
            }
        }
        return retorno;
    }

    public void cerrarCaja() throws Notificaciones{
        listaCajaDiaria = miPersistencia.getCajaDiarias();
        LocalDate fecha = LocalDate.now();
        LocalDate fechaCaja;
        for(Cajadiaria caja:listaCajaDiaria){
            fechaCaja = ((java.sql.Date) caja.getApertura()).toLocalDate();
            if(fecha.isEqual(fechaCaja)){
                caja.setCierre(new Date());
                miPersistencia.persistirInstancia(caja);
                break;
            }
        }
    }

    /*
    * retorna la instancia de caja del dia, tira Notificaciones si 
    * no hay caja abierta hoy
    */
    public Cajadiaria dameCaja() throws Notificaciones{
        Cajadiaria retorno = null;
        listaCajaDiaria = miPersistencia.getCajaDiarias();
        LocalDate fecha = LocalDate.now();
        LocalDate fechaCaja;
        for(Cajadiaria caja:listaCajaDiaria){
            
            fechaCaja = Instant.ofEpochMilli(caja.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            if(fecha.isEqual(fechaCaja)){
                retorno = caja;
                break;
            }
        }
        if(retorno == null){
            throw new Notificaciones("No hay caja abierta hoy.");
        }
        return retorno;
    }
    
}
