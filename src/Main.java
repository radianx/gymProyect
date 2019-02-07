
import gimnasio.controlador.BaseDatos;
import gimnasio.controlador.ControladorHuella;
import gimnasio.controlador.ControladorPrincipal;
import gimnasio.controlador.ControladorRele;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.ModeloPrincipal;
import gimnasio.vista.MainMenu;
import gimnasio.vista.JFramePrueba;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.geom.Rectangle2D;
import java.time.LocalDateTime;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
    static SplashScreen mySplash;                   // instantiated by JVM we use it to get graphics
    static Graphics2D splashGraphics;               // graphics context for overlay of the splash image
    static Rectangle2D.Double splashTextArea;       // area where we draw the text
    static Rectangle2D.Double splashProgressArea;   // area where we draw the progress bar
    static Font font;        
    
    private static void splashInit() {
        mySplash = SplashScreen.getSplashScreen();
           if (mySplash != null)
        {   // if there are any problems displaying the splash this will be null
            Dimension ssDim = mySplash.getSize();
            int height = ssDim.height;
            int width = ssDim.width;
            // stake out some area for our status information
            splashTextArea = new Rectangle2D.Double(15., height*0.88, width * .45, 32.);
            splashProgressArea = new Rectangle2D.Double(width * .55, height*.92, width*.4, 12 );

            // create the Graphics environment for drawing status info
            splashGraphics = mySplash.createGraphics();
            font = new Font("Dialog", Font.PLAIN, 14);
            splashGraphics.setFont(font);
            
            // initialize the status info
            splashText("Starting");
            splashProgress(0);
        }
    }
    /**
     * Display text in status area of Splash.  Note: no validation it will fit.
     * @param str - text to be displayed
     */
    public static void splashText(String str)
    {
        if (mySplash != null && mySplash.isVisible())
        {   // important to check here so no other methods need to know if there
            // really is a Splash being displayed

            // erase the last status text
            splashGraphics.setPaint(Color.LIGHT_GRAY);
            splashGraphics.fill(splashTextArea);

            // draw the text
            splashGraphics.setPaint(Color.BLACK);
            splashGraphics.drawString(str, (int)(splashTextArea.getX() + 10),(int)(splashTextArea.getY() + 15));

            // make sure it's displayed
            mySplash.update();
        }
    }
    /**
     * Display a (very) basic progress bar
     * @param pct how much of the progress bar to display 0-100
     */
    public static void splashProgress(int pct)
    {
        if (mySplash != null && mySplash.isVisible())
        {

            // Note: 3 colors are used here to demonstrate steps
            // erase the old one
            splashGraphics.setPaint(Color.LIGHT_GRAY);
            splashGraphics.fill(splashProgressArea);

            // draw an outline
            splashGraphics.setPaint(Color.BLUE);
            splashGraphics.draw(splashProgressArea);

            // Calculate the width corresponding to the correct percentage
            int x = (int) splashProgressArea.getMinX();
            int y = (int) splashProgressArea.getMinY();
            int wid = (int) splashProgressArea.getWidth();
            int hgt = (int) splashProgressArea.getHeight();

            int doneWidth = Math.round(pct*wid/100.f);
            doneWidth = Math.max(0, Math.min(doneWidth, wid-1));  // limit 0-width

            // fill the done part one pixel smaller than the outline
            splashGraphics.setPaint(Color.GREEN);
            splashGraphics.fillRect(x, y+1, doneWidth, hgt-1);

            // make sure it's displayed
            mySplash.update();
        }
    }

    
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
        try{
        splashInit();
        splashText("Inicializando Programa");
        splashProgress(10);
        ModeloPrincipal miModelo = new ModeloPrincipal();
        
//        ControladorRele miRele = new ControladorRele();
        
        splashText("Conectando con base de datos...");
        splashProgress(30);
        
        ControladorPrincipal miControlador = new ControladorPrincipal(miModelo);
        splashText("Verificando registros");
        splashProgress(70);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        MainMenu menuPrincipal = new MainMenu(miControlador);
        splashText("Finalizado. Bienvenido");
        splashProgress(100);
        if (mySplash != null)   // check if we really had a spash screen
            mySplash.close();
        
//        long delay = ChronoUnit.MILLIS.between(LocalTime.now(), LocalTime.of(23, 59, 59));
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.schedule(command, delay, TimeUnit.MILLISECONDS);
        
        ImageIcon image = new ImageIcon("src/gimnasio/imagenes/countryIcon.png");
        menuPrincipal.setIconImage(image.getImage());
        menuPrincipal.setLocationByPlatform(true);
        menuPrincipal.setVisible(true);
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
            try {
                LocalDateTime diahora = LocalDateTime.now();
                String minuto = String.valueOf(diahora.getMinute());
                String hora = String.valueOf(diahora.getHour());
                String dia = String.valueOf(diahora.getDayOfMonth());
                String mes = String.valueOf(diahora.getMonthValue());
                String ano = String.valueOf(diahora.getYear());
                String usuario = MainMenu.getUsuario().getNombreusuario();
                FileWriter fw = new FileWriter("C:\\Users\\usuario_gym\\desktop\\Software\\dist\\registros\\" + ano + "_" + mes + "_"+dia+"_"+hora+"_"+minuto+"_"+usuario+".txt", true);
                PrintWriter pw = new PrintWriter(fw);
                e.printStackTrace(pw);
                pw.close();
                JOptionPane.showMessageDialog(null, "El sistema ha capturado un error para ser corregido.\nEl programa va a cerrarse, disculpe las molestias");
                Thread.sleep(2000);
                System.exit(0);
            } catch (IOException | InterruptedException ex1) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
            }
            });
        }catch(Notificaciones | ClassNotFoundException | IllegalAccessException | IllegalStateException | InstantiationException | UnsupportedLookAndFeelException ex){
            try {
                LocalDateTime diahora = LocalDateTime.now();
                String minuto = String.valueOf(diahora.getMinute());
                String hora = String.valueOf(diahora.getHour());
                String dia = String.valueOf(diahora.getDayOfMonth());
                String mes = String.valueOf(diahora.getMonthValue());
                String ano = String.valueOf(diahora.getYear());
                String usuario = MainMenu.getUsuario().getNombreusuario();
                FileWriter fw = new FileWriter("C:\\Users\\usuario_gym\\desktop\\Software\\dist\\registros\\" + ano + "_" + mes + "_"+dia+"_"+hora+"_"+minuto+"_"+usuario+".txt", true);
                PrintWriter pw = new PrintWriter (fw);
                ex.printStackTrace(pw);
                pw.close();
                JOptionPane.showMessageDialog(null, "El sistema ha capturado un error para ser corregido.\nEl programa va a cerrarse, disculpe las molestias");
                Thread.sleep(2000);
                System.exit(0);
            } catch (IOException | InterruptedException ex1) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
    }
    
}