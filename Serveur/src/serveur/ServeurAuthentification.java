/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import beans.BeanAuthentification;
import java.io.BufferedReader;
import java.io.IOException;
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
    private int nb_utilisateurs_connectes = 0;

    public ServeurAuthentification() {
        
    }

    public int getNb_utilisateurs_connectes() {
        return nb_utilisateurs_connectes;
    }

    public boolean ValiderAuthentification(BeanAuthentification beanAuth) {
        boolean estValide = false;

        estValide = GestionCompte.isCorrect(beanAuth.getLogin(), beanAuth.getMot_de_passe());
        
        if(estValide) {
            beanAuth.Valider();
            nb_utilisateurs_connectes++;
        }
        
        return estValide;
    }
    
    
    

}

/*private Socket s;

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
                connect = GestionCompte.isCorrect(login, pass);
                
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
    } */