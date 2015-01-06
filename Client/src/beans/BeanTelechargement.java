/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author kevin
 */
public class BeanTelechargement implements Serializable{
    
    private String nom_fichier;
    private String adresse_fichier;
    private int port_telechargement;

    public BeanTelechargement(String adresse_fichier) {
        this.adresse_fichier = adresse_fichier;
    }
    
    public String getNom_fichier() {
        return nom_fichier;
    }

    public void setNom_fichier(String nom_fichier) {
        this.nom_fichier = nom_fichier;
    }
    
    public String getAdresse_fichier() {
        return adresse_fichier;
    }

    public void setAdresse_fichier(String adresse_fichier) {
        this.adresse_fichier = adresse_fichier;
    }

    public int getPort_telechargement() {
        return port_telechargement;
    }

    public void setPort_telechargement(int port_telechargement) {
        this.port_telechargement = port_telechargement;
    }
    
}
