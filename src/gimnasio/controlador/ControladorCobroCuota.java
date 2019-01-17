/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import java.util.List;
import gimnasio.modelo.CobroCuota;
import gimnasio.modelo.Cuota;
import gimnasio.herramientas.excepciones.Notificaciones;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
/**
 *
 * @author Family
 */
public class ControladorCobroCuota {

    ControladorPersistencia miPersistencia;
    List<CobroCuota> listaCobrosCuotas;
    
    public ControladorCobroCuota(ControladorPersistencia miPersistencia) throws Notificaciones{
        this.miPersistencia = miPersistencia;
        this.listaCobrosCuotas = miPersistencia.getCobroCuota();
    }

    public CobroCuota generarCobroCuota(Cuota cuota, Double abono, LocalDate fecha) throws Notificaciones{
        Date fechaCobro = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
        CobroCuota cobroCuota = new CobroCuota(cuota, abono, fechaCobro, "ACTIVO");
        miPersistencia.persistirInstancia(cobroCuota);
        return cobroCuota;
    }
    
}
