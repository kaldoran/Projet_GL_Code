/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import beans.BeanAuthentification;
import client.MVC.modele.impl.Client;
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
    public static Socket socket = null;
    
    private FTPCommunication FTP_com;
    
    public Communication() {
        /**Creation de la socket, printWriter et bufferedWinter */
    
    }
    
    public void initialiserCommunication() {
        try {
            System.out.println("Demande de connexion");
            socket = new Socket(ClientConstantes.SERVEUR, ClientConstantes.PORT);
            System.out.println("Connexion établie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connecté
                       
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean procederAuthentification (BeanAuthentification beanAuth) {
        
        AuthentificationCommunication authentification_com = new AuthentificationCommunication(socket);
        boolean reussiteAuthentification = authentification_com.authentifier(beanAuth);
        
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reussiteAuthentification;
    }
    
}
