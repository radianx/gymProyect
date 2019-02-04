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
import gimnasio.modelo.HorarioProfesor;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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
public class jInternalClasesAlumnosOff extends javax.swing.JInternalFrame {

    ControladorPrincipal miControlador;
    panelClaseAlumno panelNewClaseAlumno;
    DefaultTableModel modeloTablaHorarios;
    DefaultTableModel modeloTablaAlumnos;
    DefaultTableModel modeloTablaHorarioClase;
    ClaseProfesor claseSeleccionada;
    Alumno alumnoSeleccionado;
    String text = "";

    public jInternalClasesAlumnosOff(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        cargarTablaAlumnos();
        cargarTablaHorariosAlumno();
        cargarComboClasesEliminadas();
        cargarComboClaseProfesor();
        panelNewClaseAlumno = new panelClaseAlumno(miControlador);
        this.add(panelNewClaseAlumno);
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

    public void cargarComboClasesEliminadas() {
        try {
            List<ClaseProfesor> lista = miControlador.getListaClaseProfesor();
            List<ClaseProfesor> combo = new ArrayList<>();
            for (ClaseProfesor clase : lista) {
                if (clase.getEstado().equalsIgnoreCase("INACTIVO")) {
                    combo.add(clase);
                }
            }
            this.cmbClases1.setModel(new DefaultComboBoxModel(combo.toArray()));

        } catch (Notificaciones ex) {
            ex.printStackTrace();
        }
    }

    public void cargarTablaAlumnos(ClaseProfesor claseProfe) {
        modeloTablaAlumnos = new DefaultTableModel();
        modeloTablaAlumnos.addColumn("Clase");
        modeloTablaAlumnos.addColumn("Nombre");
        modeloTablaAlumnos.addColumn("Apellido");
        this.tablaAlumnos.setModel(modeloTablaAlumnos);
        Object[] fila = new Object[3];
        for (ClaseAlumno claseAlumno : claseProfe.getClaseAlumnos()) {
            fila[0] = claseAlumno;
            fila[1] = claseAlumno.getAlumno();
            fila[2] = claseAlumno.getAlumno().getApellidoalumno();
            modeloTablaAlumnos.addRow(fila);
        }
    }

    public void cargarTablaAlumnos() {
        modeloTablaAlumnos = new DefaultTableModel();
        modeloTablaAlumnos.addColumn("Nombre");
        modeloTablaAlumnos.addColumn("Apellido");
        this.tablaAlumnos.setModel(modeloTablaAlumnos);
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
            if (horarioProfesor.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = horarioProfesor;
                fila[1] = horarioProfesor.getInicioString();

                fila[2] = horarioProfesor.getFinString();

                fila[3] = horarioProfesor.getClaseProfesor().getProfesor().getNombreprofesor() + " "
                        + horarioProfesor.getClaseProfesor().getProfesor().getApellidoprofesor();
                fila[4] = horarioProfesor.getPromocion();
                this.modeloTablaHorarioClase.addRow(fila);
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
        jPanel4 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
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
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnCerrar1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaSuperiorHorarios = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnPromociones = new javax.swing.JButton();
        cmbDiasPorSemana = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cmbClases1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cmbClases = new javax.swing.JComboBox<>();

        setClosable(true);
        setResizable(true);
        setTitle("ALUMNOS SIN CLASES ASIGNADAS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gimnasio/imagenes/countryIcon.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(600, 550));
        setPreferredSize(new java.awt.Dimension(600, 550));
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

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCerrar);

        panelPrincipal.add(jPanel4, java.awt.BorderLayout.PAGE_END);

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
        jPanel3.setLayout(new java.awt.GridLayout());

        btnInscribir.setText("INSCRIBIR ALUMNO");
        btnInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscribirActionPerformed(evt);
            }
        });
        jPanel3.add(btnInscribir);

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
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
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

        panelPrincipal.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCerrar1.setText("CERRAR");
        btnCerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar1);

        panelPrincipal.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new java.awt.GridLayout());

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

        cmbClases1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClases1ActionPerformed(evt);
            }
        });

        jLabel18.setText("Clase Eliminada:");

        jLabel19.setText("Nueva Clase");

        cmbClases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClasesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbClases1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDiasPorSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbClases, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(8, 8, 8)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbClases1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cmbClases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDiasPorSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPrincipal.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void panelPrincipalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMouseEntered

    }//GEN-LAST:event_panelPrincipalMouseEntered

    private void panelPrincipalComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelPrincipalComponentShown

    }//GEN-LAST:event_panelPrincipalComponentShown

    private void tablaHorariosAluMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHorariosAluMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaHorariosAluMouseClicked

    private void tablaHorariosAluMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHorariosAluMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaHorariosAluMouseEntered

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInscribirActionPerformed

    }//GEN-LAST:event_btnInscribirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.cmbClases1.setEnabled(true);
        this.tablaAlumnos.getSelectionModel().clearSelection();
        this.txtAlumno.setText("");
        this.txtClase.setText("");
        this.txtPrecio.setText("");
        this.jCheckBox1.setSelected(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrar1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCerrar1ActionPerformed

    private void tablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseClicked
        if (!tablaAlumnos.getSelectionModel().isSelectionEmpty()) {
            alumnoSeleccionado = (Alumno) tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0);
            this.txtAlumno.setText(alumnoSeleccionado.getNombrealumno() + " " + alumnoSeleccionado.getApellidoalumno());
        }
    }//GEN-LAST:event_tablaAlumnosMouseClicked

    private void tablaAlumnosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseEntered

    }//GEN-LAST:event_tablaAlumnosMouseEntered

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
            this.cmbClases1.setEnabled(false);
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

    private void btnPromocionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromocionesActionPerformed
        MainMenu menu = (MainMenu) SwingUtilities.getWindowAncestor(this);
        menu.abrirPromociones();
    }//GEN-LAST:event_btnPromocionesActionPerformed

    private void cmbDiasPorSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDiasPorSemanaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDiasPorSemanaActionPerformed

    private void cmbClases1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClases1ActionPerformed
        this.cargarTablaAlumnos((ClaseProfesor) cmbClases1.getSelectedItem());
    }//GEN-LAST:event_cmbClases1ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCerrar1;
    private javax.swing.JButton btnInscribir;
    private javax.swing.JButton btnPromociones;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox<String> cmbClases;
    private javax.swing.JComboBox<String> cmbClases1;
    private javax.swing.JComboBox<String> cmbDiasPorSemana;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTable tablaHorariosAlu;
    private javax.swing.JTable tablaSuperiorHorarios;
    private javax.swing.JTextField txtAlumno;
    private javax.swing.JTextField txtClase;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
    private void cambiarPanel(JPanel panelActual, JPanel panelCambio) {
        panelActual.setVisible(false);
        panelCambio.setVisible(true);
        // this.pack();
    }

    public void cambiarPanel() {
        cambiarPanel(this.panelPrincipal, this.panelNewClaseAlumno);
    }
}
