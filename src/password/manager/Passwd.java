/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author niccher
 */
public class Passwd {
    
    public String Keyed(String ky){
       String newpd = "";
       List<String> pwdl= new ArrayList<String>();
       
       if (ky.length()<16) {
           try {
               for (int i = 0; i < ky.length(); i++) {
                    char h= ky.charAt(i);
                    pwdl.add(String.valueOf(ky.charAt(i)));
                }
                
                for (int i = 0; i < (int) (16-(ky.length())); i++) {
                    pwdl.add("0");
                }
                
                newpd = String.join("", pwdl );
                
           } catch (Exception e) {
               System.out.println("Error > "+e.getMessage());
           }
           
       }else{
           newpd=ky;
       }
       
       return newpd;
   }
        
}
