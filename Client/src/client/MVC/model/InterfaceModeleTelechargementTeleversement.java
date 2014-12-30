/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.model;

import beans.BeanInformationServeur;
import client.communication.Communication;

/**
 *
 * @author kevin
 */
public interface InterfaceModeleTelechargementTeleversement {
    
    void recupererCommunication(Communication communication);
    
    void recupererInformationServeur();
    
    BeanInformationServeur getInformationServeur();
    
    //void telecharger();
    
    //void televerser();
    
}
