/*
 * Copyright (C) 2018 Family
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Usuario;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Family
 */
public class jInternalUsuarios extends javax.swing.JInternalFrame {

     /**
     * Creates new form jInternalUsuarios
     */
    
    ControladorPrincipal miControlador;
    DefaultTableModel modeloTabla;
    TableRowSorter<TableModel> rowSorter;
    static JPanel panelOriginal;
    panelNuevoUsuario panelNewUser;
    
    public jInternalUsuarios(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        this.btnEliminar.setEnabled(false);
        this.btnModificar.setEnabled(false);
        
        panelNewUser = new panelNuevoUsuario(miControlador);
        this.jPanel1.add(panelNewUser);
        this.cargarTabla();
        
        rowSorter = new TableRowSorter<>(this.tablaUsuarios.getModel());
        tablaUsuarios.setRowSorter(rowSorter);
    }
    
    private void cambiarPanel(JPanel panelActual, JPanel panelCambio) {
		panelActual.setVisible(false);
                panelCambio.setVisible(true);
		// this.pack();
    }
    
    public void cargarTabla(){
        try {
            modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            Object[] fila = new Object[1];
            
            for(Usuario miUsuario: miControlador.getListaUsuarios()){
                fila[0] = miUsuario;
                modeloTabla.addRow(fila);
            }
            this.tablaUsuarios.setModel(modeloTabla);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar usuarios desde la base de datos.");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelPrincipal2 = new javax.swing.JPanel();
        panelPrincNorte2 = new javax.swing.JPanel();
        txtBuscar2 = new javax.swing.JTextField();
        btnBuscar2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        panelPrinCentro = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUsuarios1 = new javax.swing.JTable();
        btnNuevo1 = new javax.swing.JButton();
        panelPrincSur = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("GESTION DE USUARIOS");
        setMaximumSize(new java.awt.Dimension(430, 430));
        setMinimumSize(new java.awt.Dimension(430, 430));
        setPreferredSize(new java.awt.Dimension(430, 430));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(400, 400));
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel1.setLayout(new java.awt.CardLayout());

        panelPrincipal2.setMinimumSize(new java.awt.Dimension(400, 200));
        panelPrincipal2.setPreferredSize(new java.awt.Dimension(420, 300));
        panelPrincipal2.setLayout(new java.awt.BorderLayout());

        panelPrincNorte2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnBuscar2.setText("Buscar");

        jLabel4.setText("Nombre:");

        javax.swing.GroupLayout panelPrincNorte2Layout = new javax.swing.GroupLayout(panelPrincNorte2);
        panelPrincNorte2.setLayout(panelPrincNorte2Layout);
        panelPrincNorte2Layout.setHorizontalGroup(
            panelPrincNorte2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincNorte2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar2)
                .addContainerGap())
        );
        panelPrincNorte2Layout.setVerticalGroup(
            panelPrincNorte2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincNorte2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(panelPrincNorte2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnBuscar2)))
        );

        panelPrincipal2.add(panelPrincNorte2, java.awt.BorderLayout.PAGE_START);

        jLabel2.setText("FOTOGRAFIA");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new java.awt.GridBagLayout());

        btnNuevo.setText("NUEVO USUARIO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel5.add(btnNuevo, new java.awt.GridBagConstraints());

        btnModificar.setText("MODIFICAR USUARIO");
        jPanel5.add(btnModificar, new java.awt.GridBagConstraints());

        btnEliminar.setText("ELIMINAR USUARIO");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel5.add(btnEliminar, new java.awt.GridBagConstraints());

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        tablaUsuarios1.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaUsuarios1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuarios1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaUsuarios1);

        btnNuevo1.setText("NUEVO CARGO");
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrinCentroLayout = new javax.swing.GroupLayout(panelPrinCentro);
        panelPrinCentro.setLayout(panelPrinCentroLayout);
        panelPrinCentroLayout.setHorizontalGroup(
            panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrinCentroLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
            .addGroup(panelPrinCentroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(btnNuevo1))
                .addContainerGap())
        );
        panelPrinCentroLayout.setVerticalGroup(
            panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrinCentroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrinCentroLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        panelPrincipal2.add(panelPrinCentro, java.awt.BorderLayout.CENTER);

        panelPrincSur.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        panelPrincSur.add(btnCerrar);

        panelPrincipal2.add(panelPrincSur, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(panelPrincipal2, "card2");

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void tablaUsuarios1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuarios1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaUsuarios1MouseClicked

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        this.btnEliminar.setEnabled(true);
        this.btnModificar.setEnabled(true);
        this.btnModificar.grabFocus();
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        cambiarPanel(panelPrincipal2, panelNewUser);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String text = this.txtBuscar.getText();
        if(text.trim().length() == 0){
            rowSorter.setRowFilter(null);
        } else{
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.tablaUsuarios.setRowSelectionInterval(0, 0);
            this.btnEliminar.setEnabled(true);
            this.btnModificar.setEnabled(true);
            this.btnModificar.grabFocus();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        if(!this.panelNewUser.isVisible()){
            cambiarPanel(panelNewUser,panelPrincipal2);
            this.cargarTabla();
            this.modeloTabla.fireTableDataChanged();
            this.txtBuscar2.grabFocus();
        }
    }//GEN-LAST:event_formMouseEntered

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscar2;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelPrinCentro;
    private javax.swing.JPanel panelPrincNorte;
    private javax.swing.JPanel panelPrincNorte1;
    private javax.swing.JPanel panelPrincNorte2;
    private javax.swing.JPanel panelPrincSur;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelPrincipal1;
    private javax.swing.JPanel panelPrincipal2;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTable tablaUsuarios1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtBuscar2;
    // End of variables declaration//GEN-END:variables
}
