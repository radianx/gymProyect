/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.Profesor;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Family
 */
public class jInternalProfesores extends javax.swing.JInternalFrame {

    ControladorPrincipal miControlador;
    DefaultTableModel modeloTabla;
    TableRowSorter<TableModel> rowSorter;
    panelNuevoProfesor panelNewProfe;
    
    public jInternalProfesores(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        cargarTabla();
        panelNewProfe = new panelNuevoProfesor(miControlador);
        panelPrincipal.add(panelNewProfe);
        rowSorter = new TableRowSorter<>(this.tablaProfesore.getModel());
        tablaProfesore.setRowSorter(rowSorter);
    }
    
    public void cargarTabla(){
//        try {
            modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Apellido");
            modeloTabla.addColumn("Usuario");
            Object[] fila = new Object[3];
            
            for (Profesor miProfesor : miControlador.getListaProfesores()) {
                if (miProfesor.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = miProfesor;
                    fila[1] = miProfesor.getApellidoprofesor();
                    fila[2] = miProfesor.getUsuario().getNombreusuario();
                    modeloTabla.addRow(fila);
                }
            }
            this.tablaProfesore.setModel(modeloTabla);
//        } catch (Notificaciones ex) {
//            JOptionPane.showMessageDialog(null, "Error al cargar Profesores desde la base de datos.");
//        }
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProfesore = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setTitle("GESTION DE PROFESORES");
        setMaximumSize(new java.awt.Dimension(410, 400));
        setMinimumSize(new java.awt.Dimension(410, 400));
        setPreferredSize(new java.awt.Dimension(410, 400));

        panelPrincipal.setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nombre:");
        jPanel2.add(jLabel1);

        txtBuscar.setMinimumSize(new java.awt.Dimension(109, 20));
        txtBuscar.setPreferredSize(new java.awt.Dimension(150, 20));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txtBuscar);

        btnBuscar.setText("Buscar");
        jPanel2.add(btnBuscar);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCerrar);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Profesores"));

        tablaProfesore.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaProfesore);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNuevo.setText("<html><center>NUEVO<br>PROFESOR</center></html>");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel5.add(btnNuevo);

        btnModificar.setText("<html><center>MODIFICAR<br>PROFESOR</center></html>");
        jPanel5.add(btnModificar);

        btnEliminar.setText("<html><center>ELIMINAR<br>PROFESOR</center></html>");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel5.add(btnEliminar);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        panelPrincipal.add(jPanel1, "card2");

        getContentPane().add(panelPrincipal, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String text = this.txtBuscar.getText();
        if(text.trim().length() == 0){
            rowSorter.setRowFilter(null);
        } else{
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.cambiarPanel();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tablaProfesore;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
