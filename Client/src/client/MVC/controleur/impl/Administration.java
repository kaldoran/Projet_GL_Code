package client.MVC.controleur.impl;


import client.MVC.controleur.InterfaceControleurAuthentification;
import client.MVC.controleur.InterfaceControleurGestionnaireFichiers;
import client.MVC.model.InterfaceModeleAuthentification;
import client.MVC.model.InterfaceModeleGestionnaireFichiers;
import client.MVC.vue.impl.FenetrePrincipale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kevin
 */


public class Administration implements InterfaceControleurAuthentification, InterfaceControleurGestionnaireFichiers{
    private InterfaceModeleAuthentification modele_auth;
    private FenetrePrincipale fenetre_principale;
    
    private InterfaceModeleGestionnaireFichiers modele_gtnf;

    public Administration(InterfaceModeleAuthentification modele_auth, InterfaceModeleGestionnaireFichiers modele_gtnf) {
        this.modele_auth = modele_auth;
        this.modele_gtnf = modele_gtnf;
        fenetre_principale =  new FenetrePrincipale(this, modele_auth, this, modele_gtnf);
        modele_gtnf.initialiserObservateur();
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
        modele_auth.creerAuthentification(login, mot_de_passe);
        modele_auth.authentifier();
    }
    
}
