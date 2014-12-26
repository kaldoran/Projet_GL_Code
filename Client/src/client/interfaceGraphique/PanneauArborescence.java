/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTree;

/**
 *
 * @author kevin
 */
public class PanneauArborescence extends JPanel {
    private JTree arborescence_fichiers;
        
    public PanneauArborescence() {
        super();
        setLayout(new BorderLayout());
        String[] rep = {"rep1","rep2", "rep3"};
        arborescence_fichiers = new JTree(rep);
        arborescence_fichiers.setPreferredSize(new Dimension(400, 600));
        this.add(arborescence_fichiers,BorderLayout.CENTER);
    }
    
    
}
