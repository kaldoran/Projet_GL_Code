/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

/**
 *
 * @author kaldoran
 */
public class MainServeur {
    private int nbClients = 0; // nombre total de clients connectés

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Serveur serveur = new Serveur();
        
        /*MainServeur serv = new MainServeur();
        ServerSocket ss;
        InetAddress LocaleAdresse;
       
        try {
            ss = new ServerSocket(ServeurConstantes.PORT, ServeurConstantes.MAX_UTILISATEUR); // ouverture d'un socket serveur sur port
            LocaleAdresse = InetAddress.getLocalHost();
            printInfo(LocaleAdresse);
            while(true) {
                new ServeurThread(ss.accept(), serv); // un client se connecte, un nouveau thread client est lancé
            }
        } catch (IOException ex) {
            Logger.getLogger(MainServeur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void printInfo(InetAddress Adresse) {
        System.out.println("--------");
        System.out.println("Serveur - Team KNK");
        System.out.println("Derniere version : 10/12/2014");
        System.out.println("--------");
        System.out.println("Demarre sur le port : " + ServeurConstantes.PORT);
        System.out.println("L'adresse du serveur est : " + Adresse);
        System.out.println("--------");
    }
    
    synchronized public int getNbClients() {
        return nbClients; // retourne le nombre de clients connectés*/
    }
}
