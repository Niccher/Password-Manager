/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password.manager;

import java.awt.Dimension;
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
    String pathtest,algo_used;
    boolean seed;

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
        
        StartSlida();
        init();
    }
    
    private void StartSlida(){
        slidalen.setPaintTicks(true);
        slidalen.setPaintLabels(true);       
    }
    
    private void init(){
        //jTabbedPane1.setSelectedIndex(1);
        //opennfile conf->output
        Calendar cal =new GregorianCalendar();
        String yr=String.valueOf(cal.get(Calendar.YEAR));
        String mn=String.valueOf((cal.get(Calendar.MONTH))+1);
        String dy=String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        
        String confkey="";
        entry_date.setText(yr+"-"+mn+"-"+dy);
        entry_date.setEnabled(Boolean.FALSE);
        entry_pwd.setEditable(Boolean.FALSE);
        
        LoadEntries();
        
        /*try {
            //openssl enc -des-ecb -e -in hello.csv -out hello.csv.des-ecb
            Process proc = Runtime.getRuntime().exec("openssl enc "+algo_used+" -e -in hello.csv -out hello.csv.des-ecb");

            BufferedReader reader =new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            StringBuilder sb=new StringBuilder();
            //proc.getInputStream().reset();
            while((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
                sb.append(line);  
            }
            noisetext.setText(String.valueOf(sb));
            proc.waitFor();

        } catch (IOException ex) {
            System.out.println("StartNoise > IOException "+ex);
        } catch (InterruptedException ex) {
            System.out.println("StartNoise > InterruptedException "+ex);
        }*/
        
    }
    
    private String Encryption_Algorithm(){
        
        if (algo_aes_256_cbc.isSelected()) {
            algo_used="aes_256_cbc";
        }
        else if (algo_aes_256_ecb.isSelected()) {
            algo_used="aes_256_ecb";
        }
        else if (algo_aria_256_ecb.isSelected()) {
            algo_used="aria_256_ecb";
        }
        else if (algo_aria_256_ofb.isSelected()) {
            algo_used="aria_256_ofb";
        }
        else if (algo_cast5_cbc.isSelected()) {
            algo_used="cast5_cbc";
        }
        else if (algo_cast_cbc.isSelected()) {
            algo_used="cast_cbc";
        }
        else if (algo_des3.isSelected()) {
            algo_used="aes_256_cbc";
        }
        else if (algo_des_ecb.isSelected()) {
            algo_used="aes_256_ecb";
        }
        else if (algo_rc4.isSelected()) {
            algo_used="rc4";
        }
        else {
            algo_used="";
        }     
         
        return algo_used;
    }
    
    private void WriteEntry(String Nm,String dt,String pw){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("CoreDump.csv", true));
            writer.append(Nm+","+dt+","+pw+"\n");
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
       LoadEntries();
    }
    
    private void LoadEntries(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("CoreDump.csv")));
            
            List<String[]> elements = new ArrayList<String[]>();
            String line = null;
            while((line = br.readLine())!=null) {
                String[] splitted = line.split(",");
                elements.add(splitted);
            }
            br.close();

            JTable table = tbl_key_pair;
            String[] columNames = new String[] {
                    "Name", "Date", "Password"
                };

            Object[][] content = new Object[elements.size()][3];

            for(int i=0; i<elements.size(); i++) {
                content[i][0] = elements.get(i)[0];
                content[i][1] = elements.get(i)[1];
                content[i][2] = elements.get(i)[2];
            }

            table.setModel(new DefaultTableModel(content,columNames));
            //System.out.println(table.getModel().getValueAt(1, 1));

        } catch (Exception ex) {
            System.out.println("LoadEntries Errored "+ex.getMessage());
        }
    }
    
    private void File_Encryption(String password, File honeypot){}
    
    private void File_Decryption(String password, File honeypot){}
    
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Panel_start = new javax.swing.JPanel();
        pan_start = new javax.swing.JPanel();
        Btn_Next = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        StartNoise = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        noisetext = new javax.swing.JTextField();
        slidalen = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        slidavalue = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        algo_des3 = new javax.swing.JRadioButton();
        algo_aes_256_cbc = new javax.swing.JRadioButton();
        algo_aes_256_ecb = new javax.swing.JRadioButton();
        algo_cast5_cbc = new javax.swing.JRadioButton();
        algo_cast_cbc = new javax.swing.JRadioButton();
        algo_aria_256_ecb = new javax.swing.JRadioButton();
        algo_des_ecb = new javax.swing.JRadioButton();
        algo_rc4 = new javax.swing.JRadioButton();
        algo_aria_256_ofb = new javax.swing.JRadioButton();
        Btn_Reset = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SaveFilePath = new javax.swing.JTextField();
        Browse_Output = new javax.swing.JButton();
        log_dump = new javax.swing.JLabel();
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
        jPanel3 = new javax.swing.JPanel();
        Home_Header = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Home_Tail = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 500));

        Btn_Next.setText("Next");
        Btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NextActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Noise"));

        StartNoise.setText("Generate");
        StartNoise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartNoiseActionPerformed(evt);
            }
        });

        jLabel2.setText("Noise Text");

        slidalen.setMajorTickSpacing(8);
        slidalen.setMaximum(96);
        slidalen.setMinimum(32);
        slidalen.setMinorTickSpacing(2);
        slidalen.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slidalenStateChanged(evt);
            }
        });

        jLabel3.setText("Noise Length");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(StartNoise, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(218, 218, 218))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noisetext)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slidalen, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slidavalue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(noisetext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StartNoise)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slidavalue, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(slidalen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Algorithm"));

        buttonGroup1.add(algo_des3);
        algo_des3.setText("des3");

        buttonGroup1.add(algo_aes_256_cbc);
        algo_aes_256_cbc.setText("aes-256-cbc");

        buttonGroup1.add(algo_aes_256_ecb);
        algo_aes_256_ecb.setText("aes-256-ecb");

        buttonGroup1.add(algo_cast5_cbc);
        algo_cast5_cbc.setText("cast5-cbc");

        buttonGroup1.add(algo_cast_cbc);
        algo_cast_cbc.setText("cast-cbc");

        buttonGroup1.add(algo_aria_256_ecb);
        algo_aria_256_ecb.setText("aria-256-ecb");

        buttonGroup1.add(algo_des_ecb);
        algo_des_ecb.setText("des-ecb");

        buttonGroup1.add(algo_rc4);
        algo_rc4.setText("rc4");

        buttonGroup1.add(algo_aria_256_ofb);
        algo_aria_256_ofb.setText("aria-256-ofb");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(algo_cast5_cbc)
                        .addGap(18, 18, 18)
                        .addComponent(algo_cast_cbc)
                        .addGap(18, 18, 18)
                        .addComponent(algo_des3)
                        .addGap(18, 18, 18)
                        .addComponent(algo_des_ecb))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(algo_aes_256_cbc)
                        .addGap(18, 18, 18)
                        .addComponent(algo_aes_256_ecb)
                        .addGap(18, 18, 18)
                        .addComponent(algo_aria_256_ecb)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(algo_rc4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(algo_aria_256_ofb)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(algo_aes_256_cbc)
                    .addComponent(algo_aes_256_ecb)
                    .addComponent(algo_aria_256_ecb)
                    .addComponent(algo_aria_256_ofb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(algo_cast5_cbc)
                    .addComponent(algo_cast_cbc)
                    .addComponent(algo_des3)
                    .addComponent(algo_des_ecb)
                    .addComponent(algo_rc4)))
        );

        Btn_Reset.setText("Reset");
        Btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ResetActionPerformed(evt);
            }
        });

        jLabel1.setText("Output");

        Browse_Output.setText("Browse");
        Browse_Output.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Browse_OutputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(log_dump, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SaveFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(Browse_Output)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(SaveFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Browse_Output))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(log_dump, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pan_startLayout = new javax.swing.GroupLayout(pan_start);
        pan_start.setLayout(pan_startLayout);
        pan_startLayout.setHorizontalGroup(
            pan_startLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_startLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_startLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_startLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_Reset)
                        .addGap(28, 28, 28)
                        .addComponent(Btn_Next)
                        .addGap(55, 55, 55)))
                .addContainerGap())
        );
        pan_startLayout.setVerticalGroup(
            pan_startLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_startLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(pan_startLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Next)
                    .addComponent(Btn_Reset))
                .addContainerGap())
        );

        javax.swing.GroupLayout Panel_startLayout = new javax.swing.GroupLayout(Panel_start);
        Panel_start.setLayout(Panel_startLayout);
        Panel_startLayout.setHorizontalGroup(
            Panel_startLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Panel_startLayout.setVerticalGroup(
            Panel_startLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Start", Panel_start);

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
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Generate Key Pairs"));

        jLabel5.setText("Time");

        entry_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        entry_name.setToolTipText("");

        entry_date.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        entry_date.setToolTipText("");

        jLabel4.setText("Name");

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
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Btn_ReGenerate)
                            .addComponent(Btn_Table, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(65, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(Btn_Table)
                .addContainerGap())
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

        jTabbedPane1.addTab("Home", Panel_home);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", jPanel3);

        javax.swing.GroupLayout Home_BodyLayout = new javax.swing.GroupLayout(Home_Body);
        Home_Body.setLayout(Home_BodyLayout);
        Home_BodyLayout.setHorizontalGroup(
            Home_BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        Home_BodyLayout.setVerticalGroup(
            Home_BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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

    private void slidalenStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slidalenStateChanged
        // TODO add your handling code here:      
        slidalen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                slidavalue.setText(String.valueOf(source.getValue()));
                slidaint=source.getValue();
            }
        });
    }//GEN-LAST:event_slidalenStateChanged

    private void StartNoiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartNoiseActionPerformed
        // TODO add your handling code here:
        if ((slidaint < 30 )) {
            JOptionPane.showMessageDialog(this,"Noise level is below the expected\nSelect the limit by the sliding the slider","Select the level first"
                ,JOptionPane.ERROR_MESSAGE);
        } else {
            
            try {
                Process proc = Runtime.getRuntime().exec("head -c "+slidaint+" /dev/random");

                BufferedReader reader =new BufferedReader(new InputStreamReader(proc.getInputStream()));

                String line = "";
                StringBuilder sb=new StringBuilder();
                while((line = reader.readLine()) != null) {
                    System.out.print(line + "\n");
                    sb.append(line);  
                }
                noisetext.setText(String.valueOf(sb));
                proc.waitFor();

            } catch (IOException ex) {
                System.out.println("StartNoise > IOException "+ex);
            } catch (InterruptedException ex) {
                System.out.println("StartNoise > InterruptedException "+ex);
            }
            
        }
        
    }//GEN-LAST:event_StartNoiseActionPerformed

    private void Browse_OutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Browse_OutputActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify output Destination");   

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            //System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            pathtest=String.valueOf(fileChooser.getSelectedFile());
            SaveFilePath.setText(String.valueOf(fileChooser.getSelectedFile()));
        }
    }//GEN-LAST:event_Browse_OutputActionPerformed

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

    private void Btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ResetActionPerformed
        // TODO add your handling code here:
        slidaint=0;
        pathtest="";algo_used="";
        seed=Boolean.FALSE;
        SaveFilePath.setText("");
        noisetext.setText("");
    }//GEN-LAST:event_Btn_ResetActionPerformed

    private void Btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NextActionPerformed
        // TODO add your handling code here:
        BufferedWriter writer = null;
        Encryption_Algorithm();
        
        System.out.println("algo_used > "+algo_used);
        System.out.println("pathtest > "+pathtest);
        System.out.println("slidaint > "+slidaint);
        
        if (algo_used.equals("") || pathtest.equals("") || slidaint<30) {
            JOptionPane.showMessageDialog(this,"Misssing required data.\nFill all required fields","Blanks Fields"
                ,JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                writer = new BufferedWriter(new FileWriter("PassMan.conf", true));
                
                writer.append("Enc_type Assymetric Symetric\n");
                writer.append("Enc_Algo , "+algo_used+"\n");
                writer.append("Enc_File , "+pathtest+"\n");  
                writer.append("Noise , "+noisetext.getText()+"\n");
                writer.append("Noise_Len , "+slidaint+"\n");  
                
                writer.flush();
                
                jTabbedPane1.setSelectedIndex(1);

            } catch (IOException ex) {
                System.out.println("Btn_Next > IOException "+ex);
            } finally {
                try {
                    writer.close();
                } catch (IOException ex) {
                    System.out.println("Btn_Next > IOException Finally "+ex);
                }
            }
        }
        
    }//GEN-LAST:event_Btn_NextActionPerformed

    private void Btn_TableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TableActionPerformed
        // TODO add your handling code here:
        if (entry_name.getText().trim().equals("") || entry_date.getText().trim().equals("") || entry_pwd.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Esure all fields are filled","Unxepected Input",JOptionPane.INFORMATION_MESSAGE);
        }else{
            WriteEntry(entry_name.getText(), entry_date.getText(), entry_pwd.getText());
            System.out.println("Name >"+entry_name.getText());
            System.out.println("Date >"+entry_date.getText());
            System.out.println("Passwd >"+entry_pwd.getText());
        }
        
    }//GEN-LAST:event_Btn_TableActionPerformed

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
    private javax.swing.JButton Browse_Output;
    private javax.swing.JButton Btn_Next;
    private javax.swing.JButton Btn_ReGenerate;
    private javax.swing.JButton Btn_Reset;
    private javax.swing.JButton Btn_Table;
    private javax.swing.JPanel Home_Body;
    private javax.swing.JPanel Home_Header;
    private javax.swing.JPanel Home_Tail;
    private javax.swing.JPanel Panel_home;
    private javax.swing.JPanel Panel_start;
    private javax.swing.JTextField SaveFilePath;
    private javax.swing.JButton StartNoise;
    private javax.swing.JRadioButton algo_aes_256_cbc;
    private javax.swing.JRadioButton algo_aes_256_ecb;
    private javax.swing.JRadioButton algo_aria_256_ecb;
    private javax.swing.JRadioButton algo_aria_256_ofb;
    private javax.swing.JRadioButton algo_cast5_cbc;
    private javax.swing.JRadioButton algo_cast_cbc;
    private javax.swing.JRadioButton algo_des3;
    private javax.swing.JRadioButton algo_des_ecb;
    private javax.swing.JRadioButton algo_rc4;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField entry_date;
    private javax.swing.JTextField entry_name;
    private javax.swing.JTextField entry_pwd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel log_dump;
    private javax.swing.JTextField noisetext;
    private javax.swing.JPanel pan_start;
    private javax.swing.JSlider slidalen;
    private javax.swing.JLabel slidavalue;
    private javax.swing.JTable tbl_key_pair;
    // End of variables declaration//GEN-END:variables
}
