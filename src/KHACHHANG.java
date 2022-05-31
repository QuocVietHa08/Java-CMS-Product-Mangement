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
public class KHACHHANG extends javax.swing.JFrame {

    ArrayList<kh> list = new ArrayList<>();
    DefaultTableModel model;
    int index = 0;
    private Connection conn;
    public KHACHHANG() {
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
        list = getListKh();
        model = (DefaultTableModel) tblQLSP.getModel();
        loadDbToTable();
    }

            public boolean check() {
        if (txtMa.getText().equals("") || txtTen.getText().equals("") || txtDiachi.getText().equals("")
                || txtSdt.getText().equals("") ) {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đủ dữ liệu sau đó ấn Save");
            return false;
        } 
        return true;
    }

    public void loadDbToTable() {
        try {
            
            model.setRowCount(0);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM KHACHHANG");
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                model.addRow(row);
            }
            tblQLSP.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void fillTable() {
        model.setRowCount(0);
        for (kh s : list) {
            Object[] row = new Object[]{s.getMakh(), s.getTenkh(), s.getDiachi(), s.getSdt()};
            model.addRow(row);
        }
    }

    public void showDetail(int index) {
        txtMa.setText(list.get(index).getMakh());
        txtTen.setText(list.get(index).getTenkh());
        txtDiachi.setText(list.get(index).getDiachi());
        txtSdt.setText(list.get(index).getSdt());
    }

    public boolean deleteKh() {
        try {
            String ma = txtMa.getText();
            String sql = "DELETE FROM KHACHHANG WHERE MAKH = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateKh() {
        try {
            String ma = txtMa.getText();
            String sql = "UPDATE KHACHHANG SET TENKH = ?, DIACHI = ?, SDT = ? WHERE MAKH = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(4, ma);
            ps.setString(1, txtTen.getText());
            ps.setString(2, txtDiachi.getText());
            ps.setString(3, txtSdt.getText());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
        public boolean searchKh(kh pr) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/qlch", "root", "");
                String sql = "SELECT * FROM KHACHHANG WHERE MAKH LIKE ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, txtMa.getText());
                ResultSet rs = ps.executeQuery();
                System.out.println(txtMa.getText());
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean saveKh(kh pr) {
        String sql = "INSERT INTO KHACHHANG VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pr.getMakh());
            ps.setString(2, pr.getTenkh());
            ps.setString(3, pr.getDiachi());
            ps.setString(4, pr.getSdt());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<kh> getListKh() {

        String sql = "SELECT * FROM KHACHHANG";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kh sp = new kh();
                sp.setMakh(rs.getString(1));
                sp.setTenkh(rs.getString(2));
                sp.setDiachi(rs.getString(3));
                sp.setSdt(rs.getString(4));
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
        txtDiachi = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQLSP = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(820, 520));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã Khách Hàng:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tên Khách Hàng:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Địa chỉ:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Số Điện Thoại:");

        txtDiachi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiachiActionPerformed(evt);
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

        jButton1.setBackground(new java.awt.Color(255, 102, 0));
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\DriveC\\Java\\Project_CMS\\QLCH\\src\\images\\search-20.png")); // NOI18N
        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblQLSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "Địa Chỉ", "Số Điện Thoại"
            }
        ));
        tblQLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQLSP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSdt)
                            .addComponent(txtDiachi)
                            .addComponent(txtTen)
                            .addComponent(txtMa))
                        .addGap(76, 76, 76))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addContainerGap(71, Short.MAX_VALUE))))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate)
                    .addComponent(jButton1))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiachiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiachiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiachiActionPerformed
    
    private void clearValue() {
       txtMa.setText(null);
       txtTen.setText(null);
       txtDiachi.setText(null);
       txtSdt.setText(null); 
    }
    
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtMa.setText(null);
        txtTen.setText(null);
        txtDiachi.setText(null);
        txtSdt.setText(null);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (check()) {
            kh sp = new kh();
            sp.setMakh(txtMa.getText());
            sp.setTenkh(txtTen.getText());
            sp.setDiachi(txtDiachi.getText());
            sp.setSdt(txtSdt.getText());
            if (saveKh(sp)) {
                JOptionPane.showMessageDialog(rootPane, "Lưu thành công!");
                list.add(sp);
                clearValue();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Lỗi");
            }
            fillTable();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        index = tblQLSP.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn hàng để xóa!");
        } else {
            this.deleteKh();
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
            this.updateKh();
            kh sp = new kh();
            sp.setMakh(txtMa.getText());
            sp.setTenkh(txtTen.getText());
            sp.setDiachi(txtDiachi.getText());
            sp.setSdt(txtSdt.getText());
            list.add(sp);
            fillTable();
            JOptionPane.showMessageDialog(this, "Update thành công!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblQLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSPMouseClicked
        try {
            index = tblQLSP.getSelectedRow();
            showDetail(index);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tblQLSPMouseClicked

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
            java.util.logging.Logger.getLogger(KHACHHANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KHACHHANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KHACHHANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KHACHHANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KHACHHANG().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
