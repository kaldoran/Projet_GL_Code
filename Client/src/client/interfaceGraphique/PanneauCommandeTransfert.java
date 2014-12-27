/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
public class PanneauCommandeTransfert extends JPanel implements ActionListener{
    private JButton btn_televerser;
    private JButton btn_telecharger;

    public PanneauCommandeTransfert() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        Box boite_panneau = Box.createVerticalBox();
        boite_panneau.add(new JPanel());
        
        JPanel panneau_bouton = new JPanel();
        
        Dimension dim_btn = new Dimension(150, 30);
        
        Box boite = Box.createVerticalBox();
        btn_telecharger = new JButton(new ImageIcon("Packages ressources/arrow_left.png"));
        btn_telecharger.setBackground(Color.WHITE);
        btn_telecharger.addActionListener(this);
        boite.add(btn_telecharger);
        
        btn_televerser = new JButton(new ImageIcon("Packages ressources/arrow_right.png"));
        btn_televerser.setBackground(Color.WHITE);
        btn_televerser.addActionListener(this);
        boite.add(btn_televerser);
        
        panneau_bouton.add(boite,BorderLayout.CENTER);
        
        
        boite_panneau.add(panneau_bouton);
        boite_panneau.add(new JPanel());
        
        this.add(boite_panneau);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
