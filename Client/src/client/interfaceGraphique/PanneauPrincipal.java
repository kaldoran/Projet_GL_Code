package client.interfaceGraphique;

import java.awt.BorderLayout;
import java.nio.file.Files;
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
    
    public PanneauPrincipal() {
        super();
        this.setLayout(new BorderLayout());
        
        barre_outils = new PanneauBarreOutils();
        barre_outils.setVisible(true);
        add(barre_outils,BorderLayout.NORTH);
        
        
        arborescence_client = new PanneauArborescence();
        arborescence_client.setTitre("Client :");
        arborescence_client.setVisible(true);
        add(arborescence_client,BorderLayout.WEST);
        
        arborescence_serveur =  new PanneauArborescence();
        arborescence_serveur.setTitre("Serveur :");
        arborescence_serveur.setVisible(true);
        add(arborescence_serveur,BorderLayout.EAST);
        
        commande_transfert = new PanneauCommandeTransfert();
        commande_transfert.setVisible(true);
        add(commande_transfert);
        
    }
 
}
