/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.communication;

import java.net.Socket;
import java.util.Scanner;

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