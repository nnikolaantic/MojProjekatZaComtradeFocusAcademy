/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationFrames;

import databaseConnection.DatabaseConnection;
import models.Ucenici;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Anta
 */
public class EmpProffesor extends javax.swing.JFrame {
    
    String username,clientIDFromList = "";
    DataInputStream din;
    DataOutputStream dout;
    DefaultListModel dlm;
    //-----------------------------------------------
    String fileName =  null;
    byte[] person_image = null;
    
    public EmpProffesor(String username,Socket s) {
        this.username = username;
        
        try {
            initComponents();
            Toolkit toolkit = getToolkit();
            Dimension size = toolkit.getScreenSize();
            setLocationRelativeTo(null);
            show_newUcenici();
            dlm = new DefaultListModel();
            UL.setModel(dlm);
            idLabel.setText(username);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            new EmpProffesor.Read().start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public EmpProffesor() {
//        initComponents();
//        setLocationRelativeTo(null);
//        show_newUcenici();
//    }
    
    class Read extends Thread{
        @Override
        public void run(){
            //citanje i punjenje UL 
            while(true){
                try {
                    String m  = din.readUTF();
                    //odsecanje teksta koji je sluzio za razvrstavanje
                    if(m.contains(":;.,/=")){
                        m=m.substring(6);
                        dlm.clear();
                        StringTokenizer st = new StringTokenizer(m,",");
                        while(st.hasMoreTokens()){
                            String u = st.nextToken();
                            if(!username.equals(u))
                                dlm.addElement(u);
                        }
                    }else{
                        DefaultTableModel model1 = (DefaultTableModel) jTable_Ucenici.getModel();
                        model1.setRowCount(0);
                        show_newUcenici();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
        public ArrayList<Ucenici> newUceniciList(){
        String view = jComboBoxSubject.getSelectedItem().toString();
        switch(view){
                case "C":
                     view = "view_studenti_table_c";
                     break;
                case "C++":
                     view = "view_studenti_table_cplusplus";
                     break;
                case "JAVA":
                     view = "view_studenti_table_java";
                     break;     
                case "Python":
                     view = "view_studenti_table_python";
                     break;   
            }
            
        ArrayList<Ucenici> newUceniciList = new ArrayList<>();
        try{

            String query = "SELECT * FROM "  + view ;

            Statement st = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            Ucenici newUcenik;
            while(rs.next()){
                newUcenik = new Ucenici(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastname")
                                           ,rs.getString("gender"),rs.getString("address")
                                           ,rs.getString("subjects"),rs.getString("profesor")
                                           ,rs.getString("finalGrade"),rs.getBytes("images"));
                newUceniciList.add(newUcenik);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return newUceniciList;
    }
     public void show_newUcenici(){
        ArrayList<Ucenici> list = newUceniciList();
        DefaultTableModel model = (DefaultTableModel) jTable_Ucenici.getModel();
        Object[] row = new Object[8];
        for(int i=0;i<list.size();i++ ){
            row[0]=list.get(i).getId();
            row[1]=list.get(i).getFirstname();
            row[2]=list.get(i).getLastname();
            row[3]=list.get(i).getGender();
            row[4]=list.get(i).getAddress();
            row[5]=list.get(i).getSubjects();
            row[6]=list.get(i).getProfesor();
            row[7]=list.get(i).getFinalGrade();
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

        subject = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Ucenici = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxSubject = new javax.swing.JComboBox<>();
        jLabelImeTabele = new javax.swing.JLabel();
        SAVE = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        firstname = new javax.swing.JTextField();
        lastname = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        profesor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        studentID = new javax.swing.JLabel();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        jComboBoxPredmet = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Address = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        reset = new javax.swing.JButton();
        finalGrade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lbl_img = new javax.swing.JLabel();
        btnChoose = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        UL = new javax.swing.JList<>();
        idLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(95, 95, 161));

        jTable_Ucenici.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "FirstName", "LastName", "Gender", "Address", "Subject", "Profesor", "Final Grade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Ucenici.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_UceniciMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Ucenici);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Izaberite tabelu");

        jComboBoxSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C", "C++", "JAVA", "Python" }));
        jComboBoxSubject.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxSubjectItemStateChanged(evt);
            }
        });

        jLabelImeTabele.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelImeTabele.setText("C");

        SAVE.setText("SAVE");
        SAVE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAVEActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("UPDATE");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        Delete.setText("DELETE");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setText("Student ID");

        studentID.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        buttonGroup1.add(male);
        male.setText("Male");
        male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleActionPerformed(evt);
            }
        });

        buttonGroup1.add(female);
        female.setText("Female");
        female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleActionPerformed(evt);
            }
        });

        jComboBoxPredmet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C", "C++", "JAVA", "Python" }));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setText("Firstname");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setText("Lastname");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setText("Gender");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setText("Subjects");

        Address.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Address.setText("Address");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setText("Professor");

        reset.setText("RESET");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel8.setText("Final Grade");

        btnChoose.setText("Choose");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("Table for subject:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(24, 24, 24)
                                .addComponent(jComboBoxSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_img, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(studentID, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(jLabel4)
                                .addGap(48, 48, 48))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(female)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(male)
                                    .addComponent(jLabel5))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(86, 86, 86)
                                        .addComponent(Address)
                                        .addGap(40, 40, 40))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBoxPredmet, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(profesor, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(finalGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(79, 79, 79)
                                .addComponent(jLabel7)
                                .addGap(61, 61, 61)
                                .addComponent(jLabel8)
                                .addGap(37, 37, 37))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelImeTabele, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(SAVE, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(94, 94, 94)
                                    .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(93, 93, 93)
                                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelImeTabele, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(Address)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(28, 28, 28)
                        .addComponent(lbl_img, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(studentID, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(male)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(female))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(profesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxPredmet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finalGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SAVE, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
        );

        jPanel3.setBackground(new java.awt.Color(56, 56, 110));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        UL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        UL.setEnabled(false);
        jScrollPane3.setViewportView(UL);

        idLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        idLabel.setForeground(new java.awt.Color(204, 204, 204));
        idLabel.setText("................................");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("You are loged in as :");

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_businessman_30px.png"))); // NOI18N
        jLabel10.setText("Online users:");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/samoLogo1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(idLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)))
                .addGap(31, 31, 31))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(idLabel)
                .addGap(50, 50, 50)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxSubjectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxSubjectItemStateChanged
         DefaultTableModel model = (DefaultTableModel) jTable_Ucenici.getModel();
            model.setRowCount(0);
            show_newUcenici();
            jLabelImeTabele.setText(jComboBoxSubject.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBoxSubjectItemStateChanged

    private void jTable_UceniciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_UceniciMouseClicked
        int i = jTable_Ucenici.getSelectedRow();
        TableModel model = jTable_Ucenici.getModel();
        studentID.setText(model.getValueAt(i,0).toString());
        firstname.setText(model.getValueAt(i,1).toString());
        lastname.setText(model.getValueAt(i,2).toString());
        String sex = model.getValueAt(i, 3).toString();
            if(sex.equals("Male")){
                male.setSelected(true);
            }else{
                female.setSelected(true);
            }
        address.setText(model.getValueAt(i,4).toString());
        String subject1 = model.getValueAt(i, 5).toString();
            switch(subject1){
                case "C":
                     jComboBoxPredmet.setSelectedIndex(0);
                     break;
                case "C++":
                     jComboBoxPredmet.setSelectedIndex(1);
                     break;
                case "JAVA":
                     jComboBoxPredmet.setSelectedIndex(2);
                     break;     
                case "Python":
                     jComboBoxPredmet.setSelectedIndex(3);
                     break;   
            }
        profesor.setText(model.getValueAt(i,6).toString());
        finalGrade.setText(model.getValueAt(i,7).toString());
        if(newUceniciList().get(i).getImage() == null){
            lbl_img.setText("No image found");
        }else{
            byte[] img = (newUceniciList().get(i).getImage());
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage()
                    .getScaledInstance
                    (lbl_img.getWidth(),lbl_img.getHeight(),Image.SCALE_SMOOTH ));
            lbl_img.setIcon(imageIcon); 
        }
        
        
    }//GEN-LAST:event_jTable_UceniciMouseClicked

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed

