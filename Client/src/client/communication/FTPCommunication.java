/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import beans.BeanAuthentification;
import beans.BeanInformationServeur;
import beans.BeanTelechargement;
import beans.BeanTeleversement;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
    private Telechargement processus_telechargement;
    private Televersement processus_televersement;

    public FTPCommunication() {
        processus_telechargement = new Telechargement();
        processus_televersement = new Televersement();
    }
    
    
    
    public BeanInformationServeur demanderInformationServeur(ObjectOutputStream oos, ObjectInputStream ois, BeanInformationServeur beanInfoServ) {
        BeanInformationServeur bean_rcpt = null;
        
        try {
            oos.writeObject(beanInfoServ);
            oos.flush();
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
    
    public void lancerTelechargement(String adresse_fichier_serveur, String adresse_repertoire_client, int port_telechargement) {
        processus_telechargement.demarrer(adresse_fichier_serveur, adresse_repertoire_client, port_telechargement);
    }
    
    public void lancerTeleversement(String adresse_fichier_client, String adresse_repertoire_serveur, int port_televersement) {
        processus_televersement.demarrer(adresse_fichier_client, adresse_repertoire_serveur, port_televersement);
    }

    public BeanTelechargement demanderTelechargement(ObjectOutputStream oos, ObjectInputStream ois, BeanTelechargement bean_telechargement) {
        BeanTelechargement bean_rcpt = null;
       
        try {
            oos.writeObject(bean_telechargement);
            oos.flush();
            while(bean_rcpt == null) {
                bean_rcpt = (BeanTelechargement) ois.readObject();
            }
        } catch (IOException ex) {
            Logger.getLogger(FTPCommunication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FTPCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bean_rcpt;
    }

    BeanTeleversement demanderTeleversement(ObjectOutputStream oos, ObjectInputStream ois, BeanTeleversement bean_televersement) {
        BeanTeleversement bean_rcpt = null;
       
        try {
            oos.writeObject(bean_televersement);
            oos.flush();
            while(bean_rcpt == null) {
                bean_rcpt = (BeanTeleversement) ois.readObject();
            }
        } catch (IOException ex) {
            Logger.getLogger(FTPCommunication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FTPCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bean_rcpt;
    }
    
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
