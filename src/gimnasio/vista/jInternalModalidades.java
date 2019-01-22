/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Modalidad;
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
 * @author Oso
 */
public class jInternalModalidades extends javax.swing.JInternalFrame {

    ControladorPrincipal miControlador;
    DefaultTableModel modeloTabla;
    TableRowSorter<TableModel> rowSorter;
    String text = "";
    Modalidad unaModalidad;
    panelNuevaModalidad panelNewModalidad;
    
    public jInternalModalidades(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        cargarTabla();
        panelNewModalidad = new panelNuevaModalidad(miControlador);
        this.add(panelNewModalidad);
        this.btnEliminar.setEnabled(false);
        this.btnModificar.setEnabled(false);
    }


    public void cargarTabla() {
        try {
            modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Descripcion");
            Object[] fila = new Object[2];

            try {
                for (Modalidad miModalidad : miControlador.getListaModalidades()) {
                    if (miModalidad.getEstado().equalsIgnoreCase("ACTIVO")) {
                        fila[0] = miModalidad;
                        fila[1] = miModalidad.getDescripcionmodalidad();
                        modeloTabla.addRow(fila);
                    }
                }
            } catch (Exception ex) {
                System.err.println("Error al cargar Modalidades: " + ex.getMessage());
            }
            this.tablaModalidades.setModel(modeloTabla);
            rowSorter = new TableRowSorter<>(this.tablaModalidades.getModel());
            tablaModalidades.setRowSorter(rowSorter);
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
        tablaModalidades = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);
        setTitle("GESTION DE MODALIDADES");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gimnasio/imagenes/countryIcon.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(400, 410));
        setPreferredSize(new java.awt.Dimension(400, 411));
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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Modalidades"));

        tablaModalidades.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaModalidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaModalidadesMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaModalidadesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaModalidades);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNuevo.setText("<HTML><CENTER>NUEVA<BR>MODALIDAD</CENTER></HTML>");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel5.add(btnNuevo);

        btnModificar.setText("<HTML><CENTER>MODIFICAR<BR>MODALIDAD</CENTER></HTML>");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel5.add(btnModificar);

        btnEliminar.setText("<HTML><CENTER>ELIMINAR<BR>MODALIDAD</CENTER></HTML>");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel5.add(btnEliminar);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
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

    private void tablaModalidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaModalidadesMouseClicked
        try{
            unaModalidad = (Modalidad) this.tablaModalidades.getValueAt(this.tablaModalidades.getSelectedRow(), 0);
        }catch(Exception ex){
            System.err.println("Seleccion vacia: "+ex.getMessage());
        }
    }//GEN-LAST:event_tablaModalidadesMouseClicked

    private void tablaModalidadesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaModalidadesMouseReleased
        this.btnModificar.setEnabled(true);
        this.btnEliminar.setEnabled(true);
    }//GEN-LAST:event_tablaModalidadesMouseReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        cambiarPanel(this.panelPrincipal, panelNewModalidad);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            panelNewModalidad.recibirDatos((Modalidad) this.tablaModalidades.getValueAt(tablaModalidades.getSelectedRow(), 0));
            cambiarPanel(panelPrincipal, panelNewModalidad);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error al seleccionar Modalidad: "+ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(!this.tablaModalidades.getSelectionModel().isSelectionEmpty()){
            try {
                unaModalidad = (Modalidad) this.tablaModalidades.getValueAt(this.tablaModalidades.getSelectedRow(), 0);
                unaModalidad.setEstado("INACTIVO");
                miControlador.bajaModalidad(unaModalidad.getIdmodalidad());
                SwingUtilities.invokeLater(new Runnable(){public void run(){
                    cargarTabla();
                }});
            } catch (Notificaciones ex) {
                JOptionPane.showMessageDialog(null,ex.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        text = this.txtBuscar.getText();
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        text = this.txtBuscar.getText();
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void panelPrincipalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMouseEntered
        if(!panelNewModalidad.isVisible()){
            cambiarPanel(panelNewModalidad, panelPrincipal);
        }        
    }//GEN-LAST:event_panelPrincipalMouseEntered

    private void panelPrincipalComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelPrincipalComponentShown
        cargarTabla();
    }//GEN-LAST:event_panelPrincipalComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tablaModalidades;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
