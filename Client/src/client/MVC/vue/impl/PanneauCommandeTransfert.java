/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.vue.impl;

import client.MVC.controleur.InterfaceControleurTelechargementTeleversement;
import client.MVC.model.InterfaceModeleTelechargementTeleversement;
import client.MVC.vu.ObservateurTelechargementTeleversement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author kevin
 */
public class PanneauCommandeTransfert extends JPanel implements ObservateurTelechargementTeleversement, ActionListener{
    private InterfaceControleurTelechargementTeleversement controleur;
    private InterfaceModeleTelechargementTeleversement modele;
            
    private JButton btn_televerser;
    private JButton btn_telecharger;

    public PanneauCommandeTransfert(InterfaceControleurTelechargementTeleversement controleur, InterfaceModeleTelechargementTeleversement modele) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
       this.controleur = controleur;
       this.modele = modele;
       modele.enregistrerObservateur(this);
        
        Box boite_panneau = Box.createVerticalBox();
        boite_panneau.add(new JPanel());
        
        JPanel panneau_bouton = new JPanel();
        
        Dimension dim_btn = new Dimension(150, 30);
        
        Box boite = Box.createVerticalBox();
        btn_televerser = new JButton(new ImageIcon("Packages ressources/arrow_left.png"));
        btn_televerser.setBackground(Color.WHITE);
        btn_televerser.addActionListener(this);
        boite.add(btn_televerser);
        
        btn_telecharger = new JButton(new ImageIcon("Packages ressources/arrow_right.png"));
        btn_telecharger.setBackground(Color.WHITE);
        btn_telecharger.addActionListener(this);
        boite.add(btn_telecharger);
        
        panneau_bouton.add(boite,BorderLayout.CENTER);
        
        
        boite_panneau.add(panneau_bouton);
        boite_panneau.add(new JPanel());
        
        this.add(boite_panneau);
    }
    
    @Override
    public void initialiserBoutons() {
        btn_televerser.setEnabled(false);
        btn_telecharger.setEnabled(false);
    }
            
    @Override
    public void activerBoutonTeleverser() {
        btn_televerser.setEnabled(true);
        btn_telecharger.setEnabled(false);
    }

    @Override
    public void activerBoutonTelecharger() {
        btn_televerser.setEnabled(false);
        btn_telecharger.setEnabled(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_telecharger) {
            System.out.println("bouton telecharger");
            controleur.initialiserTelechargement();
        } else if(e.getSource() == btn_televerser) {
            
            controleur.initialiserTeleversement();
        } 
    }

}
