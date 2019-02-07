/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.Contacto;
import gimnasio.modelo.Obrasocial;
import gimnasio.modelo.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Family
 */
public class panelNuevoAlumno extends javax.swing.JPanel {

    Alumno alumnoSeleccionado = null;
    DefaultTableModel modeloTabla;
    DefaultComboBoxModel modeloCombo;
    TableRowSorter<TableModel> rowSorter;
    ControladorPrincipal miControlador;
    String text = "";
    Usuario usuarioSeleccionado;
    
    public panelNuevoAlumno(ControladorPrincipal controlador) {
        this.miControlador =controlador;
        
        initComponents();
        this.btnActivar.setEnabled(false);
        cargarTabla();
        cargarCombo();
        Locale locale = new Locale("es", "ES");
        DatePickerSettings settings = new DatePickerSettings(locale);
        settings.setFormatForDatesCommonEra("dd/MM/yyyy");
        settings.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");
        datePicker1.setSettings(settings);
        datePicker1.setDateToToday();

    }

    public void recibirDatos(Alumno unAlumno) throws IOException{
        alumnoSeleccionado = unAlumno;
        LocalDate fecha = Instant.ofEpochMilli(unAlumno.getFechanacimiento().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        this.datePicker1.setDate(fecha);
        this.txtNombre.setText(unAlumno.getNombrealumno());
        this.txtApellido.setText(unAlumno.getApellidoalumno());
        this.txtAltura.setText(unAlumno.getAltura().toString());
        this.txtDireccion.setText(unAlumno.getContacto().getDireccion());
        this.txtEmail.setText(unAlumno.getContacto().getEmail1());
        this.txtPeso.setText(unAlumno.getPeso().toString());
        this.txtTelefono1.setText(unAlumno.getContacto().getTelefono1());
        this.txtTelefono2.setText(unAlumno.getContacto().getTelefono1());
        this.txtTelefonoEmergencia.setText(unAlumno.getContacto().getTelefonoemergencia());
        this.usuarioSeleccionado = unAlumno.getUsuario();
        this.cmbObraSocial.setSelectedItem(unAlumno.getObrasocial());
    }
    
    public void cargarCombo(){
        modeloCombo = new DefaultComboBoxModel();
        final String nuevo = "---NUEVA OBRA SOCIAL---";
        this.modeloCombo.addElement( nuevo );
        try {
            for (Obrasocial miObraSocial : miControlador.getListaObrasSociales()) {
                if (miObraSocial.getEstado().equalsIgnoreCase("ACTIVO")) {
                    this.modeloCombo.addElement(miObraSocial);
                }
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, "error al leer obras sociales desde la base de datos");
        }
        this.cmbObraSocial.setModel(modeloCombo);

    }
    
    public void reCargarCombo(){
        
        try{
            for (Obrasocial miObraSocial : miControlador.getListaObrasSociales()) {
                if (miObraSocial.getEstado().equalsIgnoreCase("ACTIVO")) {
                    this.modeloCombo.addElement(miObraSocial);
                }
            }
        }catch(Notificaciones ex){
            JOptionPane.showMessageDialog(null, "error al leer obras sociales desde la base de datos");
        }
    }
    
    public void cargarTabla() {
            modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Apellido");
            modeloTabla.addColumn("Usuario");
            Object[] fila = new Object[3];
            
            try{
            for (Alumno miAlumno : miControlador.getListaAlumnos()) {
                if (miAlumno.getEstado().equalsIgnoreCase("INACTIVO")) {
                    fila[0] = miAlumno;
                    fila[1] = miAlumno.getApellidoalumno();
                    fila[2] = miAlumno.getUsuario();
                    modeloTabla.addRow(fila);
                }
            }
            } catch(NullPointerException ex){
                System.err.println("Cargar Alumnos en la tabla retorna: " + ex.getMessage() + ", lo que probablemente significa que no hay alumnos cargados en la base de datos");
            } catch(Notificaciones ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
            this.tablaAlumnosInactivos.setModel(modeloTabla);
            rowSorter = new TableRowSorter<>(this.tablaAlumnosInactivos.getModel());
            tablaAlumnosInactivos.setRowSorter(rowSorter);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        txtApellido = new javax.swing.JTextField();
        txtPeso = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAltura = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnosInactivos = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTelefono1 = new javax.swing.JTextField();
        txtTelefono2 = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtTelefonoEmergencia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        btnUsuario = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        cmbObraSocial = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });

        jLabel2.setText("Apellido:");

        jLabel5.setText("Altura:");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Alumnos Inactivos"));

        tablaAlumnosInactivos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaAlumnosInactivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaAlumnosInactivosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAlumnosInactivos);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnBuscar.setText("Buscar Inactivo");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setText("Peso:");

        jLabel4.setText("Fecha de Nacimiento:");

        jLabel1.setText("Nombre:");

        jLabel6.setText("Telefono1:");

        jLabel7.setText("Telefono2:");

        jLabel8.setText("E-mail:");

        jLabel9.setText("Telefono de Emergencia:");

        jLabel10.setText("Dirección:");

