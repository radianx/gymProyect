/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.ClaseAlumno;
import gimnasio.modelo.CobroCuota;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.HorarioAlumno;
import gimnasio.modelo.SaldoCuota;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
public class JInternalAlumnosCuotas extends javax.swing.JInternalFrame {

    ControladorPrincipal miControlador;
    DefaultTableModel modeloTablaAlumnos;
    DefaultTableModel modeloTablaCuotas;
    DefaultTableModel modeloTablaHorarios;
    TableRowSorter<TableModel> rowSorter;
    /**
     * Creates new form JInternalAlumnosCuotas
     */
    public JInternalAlumnosCuotas(ControladorPrincipal miControlador) {
        this.miControlador = miControlador;
        initComponents();
        cargarTabla();
        cargarTablaCuotas();
        cargarTablaHorarios();
    }

    public void cargarTabla(){
        modeloTablaAlumnos = new DefaultTableModel();
        modeloTablaAlumnos.addColumn("Nombre");
        modeloTablaAlumnos.addColumn("Apellido");
        Object[] fila = new Object[2];
        try {
            for (Alumno miAlumno : miControlador.getListaAlumnos()) {
                if (miAlumno.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = miAlumno;
                    fila[1] = miAlumno.getApellidoalumno();
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
    
    public void cargarTablaHorarios(){
        modeloTablaHorarios = new DefaultTableModel();
        modeloTablaHorarios.addColumn("Clase");
        modeloTablaHorarios.addColumn("Dia");
        modeloTablaHorarios.addColumn("Hora Entrada");
        modeloTablaHorarios.addColumn("Hora Salida");
        this.tablaHorarios.setModel(modeloTablaHorarios);
    }
    
    public void cargarTablaHorarios(Alumno unAlumno){
        modeloTablaHorarios = new DefaultTableModel();
        modeloTablaHorarios.addColumn("Clase");
        modeloTablaHorarios.addColumn("Dia");
        modeloTablaHorarios.addColumn("Hora Entrada");
        modeloTablaHorarios.addColumn("Hora Salida");
        this.tablaHorarios.setModel(modeloTablaHorarios);
        try {
            List<HorarioAlumno> listaHorariosAlumno = new ArrayList<>();
            for(ClaseAlumno clase:unAlumno.getClaseAlumnos()){
                listaHorariosAlumno.addAll(miControlador.getListaHorariosAlumno(clase));
            }
            Object[] fila = new Object[4];
            if (!listaHorariosAlumno.isEmpty()) {
                for (HorarioAlumno horario: listaHorariosAlumno) {
                        fila[0] = horario.getClaseAlumno();
                        String dia = new SimpleDateFormat("EEEE", new Locale("es", "ES")).format(horario.getInicio());
                        fila[1] = dia;
                        fila[2] = horario;
                        fila[3] = horario.getFinString();
                        modeloTablaHorarios.addRow(fila);
                }
            } else {
                modeloTablaHorarios = new DefaultTableModel();
                modeloTablaHorarios.addColumn("Mensaje");
                this.tablaHorarios.setModel(modeloTablaHorarios);
                Object[] data = new Object[1];
                data[0] = "El alumno seleccionado no esta inscripto a ninguna clase";
                modeloTablaHorarios.addRow(data);
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
        tablaHorarios.setModel(modeloTablaHorarios);
    }
    
    public void cargarTablaCuotas(){
        modeloTablaCuotas = new DefaultTableModel();
        modeloTablaCuotas.addColumn("Clase");
        modeloTablaCuotas.addColumn("Alta Cuota");
        modeloTablaCuotas.addColumn("Vencimiento");
        modeloTablaCuotas.addColumn("Monto");
        this.tablaCuotas.setModel(modeloTablaCuotas);
    }
    
    public void cargarTablaCuotas(Alumno unAlumno){
        modeloTablaCuotas = new DefaultTableModel();
        modeloTablaCuotas.addColumn("Clase");
        modeloTablaCuotas.addColumn("Alta Cuota");
        modeloTablaCuotas.addColumn("Vencimiento");
        modeloTablaCuotas.addColumn("Monto");
        Object[] fila = new Object[5];
        boolean tieneCuotasPagadas = false;
        List<Cuota>cuotasAlu = null;
        try{
            cuotasAlu = miControlador.getCuotasDeAlumno(unAlumno);
        }catch(Notificaciones ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        }
        if (unAlumno.getCuotas() != null) {
            if (unAlumno.getCuotas().size() >= 1) {
                for (Cuota cuota : cuotasAlu) {
                    if (cuota.getEstado().equalsIgnoreCase("GENERADO")) {
                        fila[0] = cuota.getClaseProfesor().getClase();
                        String time = new SimpleDateFormat("dd/MM/yyyy").format(cuota.getAltacuota());
                        fila[1] = time;
                        time = new SimpleDateFormat("dd/MM/yyyy").format(cuota.getVencimiento());
                        fila[2] = time;
                        fila[3] = cuota.getMonto();

                        modeloTablaCuotas.addRow(fila);

                    }
                    if (cuota.getEstado().equalsIgnoreCase("SALDO")) {
                        fila[0] = cuota.getClaseProfesor().getClase();
                        String time = new SimpleDateFormat("dd/MM/yyyy").format(cuota.getAltacuota());
                        fila[1] = time;
                        time = new SimpleDateFormat("dd/MM/yyyy").format(cuota.getVencimiento());
                        fila[2] = time;
                        if (cuota.getCobroCuotas() != null) {
                            for (CobroCuota cobro : cuota.getCobroCuotas()) {
                                if (cobro.getSaldoCuotas() != null) {
                                    for (SaldoCuota saldo : cobro.getSaldoCuotas()) {
                                        if (saldo.getEstado().equalsIgnoreCase("GENERADO")) {
                                            fila[3] = "DEUDA: " + saldo;
                                        }
                                    }
                                }
                            }
                        }
                        modeloTablaCuotas.addRow(fila);
                    }
                }
            } else {
                modeloTablaCuotas = new DefaultTableModel();
                modeloTablaCuotas.addColumn("Mensaje");
                Object[] rowData = new Object[1];
                rowData[0] = "El alumno Seleccionado no esta inscripto a ninunga clase actualmente.";
                modeloTablaCuotas.addRow(rowData);
            }
        } else {
            modeloTablaCuotas = new DefaultTableModel();
            modeloTablaCuotas.addColumn("Mensaje");
            Object[] rowData = new Object[1];
            rowData[0] = "Al Alumno Seleccionado no posee cuotas activas";
            modeloTablaCuotas.addRow(rowData);
        }
        tablaCuotas.setModel(modeloTablaCuotas);

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
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCuotas = new javax.swing.JTable();
        btnNuevaCuota = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaHorarios = new javax.swing.JTable();

        setTitle("CUOTAS DE ALUMNOS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gimnasio/imagenes/countryIcon.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(700, 460));
        setPreferredSize(new java.awt.Dimension(700, 463));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1);

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

        btnBuscar.setText("Buscar");
        jPanel1.add(btnBuscar);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("CERRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Alumnos"));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseEntered(evt);
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
        });
        jScrollPane1.setViewportView(tablaAlumnos);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuotas del Alumno Seleccionado"));

        tablaCuotas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaCuotas);

        btnNuevaCuota.setText("CARGAR CUOTA MANUALMENTE");
        btnNuevaCuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaCuotaActionPerformed(evt);
            }
        });

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Horario del Alumno Seleccionado"));

        tablaHorarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tablaHorarios);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnNuevaCuota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnNuevaCuota)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseEntered

    }//GEN-LAST:event_jScrollPane1MouseEntered

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String text = this.txtBuscar.getText();
        if(text.trim().length() == 0){
            rowSorter.setRowFilter(null);
        } else{
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseClicked
        if(!tablaAlumnos.getSelectionModel().isSelectionEmpty()){
            Alumno unAlu = (Alumno) tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0);
                cargarTablaCuotas(unAlu);
                cargarTablaHorarios(unAlu);

        }
    }//GEN-LAST:event_tablaAlumnosMouseClicked

    private void btnNuevaCuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaCuotaActionPerformed
        if (!tablaAlumnos.getSelectionModel().isSelectionEmpty()) {
            Alumno alumnoSeleccionado = (Alumno) tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0);
            jDialogCuota nuevaCuota = new jDialogCuota(null, true, miControlador, alumnoSeleccionado);
            nuevaCuota.setVisible(true);
                this.cargarTabla();
                this.cargarTablaCuotas();
                this.cargarTablaHorarios();
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno para generar una cuota");
        }
    }//GEN-LAST:event_btnNuevaCuotaActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
            SwingUtilities.invokeLater(()->{
                this.cargarTabla();
                this.cargarTablaCuotas();
                this.cargarTablaHorarios();
            });
    }//GEN-LAST:event_formFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevaCuota;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTable tablaCuotas;
    private javax.swing.JTable tablaHorarios;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}