/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.MVC.vue.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author kaldoran
 */
public class Console extends JPanel {
    private JTextArea console;
    
    public Console() {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900, 500));
        setBackground(Color.GRAY);
        
        console = new JTextArea();
        console.setBackground(Color.LIGHT_GRAY);
        console.setMargin(new Insets(10, 10, 10, 10));
        console.setFont(new Font("Arial", Font.PLAIN, 12));
        console.setEditable(false);
        
        OutputStream text_console;
        
        text_console = new OutputStream() {
            @Override
            public void write(byte[] b) {
                console.setText(console.getText() + new String(b));
            }
   
            @Override
            public void write(byte[] b, int off, int len) {
                console.setText(console.getText() + new String(b, off, len));
            }
            
            @Override
            public void write(int i) throws IOException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        
        add(console);

        System.setOut(new PrintStream(text_console));
    }
    
}
