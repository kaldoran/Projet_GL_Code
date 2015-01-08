/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.communication.FTPCommunication;

/**
 *
 * @author kaldoran
 */
public class Televersement implements Runnable {
    private String adresse_fichier;
    private int numero_port;
    
    private ServerSocket ss;
    private Socket s;
    public static Thread t;
    private File f;

    public Televersement(String adresse_fichier, int numero_port) {
        this.adresse_fichier = adresse_fichier;
        this.numero_port = numero_port;
       
    }

    @Override
    public void run() {
        
        try {
            ss = new ServerSocket(numero_port);
            s = ss.accept();
        } catch (IOException ex) {
            Logger.getLogger(Televersement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            FTPCommunication.transfert(
                new FileInputStream(adresse_fichier),
                s.getOutputStream(),
                true);
        
            s.close();
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(Televersement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void demarrer() {
        t = new Thread(this);
        t.start();
    }
}
