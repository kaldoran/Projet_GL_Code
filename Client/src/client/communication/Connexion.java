/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaldoran
 */
class Connexion {
    public static String login = null, pass = null, message1 = null, message2 = null, message3 = null;
    private Scanner sc = null;
    private Boolean connect = false;
    
    public Connexion(Socket socket, PrintWriter out, BufferedReader in) {
        try {
            String s;
            sc = new Scanner(System.in);
            
            System.out.println(in.readLine());
            
            while(!connect) {
                System.out.println("Verification de login");
                System.out.println(in.readLine());
                login = sc.nextLine();
                out.println(login);
                out.flush();

                System.out.println(in.readLine());
                pass = sc.nextLine();
                out.println(pass);
                out.flush();

                if(in.readLine().equalsIgnoreCase("connect")){
                    System.out.println("Vous etes connecté"); 
                    connect = true;
                }
                else {
                    System.out.println("Erreur de password ou login");
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }
    
}
