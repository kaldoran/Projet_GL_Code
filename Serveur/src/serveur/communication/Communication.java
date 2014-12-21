/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.communication;

import beans.BeanAuthentification;
import serveur.utils.ServeurConstantes;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.MainServeur;

/**
 *
 * @author kevin
 */
public class Communication {
    /**Attributs nécessaires à la connexion*/
    private Scanner sc = null;
    public ServerSocket server_socket_auth = null;
    public ServerSocket server_socket_ftp = null;
    
    private FTPCommunication FTP_com;
    
    public Communication() {
        /**Creation de la socket, printWriter et bufferedWinter */
        initialiserCommunication();
    
    }
    
    public void initialiserCommunication() {
        try {
            System.out.println("Demande de connexion");
            server_socket_auth = new ServerSocket(ServeurConstantes.PORT, ServeurConstantes.MAX_UTILISATEUR);
            server_socket_ftp = new ServerSocket(ServeurConstantes.PORTF);
            
        } catch (IOException ex) {
            Logger.getLogger(MainServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean procederAuthentification (BeanAuthentification beanAuth) {
        
        AuthentificationCommunication authentification_com = new AuthentificationCommunication(server_socket_auth);
        boolean reussiteAuthentification = authentification_com.ValiderAuthentification();
        
        try {
            server_socket_auth.close();
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reussiteAuthentification;
    }
    
}
