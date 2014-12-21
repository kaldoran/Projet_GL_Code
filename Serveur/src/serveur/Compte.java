/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.util.ArrayList;

/**
 *
 * @author kaldoran
 */
public class Compte {
    private String pseudo;
    private ArrayList<String> droits;

    @Override
    public String toString() {
        return "Compte{ pseudo : " + pseudo + ", droits : " + droits + '}';
    }

    public Compte(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public ArrayList<String> getDroits() {
        return droits;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setDroits(ArrayList<String> droits) {
        this.droits = droits;
    }    
}
