/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.vue.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;

/**
 *
 * @author kevin
 */
public class PanneauArborescence extends JPanel {
    private JTree arborescence_fichiers;
    private JLabel lbl_titre;
    private JLabel lbl_adresse;
    private JPanel panneau_label_tit;
    private JPanel panneau_label_adr;
        
    public PanneauArborescence() {
        super();
        setLayout(new BorderLayout());
        
        panneau_label_tit = new JPanel();
        this.add(panneau_label_tit,BorderLayout.NORTH);
        
        lbl_titre = new JLabel();
        panneau_label_tit.add(lbl_titre);
        
        panneau_label_adr = new JPanel();
        this.add(panneau_label_adr,BorderLayout.SOUTH);
        
        lbl_adresse = new JLabel();
        panneau_label_adr.add(lbl_adresse);
        
        String[] rep = {"rep1","rep2", "rep3"};
        arborescence_fichiers = new JTree(rep);
        arborescence_fichiers.setPreferredSize(new Dimension(400, 600));
        this.add(arborescence_fichiers,BorderLayout.CENTER);
    }
    
    
    public void setTitre(String titre) {
        lbl_titre.setText(titre);
    }
    
    public void setAdresse(String adresse) {
        lbl_adresse.setText(adresse);
    }
}
