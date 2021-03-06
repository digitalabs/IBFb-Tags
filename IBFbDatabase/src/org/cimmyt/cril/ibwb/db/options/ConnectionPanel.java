/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConnectionPanel.java
 *
 * Created on May 26, 2011, 10:11:12 AM
 */
package org.cimmyt.cril.ibwb.db.options;

import javax.swing.DefaultComboBoxModel;
import org.cimmyt.cril.ibwb.db.util.DatabaseInfo;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author TMSANCHEZ
 */
public class ConnectionPanel extends javax.swing.JPanel {

    private String databaseTitle;

    public ConnectionPanel(String databaseTitle) {
        initComponents();
        //initComboBoxes();
        this.databaseTitle = databaseTitle;

    }

    /** Creates new form ConnectionPanel */
    public ConnectionPanel() {
        initComponents();
        //initComboBoxes();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblDatabaseTypeCentral = new javax.swing.JLabel();
        cboDatabaseType = new javax.swing.JComboBox();
        lblServerNameCentral = new javax.swing.JLabel();
        lblSErverPortCentral = new javax.swing.JLabel();
        spnPort = new javax.swing.JSpinner();
        lblDatabaseName = new javax.swing.JLabel();
        txtDatabaseName = new javax.swing.JTextField();
        lblUserNameCentral = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnTestConnection = new javax.swing.JButton();
        txtServerName = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.border.title"))); // NOI18N
        setLayout(new java.awt.GridBagLayout());

        lblDatabaseTypeCentral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDatabaseTypeCentral.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.lblDatabaseTypeCentral.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(lblDatabaseTypeCentral, gridBagConstraints);

        cboDatabaseType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDatabaseType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDatabaseTypeItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 176;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(cboDatabaseType, gridBagConstraints);

        lblServerNameCentral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblServerNameCentral.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.lblServerNameCentral.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(lblServerNameCentral, gridBagConstraints);

        lblSErverPortCentral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSErverPortCentral.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.lblSErverPortCentral.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(lblSErverPortCentral, gridBagConstraints);

        spnPort.setEditor(new javax.swing.JSpinner.NumberEditor(spnPort, "####"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 62;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(spnPort, gridBagConstraints);

        lblDatabaseName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDatabaseName.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.lblDatabaseName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(lblDatabaseName, gridBagConstraints);

        txtDatabaseName.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.txtDatabaseName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 221;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(txtDatabaseName, gridBagConstraints);

        lblUserNameCentral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUserNameCentral.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.lblUserNameCentral.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        add(lblUserNameCentral, gridBagConstraints);

        txtUserName.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.txtUserName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 221;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(txtUserName, gridBagConstraints);

        txtPassword.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.txtPassword.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 221;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(txtPassword, gridBagConstraints);

        btnTestConnection.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.btnTestConnection.text")); // NOI18N
        btnTestConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestConnectionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        add(btnTestConnection, gridBagConstraints);

        txtServerName.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.txtServerName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(txtServerName, gridBagConstraints);

        lblPassword.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.lblPassword.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(lblPassword, gridBagConstraints);

        lblImagen.setText(org.openide.util.NbBundle.getMessage(ConnectionPanel.class, "ConnectionPanel.lblImagen.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        add(lblImagen, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cboDatabaseTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDatabaseTypeItemStateChanged
        checkCentralDatabaseType();
}//GEN-LAST:event_cboDatabaseTypeItemStateChanged

    private void btnTestConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestConnectionActionPerformed
        checkDatabaseConnection(true);
}//GEN-LAST:event_btnTestConnectionActionPerformed

    /**
     * Initializes combo boxes with database type constants
     */
    private void initComboBoxes() {
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) cboDatabaseType.getModel();
        dcbm.removeAllElements();
        for (DatabaseType databaseType : DatabaseType.values()) {
            dcbm.addElement(databaseType);
        }
    }

    private void checkCentralDatabaseType() {
        DatabaseType selectedDB = (DatabaseType) cboDatabaseType.getSelectedItem();
        if (selectedDB != null) {
            Boolean enableEditing = selectedDB.equals(DatabaseType.MYSQL);
            txtServerName.setEditable(enableEditing);
            txtUserName.setEditable(enableEditing);
            txtPassword.setEditable(enableEditing);
            spnPort.setEnabled(enableEditing);
            if (selectedDB.equals(DatabaseType.MYSQL)) {
                lblDatabaseName.setText("Database Name");
            } else {
                lblDatabaseName.setText("Data Source Name");

            }
        }
        txtServerName.setText("");
        txtUserName.setText("");
        txtDatabaseName.setText("");
        txtPassword.setText("");
        cboDatabaseType.setSelectedItem(selectedDB);
    }

    /**
     * Validates if can connect to central database using filled data in fields
     * @param showDialog if Dialog message should appear
     * @return <code>true</code> if connection is success, <code>false</code> if fails
     */
    private boolean checkDatabaseConnection(boolean showDialog) {
        DatabaseType selectedDB = (DatabaseType) cboDatabaseType.getSelectedItem();
        String url = getUrl();

        String userName = txtUserName.getText();
        String password = new String(txtPassword.getPassword());

        return checkDatabaseConnection(selectedDB, url, userName, password, databaseTitle, showDialog);
    }

    /**
     * Checks if can connect to a database
     * @param databaseType Database Type to connect
     * @param url URL database
     * @param userName user name for database
     * @param password password
     * @param database database name 
     * @param showDialog if true show a Dialog box with result
     * @return  <code>true</code> if connection is success, <code>false</code> if fails
     */
    private boolean checkDatabaseConnection(DatabaseType databaseType, String url, String userName, String password, String database, boolean showDialog) {
        boolean connectionSuccess = false;
        NotifyDescriptor nd = null;
        if (ConnectionUtils.connectionSuccess(databaseType, url, userName, password)) {
            nd = new NotifyDescriptor.Message(database + " Database Connection Success");
            connectionSuccess = true;
        } else {
            nd = new NotifyDescriptor.Message("Cannot connect to " + database + " Database");
        }
        if (showDialog) {
            DialogDisplayer.getDefault().notify(nd);
        }
        return connectionSuccess;
    }

    /**
     * Gets the URL connection using fields for central connection
     * @return JDBC URL connection
     */
    private String getUrl() {
        DatabaseType selectedDB = (DatabaseType) cboDatabaseType.getSelectedItem();
        DatabaseInfo dbInfo = ConnectionUtils.getDatabaseInfo(selectedDB);
        StringBuilder url = new StringBuilder();
        url.append(dbInfo.getJdbcPrefix());

        if (selectedDB.equals(DatabaseType.MYSQL)) {
            url.append(txtServerName.getText());
            url.append(":").append(spnPort.getValue());
            url.append("/").append(txtDatabaseName.getText());
        } else {
            url.append(txtDatabaseName.getText());
        }
        return url.toString();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTestConnection;
    private javax.swing.JComboBox cboDatabaseType;
    private javax.swing.JLabel lblDatabaseName;
    private javax.swing.JLabel lblDatabaseTypeCentral;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSErverPortCentral;
    private javax.swing.JLabel lblServerNameCentral;
    private javax.swing.JLabel lblUserNameCentral;
    private javax.swing.JSpinner spnPort;
    private javax.swing.JTextField txtDatabaseName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtServerName;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
