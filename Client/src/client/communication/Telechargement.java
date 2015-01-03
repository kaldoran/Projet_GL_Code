/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import client.utils.ClientConstantes;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaldoran
 */
public class Telechargement implements Runnable {
    private Socket s;
    public static Thread t;
    private String file;
    
    private String adresse_fichier_serveur;
    private String adresse_repertoire_client;
    private int port_telechargement;
    
    public Telechargement() {
        
        /*file = commande.substring(commande.indexOf(' ') + 1);
        
        try {
            s = new Socket(ClientConstantes.SERVEUR, ClientConstantes.PORTF);
        } catch (IOException ex) {
            Logger.getLogger(Telechargement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("File Telechargement : " + file);
        
        t = new Thread(this);
        t.start();*/
    }

    @Override
    public void run() {
        InputStream in = null;
        
        try {
            s = new Socket(ClientConstantes.SERVEUR, port_telechargement);
            in = s.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(Telechargement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            int filesize = 6022386;
            int bytesRead;
            int current = 0;
            byte[] mybytearray = new byte[filesize];
            InputStream is = null;
            BufferedOutputStream bos = null;
            FileOutputStream fos;

            fos = new FileOutputStream(adresse_repertoire_client+"/file1");
            bos = new BufferedOutputStream(fos);
            bytesRead = in.read(mybytearray, 0, mybytearray.length);
            current = bytesRead;
            
            do {    System.out.print(".");
                bytesRead = in.read(mybytearray, current,
                        (mybytearray.length - current));
                if (bytesRead >= 0) {
                    current += bytesRead;
                }
            } while (bytesRead > -1);

            bos.write(mybytearray, 0, current);
            bos.flush();
            bos.close();
            fos.close();
            
            in.close();
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Telechargement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void demarrer(String adresse_fichier_serveur, String adresse_repertoire_client, int port_telechargement) {
        this.adresse_fichier_serveur = adresse_fichier_serveur;
        this.adresse_repertoire_client = adresse_repertoire_client;
        this.port_telechargement = port_telechargement;
        
        t = new Thread(this);
        t.start();
        
    }
}
