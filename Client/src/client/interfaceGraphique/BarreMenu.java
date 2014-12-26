/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.interfaceGraphique;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author kevin
 */
public class BarreMenu extends JMenuBar{
    
    private JMenu menu_fichier;
    private JMenu menu_preference;
    private JMenu menu_configuration;
    
    private JMenuItem  mfich_deconnexion;
    private JMenuItem mprf_item1;
    private JMenuItem mprf_item2;
    private JMenuItem mconf_reseau;
    private JMenuItem mconf_compte;

    public BarreMenu() {
        super();
        
        /** allocations JMenu */
        menu_fichier = new JMenu("Fichier");
        menu_preference = new JMenu("Préférences");
        menu_configuration = new JMenu("Configurations");
        
        /** allocations JMenuItem */
        mfich_deconnexion = new JMenuItem("Déconnexion");
        mprf_item1 = new JMenuItem("item1");
        mprf_item2 = new JMenuItem("item2");
        mconf_compte = new JMenuItem("Mon Compte");
        mconf_reseau = new JMenuItem("Réseau");
        
        /** Constructions */
        menu_fichier.add(mfich_deconnexion);
        
        menu_preference.add(mprf_item1);
        menu_preference.add(mprf_item2);
        
        menu_configuration.add(mconf_reseau);
        menu_configuration.add(mconf_compte);
        
        this.add(menu_fichier);
        this.add(menu_preference);
        this.add(menu_configuration);
    }
    
    
}
