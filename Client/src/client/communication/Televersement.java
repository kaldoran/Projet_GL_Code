/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import client.utils.ClientConstantes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaldoran
 */
public class Televersement implements Runnable {
    private Socket s;
    public static Thread t;
    private File f;
    
    private String adresse_repertoire_serveur;
    private String adresse_fichier_client;
    private int port_televersement;
    
    public Televersement() {
        /*String file = commande.substring(commande.indexOf(' ') + 1);
        
        try {
            s = new Socket(ClientConstantes.SERVEUR, ClientConstantes.PORTF);
        } catch (IOException ex) {
            Logger.getLogger(Televersement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("File Televersement : " + file);
        f = new File(ClientConstantes.FICHIER_SEND);
        t = new Thread(this);
        t.start();*/
    }

    @Override
    public void run() {
        
        try {
            
            try {
                s = new Socket(ClientConstantes.SERVEUR, port_televersement);
            } catch (IOException ex) {
                Logger.getLogger(Televersement.class.getName()).log(Level.SEVERE, null, ex);
            }
            FTPCommunication.transfert(
                    new FileInputStream(adresse_fichier_client),
                    s.getOutputStream(),
                    true);
            
            s.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Televersement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void demarrer(String adresse_fichier_client, String adresse_repertoire_serveur, int port_televersement) {
        this.adresse_fichier_client = adresse_fichier_client;
        this.adresse_repertoire_serveur = adresse_repertoire_serveur;
        this.port_televersement = port_televersement;
        
        t = new Thread(this);
        t.start();
    }
}
