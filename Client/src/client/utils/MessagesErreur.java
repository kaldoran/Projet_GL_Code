/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.utils;

/**
 *
 * @author kevin
 */
public enum MessagesErreur {

    FICHIER_INTROUVABLE("adresse fichier introuvable."),
    FICHIER_INEXISTANT("fichier inexistant.");
    
    
    private String name = "";
    
    //Constructeur
    MessagesErreur(String name){
        this.name = name;
    }
   
    public String toString(){
        return name;
    }
}
