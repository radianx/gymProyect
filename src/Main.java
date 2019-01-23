
import gimnasio.controlador.BaseDatos;
import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.ModeloPrincipal;
import gimnasio.vista.MainMenu;
import gimnasio.vista.JFramePrueba;
import gimnasio.controlador.ControladorPersistencia;
import java.time.Instant;
import java.util.Date;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Family
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws javax.swing.UnsupportedLookAndFeelException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    BaseDatos unaBaseDatos = new BaseDatos();
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, Notificaciones {
        //pruebaInicio();
        ModeloPrincipal miModelo = new ModeloPrincipal();
        ControladorPrincipal miControlador = new ControladorPrincipal(miModelo);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainMenu menuPrincipal = new MainMenu(miControlador);
        ImageIcon image = new ImageIcon("src/gimnasio/imagenes/countryIcon.png");
        menuPrincipal.setIconImage(image.getImage());
        menuPrincipal.setLocationByPlatform(true);
        menuPrincipal.setVisible(true);
    }
    
    public static void pruebaInicio() {
        String[] opciones = {"SI", "NO"};
        String[] opcionesBD = {"NUEVA", "EXISTENTE", "NO"};
        int seleccion1 = JOptionPane.showOptionDialog(null, "¿Es la primera vez que inicia el sistema?", "Seleccione una opcion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        if (seleccion1 == 0) {
            int seleccion2 = JOptionPane.showOptionDialog(null, "¿Desea configurar una Base de Datos?", "Seleccione una opcion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesBD, opcionesBD[0]);
            if (seleccion2 == 0) {
                int seleccion3 = JOptionPane.showOptionDialog(null, "Esta Opcion borrara cualquier base de datos previa\n¿Confirma la seleccion?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
                if (seleccion3 == 0) {
                    BaseDatos bd = new BaseDatos();
                    try {
                        bd.restaurarDataBase();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al cargar la base de datos "+ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
            if (seleccion2 == 1) {
                JFramePrueba seleccion = new JFramePrueba();
                seleccion.setVisible(true);
            }
        }
    }

}

