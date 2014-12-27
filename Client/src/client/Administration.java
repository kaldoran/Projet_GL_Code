package client;

import beans.BeanAuthentification;
import client.MVC.InterfaceControleurAuthentification;
import client.MVC.InterfaceModeleAuthentification;
import client.interfaceGraphique.FenetrePrincipale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kevin
 */


public class Administration implements InterfaceControleurAuthentification{
    InterfaceModeleAuthentification modele;
    FenetrePrincipale fenetre_principale;

    public Administration(InterfaceModeleAuthentification modele) {
        this.modele = modele;
        fenetre_principale =  new FenetrePrincipale(this, modele);
    }
    
    
        
    @Override
    public void setBeanAuthentification(String login, String mot_de_passe) {
        if(login.length() == 0 || mot_de_passe.length() == 0) {
            fenetre_principale.getPopup_authentification().informerMessageErreur(1);
        } else if(login.length() >= 20) {
            fenetre_principale.getPopup_authentification().informerMessageErreur(2);
        } else if( mot_de_passe.length() < 4) {
            fenetre_principale.getPopup_authentification().informerMessageErreur(3);
        } else {
            
        }
        modele.creerAuthentification(login, mot_de_passe);
        modele.authentifier();
    }
    
}
