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
import gimnasio.modelo.Cuota;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
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

    public void cargarTablaCuotasAlumno(){
        modeloTablaCuotasDeAlumno = new DefaultTableModel();
        modeloTablaCuotasDeAlumno.addColumn("Clase");
        modeloTablaCuotasDeAlumno.addColumn("Fecha Emision");
        modeloTablaCuotasDeAlumno.addColumn("Vencimiento");
        modeloTablaCuotasDeAlumno.addColumn("Monto");
        this.tablaCuotasDeAlumno.setModel(modeloTablaCuotasDeAlumno);
    }
    
    public void cargarTablaVencimientos(){
        modeloTablaVencimientos = new DefaultTableModel();
        modeloTablaVencimientos.addColumn("Nombre");
        modeloTablaVencimientos.addColumn("Apellido");
        modeloTablaVencimientos.addColumn("Vencimiento");
        modeloTablaVencimientos.addColumn("Monto");
        modeloTablaVencimientos.addColumn("Telefono");
        this.tablaVencimientos.setModel(modeloTablaVencimientos);
        Object[]fila = new Object[5];
        for(Cuota cuota:this.miControlador.getListaCuotasAVencer()){
            if(cuota.getEstado().equalsIgnoreCase("ACTIVO")){
                fila[0] = cuota.getAlumno();
                fila[1] = cuota.getAlumno().getApellidoalumno();
                String time = new SimpleDateFormat("dd/MM/uuuu").format(cuota.getVencimiento());
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
        modeloTablaCuotasVencidas.addColumn("Monto");
        modeloTablaCuotasVencidas.addColumn("Telefono");
        this.tablaCuotasVencidas.setModel(modeloTablaCuotasVencidas);
        Object[]fila = new Object[5];
        for(Cuota cuota:miControlador.getListaCuotasVencidas()){
            if(cuota.getEstado().equalsIgnoreCase("ACTIVO")){
                fila[0] = cuota.getAlumno();
                fila[1] = cuota.getAlumno().getApellidoalumno();
                String time = new SimpleDateFormat("dd/MM/uuuu").format(cuota.getVencimiento());
                fila[2] = time;
                fila[3] = cuota.getMonto();
                fila[4] = cuota.getAlumno().getContacto().getTelefono1();
                modeloTablaCuotasVencidas.addRow(fila);
            }
        }
    }
    
    public void cargarTablaCuotasAlumno(Alumno unAlumno){
        modeloTablaCuotasDeAlumno = new DefaultTableModel();
        modeloTablaCuotasDeAlumno.addColumn("Clase");
        modeloTablaCuotasDeAlumno.addColumn("Fecha Emision");
        modeloTablaCuotasDeAlumno.addColumn("Vencimiento");
        modeloTablaCuotasDeAlumno.addColumn("Monto");
        this.tablaCuotasDeAlumno.setModel(modeloTablaCuotasDeAlumno);
        Object[]fila = new Object[4];
        for(Cuota cuota:unAlumno.getCuotas()){
            if(cuota.getEstado().equalsIgnoreCase("ACTIVO")){
                fila[0] = cuota.getClaseProfesor().getClase();
                String time = new SimpleDateFormat("dd/MM/uuuu").format(cuota.getAltacuota());
                fila[1] = time;
                time = new SimpleDateFormat("dd/MM/uuuu").format(cuota.getVencimiento());
                fila[2] = time;
                fila[3] = cuota.getMonto();
                modeloTablaCuotasDeAlumno.addRow(fila);
             }
        }
    }
    
    public void cargarTablaAlumnos(){
        modeloTablaAlumnos = new DefaultTableModel();
        modeloTablaAlumnos.addColumn("Nombre");
        modeloTablaAlumnos.addColumn("Apellido");
        this.tablaAlumnos.setModel(modeloTablaAlumnos);
        Object[] fila = new Object[2];
        for (Alumno unAlumno : miControlador.getListaAlumnos()) {
            if (unAlumno.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = unAlumno;
                fila[1] = unAlumno.getApellidoalumno();
                modeloTablaAlumnos.addRow(fila);
            }
        }
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaVencimientos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaCuotasVencidas = new javax.swing.JTable();
        btnCuotasVencidas = new javax.swing.JButton();
        btnVencimientos = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setTitle("GESTION DE CUOTAS");
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(750, 589));
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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaAlumnos);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("SALDO:");

        txtSaldo.setEditable(false);
        txtSaldo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtSaldo.setForeground(new java.awt.Color(255, 0, 0));
        txtSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoActionPerformed(evt);
            }
        });

        jLabel3.setText("<html><b>$</html>");

        btnCobrar.setText("COBRAR/SALDAR DEUDA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAlumno)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnCobrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btnCobrar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "VENCIMIENTOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(204, 0, 0))); // NOI18N

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

        btnCuotasVencidas.setText("GENERAR REPORTE");

        btnVencimientos.setText("GENERAR REPORTE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnVencimientos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCuotasVencidas)
                    .addComponent(btnVencimientos))
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
        if (text.trim().length() == 0) {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaAlumnos.getModel());
            rowSorter.setRowFilter(null);
            tablaAlumnos.setRowSorter(rowSorter);
        } else {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaAlumnos.getModel());
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            tablaAlumnos.setRowSorter(rowSorter);
        } 
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        text = this.txtBuscar.getText();
        if (text.trim().length() == 0) {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaAlumnos.getModel());
            rowSorter.setRowFilter(null);
            tablaAlumnos.setRowSorter(rowSorter);
        } else {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaAlumnos.getModel());
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            tablaAlumnos.setRowSorter(rowSorter);
        }
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
            alumnoSeleccionado = (Alumno) tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0);
            this.txtAlumno.setText(alumnoSeleccionado.getNombrealumno() + " " + alumnoSeleccionado.getApellidoalumno());
            this.cargarTablaCuotasAlumno(alumnoSeleccionado);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnCuotasVencidas;
    private javax.swing.JButton btnVencimientos;
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
