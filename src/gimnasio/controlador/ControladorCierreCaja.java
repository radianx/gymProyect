/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import javax.swing.JOptionPane;

/**
 *
 * @author Family
 */
public class ControladorCierreCaja implements Runnable {
    ControladorPrincipal miControlador;
    ControladorCaja controladorCaja;

    public ControladorCierreCaja(ControladorPrincipal miControlador, ControladorCaja controladorCaja) {
        this.miControlador = miControlador;
        this.controladorCaja = controladorCaja;
    }

    @Override
    public void run() {
        try{
            if (controladorCaja.hayCajaAbiertaHoy()) {
                controladorCaja.cerrarCaja();
            }
        }catch(Notificaciones ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
}
