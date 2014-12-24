/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.interfaceGraphique;

import client.MVC.InterfaceControleurAuthentification;
import client.MVC.InterfaceModeleAuthentification;
import client.MVC.ObservateurAuthentification;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author kevin
 */
public class PopUpAuthentification extends JDialog implements ActionListener, ObservateurAuthentification {
    /**Attributs MVC */
    private InterfaceModeleAuthentification modele_authentification;
    private InterfaceControleurAuthentification controleur;
    
    
    private JButton btn_valider = new JButton("Valider");
    private JButton btn_annuler = new JButton("Annuler");
    private JTextField chTxt_login = new JTextField(10);
    private JPasswordField chPwd_mot_passe = new JPasswordField(10);

    public PopUpAuthentification(InterfaceControleurAuthentification controleur, InterfaceModeleAuthentification modele_authentification) {
        this.controleur = controleur;
        this.modele_authentification = modele_authentification;
        
        JPanel panneau ;
	
	Box boite = Box.createVerticalBox();
        setModal(true);
        setTitle("Authentification KouldNotShare");
        
        panneau = new JPanel();
	panneau.add(new JLabel("Entrez votre login : "));
	panneau.add(chTxt_login);
	boite.add(panneau);
	
	panneau = new JPanel();
	panneau.add(new JLabel("Entrez votre login : "));
        panneau.add(chPwd_mot_passe);
	boite.add(panneau);
	
	panneau = new JPanel();
	panneau.add(btn_valider);
	panneau.add(btn_annuler);
	boite.add(panneau);
	
	add(boite) ;
	pack();
	setLocation(400, 200);
	setVisible(true);
    }
    
    
    

    private void setBaseConf() {
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_valider) {
            
        }
    }

    @Override
    public void actualiser(String login, String mot_de_passe) {
        chTxt_login.setText(login);
        chPwd_mot_passe.setText(mot_de_passe);
    }
}
