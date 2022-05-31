import com.mysql.jdbc.Util;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.Component;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import javax.swing.ImageIcon;
import java.text.ParseException;
import java.util.Date;  
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.sql.*;
public class HOADON extends javax.swing.JFrame {

    ArrayList<hd> listt = new ArrayList<>();
    ArrayList<nv> list = new ArrayList<>();
    ArrayList<product> li = new ArrayList<>();
    DefaultTableModel model;
    int index = 0;
    private Connection conn;
    public HOADON() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
       conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/qlch", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getListNV();
        getListSP();
        getListKH();
        listt = getListHD();
        model = (DefaultTableModel) tblQLSP.getModel();
        loadDbToTable();
    }
            public boolean check() {
        if (MA.getText().equals("") || NV.getSelectedItem().toString().equals("") || Gia.getText().equals("")
                || SL.getText().equals("") || Ngay.getText().equals("") || KH2.getSelectedItem().toString().equals("") || SP.getSelectedItem().toString().equals("") ) {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đủ dữ liệu sau đó ấn Save");
            return false;
        } 
        return true;
    }
        public void loadDbToTable() {
        try {
            
            model.setRowCount(0);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM HOADON");
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                row.add(rs.getString(7));
                row.add(rs.getString(8));
                model.addRow(row);
            }
            tblQLSP.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
            public void fillTable() {
        model.setRowCount(0);
        for (hd s : listt) {
            Object[] row = new Object[]{s.getMahd(), s.getTennv(), s.getTenkh(), s.getTensp(), s.getSl(), s.getGia(), s.getThanhtien(),s.getNgaylap()};
            model.addRow(row);
        }
    }
    public void showDetail(int index) {
        MA.setText(listt.get(index).getMahd());
    }
        
            public ArrayList<hd> getListHD() {

        String sql = "SELECT * FROM HOADON";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hd sp = new hd();
                sp.setMahd(rs.getString(1));
                sp.setTennv(rs.getString(2));
                sp.setTenkh(rs.getString(3));
                sp.setTensp(rs.getString(4));
                sp.setSl(rs.getString(5));
                sp.setGia(rs.getString(6));
                sp.setThanhtien(rs.getString(7));
                sp.setNgaylap(rs.getString(8));
                listt.add(sp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listt;
    }
    public ArrayList<nv> getListNV() {

        String sql = "SELECT NAME FROM USERS";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nv sp = new nv();
                sp.setName(rs.getString("name"));
                NV.addItem(sp.getName());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public ArrayList<product> getListSP() {

        String sql = "SELECT TENSP FROM PRODUCT";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                product p = new product();
                p.setTensp(r.getString("tensp"));
                SP.addItem(p.getTensp());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return li;
    }
    
    public ArrayList<product> getListKH() {
        String sql = "SELECT Tenkh FROM khachhang";
       try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                kh p = new kh();
                p.setTenkh(r.getString("Tenkh"));
                KH2.addItem(p.getTenkh());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return li;
    }
    
    public static boolean isDateValid(String date) {
        String DATE_FORMAT = "dd/mm/yyyy";
        try {
            // validate format 
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");  
            df.setLenient(false);
            df.parse(date);
            
//            Date currentDate = new Date();  
            
//             System.out.println(formatter.format(currentDate));
            // validate date is not overcome today
//            String currentDate = new SimpleDateFormat("dd/mm/yyyy").format(Calendar.getInstances().getTime());
//            System.out.println(df.parse(date).after(df.parse(currentDate)));
//            System.out.println(currentDate);
//
//           if (!df.parse(date).after(df.parse(currentDate))) {
//               System.out.println(currentDate);
//               return false;
//           }

            return true;
        } catch (ParseException e) {
            return false;
        }
    }
        public boolean saveHD(hd pr) {
        String sql = "INSERT INTO HOADON VALUES(?,?,?,?,?,?,?,?)";
        try {
            String s = "SELECT * FROM HOADON WHERE MAHD = ?";
                PreparedStatement p = conn.prepareStatement(s);
                p.setString(1, MA.getText());
                ResultSet rs = p.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(rootPane, "Mã hóa đơn đã tồn tại");
            }else{
                PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pr.getMahd());
            ps.setString(2, pr.getTennv());
            ps.setString(3, pr.getTenkh());
            ps.setString(4, pr.getTensp());
            ps.setString(5, pr.getGia());
            ps.setString(6, pr.getSl());
            ps.setString(7, pr.getThanhtien());
            // validate ngay lap
            if (isDateValid(pr.getNgaylap())) {
                ps.setString(8, pr.getNgaylap());
            } else {
               JOptionPane.showMessageDialog(rootPane, "Validate ngày lập bị sai format");
               return false;
            }
            return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        MA = new javax.swing.JTextField();
        NV = new javax.swing.JComboBox<>();
        SP = new javax.swing.JComboBox<>();
        SL = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQLSP = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Ngay = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Gia = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        KH2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã Hóa Đơn:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tên Sản Phẩm:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Tên Khách Hàng:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Số Lượng:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Tên Nhân Viên:");

        NV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                NVItemStateChanged(evt);
            }
        });
        NV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NVActionPerformed(evt);
            }
        });

        SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPActionPerformed(evt);
            }
        });

        tblQLSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Tên Nhân Viên", "Tên Khách Hàng:", "Tên Sản Phẩm", "Số Lượng:", "Giá:", "Thành Tiền", "Ngày lập"
            }
        ));
        tblQLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQLSP);

        btnSave.setBackground(new java.awt.Color(255, 102, 0));
        btnSave.setIcon(new javax.swing.ImageIcon("C:\\DriveC\\Java\\Project_CMS\\QLCH\\src\\images\\save-20.png")); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 102, 0));
        btnDelete.setIcon(new javax.swing.ImageIcon("C:\\DriveC\\Java\\Project_CMS\\QLCH\\src\\images\\delete-20.png")); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 102, 0));
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\DriveC\\Java\\Project_CMS\\QLCH\\src\\images\\search-20.png")); // NOI18N
        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 0));
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\DriveC\\Java\\Project_CMS\\QLCH\\src\\images\\load-20.png")); // NOI18N
        jButton2.setText("Load");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Ngày Lập:");

        Ngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NgayActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Giá");

        btnNew.setBackground(new java.awt.Color(255, 102, 0));
        btnNew.setIcon(new javax.swing.ImageIcon("C:\\DriveC\\Java\\Project_CMS\\QLCH\\src\\images\\empty-trash-20.png")); // NOI18N
        btnNew.setText("Clear");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        KH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KH2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Ngay, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MA, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SL, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NV, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SP, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Gia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(KH2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(MA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(NV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(KH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(SL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnNew)
                    .addComponent(btnDelete)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblQLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSPMouseClicked

    }//GEN-LAST:event_tblQLSPMouseClicked
    
  
    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
                if (check()) {
                    String a = Gia.getText();
                    String b = SL.getText();
                    int c = Integer.parseInt(a)*Integer.parseInt(b);
                    String s =String.valueOf(c);
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
                     dateFormat.setLenient(false);
                    
            hd sp = new hd();
            sp.setMahd(MA.getText());
            sp.setTennv(NV.getSelectedItem().toString());
//            sp.setTenkh(KH.getText());
            sp.setTenkh(KH2.getSelectedItem().toString());
            sp.setTensp(SP.getSelectedItem().toString());
            sp.setSl(SL.getText());
            sp.setGia(Gia.getText());
            sp.setThanhtien(s);
            sp.setNgaylap(Ngay.getText());
            if (saveHD(sp)) {
                JOptionPane.showMessageDialog(rootPane, "Lưu thành công!");
                listt.add(sp);
                clearValue();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Bạn đã nhập sai dữ liệu. Xin hãy nhập lại dữ liệu");
            }
            fillTable();
        }
    }//GEN-LAST:event_btnSaveActionPerformed
    public boolean deleteHD() {
        try {
            String ma = MA.getText();
            String sql = "DELETE FROM HOADON WHERE MAHD = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
        public boolean searchHD() {
        try {
            model.setRowCount(0);
                String sql = "SELECT * FROM HOADON WHERE MAHD LIKE ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, MA.getText());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                row.add(rs.getString(7));
                row.add(rs.getString(8));
                model.addRow(row);
            }
            tblQLSP.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        index = tblQLSP.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn hàng để xóa!");
        } else {
            this.deleteHD();
            listt.remove(index);
            loadDbToTable();
            this.btnNewActionPerformed(evt);
            JOptionPane.showMessageDialog(rootPane, "Xóa thành công!");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        searchHD();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loadDbToTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void NVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NVActionPerformed
        ArrayList<nv> list = new ArrayList<>();
        for(nv s : list ){
            System.out.println(s);
        }
    }//GEN-LAST:event_NVActionPerformed

    private void NVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_NVItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_NVItemStateChanged

    private void SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPActionPerformed
        ArrayList<product> li = new ArrayList<>();
        for(product p : li ){
            System.out.println(p);
        }
    }//GEN-LAST:event_SPActionPerformed
    
    private void clearValue() {
        MA.setText(null);
        NV.getSelectedItem().toString();
//        KH.setText(null);
        KH2.getSelectedItem().toString();
        SP.getSelectedItem().toString();
        SL.setText(null);
        Gia.setText(null);
        Ngay.setText(null);
    }
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        MA.setText(null);
        NV.getSelectedItem().toString();
//        KH.setText(null);
        KH2.getSelectedItem().toString();
        SP.getSelectedItem().toString();
        SL.setText(null);
        Gia.setText(null);
        Ngay.setText(null);
    }//GEN-LAST:event_btnNewActionPerformed

    private void KH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KH2ActionPerformed
         ArrayList<kh> li = new ArrayList<>();
        for(kh p : li ){
            System.out.println(p);
        }
    }//GEN-LAST:event_KH2ActionPerformed

    private void NgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NgayActionPerformed

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
            java.util.logging.Logger.getLogger(HOADON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HOADON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HOADON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HOADON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HOADON().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Gia;
    private javax.swing.JComboBox<String> KH2;
    private javax.swing.JTextField MA;
    private javax.swing.JComboBox<String> NV;
    private javax.swing.JTextField Ngay;
    private javax.swing.JTextField SL;
    private javax.swing.JComboBox<String> SP;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblQLSP;
    // End of variables declaration//GEN-END:variables
}
