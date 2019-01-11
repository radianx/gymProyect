/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.Clase;
import gimnasio.modelo.ClaseAlumno;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author adrian
 */
public class jInternalClasesAlumno extends javax.swing.JInternalFrame {

    ControladorPrincipal miControlador;
    panelClaseAlumno panelNewClaseAlumno;
    DefaultTableModel modeloTablaPrincipal;
    DefaultTableModel modeloTablaAlumnos;
    String text = "";
    
    public jInternalClasesAlumno(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        cargarTablaPrincipal();
        panelNewClaseAlumno = new panelClaseAlumno(miControlador);
        this.add(panelNewClaseAlumno);
    }

    public void cargarTablaPrincipal(){
        modeloTablaPrincipal = new DefaultTableModel();
        modeloTablaPrincipal.addColumn("Clase");
        modeloTablaPrincipal.addColumn("Profesor");
        modeloTablaPrincipal.addColumn("Dia");
        modeloTablaPrincipal.addColumn("Inicio");
        modeloTablaPrincipal.addColumn("Fin");
        modeloTablaPrincipal.addColumn("Modalidad");
        modeloTablaPrincipal.addColumn("Precio");
        this.tablaPrincipal.setModel(modeloTablaPrincipal);
        Object[]fila = new Object[7];
        for(ClaseProfesor claseProfesor:this.miControlador.getListaClaseProfesor()){
            if(claseProfesor.getEstado().equalsIgnoreCase("ACTIVO")){
                fila[0] = claseProfesor;
                fila[1] = claseProfesor.getProfesor().getNombreprofesor() + " " + claseProfesor.getProfesor().getApellidoprofesor();
                fila[2] = new SimpleDateFormat("EEEE", new Locale("es", "ES")).format(claseProfesor.getInicio());
                Calendar cal = Calendar.getInstance();
                cal.setTime(claseProfesor.getInicio());
                String time = new SimpleDateFormat("HH:mm").format(cal.getTime());
                fila[3] = time;
                cal.setTime(claseProfesor.getFin());
                time = new SimpleDateFormat("HH:mm").format(cal.getTime());
                fila[4] = time;
                fila[5] = claseProfesor.getModalidad();
                for(Profesormodalidad profeModa: claseProfesor.getProfesor().getProfesorModalidads()){
                    if(profeModa.getModalidad().equals(claseProfesor.getModalidad())){
                        fila[6] = profeModa.getPreciohora();
                        break;
                    }
                }
            }
        }
    }
    
    public void cargarTablaAlumnos(ClaseProfesor claseProfe){
        modeloTablaAlumnos = new DefaultTableModel();
        modeloTablaAlumnos.addColumn("Nombre");
        modeloTablaAlumnos.addColumn("Apellido");
        this.tablaAlumnos.setModel(modeloTablaAlumnos);
        Object[] fila = new Object[2];
        for (ClaseAlumno claseAlumno : claseProfe.getClaseAlumnos()) {
            if (claseAlumno.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = claseAlumno.getAlumno();
                fila[1] = claseAlumno.getAlumno().getApellidoalumno();
                modeloTablaAlumnos.addRow(fila);
            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPrincipal = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(750, 400));
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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Alumnos de clase seleccionada"));

        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAlumnos);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNuevo.setText("<HTML><CENTER>INSCRIBIR<BR>ALUMNO</CENTER></HTML>");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel5.add(btnNuevo);

        btnEliminar.setText("<HTML><CENTER>DESINSCRIBIR<BR>ALUMNO</CENTER></HTML>");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel5.add(btnEliminar);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Clases"));

        tablaPrincipal.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPrincipalMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaPrincipalMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPrincipal);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
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

        btnBuscar.setText("Buscar Profesor");
        jPanel1.add(btnBuscar);

        panelPrincipal.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        cambiarPanel(this.panelPrincipal, panelNewClaseAlumno);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(!this.tablaAlumnos.getSelectionModel().isSelectionEmpty()){
            try {
                Alumno unAlumno = (Alumno) this.tablaAlumnos.getValueAt(this.tablaAlumnos.getSelectedRow(), 0);
                unAlumno.setEstado("INACTIVO");
                miControlador.bajaAlumno(unAlumno.getIdalumno());
                SwingUtilities.invokeLater(new Runnable(){public void run(){
                    cargarTablaAlumnos((ClaseProfesor) tablaPrincipal.getValueAt(tablaPrincipal.getSelectedRow(),0));
                }});
            } catch (Notificaciones ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una clase para eliminarla");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        text = this.txtBuscar.getText();
        if (text.trim().length() == 0) {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaPrincipal.getModel());
            rowSorter.setRowFilter(null);
            tablaPrincipal.setRowSorter(rowSorter);
        } else {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaPrincipal.getModel());
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            tablaPrincipal.setRowSorter(rowSorter);
        } 
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        text = this.txtBuscar.getText();
        if (text.trim().length() == 0) {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaPrincipal.getModel());
            rowSorter.setRowFilter(null);
            tablaPrincipal.setRowSorter(rowSorter);
        } else {
            TableRowSorter rowSorter = new TableRowSorter<>(this.tablaPrincipal.getModel());
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            tablaPrincipal.setRowSorter(rowSorter);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void panelPrincipalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMouseEntered
        if(!panelNewClaseAlumno.isVisible()){
            cambiarPanel(panelNewClaseAlumno, panelPrincipal);
            this.txtBuscar.grabFocus();
        }
    }//GEN-LAST:event_panelPrincipalMouseEntered

    private void panelPrincipalComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelPrincipalComponentShown
        this.cargarTablaPrincipal();
    }//GEN-LAST:event_panelPrincipalComponentShown

    private void tablaPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPrincipalMouseClicked
        if(!tablaPrincipal.getSelectionModel().isSelectionEmpty()){
            cargarTablaAlumnos((ClaseProfesor) tablaPrincipal.getValueAt(tablaPrincipal.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_tablaPrincipalMouseClicked

    private void tablaPrincipalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPrincipalMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaPrincipalMouseReleased

    private void tablaAlumnosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseReleased
        this.btnEliminar.setEnabled(true);
    }//GEN-LAST:event_tablaAlumnosMouseReleased

    private void tablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseClicked
    }//GEN-LAST:event_tablaAlumnosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTable tablaPrincipal;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
    private void cambiarPanel(JPanel panelActual, JPanel panelCambio) {
		panelActual.setVisible(false);
                panelCambio.setVisible(true);
		// this.pack();
    }
}
