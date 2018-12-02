/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Family
 */
public class ControladorHuella{

    //Varible que permite iniciar el dispositivo de lector de huella conectado
    // con sus distintos metodos.
    private DPFPCapture lector = DPFPGlobal.getCaptureFactory().createCapture();
    
    //Varible que permite establecer las capturas de la huellas, para determina sus caracteristicas
    // y poder estimar la creacion de un template de la huella para luego poder guardarla
    private DPFPEnrollment reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    
    //Esta variable tambien captura una huella del lector y crea sus caracteristcas para auntetificarla
    // o verificarla con alguna guarda en la BD 
    private DPFPVerification verificador = DPFPGlobal.getVerificationFactory().createVerification();
    
    //Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
    // necesarias de la huella si no ha ocurrido ningun problema
    private DPFPTemplate planilla;
    
    //Esta es una variable comun y aburrida que indica el estado, no tiene nada especial
    private String estado;

    public static String TEMPLATE_PROPERTY = "template";

    public DPFPCapture getLector() {
        return lector;
    }

    public void setLector(DPFPCapture lector) {
        this.lector = lector;
    }

    public DPFPEnrollment getReclutador() {
        return reclutador;
    }

    public void setReclutador(DPFPEnrollment reclutador) {
        this.reclutador = reclutador;
    }

    public DPFPVerification getVerificador() {
        return verificador;
    }

    public void setVerificador(DPFPVerification verificador) {
        this.verificador = verificador;
    }

    public DPFPTemplate getPlanilla() {
        return planilla;
    }

    public void setPlanilla(DPFPTemplate planilla) {
        this.planilla = planilla;
    }

    
    
    public void Iniciar() {
        lector.addDataListener(new DPFPDataAdapter() {
            public void dataAquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(() -> {
                    EnviarTexto("La huella digital se capturo con exito.");
                    try {
                        ProcesarCaptura(e.getSample());
                    } catch (Exception ex) {
                        System.err.println(Arrays.toString(ex.getStackTrace()));
                    }
                });
            }

        });

        lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    EnviarTexto("Sensor de huellas digitalPersona U.are.U 4500 conectado.");
                });
            }

            ;
           @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    EnviarTexto("El sensor de huellas se ha desconectado o desactivado");
                });
            }
        });
        lector.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {

            }
        });
        lector.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent e) {
                SwingUtilities.invokeLater(() -> {
                    EnviarTexto("Error: " + e.getError());
                });
            }
        });
    }
    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresvertification;

    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

    public Image CrearImagenHuella(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    private void EnviarTexto(String unString) {
        this.estado = unString;
    }

    public String getEstado() {
        return this.estado;
    }
    
//   -----METODO QUE DIBUJA UNA HUELLA EN PANTALLA----
//    public void DibujarHuella(Image image){
//        this.lblHuella.setIcon(new ImageIcon(
//            image.getScaledInstance(this.lblHuella.getWidth(), lblHuella.getHeight(), Image.SCALE_DEFAULT)));
//        repaint();
//    }

    public void EstadoHuellas(){
        EnviarTexto("Muestra de Huellas Necesarias para Guardar Template " + reclutador.getFeaturesNeeded());
    }
    
    public void start(){
        lector.startCapture();
        EnviarTexto("Utilizando el Lector de Huella Dactilar");
    }
    
    public void stop(){
        lector.stopCapture();
        EnviarTexto("No se esta usando el lector de Huellas Dactular");
    }
  
    public DPFPTemplate getTemplate(){
        return planilla;
    }
    
    public void setTemplate(DPFPTemplate template){
        DPFPTemplate old = this.planilla;
        this.planilla = template;
 //       firePropertyChange(TEMPLATE_PROPERTY, old, template); <--- Esta linea es para control del componente visual
    }
    
    public void ProcesarCaptura(DPFPSample sample) throws Exception{
    // Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
    // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
        featuresvertification = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
    
    // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno    
        if(featuresinscripcion != null){
            try{
                System.out.println("Las caracteristicas de la huella han sido creadas");
                
                reclutador.addFeatures(featuresinscripcion); // Agregar las caracteristicas de la huella a la plantilla a crear
                
                Image image = CrearImagenHuella(sample);      
            } catch(DPFPImageQualityException ex){
                System.out.println("Error: "+ex.getMessage());
            } finally{
                EstadoHuellas();
                //comprueba si la plantilla se creo
                switch(reclutador.getTemplateStatus()){
                    case TEMPLATE_STATUS_READY://informe de exito y detiene la captura de huellas
                        stop();
                        setTemplate(reclutador.getTemplate());
                        EnviarTexto("La plantilla de la huella ha sido registrada");
                        break;
                    
                    case TEMPLATE_STATUS_FAILED://informe de fallo y luego reinicio
                        reclutador.clear();
                        stop();
                        EstadoHuellas();
                        setTemplate(null);
                        start();
                        break;
                }
            }
        }
    }
    
    
 /*
  * Guarda los datos de la huella digital actual en la base de datos
  */    
    public void guardarHuella(){
    // Obtiene los datos del template de la huella actual
        ByteArrayInputStream planilla = new ByteArrayInputStream(this.planilla.serialize());
        Integer tamanoHuella = this.planilla.serialize().length;
    //pregunta el nombre de la persona a la cual corresponde dicha huella
        String nombre = JOptionPane.showInputDialog("Nombre: ");
//        try{
//            //Aqui tiene que ir la persistencia
//        // un ejemplo es el siguiente para mysql
//        Connection c=con.conectar(); //establece la conexion con la BD
//        PreparedStatement updatePersonal = c.prepareStatement("UPDATE personal set huella=? WHERE clave=?");
//        //updatePersonal.setBinaryStream(1, datosHuella,tamañoHuella);
//        updatePersonal.setBinaryStream(1, datosHuella,tamañoHuella);
//        updatePersonal.setString(2,nombre);
//        updatePersonal.execute();
//        updatePersonal.close();
//        con.desconectar();
//        
//        System.out.println("Huella Guardada Correctamente");
//        }catch (SQLException ex) {
//     //Si ocurre un error lo indica en la consola
//     System.err.println("Error al guardar los datos de la huella.");
//     }finally{
//     con.desconectar();
//    }
    }
    
    public void verificarHuella(String nom){
//             try {
//    //Establece los valores para la sentencia SQL
//    Connection c=con.conectar();
//    //Obtiene la plantilla correspondiente a la persona indicada
//    PreparedStatement verificarStmt = c.prepareStatement("SELECT huella FROM personal WHERE nombre=?");
//    verificarStmt.setString(1,nom);
//    ResultSet rs = verificarStmt.executeQuery();
//
//    //Si se encuentra el nombre en la base de datos
//    if (rs.next()){
//    //Lee la plantilla de la base de datos
//      byte templateBuffer[] = rs.getBytes("huella");
            //Crea una nueva plantilla a partir de la guardada en la base de datos(Por el momento crea una no mas)
            DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate();//.createTemplate(templateBuffer);
            //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
            setTemplate(referenceTemplate);
            
            //Compara las caracteristicas de la huella recientemente capturada
            //con la plantilla guardada al usuario especifico en la base de datos
            DPFPVerificationResult result = verificador.verify(this.featuresvertification, getTemplate());
            
            if(result.isVerified()){
                System.out.println("La huella capturada coincide");           
            } else{
                System.out.println("No corresponde la huella");
            }     
    }
    
    public void identificarHuella(){
        //try{
        //Conexion con base de datos, preparar sentencia sql o blabla
        //Obtenemos ---TODAS---- las huellas de la BD
        //normalmente guardandolas en un ResultSet, ej:
        //       Connection c=con.conectar();
        //        PreparedStatement identificarStmt = c.prepareStatement("SELECT nombre,huella FROM personal");
        //        ResultSet rs = identificarStmt.executeQuery();
        //Se recorre la lista de la base de datos
        //
//        while (rs.next()) {
//            //Lee la plantilla de la base de datos
//            byte templateBuffer[] = rs.getBytes("huella");
//            String nombre = rs.getString("nombre");
//            //Crea una nueva plantilla a partir de la guardada en la base de datos
//            DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
//            //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
//            setTemplate(referenceTemplate);
//            // Compara las caracteriticas de la huella recientemente capturda con la
//            // alguna plantilla guardada en la base de datos que coincide con ese tipo
//            DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());
//            //compara las plantilas (actual vs bd)
//            //Si encuentra correspondencia dibuja el mapa
//            //e indica el nombre de la persona que coincidió.
//            if (result.isVerified()) {
//                //crea la imagen de los datos guardado de las huellas guardadas en la base de datos
//                JOptionPane.showMessageDialog(null, "Las huella capturada es de " + nombre, "Verificacion de Huella", JOptionPane.INFORMATION_MESSAGE);
//                return; <---- Esto es un break del while, seguro hay una mejor forma
//            }
//        }
//        //Si no encuentra alguna huella correspondiente al nombre lo indica con un mensaje
//        JOptionPane.showMessageDialog(null, "No existe ningún registro que coincida con la huella", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
//        setTemplate(null);
//    }
//    catch (SQLException e
//
//    
//        ) {
//       //Si ocurre un error lo indica en la consola
//       System.err.println("Error al identificar huella dactilar." + e.getMessage());
//    }
//
//    
//        finally{
//       con.desconectar();
//    }
    }
}
