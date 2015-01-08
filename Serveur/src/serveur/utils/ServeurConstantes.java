/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.utils;

import java.io.InputStream;

/**
 *
 * @author kaldoran
 */
public class ServeurConstantes {
    public final static Integer PORT = 10777;
    public final static Integer PORTF = 10778;
    public final static Integer MAX_UTILISATEUR = 5;
    public final static Integer MAX_ESSAI = 3;
    
    
    public final static String FICHIER_COMPTE = "/Compte/cpt.acc";
    public final static String FICHIER = "/home";
  
    public final static String REPERTOIRE_PARTAGE = "/home/"+System.getProperties().getProperty("user.name")+"/Téléchargements";

}
