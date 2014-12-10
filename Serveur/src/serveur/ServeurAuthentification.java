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
import java.io.InputStreamReader;
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

    private PrintWriter out = null;
    private BufferedReader in = null;
    private Object socket;
    
    public ServeurAuthentification(Socket s) {
        this.s = s;
        Boolean connect = false;
        String login = null, pass = null;
        int NbEssai;
        
        NbEssai = 0;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream());
            
            do {
                out.println("Entrez votre login :");
                out.flush();

                
                login = in.readLine();
                out.println("Entrez votre mot de passe :");
                out.flush();
                pass = in.readLine();

                ++NbEssai;
            } while(! (connect = isCorrect(login, pass))
                    && NbEssai != ServeurConstantes.MAX_ESSAI);
            
            if ( connect ) {
                out.printf("connect");
                out.flush();
            } 
            else if ( NbEssai >= ServeurConstantes.MAX_ESSAI ) {
                 out.println("Trop de tentative de connexion");
                 out.flush();
            }
           
            in.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ServeurAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }    

    private boolean isCorrect(String login, String pass) {
        String s = null;
        String hashpass = null;
        hashpass = toSha1.toSHA1(pass.getBytes());
        
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(ServeurConstantes.FICHIER_COMPTE) );
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServeurAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(scanner.hasNextLine()){
            if ( (s = scanner.nextLine().trim()).startsWith(login)) 
                return true;
        }
        
        return false;
    }
}

