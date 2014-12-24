/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.communication;

import beans.BeanAuthentification;
import serveur.utils.ServeurConstantes;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.MainServeur;

/**
 *
 * @author kevin
 */
public class Communication {
    /** Attributs nécessaires à la connexion */
    private Scanner sc = null;
    private Socket socket = null;
    
    /** */
    private FTPCommunication FTP_com;
    
    public Communication(Socket s) {
       /** */
       socket = s;
    }
    
    public boolean procederAuthentification () {
        
        AuthentificationCommunication authentification_com = new AuthentificationCommunication(socket);
        boolean reussiteAuthentification = authentification_com.ValiderAuthentification();
        
//        try {
//            server_socket_auth.close();
//        } catch (IOException ex) {
//            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return reussiteAuthentification;
    }
}