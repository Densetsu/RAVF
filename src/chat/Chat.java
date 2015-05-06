package chat;

import java.rmi.*;
import java.rmi.server.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Chat extends UnicastRemoteObject implements ChatInterface {

    public String name;
    private static final long serialVersionUID = 2674880711467464646L;
    public ChatInterface client = null;
    public ChatServer server = null;
    public ChatInterface server1=null;
    ArrayList<ChatInterface> chatClient;

    public Chat(String n) throws RemoteException {
        this.name = n;
        chatClient= new ArrayList<>();
    }

    public String getName() throws RemoteException {
        return this.name;
    }

    public synchronized void setClient(ChatInterface c) {
       this.chatClient.add(c);
    }

    public ChatInterface getClient() {
        return client;
    }

    public synchronized void send(String s) throws RemoteException{
        s+="\n";
        Echange fichier = new Echange("log");
        int i=0;
        while(i<chatClient.size()){
        	chatClient.get(i).getMessageClient(s);
        	i++;
        }
        try {
            File dossierStock = new File("/home/r/ramosad/ProjetFinal/Serveur/stockage/chat/log");
            fichier.ecritureBytesToFile(dossierStock,s.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getMessageClient(String message) {
        String msg = message.trim();
        System.out.println(msg);
    }
    
    public synchronized String[] utilisateurConnecte() throws RemoteException {
    	String[] listeUtilisateur={};
    	for(int i=0; i<chatClient.size();i++){
    		listeUtilisateur[i]=chatClient.get(i).getName();
    	}
    	return listeUtilisateur;
    }
    
    public void deconnecteUtilisateur(String nom) throws RemoteException{
    	for(int i=0; i<chatClient.size();i++){
    		if(chatClient.get(i).getName().equals(nom)){
    			chatClient.remove(i);
    		}
    	}
    }
}