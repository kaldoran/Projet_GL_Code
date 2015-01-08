/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.modele.impl;

import beans.BeanAuthentification;
import client.MVC.model.InterfaceModeleAuthentification;
import client.MVC.vu.ObservateurAuthentification;
import client.communication.Communication;

/**
 *
 * @author kevin
 */
public class Authentification implements InterfaceModeleAuthentification {
    /**Attributs MVC*/
    private ObservateurAuthentification observateur;
    /**Attributs autre*/
    private Communication communication;
    private BeanAuthentification beanAuthentification;
    
    private boolean authentificationValide = false;
    
    public Authentification(){
        communication = new Communication();
    }

  
    
    @Override
    public Communication getCommunication() {
        return communication;
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
        
        if(authentificationValide) {
            observateur.fermer();
        } else {
            observateur.informerMessageErreur(0);
            observateur.actualiser("Erreur", "Erreur");
        }
        
    }

    @Override
    public void creerAuthentification(String login, String mot_de_passe) {
        beanAuthentification = new BeanAuthentification(login, mot_de_passe);
    }

    @Override
    public void genererMessageErreur(int i) {
        observateur.informerMessageErreur(i);
    }

    @Override
    public boolean estAuthentifie() {
        return authentificationValide;
    }
   
}
