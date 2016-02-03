/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JIFRrhhFormasPago.java
 *
 * Created on 26/10/2010, 05:40:48 PM
 */

package shrimp.rrhh.formularios;

/**
 *
 * @author misael
 */
public class JIFRrhhXiiiSueldoPeriodo extends javax.swing.JInternalFrame {
    private String accion = "";
    private shrimp.componentes.GUI.MDIShrimp mDIShrimp = null;
    private sistema.TO.SisUsuarioEmpresaTO sisUsuarioEmpresaTO = null;
    private sistema.TO.SisGrupoTO sisGrupoTO;
    private String empCodigo;
    //private String vendCodigo;
    private String periodoDescripcion;
    private String fechaPeriodoInico;
    private String fechaPeriodoFin;
    private String fechaperiodoPagoMaximo;
     private Integer periodoPagoSecuencial;
            
    private String usrCodigo;
    rrhh.TO.RhXiiiSueldoPeriodoTO rhXiiiSueldoPeriodoTO = new rrhh.TO.RhXiiiSueldoPeriodoTO();
   
    /** Creates new form Periodo XIII Sueldo */
    public JIFRrhhXiiiSueldoPeriodo(shrimp.componentes.GUI.MDIShrimp mDIShrimp, String titulo) {
        super(titulo,
        true, //resizable
        true, //closable
        true, //maximizable
        true);//iconifiable
        mDIShrimp.jTabbedPane.setVisible(false);
        this.mDIShrimp = mDIShrimp;
        this.sisUsuarioEmpresaTO = mDIShrimp.getSisUsuarioEmpresaTO();
        this.empCodigo = sisUsuarioEmpresaTO.getEmpCodigo();
        this.usrCodigo = sisUsuarioEmpresaTO.getUsrCodigo();
        initComponents();
        this.setVisible(true);
        mDIShrimp.desktopPane.add(this);
        accion = "INICIO";
        jftfFechaDesde.requestFocus();
        tabulacion();
        this.eventosKeyFormulario();
        teclasBotonesCalientes();
        manejoControles(false);
        limpiar();
        rRHHBBFunciones = new shrimp.rrhh.beanbinding.RRHHBBFunciones();
        try {
            sisGrupoTO = shrimp.sistema.delegate.SistemaDelegate.getInstance().
                    sisGrupoUsuariosTO(mDIShrimp.getSisInfoTO());
            llenarjTable();
            manejoBotones();
            eventos();
        } catch (java.rmi.RemoteException re) {
           javax.swing.JOptionPane.showMessageDialog(null, "Se perdio la conexion. Contacte con el administrador...");
        } catch (javax.ejb.EJBException eje){
           javax.swing.JOptionPane.showMessageDialog(null, "Se perdio la conexion. Contacte con el administrador...");
        } catch(javax.naming.NameNotFoundException nnfe){
            javax.swing.JOptionPane.showMessageDialog(null, "El programa servidor no esta listo. Intente mas tarde...");
        } catch (Exception e) {
            shrimp.helper.Excepciones.guardarExcepcionesAC(
                    e, 
                    getClass().getName(),
                    mDIShrimp.getSisInfoTO());
        }
        jftfFechaDesde.setValue(validaciones.Validacion.fecha(validaciones.Validacion.getUltimoDiaDelMes(validaciones.Validacion.fechaSistema("yyyy-MM-dd"), "yyyy-MM-dd"), "dd-MM-yyyy"));
    }
    
