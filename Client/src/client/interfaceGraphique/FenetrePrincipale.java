/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.interfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author kevin
 */
public class FenetrePrincipale{
    
    JFrame cadre;
    JPanel panneau;

    public FenetrePrincipale() {
        cadre = new javax.swing.JFrame("Premiere fenetre");
        panneau = new JPanel();
        this.setBaseConf();
    }
    
    
    

    private void setBaseConf() {
        
        panneau.setPreferredSize (new Dimension(250, 150));
        panneau.setBackground(Color.RED);
        
        cadre.setContentPane(panneau);
        cadre.setLocation(400, 300);
        cadre.pack();
        cadre.setVisible(true);
        cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
