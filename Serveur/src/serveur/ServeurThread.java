/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.io.BufferedReader;
import java.io.File;
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
public class ServeurThread implements Runnable {
    private Compte c;
    private Socket s;  // recevra le socket liant au client
    private Thread t;  // contiendra le thread du client
    private PrintWriter out = null;
    private BufferedReader in = null;
    private final int numClient = 0; // contiendra le numéro de client géré par ce thread

    public ServeurThread(Socket s, Serveur serveur) {
        this.s = s;
        System.out.println("New Serveur Thread");

        try {
            out = new PrintWriter(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ServeurThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        t = new Thread(this); // instanciation du thread
        t.start(); // demarrage du thread, la fonction run() est ici lancée
    }

    @Override
    public void run() {

        String st;
        String loc = ServeurConstantes.FICHIER;
        File folder = new File(loc);

        System.out.println("Un nouveau client s'est connecte, no " + numClient);

        try {
            out.println("Bonjour cher User ! ");
            out.flush();
            new ServeurAuthentification(s, out, in);

            // Supposont qu'il est connecté 
            System.out.println("Attente d'input de l'utilisateur");

            do {
                st = in.readLine();
                System.out.println("Input : " + st);
                if (st.startsWith("ls")) {
                    for (File file : folder.listFiles()) {
                        out.println(file.getName());
                    }
                } else {
                    String command, file;

                    command = st.substring(0, st.indexOf(' '));
                    file = st.substring(st.indexOf(' ') + 1);

                    if (command.equalsIgnoreCase("cd")) {
                        if (file.equals("..")) {
                            System.out.println("file : " + loc);
                            if (!loc.equals(ServeurConstantes.FICHIER)) {
                                loc = loc.substring(0, loc.lastIndexOf("/"));
                                System.out.println("Loc2 " + loc);
                            }
                        }
                        else {
                            loc += "/" + file;
                        }
                        
                        System.out.println("file : " + loc);
                        folder = new File(loc);

                    } else if (command.equalsIgnoreCase("get")) {
                        out.println("Ready get");
                        out.flush();
                        new Televersement(file, loc);
                        
                    } else if (command.equalsIgnoreCase("post")) {
                        out.println("Ready post");
                        out.flush();
                        
                        new Telechargement(file, loc);
                    }
                }
                
                out.println("end");
                out.flush();
            } while (!st.equalsIgnoreCase("exit"));
        } catch (Exception e) {
        } finally {
            try {
                // on indique à la console la deconnexion du client
                System.out.println("Le client no " + numClient + " s'est deconnecte");
                s.close(); // fermeture du socket si il ne l'a pas déjà été (à cause de l'exception levée plus haut)
            } catch (IOException e) {
            }
        }
    }
}
