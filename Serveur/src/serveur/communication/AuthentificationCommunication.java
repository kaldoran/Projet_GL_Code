/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.communication;

import beans.BeanAuthentification;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.GestionCompte;


/**
 *
 * @author kevin
 */
class AuthentificationCommunication {
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private Socket socket;
    
    public AuthentificationCommunication(Socket s) {
        
        try {
            socket = s;
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public boolean ValiderAuthentification() {
        boolean estValide = false;
        BeanAuthentification beanAuth = null;
        
        while(beanAuth == null) {
            try {
                beanAuth = (BeanAuthentification) ois.readObject();
            } catch (IOException ex) {
                Logger.getLogger(AuthentificationCommunication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AuthentificationCommunication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        estValide = GestionCompte.isCorrect(beanAuth.getLogin(), beanAuth.getMot_de_passe());
        
        try {
            if(estValide) {
                beanAuth.Valider();
                oos.writeObject(beanAuth);
            }
            
            oos.close();
            ois.close();
        } catch (IOException ex) {
                    Logger.getLogger(AuthentificationCommunication.class.getName()).log(Level.SEVERE, null, ex);
                }
        return estValide;
    }
    
}
