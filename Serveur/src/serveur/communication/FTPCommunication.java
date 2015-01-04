/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author kevin
 */
public class FTPCommunication {
    
    
    
    
    public static void transfert(InputStream in, OutputStream out, boolean closeOnExit) throws IOException
    {
        byte buf[] = new byte[1024];
        
        int n;
        while((n=in.read(buf))!=-1)
            out.write(buf,0,n);
            out.flush();
        if (closeOnExit)
        {
            in.close();
            out.close();
        }
    }
}
