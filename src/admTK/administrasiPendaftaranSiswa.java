/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admTK;


import static admTK.pendataansiswa.cari_nama;
import static admTK.pendataansiswa.tblTK;
import static admTK.pendataansiswa.tgl;
import java.awt.event.KeyEvent;
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

/**
 *
 * @author Lenovo
 */
public class administrasiPendaftaranSiswa extends javax.swing.JFrame {
    Statement st;
    ResultSet rs;
    koneksi koneksi;
    int totalInt;
    int Cicilan1;
    /**
     * Creates new form Pembayaran
     */
    public administrasiPendaftaranSiswa() {
        koneksi=new koneksi();
        
        initComponents();
        datatable("");
        cari_nama.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                datatable(cari_nama.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                datatable(cari_nama.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not Supported yet.");//to change body og generated methods. choose tools |templates.
            }
        });
        
        
        simpan.setEnabled(false);
        ttotal.setEditable(false);
        status.setEditable(false);
        jTextArea1.setEditable(false);
        output();
        icon(); 
    }
    
     public void icon(){
    ImageIcon ico = new ImageIcon("src/Gambar/background.jpg");
    setIconImage(ico.getImage());
    }
    
    private void output(){
       Object header[] = {"No","NIS","Nama","Tanggal","Keterangan","Jumlah","Total","Status"};
        DefaultTableModel isi = new DefaultTableModel(null, header);
        tblpendaftaran.setModel(isi);
        
        String sql = "SELECT * from pendaftaran";
        try{
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                //int k0 = rs.;
                String k1 = rs.getString(1);
                String k2 = rs.getString(2);
                String k3 = rs.getString(3);
                String k5 = rs.getString(5);
                String k4 = rs.getDate(4).toString();
                String k6 = rs.getString(6);
                String k7 = rs.getString(7);
                String k8 = rs.getString(8);
          
                
                String kolom[] = {k1,k2,k3,k4,k5,k6,k7,k8};
                isi.addRow(kolom);
               
            }
            st.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
    }
    
    private void input(){
        String tanggal = "YYYY-MM-dd HH:mm:ss";
        String ket = null;
        Timestamp tgl = new Timestamp(new Date().getTime());
        
       try { 
       
                    st = koneksi.con.createStatement();
                    String sql = "INSERT INTO pendaftaran (nis,nama,tanggal,keterangan,jumlah,total,status) values ('"+nis.getText()+"',"
                             + "'"+nama.getText()+"',"
                            + "'"+new SimpleDateFormat(tanggal).format(tgl)+"',"
                            + "'"+cicilan.getSelectedItem()+"',"
                            + "'"+ttotal.getText()+"',"
                            + "'"+jmlhpendaftaran.getText()+"',"
                            + "'"+status.getText()+"')";
                          
                                  
                      JOptionPane.showMessageDialog(null, "Berhasil Disimpan");
                    st.executeUpdate(sql);
                 
      }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
       }  
    }
    
     private void reset(){
        nis.setText("");
        nama.setText("");
        cicilan.setSelectedItem("");
        jmlhpendaftaran.setText("");
        ttotal.setText("");
        status.setText("");
       
    }
     
     private void total(){
        int Cicilan2 = Integer.parseInt(jmlhpendaftaran.getText());
        int totl = 0;
        int ttal = Cicilan1+Cicilan2;
        ttotal.setText(Integer.toString(ttal));
    }
    
    private void status(){
        int  to =Integer.parseInt(ttotal.getText());
        if (to==700000){
            status.setText("LUNAS");
            simpan.setEnabled(true);
        } else if (to >=700000){ 
            status.setText("Melebihi Pembayaran");
            simpan.setEnabled(false);
        } else {
            status.setText("BELUM LUNAS");
            simpan.setEnabled(true);
        }
       }

    
    public void datatable(String cari_nama){
         DefaultTableModel tbl = new DefaultTableModel();
         tbl.addColumn("No");
         tbl.addColumn("NIS");
         tbl.addColumn("Nama");
         tbl.addColumn("Tanggal");
         tbl.addColumn("Keterangan");
         tbl.addColumn("Jumlah");
         tbl.addColumn("Total");
         tbl.addColumn("Status");
  
         if (cari_nama.equals("")){
             try{
                 Statement st =(Statement)koneksi.con.createStatement();
                 ResultSet res=st.executeQuery("select id_pembayaran,nis,nama,tanggal,keterangan,jumlah,total,status from pendaftaran");
                 while (res.next()){
                     tbl.addRow(new Object[]{ 
                      
                         res.getString("id_pembayaran"),
                         res.getString("nis"),
                         res.getString("nama"),
                         res.getString("tanggal"),
                         res.getString("keterangan"),
                         res.getString("jumlah"),
                         res.getString("total"),
                         res.getString("status"),
                         
                     });
                 }
             }catch (Exception e) {
                 
             }
         }else{
             try{
                 Statement st = (Statement)koneksi.con.createStatement();
                 ResultSet res = st.executeQuery("select id_pembayaran,nis,nama,tanggal,keterangan,jumlah,total,status from pendaftaran where nama Like '%"+cari_nama+"%'");
                 
                 while(res.next()){
                     tbl.addRow(new Object[]{
                         res.getString("id_pembayaran"),
                         res.getString("nis"),
                         res.getString("nama"),
                         res.getString("tanggal"),
                         res.getString("keterangan"),
                         res.getString("jumlah"),
                         res.getString("total"),
                         res.getString("status"),
                     });
                 }
             }catch (Exception e){
                 
             }
         }
         tblpendaftaran.setModel(tbl);
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cicilan = new javax.swing.JComboBox<>();
        nama = new javax.swing.JTextField();
        jmlhpendaftaran = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        cari_nama = new javax.swing.JTextField();
        simpan = new javax.swing.JButton();
        ttotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        status = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblpendaftaran = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        nis = new javax.swing.JTextField();
        nis2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel4.setText("Nama");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel7.setText("Jumlah");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel10.setText("Pembayaran");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        cicilan.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        cicilan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cicilan 1", "cicilan 2" }));
        getContentPane().add(cicilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, 31));
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 230, 30));

        jmlhpendaftaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jmlhpendaftaranKeyPressed(evt);
            }
        });
        getContentPane().add(jmlhpendaftaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 230, 34));

        jTextArea1.setBackground(new java.awt.Color(102, 153, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Notes!\n\nTotal Pembayaran Uang Gedung\nTK Islam Ruhama Bekasi\nRp.700,000,-");
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 249, 160));
        getContentPane().add(cari_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 230, 31));

        simpan.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save-icon-55391.png"))); // NOI18N
        simpan.setText("save");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        getContentPane().add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, 210, 51));
        getContentPane().add(ttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 230, 35));

        jLabel3.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel3.setText("Total");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));
        getContentPane().add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 230, 32));

        jLabel5.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel5.setText("Status");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("*Enter");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, -1, -1));

        jLabel13.setText("Untuk memperoleh Total dan Status");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logotkkecilLL.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("NSimSun", 1, 24)); // NOI18N
        jLabel1.setText("Administrasi Pendaftaran Siswa");

        jLabel2.setFont(new java.awt.Font("NSimSun", 1, 24)); // NOI18N
        jLabel2.setText("TK Islam Ruhama Bekasi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(491, 491, 491)
                .addComponent(jLabel8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel2)))
                .addContainerGap(241, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-290, 0, 1190, 110));

        tblpendaftaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblpendaftaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpendaftaranMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblpendaftaran);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 665, 110));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home (2).jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 540, 72, -1));
        getContentPane().add(nis, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 127, 230, 30));
        getContentPane().add(nis2, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 127, 230, 30));

        jLabel11.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel11.setText("NIS");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        update.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        getContentPane().add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 390, 210, 50));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background.jpg"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(-130, 110, 580, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background.jpg"))); // NOI18N
        jLabel6.setToolTipText("");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        input();
        output();
        reset();
    }//GEN-LAST:event_simpanActionPerformed

    private void tblpendaftaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpendaftaranMouseClicked
        // TODO add your handling code here:
        simpan.setEnabled(false);
        int bar = tblpendaftaran.getSelectedRow();
        String nis1 = tblpendaftaran.getValueAt(bar, 1).toString();
        String nama1 = tblpendaftaran.getValueAt(bar, 2).toString();
        String cicilan1 = tblpendaftaran.getValueAt(bar, 4).toString();
        String jumlahString = tblpendaftaran.getValueAt(bar, 5).toString();
        String totalString = tblpendaftaran.getValueAt(bar, 6).toString();
        String status1 = tblpendaftaran.getValueAt(bar, 7).toString();
        
        Cicilan1 = Integer.parseInt(jumlahString);
        totalInt = Integer.parseInt(totalString);
        
            nis.setText(nis1);
            nama.setText(nama1);
            cicilan.setSelectedItem(cicilan1);
            jmlhpendaftaran.setText(jumlahString);
            ttotal.setText(totalString);
            status.setText(status1);
    }//GEN-LAST:event_tblpendaftaranMouseClicked

    private void jmlhpendaftaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jmlhpendaftaranKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            total();
            status();
        }
    }//GEN-LAST:event_jmlhpendaftaranKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new menuutama().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        try{
           String sql = "update pendaftaran set total = '"+ttotal.getText()+"', status = '"+status.getText()+"' where nis = '"+nis.getText()+"'";
                   
            System.out.println(sql);
            st = koneksi.con.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Telah di Ubah");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null," Data Gagal Diubah "+e);
        }
        output();
    }//GEN-LAST:event_updateActionPerformed

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
            java.util.logging.Logger.getLogger(administrasiPendaftaranSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(administrasiPendaftaranSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(administrasiPendaftaranSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(administrasiPendaftaranSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new administrasiPendaftaranSiswa().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cari_nama;
    private javax.swing.JComboBox<String> cicilan;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jmlhpendaftaran;
    public static javax.swing.JTextField nama;
    public static javax.swing.JTextField nis;
    public static javax.swing.JTextField nis2;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField status;
    public static javax.swing.JTable tblpendaftaran;
    private javax.swing.JTextField ttotal;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
