/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import com.github.lgooddatepicker.components.DatePickerSettings;
import gimnasio.controlador.ControladorPrincipal;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.CobroCuota;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.SaldoCuota;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.ClaseAlumno;
import gimnasio.modelo.ClaseProfesor;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Family
 */
public class jInternalCobro extends javax.swing.JInternalFrame {

    DefaultTableModel modeloTabla;
    Alumno elAlumno;
    ControladorPrincipal controlador;
    SaldoCuota saldoAnterior;
    Double total;
    Double nuevoSaldo;
    Cuota cuota;

    /**
     * Creates new form jInternalCobro
     */
    public jInternalCobro(ControladorPrincipal miControlador, Alumno unAlumno) {
        this.controlador = miControlador;
        elAlumno = unAlumno;
        initComponents();
        Locale locale = new Locale("es", "ES");
        DatePickerSettings settings = new DatePickerSettings(locale);
        settings.setFormatForDatesCommonEra("dd/MM/yyyy");
        settings.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");
        datePicker.setSettings(settings);
        datePicker.setDateToToday();
        this.txtNombreAlumno.setText(elAlumno.getNombrealumno() + " " + elAlumno.getApellidoalumno());
        try{
            cargarTablaCuotas(elAlumno);
        }catch(Notificaciones ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void cargarTablaCuotas(Alumno unAlumno) throws Notificaciones{
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Clase");
        modeloTabla.addColumn("Fecha Emision");
        modeloTabla.addColumn("Vencimiento");
        modeloTabla.addColumn("Monto");
        this.tablaCuotasDeAlumno.setModel(modeloTabla);
        Object[]fila = new Object[4];
        total = 0.0;
        for(Cuota cuotaAlumno:controlador.getCuotasDeAlumno(unAlumno)){
            if(cuotaAlumno.getEstado().equalsIgnoreCase("GENERADO")){
                fila[0] = cuotaAlumno.getClaseProfesor().getClase();
                String time = new SimpleDateFormat("dd/MM/yyyy").format(cuotaAlumno.getAltacuota());
                fila[1] = time;
                time = new SimpleDateFormat("dd/MM/yyyy").format(cuotaAlumno.getVencimiento());
                fila[2] = time;
                fila[3] = cuotaAlumno.getMonto();
                total += cuotaAlumno.getMonto();
                this.cuota = cuotaAlumno;
                if (cuota.getCobroCuotas() != null) {
                    for (CobroCuota cobroCuota : cuota.getCobroCuotas()) {
                        if (cobroCuota.getSaldoCuotas() != null) {
                            for (SaldoCuota saldoCuota : cobroCuota.getSaldoCuotas()) {
                                saldoAnterior = saldoCuota;
                                total += saldoAnterior.getMontosaldo();
                            }
                        }

                    }
                }
                modeloTabla.addRow(fila);
             }if(cuotaAlumno.getEstado().equalsIgnoreCase("SALDO")){
                fila[0] = cuotaAlumno.getClaseProfesor().getClase();
                String time = new SimpleDateFormat("dd/MM/yyyy").format(cuotaAlumno.getAltacuota());
                fila[1] = time;
                time = new SimpleDateFormat("dd/MM/yyyy").format(cuotaAlumno.getVencimiento());
                fila[2] = time;
                this.cuota = cuotaAlumno;
                if(cuotaAlumno.getCobroCuotas()!=null){
                    for(CobroCuota cobro:cuotaAlumno.getCobroCuotas()){
                        if(cobro.getSaldoCuotas()!=null){
                            for(SaldoCuota saldo:cobro.getSaldoCuotas()){
                                if (saldo.getEstado().equalsIgnoreCase("GENERADO")) {
                                    saldoAnterior = saldo;
                                    total += saldoAnterior.getMontosaldo();
                                    fila[3] = "DEUDA: " + saldoAnterior.getMontosaldo();
                                }
                            }
                        }
                    }
                }
                modeloTabla.addRow(fila);
             }
        }
        if(cuota!=null){
            this.txtCuota.setText(cuota.getMonto().toString());
        }else{
            this.txtCuota.setText("0.0");
        }
        
        if(saldoAnterior !=null){
            this.txtSaldoAnterior.setText(String.valueOf(saldoAnterior.getMontosaldo()));
        }else{
            this.txtSaldoAnterior.setText("0.0");
        }
        this.txtMontoTotal.setText(total.toString());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNombreAlumno = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuotasDeAlumno = new javax.swing.JTable();
        txtMontoAbonar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNuevoSaldo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtVuelto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        datePicker = new com.github.lgooddatepicker.components.DatePicker();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCuota = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSaldoAnterior = new javax.swing.JTextField();

        setClosable(true);
        setTitle("COBRO DE CUOTAS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gimnasio/imagenes/countryIcon.png"))); // NOI18N

        jLabel1.setText("Nombre:");

        txtNombreAlumno.setEditable(false);
        txtNombreAlumno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreAlumno.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        tablaCuotasDeAlumno.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaCuotasDeAlumno);

