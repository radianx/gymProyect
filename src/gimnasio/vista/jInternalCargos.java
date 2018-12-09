/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Cargo;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Family
 */
public class jInternalCargos extends javax.swing.JInternalFrame {

    ControladorPrincipal miControlador;
    DefaultTableModel modeloTablaActivos;
    TableRowSorter<TableModel> rowSorterActivos;
    String text1 = "";
    Cargo unCargo = null;
    panelNuevoCargo panelNewCargo;

    public jInternalCargos(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        cargarTablaActivos();
        this.panelNewCargo = new panelNuevoCargo(miControlador);
        this.add(panelNewCargo);
        this.btnModificarCargo.setEnabled(false);
        this.btnEliminarCargo.setEnabled(false);
        
    }

    public void cargarTablaActivos() {
        try {
            modeloTablaActivos = new DefaultTableModel();
            modeloTablaActivos.addColumn("Nombre");
            modeloTablaActivos.addColumn("Descripcion");
            Object[] fila = new Object[2];

            try {
                for (Cargo unCargo : miControlador.getListaCargos()) {
                    if (unCargo.getEstado().equalsIgnoreCase("ACTIVO")) {
                        fila[0] = unCargo;
                        fila[1] = unCargo.getDescripcioncargo();
                        modeloTablaActivos.addRow(fila);
                    }
                }
            } catch (NullPointerException ex) {
                System.err.println("Error al cargar Cargos: " + ex.getMessage());
            }
            this.tablaCargosActivos.setModel(modeloTablaActivos);
            rowSorterActivos = new TableRowSorter<>(this.tablaCargosActivos.getModel());
            tablaCargosActivos.setRowSorter(rowSorterActivos);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
        }
    }
    
    private void cambiarPanel(JPanel panelActual, JPanel panelCambio) {
		panelActual.setVisible(false);
                panelCambio.setVisible(true);
		// this.pack();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCargosActivos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnNuevoCargo = new javax.swing.JButton();
        btnModificarCargo = new javax.swing.JButton();
        btnEliminarCargo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cargos"));

        tablaCargosActivos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaCargosActivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCargosActivosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaCargosActivosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCargosActivos);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNuevoCargo.setText("<HTML><CENTER>NUEVO<BR>CARGO</CENTER></HTML>");
        btnNuevoCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCargoActionPerformed(evt);
            }
        });
        jPanel5.add(btnNuevoCargo);

        btnModificarCargo.setText("<HTML><CENTER>MODIFICAR<BR>CARGO</CENTER></HTML>");
        btnModificarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCargoActionPerformed(evt);
            }
        });
        jPanel5.add(btnModificarCargo);

        btnEliminarCargo.setText("<HTML><CENTER>ELIMINAR<BR>CARGO</CENTER></HTML>");
        btnEliminarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCargoActionPerformed(evt);
            }
        });
        jPanel5.add(btnEliminarCargo);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
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

        btnBuscar.setText("Buscar");
        jPanel1.add(btnBuscar);

        panelPrincipal.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        text1 = this.txtBuscar.getText();
        if (text1.trim().length() == 0) {
            rowSorterActivos.setRowFilter(null);
        } else {
            rowSorterActivos.setRowFilter(RowFilter.regexFilter("(?i)" + text1));
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaCargosActivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCargosActivosMouseClicked
        try{
            unCargo = (Cargo) this.tablaCargosActivos.getValueAt(this.tablaCargosActivos.getSelectedRow(), 0);
        }catch(Exception ex){
            System.err.println("Seleccion vacia: "+ex.getMessage());
        }   
    }//GEN-LAST:event_tablaCargosActivosMouseClicked

    private void tablaCargosActivosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCargosActivosMouseReleased
        this.btnModificarCargo.setEnabled(true);
        this.btnEliminarCargo.setEnabled(true);
    }//GEN-LAST:event_tablaCargosActivosMouseReleased

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnEliminarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCargoActionPerformed
        if(!this.tablaCargosActivos.getSelectionModel().isSelectionEmpty()){
            unCargo = (Cargo) this.tablaCargosActivos.getValueAt(this.tablaCargosActivos.getSelectedRow(), 0);
            unCargo.setEstado("INACTIVO");
            miControlador.bajaCargo(unCargo);
            SwingUtilities.invokeLater(new Runnable(){public void run(){
                           cargarTablaActivos(); 
            }});
        }
    }//GEN-LAST:event_btnEliminarCargoActionPerformed

    private void btnModificarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCargoActionPerformed
        try {
            panelNewCargo.recibirDatos((Cargo) this.tablaCargosActivos.getValueAt(tablaCargosActivos.getSelectedRow(), 0));
            cambiarPanel(panelPrincipal, panelNewCargo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error al seleccionar Cargo: "+ex.getMessage());
        }
    }//GEN-LAST:event_btnModificarCargoActionPerformed

    private void btnNuevoCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCargoActionPerformed
        cambiarPanel(this.panelPrincipal, panelNewCargo);
    }//GEN-LAST:event_btnNuevoCargoActionPerformed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        if(!panelNewCargo.isVisible()){
            cambiarPanel(panelNewCargo, panelPrincipal);
            this.cargarTablaActivos();
            this.modeloTablaActivos.fireTableDataChanged();
            this.txtBuscar.grabFocus();
        }
    }//GEN-LAST:event_formMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminarCargo;
    private javax.swing.JButton btnModificarCargo;
    private javax.swing.JButton btnNuevoCargo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tablaCargosActivos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
