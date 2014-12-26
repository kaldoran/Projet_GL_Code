/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC;

/**
 *
 * @author kevin
 */
public interface ObservateurAuthentification {
    
    void actualiser (String login, String mot_de_passe);
    
    void fermer();
    
    void informerMessageErreur(int i);
}
