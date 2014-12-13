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
    private ServerSocket ss;
    private Socket s;
    public static Thread t;
    private File f;
    
    public Telechargement(String file, String path) {
        try {
            ss = new ServerSocket(ServeurConstantes.PORTF);
            s = ss.accept();
        } catch (IOException ex) {
            Logger.getLogger(Telechargement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.err.println("File Telechargement : " + path + "/" + file);
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        
        try {
            InputStream in = s.getInputStream();
            
            int filesize = 6022386;
            int bytesRead;
            int current = 0;
            byte[] mybytearray = new byte[filesize];
            
            FileOutputStream fos = new FileOutputStream(ServeurConstantes.FICHIER_SORTIE_DDL);
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
            
            in.close();
            s.close();
            ss.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Telechargement.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
    
    
}