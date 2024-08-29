/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaswingdev.form;

    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javax.swing.JOptionPane;
    import javax.swing.table.DefaultTableModel;
    import java.sql.PreparedStatement;
    import java.sql.Connection;



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
public class Form_Stn extends javax.swing.JPanel {

    /**
     * Creates new form Form_Stn
     */
        private final ConnectDB dbConnection; // Declare a private field for ConnectDB
        private DefaultTableModel tableModel;

    
    public Form_Stn() {
        initComponents();
        populateComboBox();
        
                dbConnection = new ConnectDB();
        dbConnection.connect();
        
        initializeTableModel();
                updateTableModel(); // Optional: Update table data initially
        addTableClickListener();

    }
    
    // ComboBox Stagiaire
    private void populateComboBox() {
       ConnectDB con = ConnectDB.getcon();
       con.connect();

       try {
           String sqlQuery = "SELECT NomStg, PrenomStg, Filiere, IdStg FROM Stagaire";
           ResultSet rs = con.executeQuery(sqlQuery);

           while (rs.next()) {
               String nomStg = rs.getString("NomStg");
               String prenomStg = rs.getString("PrenomStg");
               String filiere = rs.getString("Filiere");
               String idStg = rs.getString("IdStg");
               String fullName = idStg + " - " + nomStg + " " + prenomStg + " - " + filiere;
               // Add the full name to the combo box
               comboBox.addItem(fullName);
           }

       } catch (SQLException ex) {
           Logger.getLogger(Form_Stn.class.getName()).log(Level.SEVERE, null, ex);
       }

       comboBox.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String selectedFullName = (String) comboBox.getSelectedItem();
               String[] parts = selectedFullName.split(" - ");
               String idStg = parts[0]; // Extract the IdStg part
               String fullName = parts[1];
               String filiere = parts[2]; // Extract the filiere part
               IdStg.setText(idStg);
               Filiere.setText(filiere);
               FullName.setText(fullName);
           }
       });
   }
    
    // table
    private void initializeTableModel() {
       // Initialize the table model with columns from both tables
       tableModel = new DefaultTableModel();
       TableStn.setModel(tableModel);

       // Add columns from Sujet_these table
       tableModel.addColumn("ID");
       tableModel.addColumn("Sujet");
       tableModel.addColumn("Description");

       // Add columns from Soutenance table
       tableModel.addColumn("IdStn");
       tableModel.addColumn("DateSoutenance");
       tableModel.addColumn("Salle");
       tableModel.addColumn("Note1");
       tableModel.addColumn("Note2");
       tableModel.addColumn("Note3");
       tableModel.addColumn("Note4");
       tableModel.addColumn("Note5");
       tableModel.addColumn("Note6");
       tableModel.addColumn("Note7");
       tableModel.addColumn("Note8");
       tableModel.addColumn("Note9");
       tableModel.addColumn("Note10");
   }

    private void updateTableModel() {
        tableModel.setRowCount(0); // Clear existing rows
        ConnectDB con = ConnectDB.getcon();
        con.connect();

        try {
            String sqlQuery = "SELECT IdSujet, Sujet, Descriptio FROM Sujet_these";
            ResultSet rs = con.executeQuery(sqlQuery);

            while (rs.next()) {
                Object[] rowData = {
                    rs.getInt("IdSujet"),
                    rs.getString("Sujet"),
                    rs.getString("Descriptio"),
                    null, // Placeholder for IdStn (will be populated later)
                    null, // Placeholder for DateSoutenance
                    null, // Placeholder for Salle
                    null, // Placeholder for Note1
                    null, // Placeholder for Note2
                    null, // Placeholder for Note3
                    null, // Placeholder for Note4
                    null, // Placeholder for Note5
                    null, // Placeholder for Note6
                    null, // Placeholder for Note7
                    null, // Placeholder for Note8
                    null, // Placeholder for Note9
                    null  // Placeholder for Note10
                };
                tableModel.addRow(rowData);
            }
            rs.close();

            // Now fetch data from Soutenance table and update placeholders
            sqlQuery = "SELECT IdStn, DateSoutenance, Salle, Note1, Note2, Note3, Note4, Note5, Note6, Note7, Note8, Note9, Note10 FROM Soutenance";
            rs = con.executeQuery(sqlQuery);

            int row = 0;
            while (rs.next()) {
                // Update placeholders with data from Soutenance table
                tableModel.setValueAt(rs.getInt("IdStn"), row, 3);
                tableModel.setValueAt(rs.getString("DateSoutenance"), row, 4);
                tableModel.setValueAt(rs.getString("Salle"), row, 5);
                tableModel.setValueAt(rs.getDouble("Note1"), row, 6);
                tableModel.setValueAt(rs.getDouble("Note2"), row, 7);
                tableModel.setValueAt(rs.getDouble("Note3"), row, 8);
                tableModel.setValueAt(rs.getDouble("Note4"), row, 9);
                tableModel.setValueAt(rs.getDouble("Note5"), row, 10);
                tableModel.setValueAt(rs.getDouble("Note6"), row, 11);
                tableModel.setValueAt(rs.getDouble("Note7"), row, 12);
                tableModel.setValueAt(rs.getDouble("Note8"), row, 13);
                tableModel.setValueAt(rs.getDouble("Note9"), row, 14);
                tableModel.setValueAt(rs.getDouble("Note10"), row, 15);

                row++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_Stn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addTableClickListener() {
        TableStn.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = TableStn.getSelectedRow();
                    if (selectedRow != -1) { // Ensure a row is selected
                        Object idSujetObj = TableStn.getValueAt(selectedRow, 0);
                        Object sujetObj = TableStn.getValueAt(selectedRow, 1);
                        Object idStnObj = TableStn.getValueAt(selectedRow, 3); // Assuming IdStn is at index 3

                        // Check for null values before converting to string
                        String idSujet = idSujetObj != null ? idSujetObj.toString() : "";
                        String sujet = sujetObj != null ? sujetObj.toString() : "";
                        String idStn = idStnObj != null ? idStnObj.toString() : "";

                        IdSujet.setText(idSujet);
                        Sujet.setText(sujet);
                        IdStn.setText(idStn);

                        // Call a method to display additional data based on idSujet and idStn
                        displayAdditionalData(idSujet, idStn);
                    }
                }
            }
        });
    }
  
    private void displayAdditionalData(String idSujet, String idStn) {
        ConnectDB con = ConnectDB.getcon();
        con.connect();

        try {
            // Fetch additional data from Sujet_these table based on IdSujet
            String sqlSujetQuery = "SELECT Descriptio FROM Sujet_these WHERE IdSujet = ?";
            PreparedStatement sujetStatement = con.getConnection().prepareStatement(sqlSujetQuery);
            sujetStatement.setString(1, idSujet);
            ResultSet sujetRs = sujetStatement.executeQuery();

            String description = "";
            if (sujetRs.next()) {
                description = sujetRs.getString("Descriptio");
            }

            // Fetch additional data from Soutenance table based on IdStn
            String sqlSoutenanceQuery = "SELECT DateSoutenance, Salle, Note1, Note2, Note3, Note4, Note5, Note6, Note7, Note8, Note9, Note10 FROM Soutenance WHERE IdStn = ?";
            PreparedStatement soutenanceStatement = con.getConnection().prepareStatement(sqlSoutenanceQuery);
            soutenanceStatement.setString(1, idStn);
            ResultSet soutenanceRs = soutenanceStatement.executeQuery();

            // Update UI with fetched additional data
            Descriptio.setText(description); // Assuming Description is a JLabel or similar component
            while (soutenanceRs.next()) {
                DateSoutenance.setText(soutenanceRs.getString("DateSoutenance"));
                Salle.setText(soutenanceRs.getString("Salle"));
                Note1.setText(Double.toString(soutenanceRs.getDouble("Note1")));
                Note2.setText(Double.toString(soutenanceRs.getDouble("Note2")));
                Note3.setText(Double.toString(soutenanceRs.getDouble("Note3")));
                Note4.setText(Double.toString(soutenanceRs.getDouble("Note4")));
                Note5.setText(Double.toString(soutenanceRs.getDouble("Note5")));
                Note6.setText(Double.toString(soutenanceRs.getDouble("Note6")));
                Note7.setText(Double.toString(soutenanceRs.getDouble("Note7")));
                Note8.setText(Double.toString(soutenanceRs.getDouble("Note8")));
                Note9.setText(Double.toString(soutenanceRs.getDouble("Note9")));
                Note10.setText(Double.toString(soutenanceRs.getDouble("Note10")));
            }

            // Fetch data from Stagiaire table based on IdStn
            String sqlStagiaireQuery = "SELECT IdStg, NomStg, PrenomStg, Filiere FROM Stagaire WHERE IdStg IN (SELECT IdStg FROM Soutenance WHERE IdStn = ?)";
            PreparedStatement stagiaireStatement = con.getConnection().prepareStatement(sqlStagiaireQuery);
            stagiaireStatement.setString(1, idStn);
            ResultSet stagiaireRs = stagiaireStatement.executeQuery();

            while (stagiaireRs.next()) {
                // Process data from Stagiaire table (assuming column names in the table)
                int idStg = stagiaireRs.getInt("IdStg");
                String nameStg = stagiaireRs.getString("NomStg");
                String prenomStg = stagiaireRs.getString("PrenomStg");
                String filiere = stagiaireRs.getString("Filiere");

                // Update UI components with data from Stagiaire table
                IdStg.setText(Integer.toString(idStg)); // Assuming IdStg is a JLabel or similar component
                FullName.setText(nameStg + " " + prenomStg); // Assuming FullName is a JLabel or similar component
                Filiere.setText(filiere);
                // Update other JTextFields or components accordingly
            }

            sujetRs.close();
            sujetStatement.close();
            soutenanceRs.close();
            soutenanceStatement.close();
            stagiaireRs.close();
            stagiaireStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_Stn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Supp = new javax.swing.JButton();
        Impr = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        Ajouter = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Descriptio = new javax.swing.JTextArea();
        Modifier = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        Sujet = new javax.swing.JTextField();
        Salle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Note10 = new javax.swing.JTextField();
        Note9 = new javax.swing.JTextField();
        Note8 = new javax.swing.JTextField();
        Note7 = new javax.swing.JTextField();
        Note6 = new javax.swing.JTextField();
        Note4 = new javax.swing.JTextField();
        Note5 = new javax.swing.JTextField();
        Note3 = new javax.swing.JTextField();
        Note2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableStn = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        IdStn = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        Note1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        DateSoutenance = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        IdSujet = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        Filiere = new javax.swing.JLabel();
        FullName = new javax.swing.JLabel();
        IdStg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Supp.setBackground(new java.awt.Color(255, 0, 51));
        Supp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Supp.setText("Supprimer");
        Supp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuppActionPerformed(evt);
            }
        });
        add(Supp, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 140, 50));

        Impr.setBackground(new java.awt.Color(0, 204, 255));
        Impr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Impr.setText("Imprimer");
        Impr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprActionPerformed(evt);
            }
        });
        add(Impr, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 610, 140, 50));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Pertinence des resultats obtenus                         :");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, 350, 20));

        Ajouter.setBackground(new java.awt.Color(51, 255, 0));
        Ajouter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Ajouter.setText("Insérer");
        Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterActionPerformed(evt);
            }
        });
        add(Ajouter, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 140, 50));

        Descriptio.setColumns(20);
        Descriptio.setRows(5);
        jScrollPane2.setViewportView(Descriptio);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 150, 280, 100));

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
        add(Modifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, 140, 50));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Description:");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 90, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Intitule du projet:");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 130, -1));

        Sujet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SujetActionPerformed(evt);
            }
        });
        add(Sujet, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 270, -1));

        Salle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalleActionPerformed(evt);
            }
        });
        add(Salle, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 120, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID Stn:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 60, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Nom Prenom:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 100, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Id Stagiaire:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, 90, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 102, 255));
        jLabel1.setText("DE FIN DE FORMATION");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 102, 255));
        jLabel4.setText("SYSTÈME D'EVALUATION APPLIQUEE AUX TRAVAUX INDIVIDUELS");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Grille d'evaluation |5    |4    |3    |2    |1");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 290, 20));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Interet du sujet traite                                            :");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 350, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Demarache adoptee pour realiser le travail      :");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 350, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Presentation et organisation generale               :");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 350, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Methodes et outils de l'expose                              :");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 350, 20));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Qualite de l'expose                                                  :");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 350, 20));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Clarte des propos et elocution                              :");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 350, 20));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Maitrise du sujet expose                                        :");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 350, 20));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Capacite a developper des reponses                     :");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 410, 350, 20));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Maitrise de soi durant l'expose                               :");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 450, 360, 20));
        add(Note10, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 450, 60, -1));
        add(Note9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 410, 60, 20));
        add(Note8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 370, 60, -1));
        add(Note7, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 330, 60, -1));
        add(Note6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 290, 60, 20));
        add(Note4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, 60, 20));
        add(Note5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, 60, 20));
        add(Note3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, 60, -1));
        add(Note2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, 60, -1));

        TableStn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableStn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableStnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableStn);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 540, 780, 130));

        jLabel27.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 0, 204));
        jLabel27.setText("Stagiaire");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 103, -1, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI Historic", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Groupe IPIRNET");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Salle :");
        add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 50, -1));

        IdStn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdStnActionPerformed(evt);
            }
        });
        add(IdStn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 120, -1));

        jLabel30.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 0, 204));
        jLabel30.setText("Donnee soutenance");
        add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, -1, -1));
        add(comboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 290, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Filiere:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 60, 20));

        Note1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Note1ActionPerformed(evt);
            }
        });
        add(Note1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, 60, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 890, 240));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Date Soutenance :");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 140, -1));

        DateSoutenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateSoutenanceActionPerformed(evt);
            }
        });
        add(DateSoutenance, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 120, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("ID Sujet:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 80, -1));

        IdSujet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdSujetActionPerformed(evt);
            }
        });
        add(IdSujet, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 120, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 1120, 120));

        Filiere.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(Filiere, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 110, 110, 20));

        FullName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(FullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 210, 20));

        IdStg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(IdStg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 110, 90, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void SuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuppActionPerformed
        // TODO add your handling code here:
                int idStnToDelete;
        try {
            idStnToDelete = Integer.parseInt(IdStn.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erreur : Veuillez entrer un ID valide.");
            return;
        }

        ConnectDB con = ConnectDB.getcon();
        try {
            con.connect();
            con.supprimerSujet(idStnToDelete);
            JOptionPane.showMessageDialog(null, "Enregistrement supprimé avec succès.");
        } catch (SQLException ex) {
            Logger.getLogger(Form_Stn.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de l'enregistrement.");
        }

        updateTableModel();
    }//GEN-LAST:event_SuppActionPerformed

    private void ImprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprActionPerformed
   int[] selectedRows = TableStn.getSelectedRows();

    try {
        // Load the JasperReport template
        JasperDesign jasdi = JRXmlLoader.load("C:\\Users\\PC\\JaspersoftWorkspace\\MyReports\\Stn.jrxml");

        // Construct the SQL query dynamically based on the selected rows
        StringBuilder sqlBuilder = new StringBuilder("SELECT dbo.Stagaire.NomStg, dbo.Stagaire.IdStg, dbo.Stagaire.PrenomStg, dbo.Stagaire.Filiere, dbo.Stagaire.Promotion, dbo.Sujet_these.Descriptio, dbo.Sujet_these.Sujet, dbo.Soutenance.Note1, dbo.Soutenance.Note2, dbo.Soutenance.Note3, \n" +
        "                  dbo.Soutenance.Note5, dbo.Soutenance.Note4, dbo.Soutenance.Note6, dbo.Soutenance.Note7, dbo.Soutenance.Note8, dbo.Soutenance.Note9, dbo.Soutenance.Note10\n" +
        "FROM     dbo.Soutenance INNER JOIN\n" +
        "                  dbo.Stagaire ON dbo.Soutenance.IdStg = dbo.Stagaire.IdStg INNER JOIN\n" +
        "                  dbo.Sujet_these ON dbo.Soutenance.IdStn = dbo.Sujet_these.IdStn");

        if (selectedRows.length > 0) {
            sqlBuilder.append(" WHERE dbo.Stagaire.IdStg IN ("); // Assuming 'IdStg' is from dbo.Stagaire
            for (int i = 0; i < selectedRows.length; i++) {
                String id = IdStg.getText(); // Get the value from your label
                sqlBuilder.append("'").append(id).append("'");
                if (i < selectedRows.length - 1) {
                    sqlBuilder.append(",");
                }
            }
            sqlBuilder.append(")");
        }

        // Set the constructed SQL query
        String sql = sqlBuilder.toString();
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
        JasperViewer.viewReport(jd,false);

    } catch (JRException ex) {
        Logger.getLogger(Form_Stn.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_ImprActionPerformed

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterActionPerformed
        StnBean stn = new StnBean();

        int idStg; // Change variable name from IdStg to idStg
        try {
            // Parse the selected IdStg from the combobox
            idStg = Integer.parseInt(comboBox.getSelectedItem().toString().split(" - ")[0]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erreur : Veuillez sélectionner un IdStagiaire valide.");
            return; // Exit the method if parsing fails
        }
        
        int idStn;
        double note1, note2, note3, note4, note5, note6, note7, note8, note9, note10;

        try {
            idStn = Integer.parseInt(IdStn.getText());
            note1 = Double.parseDouble(Note1.getText());
            note2 = Double.parseDouble(Note2.getText());
            note3 = Double.parseDouble(Note3.getText());
            note4 = Double.parseDouble(Note4.getText());
            note5 = Double.parseDouble(Note5.getText());
            note6 = Double.parseDouble(Note6.getText());
            note7 = Double.parseDouble(Note7.getText());
            note8 = Double.parseDouble(Note8.getText());
            note9 = Double.parseDouble(Note9.getText());
            note10 = Double.parseDouble(Note10.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erreur : Veuillez entrer des nombres valides.");
            return; // Exit the method if parsing fails for any variable
        }
            stn.setIdStn(idStn);
            stn.setDateSoutenance(DateSoutenance.getText());
            stn.setSalle(Salle.getText());
            stn.setNote1(note1);
            stn.setNote2(note2);
            stn.setNote3(note3);
            stn.setNote4(note4);
            stn.setNote5(note5);
            stn.setNote6(note6);
            stn.setNote7(note7);
            stn.setNote8(note8);
            stn.setNote9(note9);
            stn.setNote10(note10);
            
        int idSujet;

            try {
                idSujet = Integer.parseInt(IdSujet.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erreur : Veuillez entrer des entiers valides.");
                return; // Exit the method if parsing fails for any variable
            }
            stn.setIdSujet(idSujet);
            stn.setSujet(Sujet.getText());
            stn.setDescriptio(Descriptio.getText());


        ConnectDB con = ConnectDB.getcon();
        try {
            con.connect();
            con.ajouterSujet(stn, idStg);
        } catch (SQLException ex) {
            Logger.getLogger(Form_Stn.class.getName()).log(Level.SEVERE, null, ex);
        }
                    updateTableModel(); 
    }//GEN-LAST:event_AjouterActionPerformed

    private void ModifierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModifierMouseClicked

    }//GEN-LAST:event_ModifierMouseClicked

    private void ModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierActionPerformed
            StnBean stn = new StnBean();
 
        int idStn, idSujet;
        double note1, note2, note3, note4, note5, note6, note7, note8, note9, note10;
        try {
            idStn = Integer.parseInt(IdStn.getText());
            idSujet = Integer.parseInt(IdSujet.getText());
            note1 = Double.parseDouble(Note1.getText());
            note2 = Double.parseDouble(Note2.getText());
            note3 = Double.parseDouble(Note3.getText());
            note4 = Double.parseDouble(Note4.getText());
            note5 = Double.parseDouble(Note5.getText());
            note6 = Double.parseDouble(Note6.getText());
            note7 = Double.parseDouble(Note7.getText());
            note8 = Double.parseDouble(Note8.getText());
            note9 = Double.parseDouble(Note9.getText());
            note10 = Double.parseDouble(Note10.getText());      

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erreur : Veuillez entrer des valeurs valides.");
            return;
        }
        stn.setIdStn(idStn);
        stn.setIdSujet(idSujet);
        stn.setDateSoutenance(DateSoutenance.getText());
        stn.setSalle(Salle.getText());
        stn.setNote1(note1);
        stn.setNote2(note2);
        stn.setNote3(note3);
        stn.setNote4(note4);
        stn.setNote5(note5);
        stn.setNote6(note6);
        stn.setNote7(note7);
        stn.setNote8(note8);
        stn.setNote9(note9);
        stn.setNote10(note10);
        stn.setSujet(Sujet.getText());
        stn.setDescriptio(Descriptio.getText());

        ConnectDB con = ConnectDB.getcon();
        try {
            con.connect();
            con.modifierSujet(stn);
        } catch (SQLException ex) {
            Logger.getLogger(Form_Stn.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur lors de la modification des données.");
        }

        updateTableModel();
    }//GEN-LAST:event_ModifierActionPerformed

    private void SujetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SujetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SujetActionPerformed

    private void SalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SalleActionPerformed

    private void TableStnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableStnMouseClicked
        // TODO add your handling code here:
//        DefaultTableModel model = (DefaultTableModel)TableStn.getModel();
//        int selectedIndex = TableStn.getSelectedRow();
//
//        int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
//        txtfirstname.setText(model.getValueAt(selectedIndex, 1).toString());
//        txtlastname.setText(model.getValueAt(selectedIndex, 2).toString());
//        txtemail.setText(model.getValueAt(selectedIndex, 3).toString());
//        txtrollno.setText(model.getValueAt(selectedIndex, 4).toString());
//        txtcourse.setText(model.getValueAt(selectedIndex, 5).toString());
    }//GEN-LAST:event_TableStnMouseClicked

    private void IdStnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdStnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdStnActionPerformed

    private void Note1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Note1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Note1ActionPerformed

    private void DateSoutenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSoutenanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DateSoutenanceActionPerformed

    private void IdSujetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdSujetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdSujetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ajouter;
    private javax.swing.JTextField DateSoutenance;
    private javax.swing.JTextArea Descriptio;
    private javax.swing.JLabel Filiere;
    private javax.swing.JLabel FullName;
    private javax.swing.JLabel IdStg;
    private javax.swing.JTextField IdStn;
    private javax.swing.JTextField IdSujet;
    private javax.swing.JButton Impr;
    private javax.swing.JButton Modifier;
    private javax.swing.JTextField Note1;
    private javax.swing.JTextField Note10;
    private javax.swing.JTextField Note2;
    private javax.swing.JTextField Note3;
    private javax.swing.JTextField Note4;
    private javax.swing.JTextField Note5;
    private javax.swing.JTextField Note6;
    private javax.swing.JTextField Note7;
    private javax.swing.JTextField Note8;
    private javax.swing.JTextField Note9;
    private javax.swing.JTextField Salle;
    private javax.swing.JTextField Sujet;
    private javax.swing.JButton Supp;
    private javax.swing.JTable TableStn;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
