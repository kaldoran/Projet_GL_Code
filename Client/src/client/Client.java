/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.utils.ClientConstantes;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaldoran
 */
public class Client {

    public static Socket socket = null;
    public static Thread t1;

    public static void main(String[] args) {
            
        try {
            System.out.println("Demande de connexion");
            socket = new Socket(ClientConstantes.SERVEUR, ClientConstantes.PORT);
            System.out.println("Connexion établie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connecté
            
            new Connexion(socket);
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
