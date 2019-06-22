/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admTK;

import static admTK.pendataansiswa.tblTK;
import static admTK.pendataansiswa.tgl;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.util.*;
import java.io.*;
import java.sql.SQLException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Lenovo
 */
public class data_spp1 extends javax.swing.JFrame {
    Statement st;
    ResultSet rs;
    koneksi koneksi;
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    /**
     * Creates new form data_bukudanseragam
     */
    public data_spp1() {
        koneksi = new koneksi();
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
    }
    
    public void koneksi() {
        Connection con;
        String driver, url;
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost/administrasi";
        try{
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url);
            if (con==null){
                JOptionPane.showMessageDialog (null,"KONEKSI DATABASE GAGAL");
            }
        } catch (Exception e){
            System.out.println(""+e.getMessage());
        }
    }
 protected void output(){
Object header[] ={"No","NIS","Nama","Tanggal","Keterangan","Jumlah","Total","Status"};
tabmode = new DefaultTableModel(null, header);

try {
String sql = "SELECT * FROM spp "; 
Statement stat = conn.createStatement();
ResultSet hasil = stat.executeQuery(sql);
while (hasil.next()){
tabmode.addRow(new Object[]{
hasil.getString(1),
hasil.getString(2),
hasil.getString(3),
hasil.getString(4),
hasil.getString(5),
hasil.getString(6),
hasil.getString(7),
hasil.getString(8),
});
}
tblsppp.setModel(tabmode);
}catch (Exception e){
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
    }
     
  public void datatable(String cari_nama){
         DefaultTableModel tbl = new DefaultTableModel();
         tbl.addColumn("NIS");
         tbl.addColumn("Nama");
         tbl.addColumn("Tanggal");
         tbl.addColumn("Keterangan");
         tbl.addColumn("Jumlah");
         tbl.addColumn("Total");
         tbl.addColumn("Status");
        
  
         if (cari_nama.equals("")){
             try{
                 Statement st =(Statement)conn.createStatement();
                 ResultSet res=st.executeQuery("select nis,nama,tanggal,keterangan,jumlah,total,status from spp");
                 while (res.next()){
                     tbl.addRow(new Object[]{ 
                      
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
                 Statement st = (Statement)conn.createStatement();
                 ResultSet res = st.executeQuery("select nis,nama,tanggal,keterangan,jumlah,total,status from spp where nama Like '%"+cari_nama+"%'");
                 
                 while(res.next()){
                     tbl.addRow(new Object[]{
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
         tblsppp.setModel(tbl);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsppp = new javax.swing.JTable();
        cari_nama = new javax.swing.JTextField();
        btnhome = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btntambah = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblsppp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblsppp);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 126, 795, 156));

        cari_nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cari_namaKeyPressed(evt);
            }
        });
        jPanel1.add(cari_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 92, 228, -1));

        btnhome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home (2).jpg"))); // NOI18N
        btnhome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnhome, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 480, 59, -1));

        jLabel1.setFont(new java.awt.Font("Tekton Pro Cond", 1, 36)); // NOI18N
        jLabel1.setText("Data SPP");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        jLabel2.setBackground(new java.awt.Color(0, 0, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tamanbermaintk.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, 50, 770, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        btntambah.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        btntambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tambah.png"))); // NOI18N
        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btntambah)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 370, 150, 90));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnhomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhomeActionPerformed
        // TODO add your handling code here:
        new menuutama ().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnhomeActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
        pembayaranSPP as = new pembayaranSPP();
        as.setVisible(true);
        dispose();
    }//GEN-LAST:event_btntambahActionPerformed

    private void cari_namaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari_namaKeyPressed
         
    }//GEN-LAST:event_cari_namaKeyPressed

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
            java.util.logging.Logger.getLogger(data_spp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_spp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_spp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_spp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_spp1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhome;
    private javax.swing.JButton btntambah;
    public static javax.swing.JTextField cari_nama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tblsppp;
    // End of variables declaration//GEN-END:variables
}
