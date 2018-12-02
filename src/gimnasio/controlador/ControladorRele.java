/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import com.sun.jna.Native;
import gimnasio.interfaces.GestorRele;

/**
 *
 * @author Family
 */
public class ControladorRele extends Thread {

    GestorRele gestorRele = (GestorRele) Native.load("usb_relay_device", GestorRele.class);
    int res = gestorRele.usb_relay_init();
    GestorRele.usb_relay_device_info.ByReference disp = gestorRele.usb_relay_device_enumerate();
    int handle = gestorRele.usb_relay_device_open(disp);
    boolean bandera = false;

    public ControladorRele() {
    }

    public void abrirPuerta() throws InterruptedException {
        res = gestorRele.usb_relay_device_open_one_relay_channel(handle, 1);
        Thread.sleep(3000);
        res = gestorRele.usb_relay_device_close_one_relay_channel(handle, 1);
    }
}