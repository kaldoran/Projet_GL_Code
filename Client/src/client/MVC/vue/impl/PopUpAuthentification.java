/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.vue.impl;

import client.MVC.controleur.InterfaceControleurAuthentification;
import client.MVC.model.InterfaceModeleAuthentification;
import client.MVC.vu.ObservateurAuthentification;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
    
    /** Attributs composants PopUpAuthentification */
    private JButton btn_valider = new JButton("Se connecter");
    private JButton btn_annuler = new JButton("Annuler");
    private JTextField chTxt_serveur = new JTextField(10);
    private JTextField chTxt_login = new JTextField(10);
    private JPasswordField chPwd_mot_passe = new JPasswordField(10);
    private JLabel lbl_message;

    
    public PopUpAuthentification(InterfaceControleurAuthentification controleur, InterfaceModeleAuthentification modele_authentification) {
        super();
        this.setPreferredSize(new Dimension(360, 180));
        this.controleur = controleur;
        this.modele_authentification = modele_authentification;
        System.out.println("creation dialogue");
        modele_authentification.enregistrerObservateur(this);
        
        JPanel panneau ;
        lbl_message = new JLabel();
        lbl_message.setFont(new Font("Arial", Font.PLAIN, 9));
        lbl_message.setVisible(false);
	
	Box boite = Box.createVerticalBox();
        setModal(true);
        setTitle("Authentification");
        
        panneau = new JPanel();
        panneau.add(new JLabel("Serveur :          "));
        panneau.add(chTxt_serveur);
        boite.add(panneau);
        
        panneau = new JPanel();
	panneau.add(new JLabel("Nom d'utilisateur : "));
	panneau.add(chTxt_login);
	boite.add(panneau);
	
	panneau = new JPanel();
	panneau.add(new JLabel("Mot de passe :      "));
        panneau.add(chPwd_mot_passe);
	boite.add(panneau);
	
        panneau = new JPanel();
	panneau.add(lbl_message);
	boite.add(panneau);
        
	panneau = new JPanel();
	panneau.add(btn_valider);
	panneau.add(btn_annuler);
	boite.add(panneau);
        
        btn_valider.addActionListener(this);
        btn_annuler.addActionListener(this);
        
	add(boite) ;
	pack();
	setLocation(620, 400);
	setVisible(true);
    }
    
    
    

    private void setBaseConf() {
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_valider) {
            System.out.println(chTxt_login.getText() + String.copyValueOf(chPwd_mot_passe.getPassword()));
            controleur.setBeanAuthentification(chTxt_login.getText(), String.copyValueOf(chPwd_mot_passe.getPassword()));
        } else if(e.getSource() == btn_annuler) {
            fermer();
            //controleur.fermerApplication();
        } 
    }

    @Override
    public void actualiser(String login, String mot_de_passe) {
        chTxt_login.setText(login);
        chPwd_mot_passe.setText(mot_de_passe);
    }

    @Override
    public void fermer() {
        this.dispose();
    }

    @Override
    public void informerMessageErreur(int i) {
        System.out.println("HELLO");
        lbl_message.setForeground(Color.RED);
        switch (i) {
            case 0 : lbl_message.setText("Erreur d'authentification : Identifiants invalides.");break;
            case 1 : lbl_message.setText("Erreur d'authentification : Identifiants vides.");break;
            case 2 : lbl_message.setText("Erreur d'authentification : L'utilisateur entré est invalide.");break;
            case 3 : lbl_message.setText("Erreur d'authentification : Erreur de mot de passe.");break;
            default: lbl_message.setText("");
        }
        lbl_message.setVisible(true);
    }
}
