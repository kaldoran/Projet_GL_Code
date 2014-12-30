/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.modele.impl;

import client.MVC.model.InterfaceModeleGestionnaireFichiers;
import client.MVC.vu.ObservateurGestionnaireFichiers;
import client.utils.ClientConstantes;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author kevin
 */
public class GestionnaireFichiers implements InterfaceModeleGestionnaireFichiers {
    /** Attributs MVC */
    private ArrayList<ObservateurGestionnaireFichiers> observateurs;
    
    private final File repertoire_racine;
    private HashMap<String,File> hashmap_repertoires_client = null;
    private HashMap<String,File> hashmap_repertoires_serveur = null;
    private DefaultMutableTreeNode arborescence_client;
    private DefaultMutableTreeNode arborescence_serveur = null;
    
    public GestionnaireFichiers() {
        File[] tab_repertoires;
        observateurs = new ArrayList<ObservateurGestionnaireFichiers>();
        repertoire_racine = new File(ClientConstantes.REPERTOIRE_PARTAGE);
        // on recupère les lecteurs
        hashmap_repertoires_client = new HashMap<String, File>();
        hashmap_repertoires_serveur = new HashMap<String, File>();
        tab_repertoires = repertoire_racine.listFiles();
  
        System.out.println("|repertoire|" + tab_repertoires.length );
        // on définit notre premier noeud
        arborescence_client = new DefaultMutableTreeNode("partage client",true);
        // pour chaque lecteur
        for (int i = 1 ; i<tab_repertoires.length ; i++)
        {
            System.out.println(i + " " + tab_repertoires[i].getName());
            hashmap_repertoires_client.put(tab_repertoires[i].getName(), tab_repertoires[i]);
                // on recupère son contenu grace a getSubDirs
                DefaultMutableTreeNode root = explorerRepertoire(tab_repertoires[i]);
                // et on l ajoute a notre premier noeud
                arborescence_client.add(root);
        }
        
    }
    
    public DefaultMutableTreeNode getRacine() {
        return arborescence_client;
    }
    
    public DefaultMutableTreeNode explorerRepertoire(File repertoire_racine) {
        // on créé un noeud
        DefaultMutableTreeNode racine = new DefaultMutableTreeNode(repertoire_racine.getName(),true);

        // on recupère la liste des fichiers et sous rep 
        File[] list = repertoire_racine.listFiles();

        if ( list != null)
        {
                // pour chaque sous rep on appel cette methode => recursivité
                for (int j = 1 ; j<list.length ; j++)
                {
                    hashmap_repertoires_client.put(list[j].getName(), list[j]);
                    DefaultMutableTreeNode file = null;
                    if (list[j].isDirectory())
                    {	
                        file = explorerRepertoire(list[j]);  
                        racine.add(file);
                    }
                }
        }
        //notifierObservateur(racine);
        return racine;
    }

    @Override
    public void initialiserObservateur() {
        for( int i = 0 ; i < observateurs.size(); i++) {
            if(!(observateurs.get(i).isArboServeur())) {
                observateurs.get(i).actualiser(arborescence_client);
            }
        }
    }

    
    @Override
    public String obtenirAdresseFichierClient(String nom_fichier) {
        String adresse;

        if (hashmap_repertoires_client.isEmpty()) {
            adresse = "adresse fichier introuvable.";
            notifierObservateurAdresseFichierClient(adresse);
            return adresse;
        }

        if(!(hashmap_repertoires_client.containsKey(nom_fichier))) {
            adresse = "fichier inexistant.";
            notifierObservateurAdresseFichierClient(adresse);
            return adresse;
        }

        adresse = hashmap_repertoires_client.get(nom_fichier).getAbsolutePath();
        notifierObservateurAdresseFichierClient(adresse);
        return adresse;
        
    }
    
    @Override
    public String obtenirAdresseFichierServeur(String nom_fichier) {
        String adresse;
        
        if (hashmap_repertoires_serveur.isEmpty()) {
            adresse = "adresse fichier introuvable.";
            notifierObservateurAdresseFichierServeur(adresse);
            return adresse;
        }

        if(!(hashmap_repertoires_serveur.containsKey(nom_fichier))) {
            adresse = "fichier inexistant.";
            notifierObservateurAdresseFichierServeur(adresse);
            return adresse;
        }

        adresse = hashmap_repertoires_serveur.get(nom_fichier).getAbsolutePath();
        notifierObservateurAdresseFichierServeur(adresse);
        return adresse;
    }
    
    @Override
    public void enregistrerObservateur(ObservateurGestionnaireFichiers o) {
        observateurs.add(o);
    }

    @Override
    public void supprimerObservateur(ObservateurGestionnaireFichiers o) {
        observateurs.remove(o);
    }

    @Override
    public void notifierObservateurClient(DefaultMutableTreeNode racine) {
        for( int i = 0 ; i < observateurs.size(); i++) {
            if(!(observateurs.get(i).isArboServeur())) {
                observateurs.get(i).actualiser(arborescence_client);
            }
        }
    }
    
    @Override
    public void notifierObservateurServeur(DefaultMutableTreeNode racine) {
        if(racine != null) {
            arborescence_serveur = racine;
            
            for( int i = 0 ; i < observateurs.size(); i++) {
                if(observateurs.get(i).isArboServeur()) {
                    observateurs.get(i).actualiser(arborescence_serveur);
                }
            }
        }
    }


    @Override
    public void notifierObservateurAdresseFichierClient(String adresse_fichier) {
        if(adresse_fichier != null) {
            for( int i = 0 ; i < observateurs.size(); i++) {
                if(!(observateurs.get(i).isArboServeur())) {
                    observateurs.get(i).setAdresseFichier(adresse_fichier);
                }
            }
        }
    }

    @Override
    public void notifierObservateurAdresseFichierServeur(String adresse_fichier) {
        if(adresse_fichier != null) {
            for( int i = 0 ; i < observateurs.size(); i++) {
                if(observateurs.get(i).isArboServeur()) {
                    observateurs.get(i).setAdresseFichier(adresse_fichier);
                }
            }
        }
    }


}
