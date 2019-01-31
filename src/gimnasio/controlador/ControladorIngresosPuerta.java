/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.IngresosPuerta;
import gimnasio.modelo.Usuario;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Family
 */
public class ControladorIngresosPuerta {

    ControladorPersistencia miPersistencia;
    List<IngresosPuerta> listaIngresosPuerta;

    public ControladorIngresosPuerta(ControladorPersistencia miPersistencia) throws Notificaciones {
        this.miPersistencia = miPersistencia;
        this.listaIngresosPuerta = miPersistencia.getIngresosPuerta();
    }

    public List<IngresosPuerta> getListaIngresosPuerta(LocalDate desde, LocalDate hasta) {
        listaIngresosPuerta = miPersistencia.getIngresosPuerta();
        List<IngresosPuerta> retorno = new ArrayList<>();
        LocalDate fechaIngreso;
        if(desde.isEqual(hasta)){
            for(IngresosPuerta ip:listaIngresosPuerta){
                fechaIngreso = Instant.ofEpochMilli(ip.getHoraIngreso().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                if(fechaIngreso.isEqual(desde)){
                    retorno.add(ip);
                }
            }
        }else{
            for(IngresosPuerta ip:listaIngresosPuerta){
                fechaIngreso = Instant.ofEpochMilli(ip.getHoraIngreso().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                if(fechaIngreso.isAfter(desde.minusDays(1)) && fechaIngreso.isBefore(hasta.plusDays(1))){
                       retorno.add(ip);
                }
            }
        }
        Collections.sort(retorno);
        return retorno;
    }

    
    public void nuevoIngresoPuerta(Date ahora, Usuario unUsuario, DefaultTableModel modeloTabla, JTable tabla) throws Notificaciones {
        listaIngresosPuerta = miPersistencia.getIngresosPuerta();
        IngresosPuerta unAcceso = new IngresosPuerta(ahora, unUsuario);
        IngresosPuerta ingresoPuerta = null;

        boolean esEgreso = false;
        
        for (IngresosPuerta ingresoP : listaIngresosPuerta) {
            if (ingresoP.getUsuario().getIdusuario() == unUsuario.getIdusuario()) {
                if (ingresoP.getHoraEgreso() == null) {
                    ingresoP.setHoraEgreso(ahora);
                    miPersistencia.persistirInstancia(ingresoP);
                    ingresoPuerta = ingresoP;
                    esEgreso = true;
                    System.out.println("ES EGRESO");
                    break;
                }
            }
        }
        if (esEgreso == false) {
            miPersistencia.persistirInstancia(unAcceso);
            ingresoPuerta = unAcceso;
            System.out.println("ES INGRESO");
        }

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            Usuario miUsuario = (Usuario) modeloTabla.getValueAt(i, 0);
            if (ingresoPuerta != null && esEgreso) {
                if (miUsuario.getIdusuario() == ingresoPuerta.getUsuario().getIdusuario()) {
                    modeloTabla.removeRow(i);
                    modeloTabla.fireTableStructureChanged();
                    tabla.repaint();
                    break;
                }
            }
        }

    }

}
