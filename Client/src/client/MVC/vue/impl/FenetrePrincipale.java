/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.vue.impl;

import client.MVC.controleur.InterfaceControleurAuthentification;
import client.MVC.controleur.InterfaceControleurGestionnaireFichiers;
import client.MVC.model.InterfaceModeleAuthentification;
import client.MVC.model.InterfaceModeleGestionnaireFichiers;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


/**
 *
 * @author kevin
 */
public class FenetrePrincipale{
    private InterfaceModeleAuthentification modele_authentification;
    private InterfaceModeleGestionnaireFichiers modele_gestionnaireFichiers;
    private InterfaceControleurAuthentification controleur_auth;
    private InterfaceModeleGestionnaireFichiers controleur_gtnf;
    
    private JFrame cadre;
    private PanneauPrincipal panneau_principal;
    private PopUpAuthentification popup_authentification;
    private BarreMenu barre_menu;
    
    
    public FenetrePrincipale(InterfaceControleurAuthentification controleur_auth, InterfaceModeleAuthentification modele_authentification, InterfaceControleurGestionnaireFichiers controleur_gtnf, InterfaceModeleGestionnaireFichiers modele_gtnf) {
        this.controleur_auth = controleur_auth;
        this.modele_authentification = modele_authentification;
        this.modele_gestionnaireFichiers = modele_gtnf;
        
        this.setBaseConf();
        
        popup_authentification = new PopUpAuthentification(controleur_auth, modele_authentification);
    }

    public PopUpAuthentification getPopup_authentification() {
        return popup_authentification;
    }
        

    private void setBaseConf() {
        cadre = new javax.swing.JFrame("KouldNotShare Client");
        
        barre_menu = new BarreMenu();
                
        panneau_principal = new PanneauPrincipal((InterfaceControleurGestionnaireFichiers) controleur_gtnf, modele_gestionnaireFichiers);
        panneau_principal.setPreferredSize (new Dimension(1000, 600));
        
        panneau_principal.setBackground(Color.RED);
        
        cadre.setJMenuBar(barre_menu);
        cadre.setContentPane(panneau_principal);
        cadre.setLocation(300, 200);
        cadre.pack();
        cadre.setVisible(true);
        cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
