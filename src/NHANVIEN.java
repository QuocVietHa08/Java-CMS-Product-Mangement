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
public class NHANVIEN extends javax.swing.JFrame {

    ArrayList<nv> list = new ArrayList<>();
    DefaultTableModel model;
    int index = 0;
    private Connection conn;
    public NHANVIEN() {
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
        list = getListNv();
        model = (DefaultTableModel) tblQLSP.getModel();
        loadDbToTable();
    }

        public boolean check() {
        if (txtId.getText().equals("") || txtName.getText().equals("") || txtPass.getText().equals("")
                ) {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đủ dữ liệu sau đó ấn Save");
            return false;
        } 
        return true;
    }

    public void loadDbToTable() {
        try {
            
            model.setRowCount(0);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM USERS");
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                model.addRow(row);
            }
            tblQLSP.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void fillTable() {
        model.setRowCount(0);
        for (nv s : list) {
            Object[] row = new Object[]{s.getId(), s.getName(), s.getPass()};
            model.addRow(row);
        }
    }

    public void showDetail(int index) {
        txtId.setText(list.get(index).getId());
        txtName.setText(list.get(index).getName());
        txtPass.setText(list.get(index).getPass());
    }

    public boolean deleteNv() {
        try {
            String ma = txtId.getText();
            String sql = "DELETE FROM USERS WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateNv() {
        try {
            String ma = txtId.getText();
            String sql = "UPDATE USERS SET NAME = ?, PASS = ? WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(3, ma);
            ps.setString(1, txtName.getText());
            ps.setString(2, txtPass.getText());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
   

    public boolean saveNv(nv pr) {
        String sql = "INSERT INTO USERS VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pr.getId());
            ps.setString(2, pr.getName());
            ps.setString(3, pr.getPass());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public ArrayList<nv> getListNv() {

        String sql = "SELECT * FROM USERS";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nv sp = new nv();
                sp.setId(rs.getString(1));
                sp.setName(rs.getString(2));
                sp.setPass(rs.getString(3));
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

        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQLSP = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(820, 520));

        btnNew.setBackground(new java.awt.Color(255, 102, 0));
        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Tài Khoản", "Mật Khẩu"
            }
        ));
        tblQLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQLSP);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã Nhân Viên:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tài Khoản:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Mật Khẩu:");

        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                                    .addComponent(txtPass)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(34, 34, 34)
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnUpdate)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void clearValue() {
        txtId.setText(null);
        txtName.setText(null);
        txtPass.setText(null);
    }
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtId.setText(null);
        txtName.setText(null);
        txtPass.setText(null);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (check()) {
            nv sp = new nv();
            sp.setId(txtId.getText());
            sp.setName(txtName.getText());
            sp.setPass(txtPass.getText());
            if (saveNv(sp)) {
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
            this.deleteNv();
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
            this.updateNv();
            nv sp = new nv();
            sp.setId(txtId.getText());
            sp.setName(txtName.getText());
            sp.setPass(txtPass.getText());
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

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed
        public boolean searchNv() {
        try {
            model.setRowCount(0);
                String sql = "SELECT * FROM USERS WHERE ID LIKE ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, txtId.getText());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                model.addRow(row);
            }
            tblQLSP.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

            this.searchNv();      
           
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loadDbToTable();
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
            java.util.logging.Logger.getLogger(NHANVIEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NHANVIEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NHANVIEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NHANVIEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NHANVIEN().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPass;
    // End of variables declaration//GEN-END:variables
}
