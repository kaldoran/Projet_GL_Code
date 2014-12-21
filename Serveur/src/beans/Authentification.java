/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author kevin
 */
public class Authentification implements Serializable {
    private String mot_de_passe;
    private String login;

    public Authentification(String login , String mot_de_passe) {
        this.login = login;
        this.mot_de_passe = mot_de_passe;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getLogin() {
        return login;
    }
 
}