        try{
        String value  = studentID.getText();
        String query = "UPDATE students_table SET firstname = ?,lastname = ?,gender = ?,address = ?,subjects = ? ,profesor = ?,finalGrade = ?,images = ?"
                        + " WHERE id =" + value;
        
        PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);
        pst.setString(1, firstname.getText());
        pst.setString(2, lastname.getText());
        String gender  = "";
        if(male.isSelected()){
            gender="Male";
        }
        if(female.isSelected()){
            gender="Female";
        }
        pst.setString(3, gender);
        
        pst.setString(4, address.getText());
        
        String course;
        course = jComboBoxPredmet.getSelectedItem().toString();
        pst.setString(5, course);
        
        pst.setString(6,profesor.getText());
        pst.setString(7, finalGrade.getText());    
        pst.setBytes(8,person_image);
        
        pst.executeUpdate();
        dout.writeUTF("ChangeNotificationFromClient");
        
            JOptionPane.showMessageDialog(null, "Updated Sucessfully!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void SAVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAVEActionPerformed
        
        try{
            String query = "INSERT INTO students_table (firstname,lastname,gender,address,subjects,profesor,finalGrade,images,username,password)VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

            pst.setString(1, firstname.getText());
            pst.setString(2,lastname.getText());
//            if(male.isSelected()){
//                gender="Male";
//            }
//            if(female.isSelected()){
//                gender="Female";
//            }
//            pst.setString(3, gender);
            pst.setString(3, male.isSelected() ? "Male" : "Female");
            pst.setString(4, address.getText());
            pst.setString(5, jComboBoxPredmet.getSelectedItem().toString());
            pst.setString(6, profesor.getText());
            
//            if(finalGrade.getText() == null){
//                pst.setString(7, "Not graded");
//            }else
//                pst.setString(7, finalGrade.getText());
            String finalGradeString = finalGrade.getText() == null ? "Not graded" : finalGrade.getText();
            pst.setString(7, finalGradeString);
            
            pst.setBytes(8,person_image);
            pst.setString(9, firstname.getText()+ "_student");
            pst.setString(10, firstname.getText()+ "_student");
            
            pst.executeUpdate();
                
            dout.writeUTF("ChangeNotificationFromClient");
                
            JOptionPane.showMessageDialog(null, "Inserted Sucessfully!");
                    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_SAVEActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed

        int delete = JOptionPane.showConfirmDialog(null, "Do you really want to delete selected row?", "Delete",JOptionPane.YES_NO_OPTION);
        if(delete==0){  
        try{
            int row = jTable_Ucenici.getSelectedRow();
            String value = (jTable_Ucenici.getModel().getValueAt(row,0).toString());
            String query="DELETE FROM students_table WHERE id ="+ value;
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);
            pst.executeUpdate();
            
            dout.writeUTF("ChangeNotificationFromClient");
            
            JOptionPane.showMessageDialog(null, "Deleted  Sucessfully!");
            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        studentID.setText("");
        firstname.setText("");
        lastname.setText("");
        buttonGroup1.clearSelection();
        address.setText("");
        jComboBoxPredmet.setSelectedIndex(0);
        profesor.setText("");
        finalGrade.setText("");
        lbl_img.setIcon(null);
    }//GEN-LAST:event_resetActionPerformed

    private void maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleActionPerformed
        maleOrFemale();
    }//GEN-LAST:event_maleActionPerformed

    private void femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleActionPerformed
        maleOrFemale();
    }//GEN-LAST:event_femaleActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String i = "mkoihgteazdcvgyhujb096785542AXTY";
        
