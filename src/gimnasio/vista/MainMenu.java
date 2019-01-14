/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;


import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.sun.jna.Native;
import gimnasio.controlador.ControladorHuella;
import gimnasio.controlador.ControladorPrincipal;
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
    
//    jInternalLogueo panelLogin = new jInternalLogueo();
//    jInternalProductos panelProductos = new jInternalProductos();
//    jInternalInformes panelInformes = new jInternalInformes();
    
    boolean mostrar = false;    
    public static Boolean detenerEscaner = true;
    
    public void cerrarSesion(){
        this.btnAsistencia.setEnabled(false);
        this.btnCobros.setEnabled(false);
        this.btnVentas.setEnabled(false);
        this.btnUsuarios.setEnabled(false);
    }
    
    ControladorPrincipal miControlador;
    ControladorHuella lector;
    Thread thread_object;
    /**
     * Creates new form MainMenu
     * @param controlador
     */
    
    public MainMenu(ControladorPrincipal controlador) {
        this.miControlador = controlador;
        initComponents();
        this.btnAsistencia.setEnabled(mostrar);
        this.btnCobros.setEnabled(mostrar);
        this.btnUsuarios.setEnabled(mostrar);
//        jDesktopPane1.add(panelTemporal);
//        jDesktopPane1.add(panelLogin);
//        jDesktopPane1.add(panelInformes);
//        jDesktopPane1.add(panelProductos);
//        jDesktopPane1.add(panelClases);
        lector = new ControladorHuella(controlador.getMiPersistencia(),this.txtHabilitado);
        thread_object =new Thread(lector);
        thread_object.start();
        
        this.setBtnsVisibility(false);
        
        Dimension desktopSize = jDesktopPane1.getSize();
//        Dimension loginSize = panelLogin.getSize();
       
//        panelLogin.setLocation((desktopSize.width - loginSize.width)/2, (desktopSize.height - loginSize.height)/2);
//        panelLogin.setVisible(true);
    
    }
    
    
//    public jInternalLogueo getjInternalLogueo(){
//     return this.panelLogin;
//    }
    
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
        btnUsuarios = new javax.swing.JButton();
        btnAlumnos = new javax.swing.JButton();
        btnProfesores = new javax.swing.JButton();
        btnClases = new javax.swing.JButton();
        btnClasesProfesor = new javax.swing.JButton();
        btnModalidad = new javax.swing.JButton();
        btnAsistencia = new javax.swing.JButton();
        btnCobros = new javax.swing.JButton();
        btnClaseALumno = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
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
        menuUsuarios = new javax.swing.JMenu();
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
        menuClases = new javax.swing.JMenu();
        itemNuevaClase = new javax.swing.JMenuItem();
        itemListarClases = new javax.swing.JMenuItem();
        itemEliminarClase = new javax.swing.JMenuItem();
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
        setMinimumSize(new java.awt.Dimension(920, 600));
        setName("Sistema Country GYM"); // NOI18N
        setSize(new java.awt.Dimension(920, 600));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(2, 2));

        btnUsuarios.setText("Usuarios");
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });
        jPanel2.add(btnUsuarios);

        btnAlumnos.setText("Alumnos");
        btnAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlumnosActionPerformed(evt);
            }
        });
        jPanel2.add(btnAlumnos);

        btnProfesores.setText("Profesores");
        btnProfesores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfesoresActionPerformed(evt);
            }
        });
        jPanel2.add(btnProfesores);

        btnClases.setText("Clases");
        btnClases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClasesActionPerformed(evt);
            }
        });
        jPanel2.add(btnClases);

        btnClasesProfesor.setText("<HTML><center>Clases por<br>Profesor</center></HTML>");
        btnClasesProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClasesProfesorActionPerformed(evt);
            }
        });
        jPanel2.add(btnClasesProfesor);

        btnModalidad.setText("Modalidades");
        btnModalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModalidadActionPerformed(evt);
            }
        });
        jPanel2.add(btnModalidad);

        btnAsistencia.setText("Asistencia");
        btnAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsistenciaActionPerformed(evt);
            }
        });
        jPanel2.add(btnAsistencia);

        btnCobros.setText("<html><center>Gestion<br>de Cuotas</center></html>");
        btnCobros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrosActionPerformed(evt);
            }
        });
        jPanel2.add(btnCobros);

        btnClaseALumno.setText("<html><center>Alumnos<br>por Clase</center></");
        btnClaseALumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClaseALumnoActionPerformed(evt);
            }
        });
        jPanel2.add(btnClaseALumno);

        jButton1.setText("<HTML><center>Modalidades<br>por Profesor</center></HTML>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

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
        jDesktopPane1.setMinimumSize(new java.awt.Dimension(500, 450));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jMenuArchivo.setText("Archivo");

        menuUsuarios.setText("Usuarios");
        menuUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuUsuariosMouseClicked(evt);
            }
        });
        menuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUsuariosActionPerformed(evt);
            }
        });

        jMenuItem3.setText("Listar Usuarios");
        menuUsuarios.add(jMenuItem3);

        jMenuItem1.setText("Nuevo Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuUsuarios.add(jMenuItem1);

        jMenuItem2.setText("Eliminar Usuario");
        menuUsuarios.add(jMenuItem2);

        jMenuArchivo.add(menuUsuarios);

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

        menuClases.setText("Clases");

        itemNuevaClase.setText("Nueva Clase");
        itemNuevaClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevaClaseActionPerformed(evt);
            }
        });
        menuClases.add(itemNuevaClase);

        itemListarClases.setText("Listar Clases");
        menuClases.add(itemListarClases);

        itemEliminarClase.setText("Eliminar Clase");
        menuClases.add(itemEliminarClase);

        jMenuArchivo.add(menuClases);

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
        jInternalCobroCuotas panelCuotas = new jInternalCobroCuotas(this.miControlador);
        this.jDesktopPane1.add(panelCuotas);
        panelCuotas.setVisible(true);
    }//GEN-LAST:event_btnCobrosActionPerformed

    private void btnAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsistenciaActionPerformed
