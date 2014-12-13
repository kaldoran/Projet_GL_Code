/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.utils.ServeurConstantes;

/**
 *
 * @author kaldoran
 */
public class Televersement implements Runnable {

    private ServerSocket ss;
    private Socket s;
    public static Thread t;
    private File f;

    public Televersement(String file, String path) {
        try {
            ss = new ServerSocket(ServeurConstantes.PORTF);
            s = ss.accept();
        } catch (IOException ex) {
            Logger.getLogger(Televersement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("File Televersement : " + path + "/" + file);
        f = new File(path + "/" + file);

        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            OutputStream out;
            out = s.getOutputStream();

            FileInputStream fis = null;

            byte[] mybytearray = new byte[(int) f.length() + 1];
            fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);

            out.write(mybytearray, 0, mybytearray.length);
            out.flush();
            
            out.close();
            s.close();
            ss.close();

        } catch (IOException ex) {
            Logger.getLogger(Televersement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
