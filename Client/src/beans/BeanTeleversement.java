package beans;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kevin
 */
public class BeanTeleversement implements Serializable{
    private String adresse_repertoire;
    private int port_televersement;
    
    public BeanTeleversement(String adresse_repertoire) {
        this.adresse_repertoire = adresse_repertoire;
    }

    public String getAdresse_repertoire() {
        return adresse_repertoire;
    }

    public void setAdresse_repertoire(String adresse_repertoire) {
        this.adresse_repertoire = adresse_repertoire;
    }

    public int getPort_televersement() {
        return port_televersement;
    }

    public void setPort_televersement(int port_televersement) {
        this.port_televersement = port_televersement;
    }
    
    
}