//        panelTemporal.setVisible(true);
    }//GEN-LAST:event_btnAsistenciaActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        lector.stop();
        this.detenerEscaner = false;
        jInternalUsuarios panelUsuarios = new jInternalUsuarios(this.miControlador);
        panelUsuarios.setVisible(true);
        this.jDesktopPane1.add(panelUsuarios);
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

    private void menuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUsuariosActionPerformed
        lector.stop();
        this.detenerEscaner = false;
        jInternalUsuarios panelUsuarios = new jInternalUsuarios(miControlador);
        this.jDesktopPane1.add(panelUsuarios);
        panelUsuarios.setVisible(true);
    }//GEN-LAST:event_menuUsuariosActionPerformed

    private void menuUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUsuariosMouseClicked
        jInternalUsuarios panelUsuarios = new jInternalUsuarios(miControlador);
        this.jDesktopPane1.add(panelUsuarios);
        panelUsuarios.setVisible(true);
    }//GEN-LAST:event_menuUsuariosMouseClicked

    private void btnAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlumnosActionPerformed
        jInternalAlumno panelAlumnos =  new jInternalAlumno(miControlador);
        this.jDesktopPane1.add(panelAlumnos);
        panelAlumnos.setVisible(true);
    }//GEN-LAST:event_btnAlumnosActionPerformed

    private void btnProfesoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfesoresActionPerformed
        jInternalProfesores panelProfesores = new jInternalProfesores(miControlador);
        this.jDesktopPane1.add(panelProfesores);
        panelProfesores.setVisible(true);
    }//GEN-LAST:event_btnProfesoresActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        if(detenerEscaner == true){
            if(!lector.isIniciado()){
                lector.start();
                lector.Iniciar();
            }
        }
    }//GEN-LAST:event_formMouseMoved

    private void itemNuevaClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevaClaseActionPerformed
        jInternalClasesProfesor panelClases = new jInternalClasesProfesor(this.miControlador);
        this.jDesktopPane1.add(panelClases);
        panelClases.setVisible(true);
    }//GEN-LAST:event_itemNuevaClaseActionPerformed

    private void btnClasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClasesActionPerformed
        jInternalClases panelClases = new jInternalClases(this.miControlador);
        this.jDesktopPane1.add(panelClases);
        panelClases.setVisible(true);
    }//GEN-LAST:event_btnClasesActionPerformed

    private void btnClasesProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClasesProfesorActionPerformed
        jInternalClasesProfesor panelClaseProfesor = new jInternalClasesProfesor(this.miControlador);
        this.jDesktopPane1.add(panelClaseProfesor);
        panelClaseProfesor.setVisible(true);
    }//GEN-LAST:event_btnClasesProfesorActionPerformed

    private void btnModalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModalidadActionPerformed
        jInternalModalidades panelModalidad = new jInternalModalidades(this.miControlador);
        this.jDesktopPane1.add(panelModalidad);
        panelModalidad.setVisible(true);
    }//GEN-LAST:event_btnModalidadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jInternalProfesorModalidad panelProfeMod = new jInternalProfesorModalidad(this.miControlador);
        this.jDesktopPane1.add(panelProfeMod);
        panelProfeMod.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnClaseALumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClaseALumnoActionPerformed
        jInternalClasesAlumno ventanaClaseAlumn = new jInternalClasesAlumno(this.miControlador);
        this.jDesktopPane1.add(ventanaClaseAlumn);
        ventanaClaseAlumn.setVisible(true);
    }//GEN-LAST:event_btnClaseALumnoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirPuerta;
    private javax.swing.JButton btnAlumnos;
    private javax.swing.JButton btnAsistencia;
    private javax.swing.JButton btnClaseALumno;
    private javax.swing.JButton btnClases;
    private javax.swing.JButton btnClasesProfesor;
    private javax.swing.JButton btnCobros;
    private javax.swing.JButton btnModalidad;
    private javax.swing.JButton btnProfesores;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JMenuItem itemEliminarClase;
    private javax.swing.JMenuItem itemListarClases;
    private javax.swing.JMenuItem itemNuevaClase;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JMenu menuClases;
    private javax.swing.JMenu menuUsuarios;
    private javax.swing.JTextField txtHabilitado;
    // End of variables declaration//GEN-END:variables
 
    public void setBtnsVisibility(boolean b) {
        this.btnAsistencia.setEnabled(!b);
        this.btnCobros.setEnabled(!b);
        this.btnUsuarios.setEnabled(!b);
        this.jMenuArchivo.setEnabled(!b);
        this.jMenuEdicion.setEnabled(!b);
    }

}
