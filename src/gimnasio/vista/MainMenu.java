/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;


import com.sun.jna.Native;
import gimnasio.controlador.ControladorRele;
import gimnasio.interfaces.GestorRele;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adrian
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
//    jInternalLogueo panelLogin = new jInternalLogueo();
//    jInternalProductos panelProductos = new jInternalProductos();
//    jInternalInformes panelInformes = new jInternalInformes();
//    jInternalClases panelClases = new jInternalClases();
//    jTemporal panelTemporal = new jTemporal();
//    jInternalUsuarios panelUsuarios = new jInternalUsuarios();
    
    boolean mostrar = false;    
    
    public void actualizarTabla(){
    }
    
    public void cerrarSesion(){
        this.btnAsistencia.setEnabled(false);
        this.btnCobros.setEnabled(false);
        this.btnVentas.setEnabled(false);
        this.btnUsuarios.setEnabled(false);
    }
    
    public MainMenu() {
        initComponents();
        this.btnAsistencia.setEnabled(mostrar);
        this.btnCobros.setEnabled(mostrar);
        this.btnVentas.setEnabled(mostrar);
        this.btnUsuarios.setEnabled(mostrar);
        
//        jDesktopPane1.add(panelTemporal);
//        jDesktopPane1.add(panelLogin);
//        jDesktopPane1.add(panelInformes);
//        jDesktopPane1.add(panelProductos);
//        jDesktopPane1.add(panelClases);
//        jDesktopPane1.add(panelUsuarios);
        
        this.setBtnsVisibility(false);
        
        Dimension desktopSize = jDesktopPane1.getSize();
//        Dimension loginSize = panelLogin.getSize();
       
//        panelLogin.setLocation((desktopSize.width - loginSize.width)/2, (desktopSize.height - loginSize.height)/2);
//        panelLogin.setVisible(true);
    }
    
    
    public jInternalLogueo getjInternalLogueo(){
//      return this.panelLogin;
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
        jPanel2 = new javax.swing.JPanel();
        btnAlumnos = new javax.swing.JButton();
        btnAsistencia = new javax.swing.JButton();
        btnProfesores = new javax.swing.JButton();
        btnHorarios = new javax.swing.JButton();
        btnCobros = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnPagos = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtHabilitado = new javax.swing.JTextField();
        btnAbrirPuerta = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMiArchivo1 = new javax.swing.JMenuItem();
        jMenuEdicion = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("Sistema Country GYM"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(2, 2));

        btnAlumnos.setText("Alumnos");
        jPanel2.add(btnAlumnos);

        btnAsistencia.setText("Asistencia");
        btnAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsistenciaActionPerformed(evt);
            }
        });
        jPanel2.add(btnAsistencia);

        btnProfesores.setText("Profesores");
        jPanel2.add(btnProfesores);

        btnHorarios.setText("Horarios");
        jPanel2.add(btnHorarios);

        btnCobros.setText("Cobros");
        btnCobros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrosActionPerformed(evt);
            }
        });
        jPanel2.add(btnCobros);

        btnVentas.setText("Ventas");
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        jPanel2.add(btnVentas);

        btnPagos.setText("Pagos");
        jPanel2.add(btnPagos);

        btnUsuarios.setText("Usuarios");
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });
        jPanel2.add(btnUsuarios);

        jPanel1.add(jPanel2, java.awt.BorderLayout.EAST);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel6.setMaximumSize(new java.awt.Dimension(500, 110));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setMaximumSize(new java.awt.Dimension(400, 110));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(400, 110));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(400, 110));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jPanel6.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("FOTOGRAFIA CLIENTE");
        jPanel5.add(jLabel1, java.awt.BorderLayout.CENTER);

        txtHabilitado.setEditable(false);
        jPanel5.add(txtHabilitado, java.awt.BorderLayout.SOUTH);

        btnAbrirPuerta.setText("<HTML><CENTER>ABRIR<BR>PUERTA</CENTER></HTML>");
        btnAbrirPuerta.setMaximumSize(new java.awt.Dimension(73, 20));
        btnAbrirPuerta.setMinimumSize(new java.awt.Dimension(73, 20));
        btnAbrirPuerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirPuertaActionPerformed(evt);
            }
        });
        jPanel5.add(btnAbrirPuerta, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanel5, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jDesktopPane1.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.selectionBackground"));
        jDesktopPane1.setMinimumSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jMenuArchivo.setText("Archivo");

        jMenu1.setText("Usuarios");

        jMenuItem3.setText("Listar Usuarios");
        jMenu1.add(jMenuItem3);

        jMenuItem1.setText("Nuevo Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Eliminar Usuario");
        jMenu1.add(jMenuItem2);

        jMenuArchivo.add(jMenu1);

        jMenu2.setText("Alumnos");

        jMenuItem4.setText("Listar Alumnos");
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Nuevo Alumno");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Eliminar Alumno");
        jMenu2.add(jMenuItem6);

        jMenuArchivo.add(jMenu2);

        jMenu3.setText("Profesores");

        jMenuItem7.setText("Listar Profesores");
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Nuevo Profesor");
        jMenu3.add(jMenuItem8);

        jMenuItem9.setText("Eliminar Profesor");
        jMenu3.add(jMenuItem9);

        jMenuArchivo.add(jMenu3);

        jMenu9.setText("Cargos");

        jMenuItem13.setText("Listar Cargos");
        jMenu9.add(jMenuItem13);

        jMenuItem14.setText("Nuevo Cargo");
        jMenu9.add(jMenuItem14);

        jMenuItem15.setText("Eliminar Cargo");
        jMenu9.add(jMenuItem15);

        jMenuArchivo.add(jMenu9);

        jMenu11.setText("Personal");

        jMenuItem16.setText("Listar Personal");
        jMenu11.add(jMenuItem16);

        jMenuItem17.setText("Nuevo Personal");
        jMenu11.add(jMenuItem17);

        jMenuItem18.setText("Eliminar Personal");
        jMenu11.add(jMenuItem18);

        jMenuArchivo.add(jMenu11);

        jMenu8.setText("Sectores");

        jMenuItem10.setText("Listar Sectores");
        jMenu8.add(jMenuItem10);

        jMenuItem11.setText("Nuevo Sector");
        jMenu8.add(jMenuItem11);

        jMenuItem12.setText("Eliminar Sector");
        jMenu8.add(jMenuItem12);

        jMenuArchivo.add(jMenu8);
        jMenuArchivo.add(jSeparator1);

        jMiArchivo1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jMiArchivo1.setText("Cerrar Sesion");
        jMiArchivo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMiArchivo1ActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMiArchivo1);

        jMenuBar1.add(jMenuArchivo);

        jMenuEdicion.setText("Movimientos");

        jMenuItem19.setText("Cobros");
        jMenuEdicion.add(jMenuItem19);

        jMenuItem20.setText("Pagos");
        jMenuEdicion.add(jMenuItem20);

        jMenuBar1.add(jMenuEdicion);

        jMenu5.setText("Asistencia");

        jMenuItem25.setText("Listar Asistencias");
        jMenu5.add(jMenuItem25);

        jMenuItem26.setText("Nueva Asistencia");
        jMenu5.add(jMenuItem26);

        jMenuItem27.setText("Eliminar Asistencia");
        jMenu5.add(jMenuItem27);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Horarios");

        jMenuItem28.setText("Planilla de Horarios");
        jMenu6.add(jMenuItem28);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Cuotas");

        jMenuItem29.setText("Listar Cuotas");
        jMenu7.add(jMenuItem29);

        jMenuBar1.add(jMenu7);

        jMenu10.setText("Dipositivos");

        jMenuItem23.setText("Ver Estado de Lector");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem23);

        jMenuItem24.setText("Abrir Puerta");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem24);

        jMenuBar1.add(jMenu10);

        jMenu4.setText("Caja");

        jMenuItem21.setText("Apertura de Caja");
        jMenu4.add(jMenuItem21);

        jMenuItem22.setText("Cierre de Caja");
        jMenu4.add(jMenuItem22);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMiArchivo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMiArchivo1ActionPerformed
        this.cerrarSesion();
    }//GEN-LAST:event_jMiArchivo1ActionPerformed

    private void btnCobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrosActionPerformed
