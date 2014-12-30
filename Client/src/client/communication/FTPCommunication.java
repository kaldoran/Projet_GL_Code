/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import beans.BeanAuthentification;
import beans.BeanInformationServeur;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
class FTPCommunication {

    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public FTPCommunication() {
    }
    
    
    
    public BeanInformationServeur demanderInformationServeur(Socket socket, ObjectOutputStream oos, ObjectInputStream ois, BeanInformationServeur beanInfoServ) {
        BeanInformationServeur bean_rcpt = null;
        
        try {
            this.oos = oos;
            this.ois = ois;
            
            this.oos.writeObject(beanInfoServ);
            
            /** attente de la reponse du serveur*/
            while(bean_rcpt == null) {
                bean_rcpt = (BeanInformationServeur) ois.readObject();
            }
        } catch (IOException ex) {
            Logger.getLogger(FTPCommunication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FTPCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bean_rcpt;
    }
    
}
