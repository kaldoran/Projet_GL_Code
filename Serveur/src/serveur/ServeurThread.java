/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import beans.BeanAuthentification;
import beans.BeanInformationServeur;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.communication.Communication;
import serveur.utils.ServeurConstantes;

/**
 *
 * @author kaldoran
 */
public class ServeurThread implements Runnable {
    
    private Compte c;
    private Socket s;  // recevra le socket liant au client
    private Thread t;  // contiendra le thread du client
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private ServeurAuthentification serveur_authentification = null;
    private GestionnaireFichiers gestionnaire_fichier;
    private final int numClient = 0; // contiendra le numéro de client géré par ce thread
    
    private final int DECONNEXION = 10;
    private final int AUTHENTIFICATION = 0;
    private final int ENVOYER_ARBORESCENCE = 1;
    private final int RECEVOIR_COMMANDE = 2;
    

    public ServeurThread(Socket s, ServeurAuthentification serveurAuth) {
        this.s = s;
        this.serveur_authentification = serveurAuth;
        gestionnaire_fichier = new GestionnaireFichiers();
        System.out.println("New Serveur Thread");
        

        try {
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServeurThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        t = new Thread(this); // instanciation du thread
        t.start(); // demarrage du thread, la fonction run() est ici lancée
    }

    @Override
    public void run() {
        BeanAuthentification bean_authentification = null;
        BeanInformationServeur bean_informationServeur = null;
        Object O = null;
        String st;
        String loc = ServeurConstantes.FICHIER;
        File folder = new File(loc);
        int etat = AUTHENTIFICATION;
        int sortie = 0;

        /** Procedure d'authentification :               */
        // tache déléguer à serveur d'authentification. 
        // -    si l'authentification est valide, alors on renvoie le bean authentification 
        //      avec la reponse positive à utilsateur, et autorise le traitement de
        //      telechargement et televersement
        //
        // -    si l'authentification est invalide, alors on renvoie le bean authentification
        //      avec la reponse negative à l'utilisateur et attent a nouveau un bean authentification
        // 
        while ( sortie != DECONNEXION ) {
          
            switch (etat) {

                case AUTHENTIFICATION:
                    while (O == null && !(O instanceof BeanAuthentification)) {
                        try {
                            O = in.readObject();

                        } catch (IOException ex) {
                            Logger.getLogger(ServeurThread.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ServeurThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    bean_authentification = (BeanAuthentification) O;

                    if (serveur_authentification.ValiderAuthentification(bean_authentification)) {
                        try {
                            out.writeObject(bean_authentification);
                        } catch (IOException ex) {
                            Logger.getLogger(ServeurThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Un nouveau client s'est connecte, no " + numClient);
                        etat = ENVOYER_ARBORESCENCE;
                        break;

                    } else {

                        try {
                            out.writeObject(bean_authentification);
                        } catch (IOException ex) {
                            Logger.getLogger(ServeurThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }

                case ENVOYER_ARBORESCENCE:
                    O = null;
                    while (O == null && !(O instanceof BeanInformationServeur)) {
                        try {
                            O = in.readObject();

                        } catch (IOException ex) {
                            Logger.getLogger(ServeurThread.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ServeurThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    bean_informationServeur = (BeanInformationServeur) O;

                    bean_informationServeur.setArborescence(gestionnaire_fichier.getRacine());
                    bean_informationServeur.setNom(System.getProperties().getProperty("user.name"));
                    bean_informationServeur.setHashmap_serveur(gestionnaire_fichier.getHashMap_repertoires_client());
                    try {
                        out.writeObject(bean_informationServeur);
                    } catch (IOException ex) {
                        Logger.getLogger(ServeurThread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    etat = RECEVOIR_COMMANDE;
                    break;

                case RECEVOIR_COMMANDE:
                    sortie = DECONNEXION;
                    break;

            }

            
        }
        
        try {
            out.close();
            in.close();
            s.close();
        } catch (IOException ex) {
                Logger.getLogger(ServeurThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        /*
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
        }*/
    }

}
