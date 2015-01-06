/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import serveur.utils.ServeurConstantes;
import serveur.utils.toSha1;

/**
 *
 * @author kaldoran
 */
public class GestionCompte {

    private static Pattern regex = Pattern.compile("([a-z]*)@([a-zA-Z0-9]{40}):.*");

    public GestionCompte() {
    }

    public boolean ajouterCompte(String login, String password, String droits) {

        BufferedWriter cpt = null;

        if (!isCorrect(login, password)) {
            try {
                cpt = new BufferedWriter(new FileWriter(ServeurConstantes.FICHIER_COMPTE, true));
                cpt.write(login + toSha1.toSHA1(password.getBytes()) + ":" + droits);
                cpt.newLine();
                cpt.flush();
            } catch (IOException ex) {
                Logger.getLogger(GestionCompte.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return true;
    }

    public boolean supprimerCompte(String login) {
        PrintWriter pw = null;
        BufferedReader br = null;

        String line = null;

        try {
            File account = new File(ServeurConstantes.FICHIER_COMPTE);
            File tmp = new File(ServeurConstantes.FICHIER_COMPTE + ".tmp");

            br = new BufferedReader(new FileReader(account));
            pw = new PrintWriter(new FileWriter(tmp));

            while ((line = br.readLine()) != null) {
                if (!line.trim().equals(login)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!account.delete()) {
                System.out.println("[Erreur] : Impossible de supprimer le fichier");
            }

            if (!tmp.renameTo(account)) {
                System.out.println("[Erreur] : Impossible de renommer le fichier");
            }

        } catch (IOException ex) {
            Logger.getLogger(GestionCompte.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    public static boolean isCorrect(String login, String pass) {
        String inputScanner = null;
        String hashpass = null;
        hashpass = toSha1.toSHA1(pass.getBytes());

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(ServeurConstantes.FICHIER_COMPTE));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServeurAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (scanner.hasNextLine()) {
            inputScanner = scanner.nextLine().trim();
            Matcher m = regex.matcher(inputScanner);
            if ( m.matches() ) {
                if (login.equalsIgnoreCase(m.group(1)) && hashpass.equalsIgnoreCase(m.group(2))) {
                    return true;
                }
            }
        }

        return false;
    }
}
