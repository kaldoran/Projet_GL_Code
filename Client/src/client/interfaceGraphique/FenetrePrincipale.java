/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.interfaceGraphique;

import client.MVC.InterfaceControleurAuthentification;
import client.MVC.InterfaceModeleAuthentification;
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
    private InterfaceControleurAuthentification controleur;
    
    private JFrame cadre;
    private PanneauPrincipal panneau_principal;
    private PopUpAuthentification popup_authentification;
    private BarreMenu barre_menu;
    
    
    public FenetrePrincipale(InterfaceControleurAuthentification controleur, InterfaceModeleAuthentification modele_authentification) {
        this.controleur = controleur;
        this.modele_authentification = modele_authentification;
        
        this.setBaseConf();
        
        popup_authentification = new PopUpAuthentification(controleur, modele_authentification);
    }

    public PopUpAuthentification getPopup_authentification() {
        return popup_authentification;
    }
        

    private void setBaseConf() {
        cadre = new javax.swing.JFrame("KouldNotShare Client");
        
        barre_menu = new BarreMenu();
                
        panneau_principal = new PanneauPrincipal();
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
