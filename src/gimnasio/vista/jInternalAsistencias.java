/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import com.github.lgooddatepicker.components.DatePickerSettings;
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
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
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
        cargarTablaProfesores();
        cargarTablaAlumnos();
        cargarTablaAsistencias();
        cargarCombo(null);
        Locale locale = new Locale("es", "ES");
        DatePickerSettings settings = new DatePickerSettings(locale);
        settings.setFormatForDatesCommonEra("dd/MM/yyyy");
        settings.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");
        dateTimePicker1.datePicker.setSettings(settings);
        dateTimePicker1.datePicker.setDateToToday();

        dateTimePicker1.timePicker.setTimeToNow();
        rowSorterProfesor = new TableRowSorter<>(this.tablaProfesores.getModel());
        tablaProfesores.setRowSorter(rowSorterProfesor);
        rowSorterAlumno = new TableRowSorter<>(this.tablaProfesores.getModel());
        tablaAlumnos.setRowSorter(rowSorterAlumno);

    }

    public void cargarTablaAsistencias() {
        modeloAsistencias = new DefaultTableModel();
        modeloAsistencias.addColumn("Nombre");
        modeloAsistencias.addColumn("Apellido");
        modeloAsistencias.addColumn("Hora Ingreso");
        Object[] fila = new Object[3];
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            for (AsistenciaAlumno asistAlumno : miControlador.getAsistenciasAlumnoDeHoy()) {
                fila[0] = asistAlumno.getClaseAlumno().getAlumno().getNombrealumno();
                fila[1] = asistAlumno.getClaseAlumno().getAlumno().getApellidoalumno();
                fila[2] = sdf.format(asistAlumno.getIngreso());
                modeloAsistencias.addRow(fila);
            }
            for (AsistenciaProfesor asistProfe : miControlador.getAsistenciasProfesorDeHoy()) {
                fila[0] = asistProfe.getClaseProfesor().getProfesor().getNombreprofesor();
                fila[1] = asistProfe.getClaseProfesor().getProfesor().getApellidoprofesor();
                fila[2] = sdf.format(asistProfe.getIngreso());
                modeloAsistencias.addRow(fila);
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
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void cargarTablaAlumnos() {
        modeloAlumno = new DefaultTableModel();
        modeloAlumno.addColumn("Nombre");
        modeloAlumno.addColumn("Apellido");
        Object[] fila = new Object[2];
        try {
            for (Alumno miAlumno : miControlador.getListaAlumnos()) {
                fila[0] = miAlumno;
                fila[1] = miAlumno.getApellidoalumno();
                modeloAlumno.addRow(fila);
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.tablaAlumnos.setModel(modeloAlumno);
    }

    public void cargarCombo(Object aluOprofe){
        modeloCombo = new DefaultComboBoxModel();
        if(aluOprofe instanceof Alumno){
            Alumno unAlumno = (Alumno) aluOprofe;
            if(unAlumno.getClaseAlumnos()!=null){
                for (ClaseAlumno clase : unAlumno.getClaseAlumnos()) {
                    modeloCombo.addElement(clase);
                }
            }else{
                modeloCombo.addElement("Alumno No inscrito a ninguna clase");
                btnGuardar.setEnabled(false);
            }
        }
        if(aluOprofe instanceof Profesor){
            Profesor unProfesor = (Profesor) aluOprofe;
            if(unProfesor.getClaseProfesors()!=null){
                for(ClaseProfesor clase: unProfesor.getClaseProfesors()){
                    modeloCombo.addElement(clase);
                }
            }else{
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

        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAsistencias = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnBuscar1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarAlumno = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProfesores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarProfesor = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSeleccion = new javax.swing.JTextField();
        cmbClases = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setClosable(true);
        setTitle("ASISTENCIAS");
        setDesktopIcon(null);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gimnasio/imagenes/countryIcon.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(630, 500));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        btnBuscar1.setText("Buscar");

        jLabel2.setText("Nombre:");

        txtBuscarAlumno.setMinimumSize(new java.awt.Dimension(109, 20));
        txtBuscarAlumno.setPreferredSize(new java.awt.Dimension(150, 20));
        txtBuscarAlumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAlumnoKeyReleased(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Alumnos"));

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
        });
        jScrollPane1.setViewportView(tablaAlumnos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar1))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnBuscar.setText("Buscar");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Profesores"));

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
        jScrollPane2.setViewportView(tablaProfesores);

        jLabel1.setText("Nombre:");

        txtBuscarProfesor.setMinimumSize(new java.awt.Dimension(109, 20));
        txtBuscarProfesor.setPreferredSize(new java.awt.Dimension(150, 20));
        txtBuscarProfesor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProfesorKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBuscarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnBuscar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("FECHA Y HORA DE ASISTENCIA");

        jLabel3.setText("SELECCION:");

        txtSeleccion.setEditable(false);
        txtSeleccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        cmbClases.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Clase:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbClases, 0, 207, Short.MAX_VALUE)
                    .addComponent(txtSeleccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(50, 50, 50))
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbClases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
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
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        String text = this.txtBuscarProfesor.getText();
        if (text.trim().length() == 0) {
            rowSorterAlumno.setRowFilter(null);
        } else {
            rowSorterAlumno.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtBuscarAlumnoKeyReleased

    private void tablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseClicked
        if (!tablaAlumnos.getSelectionModel().isSelectionEmpty()) {
            Alumno unAlumno = (Alumno) tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0);
            seleccion = unAlumno;
            cargarCombo(seleccion);
            btnGuardar.setEnabled(true);
            tablaProfesores.getSelectionModel().clearSelection();
            this.txtSeleccion.setText(unAlumno.getNombrealumno() + " " + unAlumno.getApellidoalumno());
        }
    }//GEN-LAST:event_tablaAlumnosMouseClicked

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
                ClaseAlumno claseAlumno = (ClaseAlumno) cmbClases.getSelectedItem();
                AsistenciaAlumno unaAsistenciaAlumno = new AsistenciaAlumno(claseAlumno, fecha, "ACTIVO");
                try{
                    miControlador.altaAsistenciaAlumno(unaAsistenciaAlumno);
                }catch(Notificaciones ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else if (seleccion instanceof Profesor) {
                Profesor unProfe = (Profesor) seleccion;
                ClaseProfesor claseProfe = (ClaseProfesor) cmbClases.getSelectedItem();
                AsistenciaProfesor unaAsistenciaProfe = new AsistenciaProfesor(claseProfe, fecha, "ACTIVO");
                try{
                    miControlador.altaAsistenciaProfesor(unaAsistenciaProfe);
                }catch(Notificaciones ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno o profesor para guardar asistencia.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbClases;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
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
