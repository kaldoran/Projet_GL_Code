/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.model;

import beans.BeanInformationServeur;
import client.MVC.vu.ObservateurTelechargementTeleversement;
import client.communication.Communication;

/**
 *
 * @author kevin
 */
public interface InterfaceModeleTelechargementTeleversement {
    
    void Telecharger();
    
    void Televerser();
    
    void recupererCommunication(Communication communication);
    
    void recupererInformationServeur();
    
    BeanInformationServeur getInformationServeur();
    
    String getAdresseFichierClient();
    
    String getAdresseFichierServeur();
    
    void setAdresseFichierClient(String adresse_fichier, boolean estUnFichier);
    
    void setAdresseFichierServeur(String adresse_fichier, boolean estUnFichier);
    
    void enregistrerObservateur (ObservateurTelechargementTeleversement o);
    
    void supprimerObservateur (ObservateurTelechargementTeleversement o);
    
    void notifierObservateur();
    
    void initialiserObservateur();
    
}
