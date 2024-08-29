/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaswingdev.form;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author PC
 */
public class Form_Formateur extends javax.swing.JPanel {
    private final ConnectDB dbConnection;
    private DefaultTableModel tableModel;
    
    /**
     * Creates new form Form_List
     */
    public Form_Formateur() {
        initComponents();
        
        initializeTableModel();
        displayDataInTable();// Call the method to display data
        addTableClickListener(); // Add listener for table row selection
        
        dbConnection = new ConnectDB();
        dbConnection.connect();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Modifier = new javax.swing.JButton();
        NomFrt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableFrt = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Ajouter = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Impr = new javax.swing.JButton();
        IdFrt = new javax.swing.JTextField();
        Supp = new javax.swing.JButton();
        PrenomFrt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Modifier.setBackground(new java.awt.Color(0, 51, 255));
        Modifier.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Modifier.setText("Modifier");
        Modifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModifierMouseClicked(evt);
            }
        });
        Modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierActionPerformed(evt);
            }
        });
        add(Modifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, 150, 50));

        NomFrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomFrtActionPerformed(evt);
            }
        });
        add(NomFrt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 150, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("ID:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, -1, -1));

        TableFrt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableFrt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableFrtMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableFrt);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 500, 510));

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Groupe IPIRNET");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Prenom:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));

        Ajouter.setBackground(new java.awt.Color(51, 255, 0));
        Ajouter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Ajouter.setText("Insérer");
        Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterActionPerformed(evt);
            }
        });
        add(Ajouter, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 150, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nom:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, -1));

        Impr.setBackground(new java.awt.Color(0, 204, 255));
        Impr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Impr.setText("Imprimer");
        Impr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprActionPerformed(evt);
            }
        });
        add(Impr, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 560, 150, 50));

        IdFrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdFrtActionPerformed(evt);
            }
        });
        add(IdFrt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 150, -1));

        Supp.setBackground(new java.awt.Color(255, 0, 51));
        Supp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Supp.setText("Supprimer");
        Supp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuppActionPerformed(evt);
            }
        });
        add(Supp, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 150, 50));
        add(PrenomFrt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 150, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 102, 255));
        jLabel4.setText("SYSTÈME D'INFORMATION DES FORMATEURS ");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 204));
        jLabel10.setText("Formulaire");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 204));
        jLabel9.setText("Donnee Formateur");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    
        // Table
    private void initializeTableModel() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nom");
        tableModel.addColumn("Prenom");
        TableFrt.setModel(tableModel);
    }

    private void updateTableModel() {
        tableModel.setRowCount(0); // Clear existing rows
        ConnectDB con = ConnectDB.getcon();
        con.connect();

        try {
            String sqlQuery = "SELECT * FROM Formateur";
            ResultSet rs = con.executeQuery(sqlQuery);

            while (rs.next()) {
                Object[] rowData = {
                    rs.getInt("IdFrt"),
                    rs.getString("NomFrt"),
                    rs.getString("PrenomFrt")
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Form_Formateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addTableClickListener() {
        TableFrt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = TableFrt.getSelectedRow();
                    if (selectedRow != -1) { // Ensure a row is selected
                        Object idFrtObj = TableFrt.getValueAt(selectedRow, 0);
                        Object nomFrtObj = TableFrt.getValueAt(selectedRow, 1);
                        Object prenomFrtObj = TableFrt.getValueAt(selectedRow, 2);

                        // Check for null values before converting to string
                        String idFrt = idFrtObj != null ? idFrtObj.toString() : "";
                        String nomFrt = nomFrtObj != null ? nomFrtObj.toString() : "";
                        String prenomFrt = prenomFrtObj != null ? prenomFrtObj.toString() : "";

                        IdFrt.setText(idFrt);
                        NomFrt.setText(nomFrt);
                        PrenomFrt.setText(prenomFrt);
                    }
                }
            }
        });
    }
    
     private void displayDataInTable() {
        ConnectDB con = ConnectDB.getcon();
        con.connect();

        try {
            String sqlQuery = "SELECT * FROM Formateur";
            ResultSet rs = con.executeQuery(sqlQuery);


            while (rs.next()) {
                Object[] rowData = {
                    rs.getInt("IdFrt"),
                    rs.getString("NomFrt"),
                    rs.getString("PrenomFrt")
                };
                 tableModel.addRow(rowData);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Form_Formateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     // Table END
     
     
    private void ModifierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModifierMouseClicked

    }//GEN-LAST:event_ModifierMouseClicked

    //Modifier
    private void ModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierActionPerformed
                    
            FormateurBean cb = new FormateurBean();
    
            // Parse the integer value for IdStg
            int idFrt;
            try {
                idFrt = Integer.parseInt(IdFrt.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erreur : Veuillez entrer un entier pour Id.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if parsing fails
            }
            cb.setIdFrt(idFrt);

            cb.setNomFrt(NomFrt.getText());
            cb.setPrenomFrt(PrenomFrt.getText());
            
            ConnectDB con = ConnectDB.getcon(); 
            try {
                con.connect();
                con.modifierfrt(cb); // Call the modifierstg method
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la modification du formateur : " + ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Form_Formateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            updateTableModel(); 
    }//GEN-LAST:event_ModifierActionPerformed

    private void NomFrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomFrtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomFrtActionPerformed

    private void TableFrtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableFrtMouseClicked

    }//GEN-LAST:event_TableFrtMouseClicked

    //Ajouter
    private void AjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterActionPerformed
            FormateurBean cb=new FormateurBean();
            int idFrt;
        try {
            idFrt = Integer.parseInt(IdFrt.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erreur : Veuillez entrer un entier pour Id.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if parsing fails
        }
        cb.setIdFrt(idFrt);
            cb.setNomFrt(NomFrt.getText());
            cb.setPrenomFrt(PrenomFrt.getText());
            ConnectDB con = ConnectDB.getcon();
                try {
            con.connect();
            con.ajouterfrt(cb);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du formateur : " + ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Form_Formateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateTableModel(); 
    }//GEN-LAST:event_AjouterActionPerformed

    private void ImprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprActionPerformed
    int[] selectedRows = TableFrt.getSelectedRows();

        try {
            // Load the JasperReport template
            JasperDesign jasdi = JRXmlLoader.load("C:\\Users\\PC\\JaspersoftWorkspace\\MyReports\\Formateur.jrxml");

            // Construct the SQL query dynamically based on the selected rows
            String sql ="SELECT * FROM Formateur";

            JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(sql);
        jasdi.setQuery(newQuery);

        // Compile the JasperReport template
        JasperReport js = JasperCompileManager.compileReport(jasdi);

        // Provide a database connection (replace 'con' with your actual Connection object)
        ConnectDB con = ConnectDB.getcon();

        // Fill the JasperReport with data
        JasperPrint jd = JasperFillManager.fillReport(js, null, dbConnection.con);

        // Show the report in a JasperViewer
        JasperViewer.viewReport(jd, false);

    } catch (JRException ex) {
        Logger.getLogger(Form_Formateur.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
        }
    }//GEN-LAST:event_ImprActionPerformed
    
    //Supprimer (Delete)
    private void SuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuppActionPerformed
        // TODO add your handling code here:
        
         String idToDelete = IdFrt.getText(); // Get ID from text field or wherever it's stored

            ConnectDB con = ConnectDB.getcon();
            try {
                con.deleteFormateur(idToDelete); // Call the delete method
                updateTableModel(); // Refresh the table after deletion
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du formateur : " + ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Form_Formateur.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_SuppActionPerformed

    private void IdFrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdFrtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdFrtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ajouter;
    private javax.swing.JTextField IdFrt;
    private javax.swing.JButton Impr;
    private javax.swing.JButton Modifier;
    private javax.swing.JTextField NomFrt;
    private javax.swing.JTextField PrenomFrt;
    private javax.swing.JButton Supp;
    private javax.swing.JTable TableFrt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}