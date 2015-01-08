/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import serveur.MVC.vue.impl.FenetrePrincipale;

/**
 *
 * @author kaldoran
 */
public class MainServeur {
    private int nbClients = 0; // nombre total de clients connectés

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();
        Serveur serveur = new Serveur();
    }
}
