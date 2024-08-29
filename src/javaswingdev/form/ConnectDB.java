package javaswingdev.form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author lahlou
 */
public class ConnectDB {
    
        Connection con;
        PreparedStatement pst;
        Statement st;
        ResultSet rs;
    

    static ConnectDB getcon() {
        return new ConnectDB();
        
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   public void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dburl = "jdbc:sqlserver://DESKTOP-BCJNDLM:1433;databaseName=DataBase;encrypt=true;trustServerCertificate=true;";
            con = DriverManager.getConnection(dburl, "sa", "admin"); 
            st = con.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "SQL Server JDBC Driver not found", ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "SQL Exception while connecting to the database", ex);
        }
    }
   
   public ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }
    
   
    /*
     * 
     *  Form_Stagiaire
     *
    */
    
   //Ajouter Stagiaire
    public void ajouterstg(StagiaireBean cb) throws SQLException {
        int a = 0;
        try {
            String query = "INSERT INTO Stagaire (IdStg, NomStg, PrenomStg, Filiere, Promotion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, cb.getIdStg());
            pst.setString(2, cb.getNomStg());
            pst.setString(3, cb.getPrenomStg());    
            pst.setString(4, cb.getFiliere());
            pst.setString(5, cb.getPromotion());  
            
            a=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
        }
        if (a==1){
            JOptionPane.showMessageDialog(null, "Bien ajouter");
        }
    }
    
     //Modifier Stagiaire
    public void modifierstg(StagiaireBean cb) throws SQLException {
        int a = 0;
        try {
            if (con == null || con.isClosed()) {
                connect(); // Reconnect if the connection is null or closed
            }
            String query = "UPDATE Stagaire SET NomStg = ?, PrenomStg = ?, Filiere = ?, Promotion = ? WHERE IdStg = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, cb.getNomStg());
            pst.setString(2, cb.getPrenomStg());
            pst.setString(3, cb.getFiliere());
            pst.setString(4, cb.getPromotion());
            pst.setInt(5, cb.getIdStg()); // Assuming IdStg is an integer
            
            a = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
        }
        if (a == 1) {
            JOptionPane.showMessageDialog(null, "Stagiaire modifié avec succès !");
        } else {
            JOptionPane.showMessageDialog(null, "Aucune modification effectuée.");
        }
    }
    
    //Supprimer (Delete) Stagiaire
    public void deleteStagiaire(String idStg) throws SQLException {
    connect(); // Ensure connection is established
    String query = "DELETE FROM Stagaire WHERE IdStg = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, idStg);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Record deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No records deleted");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting record: " + ex.getMessage());
        }
    }
        /*
     * 
     *  Form_Company
     *
    */
       //Ajouter Company
    public void ajoutercmp(CompanyBean cb) throws SQLException {
        int a = 0;
        try {
            String query = "INSERT INTO Company (IdCmp, NomCmp, AdresseCmp, Domaine, StatutJrd, NomResponsable, FonctionResponsable, NombreS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, cb.getIdCmp());
            pst.setString(2, cb.getNomCmp());
            pst.setString(3, cb.getAdresseCmp());    
            pst.setString(4, cb.getDomaine());
            pst.setString(5, cb.getStatutJrd());   
            pst.setString(6, cb.getNomResponsable()); 
            pst.setString(7, cb.getFonctionResponsable());
            pst.setString(8, cb.getNombreS());
            
            a=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
        }
        if (a==1){
            JOptionPane.showMessageDialog(null, "Bien ajouter");
        }
    }
    
        //Modifier Company
    public void modifiercmp(CompanyBean cb) throws SQLException {
        int a = 0;
        try {
            if (con == null || con.isClosed()) {
                connect(); // Reconnect if the connection is null or closed
            }
            String query = "UPDATE Company SET NomCmp = ?, AdresseCmp = ?, Domaine = ?, StatutJrd = ?, NomResponsable = ?, FonctionResponsable = ?, NombreS = ? WHERE IdCmp = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, cb.getNomCmp());
            pst.setString(2, cb.getAdresseCmp());    
            pst.setString(3, cb.getDomaine());
            pst.setString(4, cb.getStatutJrd());   
            pst.setString(5, cb.getNomResponsable()); 
            pst.setString(6, cb.getFonctionResponsable());
            pst.setString(7, cb.getNombreS());
            pst.setInt(8, cb.getIdCmp()); // Assuming IdStg is an integer
            
            a = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
        }
        if (a == 1) {
            JOptionPane.showMessageDialog(null, "Stagiaire modifié avec succès !");
        } else {
            JOptionPane.showMessageDialog(null, "Aucune modification effectuée.");
        }
    }
    
    //Supprimer (Delete) Stagiaire
    public void deletecmp(String idcmp) throws SQLException {
    connect(); // Ensure connection is established
    String query = "DELETE FROM Company WHERE IdCmp = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, idcmp);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Record deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No records deleted");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting record: " + ex.getMessage());
        }
    }
    /*
     * 
     *  Form_Formateur
     *
    */
        //Ajouter Formateur
    public void ajouterfrt(FormateurBean cb) throws SQLException {
        int a = 0;
        try {
            String query = "INSERT INTO Formateur (IdFrt, NomFrt, PrenomFrt) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, cb.getIdFrt());
            pst.setString(2, cb.getNomFrt());
            pst.setString(3, cb.getPrenomFrt());
            a=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
        }
        if (a==1){
            JOptionPane.showMessageDialog(null, "Bien ajouter");
        }
        
    }
    
        //Modifier Formateur
    public void modifierfrt(FormateurBean cb) throws SQLException {
        int a = 0;
        try {
            if (con == null || con.isClosed()) {
                connect(); // Reconnect if the connection is null or closed
            }
            String query = "UPDATE Formateur SET NomFrt = ?, PrenomFrt = ? WHERE IdFrt = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, cb.getNomFrt());
            pst.setString(2, cb.getPrenomFrt());
            pst.setInt(3, cb.getIdFrt()); // Assuming IdStg is an integer
            
            a = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
        }
        if (a == 1) {
            JOptionPane.showMessageDialog(null, "Stagiaire modifié avec succès !");
        } else {
            JOptionPane.showMessageDialog(null, "Aucune modification effectuée.");
        }
    }
    
        //Supprimer (Delete) Stagiaire
    public void deleteFormateur(String idFrt) throws SQLException {
        connect(); // Ensure connection is established
        String query = "DELETE FROM Formateur WHERE IdFrt = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, idFrt);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Record deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "No records deleted");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error deleting record: " + ex.getMessage());
            }
    }
    
    
    /*
     * 
     *  Form_Soutenance
     *
    */
    
    //Ajouter

