/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admTK;

import static admTK.administrasiPendaftaranSiswa.tblpendaftaran;
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
public class alatdanseragam extends javax.swing.JFrame {
    Statement st;
    ResultSet rs;
   
    koneksi koneksi;

    /**
     * Creates new form alatdanseragam
     */
    public alatdanseragam() {
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
        
        output();
        icon(); 
        ttotal.setEditable(false);
        status.setEditable(false);
        jTextArea1.setEditable(false);
    }
    
    public void icon(){
    ImageIcon ico = new ImageIcon("src/Gambar/background.jpg");
    setIconImage(ico.getImage());
    }
    
     private void output(){
       Object header[] = {"No","NIS","Nama","Jenis","Tanggal","Keterangan","Jumlah","Total","Status"};
       
        DefaultTableModel isi = new DefaultTableModel(null, header);
        tblalatseragam.setModel(isi);
        
        String sql = "SELECT * from alatdanseragam";
        try{
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                //int k0 = rs.;
                String k1 = rs.getString(1);
                String k2 = rs.getString(2);
                String k3 = rs.getString(3);
                String k4 = rs.getString(4);
                String k5 = rs.getDate(5).toString();
                String k6 = rs.getString(6);
                String k7 = rs.getString(7);
                String k8 = rs.getString(8);
                String k9 = rs.getString(9);
                
                String kolom[] = {k1,k2,k3,k4,k5,k6,k7,k8,k9};
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
        //int id = 0;
       try { 
       
                    st = koneksi.con.createStatement();
                    String sql = "INSERT INTO alatdanseragam (nis,nama,jenis,tanggal,keterangan,jumlah,total,status) values ('"+nisalat.getText()+"',"
                            + "'"+nama.getText()+"',"
                            + "'"+cbalatseragam.getSelectedItem()+"',"
                            + "'"+new SimpleDateFormat(tanggal).format(tgl)+"',"
                            + "'"+cbsemester.getSelectedItem()+"',"
                            + "'"+jumlah.getText()+"',"
                            + "'"+ttotal.getText()+"',"
                            + "'"+status.getText()+"')";
                          
                                  
                      JOptionPane.showMessageDialog(null, "Berhasil Disimpan");
                    st.executeUpdate(sql);
                 
      }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
       }  
    }
    
     private void reset(){
        nisalat.setText("");
        nama.setText("");
        cbalatseragam.setSelectedItem("");
        cbsemester.setSelectedItem("");
        jumlah.setText("");
        ttotal.setText("");
        status.setText("");
       
    }
     
   private void total(){
        int jmlh = Integer.parseInt(jumlah.getText());
        int totl = 0;
        int ttal = jmlh+totl;
        ttotal.setText(Integer.toString(ttal));
    }
    
    private void status(){
        int  to =Integer.parseInt(ttotal.getText());
        if (to==300000){
            status.setText("LUNAS");
            simpan.setEnabled(true);
        } else if (to >=300000){ 
            status.setText("Melebihi Pembayaran");
            simpan.setEnabled(false);
        } else {
            status.setText("Alat Tulis TK");
            simpan.setEnabled(true);
        }
       }
    
