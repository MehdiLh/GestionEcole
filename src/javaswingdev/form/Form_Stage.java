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
public class Form_Stage extends javax.swing.JPanel {

            private final ConnectDB dbConnection; // Declare a private field for ConnectDB
            private DefaultTableModel tableModel;

    /**
     * Creates new form Form_Stage
     */
    public Form_Stage() {
        initComponents();
        populateComboBox();
        dbConnection = new ConnectDB();
        dbConnection.connect();
        initializeTableModel();
        updateTableModel();
        addTableClickListener();
        showCompany();
    }
    
    // Table show
    private void initializeTableModel() {
        // Initialize the table model with columns from both tables
        tableModel = new DefaultTableModel();
        TableStage.setModel(tableModel);
        // Add columns from Stage table
        tableModel.addColumn("IdStage");
        tableModel.addColumn("DateDebut");
        tableModel.addColumn("DateFin");
        tableModel.addColumn("Acquis1");
        tableModel.addColumn("Acquis2");
        tableModel.addColumn("Acquis3");
        tableModel.addColumn("Acquis4");
        tableModel.addColumn("Acquis5");
        tableModel.addColumn("Acquis6");
        tableModel.addColumn("Acquis7");
        tableModel.addColumn("Acquis8");
        tableModel.addColumn("Acquis9");
        tableModel.addColumn("Acquis10");
        tableModel.addColumn("Acquis11");
        tableModel.addColumn("Acquis12");
        tableModel.addColumn("Acquis13");
        tableModel.addColumn("Acquis14");
        tableModel.addColumn("Acquis15");
        tableModel.addColumn("Acquis16");
        tableModel.addColumn("Acquis17");
        tableModel.addColumn("Acquis18");
        tableModel.addColumn("Acquis19");
        tableModel.addColumn("Acquis20");
        tableModel.addColumn("Acquis21");
    }

