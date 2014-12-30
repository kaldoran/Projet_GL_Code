/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.modele.impl;

import beans.BeanInformationServeur;
import client.MVC.model.InterfaceModeleTelechargementTeleversement;
import client.communication.Communication;

/**
 *
 * @author kevin
 */
public class TelechargementTeleversement implements InterfaceModeleTelechargementTeleversement {

    private Communication communication;
    private BeanInformationServeur bean_info_serveur;
    
    public TelechargementTeleversement() {
    
    }

    
    
    @Override
    public void recupererCommunication(Communication communication) {
        this.communication = communication;
    }

    @Override
    public void recupererInformationServeur() {
        BeanInformationServeur beanInfoServeur = new BeanInformationServeur();
        bean_info_serveur = communication.procederRecuperationInformation(beanInfoServeur);
    }

    @Override
    public BeanInformationServeur getInformationServeur() {
        return bean_info_serveur;
    }
    
    
    
}
