/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.MVC.vue.impl;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;


/**
 *
 * @author kevin
 */
public class FenetrePrincipale{
    
    /** Attributs MVC */
    
    /** Attribut de la fenetre*/
    private JFrame cadre;
    private PanneauPrincipal panneau_principal;
    private BarreMenu barre_menu;
    
    
    public FenetrePrincipale( ) {

        this.setBaseConf();
    }

    private void setBaseConf() {
        cadre = new javax.swing.JFrame("KouldNotShare Client");
        
        barre_menu = new BarreMenu();
                
        panneau_principal = new PanneauPrincipal();
        panneau_principal.setPreferredSize (new Dimension(1000, 600));
        
        panneau_principal.setBackground(Color.RED);
        
        Console console = new Console();
        panneau_principal.add(console);
        
        cadre.setJMenuBar(barre_menu);
        cadre.setContentPane(panneau_principal);
        cadre.setLocation(200, 100);
        cadre.pack();
        cadre.setVisible(true);
        cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
