/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static javax.xml.bind.DatatypeConverter.printHexBinary;
/**
 *
 * @author kaldoran
 */
public class toSha1 {
    
    public static String toSHA1(byte[] convertme) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return printHexBinary(md.digest(convertme));
    }
}
