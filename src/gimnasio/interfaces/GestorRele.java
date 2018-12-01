/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.interfaces;

import com.sun.jna.Library;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public interface GestorRele extends Library{
    public static class usb_relay_device_info extends Structure{
        @Override
        protected List getFieldOrder(){
            return Arrays.asList(new String[]{"serial_number", "device_path", "type", "next"});
            
        }        
    
    public static class ByReference extends usb_relay_device_info implements Structure.ByReference{}
    
    public String serial_number;
    public String device_path;
    public int type;
    public usb_relay_device_info.ByReference next;
    }
    
    int usb_relay_init();
    int usb_relay_exit();
    usb_relay_device_info.ByReference usb_relay_device_enumerate();
    int usb_relay_device_open_one_relay_channel(int handle, int index);
    int usb_relay_device_close_one_relay_channel(int handle, int index);
    int usb_relay_device_open(usb_relay_device_info.ByReference dispositivo);
    void usb_relay_device_close(int handle);
}
