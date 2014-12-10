/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.utils.ServeurConstantes;

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
            } while(!isCorrect(login, pass)
                    && NbEssai != ServeurConstantes.MAX_ESSAI);
            
            if ( NbEssai >= ServeurConstantes.MAX_ESSAI ) {
                 out.printf("Trop de tentative de connexion");
                 out.flush();
            }
           
            in.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ServeurAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }    

    private boolean isCorrect(String login, String pass) {
        System.out.println("Check Login - Password");
        return false;
    }
}

