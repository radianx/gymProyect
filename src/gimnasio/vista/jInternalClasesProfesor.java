/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Clase;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.Profesor;
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
public class jInternalClasesProfesor extends javax.swing.JInternalFrame {

    ControladorPrincipal miControlador;
    panelClaseProfesor panelNewClaseProfesor;
    DefaultTableModel modeloTablaDias;
    String text = "";
    
    public jInternalClasesProfesor(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        try {
            CargadorTabla.profesoresActivos(this.tablaProfesores, miControlador);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        cargarTablaDias();
        panelNewClaseProfesor = new panelClaseProfesor(miControlador);
        this.add(panelNewClaseProfesor);
    }

    public void cargarTablaDias(){
        modeloTablaDias = new DefaultTableModel();
        modeloTablaDias.addColumn("Clase");
        modeloTablaDias.addColumn("Dia");
        modeloTablaDias.addColumn("Inicio");
        modeloTablaDias.addColumn("Fin");
        this.tablaClasesActivas.setModel(modeloTablaDias);
    }
    
    public void cargarTablaClases(Profesor unProfe){
        modeloTablaDias = new DefaultTableModel();
        modeloTablaDias.addColumn("Clase");
        modeloTablaDias.addColumn("Dia");
        modeloTablaDias.addColumn("Inicio");
        modeloTablaDias.addColumn("Fin");
        this.tablaClasesActivas.setModel(modeloTablaDias);
        Object[] fila = new Object[4];
        if(unProfe != null){
            for(ClaseProfesor claseProfesor:unProfe.getClaseProfesors()){
                if (claseProfesor.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = claseProfesor;

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(claseProfesor.getInicio());
                    String time = new SimpleDateFormat("HH:mm").format(cal.getTime());
                    
                    fila[1] = new SimpleDateFormat("EEEE", new Locale("es", "ES")).format(claseProfesor.getInicio());
                    fila[2] = time;

                    cal.setTime(claseProfesor.getFin());
                    time = new SimpleDateFormat("HH:mm").format(cal.getTime());
                    fila[3] = time;

                    modeloTablaDias.addRow(fila);
                }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClasesActivas = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProfesores = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(600, 400));
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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Clases de Profesor"));

        tablaClasesActivas.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaClasesActivas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClasesActivasMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaClasesActivasMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClasesActivas);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNuevo.setText("<HTML><CENTER>NUEVA<BR>CLASE</CENTER></HTML>");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel5.add(btnNuevo);

        btnEliminar.setText("<HTML><CENTER>ELIMINAR<BR>CLASE</CENTER></HTML>");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel5.add(btnEliminar);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Profesores"));

        tablaProfesores.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProfesores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProfesoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaProfesoresMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaProfesoresMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProfesores);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        btnBuscar.setText("Buscar Profesor");
        jPanel1.add(btnBuscar);

        panelPrincipal.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        cambiarPanel(this.panelPrincipal, panelNewClaseProfesor);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(!this.tablaClasesActivas.getSelectionModel().isSelectionEmpty()){
            try {
                ClaseProfesor unaClase = (ClaseProfesor) this.tablaClasesActivas.getValueAt(this.tablaClasesActivas.getSelectedRow(), 0);
                unaClase.setEstado("INACTIVO");
                miControlador.bajaClaseProfesor(unaClase);
                SwingUtilities.invokeLater(new Runnable(){public void run(){
                    cargarTablaClases((Profesor) tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(),0));
                }});
            } catch (Notificaciones ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una clase para eliminarla");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        text = this.txtBuscar.getText();
        if (text.trim().length() == 0) {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaProfesores.getModel());
            rowSorter.setRowFilter(null);
            tablaProfesores.setRowSorter(rowSorter);
        } else {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaProfesores.getModel());
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            tablaProfesores.setRowSorter(rowSorter);
        }
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        text = this.txtBuscar.getText();
        if (text.trim().length() == 0) {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaProfesores.getModel());
            rowSorter.setRowFilter(null);
            tablaProfesores.setRowSorter(rowSorter);
        } else {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaProfesores.getModel());
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            tablaProfesores.setRowSorter(rowSorter);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void panelPrincipalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMouseEntered
        if(!panelNewClaseProfesor.isVisible()){
            cambiarPanel(panelNewClaseProfesor, panelPrincipal);
            this.txtBuscar.grabFocus();
        }
    }//GEN-LAST:event_panelPrincipalMouseEntered

    private void panelPrincipalComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelPrincipalComponentShown
        try {
            CargadorTabla.profesoresActivos(tablaProfesores, miControlador);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_panelPrincipalComponentShown

    private void tablaProfesoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProfesoresMouseClicked
        if(!tablaProfesores.getSelectionModel().isSelectionEmpty()){
            cargarTablaClases((Profesor) tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_tablaProfesoresMouseClicked

    private void tablaProfesoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProfesoresMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaProfesoresMouseReleased

    private void tablaClasesActivasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClasesActivasMouseReleased
        this.btnEliminar.setEnabled(true);
    }//GEN-LAST:event_tablaClasesActivasMouseReleased

    private void tablaClasesActivasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClasesActivasMouseClicked
    }//GEN-LAST:event_tablaClasesActivasMouseClicked

    private void tablaProfesoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProfesoresMouseEntered
        try {
            CargadorTabla.profesoresActivos(tablaProfesores, miControlador);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_tablaProfesoresMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tablaClasesActivas;
    private javax.swing.JTable tablaProfesores;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
    private void cambiarPanel(JPanel panelActual, JPanel panelCambio) {
		panelActual.setVisible(false);
                panelCambio.setVisible(true);
		// this.pack();
    }
}
