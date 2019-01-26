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
        LocalDate fecha = LocalDate.now();
        LocalDate fechaCaja;
        int conteo = 0;
        for(Cajadiaria caja:listaCajaDiaria){
            if(caja.getApertura()!=null){
                fechaCaja = Instant.ofEpochMilli(caja.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

                if (fecha.isEqual(fechaCaja)) {
                    conteo += 1;
                }
                if (conteo > 1) {
                    retorno = true;
                    break;
                }
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
        return cajas;
    }
    
    public List<Cajadiaria> dameCaja() throws Notificaciones{
        List<Cajadiaria> cajas = new ArrayList<>();
        listaCajaDiaria = miPersistencia.getCajaDiarias();
        LocalDate hoy = LocalDate.now();
        Cajadiaria caja1 = listaCajaDiaria.get(listaCajaDiaria.size()-1);
        if(caja1.getApertura()!=null){
            LocalDate fechaCaja1 = Instant.ofEpochMilli(caja1.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            if(fechaCaja1.isEqual(hoy)){
            cajas.add(caja1);
            }
        }

        
        if(listaCajaDiaria.size()>1){
            Cajadiaria caja2 = listaCajaDiaria.get(listaCajaDiaria.size() - 2);
            if (caja2.getApertura() != null) {
                LocalDate fechaCaja2 = Instant.ofEpochMilli(caja2.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                if (fechaCaja2.isEqual(hoy)) {
                    cajas.add(caja2);
                }
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
    
}