public void ajouterSujet(StnBean stn, int idStg) throws SQLException {
 try {
        // Turn off AutoCommit
        con.setAutoCommit(false);

        // Parse the date to ensure it is in the correct format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = sdf.parse(stn.getDateSoutenance());
        String formattedDate = sdf.format(parsedDate);

        // Insert data into Soutenance table
        String soutenanceQuery = "INSERT INTO Soutenance (IdStn, DateSoutenance, Salle, IdStg, Note1, Note2, Note3, Note4, Note5, Note6, Note7, Note8, Note9, Note10) " +
                                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement soutenanceStatement = con.prepareStatement(soutenanceQuery);
        soutenanceStatement.setInt(1, stn.getIdStn());
        soutenanceStatement.setString(2, formattedDate);
        soutenanceStatement.setString(3, stn.getSalle());
        soutenanceStatement.setInt(4, idStg);
        soutenanceStatement.setDouble(5, stn.getNote1());
        soutenanceStatement.setDouble(6, stn.getNote2());
        soutenanceStatement.setDouble(7, stn.getNote3());
        soutenanceStatement.setDouble(8, stn.getNote4());
        soutenanceStatement.setDouble(9, stn.getNote5());
        soutenanceStatement.setDouble(10, stn.getNote6());
        soutenanceStatement.setDouble(11, stn.getNote7());
        soutenanceStatement.setDouble(12, stn.getNote8());
        soutenanceStatement.setDouble(13, stn.getNote9());
        soutenanceStatement.setDouble(14, stn.getNote10());

        int soutenanceRowsAffected = soutenanceStatement.executeUpdate();

        if (soutenanceRowsAffected == 1) {
            // Insert data into Sujet_these table
            String sujetQuery = "INSERT INTO Sujet_these (IdSujet, Descriptio, Sujet, IdStn) VALUES (?, ?, ?, ?)";
            PreparedStatement sujetStatement = con.prepareStatement(sujetQuery);
            sujetStatement.setInt(1, stn.getIdSujet());
            sujetStatement.setString(2, stn.getDescriptio());
            sujetStatement.setString(3, stn.getSujet());
            sujetStatement.setInt(4, stn.getIdStn());

            int sujetRowsAffected = sujetStatement.executeUpdate();

            if (sujetRowsAffected == 1) {
                // Commit the transaction
                con.commit();
                JOptionPane.showMessageDialog(null, "Sujet bien ajouté avec Stn.");
            } else {
                // Rollback the transaction
                con.rollback();
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de Sujet_these.");
            }
        } else {
            // Rollback the transaction
            con.rollback();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de Soutenance.");
        }
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(null, "Erreur : Veuillez entrer une date valide au format yyyy-MM-dd.");
    } catch (SQLException ex) {
        // Rollback in case of exception
        con.rollback();
        Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
        JOptionPane.showMessageDialog(null, "Erreur lors de l'exécution de la requête SQL.");
    } finally {
        // Turn AutoCommit back on
        con.setAutoCommit(true);
    }
}


    
    //Modifier
    public void modifierSujet(StnBean stn) throws SQLException {
    try {
        String updateQuery = "UPDATE Soutenance SET DateSoutenance = ?, Salle = ?, Note1 = ?, Note2 = ?, Note3 = ?, Note4 = ?, " +
                             "Note5 = ?, Note6 = ?, Note7 = ?, Note8 = ?, Note9 = ?, Note10 = ? WHERE IdStn = ?";
        PreparedStatement updateStatement = con.prepareStatement(updateQuery);
        updateStatement.setString(1, stn.getDateSoutenance());
        updateStatement.setString(2, stn.getSalle());
        updateStatement.setDouble(3, stn.getNote1());
        updateStatement.setDouble(4, stn.getNote2());
        updateStatement.setDouble(5, stn.getNote3());
        updateStatement.setDouble(6, stn.getNote4());
        updateStatement.setDouble(7, stn.getNote5());
        updateStatement.setDouble(8, stn.getNote6());
        updateStatement.setDouble(9, stn.getNote7());
        updateStatement.setDouble(10, stn.getNote8());
        updateStatement.setDouble(11, stn.getNote9());
        updateStatement.setDouble(12, stn.getNote10());
        updateStatement.setInt(13, stn.getIdStn());

        int rowsAffected = updateStatement.executeUpdate();
        if (rowsAffected == 1) {
            String sujetUpdateQuery = "UPDATE Sujet_these SET Descriptio = ?, Sujet = ? WHERE IdStn = ?";
            PreparedStatement sujetUpdateStatement = con.prepareStatement(sujetUpdateQuery);
            sujetUpdateStatement.setString(1, stn.getDescriptio());
            sujetUpdateStatement.setString(2, stn.getSujet());
            sujetUpdateStatement.setInt(3, stn.getIdStn());

            int sujetRowsAffected = sujetUpdateStatement.executeUpdate();
            if (sujetRowsAffected == 1) {
                JOptionPane.showMessageDialog(null, "Données de la soutenance et du sujet modifiées avec succès.");
            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de la modification des données du sujet.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Erreur lors de la modification des données de la soutenance.");
        }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
            JOptionPane.showMessageDialog(null, "Erreur lors de l'exécution de la requête SQL.");
        }
    }

    //Supprimer (Delete)
    public void supprimerSujet(int idStnToDelete) throws SQLException {
        try {
            // Delete related records from Sujet_these table first
            String deleteSujetQuery = "DELETE FROM Sujet_these WHERE IdStn = ?";
            PreparedStatement deleteSujetStatement = con.prepareStatement(deleteSujetQuery);
            deleteSujetStatement.setInt(1, idStnToDelete);
            deleteSujetStatement.executeUpdate();

            // Now delete the record from Soutenance table
            String deleteQuery = "DELETE FROM Soutenance WHERE IdStn = ?";
            PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, idStnToDelete);
            deleteStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Enregistrement supprimé avec succès.");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de l'enregistrement.");
        }
    }
    
    
       
    /*
     * 
     *  Form_Stage
     *
    */
    
   //Ajouter Stage
    public void ajouterstage(StageBean sg, int idStg, int idCmp) throws SQLException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    try {
        // Turn off AutoCommit
        con.setAutoCommit(false);

        // Parse and validate the dates
        java.util.Date parsedDateDebut = dateFormat.parse(sg.getDateDebut());
        java.util.Date parsedDateFin = dateFormat.parse(sg.getDateFin());
        String formattedDateDebut = dateFormat.format(parsedDateDebut);
        String formattedDateFin = dateFormat.format(parsedDateFin);

        // Insert data into Stage table
        String query = "INSERT INTO Stage (IdStage, DateDebut, DateFin, IdStg, idCmp, acquis1, acquis2, acquis3, acquis4, acquis5, acquis6, acquis7, acquis8, acquis9, acquis10, acquis11, acquis12, acquis13, acquis14, acquis15, acquis16, acquis17, acquis18, acquis19, acquis20, acquis21, NomTuteur, FonctionTuteur, PostesStagiaire) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, sg.getIdStage());
        pst.setString(2, formattedDateDebut);
        pst.setString(3, formattedDateFin);
        pst.setInt(4, idStg);
        pst.setInt(5, idCmp);
        pst.setString(6, sg.getAcquis1());
        pst.setString(7, sg.getAcquis2());
        pst.setString(8, sg.getAcquis3());
        pst.setString(9, sg.getAcquis4());
        pst.setString(10, sg.getAcquis5());
        pst.setString(11, sg.getAcquis6());
        pst.setString(12, sg.getAcquis7());
        pst.setString(13, sg.getAcquis8());
        pst.setString(14, sg.getAcquis9());
        pst.setString(15, sg.getAcquis10());
        pst.setString(16, sg.getAcquis11());
        pst.setString(17, sg.getAcquis12());
        pst.setString(18, sg.getAcquis13());
        pst.setString(19, sg.getAcquis14());
        pst.setString(20, sg.getAcquis15());
        pst.setString(21, sg.getAcquis16());
        pst.setString(22, sg.getAcquis17());
        pst.setString(23, sg.getAcquis18());
        pst.setString(24, sg.getAcquis19());
        pst.setString(25, sg.getAcquis20());
        pst.setString(26, sg.getAcquis21());
        pst.setString(27, sg.getNomTuteur());
        pst.setString(28, sg.getFonctionTuteur());
        pst.setString(29, sg.getPostesStagiaire());

        int stageRowsAffected = pst.executeUpdate();

        if (stageRowsAffected == 1) {
            // Commit the transaction
            con.commit();
            JOptionPane.showMessageDialog(null, "Stage bien ajouté.");
        } else {
            // Rollback the transaction
            con.rollback();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du Stage.");
        }
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(null, "Erreur : Veuillez entrer une date valide au format yyyy-MM-dd.");
    } catch (SQLException ex) {
        // Rollback in case of exception
        con.rollback();
        Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
        JOptionPane.showMessageDialog(null, "Erreur lors de l'exécution de la requête SQL.");
    } finally {
        // Turn AutoCommit back on
        con.setAutoCommit(true);
    }
}



