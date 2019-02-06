/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import com.sun.jna.Native;
import gimnasio.interfaces.GestorRele;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Family
 */
public class ControladorRele implements Runnable{

    private final GestorRele gestorRele = (GestorRele) Native.load("usb_relay_device", GestorRele.class);
    private int res = gestorRele.usb_relay_init();
    private final GestorRele.usb_relay_device_info.ByReference disp = gestorRele.usb_relay_device_enumerate();
    private final int handle;

    public void abrirPuerta() throws InterruptedException {
        res = gestorRele.usb_relay_device_open_one_relay_channel(handle, 1);
        System.out.println("Abriendo puerta...");
        Thread.sleep(3000);
        res = gestorRele.usb_relay_device_close_one_relay_channel(handle, 1);
        System.out.println("Puerta Abierta.");
    }

    public ControladorRele() {
        this.handle = gestorRele.usb_relay_device_open(disp);
    }

    @Override
    public void run() {
        try {
            abrirPuerta();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    
}
