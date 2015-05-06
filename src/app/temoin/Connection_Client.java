package app.temoin;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;
import fonction.fichier.*;
import fonction.message.*;
import fonction.bdd.*;

public class Connection_Client{
    protected TraitementConnexionInterface clientConnexion;
    protected TraitementConnexionInterface serverConnexion;
    protected TraitementMessageInterface clientChat;
    protected TraitementMessageInterface serverChat;
    protected TraitementFichierInterface clientDrive;
    protected TraitementFichierInterface serverDrive;
	protected Date laDate;
	protected SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * Constructeur de Connection_Client 
	 * Lance le client pour la connexion de l'utilisateur au serveur specifique
	 * @param hostname adresse IP de la machine serveur
	 * @param nomObjetDistant nom de l'objet distant du serveur
	 */
    public Connection_Client(String hostname, String nomObjetDistant) {
        try {
        	clientConnexion = new TraitementConnexion();
        	serverConnexion = (TraitementConnexionInterface)Client.clientConnection(hostname, nomObjetDistant, "/home/r/ramosad/ProjetFinal/framework/ma_police.txt");          
        } catch (Exception e) {
            System.out.println("[System] Server failed: " + e);
        }
    }
    
    /**
     * Retourne le serveur utilise
     * @return le serveur utilise
     */
	public TraitementConnexionInterface getServerConnection() {
		return serverConnexion;
	}

	/**
	 * Retourne le client connecte
	 * @return le client connecte
	 */
	public TraitementConnexionInterface getClientConnection() {
		return clientConnexion;
	}
	
	/**
	 * Lance le client pour le chat en se connectant au serveur specifique
	 * @param hostname adresse IP de la machine serveur
	 * @param nomObjetDistant nom de l'objet distant du serveur
	 * @param login pseudo du client connecte
	 */
    public void Chat_Client(String hostname, String nomObjetDistant, String login) {
        try {
        	clientChat = new TraitementMessage(login);
        	serverChat = (TraitementMessageInterface)Client.clientConnection(hostname, nomObjetDistant, "/home/r/ramosad/ProjetFinal/framework/ma_police.txt");
        	laDate = new Date();
        	String date = sdf.format(laDate);
        	String msg = "[" + clientChat.getName() + " - "+"] est connecte";
            serverChat.send(msg);
            System.out.println("[System] Chat Remote Object is ready:");
            serverChat.addClient(clientChat);           
        } catch (Exception e) {
            System.out.println("[System] Server failed: " + e);
        }
    }
    
    /**
     * Envoie le message du client au serveur
     * @param message message envoye par le client
     * @throws RemoteException
     */
    public void getClientMessage(String message) throws RemoteException {
    	laDate = new Date();
    	String date=sdf.format(laDate);
        String msg = "[" + clientChat.getName() + " - "+date+"] " + message.trim();
        serverChat.send(msg);
    }
    
    /**
     * Retourne le serveur du chat utilise
     * @return le serveur du chat utilise
     */
    public TraitementMessageInterface getServerChat(){
        return serverChat;
    }
    
    /**
     * Retourne le client connecte au chat
     * @return le client connecte au chat
     * @throws RemoteException
     */
    public TraitementMessageInterface getClientChat() throws RemoteException{
        return clientChat;
    }
    
    /**
     * Envoie un message au serveur pour notifier la deconnexion de l'utilisateur
     * @throws RemoteException
     */
    public void disconnectClient() throws RemoteException{
    	laDate = new Date();
    	String date=sdf.format(laDate);
    	serverChat.send("["+clientChat.getName()+" - "+date+"]" + " deconnecte");
    }
    
    /**
	 * Lance le client pour le drive en se connectant au serveur specifique
     * @param hostname adresseIP de la machine serveur
     * @param nomObjetDistant nom de l'objet distant du serveur
     */
    public void Drive_Client(String hostname, String nomObjetDistant) {
        try {
        	clientDrive = new Fichiers();
        	serverDrive = (TraitementFichierInterface)Client.clientConnection(hostname, nomObjetDistant, "/home/r/ramosad/ProjetFinal/framework/ma_police.txt");
            System.out.println("[System] Drive Remote Object is ready:");       
        } catch (Exception e) {
            System.out.println("[System] Server failed: " + e);
        }
    }
    
    /**
     * Retourne le serveur utilise par le drive
     * @return le serveur du drive utilise
     */
	public TraitementFichierInterface getServerDrive() {
		return serverDrive;
	}

	/**
	 * Retourne le client connecte au drive
	 * @return le client connecte au drive
	 * @throws RemoteException
	 */
	public TraitementFichierInterface getClientDrive() throws RemoteException {
		return clientDrive;
	}
	
}