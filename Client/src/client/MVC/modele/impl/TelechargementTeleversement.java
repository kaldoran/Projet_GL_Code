/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.modele.impl;

import beans.BeanInformationServeur;
import client.MVC.model.InterfaceModeleTelechargementTeleversement;
import client.MVC.vu.ObservateurTelechargementTeleversement;
import client.communication.Communication;
import client.utils.MessagesErreur;

/**
 *
 * @author kevin
 */
public class TelechargementTeleversement implements InterfaceModeleTelechargementTeleversement {

    private ObservateurTelechargementTeleversement observateur;
    
    private Communication communication;
    private BeanInformationServeur bean_info_serveur;
    
    private String adresse_fichier_client = null;
    private String adresse_fichier_serveur = null;
    
    private boolean operationPossible = false;
    private boolean fichier_client_est_un_fichier = false;
    private boolean fichier_serveur_est_un_fichier = false;
    
    
    public TelechargementTeleversement() {
        
    }
    
    @Override
    public String getAdresseFichierClient() {
    return adresse_fichier_client;
    }

    @Override
    public String getAdresseFichierServeur() {
        return adresse_fichier_serveur;
    }
    
    @Override
    public void setAdresseFichierClient(String adresse_fichier, boolean estUnFichier) {
        adresse_fichier_client = adresse_fichier;
        fichier_client_est_un_fichier = estUnFichier;
        this.operationPossible();
        if(operationPossible) {
            this.miseAjourBoutons();
        }
    }

    @Override
    public void setAdresseFichierServeur(String adresse_fichier, boolean estUnFichier) {
        adresse_fichier_serveur = adresse_fichier;
        fichier_serveur_est_un_fichier = estUnFichier;
        this.operationPossible();
        if(operationPossible) {            
            this.miseAjourBoutons();
        } else {
            initialiserObservateur();
        }
    }
    
    @Override
    public void Telecharger() {
        communication.procederTelechargement(adresse_fichier_serveur, adresse_fichier_client);
    }

    @Override
    public void Televerser() {
        communication.procederTeleversement(adresse_fichier_serveur, adresse_fichier_client);
    }

    
    @Override
    public void recupererCommunication(Communication communication) {
        this.communication = communication;
    }

    @Override
    public void recupererInformationServeur() {
        BeanInformationServeur beanInfoServeur = new BeanInformationServeur();
        bean_info_serveur = communication.procederRecuperationInformation(beanInfoServeur);
    }

    @Override
    public BeanInformationServeur getInformationServeur() {
        return bean_info_serveur;
    }
    
    @Override
    public void enregistrerObservateur(ObservateurTelechargementTeleversement o) {
        observateur = o;
    }

    @Override
    public void supprimerObservateur(ObservateurTelechargementTeleversement o) {
        observateur = null;
    }

    @Override
    public void notifierObservateur() {
        //traitement vide;
    }

    @Override
    public void initialiserObservateur() {
        observateur.initialiserBoutons();
    }

    private void operationPossible() {
        if(adresse_fichier_client == null || adresse_fichier_serveur == null) {
            operationPossible = false;
            return;
        }
        for(MessagesErreur message : MessagesErreur.values()) {
            if(adresse_fichier_client.equals(message) || adresse_fichier_serveur.equals(message)) {
                operationPossible = false;
                return;
            }
        }
        operationPossible = true;
    }

    private void activerBoutonTeleversement() {
        observateur.activerBoutonTeleverser();
    }

    private void activerBoutonTelechargement() {
        observateur.activerBoutonTelecharger();
    }

    private void miseAjourBoutons() {
        if(fichier_client_est_un_fichier && !(fichier_serveur_est_un_fichier)) {
            activerBoutonTeleversement();
        }else if(!(fichier_client_est_un_fichier) && fichier_serveur_est_un_fichier) {
            activerBoutonTelechargement();
        } else {
            initialiserObservateur();
        }
    }
}
