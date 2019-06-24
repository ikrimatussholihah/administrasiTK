
package admTK;

import static admTK.alatdanseragam.tblalatseragam;
import static admTK.data_spp1.tblsppp;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import program.Koneksi;
import koneksi.koneksi;


public class pembayaranSPP extends javax.swing.JFrame {
    Statement st;
    ResultSet rs;
    koneksi koneksi;
    private Connection conn = new koneksi ().connect();
    private DefaultTableModel tabmode;
    /**
     * Creates new form pembayaranSPP
     */
    public pembayaranSPP() {
        koneksi=new koneksi();
        initComponents();
        
        

    }
    private void reset(){
        idsiswa.setText("");
        nama.setText("");
        bulanbayarComboBox.setSelectedItem("");
        jumlahspp.setText("");
      
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        idsiswa = new javax.swing.JTextField();
        jumlahspp = new javax.swing.JTextField();
        btnsimpan = new javax.swing.JButton();
        btnkembali = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        bulanbayarComboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("One Stroke Script LET", 1, 36)); // NOI18N
        jLabel1.setText("Pembayaran Uang SPP");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel2.setText("Nama");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel4.setText("Jumlah");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));
        jPanel1.add(idsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 203, 35));
        jPanel1.add(jumlahspp, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 208, 203, 30));

        btnsimpan.setFont(new java.awt.Font("Tekton Pro Cond", 1, 18)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/save-icon-55391.png"))); // NOI18N
        btnsimpan.setText("Save");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 153, 48));

        btnkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/ip_icon_02_back.png"))); // NOI18N
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });
        jPanel1.add(btnkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 400, 49, -1));

        jLabel6.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel6.setText("Bulan Bayar");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        bulanbayarComboBox.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        bulanbayarComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "July", "Agustus", "September", "Oktober", "November", "Desember" }));
        jPanel1.add(bulanbayarComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 94, 42));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/home (2).jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 54, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Rp.140,000,-");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, -1, -1));
        jPanel1.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 200, 30));

        jLabel7.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel7.setText("NIS");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 93, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/backgroundlogo.jpg"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-70, 0, 610, 380));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
       String sql = "insert into spp (nis,nama,tanggal,keterangan,jumlah,total,status) values (?,?,?,?,?,?,?) ";
         String tanggal = "YYYY-MM-dd HH:mm:ss";
try{
Timestamp tgl = new Timestamp(new Date().getTime());
PreparedStatement stat = conn.prepareStatement(sql);
stat.setString(1, idsiswa.getText());
stat.setString(2, nama.getText());
stat.setString(3, new SimpleDateFormat(tanggal).format(tgl));
stat.setString(4, (String) bulanbayarComboBox.getSelectedItem()); 
stat.setString(5, jumlahspp.getText()); 
stat.setString(6, jumlahspp.getText());
stat.setString(7, "LUNAS"); 

stat.executeUpdate();
JOptionPane.showMessageDialog(null, "data berhasil disimpan");
    reset();
idsiswa.requestFocus();
}
catch (SQLException e){
JOptionPane.showMessageDialog(null, "data gagal disimpan"+e);
}
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
        // TODO add your handling code here:
        new data_spp1().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnkembaliActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new menuutama().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(pembayaranSPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pembayaranSPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pembayaranSPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pembayaranSPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pembayaranSPP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnkembali;
    private javax.swing.JButton btnsimpan;
    public static javax.swing.JComboBox<String> bulanbayarComboBox;
    public static javax.swing.JTextField idsiswa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField jumlahspp;
    private javax.swing.JTextField nama;
    // End of variables declaration//GEN-END:variables
}
