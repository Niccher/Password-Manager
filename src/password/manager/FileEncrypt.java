/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author niccher
 */
public class FileEncrypt {
    String toEncrypt,algo="AES";
    byte[] keybits;

    /*public FileEncrypt(String toEncrypt, byte[] keybits) {
        this.toEncrypt = toEncrypt;
        this.keybits = keybits;
    }*/

   public Key Gen_Keys(String keyis){
       keybits = keyis.getBytes();
       Key ki=new SecretKeySpec(keybits, algo);
       return ki;
   }
   
   public String Enc_File(String tbl_entries,String fullkey){
       Key key = Gen_Keys(fullkey);
       String enc_content = null, decry = null;
       
        try {
            Cipher c = Cipher.getInstance(algo);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] enc_txt = c.doFinal(tbl_entries.getBytes());            
            enc_content = new BASE64Encoder().encode(enc_txt);
                        
            if (enc_content.contains("\n")) {
                enc_content = enc_content.replace("\n", "**");
            }else{
                System.out.println("No New Line" );
            }
            
            System.out.println("Enc Stream is"+enc_content);
                        
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Enc_File NoSuchAlgorithmException ");
            
        } catch (NoSuchPaddingException ex) {
            System.out.println("Enc_File NoSuchPaddingException ");
        } catch (InvalidKeyException ex) {
            System.out.println("Enc_File NoSuchPaddingException ");
        } catch (IllegalBlockSizeException ex) {
            System.out.println("Enc_File IllegalBlockSizeException ");
        } catch (BadPaddingException ex) {
            System.out.println("Enc_File BadPaddingException ");
        }
        return enc_content;
   }
   
   public String Dec_File(String fileContents,String fullkey, String mode){
       Key key = Gen_Keys(fullkey);
       String dec_content = null ;
       
       try {
            Cipher c = Cipher.getInstance(algo);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] dec_b64 = new BASE64Decoder().decodeBuffer(fileContents);
            byte[] dec_txt = c.doFinal(dec_b64);
            dec_content = new String(dec_txt);

        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Dec_File NoSuchAlgorithmException ");
        } catch (NoSuchPaddingException ex) {
            System.out.println("Dec_File NoSuchPaddingException ");
        } catch (InvalidKeyException ex) {
            System.out.println("Dec_File NoSuchPaddingException ");
        } catch (IllegalBlockSizeException ex) {
            System.out.println("Dec_File IllegalBlockSizeException ");
        } catch (BadPaddingException ex) {
            System.out.println("Dec_File BadPaddingException ");
        } catch (IOException ex) {
            System.out.println("Dec_File IOException ");
        }
        
        return dec_content;
   }
   
    /*public String Keyed(){
        Passwd nc=new Passwd();
        return nc.Keyed("helo");
    }*/
   
    /*public static void main(String[] args) {
        
        byte[] ky = null;
        FileEncrypt fe=new FileEncrypt(null,ky);
        
        System.out.println("KEY IS "+fe.Keyed());
        ky = fe.Keyed().getBytes();
        fe=new FileEncrypt("",ky);
        
        try {
            String enc = fe.Enc_File("Im encrypted here");
            System.out.println("Enc text> "+enc);
            String dec = fe.Dec_File(enc);
            System.out.println("Dec text> "+dec);
            
        } catch (Exception e) {
            System.out.println("Dec_File IOstatic void main Exception "+e.getMessage());
        }
    }*/
}
