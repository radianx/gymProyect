
import gimnasio.controlador.ControladorPrincipal;
import gimnasio.vista.MainMenu;
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
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        ControladorPrincipal miControlador = new ControladorPrincipal();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainMenu menuPrincipal = new MainMenu(miControlador);
        menuPrincipal.setLocationByPlatform(true);
        menuPrincipal.setVisible(true);
    }
    
}
