package client.MVC.controleur.impl;


import client.MVC.controleur.InterfaceControleur;
import client.MVC.controleur.InterfaceControleurAuthentification;
import client.MVC.controleur.InterfaceControleurGestionnaireFichiers;
import client.MVC.controleur.InterfaceControleurTelechargementTeleversement;
import client.MVC.model.InterfaceModeleAuthentification;
import client.MVC.model.InterfaceModeleGestionnaireFichiers;
import client.MVC.model.InterfaceModeleTelechargementTeleversement;
import client.MVC.vue.impl.FenetrePrincipale;
import client.utils.MessagesErreur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kevin
 */


public class Administration implements InterfaceControleur{
    private FenetrePrincipale fenetre_principale;
    
    private InterfaceModeleAuthentification modele_auth;
    private InterfaceModeleGestionnaireFichiers modele_gtnf;
    private InterfaceModeleTelechargementTeleversement modele_TlgTlv;

    public Administration(  InterfaceModeleAuthentification modele_auth, 
                            InterfaceModeleGestionnaireFichiers modele_gtnf, 
                            InterfaceModeleTelechargementTeleversement modele_TlgTlv) {
        this.modele_auth = modele_auth;
        this.modele_gtnf = modele_gtnf;
        this.modele_TlgTlv = modele_TlgTlv;
        
        fenetre_principale =  new FenetrePrincipale((InterfaceControleurAuthentification)this, modele_auth, 
                                                    (InterfaceControleurGestionnaireFichiers)this, modele_gtnf,
                                                    (InterfaceControleurTelechargementTeleversement)this, modele_TlgTlv);
        
        fenetre_principale.ouvrirPopUpAuthentification();
        modele_gtnf.initialiserObservateur();
        modele_TlgTlv.initialiserObservateur();
    }
    
    
        
    @Override
    public void setBeanAuthentification(String login, String mot_de_passe) {
        if(login.length() == 0 || mot_de_passe.length() == 0) {
            modele_auth.genererMessageErreur(1);
        } else if(login.length() >= 20) {
            modele_auth.genererMessageErreur(2);
        } else if( mot_de_passe.length() < 4) {
            modele_auth.genererMessageErreur(3);
        } else {
            modele_auth.creerAuthentification(login, mot_de_passe);
            modele_auth.authentifier();
            if(modele_auth.estAuthentifie()) {
                modele_TlgTlv.recupererCommunication(modele_auth.getCommunication());
                modele_TlgTlv.recupererInformationServeur();
                modele_gtnf.setHashMap_repertoires_serveur(modele_TlgTlv.getInformationServeur().getHashmap_serveur());
                modele_gtnf.notifierObservateurServeur(modele_TlgTlv.getInformationServeur().getArborescence());
            }
        }
        
    }

    @Override
    public void traiterSelectionFichier(String nom_fichier, boolean ARBORESCENCE_SERVEUR, boolean estUnFichier) {
        String adresse_fichier = null;
        System.out.println("traiterSelection fichier : " + nom_fichier + " | " + ARBORESCENCE_SERVEUR + " | " + estUnFichier);
        if(!(ARBORESCENCE_SERVEUR)) {
            adresse_fichier = modele_gtnf.obtenirAdresseFichierClient(nom_fichier);
            modele_TlgTlv.setAdresseFichierClient(adresse_fichier, estUnFichier);
            System.out.println("arborescence client : " + adresse_fichier);
        } else {
            adresse_fichier = modele_gtnf.obtenirAdresseFichierServeur(nom_fichier);
            modele_TlgTlv.setAdresseFichierServeur(adresse_fichier, estUnFichier);
            System.out.println("arborescence serveur : " + adresse_fichier);
        }
        System.out.println(" ");
        System.out.println(" ");
    }
   
    @Override
    public void fermerApplication() {
        System.exit(0);
    }

    @Override
    public void initialiserTelechargement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialiserTeleversement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
