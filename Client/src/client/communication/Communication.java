/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import beans.BeanAuthentification;
import beans.BeanInformationServeur;
import beans.BeanTelechargement;
import beans.BeanTeleversement;
import client.MVC.modele.impl.Authentification;
import client.utils.ClientConstantes;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class Communication {
    /**Attributs nécessaires à la connexion*/
    private static Scanner sc = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    public static Socket socket = null;
    
    private FTPCommunication FTP_com;
    private AuthentificationCommunication authentification_com;
    
    public Communication() {
        /**Creation de la socket, printWriter et bufferedWinter */
        FTP_com = new FTPCommunication();
        authentification_com = new AuthentificationCommunication();
        
    }
    
    public void initialiserCommunication() {
        try {
            System.out.println("Demande de connexion");
            socket = new Socket(ClientConstantes.SERVEUR, ClientConstantes.PORT);
            System.out.println("Connexion établie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connecté

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        
        } catch (IOException ex) {
            Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean procederAuthentification (BeanAuthentification beanAuth) {
     
        boolean reussiteAuthentification = authentification_com.authentifier(oos, ois, beanAuth);
   
        return reussiteAuthentification;
    }
    
    public BeanInformationServeur procederRecuperationInformation(BeanInformationServeur beanInfoServ ) {
        return FTP_com.demanderInformationServeur(oos, ois, beanInfoServ);
    }
    
    public void procederTelechargement(String adresse_fichier_serveur, String adresse_repertoire_client) {
        
        BeanTelechargement bean_telechargement = FTP_com.demanderTelechargement(oos, ois, new BeanTelechargement(adresse_fichier_serveur));
        
        FTP_com.lancerTelechargement(adresse_fichier_serveur, adresse_repertoire_client, bean_telechargement.getPort_telechargement());
    }

    public void procederTeleversement(String adresse_fichier_client, String adresse_repertoire_serveur) {
        BeanTeleversement bean_televersement = FTP_com.demanderTeleversement(oos, ois, new BeanTeleversement(adresse_repertoire_serveur));
        
        FTP_com.lancerTeleversement(adresse_fichier_client, adresse_repertoire_serveur, bean_televersement.getPort_televersement());
    }
    
}