public void modifierStage(StageBean sg, int idStg, int idCmp) throws SQLException {
    String query = "UPDATE Stage SET DateDebut = ?, DateFin = ?, acquis1 = ?, acquis2 = ?, acquis3 = ?, acquis4 = ?, acquis5 = ?, acquis6 = ?, acquis7 = ?, acquis8 = ?, acquis9 = ?, acquis10 = ?, acquis11 = ?, acquis12 = ?, acquis13 = ?, acquis14 = ?, acquis15 = ?, acquis16 = ?, acquis17 = ?, acquis18 = ?, acquis19 = ?, acquis20 = ?, acquis21 = ?, NomTuteur = ?, FonctionTuteur = ?, PostesStagiaire = ? WHERE IdStage = ?";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust the format to match the input

    try (PreparedStatement pst = con.prepareStatement(query)) {
        // Convert date strings to java.sql.Date
        Date dateDebut = new Date(dateFormat.parse(sg.getDateDebut()).getTime());
        Date dateFin = new Date(dateFormat.parse(sg.getDateFin()).getTime());

        pst.setDate(1, dateDebut);
        pst.setDate(2, dateFin);
        pst.setString(3, sg.getAcquis1());
        pst.setString(4, sg.getAcquis2());
        pst.setString(5, sg.getAcquis3());
        pst.setString(6, sg.getAcquis4());
        pst.setString(7, sg.getAcquis5());
        pst.setString(8, sg.getAcquis6());
        pst.setString(9, sg.getAcquis7());
        pst.setString(10, sg.getAcquis8());
        pst.setString(11, sg.getAcquis9());
        pst.setString(12, sg.getAcquis10());
        pst.setString(13, sg.getAcquis11());
        pst.setString(14, sg.getAcquis12());
        pst.setString(15, sg.getAcquis13());
        pst.setString(16, sg.getAcquis14());
        pst.setString(17, sg.getAcquis15());
        pst.setString(18, sg.getAcquis16());
        pst.setString(19, sg.getAcquis17());
        pst.setString(20, sg.getAcquis18());
        pst.setString(21, sg.getAcquis19());
        pst.setString(22, sg.getAcquis20());
        pst.setString(23, sg.getAcquis21());
        pst.setString(24, sg.getNomTuteur());
        pst.setString(25, sg.getFonctionTuteur());
        pst.setString(26, sg.getPostesStagiaire());

        pst.setInt(27, sg.getIdStage());

        int result = pst.executeUpdate();
        if (result == 1) {
            JOptionPane.showMessageDialog(null, "Mise à jour réussie");
        } 
//        else {
//            JOptionPane.showMessageDialog(null, "Erreur : la mise à jour a échoué.");
//        }
    } catch (SQLException ex) {
        Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
        throw ex;  // Rethrow the exception to ensure it is handled by the calling method
    } catch (ParseException ex) {
        Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while parsing date", ex);
        JOptionPane.showMessageDialog(null, "Erreur : Format de date invalide.");
    }
}


    
    //Modifier Stage
