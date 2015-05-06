package chat;

import java.io.File;
import java.io.IOException;
import java.rmi.*;
import java.util.Scanner;

public class ChatServer {

    protected static Chat server;
    protected static ChatInterface client;
    protected static String nom;
    
    public static void main(String args[]) throws RemoteException{
    	lanceServerChat("test", 1099); 
    }
    
    public static void lanceServerChat(String nomGenerique, int portRegistry) throws RemoteException {
        nom = nomGenerique;
        server = new Chat(nom);
        Server.connexionServer(1099, server, "/home/r/ramosad/ProjetFinal/framework/ma_police.txt",nom);
		System.out.println("Serveur chat prêt: ");  
		Scanner s = new Scanner(System.in);
    	while(true){
    		String msg = s.nextLine().trim();
    		if (server.getClient()!=null){
    			client=server.getClient();
    			msg="["+server.getName()+"] "+msg;
    			client.send(msg);
    		}	
    	}
    }

/*    public String getMessageServer() throws RemoteException, IOException {
        byte[] donneeFichier;
        File fichierStock = new File("/home/r/ramosad/ProjetFinal/Serveur/stockage/chat/log");
        Echange ech = new Echange("");
        donneeFichier = ech.lectureBytesFromFile(fichierStock);
        String lesDonnees = "";
        for (int i = 0; i < donneeFichier.length; i++) {
            lesDonnees += (char) donneeFichier[i];
        }
        return lesDonnees;
    }
*/
}
