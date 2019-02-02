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
import gimnasio.modelo.Clase;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.HorarioProfesor;
import gimnasio.modelo.Modalidad;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
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
public class panelClaseProfesorHorario extends javax.swing.JPanel {

    DefaultComboBoxModel modeloComboClases;
    DefaultComboBoxModel modeloComboModalidades;
    DefaultTableModel modeloTablaDias;
    ControladorPrincipal miControlador;
    int horariosRecibidos = 0;
    boolean seRecibieronDatos = false;
    
    ClaseProfesor claseProfesor;
    Profesor profesorSeleccionado;
    Modalidad modalidadSeleccionada;
    Clase claseSeleccionada;
    
    public panelClaseProfesorHorario(ControladorPrincipal controlador) {
        miControlador = controlador;
        initComponents();
        cargarTablaDiasClases();

    }
    
    public void cargarTablaDiasClases(){
        this.modeloTablaDias = new DefaultTableModel();
        modeloTablaDias.addColumn("Clase");
        modeloTablaDias.addColumn("Dia");
        modeloTablaDias.addColumn("Inicio");
        modeloTablaDias.addColumn("Fin");
        modeloTablaDias.addColumn("Promocion");

        this.tablaDiasClase.setModel(modeloTablaDias);

    }
    
    public void cargarTablaDiasClases(List<HorarioProfesor>horarios){
        this.modeloTablaDias = new DefaultTableModel();
        modeloTablaDias.addColumn("Clase");
        modeloTablaDias.addColumn("Dia");
        modeloTablaDias.addColumn("Inicio");
        modeloTablaDias.addColumn("Fin");
        modeloTablaDias.addColumn("Promocion");
        Object[] fila = new Object[5];
        for(HorarioProfesor unHorario:horarios){
            fila[0] = claseSeleccionada;
            fila[1] = unHorario;
            fila[2] = unHorario.getInicioString();
            fila[3] = unHorario.getFinString();
            fila[4] = unHorario.getPromocion();
            this.modeloTablaDias.addRow(fila);
        }
        tablaDiasClase.setModel(modeloTablaDias);
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
        btnCerrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtProfesor = new javax.swing.JTextField();
        txtModalidad = new javax.swing.JTextField();
        txtClase = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaDiasClase = new javax.swing.JTable();
        btnAgregar1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbDia = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.setAllowEmptyTimes(false);
        timeSettings.setAllowKeyboardEditing(false);
        timeSettings.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.FifteenMinutes, LocalTime.of(7, 0), LocalTime.of(23,0));
        timePicker2 = new com.github.lgooddatepicker.components.TimePicker(timeSettings);
        TimePickerSettings configuracionTiempo = new TimePickerSettings();
        configuracionTiempo.setAllowEmptyTimes(false);
        configuracionTiempo.setAllowKeyboardEditing(false);
        configuracionTiempo.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.FifteenMinutes, LocalTime.of(7, 0), LocalTime.of(23,0));
        timePicker1 = new com.github.lgooddatepicker.components.TimePicker(configuracionTiempo);

        setMinimumSize(new java.awt.Dimension(600, 360));
        setPreferredSize(new java.awt.Dimension(600, 360));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGuardar.setText("GUARDAR");
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

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Profesor:");

        txtProfesor.setEditable(false);

        txtModalidad.setEditable(false);
        txtModalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModalidadActionPerformed(evt);
            }
        });

        txtClase.setEditable(false);

        jLabel12.setText("Clase:");

        jLabel17.setText("Modalidad:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel16)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(109, 109, 109))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtModalidad, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtClase, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtProfesor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Dias de Clase"));

        tablaDiasClase.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaDiasClase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDiasClaseMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaDiasClase);

        btnAgregar1.setText("Quitar");
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jCheckBox1.setText("Es Promocion");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addGap(34, 34, 34))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Hasta:");

        jLabel15.setText("Dia:");

        jLabel4.setText("Horario:");

        cmbDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" }));

        jLabel13.setText("Desde:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(timePicker2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(timePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
            Profesor unProfesor = profesorSeleccionado;
            Modalidad unaModalidad = modalidadSeleccionada;
            Clase unaClase = claseSeleccionada;
            
        boolean seRompio = false;
        try {
            if (claseProfesor == null) {
                claseProfesor = new ClaseProfesor(unaClase, unaModalidad, unProfesor, "ACTIVO");
            }
            

            
            for (int i = modeloTablaDias.getRowCount() - 1; i >= 0; i--) {
                HorarioProfesor unHorarioProfesor = (HorarioProfesor) modeloTablaDias.getValueAt(i, 1);
                unHorarioProfesor.setEstado("ACTIVO");
                unHorarioProfesor.setClaseProfesor(claseProfesor);
                System.out.println("Id de horarioProfesor: "+ unHorarioProfesor.getIdHorario());
                if(unHorarioProfesor.getIdHorario()==0){
                    claseProfesor.addHorarioProfesor(unHorarioProfesor);
                    miControlador.altaClaseProfesor(claseProfesor);
                }
                System.out.println("Id de horarioProfesor: "+ unHorarioProfesor.getIdHorario());
                miControlador.altaHorarioProfesor(unHorarioProfesor);
            }
            //EN CASO DE QUE SEA UNA MODIFICACION DE HORARIO NO MAS
            if(horariosRecibidos==modeloTablaDias.getRowCount() && seRecibieronDatos){
                miControlador.actualizarHorariosDeAlumnos(claseProfesor);
            }
            //EN CASO DE QUE SE AGREGA UN HORARIO NUEVO
            if(horariosRecibidos!=modeloTablaDias.getRowCount() && seRecibieronDatos){
                String[] opciones = {"SI", "NO"};
                int seleccion = JOptionPane.showOptionDialog(null, "Se detecto un nuevo horario\n¿Desea actualizar la cuota de los alumnos inscriptos a esta clase?", "Seleccione una opcion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
                if(seleccion==0){
                    
                }
            }
            //
            
            profesorSeleccionado.getClaseProfesors().add(claseProfesor);
            miControlador.altaProfesor(profesorSeleccionado);
            JInternalClasesProfesor internal = (JInternalClasesProfesor)this.getParent().getParent().getParent().getParent();
            internal.cargarTablaClaseProfesores();
            internal.cargarTablaHorarios();
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, "Base de Datos: " + ex.getLocalizedMessage());
            ex.printStackTrace();
            seRompio = true;
        }
            if(!seRompio){
                JOptionPane.showMessageDialog(null, "Guardado con exito");
                this.setVisible(false);
                seRecibieronDatos = false;
            }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtModalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModalidadActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Clase unaClase = claseSeleccionada;

        String diaSeleccionado = (String) cmbDia.getSelectedItem();
        LocalDate dia = LocalDate.now();
        if(diaSeleccionado.equalsIgnoreCase("Lunes")) dia = LocalDate.now().with(DayOfWeek.MONDAY);
        if(diaSeleccionado.equalsIgnoreCase("Martes"))dia = LocalDate.now().with(DayOfWeek.TUESDAY);
        if(diaSeleccionado.equalsIgnoreCase("Miercoles"))dia = LocalDate.now().with(DayOfWeek.WEDNESDAY);
        if(diaSeleccionado.equalsIgnoreCase("Jueves"))dia = LocalDate.now().with(DayOfWeek.THURSDAY);
        if(diaSeleccionado.equalsIgnoreCase("Viernes"))dia = LocalDate.now().with(DayOfWeek.FRIDAY);
        if(diaSeleccionado.equalsIgnoreCase("Sabado"))dia = LocalDate.now().with(DayOfWeek.SATURDAY);
        if(diaSeleccionado.equalsIgnoreCase("Domingo"))dia = LocalDate.now().with(DayOfWeek.SUNDAY);
        LocalTime horaInicio = timePicker1.getTime();
        LocalTime horaFin = timePicker2.getTime();
        Instant instanteInicio = horaInicio.atDate(dia).atZone(ZoneId.systemDefault()).toInstant();
        Instant instanteFin = horaFin.atDate(dia).atZone(ZoneId.systemDefault()).toInstant();

        Date inicio = Date.from(instanteInicio);
        Date fin = Date.from(instanteFin);
        
        HorarioProfesor horarioProfesor = new HorarioProfesor(inicio, fin);

        if(jCheckBox1.isSelected()){
            horarioProfesor.setPromocion("SI");
        }else{
            horarioProfesor.setPromocion("NO");
        }
        
        if (!isHorarioProfesorHere(horarioProfesor) && inicio.before(fin)) {
            Object[] fila = new Object[5];
            fila[0] = unaClase;
            fila[1] = horarioProfesor;
            fila[2] = horarioProfesor.getInicioString();
            fila[3] = horarioProfesor.getFinString();
            fila[4] = horarioProfesor.getPromocion();
            this.modeloTablaDias.addRow(fila);
        } else{
            JOptionPane.showMessageDialog(null, "Se esta intentando ingresar un horario invalido");
        }

        if(this.jCheckBox1.isSelected()){
            jCheckBox1.setSelected(false);
        }
        //AlCubierreDrive.engageHyperSpaceTravel(Destination.ANDROMEDA);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tablaDiasClaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDiasClaseMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaDiasClaseMouseClicked

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        try{
            if(!tablaDiasClase.getSelectionModel().isSelectionEmpty()){
            HorarioProfesor horario = (HorarioProfesor)tablaDiasClase.getValueAt(this.tablaDiasClase.getSelectedRow(),1);
            miControlador.bajaHorarioProfesor(horario);
            this.modeloTablaDias.removeRow(this.tablaDiasClase.getSelectedRow());
        }else{
            JOptionPane.showMessageDialog(null, "Debe selecionar un Horario para Quitarlo");
        }
        }catch(Notificaciones ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAgregar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregar1;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbDia;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tablaDiasClase;
    private com.github.lgooddatepicker.components.TimePicker timePicker1;
    private com.github.lgooddatepicker.components.TimePicker timePicker2;
    private javax.swing.JTextField txtClase;
    private javax.swing.JTextField txtModalidad;
    private javax.swing.JTextField txtProfesor;
    // End of variables declaration//GEN-END:variables

    private boolean isHorarioProfesorHere(HorarioProfesor unHorarioProfesor) {
        boolean respuesta = false;
        for(int i=modeloTablaDias.getRowCount()-1;i>=0;i--){
                if(unHorarioProfesor.equals((HorarioProfesor)modeloTablaDias.getValueAt(i,1)))
                    respuesta = true;
        }
        return respuesta;
    }
  
    public void recibirDatos(ClaseProfesor claseProfe, Profesor unProfe, Modalidad unaMod, Clase unaClase, List<HorarioProfesor> horarios){
        claseProfesor = claseProfe;
        profesorSeleccionado = unProfe;
        modalidadSeleccionada = unaMod;
        claseSeleccionada = unaClase;
        if(claseProfe!=null) seRecibieronDatos = true;
        this.jCheckBox1.setSelected(false);
        this.txtProfesor.setText(unProfe.getNombreprofesor() + " " + unProfe.getApellidoprofesor());
        this.txtModalidad.setText(unaMod.getNombremodalidad());
        this.txtClase.setText(unaClase.toString());
        this.cargarTablaDiasClases(horarios);
        horariosRecibidos = horarios.size();
        this.setVisible(true);
    }
}
