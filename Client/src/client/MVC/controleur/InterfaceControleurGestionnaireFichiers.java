/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.controleur;

/**
 *
 * @author kevin
 */
public interface InterfaceControleurGestionnaireFichiers {

    public void traiterSelectionFichier(String nom_fichier, boolean ARBORESCENCE_SERVEUR, boolean estUnFichier);
}
