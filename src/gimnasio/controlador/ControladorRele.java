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
public class ControladorRele {

    static GestorRele gestorRele = (GestorRele) Native.load("usb_relay_device", GestorRele.class);
    static int res = gestorRele.usb_relay_init();
    static GestorRele.usb_relay_device_info.ByReference disp = gestorRele.usb_relay_device_enumerate();
    static int handle = gestorRele.usb_relay_device_open(disp);

    public static  void abrirPuerta() throws InterruptedException {
        res = gestorRele.usb_relay_device_open_one_relay_channel(handle, 1);
        Thread.sleep(3000);
        res = gestorRele.usb_relay_device_close_one_relay_channel(handle, 1);
    }

    
}
