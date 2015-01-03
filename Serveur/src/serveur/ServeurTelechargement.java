/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.net.ServerSocket;

/**
 *
 * @author kevin
 */
public class ServeurTelechargement implements Runnable {
    private String adresse_fichier;
    private int pour_serveur;
    private ServerSocket ss;
    
    
    public ServeurTelechargement() {
    }

    
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
