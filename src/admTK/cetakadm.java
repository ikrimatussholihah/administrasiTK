/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admTK;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class cetakadm extends javax.swing.JFrame {
    Statement st;
    ResultSet rs;
    koneksi koneksi;

    /**
     * Creates new form cetakadm
     */
    public cetakadm() {
          koneksi=new koneksi();
        initComponents();
    }
    
    private void koneksi(){
        Connection con;
        String id, pass, driver, url;
        id= "root";
        pass = " ";
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost/administrasi";
        try{
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url,id,pass);
            if (con==null){
                JOptionPane.showMessageDialog (null,"KONEKSI DATABASE GAGAL");
            }
        } catch (Exception e){
            System.out.println(""+e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        laporandatasiswa = new javax.swing.JButton();
        laporanpendaftaran = new javax.swing.JButton();
        laporanalatseragam = new javax.swing.JButton();
        laporanspp = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sakkal Majalla", 1, 36)); // NOI18N
        jLabel1.setText("TK Islam Ruhama Bekasi");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, 39));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));

        laporandatasiswa.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        laporandatasiswa.setText("Laporan Pendataan Siswa");
        laporandatasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporandatasiswaActionPerformed(evt);
            }
        });

        laporanpendaftaran.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        laporanpendaftaran.setText("Laporan Pendaftaran Siswa");
        laporanpendaftaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanpendaftaranActionPerformed(evt);
            }
        });

        laporanalatseragam.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        laporanalatseragam.setText("Laporan Alat dan Seragam");
        laporanalatseragam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanalatseragamActionPerformed(evt);
            }
        });

        laporanspp.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        laporanspp.setText("Laporan SPP");
        laporanspp.setToolTipText("");
        laporanspp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporansppActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(laporanspp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(laporanalatseragam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(laporandatasiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(laporanpendaftaran, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(laporandatasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(laporanpendaftaran, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(laporanalatseragam, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(laporanspp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 510, 280));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/home (2).jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 60, -1));

        jLabel2.setFont(new java.awt.Font("Sakkal Majalla", 1, 36)); // NOI18N
        jLabel2.setText("Laporan Keseluruhan siswa");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 30));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/logotkkecilLL.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new menuutama().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void laporandatasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporandatasiswaActionPerformed
        // TODO add your handling code here:
        new cetakdatasiswa().setVisible(true);
        dispose();
    }//GEN-LAST:event_laporandatasiswaActionPerformed

    private void laporanpendaftaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanpendaftaranActionPerformed
        // TODO add your handling code here:
        new cetakpendaftaransiswa().setVisible(true);
        dispose(); 
    }//GEN-LAST:event_laporanpendaftaranActionPerformed

    private void laporanalatseragamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanalatseragamActionPerformed
        // TODO add your handling code here:
        new cetakalatseragama().setVisible(true);
        dispose(); 
    }//GEN-LAST:event_laporanalatseragamActionPerformed

    private void laporansppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporansppActionPerformed
        // TODO add your handling code here:
        new cetakspp().setVisible(true);
        dispose(); 
    }//GEN-LAST:event_laporansppActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(cetakadm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cetakadm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cetakadm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cetakadm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cetakadm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton laporanalatseragam;
    private javax.swing.JButton laporandatasiswa;
    private javax.swing.JButton laporanpendaftaran;
    private javax.swing.JButton laporanspp;
    // End of variables declaration//GEN-END:variables
}
