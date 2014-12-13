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
import serveur.utils.ServeurConstantes;

/**
 *
 * @author kaldoran
 */
public class Serveur {
    private int nbClients = 0; // nombre total de clients connectés

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Serveur serv = new Serveur();
        ServerSocket ss;
        InetAddress LocaleAdresse;
        
        try {
            ss = new ServerSocket(ServeurConstantes.PORT, ServeurConstantes.MAX_UTILISATEUR); // ouverture d'un socket serveur sur port
            LocaleAdresse = InetAddress.getLocalHost();
            printInfo(LocaleAdresse);
            while(true) {
                new ServeurThread(ss.accept(), serv); // un client se connecte, un nouveau thread client est lancé
            }
        } catch (IOException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
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
    
    synchronized public int getNbClients() {
        return nbClients; // retourne le nombre de clients connectés
    }
}
