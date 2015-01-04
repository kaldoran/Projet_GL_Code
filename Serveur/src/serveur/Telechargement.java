/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
public class Telechargement implements Runnable{
    private String adresse_repertoire;
    private String nom_fichier;
    private int numero_port;
    
    private ServerSocket ss;
    private Socket s;
    public static Thread t;
    private File f;
    
    public Telechargement(String adresse_repertoire, String nom_fichier, int numero_port) {
        this.nom_fichier = File.separator+nom_fichier;
        this.adresse_repertoire = adresse_repertoire;
        this.numero_port = numero_port;
    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(numero_port);
            s = ss.accept();System.out.println("connexion de " + s.getRemoteSocketAddress().toString());
        } catch (IOException ex) {
            Logger.getLogger(Telechargement.class.getName()).log(Level.SEVERE, null, ex);
            this.numero_port++;
        }
        try {
            FTPCommunication.transfert(
                s.getInputStream(),
                new FileOutputStream(adresse_repertoire + nom_fichier),
                true);
        
            s.close();
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(Telechargement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void demarrer() {
        t = new Thread(this);
        t.start();
    }
    
}