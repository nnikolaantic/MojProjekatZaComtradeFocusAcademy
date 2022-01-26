/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author Anta
 */
public class EmpServer extends javax.swing.JFrame {

    ServerSocket ss;
    HashMap clientHash = new HashMap();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    
    public EmpServer() {
        
        try {
            initComponents();
            setLocationRelativeTo(null);
            ss = new ServerSocket(2099);
            this.sStatus.setText("Server Started");
            
            new EmpAccept().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    class EmpAccept extends Thread{
        @Override
        public void run (){
            while(true){
                try {
                    Socket s = ss.accept();
                    //Uzima za password ono sto klijent unosi u clientRegister formi i povezuje taj pasvord u HashMapu sa novim Socketom
                    String username = new DataInputStream(s.getInputStream()).readUTF();
                    if(clientHash.containsKey(username)){
                        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                        dout.writeUTF("You Are Alredy Registered....!!");
                    }else{
                        clientHash.put(username, s);
                        Date date = new Date();
                        msgBox.append(username + " Joined! " + dateFormat.format(date) + "\n");
                        //----------------------------------
                        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                        dout.writeUTF("");
                        //-----------------------------------
                        Set k = clientHash.keySet();
                        Iterator itr = k.iterator();
                        while(itr.hasNext()){
                            String key = (String)itr.next();
                            if(!key.equalsIgnoreCase(username)){
                                try {
                                    new DataOutputStream(((Socket)clientHash.get(key))
                                            .getOutputStream()).writeUTF(username + " has Joined!");
                                } catch (Exception e) {
                                    clientHash.remove(key);
                                    msgBox.append(key  + ": removed!");
                                    new EmpServer.PrepareClientList().start();
                                }
                            }
                        }
                        //------------------------------------------
                        new EmpServer.MsgRead(s,username).start();
                        new EmpServer.PrepareClientList().start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class MsgRead extends Thread{
        Socket s;
        String username;
        
        MsgRead(Socket s, String username) {
            this.s = s;
            this.username = username;
        }
        @Override
        public void run(){
            while(!clientHash.isEmpty()){
                try {
                    String i = new DataInputStream(s.getInputStream()).readUTF();
                    if(i.equals("mkoihgteazdcvgyhujb096785542AXTY")){//prepoznavanje za remove
                        clientHash.remove(username);
                        String msgForRemove = username + ":  removed! \n";
                        Date date = new Date();
                        msgBox.append(username + ": removed! " + dateFormat.format(date) + " \n");
                        new PrepareClientList().start();
                        //kada smo smakli klijenta opet pokrecemo PrepareClientList()
                        //potom svim korisnicima saljemo novu listu klijenata bez uklonjenog korisnika
                        Set k = clientHash.keySet();

                        Iterator itr = k.iterator();
                        while(itr.hasNext()){
                            String key = (String)itr.next();
                            if(!key.equalsIgnoreCase(username)){
                                try {
                                    new DataOutputStream(((Socket)clientHash.get(key)).getOutputStream()).writeUTF(msgForRemove);
                                } catch (Exception e) {
                                    clientHash.remove(key);
                                    msgBox.append(key  + ": removed!");
                                    new PrepareClientList().start();
                                }
                            }
                        }
                    }else if(i.equals("ChangeNotificationFromClient")){//prepoznavanje za promenu na bazi
                        Date date = new Date();
                        msgBox.append(username + ": has made some changes  " + dateFormat.format(date) + "\n");// ovde mogu da dodam i current date
                        Set k = clientHash.keySet();
                        Iterator itr = k.iterator();
                        while(itr.hasNext()){
                            String key = (String)itr.next();
//                            if(!key.equalsIgnoreCase(username)){
                                try {
                                    new DataOutputStream(((Socket)clientHash.get(key))
                                            .getOutputStream()).writeUTF("ChangeNotificatonFromServer");
                                } catch (Exception e) {
                                    clientHash.remove(key);
                                    msgBox.append(key  + ": removed!");
                                    new EmpServer.PrepareClientList().start();
                                }
//                            }
//                            }
                        }
                    }
                    } catch (Exception e) {
//                        e.printStackTrace();
                        System.out.println("Klijent je izasao");
                }
            }
        }
            
        
    }
    class PrepareClientList extends Thread{
        @Override
        public void run(){
            try {
                String ids = "";
                Set k = clientHash.keySet();
                Iterator itr = k.iterator();
                while(itr.hasNext()){
                    //ovde pravimo spisak keyeva
                    String key = (String)itr.next();
                    ids += key + ",";  
                }
                if(ids.length()!=0)
                    ids=ids.substring(0,ids.length()-1);
                
                itr= k.iterator();
                
                while(itr.hasNext()){
                    String key =(String)itr.next();
                    try {
                         new DataOutputStream(((Socket)clientHash.get(key)).getOutputStream()).writeUTF(":;.,/="+ ids);
                    } catch (Exception e) {
                        clientHash.remove(key);
                        msgBox.append(key + ": removed!");
                    } 
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        msgBox = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        sStatus = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(95, 95, 161));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        msgBox.setColumns(20);
        msgBox.setRows(5);
        jScrollPane1.setViewportView(msgBox);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Server Stastus");

        sStatus.setText("...............................................");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/samoLogo1.png"))); // NOI18N
        jLabel2.setText("LOGO APLIKACIJE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 364, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(sStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(56, 56, 110));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.PNG"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(EmpServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmpServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmpServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmpServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmpServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea msgBox;
    private javax.swing.JLabel sStatus;
    // End of variables declaration//GEN-END:variables
}
