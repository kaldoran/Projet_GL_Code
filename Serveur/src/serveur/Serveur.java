/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.communication.Communication;
import serveur.utils.ServeurConstantes;

/**
 *
 * @author kevin
 */
public class Serveur {
    /**Attributs autre */
    private ServerSocket ss;
    private InetAddress LocaleAdresse;
    
    /** Attributs Service */
    private ServeurAuthentification serveurAuthentification;
    /**Attributs Interface Graphique */
    

    public Serveur() {
        
        serveurAuthentification =  new ServeurAuthentification();
        try {
            ss = new ServerSocket(ServeurConstantes.PORT, ServeurConstantes.MAX_UTILISATEUR); // ouverture d'un socket serveur sur port
            LocaleAdresse = InetAddress.getLocalHost();
            printInfo(LocaleAdresse);
            while(true) {
                new ServeurThread(ss.accept(), serveurAuthentification); // un client se connecte, un nouveau thread client est lancé
            }
        } catch (IOException ex) {
            Logger.getLogger(MainServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void printInfo(InetAddress Adresse) {
        System.out.println("--------");
        System.out.println("Serveur - Team KNK");
        System.out.println("Derniere version : 10/12/2014");
        System.out.println("--------");
        System.out.println("Demarre sur le port : " + ServeurConstantes.PORT);
        System.out.println("L'adresse du serveur est : " + Adresse);
        System.out.println("--------");
    }
}
