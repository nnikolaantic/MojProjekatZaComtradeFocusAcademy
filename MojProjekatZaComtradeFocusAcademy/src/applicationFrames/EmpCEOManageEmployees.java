/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationFrames;

import databaseConnection.DatabaseConnection;
import models.NoviZaposleni;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Anta
 */
public class EmpCEOManageEmployees extends javax.swing.JFrame {

    /**
     * Creates new form ManageEmployees
     */
    
    String username;
    public EmpCEOManageEmployees(String username) {
        this.username = username;
        
        initComponents();
        setLocationRelativeTo(null);
//        show_newUsers(); ovo se ne mora odmah uraditi jer to odmah uradi timer
        timer.schedule(new Refresh (), 0, 10000);
    }
    Timer timer = new Timer();
    class Refresh extends TimerTask {
        @Override
        public void run() {
            DefaultTableModel model = (DefaultTableModel) jTableDispleyNewEmployee.getModel();
            model.setRowCount(0);
            show_newUsers();
        }
    }

    public ArrayList<NoviZaposleni> noviZaposleniList(){
        ArrayList<NoviZaposleni> noviZaposleniList = new ArrayList<>();
        try{

            String query = "SELECT * FROM logintable";

            Statement st = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            NoviZaposleni noviZaposleni;
            while(rs.next()){
                noviZaposleni = new NoviZaposleni(rs.getInt("id"),rs.getString("username"),rs.getString("password")
                                           ,rs.getString("rank"));
                noviZaposleniList.add(noviZaposleni);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return noviZaposleniList;
    }
     public void show_newUsers(){
        ArrayList<NoviZaposleni> list = noviZaposleniList();
        DefaultTableModel model = (DefaultTableModel)jTableDispleyNewEmployee.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<list.size();i++ ){
            row[0]=list.get(i).getId();
            row[1]=list.get(i).getUsername();
            row[2]=list.get(i).getPassword();
            row[3]=list.get(i).getRank();
            model.addRow(row);
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
        jButtonSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDispleyNewEmployee = new javax.swing.JTable();
        jButtonUpdate = new javax.swing.JButton();
        jComboModifikatorPristupa = new javax.swing.JComboBox<>();
        DELETE = new javax.swing.JButton();
        jTextUsername = new javax.swing.JTextField();
        jTextPassword = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        USERNAME = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextID = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(95, 95, 161));

        jButtonSave.setText("SAVE");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jTableDispleyNewEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Pasword", "Rank"
            }
        ));
        jTableDispleyNewEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDispleyNewEmployeeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDispleyNewEmployee);

        jButtonUpdate.setText("UPDATE");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jComboModifikatorPristupa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CEO", "MANAGER", "Employee", "-----" }));
        jComboModifikatorPristupa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboModifikatorPristupaActionPerformed(evt);
            }
        });

        DELETE.setText("DELETE");
        DELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETEActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("ID");

        USERNAME.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        USERNAME.setText("USERNAME");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("PASSWORD");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("RANK");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jTextID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(USERNAME)
                                .addGap(10, 10, 10)))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel3)
                                .addGap(83, 83, 83)
                                .addComponent(jLabel4)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(jComboModifikatorPristupa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(43, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(USERNAME)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboModifikatorPristupa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
        );

        jPanel2.setBackground(new java.awt.Color(56, 56, 110));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/samoLogo1.png"))); // NOI18N
        jLabel2.setText("logoo");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/zaposleni2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboModifikatorPristupaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboModifikatorPristupaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboModifikatorPristupaActionPerformed

    private void jTableDispleyNewEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDispleyNewEmployeeMouseClicked
        int i = jTableDispleyNewEmployee.getSelectedRow();
        TableModel model = jTableDispleyNewEmployee.getModel();
        jTextID.setText(model.getValueAt(i,0).toString());
        jTextUsername.setText(model.getValueAt(i,1).toString());
        jTextPassword.setText(model.getValueAt(i,2).toString());
            
        String rank = model.getValueAt(i, 3).toString();
            switch(rank){
                case "CEO":
                     jComboModifikatorPristupa.setSelectedIndex(0);
                     break;
                case "MANAGER":
                     jComboModifikatorPristupa.setSelectedIndex(1);
                     break;
//                case "TeamLeader":
//                     jComboModifikatorPristupa.setSelectedIndex(2);
//                     break;     
                case "Employee":
                     jComboModifikatorPristupa.setSelectedIndex(2);
                     break;
                case "-----":
                     jComboModifikatorPristupa.setSelectedIndex(3);
                     break;        
            }
                                 
    }//GEN-LAST:event_jTableDispleyNewEmployeeMouseClicked

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
         try{
            String value = jTextID.getText();
            String query = "UPDATE logintable SET username = ?,password = ?,rank = ?"
                            + "WHERE id =" + value;
            
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);
            pst.setString(1, jTextUsername.getText());
            pst.setString(2, jTextPassword.getText());
          
            String rank;
            rank = jComboModifikatorPristupa.getSelectedItem().toString();
            pst.setString(3, rank);
            
            pst.executeUpdate();

            //Ovo je kod za refresovanje tabele(naredna 3 reda)
            DefaultTableModel model = (DefaultTableModel) jTableDispleyNewEmployee.getModel();
            model.setRowCount(0);
            show_newUsers();
            //----------------------
            JOptionPane.showMessageDialog(null, "Updated  Sucessfully!");
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }    
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEActionPerformed
        int delete = JOptionPane.showConfirmDialog(null, "Do you really want to delete selected row?", "Delete",JOptionPane.YES_NO_OPTION);
        if(delete==0){
        try{
            int row = jTableDispleyNewEmployee.getSelectedRow();
            String value = (jTableDispleyNewEmployee.getModel().getValueAt(row,0).toString());
            String query="DELETE FROM logintable WHERE id ="+ value;
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);
            pst.executeUpdate();
            
            DefaultTableModel model = (DefaultTableModel) jTableDispleyNewEmployee.getModel();
            model.setRowCount(0);
            show_newUsers();
            
            JOptionPane.showMessageDialog(null, "Deleted  Sucessfully!");
            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
        }
    }//GEN-LAST:event_DELETEActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
         try{
            if(jTextUsername.getText().isEmpty() || jTextPassword.getText().isEmpty() || jTextID.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Account must have ID , Username and Password");
                return;
            }
            String query = "INSERT INTO logintable(id,username,password,rank)VALUES(?,?,?,?)";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);
            int id  = Integer.parseInt(jTextID.getText());
            pst.setInt(1, id);
            pst.setString(2, jTextUsername.getText());
            pst.setString(3, jTextPassword.getText());
       
            String rank;
            rank = jComboModifikatorPristupa.getSelectedItem().toString();
            pst.setString(4, rank);

            pst.executeUpdate();
            
            DefaultTableModel model = (DefaultTableModel) jTableDispleyNewEmployee.getModel();
            model.setRowCount(0);
            show_newUsers();
            JOptionPane.showMessageDialog(null, "Inserted Sucessfully!");
                    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DELETE;
    private javax.swing.JLabel USERNAME;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboModifikatorPristupa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDispleyNewEmployee;
    private javax.swing.JTextField jTextID;
    private javax.swing.JTextField jTextPassword;
    private javax.swing.JTextField jTextUsername;
    // End of variables declaration//GEN-END:variables
}
