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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Family
 */
public class jInternalUsuarios extends javax.swing.JInternalFrame {

     /**
     * Creates new form jInternalUsuarios
     */
    
    ControladorPrincipal miControlador;
    DefaultListModel modeloLista;
    
    public jInternalUsuarios(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        
        modeloLista = new DefaultListModel();
        for(Usuario miUsuario: miControlador.getListaUsuarios()){
            modeloLista.addElement(miUsuario.getNombreusuario());
        }
        
        this.listaUsuarios.setModel(modeloLista);
    }

    private void buscarFiltro(String busqueda){
        DefaultListModel itemsFiltrados=new DefaultListModel();
        Set<Usuario> usuarios=miControlador.getListaUsuarios();

        usuarios.stream().forEach((usuario) -> {
            String nombreUsuario=usuario.getNombreusuario().toLowerCase();
            if (nombreUsuario.contains(busqueda.toLowerCase())) {
                itemsFiltrados.addElement(usuario.getNombreusuario());
            }
        });
        this.listaUsuarios.setModel(itemsFiltrados);

    }
    
    private void cambiarPanel(JPanel panel) {
		getContentPane().remove(this.panelPrincipal);

		this.panelPrincipal = panel;

		getContentPane().add(this.panelPrincipal);

		this.paintAll(getGraphics());
		// this.pack();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelPrincNorte = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        panelPrinCentro = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaUsuarios = new javax.swing.JList<>();
        panelPrincSur = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("GESTION DE USUARIOS");
        setMaximumSize(new java.awt.Dimension(430, 300));
        setMinimumSize(new java.awt.Dimension(430, 300));
        setPreferredSize(new java.awt.Dimension(430, 300));

        panelPrincipal.setMinimumSize(new java.awt.Dimension(400, 200));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(420, 300));
        panelPrincipal.setLayout(new java.awt.BorderLayout());

        panelPrincNorte.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        btnBuscar.setText("Buscar");

        jLabel1.setText("Nombre:");

        javax.swing.GroupLayout panelPrincNorteLayout = new javax.swing.GroupLayout(panelPrincNorte);
        panelPrincNorte.setLayout(panelPrincNorteLayout);
        panelPrincNorteLayout.setHorizontalGroup(
            panelPrincNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincNorteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addContainerGap())
        );
        panelPrincNorteLayout.setVerticalGroup(
            panelPrincNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincNorteLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(panelPrincNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnBuscar)))
        );

        panelPrincipal.add(panelPrincNorte, java.awt.BorderLayout.PAGE_START);

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
        jPanel5.add(btnEliminar, new java.awt.GridBagConstraints());

        listaUsuarios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                listaUsuariosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(listaUsuarios);

        javax.swing.GroupLayout panelPrinCentroLayout = new javax.swing.GroupLayout(panelPrinCentro);
        panelPrinCentro.setLayout(panelPrinCentroLayout);
        panelPrinCentroLayout.setHorizontalGroup(
            panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrinCentroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelPrinCentroLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelPrinCentroLayout.setVerticalGroup(
            panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrinCentroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrinCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelPrinCentro, java.awt.BorderLayout.CENTER);

        panelPrincSur.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        panelPrincSur.add(btnCerrar);

        panelPrincipal.add(panelPrincSur, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(panelPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ENTER){
             this.txtBuscar.setText("");
         }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void listaUsuariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listaUsuariosKeyReleased
        buscarFiltro(this.txtBuscar.getText());
    }//GEN-LAST:event_listaUsuariosKeyReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        cambiarPanel(new panelNuevoUsuario(miControlador));
    }//GEN-LAST:event_btnNuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaUsuarios;
    private javax.swing.JPanel panelPrinCentro;
    private javax.swing.JPanel panelPrincNorte;
    private javax.swing.JPanel panelPrincSur;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