    public void datatable(String cari_nama){
         DefaultTableModel tbl = new DefaultTableModel();
         tbl.addColumn("NIS");
         tbl.addColumn("Nama");
         tbl.addColumn("Jenis");
         tbl.addColumn("Tanggal");
         tbl.addColumn("Keterangan");
         tbl.addColumn("Jumlah");
         tbl.addColumn("Total");
         tbl.addColumn("Status");
  
         if (cari_nama.equals("")){
             try{
                 Statement st =(Statement)koneksi.con.createStatement();
                 ResultSet res=st.executeQuery("select nis,nama,jenis,tanggal,keterangan,jumlah,total,status from alatdanseragam");
                 while (res.next()){
                     tbl.addRow(new Object[]{ 
                      
                         res.getString("nis"),
                         res.getString("nama"),
                         res.getString("jenis"),
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
                 ResultSet res = st.executeQuery("select nis,nama,jenis,tanggal,keterangan,jumlah,total,status from alatdanseragam where nama Like '%"+cari_nama+"%'");
                 
                 while(res.next()){
                     tbl.addRow(new Object[]{
                         res.getString("nis"),
                         res.getString("nama"),
                         res.getString("jenis"),
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
         tblalatseragam.setModel(tbl);
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        simpan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblalatseragam = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        cari_nama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        status = new javax.swing.JTextField();
        ttotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbsemester = new javax.swing.JComboBox<>();
        cbalatseragam = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        nisalat = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jRadioButton2.setText("jRadioButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        simpan.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save-icon-55391.png"))); // NOI18N
        simpan.setText("Save");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(simpan)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 242, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setBackground(new java.awt.Color(0, 153, 153));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setText("Notes !\n\nTotal Rp. 300,000 Pembayaran \nAlat dan Seragam\nPembayaran Alat Tulis TK Rp. 80,000");
        jScrollPane2.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 270, 113));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(541, 342, -1, -1));

        tblalatseragam.setModel(new javax.swing.table.DefaultTableModel(
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
        tblalatseragam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblalatseragamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblalatseragam);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 610, 106));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home (2).jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 590, 68, -1));
        jPanel1.add(cari_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 170, 30));

        jLabel8.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel8.setText("Status");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, -1, -1));
        jPanel1.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 200, 32));
        jPanel1.add(ttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 200, 32));

        jLabel9.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel9.setText("Total");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jumlahKeyPressed(evt);
            }
        });
        jPanel1.add(jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 200, 32));

        jLabel13.setText("Untuk memperoleh Total dan Status");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("*Enter");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, -1));

        cbsemester.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        cbsemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semester Ganjil", "Semester Genap" }));
        jPanel1.add(cbsemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 158, 31));

        cbalatseragam.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        cbalatseragam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seragam TK dan Alat Tulis", "Alat tulis TK" }));
        jPanel1.add(cbalatseragam, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel4.setText("Pembayaran");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));
        jPanel1.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 210, 30));

        jLabel3.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel3.setText("NIS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel14.setText("Nama");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));
        jPanel1.add(nisalat, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 210, 29));

        jLabel15.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel15.setText("Jumlah");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background.jpg"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logotkkecilLL.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tekton Pro", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pembayaran Alat dan Seragam");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tekton Pro", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TK Islam Ruhama Bekasi");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 660));

        jLabel11.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel11.setText("NIS");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 111, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        input();
        output();
        reset();
    }//GEN-LAST:event_simpanActionPerformed

    private void tblalatseragamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblalatseragamMouseClicked
        // TODO add your handling code here:
        String AS = String.valueOf(alatdanseragam.tblalatseragam.getValueAt(alatdanseragam.tblalatseragam.getSelectedRow(),0));
        System.out.println(AS);
       // String sql = "SELECT id_pembayaran,nis,jenis,keterangan,jumlah,total,status FROM pembayaran WHERE nis ='"+AS+"'";
       String sql = "SELECT * FROM alatdanseragam WHERE id_pembayaran ='"+ AS+"'"; 
       try{
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            nisalat.setText(rs.getString(1));
            nama.setText(rs.getString(2));
            cbalatseragam.setSelectedItem(rs.getString(3));
            cbsemester.setSelectedItem(rs.getString(5));
            tgl.setDate (rs.getDate(4));
            jumlah.setText(rs.getString(6));
            ttotal.setText(rs.getString(7));
            status.setText(rs.getString(8));
            
            }catch (Exception e){
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
    }//GEN-LAST:event_tblalatseragamMouseClicked

    private void jumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            total();
            status ();
        }
    }//GEN-LAST:event_jumlahKeyPressed

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
            java.util.logging.Logger.getLogger(alatdanseragam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(alatdanseragam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(alatdanseragam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(alatdanseragam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new alatdanseragam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cari_nama;
    private javax.swing.JComboBox<String> cbalatseragam;
    private javax.swing.JComboBox<String> cbsemester;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nisalat;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField status;
    public static javax.swing.JTable tblalatseragam;
    private javax.swing.JTextField ttotal;
    // End of variables declaration//GEN-END:variables
}
