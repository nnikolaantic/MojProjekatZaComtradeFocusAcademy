/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package applicationFrames;

import databaseConnection.DatabaseConnection;
import models.Profesori;
import models.Ucenici;
import ChatApp.StudentChat_MyClient;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author Anta
 */
public class StudentsMenu extends javax.swing.JFrame {

    String username;
    String fileName =  null;
    byte[] person_image = null;
    
    public StudentsMenu() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public StudentsMenu(String username){
        this.username = username;
        initComponents();
        setLocationRelativeTo(null);
        helloLbl.setText("Welcome " + username);
        show_image();
        show_tabelaZaUcenika();
        show_profesori();
    }
    
    public ArrayList<Ucenici> listaZaStudenta(){
        ArrayList<Ucenici> listaZaStudenta = new ArrayList<>();
        try{
            
            String query = "SELECT subjects,profesor,finalGrade FROM students_table WHERE username = '" + username + "'" ;
            Statement st = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            
            Ucenici ucenik;
            
            while(rs.next()){
                ucenik = new Ucenici(rs.getString("subjects"),rs.getString("profesor"),rs.getString("finalGrade"));
                listaZaStudenta.add(ucenik);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return listaZaStudenta;
    }
    
    public void show_tabelaZaUcenika(){
        ArrayList<Ucenici> list = listaZaStudenta();
        
        DefaultTableModel model = (DefaultTableModel)jTableUcenik.getModel();
        Object[] row = new Object[3];
        for(int i=0;i<list.size();i++ ){
            row[0]=list.get(i).getSubjects();
            row[1]=list.get(i).getProfesor();
            row[2]=list.get(i).getFinalGrade();

            model.addRow(row);
        }
            
    }
    //-------------------------------------------------------
    public ArrayList<Profesori> ProfesoriList(){
        ArrayList<Profesori> ProfesoriList = new ArrayList<>();
        try{

            String predmet = jComboBoxSubjects.getSelectedItem().toString();
            
            String query = "SELECT name FROM zaposlenitable WHERE subjects = '"  + predmet +"'" ;
            Statement st = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            
            Profesori Profesor;
            
            while(rs.next()){
                Profesor = new Profesori(rs.getString("name"));
                ProfesoriList.add(Profesor);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return ProfesoriList;
    }
    
    public void show_profesori(){
        ArrayList<Profesori> list = ProfesoriList();
        
        DefaultTableModel model = (DefaultTableModel)jTableProfesori.getModel();
        Object[] row = new Object[1];
        for(int i=0;i<list.size();i++ ){
            row[0]=list.get(i).getName();

            model.addRow(row);
        }
            
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        helloLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUcenik = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProfesori = new javax.swing.JTable();
        jComboBoxSubjects = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        lbl_img = new javax.swing.JLabel();
        btnChoose = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuChat = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(73, 118, 186));

        helloLbl.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        helloLbl.setText("-----------------------------------------------");

        jTableUcenik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subjects", "Proffesor", "Final Grade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableUcenik);

        jTableProfesori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Professors"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableProfesori);

        jComboBoxSubjects.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C", "C++", "JAVA", "Python" }));
        jComboBoxSubjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSubjectsActionPerformed(evt);
            }
        });

        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel2.setText("Add new Subject  :");

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnChoose.setText("Change Image");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(helloLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 274, Short.MAX_VALUE)
                            .addComponent(lbl_img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnChoose)))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(helloLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBoxSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChoose)))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(31, 69, 126));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.PNG"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuChat.setText("Chat");
        menuChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuChatMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuChat);

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(fileName).getImage()
            .getScaledInstance
            (lbl_img.getWidth(),lbl_img.getHeight(),Image.SCALE_SMOOTH ));
        lbl_img.setIcon(imageIcon);
        try {
            File image = new File (fileName);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum ; (readNum=fis.read(buf)) != -1;){
                bos.write(buf, 0, readNum);
            }
            person_image = bos.toByteArray();

            String query = "UPDATE students_table SET images = ? WHERE username = '" + username + "'";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);
            pst.setBytes(1, person_image);
            pst.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnChooseActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int i = jTableUcenik.getSelectedRow();
        TableModel model = jTableUcenik.getModel();

        int delete = JOptionPane.showConfirmDialog(null, "Do you really want to delete selected row?", "Delete",JOptionPane.YES_NO_OPTION);
        if(delete==0){

            try {
                String subjects = model.getValueAt(i,0).toString();
                String profesor = model.getValueAt(i,1).toString();
                String query = "DELETE FROM students_table WHERE username = '" + username + "'" + " && profesor = '" + profesor + "'"  + " && subjects = '" + subjects + "'" ;
                PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

                pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(StudentsMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "Deleted  Sucessfully!");
            DefaultTableModel model1 = (DefaultTableModel) jTableUcenik.getModel();
            model1.setRowCount(0);
            show_tabelaZaUcenika();
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        try{
            String query1 = "SELECT * FROM students_table WHERE username = '" + username + "'";
            Statement st = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query1);

            String firstname = "";
            String lastname = "";
            String gender = "";
            String address = "";
            byte[] person_image =  null;
            String password = "";

            while (rs.next()){
                firstname = rs.getNString("firstname");
                lastname = rs.getString("lastname");
                gender = rs.getString("gender");
                address = rs.getString("address");
                person_image = rs.getBytes("images");
                password = rs.getString("password");
            }

            String query = "INSERT INTO students_table (firstname,lastname,gender,address,subjects,profesor,images,username,password)VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

            pst.setString(1,firstname);
            pst.setString(2,lastname);
            pst.setString(3,gender);
            pst.setString(4,address);
            pst.setString(5, jComboBoxSubjects.getSelectedItem().toString());

            int i = jTableProfesori.getSelectedRow();
            TableModel model = jTableProfesori.getModel();
            if(i == -1){
                JOptionPane.showMessageDialog(null,"Molimo, izaberite Profesora");
                return;
            }else
            pst.setString(6,model.getValueAt(i,0).toString());

            pst.setBytes(7,person_image);
            pst.setString(8, username);
            pst.setString(9, password);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Subject Added Sucessfully!");

            DefaultTableModel model1 = (DefaultTableModel) jTableUcenik.getModel();
            model1.setRowCount(0);
            show_tabelaZaUcenika();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jComboBoxSubjectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSubjectsActionPerformed
        if(ProfesoriList().isEmpty()){
            JOptionPane.showMessageDialog(null, "Qurently there are no profesors for this subject!");
        }

        DefaultTableModel model = (DefaultTableModel) jTableProfesori.getModel();
        model.setRowCount(0);
        show_profesori();
    }//GEN-LAST:event_jComboBoxSubjectsActionPerformed

    private void menuChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuChatMouseClicked
        try {
            Socket s = new Socket("localhost",2089);

//            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(username);

            String i = new DataInputStream(s.getInputStream()).readUTF();
            if(i.equals("You Are Alredy Registered....!!")){
                JOptionPane.showMessageDialog(this,"Your chat is already open");
            }else{
                new StudentChat_MyClient(username,s).setVisible(true);
                this.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuChatMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(StudentsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(StudentsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(StudentsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(StudentsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new StudentsMenu().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel helloLbl;
    private javax.swing.JComboBox<String> jComboBoxSubjects;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProfesori;
    private javax.swing.JTable jTableUcenik;
    private javax.swing.JLabel lbl_img;
    private javax.swing.JMenu menuChat;
    // End of variables declaration//GEN-END:variables

    private void show_image() {
        try{
            String query1 = "SELECT images FROM students_table WHERE username = '" + username + "'";
            Statement st = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query1);
            
            byte[] person_image =  null; 
            
            while (rs.next()){
                person_image = rs.getBytes("images");
            }
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(person_image).getImage()
                    .getScaledInstance(lbl_img.getWidth()
                    ,lbl_img.getHeight(),Image.SCALE_SMOOTH ));
            lbl_img.setIcon(imageIcon); 
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
