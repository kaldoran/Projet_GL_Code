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
    
    public PanneauPrincipal() {
        super();
        arborescence_client = new PanneauArborescence();
        arborescence_serveur =  new PanneauArborescence();
        this.setLayout(new BorderLayout());
        
        
        
        
    }
 
}
