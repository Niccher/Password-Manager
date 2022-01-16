/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password.manager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.File;
import java.io.FileReader;
import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author niccher
 */
public class Casa extends javax.swing.JFrame {
    
    int slidaint;
    String pathtest,algo_used,paswd,full_pwd;
    boolean seed;
    
    BufferedWriter writer = null;
    FileWriter writ ;
    
    BufferedReader br = null;
    FileReader read;
    
    FileEncrypt fienc;
    Passwd passwd;

    /**
     * Creates new form Casa
     */
    public Casa() {
        initComponents();
        
        Dimension dim=getToolkit().getScreenSize();
        int jframWidth=this.getSize().width;
        int jframHeight=this.getSize().height;
        int locationX=(dim.width-jframWidth)/2;
        int locationY=(dim.height-jframHeight)/2;
        this.setLocation(locationX, locationY);
        
        this.setTitle("PassMan KeyStore");
                
        init();
    }
    
    private void init(){        
        paswd=JOptionPane.showInputDialog(this, "Enter your Symmetric Password, that is used for Encryption and Decryption.","Master Password",JOptionPane.YES_OPTION);
        
        if (paswd.equals("")) {
            paswd=JOptionPane.showInputDialog(this, "Enter your Symmetric Password, that is used for Encryption and Decryption.","Master Password",JOptionPane.YES_OPTION);
        }
                
        passwd = new Passwd();
        full_pwd = passwd.Keyed(paswd);
        System.out.println("Fill your Password "+full_pwd);
        
        Tab_Main.setSelectedIndex(0);
        Calendar cal =new GregorianCalendar();
        String yr=String.valueOf(cal.get(Calendar.YEAR));
        String mn=String.valueOf((cal.get(Calendar.MONTH))+1);
        String dy=String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        
        entry_date.setText(yr+"-"+mn+"-"+dy);
        entry_date.setEnabled(Boolean.FALSE);
        entry_pwd.setEditable(Boolean.FALSE);
        
        LoadEntries();        
    }
        
    private void WriteEntry(String Nm,String dt,String pw){      
                
        fienc=new FileEncrypt();
        
        if (full_pwd.equals("")) {
            //System.out.println("Password is Null");
        }else{
            try {
                String enc_entry=fienc.Enc_File(Nm+","+dt+","+pw, full_pwd);

                try {
                    writ = new FileWriter("CoreDump.csv", true);
                    writer = new BufferedWriter(writ);
                    writer.append(enc_entry+"\n");
                    writer.flush();
                } catch (IOException ex) {
                    System.out.println("WriteEntry Errored "+ex.getMessage());
                } finally {
                    try {
                        writer.close();
                    } catch (IOException ex) {
                        System.out.println("WriteEntry Finally Errored "+ex.getMessage());
                    }
                }

            } catch (Exception ex) {
                System.out.println("public String Enc_File Errored "+ex.getMessage());
            }


           LoadEntries();
        }
        
    }
    
    private void LoadEntries(){      
        fienc=new FileEncrypt();
                
        try {
            read = new FileReader(new File("CoreDump.csv"));
            br = new BufferedReader(read);
            
            List<String[]> elements = new ArrayList<String[]>();
            String line = null;
            
            while((line = br.readLine())!=null) {
                String filelines = fienc.Dec_File(line.replace("**", "\n"),full_pwd,"");
                String spl[]=filelines.split(",");
                //System.out.println("Encry "+filelines);
                //System.out.println("Decry "+spl);
                elements.add(spl);
                
            }
            br.close();

            JTable table = tbl_key_pair;
            String[] columNames = new String[] {"Name", "Date", "Password"};

            Object[][] content = new Object[elements.size()][3];

            for(int i=0; i<elements.size(); i++) {
                content[i][0] = elements.get(i)[0];
                content[i][1] = elements.get(i)[1];
                content[i][2] = elements.get(i)[2];
            }

            table.setModel(new DefaultTableModel(content,columNames));

        } catch (Exception ex) {
            System.out.println("LoadEntries Errored "+ex.getMessage());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        Home_Body = new javax.swing.JPanel();
        Tab_Main = new javax.swing.JTabbedPane();
        Panel_home = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_key_pair = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        entry_name = new javax.swing.JTextField();
        entry_date = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        entry_pwd = new javax.swing.JTextField();
        Btn_ReGenerate = new javax.swing.JButton();
        Btn_Table = new javax.swing.JButton();
        Btn_Copypwd = new javax.swing.JButton();
        Home_Header = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Home_Tail = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Tab_Main.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                Tab_MainStateChanged(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Key-Pair"));

        tbl_key_pair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_key_pair.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbl_key_pair);
        if (tbl_key_pair.getColumnModel().getColumnCount() > 0) {
            tbl_key_pair.getColumnModel().getColumn(0).setMinWidth(100);
            tbl_key_pair.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_key_pair.getColumnModel().getColumn(1).setMinWidth(100);
            tbl_key_pair.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Entry - Key Pair"));

        jLabel5.setText("Date");

        entry_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        entry_name.setToolTipText("");

        entry_date.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        entry_date.setToolTipText("");

        jLabel4.setText("Entry Name");

        jLabel6.setText("Generated Password");

        entry_pwd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        entry_pwd.setToolTipText("");

        Btn_ReGenerate.setText("Re-Generate");
        Btn_ReGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ReGenerateActionPerformed(evt);
            }
        });

