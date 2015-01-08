/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.vue.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author kevin
 */
public class PanneauBarreOutils extends JPanel implements MouseListener{
    
    private JButton btn_statistique;
    private JButton btn_limitation;
    private JButton btn_configuration;
    
    private final String DESC_CONFIGURATION = "Configuration : configurer votre client.";
    private final String DESC_LIMITATION = "Limitations : limiter votre bande passante.";
    private final String DESC_STATISTIQUE = "Statistisques : consulter les statistiques de votre compte.";

    public PanneauBarreOutils() {
        super();
        this.setPreferredSize(new Dimension(1000, 44));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        btn_configuration = new JButton(new ImageIcon("Packages ressources/configure.png"));
        btn_configuration.setBackground(Color.WHITE);
        btn_configuration.addMouseListener(this);
        this.add(btn_configuration);
        
        btn_limitation = new JButton(new ImageIcon("Packages ressources/speed.png"));
        btn_limitation.setBackground(Color.WHITE);
        this.add(btn_limitation);
        
        btn_statistique = new JButton(new ImageIcon("Packages ressources/chart.png"));
        btn_statistique.setBackground(Color.WHITE);
        this.add(btn_statistique);
        
                
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(me.getSource() == btn_configuration) {
            btn_configuration.setToolTipText(DESC_CONFIGURATION);
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