    private void updateTableModel() {
        tableModel.setRowCount(0); // Clear existing rows
        ConnectDB con = ConnectDB.getcon();
        con.connect();

        try {
            String sqlQuery = "SELECT IdStage, DateDebut, DateFin, Acquis1, Acquis2, Acquis3, Acquis4, Acquis5, Acquis6, Acquis7, Acquis8, Acquis9, Acquis10, Acquis11, Acquis12, Acquis13, Acquis14, Acquis15, Acquis16, Acquis17, Acquis18, Acquis19, Acquis20, Acquis21 FROM Stage";
            ResultSet rs = con.executeQuery(sqlQuery);

            while (rs.next()) {
                Object[] rowData = {
                    rs.getInt("IdStage"),
                    rs.getString("DateDebut"),
                    rs.getString("DateFin"),
                    rs.getString("Acquis1"),
                    rs.getString("Acquis2"),
                    rs.getString("Acquis3"),
                    rs.getString("Acquis4"),
                    rs.getString("Acquis5"),
                    rs.getString("Acquis6"),
                    rs.getString("Acquis7"),
                    rs.getString("Acquis8"),
                    rs.getString("Acquis9"),
                    rs.getString("Acquis10"),
                    rs.getString("Acquis11"),
                    rs.getString("Acquis12"),
                    rs.getString("Acquis13"),
                    rs.getString("Acquis14"),
                    rs.getString("Acquis15"),
                    rs.getString("Acquis16"),
                    rs.getString("Acquis17"),
                    rs.getString("Acquis18"),
                    rs.getString("Acquis19"),
                    rs.getString("Acquis20"),
                    rs.getString("Acquis21")
                };
                tableModel.addRow(rowData);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_Stage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addTableClickListener() {
        TableStage.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = TableStage.getSelectedRow();
                    if (selectedRow != -1) { // Ensure a row is selected
                        Object idStageObj = TableStage.getValueAt(selectedRow, 0);
                        Object dateDebutObj = TableStage.getValueAt(selectedRow, 1);
                        Object dateFinObj = TableStage.getValueAt(selectedRow, 2);

                        // Check for null values before converting to string
                        String idStage = idStageObj != null ? idStageObj.toString() : "";
                        String dateDebut = dateDebutObj != null ? dateDebutObj.toString() : "";
                        String dateFin = dateFinObj != null ? dateFinObj.toString() : "";

                        IdStage.setText(idStage);
                        DateDebut.setText(dateDebut);
                        DateFin.setText(dateFin);

                        // Call a method to display additional data based on idStage
                        displayAdditionalData(idStage);
                    }
                }
            }
        });
    }

    private void displayAdditionalData(String idStage) {
        ConnectDB con = ConnectDB.getcon();
        con.connect();

        try {
            // Fetch additional data from Stage table based on IdStage
            String sqlSujetQuery = "SELECT IdStage, DateDebut, DateFin, Acquis1, Acquis2, Acquis3, Acquis4, Acquis5, Acquis6, Acquis7, Acquis8, Acquis9, Acquis10, Acquis11, Acquis12, Acquis13, Acquis14, Acquis15, Acquis16, Acquis17, Acquis18, Acquis19, Acquis20, Acquis21, NomTuteur, FonctionTuteur, PostesStagiaire FROM Stage WHERE IdStage = ?";
            PreparedStatement sujetStatement = con.getConnection().prepareStatement(sqlSujetQuery);
            sujetStatement.setString(1, idStage);
            ResultSet sujetRs = sujetStatement.executeQuery();

            if (sujetRs.next()) {
                // Update UI with fetched additional data
                IdStage.setText(Integer.toString(sujetRs.getInt("IdStage")));
                DateDebut.setText(sujetRs.getString("DateDebut"));
                DateFin.setText(sujetRs.getString("DateFin"));
                Acquis1.setSelectedItem(sujetRs.getString("Acquis1"));
                Acquis2.setSelectedItem(sujetRs.getString("Acquis2"));
                Acquis3.setSelectedItem(sujetRs.getString("Acquis3"));
                Acquis4.setSelectedItem(sujetRs.getString("Acquis4"));
                Acquis5.setSelectedItem(sujetRs.getString("Acquis5"));
                Acquis6.setSelectedItem(sujetRs.getString("Acquis6"));
                Acquis7.setSelectedItem(sujetRs.getString("Acquis7"));
                Acquis8.setSelectedItem(sujetRs.getString("Acquis8"));
                Acquis9.setSelectedItem(sujetRs.getString("Acquis9"));
                Acquis10.setSelectedItem(sujetRs.getString("Acquis10"));
                Acquis11.setSelectedItem(sujetRs.getString("Acquis11"));
                Acquis12.setSelectedItem(sujetRs.getString("Acquis12"));
                Acquis13.setSelectedItem(sujetRs.getString("Acquis13"));
                Acquis14.setSelectedItem(sujetRs.getString("Acquis14"));
                Acquis15.setSelectedItem(sujetRs.getString("Acquis15"));
                Acquis16.setSelectedItem(sujetRs.getString("Acquis16"));
                Acquis17.setSelectedItem(sujetRs.getString("Acquis17"));
                Acquis18.setSelectedItem(sujetRs.getString("Acquis18"));
                Acquis19.setSelectedItem(sujetRs.getString("Acquis19"));
                Acquis20.setSelectedItem(sujetRs.getString("Acquis20"));
                Acquis21.setSelectedItem(sujetRs.getString("Acquis21"));
                NomTuteur.setText(sujetRs.getString("NomTuteur"));
                FonctionTuteur.setText(sujetRs.getString("FonctionTuteur"));
                PostesStagiaire.setText(sujetRs.getString("PostesStagiaire"));
            }
                   String sqlStagiaireQuery = "SELECT IdStg, NomStg, PrenomStg, Filiere FROM Stagaire WHERE IdStg IN (SELECT IdStg FROM Stage WHERE IdStage = ?)";
            PreparedStatement stagiaireStatement = con.getConnection().prepareStatement(sqlStagiaireQuery);
            stagiaireStatement.setString(1, idStage);
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
                    stagiaireRs.close();
        stagiaireStatement.close();

            sujetRs.close();
            sujetStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_Stage.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    // ComboBox Company
     public void showCompany(){
         ConnectDB con = ConnectDB.getcon();
       con.connect();
        try {
            
            String query = "SELECT IdCmp, NomCmp FROM Company";
            ResultSet result = con.executeQuery(query);
            
            while(result.next()){
                String company = result.getString("IdCmp");
                String Nomcompany = result.getString("NomCmp");
                String sss = company + " - " + Nomcompany;
                jComboBox1.addItem(sss );
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
   
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Supp = new javax.swing.JButton();
        Impr = new javax.swing.JButton();
        Ajouter = new javax.swing.JButton();
        Modifier = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableStage = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        Filiere = new javax.swing.JLabel();
        FullName = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        DateDebut = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        DateFin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Acquis2 = new javax.swing.JComboBox<>();
        Acquis3 = new javax.swing.JComboBox<>();
        Acquis4 = new javax.swing.JComboBox<>();
        Acquis5 = new javax.swing.JComboBox<>();
        Acquis6 = new javax.swing.JComboBox<>();
        Acquis1 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        Acquis7 = new javax.swing.JComboBox<>();
        Acquis8 = new javax.swing.JComboBox<>();
        Acquis9 = new javax.swing.JComboBox<>();
        Acquis10 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        Acquis11 = new javax.swing.JComboBox<>();
        Acquis12 = new javax.swing.JComboBox<>();
        Acquis13 = new javax.swing.JComboBox<>();
        Acquis14 = new javax.swing.JComboBox<>();
        Acquis15 = new javax.swing.JComboBox<>();
        Acquis16 = new javax.swing.JComboBox<>();
        Acquis17 = new javax.swing.JComboBox<>();
        Acquis18 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        Acquis21 = new javax.swing.JComboBox<>();
        Acquis19 = new javax.swing.JComboBox<>();
        Acquis20 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        IdStage = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        IdStg = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        NomTuteur = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        FonctionTuteur = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        PostesStagiaire = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setToolTipText("");
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe Print", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 204));
        jLabel10.setText("Evaluation Des Acquis");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 102, 255));
        jLabel1.setText("STAGE EN ENTRPRISE");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Groupe IPIRNET");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, 30));

        Supp.setBackground(new java.awt.Color(255, 0, 51));
        Supp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Supp.setText("Supprimer");
        Supp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuppActionPerformed(evt);
            }
        });
        add(Supp, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 630, 140, 50));

        Impr.setBackground(new java.awt.Color(0, 204, 255));
        Impr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Impr.setText("Imprimer");
        Impr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprActionPerformed(evt);
            }
        });
        add(Impr, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 630, 140, 50));

        Ajouter.setBackground(new java.awt.Color(51, 255, 0));
        Ajouter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Ajouter.setText("Insérer");
        Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterActionPerformed(evt);
            }
        });
        add(Ajouter, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 140, 50));

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
        add(Modifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 560, 140, 50));

        TableStage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableStage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableStageMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableStage);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 570, 780, 120));

        jLabel31.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 0, 204));
        jLabel31.setText("Donnee Acquis");
        add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 540, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Nom Prenom:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 100, 20));

        jLabel29.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 204));
        jLabel29.setText("Company");
        add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 30));
        add(comboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 290, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Filiere:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 70, 60, 20));

        Filiere.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(Filiere, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 110, 20));

        FullName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(FullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 210, 20));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Date de Debut:");
        add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, -1, 20));

        DateDebut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateDebutActionPerformed(evt);
            }
        });
        add(DateDebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 170, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Date de fin:");
        add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, -1, 20));

        DateFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateFinActionPerformed(evt);
            }
        });
        add(DateFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, 170, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Exactitude et assiduité ");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 170, 20));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Connaissances technique de base ");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 250, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Efficacité dans le travail ");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Comportement au travail ");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 190, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Intérêt porté au travail ");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, 20));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Rendement dans le travail ");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 200, 20));

        Acquis2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, -1, -1));

        Acquis3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, -1, -1));

        Acquis4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, -1, -1));

        Acquis5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, -1, -1));

        Acquis6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, -1, -1));

        Acquis1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Tenue de travail ");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 130, 20));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Propreté et hygiène ");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 160, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Aptitude à comprendre les instructions ");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, 20));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Esprit d'équipe ");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 250, 20));

        Acquis7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 410, -1, -1));

        Acquis8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, -1, -1));

        Acquis9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 470, -1, -1));

        Acquis10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 500, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Mobilisation des acquis de la formation ");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 330, 300, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Vivacité, rapidité d'exécution, respectes délais ");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, -1, 20));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Rigueur méthodologique et réalisme ");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 280, 20));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Autonomie anticipation des difficultés ");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, -1, 20));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Capacités de solliciter d'argumenter de clarifier ");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 350, 20));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Acquisition de savoir et de savoir- faire nouveaux ");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 360, 360, 20));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Régularité de l'effort et capacité progressé ");
        add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 390, 320, 20));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Soin et rigueur ");
        add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 420, -1, 20));

        Acquis11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis11, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 210, -1, -1));

        Acquis12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis12, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 240, -1, -1));

        Acquis13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis13, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 270, -1, -1));

        Acquis14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis14, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 300, -1, -1));

        Acquis15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis15, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 330, -1, -1));

        Acquis16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis16, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 360, -1, -1));

        Acquis17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis17, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 390, -1, -1));

        Acquis18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis18, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 420, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Adaptabilité ");
        add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 480, 300, 20));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Initiative et implication ");
        add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, 350, 20));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Volonté de remédier aux l'aucunes antérieures");
        add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 510, 360, 20));

        Acquis21.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis21, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 510, -1, -1));

        Acquis19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis19, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 450, -1, -1));

        Acquis20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TRES BIEN", "BIEN", "MOYEN", "FAIBLE", "INSUFFUSANT" }));
        add(Acquis20, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 480, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 1130, 340));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("ID :");
        add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, 20));

        IdStage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdStageActionPerformed(evt);
            }
        });
        add(IdStage, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 90, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 760, 40));

        jLabel37.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 0, 204));
        jLabel37.setText("Stagiaire");
        add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 30));

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 240, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Id Stagiaire:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 90, 20));

        IdStg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(IdStg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 70, 90, 20));

        jLabel38.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 0, 204));
        jLabel38.setText("Tuteur");
        add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, -1, 30));
        add(NomTuteur, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 180, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Nom :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, -1, 20));

        FonctionTuteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FonctionTuteurActionPerformed(evt);
            }
        });
        add(FonctionTuteur, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 180, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Fonction :");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, -1, 20));
        add(PostesStagiaire, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 110, 140, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Poste Stagiaire :");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 110, -1, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void SuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuppActionPerformed
        // TODO add your handling code here:
        
                int idCmp; // Change variable name from IdCmp to idCmp
        try {
            // Parse the selected idCmp from the comboBox
            idCmp = Integer.parseInt(jComboBox1.getSelectedItem().toString().split(" - ")[0]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erreur : Veuillez sélectionner un IdCompagnie valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if parsing fails
        }
        
                int idStg; // Change variable name from IdStg to idStg
        try {
            // Parse the selected IdStg from the combobox
            idStg = Integer.parseInt(comboBox.getSelectedItem().toString().split(" - ")[0]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erreur : Veuillez sélectionner un IdStagiaire valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if parsing fails
        }

        int idStage;
        try {
            idStage = Integer.parseInt(IdStage.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erreur : Veuillez entrer un entier pour IdStage.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if parsing fails
        }

        ConnectDB con = ConnectDB.getcon();
        try {
            con.connect();
            con.deleteStage(idStage, idStg, idCmp);
        } catch (SQLException ex) {
            Logger.getLogger(Form_Stage.class.getName()).log(Level.SEVERE, null, ex);
        }
         updateTableModel();
    }//GEN-LAST:event_SuppActionPerformed

    private void ImprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprActionPerformed
    int[] selectedRows = TableStage.getSelectedRows();

    try {
        // Load JasperReport templates
        JasperDesign jasdi1 = JRXmlLoader.load("C:\\Users\\PC\\JaspersoftWorkspace\\MyReports\\stage1.jrxml");
        JasperDesign jasdi2 = JRXmlLoader.load("C:\\Users\\PC\\JaspersoftWorkspace\\MyReports\\stage2.jrxml");
        JasperDesign jasdi3 = JRXmlLoader.load("C:\\Users\\PC\\JaspersoftWorkspace\\MyReports\\stage3.jrxml");
        JasperDesign jasdi4 = JRXmlLoader.load("C:\\Users\\PC\\JaspersoftWorkspace\\MyReports\\stage4.jrxml");
        JasperDesign jasdi5 = JRXmlLoader.load("C:\\Users\\PC\\JaspersoftWorkspace\\MyReports\\stage5.jrxml");

        // Construct the SQL query dynamically based on the selected rows
        StringBuilder sqlBuilder = new StringBuilder("SELECT dbo.Company.NomCmp, dbo.Company.AdresseCmp, dbo.Company.Domaine, dbo.Company.StatutJrd, dbo.Company.NomResponsable, dbo.Company.NombreS, dbo.Company.FonctionResponsable, dbo.Stagaire.IdStg, \n" +
                "dbo.Stagaire.NomStg, dbo.Stagaire.PrenomStg, dbo.Stagaire.Filiere, dbo.Stage.acquis1, dbo.Stage.acquis2, dbo.Stage.acquis4, dbo.Stage.acquis3, dbo.Stage.acquis5, dbo.Stage.acquis6, dbo.Stage.acquis7, dbo.Stage.acquis8, \n" +
                "dbo.Stage.acquis9, dbo.Stage.acquis10, dbo.Stage.acquis11, dbo.Stage.acquis12, dbo.Stage.acquis13, dbo.Stage.acquis14, dbo.Stage.acquis15, dbo.Stage.acquis16, dbo.Stage.acquis17, dbo.Stage.acquis18, dbo.Stage.acquis19, \n" +
                "dbo.Stage.acquis20, dbo.Stage.acquis21, dbo.Stage.NomTuteur, dbo.Stage.FonctionTuteur, dbo.Stage.PostesStagiaire, dbo.Stage.DateDebut, dbo.Stage.DateFin\n" +
                "FROM dbo.Company INNER JOIN\n" +
                "dbo.Stage ON dbo.Company.IdCmp = dbo.Stage.IdCmp INNER JOIN\n" +
                "dbo.Stagaire ON dbo.Stage.IdStg = dbo.Stagaire.IdStg");

        if (selectedRows.length > 0) {
            sqlBuilder.append(" WHERE dbo.Stagaire.IdStg IN (");
            for (int i = 0; i < selectedRows.length; i++) {
                int row = selectedRows[i];
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

        jasdi1.setQuery(newQuery);
        jasdi2.setQuery(newQuery);
        jasdi3.setQuery(newQuery);

        // Compile the JasperReport templates
        JasperReport js1 = JasperCompileManager.compileReport(jasdi1);
        JasperReport js2 = JasperCompileManager.compileReport(jasdi2);
        JasperReport js3 = JasperCompileManager.compileReport(jasdi3);
        JasperReport js4 = JasperCompileManager.compileReport(jasdi4);
        JasperReport js5 = JasperCompileManager.compileReport(jasdi5);
        
        // Provide a database connection
        ConnectDB con = ConnectDB.getcon();

        // Fill the JasperReports with data
        JasperPrint jd1 = JasperFillManager.fillReport(js1, null, dbConnection.con);
        JasperPrint jd2 = JasperFillManager.fillReport(js2, null, dbConnection.con);
        JasperPrint jd3 = JasperFillManager.fillReport(js3, null, dbConnection.con);
        JasperPrint jd4 = JasperFillManager.fillReport(js4, null, dbConnection.con);
        JasperPrint jd5 = JasperFillManager.fillReport(js5, null, dbConnection.con);
        
        // Show the reports in JasperViewer
        JasperViewer.viewReport(jd1, false);
        JasperViewer.viewReport(jd2, false);
        JasperViewer.viewReport(jd3, false);
        JasperViewer.viewReport(jd4, false);
        JasperViewer.viewReport(jd5, false);
        
    } catch (JRException ex) {
        Logger.getLogger(Form_Stn.class.getName()).log(Level.SEVERE, null, ex);
    }

    }//GEN-LAST:event_ImprActionPerformed

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterActionPerformed
        StageBean sg = new StageBean();

           int idCmp; // Change variable name from IdCmp to idCmp
           try {
               // Parse the selected IdCmp from the combobox
               idCmp = Integer.parseInt(jComboBox1.getSelectedItem().toString().split(" - ")[0]);
           } catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Erreur : Veuillez sélectionner un IdCompagnie valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
               return; // Exit the method if parsing fails
           }

           int idStg; // Change variable name from IdStg to idStg
           try {
               // Parse the selected IdStg from the combobox
               idStg = Integer.parseInt(comboBox.getSelectedItem().toString().split(" - ")[0]);
           } catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Erreur : Veuillez sélectionner un IdStagiaire valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
               return; // Exit the method if parsing fails
           }

           int idStage;
           try {
               idStage = Integer.parseInt(IdStage.getText());
           } catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Erreur : Veuillez entrer un entier pour IdStage.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
               return; // Exit the method if parsing fails
           }
           sg.setIdStage(idStage);
           sg.setDateDebut(DateDebut.getText());
           sg.setDateFin(DateFin.getText());
           sg.setAcquis1(Acquis1.getSelectedItem().toString());
           sg.setAcquis2(Acquis2.getSelectedItem().toString());
           sg.setAcquis3(Acquis3.getSelectedItem().toString());
           sg.setAcquis4(Acquis4.getSelectedItem().toString());
           sg.setAcquis5(Acquis5.getSelectedItem().toString());
           sg.setAcquis6(Acquis6.getSelectedItem().toString());
           sg.setAcquis7(Acquis7.getSelectedItem().toString());
           sg.setAcquis8(Acquis8.getSelectedItem().toString());
           sg.setAcquis9(Acquis9.getSelectedItem().toString());
           sg.setAcquis10(Acquis10.getSelectedItem().toString());
           sg.setAcquis11(Acquis11.getSelectedItem().toString());
           sg.setAcquis12(Acquis12.getSelectedItem().toString());
           sg.setAcquis13(Acquis13.getSelectedItem().toString());
           sg.setAcquis14(Acquis14.getSelectedItem().toString());
           sg.setAcquis15(Acquis15.getSelectedItem().toString());
           sg.setAcquis16(Acquis16.getSelectedItem().toString());
           sg.setAcquis17(Acquis17.getSelectedItem().toString());
           sg.setAcquis18(Acquis18.getSelectedItem().toString());
           sg.setAcquis19(Acquis19.getSelectedItem().toString());
           sg.setAcquis20(Acquis20.getSelectedItem().toString());
           sg.setAcquis21(Acquis21.getSelectedItem().toString());
           sg.setNomTuteur(NomTuteur.getText()); // Set NomTuteur
           sg.setFonctionTuteur(FonctionTuteur.getText()); // Set FonctionTuteur
           sg.setPostesStagiaire(PostesStagiaire.getText());

           ConnectDB con = ConnectDB.getcon();
           try {
               con.connect();
               con.ajouterstage(sg, idStg, idCmp); // Add idCmp as an argument
           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du stage : " + ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
               Logger.getLogger(Form_Stage.class.getName()).log(Level.SEVERE, null, ex);
           }
            updateTableModel();
    }//GEN-LAST:event_AjouterActionPerformed

    private void ModifierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModifierMouseClicked

    }//GEN-LAST:event_ModifierMouseClicked

    private void ModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierActionPerformed
   StageBean sg = new StageBean();

    int idCmp; // Change variable name from IdCmp to idCmp
    try {
        // Parse the selected idCmp from the comboBox
        idCmp = Integer.parseInt(jComboBox1.getSelectedItem().toString().split(" - ")[0]);
        System.out.println("Selected idCmp: " + idCmp);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Erreur : Veuillez sélectionner un IdCompagnie valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        return; // Exit the method if parsing fails
    }

    int idStg; // Change variable name from IdStg to idStg
    try {
        // Parse the selected IdStg from the combobox
        idStg = Integer.parseInt(comboBox.getSelectedItem().toString().split(" - ")[0]);
        System.out.println("Selected idStg: " + idStg);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Erreur : Veuillez sélectionner un IdStagiaire valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        return; // Exit the method if parsing fails
    }

    int idStage;
    try {
        idStage = Integer.parseInt(IdStage.getText());
        System.out.println("Entered idStage: " + idStage);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Erreur : Veuillez entrer un entier pour IdStage.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        return; // Exit the method if parsing fails.");
    }
    sg.setIdStage(idStage);
    sg.setDateDebut(DateDebut.getText());
    sg.setDateFin(DateFin.getText());
    sg.setAcquis1(Acquis1.getSelectedItem().toString());
    sg.setAcquis2(Acquis2.getSelectedItem().toString());
    sg.setAcquis3(Acquis3.getSelectedItem().toString());
    sg.setAcquis4(Acquis4.getSelectedItem().toString());
    sg.setAcquis5(Acquis5.getSelectedItem().toString());
    sg.setAcquis6(Acquis6.getSelectedItem().toString());
    sg.setAcquis7(Acquis7.getSelectedItem().toString());
    sg.setAcquis8(Acquis8.getSelectedItem().toString());
    sg.setAcquis9(Acquis9.getSelectedItem().toString());
    sg.setAcquis10(Acquis10.getSelectedItem().toString());
    sg.setAcquis11(Acquis11.getSelectedItem().toString());
    sg.setAcquis12(Acquis12.getSelectedItem().toString());
    sg.setAcquis13(Acquis13.getSelectedItem().toString());
    sg.setAcquis14(Acquis14.getSelectedItem().toString());
    sg.setAcquis15(Acquis15.getSelectedItem().toString());
    sg.setAcquis16(Acquis16.getSelectedItem().toString());
    sg.setAcquis17(Acquis17.getSelectedItem().toString());
    sg.setAcquis18(Acquis18.getSelectedItem().toString());
    sg.setAcquis19(Acquis19.getSelectedItem().toString());
    sg.setAcquis20(Acquis20.getSelectedItem().toString());
    sg.setAcquis21(Acquis21.getSelectedItem().toString());
    sg.setNomTuteur(NomTuteur.getText()); // Set NomTuteur
    sg.setFonctionTuteur(FonctionTuteur.getText()); // Set FonctionTuteur
    sg.setPostesStagiaire(PostesStagiaire.getText());

    ConnectDB con = ConnectDB.getcon();
    try {
        con.connect();
        con.modifierStage(sg, idStg, idCmp); // Pass idCmp as an argument
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erreur lors de la modification du stage : " + ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
        Logger.getLogger(Form_Stage.class.getName()).log(Level.SEVERE, null, ex);
    }
    updateTableModel();
    }//GEN-LAST:event_ModifierActionPerformed

    private void TableStageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableStageMouseClicked

    }//GEN-LAST:event_TableStageMouseClicked

    private void DateDebutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateDebutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DateDebutActionPerformed

    private void DateFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateFinActionPerformed

    }//GEN-LAST:event_DateFinActionPerformed

    private void IdStageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdStageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdStageActionPerformed

    private void FonctionTuteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FonctionTuteurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FonctionTuteurActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Acquis1;
    private javax.swing.JComboBox<String> Acquis10;
    private javax.swing.JComboBox<String> Acquis11;
    private javax.swing.JComboBox<String> Acquis12;
    private javax.swing.JComboBox<String> Acquis13;
    private javax.swing.JComboBox<String> Acquis14;
    private javax.swing.JComboBox<String> Acquis15;
    private javax.swing.JComboBox<String> Acquis16;
    private javax.swing.JComboBox<String> Acquis17;
    private javax.swing.JComboBox<String> Acquis18;
    private javax.swing.JComboBox<String> Acquis19;
    private javax.swing.JComboBox<String> Acquis2;
    private javax.swing.JComboBox<String> Acquis20;
    private javax.swing.JComboBox<String> Acquis21;
    private javax.swing.JComboBox<String> Acquis3;
    private javax.swing.JComboBox<String> Acquis4;
    private javax.swing.JComboBox<String> Acquis5;
    private javax.swing.JComboBox<String> Acquis6;
    private javax.swing.JComboBox<String> Acquis7;
    private javax.swing.JComboBox<String> Acquis8;
    private javax.swing.JComboBox<String> Acquis9;
    private javax.swing.JButton Ajouter;
    private javax.swing.JTextField DateDebut;
    private javax.swing.JTextField DateFin;
    private javax.swing.JLabel Filiere;
    private javax.swing.JTextField FonctionTuteur;
    private javax.swing.JLabel FullName;
    private javax.swing.JTextField IdStage;
    private javax.swing.JLabel IdStg;
    private javax.swing.JButton Impr;
    private javax.swing.JButton Modifier;
    private javax.swing.JTextField NomTuteur;
    private javax.swing.JTextField PostesStagiaire;
    private javax.swing.JButton Supp;
    private javax.swing.JTable TableStage;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
