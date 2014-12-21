/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.MVC.ObservateurAuthentification;
import client.interfaceGraphique.PopUpAuthentification;
import client.utils.ClientConstantes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
//        String s = null;
//        try {
//            System.out.println("Demande de connexion");
//            socket = new Socket(ClientConstantes.SERVEUR, ClientConstantes.PORT);
//            System.out.println("Connexion établie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connecté
//                        
//            out = new PrintWriter(socket.getOutputStream());
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
//            
//            new Connexion(socket, out, in);
//            
//            // Supposont qu'il est connecté.
//
//            sc = new Scanner(System.in);
//            
//
//
//            do { 
//                System.out.println("Commande : [ls | cd | get | post ]");
//                System.out.println("---------------------");
//                
//                s = sc.nextLine();
//
//                out.println(s);
//                out.flush();
//                
//                while ( (s = in.readLine()) != null 
//                            && !s.equals("end") ) { 
//                    if ( s.equalsIgnoreCase("Ready get")) {
//                        new Telechargement(s);
//                    }
//                    else if ( s.equalsIgnoreCase("Ready post") ) {
//                        new Televersement(s);
//                    }
//                    System.out.println("Input : " + s); }
//
//            }while ( !s.equalsIgnoreCase("exit") );
//        } catch (IOException ex) {
//            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
  
}
