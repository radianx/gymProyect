/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.sun.glass.ui.Window;
import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.AsistenciaAlumno;
import gimnasio.modelo.AsistenciaProfesor;
import gimnasio.modelo.ClaseAlumno;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.Profesor;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
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
public class jInternalAsistencias extends javax.swing.JInternalFrame {

    ControladorPrincipal miControlador;
    DefaultTableModel modeloAlumno, modeloProfesor, modeloAsistencias;
    DefaultComboBoxModel modeloCombo;
    TableRowSorter<TableModel> rowSorterAlumno, rowSorterProfesor;
    Object seleccion;
    /**
     * Creates new form jInternalAsistencias
     */
    public jInternalAsistencias(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        Locale locale = new Locale("es", "ES");
        DatePickerSettings settings = new DatePickerSettings(locale);
        settings.setFormatForDatesCommonEra("dd/MM/yyyy");
        settings.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");
        dateTimePicker1.datePicker.setSettings(settings);
        dateTimePicker1.datePicker.setDateToToday();

        dateTimePicker1.timePicker.setTimeToNow();
        SwingUtilities.invokeLater(()->{
            cargarTablaProfesores();
            cargarTablaAsistencias();
            cargarCombo(null);
            cargarTablaAlumnos();
        });

    }

    public void cargarTablaAsistencias() {
        modeloAsistencias = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloAsistencias.addColumn("Nombre");
        modeloAsistencias.addColumn("Apellido");
        modeloAsistencias.addColumn("Hora Ingreso");
        modeloAsistencias.addColumn("Hora Egreso");
        Object[] fila = new Object[4];
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            for (AsistenciaAlumno asistAlumno : miControlador.getAsistenciasAlumnoDeHoy()) {
                if (asistAlumno.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = asistAlumno.getClaseAlumno().getAlumno().getNombrealumno();
                    fila[1] = asistAlumno.getClaseAlumno().getAlumno().getApellidoalumno();
                    fila[2] = asistAlumno;
                    if (asistAlumno.getSalida() != null) {
                        fila[3] = sdf.format(asistAlumno.getSalida());
                    } else {
                        fila[3] = "Salida no registrada";
                    }
                    modeloAsistencias.insertRow(0, fila);
                }
            }
            for (AsistenciaProfesor asistProfe : miControlador.getAsistenciasProfesorDeHoy()) {
                if (asistProfe.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = asistProfe.getClaseProfesor().getProfesor().getNombreprofesor();
                    fila[1] = asistProfe.getClaseProfesor().getProfesor().getApellidoprofesor();
                    fila[2] = asistProfe;
                    if (asistProfe.getSalida() != null) {
                        fila[3] = sdf.format(asistProfe.getSalida());
                    } else {
                        fila[3] = "Salida no registrada";
                    }
                    modeloAsistencias.insertRow(0, fila);
                }
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.tablaAsistencias.setModel(modeloAsistencias);

    }

    public void cargarTablaProfesores() {
        try {
            modeloProfesor = new DefaultTableModel();
            modeloProfesor.addColumn("Nombre");
            modeloProfesor.addColumn("Apellido");
            Object[] fila = new Object[2];

            for (Profesor miProfesor : miControlador.getListaProfesores()) {
                if (miProfesor.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = miProfesor;
                    fila[1] = miProfesor.getApellidoprofesor();
                    modeloProfesor.addRow(fila);
                }
            }
            this.tablaProfesores.setModel(modeloProfesor);
            rowSorterProfesor = new TableRowSorter<>(this.tablaProfesores.getModel());
            tablaProfesores.setRowSorter(rowSorterProfesor);
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void cargarTablaAlumnos() {
        System.out.println("Cargando Tabla de Alumnos...");
        modeloAlumno = new DefaultTableModel();
        modeloAlumno.addColumn("Nombre");
        modeloAlumno.addColumn("Apellido");
        modeloAlumno.addColumn("Usuario");
        Object[] fila = new Object[3];
        try {
            for (Alumno miAlumno : miControlador.getListaAlumnos()) {
                if (miAlumno.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = miAlumno;
                    fila[1] = miAlumno.getApellidoalumno();
                    fila[2] = miAlumno.getUsuario().getNombreusuario();
                    modeloAlumno.addRow(fila);
                }
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.tablaAlumnos.setModel(modeloAlumno);
        rowSorterAlumno = new TableRowSorter<>(this.tablaAlumnos.getModel());
        tablaAlumnos.setRowSorter(rowSorterAlumno);
    }

    public void cargarCombo(Object aluOprofe){
        modeloCombo = new DefaultComboBoxModel();
        if(aluOprofe instanceof Alumno){
            Alumno unAlumno = (Alumno) aluOprofe;
            boolean tieneClase = false;
            if(unAlumno.getClaseAlumnos()!=null){
                for (ClaseAlumno clase : unAlumno.getClaseAlumnos()) {
                    modeloCombo.addElement(clase);
                    tieneClase=true;
                }
            }
            if(!tieneClase){
                modeloCombo.addElement("Alumno No inscrito a ninguna clase");
                btnGuardar.setEnabled(false);
            }
        }
        if(aluOprofe instanceof Profesor){
            Profesor unProfesor = (Profesor) aluOprofe;
            boolean tieneClase=false;
            if(unProfesor.getClaseProfesors()!=null){
                for(ClaseProfesor clase: unProfesor.getClaseProfesors()){
                    modeloCombo.addElement(clase);
                    tieneClase = true;
                }
            }
            if(!tieneClase){
                modeloCombo.addElement("Profesor sin clase asignada.");
                btnGuardar.setEnabled(false);
            }
        }
        this.cmbClases.setModel(modeloCombo);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAsistencias = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarAlumno = new javax.swing.JTextField();
        btnBuscar1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarProfesor = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProfesores = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSeleccion = new javax.swing.JTextField();
        cmbClases = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("ASISTENCIAS");
        setPreferredSize(new java.awt.Dimension(650, 520));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Asistencias del Dia"));

        tablaAsistencias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tablaAsistencias);

        jButton2.setText("<html><center>Marcar<br>Egreso</center></html>");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("<html><center>Eliminar<br>Asistencia</center></html>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGuardar.setText("GUARDAR ASISTENCIA");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);

        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ASISTENCIA MANUAL"));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Nombre:");
        jPanel7.add(jLabel2);

        txtBuscarAlumno.setMinimumSize(new java.awt.Dimension(109, 20));
        txtBuscarAlumno.setPreferredSize(new java.awt.Dimension(150, 20));
        txtBuscarAlumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAlumnoKeyReleased(evt);
            }
        });
        jPanel7.add(txtBuscarAlumno);

        btnBuscar1.setText("Buscar");
        jPanel7.add(btnBuscar1);

        jPanel4.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Alumnos"));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 100));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseEntered(evt);
            }
        });

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
        tablaAlumnos.setPreferredSize(new java.awt.Dimension(200, 64));
        tablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAlumnos);

        jPanel8.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Nombre:");
        jPanel9.add(jLabel1);

        txtBuscarProfesor.setMinimumSize(new java.awt.Dimension(109, 20));
        txtBuscarProfesor.setPreferredSize(new java.awt.Dimension(150, 20));
        txtBuscarProfesor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProfesorKeyReleased(evt);
            }
        });
        jPanel9.add(txtBuscarProfesor);

        btnBuscar.setText("Buscar");
        jPanel9.add(btnBuscar);

        jPanel5.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Profesores"));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(200, 100));

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
        });
        tablaProfesores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaProfesoresKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProfesores);

        jPanel10.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 153, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("<html><b>FECHA Y HORA DE ASISTENCIA</b></html>");

        jLabel3.setText("<html><b>SELECCION:</b></html>");

        txtSeleccion.setEditable(false);
        txtSeleccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        cmbClases.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("<html><b>Clase:</b></html>");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbClases, 0, 207, Short.MAX_VALUE)
                    .addComponent(txtSeleccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbClases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarProfesorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProfesorKeyReleased
        String text = this.txtBuscarProfesor.getText();
        if (text.trim().length() == 0) {
            rowSorterProfesor.setRowFilter(null);
        } else {
            rowSorterProfesor.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtBuscarProfesorKeyReleased

    private void txtBuscarAlumnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAlumnoKeyReleased
        String text = this.txtBuscarAlumno.getText();
        if (text.trim().length() == 0) {
            rowSorterAlumno.setRowFilter(null);
        } else {
            rowSorterAlumno.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtBuscarAlumnoKeyReleased

    private void tablaProfesoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProfesoresMouseClicked
        if (!tablaProfesores.getSelectionModel().isSelectionEmpty()) {
            Profesor unProfe = (Profesor) tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 0);
            seleccion = unProfe;
            cargarCombo(seleccion);
            btnGuardar.setEnabled(true);
            tablaAlumnos.getSelectionModel().clearSelection();
            this.txtSeleccion.setText(unProfe.getNombreprofesor() + " " + unProfe.getApellidoprofesor());
        }
    }//GEN-LAST:event_tablaProfesoresMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(seleccion != null){
            LocalDate dia = dateTimePicker1.getDatePicker().getDate();
            LocalTime hora = dateTimePicker1.getTimePicker().getTime();
            LocalDateTime fechaJava = LocalDateTime.of(dia, hora);
            Date fecha = Date.from(fechaJava.atZone(ZoneId.systemDefault()).toInstant());
            if (seleccion instanceof Alumno) {
                Alumno unAlumno = (Alumno) seleccion;
                try{
                    ClaseAlumno claseAlumno = (ClaseAlumno) cmbClases.getSelectedItem();
                    miControlador.altaAsistenciaAlumno(claseAlumno ,fecha);
                }catch(ClassCastException | Notificaciones ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    ex.printStackTrace();
                }
            } else if (seleccion instanceof Profesor) {
                Profesor unProfe = (Profesor) seleccion;
                ClaseProfesor claseProfe = (ClaseProfesor) cmbClases.getSelectedItem();
                System.out.println("INTERNAL ASISTENCIAS: ClaseProfesor id: "+claseProfe.getIdclaseprofesor());
                AsistenciaProfesor unaAsistenciaProfe = new AsistenciaProfesor(claseProfe, fecha, "ACTIVO");
                try{
                    miControlador.altaAsistenciaProfesor(true,claseProfe, fecha);
                }catch(Notificaciones ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    ex.printStackTrace();
                }
            }
            SwingUtilities.invokeLater(()->{
               this.cargarTablaAsistencias();
            });
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno o profesor para guardar asistencia.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void tablaProfesoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProfesoresKeyReleased
        String text = this.txtBuscarProfesor.getText();
        if (text.trim().length() == 0) {
            rowSorterProfesor.setRowFilter(null);
        } else {
            rowSorterProfesor.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_tablaProfesoresKeyReleased

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered

    }//GEN-LAST:event_formMouseEntered

    private void jScrollPane1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseEntered

    }//GEN-LAST:event_jScrollPane1MouseEntered

    private void tablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseClicked
        if(!tablaAlumnos.getSelectionModel().isSelectionEmpty()){
            Alumno unAlumno = (Alumno) tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0);
            seleccion = unAlumno;
            cargarCombo(seleccion);
            btnGuardar.setEnabled(true);
            tablaProfesores.getSelectionModel().clearSelection();
            this.txtSeleccion.setText(unAlumno.getNombrealumno() + " " + unAlumno.getApellidoalumno());
        }
    }//GEN-LAST:event_tablaAlumnosMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(!this.tablaAsistencias.getSelectionModel().isSelectionEmpty()){
           JDialogFecha dialog = new JDialogFecha((JFrame)SwingUtilities.getWindowAncestor(this), true);
           dialog.setLocationRelativeTo(null);
           Date fecha = dialog.dameFecha();
           Object o = tablaAsistencias.getValueAt(tablaAsistencias.getSelectedRow(),2);
           try{
               if (o instanceof AsistenciaAlumno) {
                   AsistenciaAlumno asist = (AsistenciaAlumno) o;
                   asist.setSalida(fecha);
                   miControlador.getMiPersistencia().persistirInstancia(asist);
               }
               if(o instanceof AsistenciaProfesor){
                   AsistenciaProfesor asist = (AsistenciaProfesor) o;
                   asist.setSalida(fecha);
                   miControlador.getMiPersistencia().persistirInstancia(asist);
               }
           }catch(Notificaciones ex){
               ex.printStackTrace();
           }finally{
               SwingUtilities.invokeLater(() -> {
                   this.cargarTablaAsistencias();
               });
           }
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(!this.tablaAsistencias.getSelectionModel().isSelectionEmpty()){
           Object o = tablaAsistencias.getValueAt(tablaAsistencias.getSelectedRow(),2);
           try{
               if (o instanceof AsistenciaAlumno) {
                   AsistenciaAlumno asist = (AsistenciaAlumno) o;
                   asist.setEstado("BAJA");
                   miControlador.getMiPersistencia().persistirInstancia(asist);
               }
               if(o instanceof AsistenciaProfesor){
                   AsistenciaProfesor asist = (AsistenciaProfesor) o;
                   asist.setEstado("BAJA");
                   miControlador.getMiPersistencia().persistirInstancia(asist);
               }
           }catch(Notificaciones ex){
               ex.printStackTrace();
           }finally{
               SwingUtilities.invokeLater(() -> {
                   this.cargarTablaAsistencias();
               });
           }
       }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbClases;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTable tablaAsistencias;
    private javax.swing.JTable tablaProfesores;
    private javax.swing.JTextField txtBuscarAlumno;
    private javax.swing.JTextField txtBuscarProfesor;
    private javax.swing.JTextField txtSeleccion;
    // End of variables declaration//GEN-END:variables
}
