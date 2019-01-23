/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Modalidad;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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
public class jInternalProfesorModalidad extends javax.swing.JInternalFrame {

    ControladorPrincipal miControlador;
    DefaultTableModel modeloTabla;
    DefaultComboBoxModel modeloCombo;
    TableRowSorter<TableModel> rowSorter;
    Profesor profesorSeleccionado;
    String text = "";
    
    public jInternalProfesorModalidad(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        this.tablaModalidadProfesor.setModel(modeloTabla);
        try {
            CargadorTabla.profesoresActivos(tablaProfesores, controlador);
            this.cargarComboModalidades();
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        this.btnQuitar.setEnabled(false);
    }


    public void cargarTablaModalidad(Profesor unProfesor){
        try {
            modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Precio");
            Object[] fila = new Object[2];

            try {
                for (Profesormodalidad miProfeMod: unProfesor.getProfesorModalidads()) {
                    if (miProfeMod.getEstado().equalsIgnoreCase("ACTIVO")) {
                        fila[0] = miProfeMod;
                        fila[1] = miProfeMod.getPreciohora();
                        modeloTabla.addRow(fila);
                    }
                }
            } catch (Exception ex) {
                System.err.println("Error al cargar Modalidades: " + ex.getMessage());
            }
            this.tablaModalidadProfesor.setModel(modeloTabla);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
        }
    }
    
    
   public void cargarComboModalidades() throws Notificaciones{
        ArrayList<Modalidad> listaModalidades = new ArrayList<>();
        for(Modalidad miModalidad: miControlador.getListaModalidades()){
            if(miModalidad.getEstado().equalsIgnoreCase("ACTIVO")){
                listaModalidades.add(miModalidad);
            }
        }
        modeloCombo = new DefaultComboBoxModel(listaModalidades.toArray());
        this.cmbModalidad.setModel(modeloCombo);

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
        tablaProfesores = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaModalidadProfesor = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        cmbModalidad = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSeleccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setTitle("MODALIDADES DE PROFESOR");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gimnasio/imagenes/countryIcon.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(400, 410));
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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Profesores"));

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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaProfesoresMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProfesores);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Modalidades del profesor"));

        tablaModalidadProfesor.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaModalidadProfesor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaModalidadProfesorMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaModalidadProfesorMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaModalidadProfesor);

        btnAgregar.setText("Agregar -->");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        cmbModalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Modalidad:");

        jLabel3.setText("Precio:");

        txtSeleccion.setEditable(false);
        txtSeleccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel4.setText("Seleccion:");

        txtPrecio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(255, 0, 0));

        jLabel5.setText("<html><b>$</b></html>");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbModalidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSeleccion))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAgregar)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addComponent(btnQuitar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel2.setText("Nombre Profesor:");
        jPanel1.add(jLabel2);

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

    private void tablaProfesoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProfesoresMouseClicked
        if(!tablaProfesores.getSelectionModel().isSelectionEmpty()){
            profesorSeleccionado = (Profesor) tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(),0);
            this.txtSeleccion.setText(profesorSeleccionado.getNombreprofesor() + " "+profesorSeleccionado.getApellidoprofesor());
            this.cargarTablaModalidad(profesorSeleccionado);
        }
    }//GEN-LAST:event_tablaProfesoresMouseClicked

    private void tablaProfesoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProfesoresMouseReleased

    }//GEN-LAST:event_tablaProfesoresMouseReleased

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

    }//GEN-LAST:event_panelPrincipalMouseEntered

    private void panelPrincipalComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelPrincipalComponentShown
        try {
            CargadorTabla.profesoresActivos(tablaProfesores, miControlador);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_panelPrincipalComponentShown

    private void tablaModalidadProfesorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaModalidadProfesorMouseClicked
        this.btnQuitar.setEnabled(true);
    }//GEN-LAST:event_tablaModalidadProfesorMouseClicked

    private void tablaModalidadProfesorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaModalidadProfesorMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaModalidadProfesorMouseReleased

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            double precio = Double.valueOf(txtPrecio.getText());
            Modalidad unaModalidad = (Modalidad)cmbModalidad.getSelectedItem();
            Profesormodalidad profeMod = new Profesormodalidad(profesorSeleccionado, unaModalidad, precio, "ACTIVO");
            this.miControlador.altaProfesorPorModalidad(profeMod);
            CargadorTabla.profesoresActivos(tablaProfesores, miControlador);
            this.cargarTablaModalidad(profesorSeleccionado);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        try {
            Profesormodalidad profesorModalidad = (Profesormodalidad)this.tablaModalidadProfesor.getValueAt(tablaModalidadProfesor.getSelectedRow(),0);
            miControlador.bajaProfesorDeModalidad(profesorModalidad);
            cargarTablaModalidad(profesorSeleccionado);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
            
    }//GEN-LAST:event_btnQuitarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox<String> cmbModalidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tablaModalidadProfesor;
    private javax.swing.JTable tablaProfesores;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSeleccion;
    // End of variables declaration//GEN-END:variables
}
