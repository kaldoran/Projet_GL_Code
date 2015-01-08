/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.model;

import client.MVC.vu.ObservateurAuthentification;
import client.communication.Communication;

/**
 *
 * @author kevin
 */
public interface InterfaceModeleAuthentification {
   
    void genererMessageErreur(int i);
    
    void creerAuthentification (String login, String mot_de_passe);
    
    void authentifier ();
    
    boolean estAuthentifie();
    
    Communication getCommunication();
    
    void enregistrerObservateur (ObservateurAuthentification o);
    
    void supprimerObservateur (ObservateurAuthentification o);
    
    void notifierObservateur ();
    
}
