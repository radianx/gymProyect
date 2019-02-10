/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Clase;
import gimnasio.modelo.ClaseAlumno;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.HorarioProfesor;
import gimnasio.modelo.Modalidad;
import gimnasio.modelo.Profesor;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
 * @author adrian
 */
public class JInternalClasesProfesor extends javax.swing.JInternalFrame {

    ControladorPrincipal miControlador;
    panelClaseProfesorHorario panelClaseProfesorHorario;
    panelClaseProfesor panelNewClaseProfesor;
    DefaultTableModel modeloTablaHorarios;
    DefaultTableModel modeloTablaClaseProfesor;
    TableRowSorter<TableModel> rowSorter;
    String text = "";

    public Profesor profesorSeleccionado;
    public Clase claseSeleccionada;
    public Modalidad modalidadSeleccionada;
    public ClaseProfesor claseProfesor;

    public JInternalClasesProfesor(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        cargarTablaClaseProfesores();
        cargarTablaHorarios();
        panelClaseProfesorHorario = new panelClaseProfesorHorario(miControlador);
        panelNewClaseProfesor = new panelClaseProfesor(miControlador, panelClaseProfesorHorario);

        this.add(panelClaseProfesorHorario);
        this.add(panelNewClaseProfesor);
    }

    public void cargarTablaHorarios() {
        modeloTablaHorarios = new DefaultTableModel();
        modeloTablaHorarios.addColumn("Dia");
        modeloTablaHorarios.addColumn("Inicio");
        modeloTablaHorarios.addColumn("Fin");
        modeloTablaHorarios.addColumn("Es Promocion");
        this.tablaHorarios.setModel(modeloTablaHorarios);
    }

    public void cargarTablaHorarios(ClaseProfesor claseProfesor) {
        try {
            modeloTablaHorarios = new DefaultTableModel();
            modeloTablaHorarios.addColumn("Dia");
            modeloTablaHorarios.addColumn("Inicio");
            modeloTablaHorarios.addColumn("Fin");
            modeloTablaHorarios.addColumn("Es Promocion");
            Object[] fila = new Object[4];
            for (HorarioProfesor unHorario : miControlador.getListaHorarios()) {
                if (unHorario.getClaseProfesor().getIdclaseprofesor() == claseProfesor.getIdclaseprofesor()) {
                    if (unHorario.getEstado().equalsIgnoreCase("ACTIVO")) {
                        fila[0] = unHorario;
                        fila[1] = unHorario.getInicioString();
                        fila[2] = unHorario.getFinString();
                        fila[3] = unHorario.getPromocion();
                        modeloTablaHorarios.addRow(fila);
                    }
                }
            }
            this.tablaHorarios.setModel(modeloTablaHorarios);
        } catch (Notificaciones ex) {
            ex.printStackTrace();
        }
    }

    public void cargarTablaClaseProfesores() {
        try {
            modeloTablaClaseProfesor = new DefaultTableModel();
            modeloTablaClaseProfesor.addColumn("Clase");
            modeloTablaClaseProfesor.addColumn("Nombre");
            modeloTablaClaseProfesor.addColumn("Apellido");
            Object[] fila = new Object[3];

            for (ClaseProfesor claseProfe : miControlador.getListaClaseProfesor()) {
                if (claseProfe.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = claseProfe;
                    fila[1] = claseProfe.getProfesor();
                    fila[2] = claseProfe.getProfesor().getApellidoprofesor();
                    modeloTablaClaseProfesor.addRow(fila);
                }
            }
            this.tablaClasesProfesores.setModel(modeloTablaClaseProfesor);
            rowSorter = new TableRowSorter<>(this.tablaClasesProfesores.getModel());
            tablaClasesProfesores.setRowSorter(rowSorter);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaClasesProfesores = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEliminarClase = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHorarios = new javax.swing.JTable();
        btnNuevo1 = new javax.swing.JButton();
        btnEliminarHorario = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setTitle("GESTION PROFESORES POR CLASE");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gimnasio/imagenes/countryIcon.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(600, 426));
        setPreferredSize(new java.awt.Dimension(600, 426));
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

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Clases y Profesores"));

        tablaClasesProfesores.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaClasesProfesores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClasesProfesoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaClasesProfesoresMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaClasesProfesoresMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaClasesProfesores);

        btnNuevo.setText("<HTML><CENTER>NUEVA<BR>CLASE</CENTER></HTML>");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminarClase.setText("<HTML><CENTER>ELIMINAR<BR>CLASE</CENTER></HTML>");
        btnEliminarClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClaseActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Horarios de Clase"));

        tablaHorarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaHorarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHorariosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaHorariosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaHorarios);

        btnNuevo1.setText("<HTML><CENTER>MODIFICAR<BR>HORARIO</CENTER></HTML>");
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        btnEliminarHorario.setText("<HTML><CENTER>ELIMINAR<BR>HORARIO</CENTER></HTML>");
        btnEliminarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarHorarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(10, 10, 10)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        btnBuscar.setText("BUSCAR");
        jPanel1.add(btnBuscar);

        panelPrincipal.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        panelNewClaseProfesor.reLoad();
        String[] opciones = {"SI", "NO"};
        int seleccion = JOptionPane.showOptionDialog(null,
                "¿La clase va a ser de Horario Libre?",
                "Seleccione una opcion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, opciones, opciones[0]);
        if (seleccion == 1) {
            cambiarPanel(this.panelPrincipal, panelNewClaseProfesor);
        }
        if (seleccion == 0) {
            panelNewClaseProfesor.cargarLibre();
            cambiarPanel(this.panelPrincipal, panelNewClaseProfesor);
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClaseActionPerformed
        if (!this.tablaClasesProfesores.getSelectionModel().isSelectionEmpty()) {
            try {
                List<ClaseAlumno> alumnosSinClase = miControlador.bajaClaseProfesor((ClaseProfesor) tablaClasesProfesores.getValueAt(tablaClasesProfesores.getSelectedRow(), 0));
                miControlador.actualizarHorariosDeAlumnos(true, (ClaseProfesor) tablaClasesProfesores.getValueAt(tablaClasesProfesores.getSelectedRow(), 0));
                for (ClaseAlumno alu : alumnosSinClase) {
                    System.out.println(alu);
                }
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        cargarTablaClaseProfesores();
                        cargarTablaHorarios();
                    }
                });
            } catch (Notificaciones ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Horario para eliminar");
        }
    }//GEN-LAST:event_btnEliminarClaseActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        text = this.txtBuscar.getText();
        if (text.trim().length() == 0) {
            rowSorter = new TableRowSorter<>(this.tablaClasesProfesores.getModel());
            rowSorter.setRowFilter(null);
            tablaClasesProfesores.setRowSorter(rowSorter);
        } else {
            rowSorter = new TableRowSorter<>(this.tablaClasesProfesores.getModel());
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            tablaClasesProfesores.setRowSorter(rowSorter);
        }
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        text = this.txtBuscar.getText();
        if (text.trim().length() == 0) {
            rowSorter = new TableRowSorter<>(this.tablaClasesProfesores.getModel());
            rowSorter.setRowFilter(null);
            tablaClasesProfesores.setRowSorter(rowSorter);
        } else {
            rowSorter = new TableRowSorter<>(this.tablaClasesProfesores.getModel());
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            tablaClasesProfesores.setRowSorter(rowSorter);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void panelPrincipalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMouseEntered
        if (!panelNewClaseProfesor.isVisible()) {
            cambiarPanel(panelNewClaseProfesor, panelPrincipal);
            this.txtBuscar.grabFocus();
        }
    }//GEN-LAST:event_panelPrincipalMouseEntered

    private void panelPrincipalComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelPrincipalComponentShown

    }//GEN-LAST:event_panelPrincipalComponentShown

    private void tablaClasesProfesoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClasesProfesoresMouseClicked
        if (!tablaClasesProfesores.getSelectionModel().isSelectionEmpty()) {
            claseProfesor = (ClaseProfesor) tablaClasesProfesores.getValueAt(tablaClasesProfesores.getSelectedRow(), 0);
            this.cargarTablaHorarios(claseProfesor);
            profesorSeleccionado = (Profesor) tablaClasesProfesores.getValueAt(tablaClasesProfesores.getSelectedRow(), 1);
            this.modalidadSeleccionada = claseProfesor.getModalidad();
            claseSeleccionada = claseProfesor.getClase();
        }
    }//GEN-LAST:event_tablaClasesProfesoresMouseClicked

    private void tablaClasesProfesoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClasesProfesoresMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaClasesProfesoresMouseReleased

    private void tablaHorariosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHorariosMouseReleased
        this.btnEliminarClase.setEnabled(true);
    }//GEN-LAST:event_tablaHorariosMouseReleased

    private void tablaHorariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHorariosMouseClicked
    }//GEN-LAST:event_tablaHorariosMouseClicked

    private void tablaClasesProfesoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClasesProfesoresMouseEntered
        cargarTablaClaseProfesores();
    }//GEN-LAST:event_tablaClasesProfesoresMouseEntered

    private void btnEliminarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarHorarioActionPerformed
        if (!tablaHorarios.getSelectionModel().isSelectionEmpty()) {
            try {
                miControlador.bajaHorarioProfesor((HorarioProfesor) tablaHorarios.getValueAt(tablaHorarios.getSelectedRow(), 0));
                modeloTablaHorarios.removeRow(tablaHorarios.getSelectedRow());
            } catch (Notificaciones ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnEliminarHorarioActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        if (profesorSeleccionado != null && modalidadSeleccionada != null && claseSeleccionada != null) {
            cambiarPanel(panelPrincipal, panelClaseProfesorHorario);
            List<HorarioProfesor> horarios = new ArrayList<>();
            for (int i = 0; i < modeloTablaHorarios.getRowCount(); i++) {
                horarios.add((HorarioProfesor) tablaHorarios.getValueAt(i, 0));
            }
            panelClaseProfesorHorario.recibirDatos(claseProfesor, profesorSeleccionado, modalidadSeleccionada, claseSeleccionada, horarios);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una clase para agregar un horario");
        }
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminarClase;
    private javax.swing.JButton btnEliminarHorario;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tablaClasesProfesores;
    private javax.swing.JTable tablaHorarios;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
    private void cambiarPanel(JPanel panelActual, JPanel panelCambio) {
        panelActual.setVisible(false);
        panelCambio.setVisible(true);
        // this.pack();
    }

}
