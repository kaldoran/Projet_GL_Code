/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.model;

import client.MVC.vu.ObservateurGestionnaireFichiers;
import java.io.File;
import java.util.HashMap;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author kevin
 */
public interface InterfaceModeleGestionnaireFichiers {
    
    boolean estUnRepertoire(String adresse_fichier, boolean depuis_serveur);
    
    DefaultMutableTreeNode explorerRepertoire(File repertoire_racine);
    
    public String obtenirAdresseFichierClient(String nom_fichier);
    
    String obtenirAdresseFichierServeur(String nom_fichier);
    
    void setHashMap_repertoires_serveur (HashMap<String, File> hashmap);
    
    void enregistrerObservateur (ObservateurGestionnaireFichiers o);
    
    void supprimerObservateur (ObservateurGestionnaireFichiers o);
    
    void notifierObservateurServeur(DefaultMutableTreeNode racine);
    
    void notifierObservateurClient(DefaultMutableTreeNode racine);
    
    void notifierObservateurAdresseFichierClient(String adresse_fichier);
    
    void notifierObservateurAdresseFichierServeur(String adresse_fichier);
    
    void initialiserObservateur();
}
