/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medserver;

import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encode {

    private final static Logger LOGGER = Logger.getLogger(RunServer.class.getName());
    private static String pass;
    /**
     * Metoda do szyfrowania hasla zapisywanego do bazy danych.
     * SHA-256
     * @param pass
     * @return 
     * @String - zaszyfrowane haslo
     */
    public String encode(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pass.getBytes());
            byte byteData[] = md.digest();
            StringBuilder hexPass = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexPass.append('0');
                }
                hexPass.append(hex);  
            }
            String pass2=hexPass.toString();
            return pass2;
        } catch (Exception e) {
            LOGGER.severe("Wystapil krytyczny blad SZYFROWANIA hasla klienta!!");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
