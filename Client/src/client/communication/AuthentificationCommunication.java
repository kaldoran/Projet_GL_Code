/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import beans.BeanAuthentification;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author kevin
 */
class AuthentificationCommunication {
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    
    public AuthentificationCommunication() {
    
    }
    
    boolean authentifier(ObjectOutputStream oos, ObjectInputStream ois, BeanAuthentification beanAuth) {
        BeanAuthentification bean_rcpt = null;
        
        /** envoie du bean authentification */
        try {
            oos.writeObject(beanAuth);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /** attente de la reponse du serveur*/
        while(bean_rcpt == null) {
            try {
                bean_rcpt = (BeanAuthentification) ois.readObject();
            } catch (IOException ex) {
                Logger.getLogger(AuthentificationCommunication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AuthentificationCommunication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*try {
            oos.close();
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        /** retourner la validation de l'authentifcation */
        return bean_rcpt.isValide();
    }

}
