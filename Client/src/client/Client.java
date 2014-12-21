/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.MVC.InterfaceModeleAuthentification;
import client.MVC.ObservateurAuthentification;
import static client.MainClient.socket;
import client.communication.Communication;
import client.interfaceGraphique.FenetrePrincipale;
import client.utils.ClientConstantes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class Client implements ObservateurAuthentification{
    
    /**Attributs MVC */
    private InterfaceModeleAuthentification modele_authentification;
    private InterfaceControleur controleur;
    
    /**Attributs autre*/
    private Communication communication;
    
    /**Attributs interface graphique client */
    private FenetrePrincipale fen_principale;

    public Client( InterfaceModeleAuthentification modele, InterfaceControleur controleur) {
        /**Code intialisation MVC */
        modele_authentification = modele;
        this.controleur = controleur;
        modele_authentification.enregistrerObservateur(this);
        
        
      
    }
    
    private void creerInterfaceGraphique() {
        fen_principale = new FenetrePrincipale();
    }

    @Override
    public void actualiser(String login, String mot_de_passe) {
        System.out.println("login : " + login);
        System.out.println("mot de passe : " + mot_de_passe);
    }
    
    
    
    
}
