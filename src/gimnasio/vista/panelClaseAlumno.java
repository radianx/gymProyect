/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;
import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.Clase;
import gimnasio.modelo.ClaseAlumno;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.HorarioAlumno;
import gimnasio.modelo.HorarioProfesor;
import gimnasio.modelo.Modalidad;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Family
 */
public class panelClaseAlumno extends javax.swing.JPanel {

    DefaultTableModel modeloTablaAlumnos;
    DefaultTableModel modeloTablaHorarios;
    DefaultTableModel modeloTablaHorarioClase;

    ControladorPrincipal miControlador;
    TableRowSorter<TableModel> rowSorterClases;
    TableRowSorter<TableModel> rowSorterAlumnos;
    ClaseProfesor claseSeleccionada;
    Alumno alumnoSeleccionado;

    public panelClaseAlumno(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        cargarAlumnos();
        cargarTablaHorariosAlumno();
        cargarTablaHorarios();
        cargarComboClaseProfesor();
        rowSorterAlumnos = new TableRowSorter<>(this.tablaAlumnos.getModel());
        tablaAlumnos.setRowSorter(rowSorterClases);
    }

    public void cargarAlumnos() {
        try {
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Apellido");
            Object[] fila = new Object[2];

            for (Alumno miAlumno : miControlador.getListaAlumnos()) {
                if (miAlumno.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = miAlumno;
                    fila[1] = miAlumno.getApellidoalumno();
                    modeloTabla.addRow(fila);
                }
            }

            this.tablaAlumnos.setModel(modeloTabla);
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tablaAlumnos.getModel());
            this.tablaAlumnos.setRowSorter(rowSorter);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void cargarComboClaseProfesor() {
        cmbClases.addItem("--Seleccionar--");
        try {
            for (ClaseProfesor clase : miControlador.getListaClaseProfesor()) {
                if (clase.getEstado().equalsIgnoreCase("ACTIVO")) {
                    if (clase.getClase().getTipoclase().equalsIgnoreCase("LIBRE")) {
                        cmbClases.addItem(clase.toString() + "-Libre-");
                    } else {
                        cmbClases.addItem(clase.toString());
                    }
                }
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void cargarTablaHorariosAlumno() {
        this.modeloTablaHorarioClase = new DefaultTableModel();
        modeloTablaHorarioClase.addColumn("Dia");
        modeloTablaHorarioClase.addColumn("Inicio");
        modeloTablaHorarioClase.addColumn("Fin");
        modeloTablaHorarioClase.addColumn("Profesor");
        modeloTablaHorarioClase.addColumn("Es Promocion");
        this.tablaSuperiorHorarios.setModel(modeloTablaHorarioClase);
    }

    public void cargarTablaHorarios() {
        modeloTablaHorarios = new DefaultTableModel();
        modeloTablaHorarios.addColumn("Dia");
        modeloTablaHorarios.addColumn("Inicio");
        modeloTablaHorarios.addColumn("Fin");
        modeloTablaHorarios.addColumn("Profesor");
        modeloTablaHorarios.addColumn("Es Promocion");
        this.tablaHorariosAlu.setModel(modeloTablaHorarios);
    }

    public void cargarTablaHorarios(Set<HorarioProfesor> horariosProfe) {
        this.modeloTablaHorarioClase.setRowCount(0);
//        modeloTablaHorarios = new DefaultTableModel();
//        modeloTablaHorarios.addColumn("Dia");
//        modeloTablaHorarios.addColumn("Inicio");
//        modeloTablaHorarios.addColumn("Fin");
//        modeloTablaHorarios.addColumn("Profesor");
//        modeloTablaHorarios.addColumn("Es Promocion");
//        this.tablaSuperiorHorarios.setModel(modeloTablaHorarios);
        Object[] fila = new Object[5];
        for (HorarioProfesor horarioProfesor : horariosProfe) {

            fila[0] = horarioProfesor;
            fila[1] = horarioProfesor.getInicioString();

            fila[2] = horarioProfesor.getFinString();

            fila[3] = horarioProfesor.getClaseProfesor().getProfesor().getNombreprofesor() + " "
                    + horarioProfesor.getClaseProfesor().getProfesor().getApellidoprofesor();
            fila[4] = horarioProfesor.getPromocion();
            this.modeloTablaHorarioClase.addRow(fila);
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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaHorariosAlu = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtAlumno = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtClase = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        btnInscribir = new javax.swing.JButton();
        btnInscribir1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtBuscarAlumno = new javax.swing.JTextField();
        btnBuscarAlumno = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaSuperiorHorarios = new javax.swing.JTable();
        cmbClases = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnPromociones = new javax.swing.JButton();
        cmbDiasPorSemana = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(600, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane6.setBackground(new java.awt.Color(255, 204, 102));
        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("Horarios"));

        tablaHorariosAlu.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaHorariosAlu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHorariosAluMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaHorariosAluMouseEntered(evt);
            }
        });
        jScrollPane6.setViewportView(tablaHorariosAlu);

        jLabel12.setText("Alumno:");

        jLabel14.setText("$");

        jLabel13.setText("Precio:");

        txtPrecio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(51, 153, 0));
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        txtAlumno.setEditable(false);
        txtAlumno.setBackground(new java.awt.Color(255, 204, 51));
        txtAlumno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel16.setText("Clase:");

        txtClase.setEditable(false);
        txtClase.setBackground(new java.awt.Color(255, 204, 51));
        txtClase.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jCheckBox1.setBackground(new java.awt.Color(255, 204, 102));
        jCheckBox1.setText("Precio de Promoción");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btnInscribir.setText("INSCRIBIR ALUMNO");
        btnInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscribirActionPerformed(evt);
            }
        });
        jPanel3.add(btnInscribir);

        btnInscribir1.setText("MODIFICAR UNA INSCRIPCION");
        btnInscribir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscribir1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnInscribir1);

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancelar);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAlumno)
                                    .addComponent(txtClase)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jCheckBox1))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox1)
                        .addGap(4, 4, 4))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBuscarAlumno.setMinimumSize(new java.awt.Dimension(109, 20));
        txtBuscarAlumno.setPreferredSize(new java.awt.Dimension(150, 20));
        txtBuscarAlumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAlumnoKeyReleased(evt);
            }
        });

        btnBuscarAlumno.setText("Buscar");
        btnBuscarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAlumnoActionPerformed(evt);
            }
        });

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar Alumno"));

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(tablaAlumnos);

        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder("Horarios de clases"));

        tablaSuperiorHorarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaSuperiorHorarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaSuperiorHorariosMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tablaSuperiorHorarios);

        cmbClases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClasesActionPerformed(evt);
            }
        });

        jLabel17.setText("Clase:");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel5.add(btnAgregar);

        btnQuitar.setText("QUITAR");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
        jPanel5.add(btnQuitar);

        btnPromociones.setText("VER PROMOCIONES");
        btnPromociones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromocionesActionPerformed(evt);
            }
        });
        jPanel5.add(btnPromociones);

        cmbDiasPorSemana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        cmbDiasPorSemana.setEnabled(false);
        cmbDiasPorSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDiasPorSemanaActionPerformed(evt);
            }
        });

        jLabel1.setText("Dias por Semana:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbClases, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDiasPorSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarAlumno)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbClases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDiasPorSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel4, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarAlumnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAlumnoKeyReleased
        String text = this.txtBuscarAlumno.getText();
        if (text.trim().length() == 0) {
            rowSorterAlumnos.setRowFilter(null);
        } else {
            rowSorterAlumnos.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtBuscarAlumnoKeyReleased

    private void tablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseClicked
        if (!tablaAlumnos.getSelectionModel().isSelectionEmpty()) {
            alumnoSeleccionado = (Alumno) tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0);
            this.txtAlumno.setText(alumnoSeleccionado.getNombrealumno() + " " + alumnoSeleccionado.getApellidoalumno());
        }
    }//GEN-LAST:event_tablaAlumnosMouseClicked

    private void btnInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInscribirActionPerformed
        if (alumnoSeleccionado != null && claseSeleccionada != null) {
            try {
                Double precio = Double.valueOf(this.txtPrecio.getText());
                int dias = 1;
//Verificacion de si es o por dia
                if (this.cmbDiasPorSemana.isEnabled()) {
                    dias = Integer.valueOf(this.cmbDiasPorSemana.getSelectedItem().toString());
                } else {
                    dias = modeloTablaHorarios.getRowCount();
                }

//Seccion de clasesAlumno
                ClaseAlumno claseAlumno = new ClaseAlumno(alumnoSeleccionado, claseSeleccionada, precio, dias, "ACTIVO");
                miControlador.altaClaseAlumno(claseAlumno);
                claseSeleccionada.getClaseAlumnos().add(claseAlumno);
                alumnoSeleccionado.getClaseAlumnos().add(claseAlumno);
                miControlador.actualizarClaseProfesor(claseSeleccionada);
                int rows = this.tablaHorariosAlu.getRowCount();

//Seccion de horarioAlumno
                for (int i = 0; i < rows; i++) {
                    HorarioProfesor horario = (HorarioProfesor) this.modeloTablaHorarios.getValueAt(i, 0);
                    HorarioAlumno unHorario = new HorarioAlumno(claseAlumno, horario.getInicio(), horario.getFin());
                    try {
                        System.out.println("Dando de alta horario_alumno");
                        unHorario.setEstado("ACTIVO");
                        claseAlumno.getHorarios().add(unHorario);
                        miControlador.altaHorarioAlumno(unHorario);
                    } catch (Notificaciones ex) {
                        JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                        ex.printStackTrace();
                    }
                }
//Generacion de Cuotas
                String[] opciones = {"SI", "NO"};
                int seleccion = JOptionPane.showOptionDialog(null, "¿Desea generar una Cuota?", "Seleccione una opcion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
                switch (seleccion) {
                    case 0:
                        jInternalCuotas cuotas = new jInternalCuotas(this.miControlador, claseAlumno.getAlumno(), claseAlumno.getClaseProfesor(), precio);
                        cuotas.setVisible(true);
                        this.getParent().getParent().getParent().getParent().getParent().add(cuotas);
                        this.setVisible(false);
                        cuotas.toFront();
                        break;
                    case 1: //salio por el no
                        this.setVisible(false);
                        break;
                }
//Manejo de errores
            } catch (Notificaciones ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                System.out.println("AL FINAL");
                ex.printStackTrace();
                this.cargarAlumnos();
                this.cargarComboClaseProfesor();
                this.cargarTablaHorarios();
                this.cargarTablaHorariosAlumno();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un precio valido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Faltan completar datos para la inscripcion.");
        }
        cargarAlumnos();

    }//GEN-LAST:event_btnInscribirActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void btnBuscarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAlumnoActionPerformed
        String text = this.txtBuscarAlumno.getText();
        if (text.trim().length() == 0) {
            rowSorterAlumnos.setRowFilter(null);
        } else {
            rowSorterAlumnos.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_btnBuscarAlumnoActionPerformed

    private void cmbDiasPorSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDiasPorSemanaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDiasPorSemanaActionPerformed

    private void tablaHorariosAluMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHorariosAluMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaHorariosAluMouseClicked

    private void tablaSuperiorHorariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSuperiorHorariosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaSuperiorHorariosMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (!this.tablaSuperiorHorarios.getSelectionModel().isSelectionEmpty()) {
            HorarioProfesor horario = (HorarioProfesor) this.modeloTablaHorarioClase.getValueAt(tablaSuperiorHorarios.getSelectedRow(), 0);
            modeloTablaHorarioClase.removeRow(tablaSuperiorHorarios.getSelectedRow());

            Object[] rowData = new Object[5];

            rowData[0] = horario;
            rowData[1] = horario.getInicioString();
            rowData[2] = horario.getFinString();

            rowData[3] = horario.getClaseProfesor().getProfesor().getNombreprofesor() + " "
                    + horario.getClaseProfesor().getProfesor().getApellidoprofesor();

            rowData[4] = horario.getPromocion();
            modeloTablaHorarios.addRow(rowData);
            this.tablaHorariosAlu.setModel(modeloTablaHorarios);
            this.cmbClases.setEnabled(false);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        if (!this.tablaHorariosAlu.getSelectionModel().isSelectionEmpty()) {
            HorarioProfesor horario = (HorarioProfesor) this.modeloTablaHorarioClase.getValueAt(tablaHorariosAlu.getSelectedRow(), 0);

            modeloTablaHorarioClase.removeRow(tablaHorariosAlu.getSelectedRow());
            Object[] rowData = new Object[5];

            rowData[0] = horario;
            rowData[1] = horario.getInicioString();
            rowData[2] = horario.getFinString();

            rowData[3] = horario.getClaseProfesor().getProfesor().getNombreprofesor() + " "
                    + horario.getClaseProfesor().getProfesor().getApellidoprofesor();
            rowData[4] = horario.getPromocion();

            modeloTablaHorarios.addRow(rowData);
            this.tablaSuperiorHorarios.setModel(modeloTablaHorarios);
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void tablaAlumnosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseEntered

    }//GEN-LAST:event_tablaAlumnosMouseEntered

    private void tablaHorariosAluMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHorariosAluMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaHorariosAluMouseEntered

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnInscribir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInscribir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInscribir1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.cmbClases.setEnabled(true);
        this.tablaAlumnos.getSelectionModel().clearSelection();
        this.cargarTablaHorarios();
        this.cargarTablaHorariosAlumno();
        this.txtAlumno.setText("");
        this.txtBuscarAlumno.setText("");
        this.txtClase.setText("");
        this.txtPrecio.setText("");
        this.alumnoSeleccionado = null;
        this.jCheckBox1.setSelected(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbClasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClasesActionPerformed
        String text = cmbClases.getSelectedItem().toString();
        System.out.println("Seleccionando: " + text);
        if (text.equalsIgnoreCase("--Seleccionar--")) {
            this.modeloTablaHorarioClase.setRowCount(0);
        }
        if (text.contains("-Libre-")) {
            this.cmbDiasPorSemana.setEnabled(true);
            text = text.replace("-Libre-", "");
            System.out.println(text);
        }

        try {
            List<ClaseProfesor> clase = miControlador.dameClaseProfesor(text);
            for (ClaseProfesor claseProf : clase) {
                this.cargarTablaHorarios(claseProf.getHorarios());
                claseSeleccionada = claseProf;
                this.txtClase.setText(claseSeleccionada.toString());
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }

    }//GEN-LAST:event_cmbClasesActionPerformed

    private void btnPromocionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromocionesActionPerformed
        MainMenu menu = (MainMenu) SwingUtilities.getWindowAncestor(this);
        menu.abrirPromociones();
    }//GEN-LAST:event_btnPromocionesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarAlumno;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnInscribir;
    private javax.swing.JButton btnInscribir1;
    private javax.swing.JButton btnPromociones;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox<String> cmbClases;
    private javax.swing.JComboBox<String> cmbDiasPorSemana;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTable tablaHorariosAlu;
    private javax.swing.JTable tablaSuperiorHorarios;
    private javax.swing.JTextField txtAlumno;
    private javax.swing.JTextField txtBuscarAlumno;
    private javax.swing.JTextField txtClase;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
