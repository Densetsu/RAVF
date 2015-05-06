package chat;

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChatClient {

    protected static ChatInterface client;
    protected static ChatInterface server;
    
    public static void main(String args[]){
    	lanceClientChat("152.77.82.175","test");
    }
    
    public static void lanceClientChat(String hostname, String nomObjetDistant) {
        String nom = nomObjetDistant.trim(), host = hostname.trim();
        // récupération des arguments
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Entrez votre nom:");
            String name = s.nextLine().trim();
            client = new Chat(name);
            
            server = (ChatInterface)Client.connexionClient(host, nom, "/home/r/ramosad/ProjetFinal/framework/ma_police.txt");
            
            String msg = "[" + client.getName() + "] est connecté";
            server.send(msg);
            System.out.println("[System] Chat Remote Object is ready:");
            server.setClient(client);

            while(true){
            	msg = "[" + client.getName() + "]";
            	msg += s.nextLine();
            	try{
            		server.send(msg);
            	}catch(Exception e){
            		System.out.println(e);
            	}
            }
        } catch (Exception e) {
            System.out.println("[System] Server failed: " + e);
        }
    }

    public void getUserConnected() throws RemoteException {
        String s = "[" + client.getName() + "]"+"\n";
        Echange fichier = new Echange("log_connection");
        try {
            File dossierStock = new File("/home/r/ramosad/ProjetFinal/Serveur/stockage/log_connection");
            fichier.ecritureBytesToFile(dossierStock,s.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}