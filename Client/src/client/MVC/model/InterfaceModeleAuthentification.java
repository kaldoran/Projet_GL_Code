/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.model;

import client.MVC.vu.ObservateurAuthentification;
import beans.BeanAuthentification;

/**
 *
 * @author kevin
 */
public interface InterfaceModeleAuthentification {
    
    void creerAuthentification (String login, String mot_de_passe);
    
    void authentifier ();
    
    void enregistrerObservateur (ObservateurAuthentification o);
    
    void supprimerObservateur (ObservateurAuthentification o);
    
    void notifierObservateur ();
    
}
