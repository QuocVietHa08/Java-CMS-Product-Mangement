/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class SANPHAM extends javax.swing.JFrame {

    ArrayList<product> list = new ArrayList<>();
    DefaultTableModel model;
    int index = 0;
    private Connection conn;
    public SANPHAM() {
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
        list = getListProduct();
        model = (DefaultTableModel) tblQLSP.getModel();
        loadDbToTable();
    }

        public boolean check() {
        if (txtMa.getText().equals("") || txtTen.getText().equals("") || txtGia.getText().equals("")
                || txtSL.getText().equals("") || txtDV.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đủ dữ liệu sau đó ấn Save");
            return false;
        } 
        return true;
    }

    public void loadDbToTable() {
        try {
            
            model.setRowCount(0);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PRODUCT");
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                model.addRow(row);
            }
            tblQLSP.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void fillTable() {
        model.setRowCount(0);
        for (product s : list) {
            Object[] row = new Object[]{s.getMasp(), s.getTensp(), s.getGia(), s.getSoluong(), s.getDv()};
            model.addRow(row);
        }
    }

    public void showDetail(int index) {
        txtMa.setText(list.get(index).getMasp());
        txtTen.setText(list.get(index).getTensp());
        txtGia.setText(list.get(index).getGia());
        txtSL.setText(list.get(index).getSoluong());
        txtDV.setText(list.get(index).getDv());
    }

    public boolean deleteProduct() {
        try {
            String ma = txtMa.getText();
            String sql = "DELETE FROM PRODUCT WHERE MASP = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateProduct() {
        try {
            String ma = txtMa.getText();
            String sql = "UPDATE PRODUCT SET TENSP = ?, GIA = ?, SOLUONG = ?, DV = ? WHERE MASP = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(5, ma);
            ps.setString(1, txtTen.getText());
            ps.setString(2, txtGia.getText());
            ps.setString(3, txtSL.getText());
            ps.setString(4, txtDV.getText());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
        public boolean searchProduct(product pr) {
        try {
                String sql = "SELECT * FROM PRODUCT WHERE MASP LIKE ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, txtMa.getText());
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(rootPane, "ma ton tai");
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean saveProduct(product pr) {
        String sql = "INSERT INTO PRODUCT VALUES(?,?,?,?,?)";
        try {
            String s = "SELECT * FROM PRODUCT WHERE MASP = ?";
                PreparedStatement p = conn.prepareStatement(s);
                p.setString(1, txtMa.getText());
                ResultSet rs = p.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(rootPane, "Mã sản phẩm đã tồn tại");
            }else{
                PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pr.getMasp());
            ps.setString(2, pr.getTensp());
            ps.setString(3, pr.getGia());
            ps.setString(4, pr.getSoluong());
            ps.setString(5, pr.getDv());
            return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<product> getListProduct() {

        String sql = "SELECT * FROM PRODUCT";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product sp = new product();
                sp.setMasp(rs.getString(1));
                sp.setTensp(rs.getString(2));
                sp.setGia(rs.getString(3));
                sp.setSoluong(rs.getString(4));
                sp.setDv(rs.getString(5));
                list.add(sp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        txtSL = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQLSP = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtDV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã Sản Phẩm:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tên Sản Phẩm:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Giá:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Số Lượng:");

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(255, 102, 0));
        btnNew.setIcon(new javax.swing.ImageIcon("C:\\DriveC\\Java\\Project_CMS\\QLCH\\src\\images\\empty-trash-20.png")); // NOI18N
        btnNew.setText("Clear");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

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

        btnUpdate.setBackground(new java.awt.Color(255, 102, 0));
        btnUpdate.setIcon(new javax.swing.ImageIcon("C:\\DriveC\\Java\\Project_CMS\\QLCH\\src\\images\\update-20.png")); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        tblQLSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Sản phẩm", "Tên Sản Phẩm", "Giá", "Số Lượng", "Đơn Vị Tính"
            }
        ));
        tblQLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQLSP);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Đơn Vị Tính:");

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\DriveC\\Java\\Project_CMS\\QLCH\\src\\images\\product-200.png")); // NOI18N

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(198, 198, 198)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(txtTen)
                                    .addComponent(txtGia)
                                    .addComponent(txtSL)
                                    .addComponent(txtDV))))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed
    private void clearValue() {
        txtMa.setText(null);
        txtTen.setText(null);
        txtGia.setText(null);
        txtSL.setText(null);
        txtDV.setText(null);
    }
    
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtMa.setText(null);
        txtTen.setText(null);
        txtGia.setText(null);
        txtSL.setText(null);
        txtDV.setText(null);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (check()) {
            product sp = new product();
            sp.setMasp(txtMa.getText());
            sp.setTensp(txtTen.getText());
            sp.setGia(txtGia.getText());
            sp.setSoluong(txtSL.getText());
            sp.setDv(txtDV.getText());
            if (saveProduct(sp)) {
                JOptionPane.showMessageDialog(rootPane, "Lưu thành công!");
                list.add(sp);
                clearValue();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Nhập lại dữ liệu");
            }
            fillTable();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        index = tblQLSP.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn hàng để xóa!");
        } else {
            this.deleteProduct();
            list.remove(index);
            loadDbToTable();
            this.btnNewActionPerformed(evt);
            JOptionPane.showMessageDialog(rootPane, "Xóa thành công!");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        index = tblQLSP.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn hàng cần update!");
        } else {
            list.remove(index);
            this.updateProduct();
            product sp = new product();
            sp.setMasp(txtMa.getText());
            sp.setTensp(txtTen.getText());
            sp.setGia(txtGia.getText());
            sp.setSoluong(txtSL.getText());
            sp.setDv(txtDV.getText());
            list.add(sp);
            fillTable();
            JOptionPane.showMessageDialog(this, "Update thành công!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblQLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSPMouseClicked
        try {
            index = tblQLSP.getSelectedRow();
            showDetail(index);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tblQLSPMouseClicked
        public boolean searchProduct() {
        try {
            model.setRowCount(0);
                String sql = "SELECT * FROM PRODUCT WHERE MASP LIKE ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, txtMa.getText());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                model.addRow(row);
            }
            tblQLSP.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        searchProduct();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         loadDbToTable();   // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(SANPHAM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SANPHAM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SANPHAM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SANPHAM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SANPHAM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTextField txtDV;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

   
}
