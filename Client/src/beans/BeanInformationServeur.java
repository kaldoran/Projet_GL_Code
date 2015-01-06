/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author kevin
 */
public class BeanInformationServeur implements Serializable{
    private String nom;
    private DefaultMutableTreeNode arborescence;
    private HashMap<String,File> hashmap_serveur;

    public BeanInformationServeur() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public DefaultMutableTreeNode getArborescence() {
        return arborescence;
    }

    public void setArborescence(DefaultMutableTreeNode arborescence) {
        this.arborescence = arborescence;
    }
    
    public HashMap<String, File> getHashmap_serveur() {
        return hashmap_serveur;
    }

    public void setHashmap_serveur(HashMap<String, File> hashmap_serveur) {
        this.hashmap_serveur = hashmap_serveur;
    }
}
