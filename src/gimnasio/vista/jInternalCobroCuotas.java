/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.Clase;
import gimnasio.modelo.ClaseAlumno;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.CobroCuota;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import gimnasio.modelo.SaldoCuota;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author adrian
 */
public class jInternalCobroCuotas extends javax.swing.JInternalFrame {

    ControladorPrincipal miControlador;
    DefaultTableModel modeloTablaAlumnos;
    DefaultTableModel modeloTablaCuotasDeAlumno;
    DefaultTableModel modeloTablaVencimientos;
    DefaultTableModel modeloTablaCuotasVencidas;
    TableRowSorter<TableModel> rowSorter;
    
    Alumno alumnoSeleccionado;
    String text = "";
    
    public jInternalCobroCuotas(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        cargarTablaAlumnos();
        cargarTablaCuotasAlumno();
        cargarTablaVencimientos();
        cargarTablaCuotasVencidas();
    }

    public jInternalCobroCuotas(ControladorPrincipal controlador, Alumno unAlu){
        miControlador = controlador;
        initComponents();
        cargarTablaAlumnos();
        cargarTablaCuotasAlumno();
        cargarTablaVencimientos();
        cargarTablaCuotasVencidas();
        alumnoSeleccionado = unAlu;
        this.txtAlumno.setText(unAlu.getNombrealumno() + " " + unAlu.getApellidoalumno());
        try {
            cargarTablaCuotasAlumno(alumnoSeleccionado);
        } catch (Notificaciones ex) {
            ex.printStackTrace();
        }
    }
    
    public void cargarTablaCuotasAlumno(){
        modeloTablaCuotasDeAlumno = new DefaultTableModel();
        modeloTablaCuotasDeAlumno.addColumn("Clase");
        modeloTablaCuotasDeAlumno.addColumn("Vencimiento");
        modeloTablaCuotasDeAlumno.addColumn("Prox. Vencimiento");
        modeloTablaCuotasDeAlumno.addColumn("Monto a Pagar");
        this.tablaCuotasDeAlumno.setModel(modeloTablaCuotasDeAlumno);
    }
    
    public void cargarTablaVencimientos(){
        modeloTablaVencimientos = new DefaultTableModel();
        modeloTablaVencimientos.addColumn("Nombre");
        modeloTablaVencimientos.addColumn("Apellido");
        modeloTablaVencimientos.addColumn("Vencimiento");
        modeloTablaVencimientos.addColumn("Monto a pagar");
        modeloTablaVencimientos.addColumn("Telefono");
        this.tablaVencimientos.setModel(modeloTablaVencimientos);
        Object[]fila = new Object[5];
        for(Cuota cuota:this.miControlador.getListaCuotasAVencer()){
            if(cuota.getEstado().equalsIgnoreCase("GENERADO") || cuota.getEstado().equalsIgnoreCase("SALDO")){
                fila[0] = cuota.getAlumno();
                fila[1] = cuota.getAlumno().getApellidoalumno();
                String time = new SimpleDateFormat("dd/MM/yyyy").format(cuota.getAltacuota());
                fila[2] = time;
                fila[3] = cuota.getMonto();
                fila[4] = cuota.getAlumno().getContacto().getTelefono1();
                modeloTablaVencimientos.addRow(fila);
            }
        }
    }
    
    public void cargarTablaCuotasVencidas(){
        modeloTablaCuotasVencidas = new DefaultTableModel();
        modeloTablaCuotasVencidas.addColumn("Nombre");
        modeloTablaCuotasVencidas.addColumn("Apellido");
        modeloTablaCuotasVencidas.addColumn("Vencimiento");
        modeloTablaCuotasVencidas.addColumn("Monto a pagar");
        modeloTablaCuotasVencidas.addColumn("Telefono");
        this.tablaCuotasVencidas.setModel(modeloTablaCuotasVencidas);
        Object[]fila = new Object[5];
        for(Cuota cuota:miControlador.getListaCuotasVencidas()){
            if(cuota.getEstado().equalsIgnoreCase("GENERADO")){
                fila[0] = cuota.getAlumno();
                fila[1] = cuota.getAlumno().getApellidoalumno();
                String time = new SimpleDateFormat("dd/MM/yyyy").format(cuota.getAltacuota());
                fila[2] = time;
                fila[3] = cuota.getMonto();
                fila[4] = cuota.getAlumno().getContacto().getTelefono1();
                modeloTablaCuotasVencidas.addRow(fila);
            }
        }
    }
    