        txtMontoAbonar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMontoAbonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoAbonarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoAbonarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoAbonarKeyTyped(evt);
            }
        });

        jLabel3.setText("COBRO:");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtNuevoSaldo.setEditable(false);
        txtNuevoSaldo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel4.setText("NUEVO SALDO:");
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));

        txtMontoTotal.setEditable(false);
        txtMontoTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel5.setText("MONTO TOTAL:");
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));

        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtVuelto.setEditable(false);
        txtVuelto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel6.setText("VUELTO:");
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setText("Fecha de Pago:");

        jLabel7.setText("CUOTA:");
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtCuota.setEditable(false);
        txtCuota.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setText("SALDO ANTERIOR:");
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));

        txtSaldoAnterior.setEditable(false);
        txtSaldoAnterior.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreAlumno))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMontoAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSaldoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNuevoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSaldoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMontoAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNuevoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMontoAbonarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAbonarKeyTyped

    }//GEN-LAST:event_txtMontoAbonarKeyTyped

    private void txtMontoAbonarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAbonarKeyPressed
 
    }//GEN-LAST:event_txtMontoAbonarKeyPressed

    private void txtMontoAbonarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAbonarKeyReleased
      try{
           Double i = Double.valueOf(this.txtMontoAbonar.getText());
           Double d = Double.valueOf(this.txtMontoTotal.getText());
           Double resultado = d - i;
           if(resultado<0){
               nuevoSaldo = 0.0;
               txtVuelto.setText(String.valueOf(resultado*-1));
               this.txtNuevoSaldo.setText("0");
           }else{
               nuevoSaldo = resultado;
               txtVuelto.setText("0");
               this.txtNuevoSaldo.setText(nuevoSaldo.toString());
           }

       }catch(NumberFormatException e){
           if(!txtMontoAbonar.getText().isEmpty()) {
               JOptionPane.showMessageDialog(null, "Debe ingresar numeros validos");
               txtMontoAbonar.setText("");
           }
           
       }
    }//GEN-LAST:event_txtMontoAbonarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String[] opciones = {"SI", "NO"};
        int seleccion = JOptionPane.showOptionDialog(null, "¿Confirma pago de Cuota?", "Seleccione una opcion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        if (seleccion == 0) {
            try {
                Double abono = Double.valueOf(txtMontoAbonar.getText());

                LocalDate fecha = datePicker.getDate();

                try {
                    if (poseeSaldoAnterior()) {
                        saldarAnterior();
                    }

                    CobroCuota unCobroCuota = null;
                    
                    if (nuevoSaldo > 0) {
                        cuota.setEstado("SALDO");
                    } else {
                        cuota.setEstado("PAGADO");
                        unCobroCuota = generarCobroCuota(cuota, cuota.getMonto(), fecha);
                    }

                    if (nuevoSaldo > 0) {
                        generarNuevoSaldo(unCobroCuota, nuevoSaldo);
                    }

                    jDialogCuota nuevaCuota = new jDialogCuota(null, true, controlador, elAlumno, cuota, abono);
                    nuevaCuota.setVisible(true);

                    MainMenu.nuevoMovimiento(elAlumno, cuota, abono);

                } catch (Notificaciones ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Debe ingresar el monto para registrar el pago");
            }
        this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker datePicker;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCuotasDeAlumno;
    private javax.swing.JTextField txtCuota;
    private javax.swing.JTextField txtMontoAbonar;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtNombreAlumno;
    private javax.swing.JTextField txtNuevoSaldo;
    private javax.swing.JTextField txtSaldoAnterior;
    private javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables

    private boolean poseeSaldoAnterior() {
        boolean retorno = false;
        if(saldoAnterior !=null){
            retorno = true;
        }
        return retorno;
    }

    private void saldarAnterior() throws Notificaciones{
        saldoAnterior.setEstado("SALDADO");
        controlador.guardarSaldoAnterior(saldoAnterior);
    }

    private CobroCuota generarCobroCuota(Cuota cuota, Double abono, LocalDate fecha) throws Notificaciones {
        return controlador.generarCobroCuota(cuota, abono, fecha);
    }

    private void generarNuevoSaldo(CobroCuota unCobroCuota, Double saldo) throws Notificaciones {
        controlador.generarNuevoSaldo(unCobroCuota, saldo);
    }
}
