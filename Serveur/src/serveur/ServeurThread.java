/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author kaldoran
 */
public class ServeurThread implements Runnable {
    private Socket s;  // recevra le socket liant au client
    private Thread t;  // contiendra le thread du client
   
    private final int numClient=0; // contiendra le numéro de client géré par ce thread
      
    public ServeurThread(Socket s) {
        this.s = s;
        System.out.println("New Serveur Thread");
        t = new Thread(this); // instanciation du thread
        t.start(); // demarrage du thread, la fonction run() est ici lancée
    }
    
    @Override
    public void run() {
        PrintWriter out;

        System.out.println("Un nouveau client s'est connecte, no "+numClient);
        
        try {
            out = new PrintWriter(s.getOutputStream());
            out.println("Bonjour cher User ! ");
            out.flush();
            new ServeurAuthentification(s);

        }
        catch (Exception e){ }
        finally {
          try {
            // on indique à la console la deconnexion du client
            System.out.println("Le client no "+numClient+" s'est deconnecte");
            s.close(); // fermeture du socket si il ne l'a pas déjà été (à cause de l'exception levée plus haut)
          }
          catch (IOException e){ }
        }
    }
}
