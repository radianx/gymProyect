/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import com.github.lgooddatepicker.components.DatePickerSettings;
import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.ClaseAlumno;
import gimnasio.modelo.Cuota;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Family
 */
public class jDialogCuota extends javax.swing.JDialog {

    ControladorPrincipal miControlador;
    Alumno alumno;
    DefaultTableModel modeloTabla;
    /**
     * Creates new form jDialogCuota
     */
    public jDialogCuota(java.awt.Frame parent, boolean modal, ControladorPrincipal controlador, Alumno unAlumno){
        super(parent, modal);
        miControlador = controlador;
        alumno = unAlumno;
        initComponents();
        this.txtAlumno.setText(alumno.getNombrealumno() + " " + alumno.getApellidoalumno());
        
        Locale locale = new Locale("es", "ES");
        DatePickerSettings settings = new DatePickerSettings(locale);
        settings.setFormatForDatesCommonEra("dd/MM/yyyy");
        settings.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");
        datePicker1.setSettings(settings);
        LocalDate fecha;
        try {
            Cuota cuota = unAlumno.getUltimaCuota();
            Date fechaVencimiento = cuota.getVencimiento();
            fecha = Instant.ofEpochMilli(fechaVencimiento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        }catch(Notificaciones ex){
            fecha = LocalDate.now();
        }
        this.datePicker1.setDate(fecha);
        cargarTabla(alumno, true);
    }
    
    
    
    
    public jDialogCuota(java.awt.Frame parent, boolean modal, ControladorPrincipal controlador, Alumno unAlumno, Cuota cuota, Double monto, boolean mostrarTodas){
        super(parent, modal);
        miControlador = controlador;
        alumno = unAlumno;
        initComponents();
        this.txtAlumno.setText(alumno.getNombrealumno() + " " + alumno.getApellidoalumno());
        if(monto!=null){
            this.txtMonto.setText(String.valueOf(monto));
        }
        Locale locale = new Locale("es", "ES");
        DatePickerSettings settings = new DatePickerSettings(locale);
        settings.setFormatForDatesCommonEra("dd/MM/yyyy");
        settings.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");
        datePicker1.setSettings(settings);
        LocalDate fecha;
//            cuota = unAlumno.getUltimaCuota();
        if(cuota!=null){    Date fechaVencimiento = cuota.getVencimiento();
            fecha = Instant.ofEpochMilli(fechaVencimiento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        this.datePicker1.setDate(fecha);
        }else{
            datePicker1.setDateToToday();
        }
        cargarTabla(alumno, mostrarTodas);
    }

    public void cargarTabla(Alumno unAlumno, boolean mostrarTodas){
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Clase");
        modeloTabla.addColumn("Precio");
        Object[] fila = new Object[2];
        try {
            List<ClaseAlumno>noMostrar = new ArrayList<>();
            Set<ClaseAlumno>setAlumno = unAlumno.getClaseAlumnos();
            List<ClaseAlumno>listaClasesDelAlumno = new ArrayList<>();
            listaClasesDelAlumno.addAll(setAlumno);
            if(mostrarTodas){
                for(ClaseAlumno claseAlu: unAlumno.getClaseAlumnos()){
                    fila[0] = claseAlu;
                    fila[1] = claseAlu.getPrecio();
                    modeloTabla.addRow(fila);
                }
            }else{
            for (Cuota cuota : miControlador.getCuotasDeAlumno(unAlumno)) {
                    for (ClaseAlumno claseAlu : unAlumno.getClaseAlumnos()) {
                        if (cuota.getClaseProfesor().getIdclaseprofesor() == claseAlu.getClaseProfesor().getIdclaseprofesor()
                                && cuota.getEstado().equalsIgnoreCase("GENERADO")) {
                            noMostrar.add(claseAlu);
                            break;
                        }
                    }
                }
                listaClasesDelAlumno.removeAll(noMostrar);
                for (ClaseAlumno claseAlu : listaClasesDelAlumno) {
                    fila[0] = claseAlu;
                    fila[1] = claseAlu.getPrecio();
                    modeloTabla.addRow(fila);
                }
            }
        } catch (Notificaciones ex) {
            ex.printStackTrace();
        }
        this.tablaClasesAlumno.setModel(modeloTabla);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAlumno = new javax.swing.JTextField();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClasesAlumno = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NUEVA CUOTA");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Alumno:");

        txtAlumno.setEditable(false);

        jLabel2.setText("Fecha:");

        jLabel3.setText("Monto:");

        txtMonto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMonto.setForeground(new java.awt.Color(0, 0, 255));
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        tablaClasesAlumno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaClasesAlumno);

        jLabel4.setText("Clases del Alumno:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtMonto)
                    .addComponent(datePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped

    }//GEN-LAST:event_txtMontoKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(!this.tablaClasesAlumno.getSelectionModel().isSelectionEmpty()){
            ClaseAlumno claseAlu = (ClaseAlumno) tablaClasesAlumno.getValueAt(tablaClasesAlumno.getSelectedRow(),0);
            Double monto = Double.valueOf(this.txtMonto.getText());
            Date fecha = Date.from(this.datePicker1.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            LocalDate vence = datePicker1.getDate().plusMonths(1).with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            Date vencimiento = Date.from(vence.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Cuota unaCuota = new Cuota(alumno,claseAlu.getClaseProfesor(),monto,fecha,vencimiento, "GENERADO");
            
            alumno.getCuotas().add(unaCuota);
            try{
                miControlador.altaCuota(unaCuota);
            }catch(Notificaciones ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
                ex.printStackTrace();
            }
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar la clase para generar una cuota.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyReleased
        try{
            double numero = Double.valueOf(this.txtMonto.getText());
        }catch(NumberFormatException ex){  
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_txtMontoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClasesAlumno;
    private javax.swing.JTextField txtAlumno;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables

}
