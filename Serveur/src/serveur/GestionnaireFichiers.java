/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import serveur.utils.ServeurConstantes;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author kevin
 */
public class GestionnaireFichiers {
    /** Attributs MVC */
    
    private final File repertoire_racine;
    private HashMap<String,File> hashmap_repertoires_client = null;
    private HashMap<String,File> hashmap_repertoires_serveur = null;
    private DefaultMutableTreeNode arborescence_client;
    private DefaultMutableTreeNode arborescence_serveur = null;
    
    public GestionnaireFichiers() {
        File[] tab_repertoires;
        
        repertoire_racine = new File(ServeurConstantes.REPERTOIRE_PARTAGE);
        // on recupère les lecteurs
        hashmap_repertoires_client = new HashMap<String, File>();
        tab_repertoires = repertoire_racine.listFiles();
  
        //System.out.println("|repertoire|" + tab_repertoires.length );
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
    
    public HashMap<String,File> getHashMap_repertoires_client() {
        return hashmap_repertoires_client;
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
                    {	System.out.println("REPERTOIRE");
                        file = explorerRepertoire(list[j]);  
                        racine.add(file);
                    }else {
                        file = new DefaultMutableTreeNode(list[j].getName(),true);
                        racine.add(file);
                    }
                }
        }
        //notifierObservateur(racine);
        return racine;
    }

    
}
