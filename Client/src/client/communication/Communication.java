/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import beans.BeanAuthentification;
import beans.BeanInformationServeur;
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
    
    public Communication() {
        /**Creation de la socket, printWriter et bufferedWinter */
        FTP_com = new FTPCommunication();
        
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
        
        AuthentificationCommunication authentification_com = new AuthentificationCommunication(socket, oos, ois);
        boolean reussiteAuthentification = authentification_com.authentifier(beanAuth);
   
        return reussiteAuthentification;
    }
    
    public BeanInformationServeur procederRecuperationInformation(BeanInformationServeur beanInfoServ ) {
        return FTP_com.demanderInformationServeur(socket, oos, ois, beanInfoServ);
    }
}
