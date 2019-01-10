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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
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
    String text = "";
    
    public jInternalUsuarios(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        this.btnEliminar.setEnabled(false);
        this.btnModificar.setEnabled(false);
            
        panelNewUser = new panelNuevoUsuario(miControlador);
        this.jPanel1.add(panelNewUser);
        tablaUsuarios.clearSelection();
        this.cargarTabla();

        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                System.out.println("internalFrameClosing....");
                MainMenu.detenerEscaner = true;
                super.internalFrameClosing(e);
            }
         
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                System.out.println("internalFrameClosed....");
                MainMenu.detenerEscaner = true;
                super.internalFrameClosed(e);
            }
        });
        
    }
    
    private void cambiarPanel(JPanel panelActual, JPanel panelCambio) {
		panelActual.setVisible(false);
                panelCambio.setVisible(true);
		// this.pack();
    }
    
    public void cargarTabla() {
            modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            Object[] fila = new Object[1];

            try {
                for (Usuario miUsuario : miControlador.getListaUsuarios()) {
                    if (miUsuario.getEstado().equalsIgnoreCase("ACTIVO")) {
                        fila[0] = miUsuario;
                        modeloTabla.addRow(fila);
                    }
                }
            } catch (NullPointerException ex) {
                System.err.println("Error al cargar usuarios: " + ex.getMessage());
            }
            this.tablaUsuarios.setModel(modeloTabla);
            rowSorter = new TableRowSorter<>(this.tablaUsuarios.getModel());
            tablaUsuarios.setRowSorter(rowSorter);
            
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
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscar2KeyTyped(evt);
            }
        });

        btnBuscar2.setText("Buscar");
        btnBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar2ActionPerformed(evt);
            }
        });

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

        btnNuevo.setText("<html><center>NUEVO<br>USUARIO</center></html>");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel5.add(btnNuevo);

        btnModificar.setText("<html><center>MODIFICAR<br>USUARIO</center></html>");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel5.add(btnModificar);

        btnEliminar.setText("<html><center>ELIMINAR<br>USUARIO</center></html>");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel5.add(btnEliminar);

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
        tablaUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseEntered(evt);
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
                .addGroup(panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrinCentroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addGroup(panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrinCentroLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelPrinCentroLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevo1)))
                        .addGap(14, 14, 14))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelPrinCentroLayout.setVerticalGroup(
            panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrinCentroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelPrinCentroLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
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
        jInternalCargos cargos = new jInternalCargos(miControlador);
        this.getParent().add(cargos);
        cargos.toFront();
        
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
        text = this.txtBuscar2.getText();
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
    }//GEN-LAST:event_formMouseEntered

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(!this.tablaUsuarios.getSelectionModel().isSelectionEmpty()){
        Usuario unUsuario = (Usuario)this.tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0);
        tablaUsuarios.clearSelection();
        try {
            this.miControlador.bajaUsuario(unUsuario.getIdusuario());
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un usario para Eliminarlo");
        }
        SwingUtilities.invokeLater(new Runnable(){public void run(){
            cargarTabla();
        }});
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscar2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyTyped

    }//GEN-LAST:event_txtBuscar2KeyTyped

    private void btnBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar2ActionPerformed
        text = this.txtBuscar2.getText();
        if(text.trim().length() == 0){
            rowSorter.setRowFilter(null);
        } else{
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_btnBuscar2ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            if(!this.tablaUsuarios.getSelectionModel().isSelectionEmpty()){
            panelNewUser.recibirDatos((Usuario) this.tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0));
            cambiarPanel(panelPrincipal2, panelNewUser);
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario para Modificarlo");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error al seleccionar Usuario: "+ex.getMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        if(!this.panelNewUser.isVisible()){
            cambiarPanel(panelNewUser,panelPrincipal2);
        }
    }//GEN-LAST:event_jPanel1MouseEntered

    private void tablaUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseEntered
        tablaUsuarios.clearSelection();
        this.cargarTabla();
        this.modeloTabla.fireTableDataChanged();
    }//GEN-LAST:event_tablaUsuariosMouseEntered

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown
        this.cargarTabla();
    }//GEN-LAST:event_jPanel1ComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar2;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelPrinCentro;
    private javax.swing.JPanel panelPrincNorte2;
    private javax.swing.JPanel panelPrincSur;
    private javax.swing.JPanel panelPrincipal2;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTable tablaUsuarios1;
    private javax.swing.JTextField txtBuscar2;
    // End of variables declaration//GEN-END:variables
 
}
