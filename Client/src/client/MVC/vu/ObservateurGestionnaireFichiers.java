/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.vu;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

/**
 *
 * @author kevin
 */
public interface ObservateurGestionnaireFichiers {
  
    void actualiser (DefaultMutableTreeNode racine);
    
    void setAdresseFichier(String adresse_fichier);
    
    boolean isArboServeur();
}
