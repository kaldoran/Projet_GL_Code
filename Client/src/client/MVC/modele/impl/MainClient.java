/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.MVC.modele.impl;

import client.MVC.controleur.InterfaceControleur;
import client.MVC.controleur.impl.Administration;
import client.MVC.model.InterfaceModeleAuthentification;
import client.MVC.model.InterfaceModeleGestionnaireFichiers;
import client.MVC.model.InterfaceModeleTelechargementTeleversement;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author kaldoran & kevin.L
 */
public class MainClient {
    private static PrintWriter out = null;
    private static BufferedReader in = null;
    private static Scanner sc = null;
    public static Socket socket = null;
    
    public static void main(String[] args) {
        InterfaceModeleAuthentification modele_auth = new Authentification();
        InterfaceModeleGestionnaireFichiers modele_gtnf = new GestionnaireFichiers();
        InterfaceModeleTelechargementTeleversement modele_TlgTlv = new TelechargementTeleversement();
        InterfaceControleur controleur = new Administration(modele_auth, modele_gtnf, modele_TlgTlv);
    }
  
}
