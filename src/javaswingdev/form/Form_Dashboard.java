package javaswingdev.form;

import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.card.ModelCard;

public class Form_Dashboard extends javax.swing.JPanel {

    public Form_Dashboard() {
        initComponents();
        init();
         datecourante() ;
    }
    
        public void datecourante() {
        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar Cal = new GregorianCalendar();

                    int sconde = Cal.get(Calendar.SECOND);
                    int minute = Cal.get(Calendar.MINUTE);
                    int heure = Cal.get(Calendar.HOUR);
                    int AM_PM = Cal.get(Calendar.AM_PM);  
                    String pa;
                    if(AM_PM==1){
                        pa="PM";
                    }else{
                        pa="AM";
                    }
                    
                         
                labDate.setText( + heure + ":" + (minute) + ":" + sconde +" "+pa  );
                    
                 int mois = Cal.get(Calendar.MONTH);
                    int annee = Cal.get(Calendar.YEAR);
                    int jour = Cal.get(Calendar.DAY_OF_MONTH);

                    labdate2.setText( + jour + "/" + (mois+1) + "/" + annee);              
                    
                    
                    try {
                        sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        };
        
clock.start();
    }

    private void init() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labDate = new javax.swing.JLabel();
        labdate2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Book Antiqua", 1, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 255, 255));
        jLabel6.setText("Groupe");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Book Antiqua", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 255));
        jLabel5.setText("IPIRNET");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 90, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaswingdev/img/banner-01.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, 820));

        labDate.setFont(new java.awt.Font("DS-Digital", 1, 24)); // NOI18N
        labDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labDate.setText("10:15:30 PM");
        add(labDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, -1, -1));

        labdate2.setFont(new java.awt.Font("FreeSerif", 0, 14)); // NOI18N
        labdate2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labdate2.setText("19/04/2024");
        add(labdate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 200, -1, 20));

        jLabel9.setFont(new java.awt.Font("Segoe Print", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 204));
        jLabel9.setText("\"Be the best version of yourself.\"");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 670, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe Print", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 204));
        jLabel12.setText("\"Deviens la meilleure version de toi-même.\"");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 650, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel14.setText("\"Bienvenue sur le Tableau de Bord de Groupe IPERNET\"");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 410, 20));

        jLabel15.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel15.setText("Vous trouverez ici tous les outils et informations nécessaires ");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 350, 460, 20));

        jLabel16.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel16.setText("pour gérer efficacement les opérations de l'école. Des dossiers");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, 460, 20));

        jLabel17.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel17.setText("des élèves à la gestion du personnel, en passant par");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 460, 20));

        jLabel18.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel18.setText("tout est à portée de main.");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 430, 460, 20));

        jLabel19.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel19.setText("les données financières et les outils de communication,");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 410, 460, 20));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labDate;
    private javax.swing.JLabel labdate2;
    // End of variables declaration//GEN-END:variables
}
