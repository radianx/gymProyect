/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.SaldoCuota;
import java.util.List;
import gimnasio.herramientas.excepciones.Notificaciones;

/**
 *
 * @author Family
 */
public class ControladorSaldoCuota {
    
    ControladorPersistencia persistencia;
    List<SaldoCuota> listaSaldosCuotas;
    
    public ControladorSaldoCuota(ControladorPersistencia miPersistencia) throws Notificaciones{
        this.persistencia = miPersistencia;
        listaSaldosCuotas = persistencia.getSaldoCuota();
    }

    public void guardarSaldo(SaldoCuota saldoAnterior) throws Notificaciones{
        this.persistencia.persistirInstancia(saldoAnterior);
    }
    
}
