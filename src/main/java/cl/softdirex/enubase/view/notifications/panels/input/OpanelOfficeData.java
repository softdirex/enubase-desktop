/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.softdirex.enubase.view.notifications.panels.input;

import cl.softdirex.enubase.dao.Dao;
import cl.softdirex.enubase.entities.Inventario;
import cl.softdirex.enubase.entities.Oficina;
import cl.softdirex.enubase.utils.BDUtils;
import cl.softdirex.enubase.utils.GV;
import cl.softdirex.enubase.utils.Icons;
import cl.softdirex.enubase.utils.StEntities;
import cl.softdirex.enubase.utils.GlobalValuesVariables;
import cl.softdirex.enubase.utils.SubProcess;
import cl.softdirex.enubase.utils.XmlUtils;
import cl.softdirex.enubase.view.notifications.OptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sdx
 */
public class OpanelOfficeData extends javax.swing.JPanel {
    
    private static int OPTION = 0;//0: desde GUI, 1: desde INIT
    /**
     * Creates new form OpanelSelectDate
     */
    public OpanelOfficeData(int option) {
        initComponents();
        OPTION = option;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEmail = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblWeb = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtMail = new javax.swing.JTextField();
        txtWeb = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lblDireccion = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txtDireccion = new javax.swing.JTextField();
        lclCiudad = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));

        lblEmail.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblEmail.setText("Email");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel_50px.png"))); // NOI18N
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelarMousePressed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept_50px.png"))); // NOI18N
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGuardarMousePressed(evt);
            }
        });

        lblTelefono.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblTelefono.setText("Teléfono");

        lblWeb.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblWeb.setText("Web");

        txtTelefono.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txtTelefono.setBorder(null);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtMail.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txtMail.setBorder(null);
        txtMail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMailFocusLost(evt);
            }
        });
        txtMail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMailKeyTyped(evt);
            }
        });

        txtWeb.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txtWeb.setBorder(null);
        txtWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWebActionPerformed(evt);
            }
        });
        txtWeb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtWebKeyTyped(evt);
            }
        });

        lblDireccion.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblDireccion.setText("Dirección");

        txtDireccion.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txtDireccion.setBorder(null);
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        lclCiudad.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lclCiudad.setText("Ciudad");

        txtCiudad.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txtCiudad.setBorder(null);
        txtCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCiudadActionPerformed(evt);
            }
        });
        txtCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCiudadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefono)
                    .addComponent(lblEmail)
                    .addComponent(lblWeb)
                    .addComponent(lblDireccion)
                    .addComponent(lclCiudad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDireccion)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(jSeparator6))
                                .addGap(108, 108, 108)
                                .addComponent(btnCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar))
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtWeb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMail, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblWeb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lclCiudad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCiudad, txtDireccion, txtMail, txtTelefono, txtWeb});

    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        if(OPTION == 0){
            OptionPane.closeOptionPanel();
        }else{
            if(OptionPane.getConfirmation("Cancelar todo", "¿Desea cancelar todo y volver a intentar mas tarde?", 1)){
                BDUtils.dropDB();
                System.exit(0);
            }else{
                return;
            }
        }
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnCancelar.getIcon().toString()))));
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnCancelar.getIcon().toString()))));
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnCancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarMousePressed

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
        if(OPTION == 0){
            saveFromGUI();
        }else{
            saveFromInit();
        }
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnGuardar.getIcon().toString()))));
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnGuardar.getIcon().toString()))));
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnGuardarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarMousePressed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        int largo = 25;
        if(txtTelefono.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "El telefono solo debe contener hasta 25 caracteres", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtMailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMailKeyTyped
        int largo = 45;
        
        if(txtMail.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "El email solo debe contener 45 digitos", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtMailKeyTyped

    private void txtWebKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWebKeyTyped
        int largo = 45;
        if(txtWeb.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "El sitio web solo debe contener hasta 45 caracteres", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtWebKeyTyped

    private void txtWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWebActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtWebActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        int largo = 45;
        if(txtDireccion.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "La dirección ingresada solo debe contener hasta 45 caracteres", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtMailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMailFocusLost
        
    }//GEN-LAST:event_txtMailFocusLost

    private void txtCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCiudadActionPerformed

    private void txtCiudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCiudadKeyTyped
        int largo = 45;
        if(txtCiudad.getText().length() >= largo){
            evt.consume();
            OptionPane.showMsg("Error de ingreso de datos", "La ciudad ingresada solo debe contener hasta 45 caracteres", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtCiudadKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCancelar;
    private javax.swing.JLabel btnGuardar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblWeb;
    private javax.swing.JLabel lclCiudad;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtWeb;
    // End of variables declaration//GEN-END:variables


    private void saveFromGUI() {
    }

    private void saveFromInit() {
        String telefono = GV.getStr(txtTelefono.getText());
        String mail = GV.getStr(txtMail.getText());
        String web = GV.getStr(txtWeb.getText());
        String direccion = GV.getStr(txtDireccion.getText());
        String ciudad = GV.getStr(txtCiudad.getText());
        if(telefono.isEmpty() || mail.isEmpty() || direccion.isEmpty() || ciudad.isEmpty()){
            String msgReject = "Los siguientes campos son obligatorios:\n\n"
                    + "-Teléfono\n"
                    + "-Email\n"
                    + "-Dirección\n"
                    + "-Ciudad\n"
                    + "Complete los campos restantes para continuar.";
            OptionPane.showMsg("Faltan campos obligatorios", msgReject, 2);
            return;
        }
        if(OptionPane.getConfirmation("Confirmar y cerrar", "Para finalizar correctamente la operación el sistema se cerrará.\n"
                + "Si confirma los datos, deberá volver a iniciar el programa.\n"
                + "¿Estas seguro que los datos son correctos?", 1)){
            String officeName = "Local "+ciudad;
            String inventarioName = "Inventario "+officeName;
            Oficina of = new Oficina(0, officeName, direccion, ciudad, telefono, "", mail, web, 1, null, 0);
            Dao load = new Dao();
            SubProcess.report("Nuevo registro", "Reporte de nueva instalación de "
                    +GlobalValuesVariables.getProjectName()+" "+GlobalValuesVariables.getVersion()+"\n"
                    + XmlUtils.imprimirDatosLeidos());
            try {
                load.add(of);
                Inventario inv = new Inventario(0, inventarioName, 
                        "Inventario principal para la administracíon de insumos del local", 
                        1, null, 0);
                load.add(inv);
                StEntities.setOficina(officeName);
                GlobalValuesVariables.setInventarioLocal(inventarioName);
                OptionPane.closeOptionPanel();
                XmlUtils.crearRegistroLocal();
                System.exit(0);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(OpanelOfficeData.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showMsg("Error al inicio", "No se pudo conectar con la base de datos\n"
                        + ex.getMessage(), 3);
            }
        }else{
            return;
        }
    }
}
