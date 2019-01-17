
import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.ModeloPrincipal;
import gimnasio.vista.MainMenu;
import java.time.Instant;
import java.util.Date;
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
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, Notificaciones {
        ModeloPrincipal miModelo = new ModeloPrincipal();
        ControladorPrincipal miControlador = new ControladorPrincipal(miModelo);
//        ClaseProfesor clase = miControlador.getListaClaseProfesor().get(0);
//        Alumno alumno = miControlador.getListaAlumnos().get(0);
//        Date alta = Date.from(Instant.now().minusSeconds(864000));
//        Date vence = Date.from(Instant.now().minusSeconds(84000));
//        Cuota cuota = new Cuota(alumno, clase, 0.0, alta, vence, "GENERADO");
//        try{
//            miControlador.altaCuota(cuota);
//        }catch(Notificaciones ex){
//            
//        }
        
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainMenu menuPrincipal = new MainMenu(miControlador);
        menuPrincipal.setLocationByPlatform(true);
        menuPrincipal.setVisible(true);
    }
    
}
