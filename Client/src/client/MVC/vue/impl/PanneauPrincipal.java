package client.MVC.vue.impl;

import client.MVC.controleur.InterfaceControleurGestionnaireFichiers;
import client.MVC.controleur.InterfaceControleurTelechargementTeleversement;
import client.MVC.model.InterfaceModeleGestionnaireFichiers;
import client.MVC.model.InterfaceModeleTelechargementTeleversement;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kevin
 */
public class PanneauPrincipal extends JPanel{
    private PanneauArborescence arborescence_client;
    private PanneauArborescence arborescence_serveur;
    private PanneauBarreOutils barre_outils;
    private PanneauCommandeTransfert commande_transfert;
    
    public PanneauPrincipal(InterfaceControleurGestionnaireFichiers controleur_gtnf, 
                            InterfaceModeleGestionnaireFichiers modele_gtnf, 
                            InterfaceControleurTelechargementTeleversement controleur_TlgTlv, 
                            InterfaceModeleTelechargementTeleversement modele_TlgTlv) {
        super();
        this.setLayout(new BorderLayout());
        
        barre_outils = new PanneauBarreOutils();
        barre_outils.setVisible(true);
        add(barre_outils,BorderLayout.NORTH);
        
        
        arborescence_serveur =  new PanneauArborescence(controleur_gtnf,modele_gtnf, true);
        arborescence_serveur.setTitre("Serveur :");
        arborescence_serveur.setVisible(true);
        add(arborescence_serveur,BorderLayout.EAST);
        
        arborescence_client = new PanneauArborescence(controleur_gtnf,modele_gtnf, false);
        arborescence_client.setTitre("Client :");
        arborescence_client.setVisible(true);
        add(arborescence_client,BorderLayout.WEST);
        
        commande_transfert = new PanneauCommandeTransfert(controleur_TlgTlv, modele_TlgTlv);
        commande_transfert.setVisible(true);
        add(commande_transfert);
        
    }
 
}
