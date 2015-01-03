/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import client.utils.ClientConstantes;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
        OutputStream out = null;
        
        try {
            s = new Socket(ClientConstantes.SERVEUR, port_televersement);
        } catch (IOException ex) {
            Logger.getLogger(Televersement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            out = s.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(Televersement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        f = new File(adresse_fichier_client);
        try {
            byte[] mybytearray = new byte[(int) f.length() + 1];
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);
            out.write(mybytearray, 0, mybytearray.length);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Televersement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            out.close();
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
