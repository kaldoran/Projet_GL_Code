/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import beans.BeanAuthentification;
import client.MVC.InterfaceControleurAuthentification;
import client.MVC.InterfaceModeleAuthentification;
import client.MVC.ObservateurAuthentification;
import static client.MainClient.socket;
import client.communication.Communication;
import client.interfaceGraphique.FenetrePrincipale;
import client.interfaceGraphique.PopUpAuthentification;
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
public class Client implements InterfaceModeleAuthentification{
    /**Attributs MVC*/
    private Object observateur;
    /**Attributs autre*/
    private Communication communication;
    private BeanAuthentification beanAuthentification;
    
    private boolean authentificationValide = false;
    
    public Client(){
        communication = new Communication();
    }

  

    @Override
    public void authentifier() {
        communication.initialiserCommunication();
        authentificationValide = communication.procederAuthentification(beanAuthentification);
        notifierObservateur();
    }

    @Override
    public void enregistrerObservateur(ObservateurAuthentification o) {
        observateur = o;
    }

    @Override
    public void supprimerObservateur(ObservateurAuthentification o) {
        observateur = null;
    }

    @Override
    public void notifierObservateur() {
        ObservateurAuthentification obsAuth = (ObservateurAuthentification)observateur;
        if(authentificationValide) {
            obsAuth.fermer();
        } else {
            obsAuth.informerMessageErreur();
            obsAuth.actualiser("Erreur", "Erreur");
        }
        
    }

    @Override
    public void creerAuthentification(String login, String mot_de_passe) {
        beanAuthentification = new BeanAuthentification(login, mot_de_passe);
    }

   
}
