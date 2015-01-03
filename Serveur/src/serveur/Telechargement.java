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
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.utils.ServeurConstantes;

/**
 *
 * @author kaldoran
 */
public class Telechargement implements Runnable{
    private String adresse_repertoire;
    private int numero_port;
    
    private ServerSocket ss;
    private Socket s;
    public static Thread t;
    private File f;
    
    public Telechargement(String adresse_repertoire, int numero_port) {
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
            InputStream in = s.getInputStream();
            
            int filesize = 6022386;
            int bytesRead;
            int current = 0;
            byte[] mybytearray = new byte[filesize];
            
            FileOutputStream fos = new FileOutputStream(adresse_repertoire + "/file1");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bytesRead = in.read(mybytearray, 0, mybytearray.length);
            current = bytesRead;
            
            do {
                    bytesRead = in.read(mybytearray, current,
                            (mybytearray.length - current));
                    if (bytesRead >= 0)
                        current += bytesRead;
            } while (bytesRead > -1);
            
            bos.write(mybytearray, 0, current);
            bos.flush();
            bos.close();
            fos.close();
            in.close();
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