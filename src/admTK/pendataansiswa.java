/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admTK;

import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Lenovo
 */
public class pendataansiswa extends javax.swing.JFrame {
    Statement st;
    ResultSet rs;
   
    koneksi koneksi;
    //cari_nama cari_nama;
   
    /**
     * Creates new form pendataansiswa
     */
    public pendataansiswa() {
        koneksi=new koneksi();
        initComponents();
        tgl.setDateFormatString("YYYY-MM-dd");
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
    }
    
     public void icon(){
    ImageIcon ico = new ImageIcon("src/Gambar/backgroundlogo.jpg");
    setIconImage(ico.getImage());
    }
    
    private void output(){
       Object header[] = {"No","NIS","Nama","NoTelp","Jns Kelamin","Tempat","Tgl Lahir","Nama Orang Tua","Pekerjaan Orang Tua","Agama","Alamat","Diterima","Tahun Ajaran"};

        DefaultTableModel isi = new DefaultTableModel(null, header);
        tblTK.setModel(isi);
        
        
        String sql = "SELECT * from datasiswa";
        try{
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                String k1 = rs.getString(1);
                String k2 = rs.getString(2);
                String k3 = rs.getString(3);
                String k4 = rs.getString(4);
                String k5 = rs.getString(5);
                String k6 = rs.getString(6);
                String k7 = rs.getDate(7).toString();
                String k8 = rs.getString(8);
                String k9 = rs.getString(9);
                String k10 = rs.getString(10);
                String k11 = rs.getString(11);
                String k12 = rs.getString(12);
                String k13 = rs.getString(13);
           
                
                String kolom[] = {k1,k2,k3,k4,k5,k6,k7,k8,k9,k10,k11,k12,k13};
               isi.addRow(kolom);  
           
            }
            
            st.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"error "+e.getMessage());
        }
    }
    
    private void input(){
        String tanggal = "YYYY-MM-dd";
       try {
                    st = koneksi.con.createStatement();
                    String sql = "INSERT INTO datasiswa (nis,nama,no_hp,jns_kel,tmptlahir,tgllahir,nmortu,p_ortu,agama,alamat,terima,thnajaran) values ('"+idsiswa.getText()+"',"
                            + "'"+nama.getText()+"',"
                            + "'"+txthp.getText()+"',"
                            + "'"+cbjk.getSelectedItem()+"',"
                            + "'"+txttempat.getText()+"',"
                            + "'"+new SimpleDateFormat(tanggal).format(tgl.getDate())+"',"
                            + "'"+nmortu.getText()+"',"
                            + "'"+portu.getText()+"',"
                            + "'"+agama.getText()+"',"
                            + "'"+txtalamat.getText()+"',"
                            + "'"+cbterima.getSelectedItem()+"',"
                            + "'"+cbthn.getSelectedItem()+"')";
 
                    JOptionPane.showMessageDialog(null, "Berhasil Disimpan");
                    st.executeUpdate(sql);

                 
      }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
       } 

    }
      private void update(){
          String tanggal = "YYYY-MM-dd";
        try{
           String sql = "update datasiswa set nis= '"+idsiswa.getText()+"',"
                    + "nama = '"+nama.getText()+"',"
                    + "no_hp = '"+txthp.getText()+"',"
                    + "jns_kel = '"+cbjk.getSelectedItem()+"',"
                    + "tmptlahir = '"+txttempat.getText()+"',"
                    + "tgllahir = '"+new SimpleDateFormat(tanggal).format(tgl.getDate())+"',"
                    + "nmortu = '"+nmortu.getText()+"',"
                    + "p_ortu = '"+portu.getText()+"',"
                    + "agama = '"+agama.getText()+"',"
                    + "alamat = '"+txtalamat.getText()+"',"
                    + "terima = '"+cbterima.getSelectedItem()+"',"
                    + "thnajaran = '"+cbthn.getSelectedItem()+"' where nis = '"+idsiswa.getText()+"'";
            System.out.println(sql);
            st = koneksi.con.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Telah di Ubah");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null," Data Gagal Diubah "+e);
        }
    }
     
      private void hapus(){
        String DATA = String.valueOf(tblTK.getValueAt(tblTK.getSelectedRow(),0));
        String query = "delete from datasiswa where id_siswa= '"+DATA+"'";
        try{
            st = koneksi.con.createStatement();
            JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
            st.executeUpdate(query);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"error pada :"+e.getMessage());
        }        
    }
     private void reset(){
        idsiswa.setText("");
        nama.setText("");
        txthp.setText("");
        cbjk.setSelectedItem("");
        txttempat.setText("");
        tgl.setDate(null);
        nmortu.setText("");
        portu.setText("");
        agama.setText("");
        txtalamat.setText("");
        cbterima.setSelectedItem("");
        cbthn.setSelectedItem("");
       
    }
     
     public void datatable(String cari_nama){
         DefaultTableModel tbl = new DefaultTableModel();
         tbl.addColumn("No");
         tbl.addColumn("NIS");
         tbl.addColumn("Nama");
         tbl.addColumn("No Telp");
         tbl.addColumn("Jns Kelamin");
         tbl.addColumn("Tmpt Lahir");
         tbl.addColumn("Tgl Lahir");
         tbl.addColumn("Nama Ortu");
         tbl.addColumn("Pekerjaan Ortu");
         tbl.addColumn("Agama");
         tbl.addColumn("Alamat");
         tbl.addColumn("Diterima");
         tbl.addColumn("Tahun Ajaran");
         if (cari_nama.equals("")){
             try{
                 Statement st =(Statement)koneksi.con.createStatement();
                 ResultSet res=st.executeQuery("select id_siswa,nis,nama,no_hp,jns_kel,tmptlahir,tgllahir,nmortu,p_ortu,agama,alamat,terima,thnajaran from datasiswa");
                 while (res.next()){
                     tbl.addRow(new Object[]{ 
                         res.getString("id_siswa"),
                         res.getString("nis"),
                         res.getString("nama"),
                         res.getString("no_hp"),
                         res.getString("jns_kel"),
                         res.getString("tmptlahir"),
                         res.getString("tgllahir"),
                         res.getString("nmortu"),
                         res.getString("p_ortu"),
                         res.getString("agama"),
                         res.getString("alamat"),
                         res.getString("terima"),
                         res.getString("thnajaran"),
                     });
                 }
             }catch (Exception e) {
                 
             }
         }else{
             try{
                 Statement st = (Statement)koneksi.con.createStatement();
                 ResultSet res = st.executeQuery("select id_siswa,nis,nama,no_hp,jns_kel,tmptlahir,tgllahir,nmortu,p_ortu,agama,alamat,terima,thn ajaran from datasiswa where nama Like '%"+cari_nama+"%'");
                 
                 while(res.next()){
                     tbl.addRow(new Object[]{
                         res.getString("id_siswa"),
                         res.getString("nis"),
                         res.getString("nama"),
                         res.getString("no_hp"),
                         res.getString("jns_kel"),
                         res.getString("tmptlahir"),
                         res.getString("tgllahir"),
                         res.getString("nmortu"),
                         res.getString("p_ortu"),
                         res.getString("agama"),
                         res.getString("alamat"),
                         res.getString("terima"),
                         res.getString("thnajaran")
                     });
                 }
             }catch (Exception e){
                 
             }
         }
         tblTK.setModel(tbl);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        idsiswa = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nmortu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txthp = new javax.swing.JTextField();
        txttempat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTK = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        cbjk = new javax.swing.JComboBox<>();
        cari_nama = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        agama = new javax.swing.JTextField();
        portu = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnsave = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnhome = new javax.swing.JButton();
        tgl = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbterima = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbthn = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 182, -1, 195));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel3.setText("NIS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 99, -1, -1));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel4.setText("Nama");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));
        jPanel1.add(idsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 90, 200, 28));
        jPanel1.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 200, 25));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel5.setText("Jenis Kelamin");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel6.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel6.setText("Alamat");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, -1, -1));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel7.setText("Nama OrangTua");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));
        jPanel1.add(nmortu, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 198, 26));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel8.setText("Tempat");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel10.setText("No.Telpn");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));
        jPanel1.add(txthp, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 167, 200, 28));
        jPanel1.add(txttempat, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 200, 30));

        tblTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No induk", "Nama", "No Hp", "Jenis Kelamin", "Tempat Lahir", "Tanggal Lahir", "Nama Bapak", "Pekerjaan Bapak", "Agama", "terima"
            }
        ));
        tblTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTKMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTK);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 1130, 200));

        txtalamat.setColumns(20);
        txtalamat.setRows(5);
        jScrollPane2.setViewportView(txtalamat);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 180, 200, 82));

        cbjk.setFont(new java.awt.Font("Tekton Pro Ext", 1, 14)); // NOI18N
        cbjk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "L", "P" }));
        jPanel1.add(cbjk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, -1, -1));
        jPanel1.add(cari_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 258, 25));

        jLabel11.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel11.setText("Tgl Lahir");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jLabel12.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel12.setText("PekerjaanOrangTua");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, -1, 30));

        jLabel15.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel15.setText("Agama");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, -1, -1));
        jPanel1.add(agama, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 130, 198, 31));
        jPanel1.add(portu, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 198, 27));

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        btnsave.setFont(new java.awt.Font("Prestige Elite Std", 1, 14)); // NOI18N
        btnsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/save-icon-55391.png"))); // NOI18N
        btnsave.setText("Simpan");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnedit.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/report_edit.png"))); // NOI18N
        btnedit.setText("ubah");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btnhapus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/Delete.png"))); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btnhome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/home (2).jpg"))); // NOI18N
        btnhome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnhapus)
                            .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(btnsave)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnhome, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnhome)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 100, 130, 310));
        jPanel1.add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 140, 30));

        jPanel3.setBackground(new java.awt.Color(0, 153, 204));

        jLabel9.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel9.setText("Penerimaan Siswa Baru TK Islam Ruhama ");

        jLabel16.setFont(new java.awt.Font("Tekton Pro", 1, 18)); // NOI18N
        jLabel16.setText("Jalan Raya Hankam No.5 RT 006/009, Jati warna Pondok Melati Bekasi");

        jLabel1.setToolTipText("");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/logotkkecilLL.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel9)))
                .addContainerGap(333, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16))
                    .addComponent(jLabel1))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setToolTipText("");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 690, 450));

        jLabel13.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel13.setText("Diterima");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, -1, -1));

        cbterima.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbterima.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kelompok A", "Kelompok B" }));
        jPanel1.add(cbterima, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, -1, -1));

        jLabel18.setFont(new java.awt.Font("Simplified Arabic Fixed", 1, 18)); // NOI18N
        jLabel18.setText("Tahun Ajaran");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, -1, -1));
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 670, -1));

        cbthn.setFont(new java.awt.Font("Yu Gothic Light", 1, 14)); // NOI18N
        cbthn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2014/2015", "2015/2016", "2016/2017", "2017/2018", "2018/2019", "2019/2020", "2020/2021", "2021/2022", "2022/2023", "2023/2024", "2024/2025" }));
        jPanel1.add(cbthn, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 350, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar icon/backgroundlogo.jpg"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(-120, 80, 580, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed

        // TODO add your handling code here:
        input();
        output();
        reset();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        update();
          reset();
        output();
    }//GEN-LAST:event_btneditActionPerformed

    private void tblTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTKMouseClicked
        // TODO add your handling code here:
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        int bar = tblTK.getSelectedRow();
        String id = tblTK.getValueAt(bar, 1).toString();
        String nama1 = tblTK.getValueAt(bar, 2).toString();
        String no_hp = tblTK.getValueAt(bar, 3).toString();
        String jenis_kel = tblTK.getValueAt(bar, 4).toString();
        String tempat_lahir = tblTK.getValueAt(bar, 5).toString();
        String tgl_lahir = tblTK.getValueAt(bar, 6).toString();
        String nama_ortu = tblTK.getValueAt(bar, 7).toString();
        String pekerjaan_ortu = tblTK.getValueAt(bar, 8).toString();
        String agama1 = tblTK.getValueAt(bar, 9).toString();
        String alamat = tblTK.getValueAt(bar, 10).toString();
        String terima_kelas = tblTK.getValueAt(bar, 11).toString();
        String tahun_ajaran = tblTK.getValueAt(bar, 12).toString();
        
       
        try {
            Date dateok = formatter.parse(tgl_lahir);
            tgl.setDate(dateok);
        } catch (ParseException ex) {
            Logger.getLogger(pendataansiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        idsiswa.setText(id);
        nama.setText(nama1);
        txthp.setText(no_hp);
        cbjk.setSelectedItem(jenis_kel);
        txttempat.setText(tempat_lahir);
        nmortu.setText(nama_ortu);
        portu.setText(pekerjaan_ortu);
        agama.setText(agama1);
        txtalamat.setText(alamat);
        cbterima.setSelectedItem(terima_kelas);
        cbthn.setSelectedItem(tahun_ajaran);
    
    }//GEN-LAST:event_tblTKMouseClicked

    private void btnhomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhomeActionPerformed
        // TODO add your handling code here:
         new menuutama().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnhomeActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
         String ObjButtons[] = {"Iya","Tidak"};
        int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda yakin ingin menghapus?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(pilihan == 0){
            hapus();
        }else {JOptionPane.showMessageDialog(null,"Data batal dihapus");}
        output();
        reset();
    }//GEN-LAST:event_btnhapusActionPerformed

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
            java.util.logging.Logger.getLogger(pendataansiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pendataansiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pendataansiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pendataansiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pendataansiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField agama;
    private javax.swing.JButton btnedit;
    public static javax.swing.JButton btnhapus;
    private javax.swing.JButton btnhome;
    public static javax.swing.JButton btnsave;
    public static javax.swing.JTextField cari_nama;
    private javax.swing.JComboBox<String> cbjk;
    private javax.swing.JComboBox<String> cbterima;
    private javax.swing.JComboBox<String> cbthn;
    private javax.swing.JTextField idsiswa;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTextField nama;
    private javax.swing.JTextField nmortu;
    private javax.swing.JTextField portu;
    public static javax.swing.JTable tblTK;
    public static com.toedter.calendar.JDateChooser tgl;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txthp;
    private javax.swing.JTextField txttempat;
    // End of variables declaration//GEN-END:variables

    private void kosong() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void datatable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
