
import gimnasio.controlador.BaseDatos;
import gimnasio.controlador.ControladorPrincipal;
import gimnasio.controlador.ControladorRele;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.ModeloPrincipal;
import gimnasio.vista.MainMenu;
import gimnasio.vista.JFramePrueba;
import java.time.LocalDateTime;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.StringTokenizer;
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
//        try{
        ModeloPrincipal miModelo = new ModeloPrincipal();
        ControladorRele miRele = new ControladorRele();
        
        ControladorPrincipal miControlador = new ControladorPrincipal(miModelo);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainMenu menuPrincipal = new MainMenu(miControlador);
        ImageIcon image = new ImageIcon("src/gimnasio/imagenes/countryIcon.png");
        menuPrincipal.setIconImage(image.getImage());
        menuPrincipal.setLocationByPlatform(true);
        menuPrincipal.setVisible(true);
//        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
//            try {
//                miControlador.cerrarPersistencia();
//                LocalDateTime diahora = LocalDateTime.now();
//                String minuto = String.valueOf(diahora.getMinute());
//                String hora = String.valueOf(diahora.getHour());
//                String dia = String.valueOf(diahora.getDayOfMonth());
//                String mes = String.valueOf(diahora.getMonthValue());
//                String ano = String.valueOf(diahora.getYear());
//                String usuario = MainMenu.getUsuario().getNombreusuario();
//                FileWriter fw = new FileWriter("C:\\Users\\usuario_gym\\desktop\\Software\\dist\\registros\\" + ano + "_" + mes + "_"+dia+"_"+hora+"_"+minuto+"_"+usuario+".txt", true);
//                PrintWriter pw = new PrintWriter(fw);
//                e.printStackTrace(pw);
//                pw.close();
//                JOptionPane.showMessageDialog(null, "El sistema ha capturado un error para ser corregido.\nEl programa va a cerrarse, disculpe las molestias");
//                Thread.sleep(2000);
//                System.exit(0);
//            } catch (IOException | InterruptedException ex1) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//        });
//        }catch(Notificaciones | ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex){
//            try {
//                LocalDateTime diahora = LocalDateTime.now();
//                String minuto = String.valueOf(diahora.getMinute());
//                String hora = String.valueOf(diahora.getHour());
//                String dia = String.valueOf(diahora.getDayOfMonth());
//                String mes = String.valueOf(diahora.getMonthValue());
//                String ano = String.valueOf(diahora.getYear());
//                String usuario = MainMenu.getUsuario().getNombreusuario();
//                FileWriter fw = new FileWriter("C:\\Users\\usuario_gym\\desktop\\Software\\dist\\registros\\" + ano + "_" + mes + "_"+dia+"_"+hora+"_"+minuto+"_"+usuario+".txt", true);
//                PrintWriter pw = new PrintWriter (fw);
//                ex.printStackTrace(pw);
//                pw.close();
//                JOptionPane.showMessageDialog(null, "El sistema ha capturado un error para ser corregido.\nEl programa va a cerrarse, disculpe las molestias");
//                Thread.sleep(2000);
//                System.exit(0);
//            } catch (IOException | InterruptedException ex1) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//
//        }
    }
    
}