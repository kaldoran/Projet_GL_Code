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
import javax.swing.JPanel;


/**
 *
 * @author kevin
 */
public class FenetrePrincipale{
    private InterfaceModeleAuthentification modele_authentification;
    private InterfaceControleurAuthentification controleur;
    
    JFrame cadre;
    JPanel panneau;
    PopUpAuthentification popup_authentification;
    
    public FenetrePrincipale(InterfaceControleurAuthentification controleur, InterfaceModeleAuthentification modele_authentification) {
        this.controleur = controleur;
        this.modele_authentification = modele_authentification;
        
        cadre = new javax.swing.JFrame("Premiere fenetre");
        panneau = new JPanel();
        this.setBaseConf();
        
        popup_authentification = new PopUpAuthentification(controleur, modele_authentification);
    }
    
    
    

    private void setBaseConf() {
        panneau.setPreferredSize (new Dimension(250, 150));
        
        //panneau.setBackground(Color.RED);
        
        cadre.setContentPane(panneau);
        cadre.setLocation(400, 300);
        cadre.pack();
        cadre.setVisible(true);
        cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
