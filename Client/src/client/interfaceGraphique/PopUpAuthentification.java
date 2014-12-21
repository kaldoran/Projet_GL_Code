/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.interfaceGraphique;

import client.MVC.ObservateurAuthentification;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author kevin
 */
public class PopUpAuthentification extends JDialog {
    private JButton valider = new JButton("Valider");
    private JButton annuler = new JButton("Annuler");
    private JTextField chTxt_login = new JTextField(10);
    private JPasswordField chPwd_mot_passe = new JPasswordField(10);

    public PopUpAuthentification() {
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
	panneau.add(valider);
	panneau.add(annuler);
	boite.add(panneau);
	
	add(boite) ;
	pack();
	setLocation(400, 200);
	setVisible(true);
    }
    
    
    

    private void setBaseConf() {
       
    }
}