        try {
            dout.writeUTF(i);
            this.dispose();
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        fileName = f.getAbsolutePath();
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
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnChooseActionPerformed

//    /**
//     * @param args the command line arguments
//     */
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
//            java.util.logging.Logger.getLogger(EmpProffesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EmpProffesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EmpProffesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EmpProffesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EmpProffesor().setVisible(true);
//            }
//        });
//    }
    private void maleOrFemale() {
        //code kada bi iz baze izvlacio Default slike za male i female
//        String id= "";
//            if(male.isSelected()){
//                id = "1";
//            }else{
//                id = "2";
//            }
//            String query = "SELECT images FROM images_table WHERE id = " + id;
//            
//            try {
//                Statement st = DatabaseConnection.getConnection().createStatement();
//                ResultSet rs = st.executeQuery(query);
//                while (rs.next()){
//                person_image = rs.getBytes("images");
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(person_image).getImage()
//                        .getScaledInstance
//                        (lbl_img.getWidth(),lbl_img.getHeight(),Image.SCALE_SMOOTH ));
//                lbl_img.setIcon(imageIcon); 
//                }
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, ex);
//            

        // za izvlacenje default slika iz sorce/Images... 
            if(male.isSelected()){
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/Images/man.png")).getImage()
                        .getScaledInstance
                        (lbl_img.getWidth(),lbl_img.getHeight(),Image.SCALE_SMOOTH ));
                lbl_img.setIcon(imageIcon);
                String path = getClass().getResource("/Images/man.png").getFile();
                File fi = new File(path);
                try {
                    person_image = Files.readAllBytes(fi.toPath());
                            } catch (IOException ex) {
                    Logger.getLogger(StudentMakeAcc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/Images/woman.png")).getImage()
                        .getScaledInstance
                        (lbl_img.getWidth(),lbl_img.getHeight(),Image.SCALE_SMOOTH ));
                lbl_img.setIcon(imageIcon); 
                String path = getClass().getResource("/Images/woman.png").getFile();
                File fi = new File(path);
                try {
                    person_image = Files.readAllBytes(fi.toPath());
                            } catch (IOException ex) {
                    Logger.getLogger(StudentMakeAcc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Address;
    private javax.swing.JButton Delete;
    private javax.swing.JButton SAVE;
    private javax.swing.JList<String> UL;
    private javax.swing.JTextField address;
    private javax.swing.JButton btnChoose;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JRadioButton female;
    private javax.swing.JTextField finalGrade;
    private javax.swing.JTextField firstname;
    private javax.swing.JLabel idLabel;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxPredmet;
    private javax.swing.JComboBox<String> jComboBoxSubject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImeTabele;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_Ucenici;
    private javax.swing.JTextField lastname;
    private javax.swing.JLabel lbl_img;
    private javax.swing.JRadioButton male;
    private javax.swing.JTextField profesor;
    private javax.swing.JButton reset;
    private javax.swing.JLabel studentID;
    private javax.swing.JTextField subject;
    // End of variables declaration//GEN-END:variables
}
