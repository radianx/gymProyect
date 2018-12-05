package gimnasio.vista;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import gimnasio.controlador.ControladorRele;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;

public class jDialogHuella extends javax.swing.JDialog {

    private DPFPCapture lector = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPVerification verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPFeatureSet featuresinscripcion;
    private DPFPFeatureSet featuresvertification;
    private DPFPTemplate planilla;
    private ControladorRele controlRele = new ControladorRele();
    private ByteArrayInputStream datosRetorno;
   
    public jDialogHuella(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        DefaultCaret caret = (DefaultCaret)this.txtArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        controlRele.start();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e){
                init();
                start();
            }
            
            @Override
            public void componentHidden(ComponentEvent e){
                
            }
        });
    }

    protected void init(){
        lector.addDataListener((DPFPDataEvent dpfpde) -> {
            procesarHuella(dpfpde.getSample());
        });
        
        lector.addReaderStatusListener(new DPFPReaderStatusListener(){
            @Override
            public void readerConnected(DPFPReaderStatusEvent dpfprs){
                txtArea.append("Lector de huellas conectado.\n");
            }
            @Override
            public void readerDisconnected(DPFPReaderStatusEvent dpfprs){
                txtArea.append("Lector de huellas desconectado.\n");
            }
        });
        
    }
    
    protected void procesarHuella(DPFPSample sample){
        //con las dos lineas siguientes muestro la huella en la pantalla
        Image image = DPFPGlobal.getSampleConversionFactory().createImage(sample);
        drawPicture(image);
        //con esta linea se pretende extraer una nueva huella para guardarla despues
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        //con esta linea le digo que voy a verificar la huella con otra
        featuresvertification = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
        
        if(featuresinscripcion != null){
            try{
                txtArea.append("\nHuella detectada.\n");
                reclutador.addFeatures(featuresinscripcion);
            } catch(DPFPImageQualityException ex){
                txtArea.append("\nError: " + ex.getMessage());
            } finally{
                EstadoHuellas(); //comprueba si se creo la plantilla con exito
                switch(reclutador.getTemplateStatus()){
                    case TEMPLATE_STATUS_READY:
                        setTemplate(reclutador.getTemplate());
                        break;
                    case TEMPLATE_STATUS_FAILED:
                        reclutador.clear();
                        stop();
                        EstadoHuellas();
                        setTemplate(null);
                        JOptionPane.showMessageDialog(this, "Error, intente nuevamente.");
                        start();
                        break;
                }
            }
        }
        
    }
    
    public void guardarHuella(){
        this.datosRetorno = new ByteArrayInputStream(planilla.serialize());
        Integer tamanoHuella=planilla.serialize().length;
        //pregunta el nombre de la persona a la cual corresponde dicha huella
 //       nombre = JOptionPane.showInputDialog("Nombre: ");
            
        JOptionPane.showMessageDialog(null,"Huella Guardada Correctamente");
            this.dispose();
    }
    
    public void verificarHuella() throws InterruptedException{
     
            //Crea una nueva plantilla a partir de la guardada en la base de datos
            DPFPTemplate referenceTemplate = planilla;
            //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
            //setTemplate(referenceTemplate);
            
            //Compara las caracteristicas de la huella recientemente capturada
            //con la plantilla guardada al usuario especifico en la base de datos
            DPFPVerificationResult result = verificador.verify(this.featuresvertification, getTemplate());
            
            if(result.isVerified()){
                JOptionPane.showMessageDialog(null, "La huella capturada coincide con la de ", "Verificacion de huella", JOptionPane.INFORMATION_MESSAGE);           
                controlRele.abrirPuerta();
            } else{
                JOptionPane.showMessageDialog(null, "No corresponde la huella con ", "Verificacion de huella", JOptionPane.ERROR_MESSAGE);
            } 
          
    }
    
    public void identificarHuella() throws IOException{
         //Crea una nueva plantilla a partir de la guardada en la base de datos
            DPFPTemplate referenceTemplate = planilla;
            //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
            //setTemplate(referenceTemplate);
            
    }
    
    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try{
            return extractor.createFeatureSet(sample, purpose);
        }catch(DPFPImageQualityException e){
            return null;
        }
    }
    
    
    public void drawPicture(Image image){
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bufferedImage.createGraphics();
        bGr.drawImage(image, 0, 0 , null);
        bGr.dispose();
        
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(180), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        bufferedImage = op.filter(bufferedImage, null);
        lblHuella.setIcon(new ImageIcon(image.getScaledInstance(lblHuella.getWidth(), lblHuella.getHeight(), Image.SCALE_DEFAULT)));
    }
    
    public void stop(){
        lector.stopCapture();
        txtArea.append("Se detuvo la captura de huellas\n");
    }
    
    public void EstadoHuellas(){
        txtArea.append("\nFalta verificar huella " + reclutador.getFeaturesNeeded() + " veces.");
    }
    
    public void setTemplate(DPFPTemplate template){
        DPFPTemplate old = this.planilla;
        this.planilla = template;
        firePropertyChange("template", old, template);
    }
    
    public DPFPTemplate getTemplate(){
        return planilla;
    }
    
    public void start(){
        lector.startCapture();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblHuella = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(500, 300));
        setMinimumSize(new java.awt.Dimension(200, 300));
        setPreferredSize(new java.awt.Dimension(200, 300));
        setSize(new java.awt.Dimension(200, 300));
        getContentPane().add(lblHuella, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        txtArea.setEditable(false);
        txtArea.setColumns(15);
        txtArea.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtArea.setRows(5);
        txtArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(txtArea);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarHuella();
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHuella;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables

    public ByteArrayInputStream showDialog(){
        this.setVisible(true);
        return this.datosRetorno;
    }
}
