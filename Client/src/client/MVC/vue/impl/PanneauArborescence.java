/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.vue.impl;

import client.MVC.controleur.InterfaceControleurGestionnaireFichiers;
import client.MVC.model.InterfaceModeleGestionnaireFichiers;
import client.MVC.vu.ObservateurGestionnaireFichiers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author kevin
 */
public class PanneauArborescence extends JPanel implements ObservateurGestionnaireFichiers, TreeSelectionListener {
    private InterfaceControleurGestionnaireFichiers controleur;
    private InterfaceModeleGestionnaireFichiers modele;
    
    private JTree arborescence_fichiers;
    private JLabel lbl_titre;
    private JLabel lbl_adresse;
    private JPanel panneau_label_tit;
    private JPanel panneau_label_adr;
    private JScrollPane scroll;
    
    private DefaultMutableTreeNode racine;
    private final boolean ARBORESCENCE_SERVEUR;
    
    public PanneauArborescence(InterfaceControleurGestionnaireFichiers controleur, InterfaceModeleGestionnaireFichiers modele, boolean estArboServeur) {
        super();
        setLayout(new BorderLayout());
        
        this.controleur = controleur;
        this.modele = modele;
        this.modele.enregistrerObservateur(this);
        this.ARBORESCENCE_SERVEUR = estArboServeur;
        
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        
        renderer.setTextSelectionColor(Color.yellow);
        
        panneau_label_tit = new JPanel();
        this.add(panneau_label_tit,BorderLayout.NORTH);
        
        lbl_titre = new JLabel();
        panneau_label_tit.add(lbl_titre);
        
        panneau_label_adr = new JPanel();
        this.add(panneau_label_adr,BorderLayout.SOUTH);
        
        lbl_adresse = new JLabel();
        lbl_adresse.setPreferredSize(new Dimension(400, 20));
        lbl_adresse.setFont(new Font("Arial", Font.PLAIN, 9));
        panneau_label_adr.add(lbl_adresse);
        
        String[] rep = {"rep1","rep2", "rep3"};
        racine = new DefaultMutableTreeNode();
        arborescence_fichiers = new JTree(racine);
        arborescence_fichiers.addTreeSelectionListener(this);
        //arborescence_fichiers.setPreferredSize(new Dimension(400, 600));
        //arborescence_fichiers.setCellRenderer(renderer);
        
        scroll = new JScrollPane(arborescence_fichiers);
        scroll.setPreferredSize(new Dimension(400, 600));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scroll,BorderLayout.CENTER);
    }

    
    public void setTitre(String titre) {
        lbl_titre.setText(titre);
    }
    
    public void setAdresse(String adresse) {
        lbl_adresse.setText(adresse);
    }

    @Override
    public void actualiser(DefaultMutableTreeNode racine) {
        this.racine.add((MutableTreeNode) racine);
        this.arborescence_fichiers.updateUI();
    }
    
    @Override
    public boolean isArboServeur() {
        return ARBORESCENCE_SERVEUR;
    }

    @Override
    public void setAdresseFichier(String adresse_fichier) {
        lbl_adresse.setText(adresse_fichier);
    }

    @Override
    public void valueChanged(TreeSelectionEvent tse) {
        JTree tree = (JTree) tse.getSource();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        String selectedNodeName = selectedNode.toString();System.out.println("selectdeNodeName : " + selectedNodeName);
        controleur.traiterSelectionFichier(selectedNodeName, ARBORESCENCE_SERVEUR, selectedNode.isLeaf());
    }
    
}
