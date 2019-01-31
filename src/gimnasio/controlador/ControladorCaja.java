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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
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

    /*
    * Pregunta si se abrio la caja hoy 2 veces
    */
    public boolean hayCajaAbiertaHoy() throws Notificaciones{
        boolean retorno = false;
        listaCajaDiaria = miPersistencia.getCajaDiarias();
        for(Cajadiaria caja:listaCajaDiaria){
            if(caja.getCierre()==null){
                retorno = true;
                break;
            }
        }
        return retorno;
    }

    public void cerrarCaja() throws Notificaciones{
        listaCajaDiaria = miPersistencia.getCajaDiarias();
        Collections.sort(listaCajaDiaria);
        Date horarioCierre = Date.from(LocalDateTime.now()
                    .atZone(ZoneId.systemDefault()).toInstant());;
        int i = listaCajaDiaria.size()-1;
        Cajadiaria caja = listaCajaDiaria.get(i);
        caja.setCierre(horarioCierre);
        miPersistencia.persistirInstancia(caja);
    }

    /*
    * retorna la instancia de caja del dia, tira Notificaciones si 
    * no hay caja abierta hoy
    */
    
    public List<Cajadiaria> dameCajas(LocalDate desde, LocalDate hasta) throws Notificaciones{
        List<Cajadiaria> cajas = new ArrayList<>();
        listaCajaDiaria = miPersistencia.getCajaDiarias();
        LocalDate fechaCaja = null;
        for(Cajadiaria caja:listaCajaDiaria){
            fechaCaja = Instant.ofEpochMilli(caja.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            if(fechaCaja.isEqual(desde) 
                    || (fechaCaja.isAfter(desde) && fechaCaja.isBefore(hasta)) 
                    || fechaCaja.isEqual(hasta)){
                cajas.add(caja);
            }
        }
        Collections.sort(cajas);
        return cajas;
    }
    
    public List<Cajadiaria> dameCaja() throws Notificaciones{
        List<Cajadiaria> cajas = new ArrayList<>();
        listaCajaDiaria = miPersistencia.getCajaDiarias();
        LocalDate hoy = LocalDate.now();
        Collections.sort(listaCajaDiaria);
        LocalDate diaCaja;
        for(Cajadiaria caja:listaCajaDiaria){
            diaCaja = Instant.ofEpochMilli(caja.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            if(diaCaja.isEqual(hoy)){
                cajas.add(caja);
            }
        }
        
        Collections.reverse(cajas);
        return cajas;
    }

    public Cajadiaria dameCajaActual() throws Notificaciones {
        listaCajaDiaria = miPersistencia.getCajaDiarias();
        Collections.sort(listaCajaDiaria);
        return listaCajaDiaria.get(listaCajaDiaria.size()-1);
    }

    public void cerrarTodasLasCajas() throws Notificaciones{
        listaCajaDiaria = miPersistencia.getCajaDiarias();
        for(Cajadiaria unaCaja:listaCajaDiaria){
            if(unaCaja.getCierre()==null){
                LocalDate fecha = Instant.ofEpochMilli(unaCaja.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                Instant instante = Instant.from(fecha.atTime(23, 59,59).atZone(ZoneId.systemDefault()));
                Date horaCierre = Date.from(instante);
                unaCaja.setCierre(horaCierre);
                miPersistencia.persistirInstancia(unaCaja);
            }
        }
    }

    public List<Cajadiaria> dameCajasSinCerrar() {
        List<Cajadiaria> retorno = new ArrayList<>();
        for(Cajadiaria unaCaja:listaCajaDiaria){
            if(unaCaja.getCierre()==null){
                retorno.add(unaCaja);
            }
        }
        return retorno;
    }
    
}
