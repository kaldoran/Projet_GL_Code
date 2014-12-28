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
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author kevin
 */
public class GestionnaireFichiers implements InterfaceModeleGestionnaireFichiers {
    /** Attributs MVC */
    private ArrayList<ObservateurGestionnaireFichiers> observateurs;
    
    private final File repertoire_racine;
    private File[] repertoires;
    private DefaultMutableTreeNode racine;
    
    public GestionnaireFichiers() {
        observateurs = new ArrayList<ObservateurGestionnaireFichiers>();
        repertoire_racine = new File(ClientConstantes.REPERTOIRE_PARTAGE);
        // on recupère les lecteurs
        repertoires = repertoire_racine.listFiles();
        System.out.println("|repertoire|" + repertoires.length );
        // on définit notre premier noeud
        racine = new DefaultMutableTreeNode("partage client",true);
        // pour chaque lecteur
        for (int i = 1 ; i<repertoires.length ; i++)
        {
            System.out.println(i + " " + repertoires[i].getName());
                // on recupère son contenu grace a getSubDirs
                DefaultMutableTreeNode root = explorerRepertoire(repertoires[i]);
                // et on l ajoute a notre premier noeud
                racine.add(root);
        }
        
    }
    
    public DefaultMutableTreeNode getRacine() {
        return racine;
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
                        DefaultMutableTreeNode file = null;
                        if (list[j].isDirectory())
                        {	file = explorerRepertoire(list[j]);  
                                racine.add(file);
                        }
                }
        }
        //notifierObservateur(racine);
        return racine;
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
    public void notifierObservateur(DefaultMutableTreeNode racine) {
        for( int i = 0 ; i < observateurs.size(); i++) {
            observateurs.get(i).actualiser(racine);
        }
    }

    @Override
    public void initialiserObservateur() {
        for( int i = 0 ; i < observateurs.size(); i++) {
            observateurs.get(i).actualiser(racine);
        }
    }

}
