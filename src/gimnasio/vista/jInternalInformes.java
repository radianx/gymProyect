/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author adrian
 */
public class jInternalInformes extends javax.swing.JInternalFrame {

    
    
    DefaultPieDataset dataset = new DefaultPieDataset( );

    //Esto crea el grafico de tortas en si
    public JFreeChart createChart(PieDataset dataset){
        JFreeChart chart = ChartFactory.createPieChart(
         "Ventas",   // chart title
         dataset,          // data
         true,             // include legend
         true,
         false);
        
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        
        return chart;
    }
        
    public jInternalInformes() {
        dataset.setValue("Producto 1", new Double( 20 ) );
        dataset.setValue("Producto 2", new Double( 20 ) );
        dataset.setValue("Producto 3", new Double( 40 ) );
        dataset.setValue("Producto 4", new Double( 10 ) );
        
        JFreeChart tortilla = createChart(dataset);
        ChartPanel torta = new ChartPanel(tortilla);
        
        initComponents();
        torta.setVisible(true);
        jPanel1.add(torta, BorderLayout.CENTER);
        jPanel1.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jBtnDeudas = new javax.swing.JButton();
        jTxtDeudas = new javax.swing.JTextField();
        jTxtCheck = new javax.swing.JTextField();
        jTxtClientes = new javax.swing.JTextField();
        jTxtAusentes = new javax.swing.JTextField();
        jTxtTotal = new javax.swing.JTextField();
        jTxtPromedio = new javax.swing.JTextField();
        jTxtUltimaS = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setTitle("- INFORMES -");

        jLabel1.setText("Ingresos Totales este año:");

        jLabel2.setText("Ingreso de la Ultima Semana:");

        jLabel3.setText("Ingreso Promedio Mensual:");

        jLabel4.setText("Clientes Totales:");

        jLabel5.setText("Clientes ausentes la ultima semana:");

        jLabel6.setText("Check Ins del dia:");

        jLabel7.setText("Ventas del ultimo mes:");

        jPanel1.setMinimumSize(new java.awt.Dimension(500, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 273));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Deudas por Cuotas:");

        jBtnDeudas.setText("Detalle Deudas");

        jTxtDeudas.setEditable(false);
        jTxtDeudas.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jTxtDeudas.setForeground(new java.awt.Color(216, 23, 23));
        jTxtDeudas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxtDeudas.setText("1500");

        jTxtCheck.setEditable(false);
        jTxtCheck.setFont(new java.awt.Font("DejaVu Sans", 1, 15)); // NOI18N
        jTxtCheck.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTxtClientes.setEditable(false);
        jTxtClientes.setFont(new java.awt.Font("DejaVu Sans", 1, 15)); // NOI18N
        jTxtClientes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTxtAusentes.setEditable(false);
        jTxtAusentes.setFont(new java.awt.Font("DejaVu Sans", 1, 15)); // NOI18N
        jTxtAusentes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTxtTotal.setEditable(false);
        jTxtTotal.setFont(new java.awt.Font("DejaVu Sans", 1, 15)); // NOI18N
        jTxtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTxtPromedio.setEditable(false);
        jTxtPromedio.setFont(new java.awt.Font("Lato Semibold", 1, 15)); // NOI18N
        jTxtPromedio.setForeground(new java.awt.Color(64, 231, 48));
        jTxtPromedio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxtPromedio.setText("20000");
        jTxtPromedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtPromedioActionPerformed(evt);
            }
        });

        jTxtUltimaS.setEditable(false);
        jTxtUltimaS.setFont(new java.awt.Font("DejaVu Sans", 1, 15)); // NOI18N
        jTxtUltimaS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButton1.setText("Cerrar");
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
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxtPromedio, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jTxtUltimaS)
                            .addComponent(jTxtTotal)
                            .addComponent(jTxtDeudas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBtnDeudas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtAusentes)
                            .addComponent(jTxtCheck)
                            .addComponent(jTxtClientes))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jTxtClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtAusentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTxtPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtUltimaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTxtDeudas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnDeudas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(33, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtPromedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtPromedioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtPromedioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnDeudas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTxtAusentes;
    private javax.swing.JTextField jTxtCheck;
    private javax.swing.JTextField jTxtClientes;
    private javax.swing.JTextField jTxtDeudas;
    private javax.swing.JTextField jTxtPromedio;
    private javax.swing.JTextField jTxtTotal;
    private javax.swing.JTextField jTxtUltimaS;
    // End of variables declaration//GEN-END:variables
}