    public void cargarTablaCuotasAlumno(Alumno unAlumno) throws Notificaciones{
        modeloTablaCuotasDeAlumno = new DefaultTableModel();
        modeloTablaCuotasDeAlumno.addColumn("Clase");
        modeloTablaCuotasDeAlumno.addColumn("Vencimiento");
        modeloTablaCuotasDeAlumno.addColumn("Prox. Vencimiento");
        modeloTablaCuotasDeAlumno.addColumn("Monto a pagar");
        this.tablaCuotasDeAlumno.setModel(modeloTablaCuotasDeAlumno);
        Object[]fila = new Object[5];
        SaldoCuota saldoCuota = null;
            for(Cuota cuota:miControlador.getCuotasDeAlumno(unAlumno)){
                if(cuota.getEstado().equalsIgnoreCase("GENERADO")){
                    fila[0] = cuota.getClaseProfesor().getClase();                
                    fila[1] = cuota;
                    String time = new SimpleDateFormat("dd/MM/yyyy").format(cuota.getVencimiento());
                    fila[2] = time;
                    fila[3] = cuota.getMonto();
                    if(cuota.getCobroCuotas()!=null){
                        for(CobroCuota cobro:cuota.getCobroCuotas()){
                            if(cobro.getSaldoCuotas()!=null){
                                for(SaldoCuota saldo:cobro.getSaldoCuotas()){
                                    saldoCuota = saldo;
                                }
                            }
                        }
                    }
                    modeloTablaCuotasDeAlumno.addRow(fila);
                 }if(cuota.getEstado().equalsIgnoreCase("SALDO")){
                    fila[0] = cuota.getClaseProfesor().getClase();
                    fila[1] = cuota;
                    String time = new SimpleDateFormat("dd/MM/yyyy").format(cuota.getVencimiento());
                    fila[2] = time;
                    if(cuota.getCobroCuotas()!=null){
                        for(CobroCuota cobro:cuota.getCobroCuotas()){
                            if(cobro.getSaldoCuotas()!=null){
                                for(SaldoCuota saldo:cobro.getSaldoCuotas()){
                                    if (saldo.getEstado().equalsIgnoreCase("GENERADO")) {
                                        saldoCuota = saldo;
                                        fila[3] = "DEUDA: " + saldoCuota.getMontosaldo();
                                    }
                                }
                            }
                        }
                    }
                    modeloTablaCuotasDeAlumno.addRow(fila);
                 }
            }
        if(saldoCuota!=null) txtSaldo.setText(String.valueOf(saldoCuota.getMontosaldo()));
        else txtSaldo.setText("0");
    }
    
