package serveur.MVC.vue.impl;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kevin
 */
public class PanneauPrincipal extends JPanel{
    private PanneauBarreOutils barre_outils;
    
    public PanneauPrincipal() {
        super();
        this.setLayout(new BorderLayout());
        
        barre_outils = new PanneauBarreOutils();
        barre_outils.setVisible(true);
        add(barre_outils,BorderLayout.NORTH);
    
    }
 
}
