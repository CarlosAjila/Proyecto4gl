/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JIFInventarioBodega.java
 *
 * Created on 10/12/2010, 04:13:01 PM
 */
package vista;


/**
 *
 * @author jack
 */
public class JIFMaterias extends javax.swing.JInternalFrame {

   
    public JIFMaterias(String titulo) throws Exception {
        super(titulo,
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        initComponents();
        this.setVisible(true);
       
    }

     private void llenarjTable() throws Exception{
        this.rRHHBBFunciones.setRhComboXiiiSueldoPeriodoTO(shrimp.rrhh.delegate.RrhhDelegate.getInstance().getRhComboXiiiSueldoPeriodoTO());
        this.bindingGroup.bind();//consigue los datos
        this.jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_ONCE, this.rRHHBBFunciones.getRhComboXiiiSueldoPeriodoTO()
               , jtblMateria);
        
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = this.jTableBinding.
                addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${xiiiDescripcion}"));
        columnBinding.setColumnName("Descripción");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);

        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding1 = this.jTableBinding.
                addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${xiiiDesde}"));//${nombreAtributo}
        columnBinding1.setColumnName("Fecha desde");
        columnBinding1.setColumnClass(String.class);
        columnBinding1.setEditable(false);
        
         org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding2 = this.jTableBinding.
                addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${xiiiHasta}"));//${nombreAtributo}
        columnBinding2.setColumnName("Fecha hasta");
        columnBinding2.setColumnClass(String.class);
        columnBinding2.setEditable(false);
        
         org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding3 = this.jTableBinding.
                addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${xiiiFechaMaximaPago}"));//${nombreAtributo}
        columnBinding3.setColumnName("Fecha Maximo Pago");
        columnBinding3.setColumnClass(String.class);
        columnBinding3.setEditable(false);

        this.bindingGroup.addBinding(this.jTableBinding);
        this.bindingGroup.bind();
        
        ///// PARA CAMBIAR EL ANCHO DE LAS COLUMNAS
        javax.swing.table.TableColumn col = null;
        col = jtblMateria.getColumnModel().getColumn(0);
        col.setPreferredWidth(80);
        col = jtblMateria.getColumnModel().getColumn(1);
        col.setPreferredWidth(200);
        /*col = jtblVendedor.getColumnModel().getColumn(2);
        col.setPreferredWidth(100);*/

        jtblMateria.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        // Metemos el modelo ordenable en la tabla.
        javax.swing.table.TableRowSorter modeloOrdenado = new javax.swing.table.TableRowSorter(jtblMateria.getModel());
        jtblMateria.setRowSorter(modeloOrdenado);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        JtfMateria = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtfEstado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblMateria = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Materia"));

        jLabel1.setText("Materia : ");

        jLabel2.setText("Estado:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtfMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JtfMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Guardar");

        jtblMateria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtblMateria);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JtfMateria;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblMateria;
    private javax.swing.JTextField jtfEstado;
    // End of variables declaration//GEN-END:variables
}