    private void eventosKeyFormulario(){
        javax.swing.InputMap map = this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        this.getActionMap().put("refrescar", new javax.swing.AbstractAction(){
            public void actionPerformed(java.awt.event.ActionEvent e) {
                actualizar();
            }
        });
        map.put(javax.swing.KeyStroke.getKeyStroke("F5"),"refrescar");

        this.getActionMap().put("cancelar", new javax.swing.AbstractAction(){
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    if ((accion.equals("NUEVO")) ||(accion.equals("MODIFICAR"))){
                        cancelar();
                    }else
                        salirFormulario();
                } catch (javax.ejb.EJBException eje){
                   javax.swing.JOptionPane.showMessageDialog(null, "Se perdio la conexion. Contacte con el administrador...");
                } catch (Exception ex) {
                    shrimp.helper.Excepciones.guardarExcepcionesAC(
                            ex, 
                            getClass().getName(),
                            mDIShrimp.getSisInfoTO());
                }
            }
        });
        map.put(javax.swing.KeyStroke.getKeyStroke("ESCAPE"),"cancelar");
    }

    private void salirFormulario(){
        this.dispose();
        this.mDIShrimp.requestFocus();
    }

    private void actualizar(){
        try {
            llenarjTable();
        } catch (java.rmi.RemoteException re) {
           javax.swing.JOptionPane.showMessageDialog(null, "Se perdio la conexion. Contacte con el administrador...");
        } catch (javax.ejb.EJBException eje){
           javax.swing.JOptionPane.showMessageDialog(null, "Se perdio la conexion. Contacte con el administrador...");
        } catch(javax.naming.NameNotFoundException nnfe){
            javax.swing.JOptionPane.showMessageDialog(null, "El programa servidor no esta listo. Intente mas tarde...");
        } catch (Exception ex) {
            shrimp.helper.Excepciones.guardarExcepcionesAC(
                    ex, 
                    getClass().getName(),
                    mDIShrimp.getSisInfoTO());
        }
    }

    private void cancelar(){
        this.accion = "CANCELAR";
        manejoBotones();
        manejoControles(false);
        limpiar();
    }

    private void eventoKeyPressControles(java.awt.event.KeyEvent evt){
        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ESCAPE)
            cancelar();
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_F5){
            actualizar();
        }
    }

    private void tabulacion(){
        jftfFechaDesde.setNextFocusableComponent(jftfFechaHasta);
        jftfFechaHasta.setNextFocusableComponent(jftfFechaMaxima);
        jftfFechaMaxima.setNextFocusableComponent(jtfDescripcion);
        jtfDescripcion.setNextFocusableComponent(jbtnGuardar);
        jbtnGuardar.setNextFocusableComponent(jftfFechaDesde);
      
    }

    int fila = 0;
    private void eventos(){
         
       jftfFechaDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eventoKeyPressControles(evt);
                if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER)
                    jftfFechaHasta.requestFocus();
            }
        });
       
       jftfFechaHasta.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eventoKeyPressControles(evt);
                if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER)
                    jftfFechaMaxima.requestFocus();
            }
        });
       
       jftfFechaMaxima.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eventoKeyPressControles(evt);
                if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER)
                    jtfDescripcion.requestFocus();
            }
        });
        
       jtfDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eventoKeyPressControles(evt);
                if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER)
                    jbtnGuardar.requestFocus();
                if(jtfDescripcion.getText().length()<50)
                    shrimp.validaciones.Presionar.presionarAlfanumericoEspacioMayusculas(evt);
                else
                    evt.consume();
            }
        });
        
        jtblPeriodoXiii.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                llenarControles();
            }
        });
        jtblPeriodoXiii.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eventoKeyPressControles(evt);
                if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ESCAPE)
                    salirFormulario();
                if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER){
                    jtblPeriodoXiii.changeSelection(fila, 0, false, false);
                }
            }
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fila = jtblPeriodoXiii.getSelectedRow();
                eventoKeyPressControles(evt);
                if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER){
                    jtblPeriodoXiii.changeSelection(fila, 0, false, false);
                    llenarControles();
                }
            }
        });
      
    }

    private void teclasBotonesCalientes(){
        jbtnNuevo.setMnemonic(java.awt.event.KeyEvent.VK_N);
        jbtnModificar.setMnemonic(java.awt.event.KeyEvent.VK_M);
        jbtnEliminar.setMnemonic(java.awt.event.KeyEvent.VK_E);
        jbtnSalir.setMnemonic(java.awt.event.KeyEvent.VK_S);
        jbtnCancelar.setMnemonic(java.awt.event.KeyEvent.VK_L);
        jbtnGuardar.setMnemonic(java.awt.event.KeyEvent.VK_G);
    }

    private void manejoBotones(){
        boolean estado = true;
        if((this.accion.equals("NUEVO")
                ||(this.accion.equals("MODIFICAR")))){
            this.jbtnGuardar.setEnabled(estado);
            this.jbtnCancelar.setEnabled(estado);
            this.jbtnNuevo.setEnabled(!estado);
            this.jbtnModificar.setEnabled(!estado);
            this.jbtnBuscar.setEnabled(!estado);
            this.jbtnEliminar.setEnabled(!estado);
            estado = false;
        }
        if((this.accion.equals("GUARDAR")
                ||(this.accion.equals("CANCELAR")))
                ||(this.accion.equals("INICIO"))){
            this.jbtnGuardar.setEnabled(!estado);
            this.jbtnCancelar.setEnabled(!estado);
            this.jbtnNuevo.setEnabled(sisGrupoTO.getGruCrear());
            this.jbtnModificar.setEnabled(sisGrupoTO.getGruModificar());
            this.jbtnEliminar.setEnabled(sisGrupoTO.getGruEliminar());
            this.jbtnBuscar.setEnabled(estado);
            estado = true;
        }
        this.jbtnSalir.setEnabled(estado);
    }

    private void manejoControles(boolean estado){
        this.jftfFechaDesde.setEditable(estado);
        this.jftfFechaHasta.setEditable(estado);
        this.jftfFechaMaxima.setEditable(estado);
        this.jtfDescripcion .setEditable(estado);
    }

    private void limpiar(){
        jftfFechaDesde.setValue(validaciones.Validacion.fecha(validaciones.Validacion.getUltimoDiaDelMes(validaciones.Validacion.fechaSistema("yyyy-MM-dd"), "yyyy-MM-dd"), "dd-MM-yyyy"));
        jftfFechaHasta.setValue(validaciones.Validacion.fecha(validaciones.Validacion.getUltimoDiaDelMes(validaciones.Validacion.fechaSistema("yyyy-MM-dd"), "yyyy-MM-dd"), "dd-MM-yyyy"));
        jftfFechaMaxima.setValue(validaciones.Validacion.fecha(validaciones.Validacion.getUltimoDiaDelMes(validaciones.Validacion.fechaSistema("yyyy-MM-dd"), "yyyy-MM-dd"), "dd-MM-yyyy"));
        this.jtfDescripcion.setText("");
    }
    
    private void llenarControles(){
        if (accion.equals("INICIO")||
                accion.equals("NUEVO")||
                accion.equals("MODIFICAR")||
                accion.equals("ELIMINAR")||
                accion.equals("GUARDAR")||
                accion.equals("CANCELAR")){
            if (jtblPeriodoXiii.getSelectedRow() > -1){
                rrhh.TO.RhXiiiSueldoPeriodoTO xiiiSueldoPeriodoTO = rRHHBBFunciones.getRhComboXiiiSueldoPeriodoTO().get(jtblPeriodoXiii.convertRowIndexToModel(jtblPeriodoXiii.getSelectedRow()));
                 
                periodoPagoSecuencial = (xiiiSueldoPeriodoTO.getPeriodoSecuencial() == null)
                        ? 0 : xiiiSueldoPeriodoTO.getPeriodoSecuencial();
                jtfDescripcion.setText((xiiiSueldoPeriodoTO.getXiiiDescripcion() == null)
                        ? "" : xiiiSueldoPeriodoTO.getXiiiDescripcion());
                jftfFechaDesde.setValue(validaciones.Validacion.fecha(xiiiSueldoPeriodoTO.getXiiiDesde(), "yyyy-MM-dd", "dd-MM-yyyy"));
                jftfFechaHasta.setValue(validaciones.Validacion.fecha(xiiiSueldoPeriodoTO.getXiiiHasta(), "yyyy-MM-dd", "dd-MM-yyyy"));
                jftfFechaMaxima.setValue(validaciones.Validacion.fecha(xiiiSueldoPeriodoTO.getXiiiFechaMaximaPago(), "yyyy-MM-dd", "dd-MM-yyyy"));
            }
        }
    }

    private void llenarAtributos(){
       // vendCodigo = jtfCodigo.getText();
        periodoDescripcion = jtfDescripcion.getText();
        fechaPeriodoInico = jftfFechaDesde.getText().equals("  -  -    ") 
                ? null : validaciones.Validacion.fecha(jftfFechaDesde.getText(), "dd-MM-yyyy", "yyyy-MM-dd");//Validacion.fecha(rhEmpleadoTO.getEmpFechaNacimiento(), "yyyy-MM-dd"));
        fechaPeriodoFin  =jftfFechaHasta.getText().equals("  -  -    ") 
                ? null : validaciones.Validacion.fecha(jftfFechaHasta.getText(), "dd-MM-yyyy", "yyyy-MM-dd");
        fechaperiodoPagoMaximo = jftfFechaMaxima.getText().equals("  -  -    ") 
                ? null : validaciones.Validacion.fecha(jftfFechaMaxima.getText(), "dd-MM-yyyy", "yyyy-MM-dd");
    }
    
    private void llenarObjetoTO(){
        rhXiiiSueldoPeriodoTO = new rrhh.TO.RhXiiiSueldoPeriodoTO();
        
        rhXiiiSueldoPeriodoTO.setPeriodoSecuencial(periodoPagoSecuencial);
        rhXiiiSueldoPeriodoTO.setXiiiDesde(fechaPeriodoInico);
        rhXiiiSueldoPeriodoTO.setXiiiHasta(fechaPeriodoFin);
        rhXiiiSueldoPeriodoTO.setXiiiFechaMaximaPago(fechaperiodoPagoMaximo);
        rhXiiiSueldoPeriodoTO.setXiiiDescripcion(periodoDescripcion);
    }

    private org.jdesktop.beansbinding.BindingGroup bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

    private shrimp.rrhh.beanbinding.RRHHBBFunciones  rRHHBBFunciones  = null;
    
    private org.jdesktop.swingbinding.JTableBinding jTableBinding;
    private void llenarjTable() throws Exception{
        this.rRHHBBFunciones.setRhComboXiiiSueldoPeriodoTO(shrimp.rrhh.delegate.RrhhDelegate.getInstance().getRhComboXiiiSueldoPeriodoTO());
        this.bindingGroup.bind();//consigue los datos
        this.jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_ONCE, this.rRHHBBFunciones.getRhComboXiiiSueldoPeriodoTO()
               , jtblPeriodoXiii);
        
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
        col = jtblPeriodoXiii.getColumnModel().getColumn(0);
        col.setPreferredWidth(80);
        col = jtblPeriodoXiii.getColumnModel().getColumn(1);
        col.setPreferredWidth(200);
        /*col = jtblVendedor.getColumnModel().getColumn(2);
        col.setPreferredWidth(100);*/

        jtblPeriodoXiii.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        // Metemos el modelo ordenable en la tabla.
        javax.swing.table.TableRowSorter modeloOrdenado = new javax.swing.table.TableRowSorter(jtblPeriodoXiii.getModel());
        jtblPeriodoXiii.setRowSorter(modeloOrdenado);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtblPeriodoXiii = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jbtnNuevo = new javax.swing.JButton();
        jbtnModificar = new javax.swing.JButton();
        jbtnEliminar = new javax.swing.JButton();
        jbtnBuscar = new javax.swing.JButton();
        jbtnSalir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jbtnGuardar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jftfFechaMaxima = new shrimp.validaciones.JFormattedTextFieldDate(false);
        jftfFechaDesde = new shrimp.validaciones.JFormattedTextFieldDate(false);
        jftfFechaHasta = new shrimp.validaciones.JFormattedTextFieldDate(false);
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtfDescripcion = new javax.swing.JTextField();

        jtblPeriodoXiii.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtblPeriodoXiii);

        jbtnNuevo.setText("Nuevo");
        jbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevoActionPerformed(evt);
            }
        });

        jbtnModificar.setText("Modificar");
        jbtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarActionPerformed(evt);
            }
        });

        jbtnEliminar.setText("Eliminar");
        jbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarActionPerformed(evt);
            }
        });

        jbtnBuscar.setText("Buscar");
        jbtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarActionPerformed(evt);
            }
        });

        jbtnSalir.setText("Salir");
        jbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jbtnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnSalir))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jbtnModificar)
                .addComponent(jbtnEliminar)
                .addComponent(jbtnBuscar)
                .addComponent(jbtnSalir)
                .addComponent(jbtnNuevo))
        );

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jbtnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnCancelar))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jbtnGuardar)
                .addComponent(jbtnCancelar))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtrado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 10))); // NOI18N

        jftfFechaMaxima.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N

        jftfFechaDesde.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N

        jftfFechaHasta.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N

        jLabel2.setText("Desde:");

        jLabel4.setText("Hasta:");

        jLabel3.setText("Maxima Pago:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jftfFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jftfFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jftfFechaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftfFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftfFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftfFechaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel6.setText("Descripción");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jtfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("Periodos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed
        // TODO add your handling code here:
        this.accion = "NUEVO";
        manejoBotones();
        manejoControles(true);
        limpiar();
        this.jftfFechaDesde.requestFocus();
}//GEN-LAST:event_jbtnNuevoActionPerformed

    private void jbtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModificarActionPerformed
        // TODO add your handling code here:
        try {
            llenarjTable();
        } catch (java.rmi.RemoteException re) {
           javax.swing.JOptionPane.showMessageDialog(null, "Se perdio la conexion. Contacte con el administrador...");
        } catch (javax.ejb.EJBException eje){
           javax.swing.JOptionPane.showMessageDialog(null, "Se perdio la conexion. Contacte con el administrador...");
        } catch(javax.naming.NameNotFoundException nnfe){
            javax.swing.JOptionPane.showMessageDialog(null, "El programa servidor no esta listo. Intente mas tarde...");
        } catch (Exception e) {
            shrimp.helper.Excepciones.guardarExcepcionesAC(
                    e, 
                    getClass().getName(),
                    mDIShrimp.getSisInfoTO());
        }
         llenarAtributos();
         //llenarObjetoTO();
        if(this.periodoDescripcion.isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila antes "
                    + "de la acción..");
        }else{
            this.accion = "MODIFICAR";
            manejoBotones();
            manejoControles(true);
            this.jftfFechaDesde.requestFocus();
        }
}//GEN-LAST:event_jbtnModificarActionPerformed

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarActionPerformed
        // TODO add your handling code here:
        
        try {
            llenarjTable();
        } catch (java.rmi.RemoteException re) {
           javax.swing.JOptionPane.showMessageDialog(null, "Se perdio la conexion. Contacte con el administrador...");
        } catch (javax.ejb.EJBException eje){
           javax.swing.JOptionPane.showMessageDialog(null, "Se perdio la conexion. Contacte con el administrador...");
        } catch(javax.naming.NameNotFoundException nnfe){
            javax.swing.JOptionPane.showMessageDialog(null, "El programa servidor no esta listo. Intente mas tarde...");
        } catch (Exception e) {
            shrimp.helper.Excepciones.guardarExcepcionesAC(
                    e, 
                    getClass().getName(),
                    mDIShrimp.getSisInfoTO());
        }
        llenarAtributos();
        if(this.periodoDescripcion.isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila antes "
                    + "de la acción..");
        }else{
            this.accion = "ELIMINAR";
            guardar();
            this.jftfFechaDesde.requestFocus();
        }
}//GEN-LAST:event_jbtnEliminarActionPerformed

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscarActionPerformed
        // TODO add your handling code here:
        if (jtblPeriodoXiii.getModel().getRowCount()>0){
            String msg = javax.swing.JOptionPane.showInputDialog(this, "Filtro");
            msg = (msg == null) ? "" : msg;
            javax.swing.table.TableRowSorter modeloOrdenado = new javax.swing.table.TableRowSorter(jtblPeriodoXiii.getModel());
            modeloOrdenado.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)"+msg));
            jtblPeriodoXiii.setRowSorter(modeloOrdenado);
        }
}//GEN-LAST:event_jbtnBuscarActionPerformed

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        this.mDIShrimp.requestFocus();
}//GEN-LAST:event_jbtnSalirActionPerformed

    private void guardar(){
        
        try{
             llenarAtributos();
            if (!this.periodoDescripcion.isEmpty()){
                llenarObjetoTO();
                String mensaje = "";
                if(accion.equals("NUEVO"))
                    mensaje = shrimp.rrhh.delegate.RrhhDelegate.getInstance().
                            accionRhXiiiSueldoPeriodo(
                                rhXiiiSueldoPeriodoTO,
                                empCodigo,
                                usrCodigo,
                                'I',
                                mDIShrimp.getSisInfoTO());
                if(accion.equals("MODIFICAR"))
                    mensaje = shrimp.rrhh.delegate.RrhhDelegate.getInstance().
                            accionRhXiiiSueldoPeriodo(
                                rhXiiiSueldoPeriodoTO,
                                empCodigo,
                                usrCodigo,
                                'M',
                                mDIShrimp.getSisInfoTO());
                if(accion.equals("ELIMINAR"))
                    mensaje = shrimp.rrhh.delegate.RrhhDelegate.getInstance().
                            accionRhXiiiSueldoPeriodo(
                                rhXiiiSueldoPeriodoTO,
                                empCodigo,
                                usrCodigo,
                                'E',
                                mDIShrimp.getSisInfoTO());
                if (mensaje.charAt(0) == 'T'){
                    javax.swing.JOptionPane.showInternalMessageDialog(this, mensaje.substring(1));
                    cancelar();
                    actualizar();
                    jftfFechaDesde.requestFocus();
                } 
                
                else
                    javax.swing.JOptionPane.showInternalMessageDialog(this, mensaje.substring(1));
            }else
                javax.swing.JOptionPane.showMessageDialog(this, "Faltan llenar el formulario.\nVuelva a intentarlo");
        }  catch (java.rmi.RemoteException re) {
           javax.swing.JOptionPane.showMessageDialog(null, "Se perdio la conexion. Contacte con el administrador...");
        } catch (javax.ejb.EJBException eje){
           javax.swing.JOptionPane.showMessageDialog(null, "Se perdio la conexion. Contacte con el administrador...");
        } catch(javax.naming.NameNotFoundException nnfe){
            javax.swing.JOptionPane.showMessageDialog(null, "El programa servidor no esta listo. Intente mas tarde...");
        } catch (Exception e) {
            shrimp.helper.Excepciones.guardarExcepcionesAC(
                    e,  
                    getClass().getName(),
                    mDIShrimp.getSisInfoTO());
        }
    }

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        // TODO add your handling code here:
        guardar();
}//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        // TODO add your handling code here:
        cancelar();
}//GEN-LAST:event_jbtnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnBuscar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnModificar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JButton jbtnSalir;
    private javax.swing.JFormattedTextField jftfFechaDesde;
    private javax.swing.JFormattedTextField jftfFechaHasta;
    private javax.swing.JFormattedTextField jftfFechaMaxima;
    private javax.swing.JTable jtblPeriodoXiii;
    private javax.swing.JTextField jtfDescripcion;
    // End of variables declaration//GEN-END:variables

}