        btnUsuario.setText("SELECCIONAR USUARIO");
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });

        jLabel11.setText(":");

        txtUsuario.setEditable(false);
        txtUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        cmbObraSocial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbObraSocialItemStateChanged(evt);
            }
        });
        cmbObraSocial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbObraSocialFocusGained(evt);
            }
        });
        cmbObraSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbObraSocialActionPerformed(evt);
            }
        });

        jLabel12.setText("Obra Social:");

        jButton1.setText("Recargar Lista");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jLabel1)
                                    .addGap(7, 7, 7)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(23, 23, 23)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(datePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addComponent(txtTelefonoEmergencia)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDireccion)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoEmergencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jButton1))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuario)
                    .addComponent(jLabel11)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar);

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel3.add(btnLimpiar);

        btnActivar.setText("ACTIVAR");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });
        jPanel3.add(btnActivar);

        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCerrar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        text = this.txtBuscar.getText();
        if(text.trim().length() == 0){
            rowSorter.setRowFilter(null);
        } else{
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        text = this.txtBuscar.getText();
        if(text.trim().length() == 0){
            rowSorter.setRowFilter(null);
        } else{
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }        
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.setVisible(false);
        alumnoSeleccionado = null;
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            if (usuarioSeleccionado != null) {
                Date fecha = Date.from(this.datePicker1.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String direccion = txtDireccion.getText();
                String telefono1 = txtTelefono1.getText();
                String telefono2 = txtTelefono2.getText();
                String email = txtEmail.getText();
                String telefonoEmer = txtTelefonoEmergencia.getText();
                Double peso = 0.0;
                Double altura = 0.0;
                if (!txtPeso.getText().isEmpty()) {
                    peso = Double.valueOf(this.txtPeso.getText());
                }
                if(!txtAltura.getText().isEmpty()){
                    altura = Double.valueOf(this.txtAltura.getText());
                }
                Contacto unContacto = new Contacto(direccion, telefono1,telefono2,email,telefonoEmer);
                Obrasocial unaOS = (Obrasocial) this.modeloCombo.getSelectedItem();
                miControlador.altaContacto(unContacto);
                if(alumnoSeleccionado==null){
                Alumno unAlumno = new Alumno(unContacto,unaOS,usuarioSeleccionado, nombre, apellido, peso, altura, fecha, "ACTIVO");
                miControlador.altaAlumno(unAlumno);
                }else{
                    alumnoSeleccionado.setAltura(altura);
                    alumnoSeleccionado.setApellidoalumno(apellido);
                    alumnoSeleccionado.setContacto(unContacto);
                    alumnoSeleccionado.setFechanacimiento(fecha);
                    alumnoSeleccionado.setNombrealumno(nombre);
                    alumnoSeleccionado.setObrasocial(unaOS);
                    alumnoSeleccionado.setPeso(peso);
                    miControlador.altaAlumno(alumnoSeleccionado);
                }
                
                jInternalAlumno usuarios = (jInternalAlumno) this.getParent().getParent().getParent().getParent().getParent();
                usuarios.cargarTabla();
                
                String[] opciones ={"SI","NO","CANCELAR"};
                int seleccion = JOptionPane.showOptionDialog(null, "¿Inscribir al alumno a una clase?", "Seleccione una opcion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
                switch (seleccion){
                    case 0: 
                        jInternalClasesAlumno clases = new jInternalClasesAlumno(this.miControlador);
                        clases.setVisible(true);
                        clases.cambiarPanel();
                        this.getParent().getParent().getParent().getParent().getParent().getParent().add(clases);
                        this.setVisible(false);
                        this.limpiarCampos();
                        clases.toFront();
                        break;
                    case 1: //salio por el no
                        this.setVisible(false);
                        this.limpiarCampos();
                        break;
                    case 3: //no hacer nada --> hacer algo?
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar alumno: " + ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tablaAlumnosInactivosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosInactivosMouseReleased
        this.btnActivar.setEnabled(true);
    }//GEN-LAST:event_tablaAlumnosInactivosMouseReleased

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        try {
            Alumno unAlumno = (Alumno) this.tablaAlumnosInactivos.getValueAt(this.tablaAlumnosInactivos.getSelectedRow(), 0);
            unAlumno.setEstado("ACTIVO");
            miControlador.altaAlumno(unAlumno);
            SwingUtilities.invokeLater(new Runnable(){public void run(){
                           cargarTabla(); 
            }});
            this.btnActivar.setEnabled(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el Alumno: "+ex.getMessage());
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed
        jDialogSeleccionarUsuario seleccion = new jDialogSeleccionarUsuario(null, false,miControlador);
        this.usuarioSeleccionado = seleccion.showDialog();
    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        if(usuarioSeleccionado == null){
            this.txtUsuario.setText("SIN CARGAR");
        }else{
            this.txtUsuario.setText(usuarioSeleccionado.getNombreusuario());
        }
    }//GEN-LAST:event_jPanel1MouseEntered

    private void cmbObraSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbObraSocialActionPerformed
        if (cmbObraSocial.getSelectedItem().toString().equalsIgnoreCase("---NUEVA OBRA SOCIAL---")) {
            jInternalObraSocial panelObraSocial = new jInternalObraSocial(miControlador);
            panelObraSocial.setVisible(true);
            this.getParent().getParent().getParent().getParent().getParent().getParent().add(panelObraSocial);
            panelObraSocial.toFront();
        }
    }//GEN-LAST:event_cmbObraSocialActionPerformed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered

    }//GEN-LAST:event_formMouseEntered

    private void cmbObraSocialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbObraSocialFocusGained

    }//GEN-LAST:event_cmbObraSocialFocusGained

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarCombo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbObraSocialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbObraSocialItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbObraSocialItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JComboBox<String> cmbObraSocial;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAlumnosInactivos;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTelefono2;
    private javax.swing.JTextField txtTelefonoEmergencia;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
    
    private void limpiarCampos(){
        this.txtNombre.setText("");
        this.txtApellido.setText("");
        this.txtAltura.setText("");
        this.txtDireccion.setText("");
        this.txtEmail.setText("");
        this.txtPeso.setText("");
        this.txtTelefono1.setText("");
        this.txtTelefono2.setText("");
        this.txtTelefonoEmergencia.setText("");
        this.txtBuscar.setText("");
        this.datePicker1.setText("");
        this.btnActivar.setEnabled(false);
    }
}