//    public void modifierStage(StageBean sg, int idStg, int idCmp) throws SQLException {
//        String query = "UPDATE Stage SET DateDebut = ?, DateFin = ?, acquis1 = ?, acquis2 = ?, acquis3 = ?, acquis4 = ?, acquis5 = ?, acquis6 = ?, acquis7 = ?, acquis8 = ?, acquis9 = ?, acquis10 = ?, acquis11 = ?, acquis12 = ?, acquis13 = ?, acquis14 = ?, acquis15 = ?, acquis16 = ?, acquis17 = ?, acquis18 = ?, acquis19 = ?, acquis20 = ?, acquis21 = ?, NomTuteur = ?, FonctionTuteur = ?, PostesStagiaire = ? WHERE IdStage = ? AND IdStg = ? AND idCmp = ?";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy"); // Adjust the format to match the input
//        try (PreparedStatement pst = con.prepareStatement(query)) {
//            // Convert date strings to java.sql.Date
//            Date dateDebut = new Date(dateFormat.parse(sg.getDateDebut()).getTime());
//            Date dateFin = new Date(dateFormat.parse(sg.getDateFin()).getTime());
//
//            pst.setDate(1, dateDebut);
//            pst.setDate(2, dateFin);
//            pst.setString(3, sg.getAcquis1());
//            pst.setString(4, sg.getAcquis2());
//            pst.setString(5, sg.getAcquis3());
//            pst.setString(6, sg.getAcquis4());
//            pst.setString(7, sg.getAcquis5());
//            pst.setString(8, sg.getAcquis6());
//            pst.setString(9, sg.getAcquis7());
//            pst.setString(10, sg.getAcquis8());
//            pst.setString(11, sg.getAcquis9());
//            pst.setString(12, sg.getAcquis10());
//            pst.setString(13, sg.getAcquis11());
//            pst.setString(14, sg.getAcquis12());
//            pst.setString(15, sg.getAcquis13());
//            pst.setString(16, sg.getAcquis14());
//            pst.setString(17, sg.getAcquis15());
//            pst.setString(18, sg.getAcquis16());
//            pst.setString(19, sg.getAcquis17());
//            pst.setString(20, sg.getAcquis18());
//            pst.setString(21, sg.getAcquis19());
//            pst.setString(22, sg.getAcquis20());
//            pst.setString(23, sg.getAcquis21());
//            pst.setString(24, sg.getNomTuteur()); // Set NomTuteur
//            pst.setString(25, sg.getFonctionTuteur()); // Set FonctionTuteur
//            pst.setString(26, sg.getPostesStagiaire()); // Set PostesStagiaire
//
//            pst.setInt(27, sg.getIdStage());
//            pst.setInt(28, idStg);
//            pst.setInt(29, idCmp); // Set idCmp
//
//            int result = pst.executeUpdate();
//            if (result == 1) {
//                JOptionPane.showMessageDialog(null, "Mise à jour réussie");
//            } else {
//                JOptionPane.showMessageDialog(null, "Erreur : la mise à jour a échoué.");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
//            throw ex;  // Rethrow the exception to ensure it is handled by the calling method
//        } catch (ParseException ex) {
//            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while parsing date", ex);
//            JOptionPane.showMessageDialog(null, "Erreur : Format de date invalide.");
//        }
//    }

    
    //Supprimer (Delete)
    public void deleteStage(int idStage, int idStg, int idCmp) throws SQLException {
        String query = "DELETE FROM Stage WHERE IdStage = ? AND IdStg = ? AND IdCmp = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, idStage);
            pst.setInt(2, idStg);
            pst.setInt(3, idCmp);

            int result = pst.executeUpdate();
            if (result == 1) {
                JOptionPane.showMessageDialog(null, "Suppression réussie");
            } else {
                JOptionPane.showMessageDialog(null, "Erreur : la suppression a échoué.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, "Error while executing SQL statement", ex);
            throw ex;  // Rethrow the exception to ensure it is handled by the calling method
        }
    }

    public Connection getConnection() {
        return con;
    }
}