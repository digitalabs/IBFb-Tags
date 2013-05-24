/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.germplasmlist.importing.wizard;

import ibfb.domain.core.Workbook;
import ibfb.settings.core.FieldbookSettings;
import ibfb.workbook.api.WorkbookExcelReader;
import ibfb.workbook.core.WorkbookExcelReaderImpl;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import org.cimmyt.cril.ibwb.commongui.filters.ExcelFiltro;
import org.openide.util.NbBundle;

public final class ImportGermplasmVisualPanel1 extends JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(ImportGermplasmVisualPanel1.class);
    private JFileChooser fileChooser = new JFileChooser();
    private WorkbookExcelReader workbookExcelReader = new WorkbookExcelReaderImpl();
    private Workbook workbook = new Workbook();

    /**
     * Creates new form ImportGermplasmVisualPanel1
     */
    public ImportGermplasmVisualPanel1() {
        initComponents();
    }

    @Override
    public String getName() {
        return bundle.getString("ImportGermplasmVisualPanel1.title");
    }

    private void openTemplate() {

        FileFilter[] filtros = new FileFilter[10];
        filtros = fileChooser.getChoosableFileFilters();
        String path = "";
        path = FieldbookSettings.getSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER);

        File myDesktop = new File(FieldbookSettings.getSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER));

        if (myDesktop.exists()) {
            fileChooser.setCurrentDirectory(myDesktop);
        }

        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            fileChooser.removeChoosableFileFilter(filtro);
        }

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new ExcelFiltro());
        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }

        this.txtFilePath.setText(fileChooser.getSelectedFile().toString());
        WorkbookExcelReader validateExcelReader = new WorkbookExcelReaderImpl();
        boolean isValidFile = false;

        try {
            isValidFile = validateExcelReader.isValidTemplate(this.txtFilePath.getText());
        } catch (Exception ex) {
            System.out.println("ERROR AL VALIDAD TEMPLATE DE EXCEL");
        }

        if (isValidFile) {

            this.txtFilePath.setText(fileChooser.getSelectedFile().toString());
            //this.jButtonPreviewTemplate.setEnabled(true);
            readExcelWorkbookTemplate(txtFilePath.getText());
            //habilitaGSM();


            //checkButtonsStatus();

        } else {
            this.txtFilePath.setText("");
            JOptionPane.showMessageDialog(this, bundle.getString("ImportGermplasmVisualPanel1.invalidGermplasmTempalte"), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        checkEnablePreviewButton();
    }

    private void readExcelWorkbookTemplate(String fileName) {
        try {
            workbook = workbookExcelReader.getWorkbookData(fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL LEER EXCEL A: " + ex);
        }
    }

    public JTextField getTxtFilePath() {
        return txtFilePath;
    }

    public void setTxtFilePath(JTextField txtFilePath) {
        this.txtFilePath = txtFilePath;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    private void previewFile(String path) {
        Desktop desktop = null;
        try {
            if (Desktop.isDesktopSupported() == true) {
                desktop = Desktop.getDesktop();
                File archivo = new File(path);
                desktop.open(archivo);
            }
        } catch (Exception e) {
            System.out.println("ERROR e PROCSA_ARCHIVO");
        }
    }

    private void checkEnablePreviewButton() {
        btnTemplatePreview.setEnabled(!txtFilePath.getText().isEmpty());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblExcelFile = new javax.swing.JLabel();
        txtFilePath = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        btnTemplatePreview = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(lblExcelFile, org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel1.class, "ImportGermplasmVisualPanel1.lblExcelFile.text")); // NOI18N

        txtFilePath.setText(org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel1.class, "ImportGermplasmVisualPanel1.txtFilePath.text")); // NOI18N
        txtFilePath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtFilePathMousePressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnBrowse, org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel1.class, "ImportGermplasmVisualPanel1.btnBrowse.text")); // NOI18N
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnTemplatePreview, org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel1.class, "ImportGermplasmVisualPanel1.btnTemplatePreview.text")); // NOI18N
        btnTemplatePreview.setEnabled(false);
        btnTemplatePreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTemplatePreviewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblExcelFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBrowse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTemplatePreview)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblExcelFile)
                    .addComponent(txtFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse)
                    .addComponent(btnTemplatePreview))
                .addGap(0, 277, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        openTemplate();
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void txtFilePathMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFilePathMousePressed
        openTemplate();
    }//GEN-LAST:event_txtFilePathMousePressed

    private void btnTemplatePreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemplatePreviewActionPerformed
        previewFile(txtFilePath.getText());
    }//GEN-LAST:event_btnTemplatePreviewActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnTemplatePreview;
    private javax.swing.JLabel lblExcelFile;
    private javax.swing.JTextField txtFilePath;
    // End of variables declaration//GEN-END:variables
}