//        panelInformes.setVisible(true);
    }//GEN-LAST:event_btnCobrosActionPerformed

    private void btnAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsistenciaActionPerformed
//        panelTemporal.setVisible(true);
    }//GEN-LAST:event_btnAsistenciaActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
//        panelProductos.setVisible(true);
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
//        panelUsuarios.setVisible(true);
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnAbrirPuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirPuertaActionPerformed
        Thread t1 = new Thread(new ControladorRele());
        t1.start();
    }//GEN-LAST:event_btnAbrirPuertaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        Thread t1 = new Thread(new ControladorRele());
        t1.start();
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirPuerta;
    private javax.swing.JButton btnAlumnos;
    private javax.swing.JButton btnAsistencia;
    private javax.swing.JButton btnCobros;
    private javax.swing.JButton btnHorarios;
    private javax.swing.JButton btnPagos;
    private javax.swing.JButton btnProfesores;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton btnVentas;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdicion;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMiArchivo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtHabilitado;
    // End of variables declaration//GEN-END:variables

    public void setBtnsVisibility(boolean b) {
        this.btnAsistencia.setEnabled(!b);
        this.btnCobros.setEnabled(!b);
        this.btnVentas.setEnabled(!b);
        this.btnUsuarios.setEnabled(!b);
        this.jMenuArchivo.setEnabled(!b);
        this.jMenuEdicion.setEnabled(!b);
    }

}