        Btn_Table.setText("Finish");
        Btn_Table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_TableActionPerformed(evt);
            }
        });

        Btn_Copypwd.setText("Copy Password");
        Btn_Copypwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_CopypwdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entry_name)
            .addComponent(entry_date, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(entry_pwd, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Btn_ReGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Btn_Table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Btn_Copypwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entry_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entry_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entry_pwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btn_ReGenerate)
                .addGap(18, 18, 18)
                .addComponent(Btn_Table)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Btn_Copypwd)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_homeLayout = new javax.swing.GroupLayout(Panel_home);
        Panel_home.setLayout(Panel_homeLayout);
        Panel_homeLayout.setHorizontalGroup(
            Panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_homeLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Panel_homeLayout.setVerticalGroup(
            Panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Tab_Main.addTab("Home", Panel_home);

        javax.swing.GroupLayout Home_BodyLayout = new javax.swing.GroupLayout(Home_Body);
        Home_Body.setLayout(Home_BodyLayout);
        Home_BodyLayout.setHorizontalGroup(
            Home_BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tab_Main)
        );
        Home_BodyLayout.setVerticalGroup(
            Home_BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tab_Main)
        );

        getContentPane().add(Home_Body, java.awt.BorderLayout.CENTER);

        Home_Header.setBackground(new java.awt.Color(153, 153, 255));
        Home_Header.setPreferredSize(new java.awt.Dimension(700, 80));

        jLabel7.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("PassMan KeyStore");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Generate and Store your Passwords");

        javax.swing.GroupLayout Home_HeaderLayout = new javax.swing.GroupLayout(Home_Header);
        Home_Header.setLayout(Home_HeaderLayout);
        Home_HeaderLayout.setHorizontalGroup(
            Home_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Home_HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Home_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Home_HeaderLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(Home_HeaderLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 22, Short.MAX_VALUE))))
        );
        Home_HeaderLayout.setVerticalGroup(
            Home_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Home_HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(Home_Header, java.awt.BorderLayout.PAGE_START);

        Home_Tail.setBackground(new java.awt.Color(153, 153, 153));
        Home_Tail.setPreferredSize(new java.awt.Dimension(700, 50));

        javax.swing.GroupLayout Home_TailLayout = new javax.swing.GroupLayout(Home_Tail);
        Home_Tail.setLayout(Home_TailLayout);
        Home_TailLayout.setHorizontalGroup(
            Home_TailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 738, Short.MAX_VALUE)
        );
        Home_TailLayout.setVerticalGroup(
            Home_TailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(Home_Tail, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_ReGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ReGenerateActionPerformed
        // TODO add your handling code here:
        try {
            Process proc = Runtime.getRuntime().exec("head -c 20 /dev/random");

            BufferedReader reader =new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            StringBuilder sb=new StringBuilder();
            while((line = reader.readLine()) != null) {
                //System.out.print(line + "\n");
                sb.append(line);  
            }
            entry_pwd.setText(String.valueOf(sb));
            proc.waitFor();

        } catch (IOException ex) {
            System.out.println("Btn_ReGenerate > IOException "+ex);
        } catch (InterruptedException ex) {
            System.out.println("Btn_ReGenerate > InterruptedException "+ex);
        }
    }//GEN-LAST:event_Btn_ReGenerateActionPerformed

    private void Btn_TableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TableActionPerformed
        // TODO add your handling code here:
        if (entry_name.getText().trim().equals("") || entry_date.getText().trim().equals("") || entry_pwd.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Ensure all fields are filled","Unxepected Input",JOptionPane.INFORMATION_MESSAGE);
        }else{
            WriteEntry(entry_name.getText(), entry_date.getText(), entry_pwd.getText());
            //System.out.println("Name >"+entry_name.getText());
            //System.out.println("Date >"+entry_date.getText());
            //System.out.println("Passwd >"+entry_pwd.getText());
        }
    }//GEN-LAST:event_Btn_TableActionPerformed

    private void Tab_MainStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_Tab_MainStateChanged
        // TODO add your handling code here:
        /*if (Panel_start.isShowing()) {
            System.out.println("Panel_start Opened");
        }else if (Panel_home.isShowing()) {
            System.out.println("Panel_home Opened");
        }else if (Panel_3.isShowing()) {
            System.out.println("Panel_3 Opened");
        }*/
    }//GEN-LAST:event_Tab_MainStateChanged

    private void Btn_CopypwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_CopypwdActionPerformed
        // TODO add your handling code here:
        JTable tbl = tbl_key_pair;
        int column = 2;
        int row = tbl.getSelectedRow();
        String value = tbl.getModel().getValueAt(row, column).toString();
        
        try {
            StringSelection selection = new StringSelection(value);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        } catch (Exception e) {
            System.out.println("Error as " + e);
        }
    }//GEN-LAST:event_Btn_CopypwdActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Proceeding with closing this application. \nUnsaved data will be lost.","Terminate this session.",JOptionPane.WARNING_MESSAGE);
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Casa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Casa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Casa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Casa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Casa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Copypwd;
    private javax.swing.JButton Btn_ReGenerate;
    private javax.swing.JButton Btn_Table;
    private javax.swing.JPanel Home_Body;
    private javax.swing.JPanel Home_Header;
    private javax.swing.JPanel Home_Tail;
    private javax.swing.JPanel Panel_home;
    private javax.swing.JTabbedPane Tab_Main;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField entry_date;
    private javax.swing.JTextField entry_name;
    private javax.swing.JTextField entry_pwd;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_key_pair;
    // End of variables declaration//GEN-END:variables
}
