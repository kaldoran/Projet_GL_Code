/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.interfaceGraphique;

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
        String[] rep = {"rep1","rep2", "rep3"};
        arborescence_fichiers = new JTree(rep);
        this.add(arborescence_fichiers);
    }
    
    
}
