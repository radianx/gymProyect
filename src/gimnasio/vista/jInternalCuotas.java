/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.Cuota;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Family
 */
public class jInternalCuotas extends javax.swing.JInternalFrame {

    private Alumno alumno;
    private ClaseProfesor claseProfesor;
    private Double monto;
    private LocalDate altacuota; 
    private LocalDate vencimiento;
    private DefaultTableModel modeloTabla;
    private ControladorPrincipal miControlador;
    private Cuota cuota;
    /**
     * Creates new form jInternalCuotas
     */
    public jInternalCuotas(ControladorPrincipal controlador, Alumno unAlumno, ClaseProfesor claseProfesor, Double unMonto) {
        this.miControlador = controlador;
        this.alumno = unAlumno;
        this.claseProfesor = claseProfesor;
        this.monto = unMonto;
        this.altacuota = LocalDate.now();
        this.vencimiento = LocalDate.now().plusMonths(1).with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        initComponents();
        cargarTabla();
        this.txtAlumno.setText(alumno.getNombrealumno() + " " + alumno.getApellidoalumno());
        this.txtClase.setText(claseProfesor.getClase().toString());
    }

    public void cargarTabla(){
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("N° Cuota");
        modeloTabla.addColumn("Clase");
        modeloTabla.addColumn("Alta Cuota");
        modeloTabla.addColumn("Vencimiento");
        modeloTabla.addColumn("Monto");
        this.tabla.setModel(modeloTabla);
        Date alta = Date.from(altacuota.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date vence = Date.from(vencimiento.atStartOfDay(ZoneId.systemDefault()).toInstant());
        cuota = new Cuota(alumno, claseProfesor, monto, alta, vence, "GENERADO");
        alumno.getCuotas().add(cuota);

        Object[]fila = new Object[5];
        if(alumno.getCuotas().size()>=0) fila[0] = alumno.getCuotas().size();
        else fila[0] = 1;
        fila[1] = cuota.getClaseProfesor().getClase();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(cuota.getAltacuota());
        fila[2] = date;
        date = new SimpleDateFormat("dd/MM/yyyy").format(cuota.getVencimiento());
        fila[3] = date;
        fila[4] = cuota.getMonto();
        modeloTabla.addRow(fila);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtAlumno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtClase = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("GENERACION DE CUOTA");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gimnasio/imagenes/countryIcon.png"))); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabla);

        jLabel2.setText("Alumno:");

        txtAlumno.setEditable(false);

        jLabel3.setText("Clase:");

        txtClase.setEditable(false);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClase)
                            .addComponent(txtAlumno)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
    }//GEN-LAST:event_formComponentShown

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            cuota.setAlumno(alumno);
            alumno.getCuotas().add(cuota);
            miControlador.altaCuota(cuota);
            miControlador.getMiPersistencia().persistirInstancia(alumno);
        }catch(Notificaciones ex){
            JOptionPane.showMessageDialog(null,ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtAlumno;
    private javax.swing.JTextField txtClase;
    // End of variables declaration//GEN-END:variables
}
