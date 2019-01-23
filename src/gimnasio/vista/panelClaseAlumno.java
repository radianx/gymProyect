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
import javax.swing.DefaultComboBoxModel;
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
    DefaultTableModel modeloTablaPrincipal;
    DefaultTableModel modeloTablaHorarios;
    DefaultTableModel modeloTablaHorarioClase;
    ControladorPrincipal miControlador;
    TableRowSorter<TableModel> rowSorterClases;
    TableRowSorter<TableModel> rowSorterAlumnos;
    ClaseProfesor claseSeleccionada;
    Alumno alumnoSeleccionado;

    public panelClaseAlumno(ControladorPrincipal controlador) {
        try {
            miControlador = controlador;
            initComponents();
            cargarTablaPrincipal();
            CargadorTabla.alumnosActivos(tablaAlumnos, miControlador);
            cargarTablaHorariosAlumno();
            cargarTablaHorarios();
            rowSorterClases = new TableRowSorter<>(this.tablaPrincipal.getModel());
            tablaPrincipal.setRowSorter(rowSorterClases);
            rowSorterAlumnos = new TableRowSorter<>(this.tablaAlumnos.getModel());
            tablaPrincipal.setRowSorter(rowSorterClases);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }

    public void cargarTablaPrincipal() {
        modeloTablaPrincipal = new DefaultTableModel();
        modeloTablaPrincipal.addColumn("Clase");
        modeloTablaPrincipal.addColumn("Profesor");
        modeloTablaPrincipal.addColumn("Modalidad");
        this.tablaPrincipal.setModel(modeloTablaPrincipal);
        Object[] fila = new Object[3];
        try {
            for (ClaseProfesor claseProfesor : this.miControlador.getListaClaseProfesor()) {
                if (claseProfesor.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = claseProfesor;
                    fila[1] = claseProfesor.getProfesor().getNombreprofesor() + " " + claseProfesor.getProfesor().getApellidoprofesor();
                    fila[2] = claseProfesor.getModalidad();
                    modeloTablaPrincipal.addRow(fila);
                }
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }

    public void cargarTablaHorariosAlumno() {
        this.modeloTablaHorarioClase = new DefaultTableModel();
        modeloTablaHorarioClase.addColumn("Clase");
        modeloTablaHorarioClase.addColumn("Dia");
        modeloTablaHorarioClase.addColumn("Inicio");
        modeloTablaHorarioClase.addColumn("Fin");
        this.tablaHorariosAlumno.setModel(modeloTablaHorarioClase);
    }

    public void cargarTablaHorarios() {
        modeloTablaHorarios = new DefaultTableModel();
        modeloTablaHorarios.addColumn("Clase");
        modeloTablaHorarios.addColumn("Dia");
        modeloTablaHorarios.addColumn("Inicio");
        modeloTablaHorarios.addColumn("Fin");
        this.tablaHorariosClase.setModel(modeloTablaHorarios);
    }

    public void cargarTablaHorarios(Profesor unProfe) {
        modeloTablaHorarios = new DefaultTableModel();
        modeloTablaHorarios.addColumn("Clase");
        modeloTablaHorarios.addColumn("Dia");
        modeloTablaHorarios.addColumn("Inicio");
        modeloTablaHorarios.addColumn("Fin");
        this.tablaHorariosClase.setModel(modeloTablaHorarios);
        Object[] fila = new Object[4];
        List<HorarioProfesor> horarios = null;
        try {
            horarios = miControlador.getListaHorarios();
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if (unProfe != null && horarios !=null) {
            for (ClaseProfesor claseProfesor : unProfe.getClaseProfesors()) {
                for (HorarioProfesor horarioProfesor : horarios) {
                    if (claseProfesor.getEstado().equalsIgnoreCase("ACTIVO")
                            && horarioProfesor.getClaseProfesor().toString().equalsIgnoreCase(claseProfesor.toString())) {
                        fila[0] = claseProfesor;

                        fila[1] = new SimpleDateFormat("EEEE", new Locale("es", "ES")).format(horarioProfesor.getInicio());
                        fila[2] = horarioProfesor;

                        fila[3] = horarioProfesor.getFinString();

                        modeloTablaHorarios.addRow(fila);
                    }
                }
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

        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaPrincipal = new javax.swing.JTable();
        txtBuscarAlumno = new javax.swing.JTextField();
        btnBuscarAlumno = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        btnInscribir = new javax.swing.JButton();
        txtClase = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtAlumno = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtBuscarClase = new javax.swing.JTextField();
        btnBuscarClases = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbDiasPorSemana = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaHorariosClase = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaHorariosAlumno = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(484, 370));
        setPreferredSize(new java.awt.Dimension(484, 370));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar);

        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jLabel5.setText("Alumno:");

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar Clase"));

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
        });
        jScrollPane4.setViewportView(tablaPrincipal);

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

        jLabel12.setText("Alumno:");

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

        btnInscribir.setText("INSCRIBIR ALUMNO A CLASE");
        btnInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscribirActionPerformed(evt);
            }
        });

        txtClase.setEditable(false);

        jLabel16.setText("Clase:");

        txtAlumno.setEditable(false);

        txtPrecio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(51, 153, 0));
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        jLabel13.setText("Precio:");

        jLabel14.setText("$");

        jLabel6.setText("Clase:");

        txtBuscarClase.setMinimumSize(new java.awt.Dimension(109, 20));
        txtBuscarClase.setPreferredSize(new java.awt.Dimension(150, 20));
        txtBuscarClase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClaseKeyReleased(evt);
            }
        });

        btnBuscarClases.setText("Buscar");
        btnBuscarClases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClasesActionPerformed(evt);
            }
        });

        jLabel1.setText("Dias por Semana:");

        cmbDiasPorSemana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        cmbDiasPorSemana.setEnabled(false);
        cmbDiasPorSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDiasPorSemanaActionPerformed(evt);
            }
        });

        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("Horarios"));

        tablaHorariosClase.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaHorariosClase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHorariosClaseMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaHorariosClase);

        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder("Horarios de clases"));

        tablaHorariosAlumno.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaHorariosAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHorariosAlumnoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tablaHorariosAlumno);

        btnAgregar.setText("AGREGAR ->");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setText("<- QUITAR");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarClase, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarClases))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarAlumno)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbDiasPorSemana, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAlumno))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnInscribir, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtClase)))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarAlumno)
                    .addComponent(jLabel6)
                    .addComponent(txtBuscarClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarClases))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbDiasPorSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addGap(3, 3, 3)
                        .addComponent(btnInscribir))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitar)
                        .addGap(56, 56, 56)))
                .addContainerGap())
        );

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!txtClase.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Guardado con exito");
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Error: Profesor no cargado");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.txtBuscarAlumno.setText("");
        this.txtClase.setText("");
        this.txtBuscarClase.setText("");
        this.txtAlumno.setText("");
        this.txtPrecio.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void tablaPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPrincipalMouseClicked

        if (!tablaPrincipal.getSelectionModel().isSelectionEmpty()) {
            claseSeleccionada = (ClaseProfesor) tablaPrincipal.getValueAt(tablaPrincipal.getSelectedRow(), 0);
            this.cargarTablaHorarios(claseSeleccionada.getProfesor());
            this.txtClase.setText(claseSeleccionada.toString());
            
            if(claseSeleccionada.getClase().getDescripcionclase().equalsIgnoreCase("Maquina")){
                this.cmbDiasPorSemana.setEnabled(true);
            }
        }
    }//GEN-LAST:event_tablaPrincipalMouseClicked

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
        if (!tablaPrincipal.getSelectionModel().isSelectionEmpty()) {
            try {
                Double precio = Double.valueOf(this.txtPrecio.getText());
                int dias = 1;
                if (this.cmbDiasPorSemana.isEnabled()) {
                    dias = Integer.valueOf(this.cmbDiasPorSemana.getSelectedItem().toString());
                } else {
                    dias = modeloTablaHorarioClase.getRowCount();
                }
                ClaseAlumno claseAlumno = new ClaseAlumno(alumnoSeleccionado, claseSeleccionada, precio, dias, "ACTIVO");
                miControlador.altaClaseAlumno(claseAlumno);
                claseSeleccionada.getClaseAlumnos().add(claseAlumno);
                miControlador.actualizarClaseProfesor(claseSeleccionada);
                for (int i = this.modeloTablaHorarioClase.getRowCount() - 1; i >= 0; i--) {
                    HorarioProfesor horario = (HorarioProfesor) this.modeloTablaHorarioClase.getValueAt(i, 2);
                    HorarioAlumno unHorario = new HorarioAlumno(claseAlumno, horario.getInicio(), horario.getFin());
                    try {
                        miControlador.altaHorarioAlumno(unHorario);
                    } catch (Notificaciones ex) {
                        JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                    }
                }
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
            } catch (Notificaciones ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_btnInscribirActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtBuscarClaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClaseKeyReleased
        String text = this.txtBuscarClase.getText();
        if (text.trim().length() == 0) {
            rowSorterClases.setRowFilter(null);
        } else {
            rowSorterClases.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtBuscarClaseKeyReleased

    private void btnBuscarClasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClasesActionPerformed
        String text = this.txtBuscarClase.getText();
        if (text.trim().length() == 0) {
            rowSorterClases.setRowFilter(null);
        } else {
            rowSorterClases.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_btnBuscarClasesActionPerformed

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

    private void tablaHorariosClaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHorariosClaseMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaHorariosClaseMouseClicked

    private void tablaHorariosAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHorariosAlumnoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaHorariosAlumnoMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (!this.tablaHorariosClase.getSelectionModel().isSelectionEmpty()) {
            HorarioProfesor horario = (HorarioProfesor) this.modeloTablaHorarios.getValueAt(tablaHorariosClase.getSelectedRow(), 2);
            ClaseProfesor claseProfesor = (ClaseProfesor) this.modeloTablaHorarios.getValueAt(tablaHorariosClase.getSelectedRow(), 0);
            modeloTablaHorarios.removeRow(tablaHorariosClase.getSelectedRow());
            Object[] rowData = new Object[4];
            rowData[0] = claseProfesor;

            rowData[1] = new SimpleDateFormat("EEEE", new Locale("es", "ES")).format(horario.getInicio());
            rowData[2] = horario;

            rowData[3] = horario.getFinString();
            modeloTablaHorarioClase.addRow(rowData);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        if (!this.tablaHorariosAlumno.getSelectionModel().isSelectionEmpty()) {
            HorarioProfesor horario = (HorarioProfesor) this.modeloTablaHorarioClase.getValueAt(tablaHorariosAlumno.getSelectedRow(), 2);
            ClaseProfesor claseProfesor = (ClaseProfesor) this.modeloTablaHorarioClase.getValueAt(tablaHorariosAlumno.getSelectedRow(), 0);
            modeloTablaHorarioClase.removeRow(tablaHorariosAlumno.getSelectedRow());
            Object[] rowData = new Object[4];
            rowData[0] = claseProfesor;

            rowData[1] = new SimpleDateFormat("EEEE", new Locale("es", "ES")).format(horario.getInicio());
            rowData[2] = horario;

            rowData[3] = horario.getFinString();
            modeloTablaHorarios.addRow(rowData);
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void tablaAlumnosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseEntered
        try {
            CargadorTabla.alumnosActivos(tablaAlumnos, miControlador);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_tablaAlumnosMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarAlumno;
    private javax.swing.JButton btnBuscarClases;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnInscribir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox<String> cmbDiasPorSemana;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTable tablaHorariosAlumno;
    private javax.swing.JTable tablaHorariosClase;
    private javax.swing.JTable tablaPrincipal;
    private javax.swing.JTextField txtAlumno;
    private javax.swing.JTextField txtBuscarAlumno;
    private javax.swing.JTextField txtBuscarClase;
    private javax.swing.JTextField txtClase;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
