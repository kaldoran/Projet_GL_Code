/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.utils.ServeurConstantes;
import serveur.utils.toSha1;

/**
 *
 * @author kaldoran
 */
public class ServeurAuthentification {
    private Socket s;

    private Object socket;
    
    public ServeurAuthentification(Socket s, PrintWriter out, BufferedReader in) {
        this.s = s;
        Boolean connect = false;
        String login, pass;
        int NbEssai;
        
        NbEssai = 0;
        try {            
            do {
                System.out.println("Verification de login");
                
                out.println("Entrez votre login :");
                out.flush();

                login = in.readLine();
                out.println("Entrez votre mot de passe :");
                out.flush();
                pass = in.readLine();
                
                ++NbEssai;
                connect = isCorrect(login, pass);
                
                if ( connect ) 
                    out.println("connect");
                else 
                    out.println("Erreur");  

                out.flush();
            } while(! connect
                    && NbEssai != ServeurConstantes.MAX_ESSAI);
            
            if ( NbEssai >= ServeurConstantes.MAX_ESSAI ) {
                 out.println("Trop de tentative de connexion");
                 out.flush();
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServeurAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }    

    private boolean isCorrect(String login, String pass) {
        String inputScanner = null;
        String hashpass = null;
        hashpass = toSha1.toSHA1(pass.getBytes());
        
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(ServeurConstantes.FICHIER_COMPTE) );
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServeurAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(scanner.hasNextLine()){
            if ( (inputScanner = scanner.nextLine().trim()).startsWith(login)) 
                return true;
        }
        
        return false;
    }
}

