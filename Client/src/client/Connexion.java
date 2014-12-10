/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

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
 * @author kaldoran
 */
class Connexion {
    public static String login = null, pass = null, message1 = null, message2 = null, message3 = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc = null;
    private Boolean connect = false;
    
    public Connexion(Socket socket) {
        try {
            String s;
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
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

                if(in.readLine().equals("connect")){
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