    public void cargarTablaAlumnos(){
        modeloTablaAlumnos = new DefaultTableModel();
        modeloTablaAlumnos.addColumn("Nombre");
        modeloTablaAlumnos.addColumn("Apellido");
        Object[] fila = new Object[2];
        try {
            for (Alumno unAlumno : miControlador.getListaAlumnos()) {
                if (unAlumno.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = unAlumno;
                    fila[1] = unAlumno.getApellidoalumno();
                    modeloTablaAlumnos.addRow(fila);
                }
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.tablaAlumnos.setModel(modeloTablaAlumnos);
        rowSorter = new TableRowSorter<>(this.tablaAlumnos.getModel());
        tablaAlumnos.setRowSorter(rowSorter);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtAlumno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuotasDeAlumno = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnCobrar = new javax.swing.JButton();
        btnNuevaCuota = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaVencimientos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaCuotasVencidas = new javax.swing.JTable();
        btnCuotasVencidas = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setTitle("GESTION DE CUOTAS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gimnasio/imagenes/countryIcon.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(750, 521));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelPrincipalMouseEntered(evt);
            }
        });
        panelPrincipal.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panelPrincipalComponentShown(evt);
            }
        });
        panelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAlumno.setEditable(false);
        txtAlumno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlumnoActionPerformed(evt);
            }
        });

        jLabel1.setText("Alumno:");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuotas de Alumno Seleccionado"));

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
        tablaCuotasDeAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCuotasDeAlumnoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaCuotasDeAlumnoMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCuotasDeAlumno);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Alumnos"));
        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaAlumnos);

        jLabel2.setText("SALDO:");
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));

        txtSaldo.setEditable(false);
        txtSaldo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtSaldo.setForeground(new java.awt.Color(255, 0, 0));
        txtSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoActionPerformed(evt);
            }
        });

        jLabel3.setText("<html><b>$</html>");

        btnCobrar.setText("COBRAR");
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        btnNuevaCuota.setText("NUEVA CUOTA");
        btnNuevaCuota.setEnabled(false);
        btnNuevaCuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaCuotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAlumno)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevaCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCobrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevaCuota))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btnCobrar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRÓXIMAS A VENCER", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 102, 0))); // NOI18N

        tablaVencimientos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaVencimientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVencimientosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaVencimientosMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tablaVencimientos);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CUOTAS VENCIDAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        tablaCuotasVencidas.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaCuotasVencidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCuotasVencidasMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaCuotasVencidasMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tablaCuotasVencidas);

        btnCuotasVencidas.setText("GENERAR REPORTE MOROSOS");
        btnCuotasVencidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuotasVencidasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCuotasVencidas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCuotasVencidas)
                .addContainerGap())
        );

        panelPrincipal.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCerrar);

        panelPrincipal.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBuscar.setMinimumSize(new java.awt.Dimension(150, 20));
        txtBuscar.setPreferredSize(new java.awt.Dimension(150, 20));
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar);

        btnBuscar.setText("Buscar Alumno");
        jPanel1.add(btnBuscar);

        panelPrincipal.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        text = this.txtBuscar.getText();
        TableRowSorter rowSorter = new TableRowSorter<>(this.tablaAlumnos.getModel());
        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        tablaAlumnos.setRowSorter(rowSorter);
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        text = this.txtBuscar.getText();
        TableRowSorter rowSorter = new TableRowSorter<>(this.tablaAlumnos.getModel());
        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        tablaAlumnos.setRowSorter(rowSorter);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void panelPrincipalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMouseEntered

    }//GEN-LAST:event_panelPrincipalMouseEntered

    private void panelPrincipalComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelPrincipalComponentShown
        cargarTablaAlumnos();
        cargarTablaCuotasAlumno();
        cargarTablaVencimientos();
        cargarTablaCuotasVencidas();
    }//GEN-LAST:event_panelPrincipalComponentShown

    private void tablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseClicked
        if(!tablaAlumnos.getSelectionModel().isSelectionEmpty()){
            alumnoSeleccionado = (Alumno) this.tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0);
            this.txtAlumno.setText(alumnoSeleccionado.getNombrealumno() + " " + alumnoSeleccionado.getApellidoalumno());
            this.btnNuevaCuota.setEnabled(true);
            try{
                this.cargarTablaCuotasAlumno(alumnoSeleccionado);
            }catch(Notificaciones ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_tablaAlumnosMouseClicked

    private void tablaAlumnosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaAlumnosMouseReleased

    private void tablaCuotasDeAlumnoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCuotasDeAlumnoMouseReleased

    }//GEN-LAST:event_tablaCuotasDeAlumnoMouseReleased

    private void tablaCuotasDeAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCuotasDeAlumnoMouseClicked
    }//GEN-LAST:event_tablaCuotasDeAlumnoMouseClicked

    private void tablaVencimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVencimientosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaVencimientosMouseClicked

    private void tablaVencimientosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVencimientosMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaVencimientosMouseReleased

    private void tablaCuotasVencidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCuotasVencidasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaCuotasVencidasMouseClicked

    private void tablaCuotasVencidasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCuotasVencidasMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaCuotasVencidasMouseReleased

    private void txtAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlumnoActionPerformed

    private void txtSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        if(!tablaCuotasDeAlumno.getSelectionModel().isSelectionEmpty()){
            Cuota cuota = (Cuota) tablaCuotasDeAlumno.getValueAt(tablaCuotasDeAlumno.getSelectedRow(),1);
            MainMenu.abrirCobro(alumnoSeleccionado, cuota);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar el item a pagar.");
        }
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void tablaAlumnosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseEntered

    }//GEN-LAST:event_tablaAlumnosMouseEntered

    private void btnNuevaCuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaCuotaActionPerformed
        try{
            if (!tablaAlumnos.getSelectionModel().isSelectionEmpty()) {
                alumnoSeleccionado = (Alumno) tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0);
                List<Cuota> cuotasAlu = miControlador.getCuotasDeAlumno(alumnoSeleccionado);
                boolean tieneGenerado = false;
                if(alumnoSeleccionado.getClaseAlumnos().size()>1){
                    int contadorDeClases = alumnoSeleccionado.getClaseAlumnos().size();
                    int contadorGenerados = 0;
                    for(ClaseAlumno claseAlumno:alumnoSeleccionado.getClaseAlumnos()){
                        contadorGenerados = 0;
                        ClaseProfesor claseProf = claseAlumno.getClaseProfesor();
                        List<Cuota>cuotasClaseProfesor =new ArrayList<>();
                        for(Cuota cuota:cuotasAlu){
                            if(cuota.getClaseProfesor().getIdclaseprofesor()==claseProf.getIdclaseprofesor()){
                                cuotasClaseProfesor.add(cuota);
                            }
                        }
                        int selector = cuotasClaseProfesor.size();
                        System.out.println("cantidad de cuotas del alumno: " + selector + "en la clase: " + claseProf.getClase());
                        for(Cuota unaCuota:cuotasClaseProfesor){
                            if(unaCuota.getEstado().equalsIgnoreCase("GENERADO")){
                                contadorGenerados++;
                            }
                        }
                    }
                    if(contadorDeClases==contadorGenerados){
                        JOptionPane.showMessageDialog(null, "El alumno ya tiene generadas todas sus cuotas");
                        tieneGenerado= true;
                    }
                }else {
                    for (Cuota unaCuota : cuotasAlu) {
                        if (unaCuota.getEstado().equalsIgnoreCase("GENERADO")) {
                            JOptionPane.showMessageDialog(null, "El alumno ya tiene generada una cuota.");
                            tieneGenerado = true;
                            break;
                        }
                    }
                }

                if (!tieneGenerado) {
                    jDialogCuota nuevaCuota = new jDialogCuota(null, true, miControlador, alumnoSeleccionado,null,null,false);
                    nuevaCuota.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno para generar una cuota");
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnNuevaCuotaActionPerformed

    private void btnCuotasVencidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuotasVencidasActionPerformed
        miControlador.generarReporteMorosos();
    }//GEN-LAST:event_btnCuotasVencidasActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        this.cargarTablaAlumnos();
        this.cargarTablaCuotasAlumno();
        this.cargarTablaCuotasVencidas();
        this.cargarTablaVencimientos();
    }//GEN-LAST:event_formInternalFrameActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnCuotasVencidas;
    private javax.swing.JButton btnNuevaCuota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTable tablaCuotasDeAlumno;
    private javax.swing.JTable tablaCuotasVencidas;
    private javax.swing.JTable tablaVencimientos;
    private javax.swing.JTextField txtAlumno;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtSaldo;
    // End of variables declaration//GEN-END:variables
    private void cambiarPanel(JPanel panelActual, JPanel panelCambio) {
		panelActual.setVisible(false);
                panelCambio.setVisible(true);
		// this.pack();
    }
}
