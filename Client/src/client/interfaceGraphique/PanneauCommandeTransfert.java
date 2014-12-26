/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.interfaceGraphique;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author kevin
 */
public class PanneauCommandeTransfert extends JPanel implements ActionListener{
    private JButton btn_televerser;
    private JButton btn_telecharger;

    public PanneauCommandeTransfert() {
        super();
        this.setLayout(new FlowLayout());
        
        btn_telecharger = new JButton("Telecharger");
        btn_telecharger.addActionListener(this);
        add(btn_telecharger);
        
        btn_televerser = new JButton("Televerser");
        btn_televerser.addActionListener(this);
        add(btn_televerser);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
