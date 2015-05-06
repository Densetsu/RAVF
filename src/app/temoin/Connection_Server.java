package app.temoin;

import base.connexion.*;
import fonction.bdd.TraitementConnexion;
import fonction.fichier.Fichiers;
import fonction.message.TraitementMessage;

public class Connection_Server {

    protected static TraitementConnexion serverConnection;
    protected static TraitementMessage serverChat;
    protected static Fichiers serverDrive;

    /**
     * Lance les serveur de connection, drive et chat
     * @param args
     */
    public static void main(String[] args){
    	launchServerConnection("connexionRMI", 1099);
    	launchServerChat("chatRMI", 1099);
    	launchServerDrive("driveRMI", 1099);
        System.out.println("[System] Remote Object is ready:");
    }
    
    /**
     * Lance le serveur de connexion
     * @param remoteObjectName nom de l'objet distant du serveur
     * @param portRegistry port du rmiregistry
     */
    public static void launchServerConnection(String remoteObjectName, int portRegistry) {
            try {
            	serverConnection = new TraitementConnexion();
                try {
                    Serveur.serverConnection(portRegistry, serverConnection, "/home/r/ramosad/ProjetFinal/framework/ma_police.txt",remoteObjectName);
                    serverConnection.setBddServer("/home/r/ramosad/ProjetFinal/Serveur/stockage/BDD/bddConnection.xml");
            } catch (Exception e) {
                System.out.println("Serv connection err: " + e);
            }

        } catch (Exception e) {
            System.out.println("[System] Server connection failed: " + e);
        }
    }
    
    /**
     * Lancer le serveur de chat
     * @param remoteObjectName nom de l'objet distant du serveur
     * @param portRegistry port du rmiregistry
     */
    public static void launchServerChat(String remoteObjectName, int portRegistry) {
        try {
        	serverChat = new TraitementMessage(remoteObjectName);
            try {
                Serveur.serverConnection(portRegistry, serverChat, "/home/r/ramosad/ProjetFinal/framework/ma_police.txt",remoteObjectName);
                serverChat.setStoragePathLog("/home/r/ramosad/ProjetFinal/Serveur/stockage/chat/log");
            } catch (Exception e) {
                System.out.println("Serv chat err: " + e);
            }
        } catch (Exception e) {
            System.out.println("[System] Server chat failed: " + e);
        }
    }

    /**
     * Lance le serveur de drive
     * @param remoteObjectName nom de l'objet distant du serveur
     * @param portRegistry port du rmiregistry
     */
    public static void launchServerDrive(String remoteObjectName, int portRegistry) {
        try {
        	serverDrive = new Fichiers();
            try {
                Serveur.serverConnection(portRegistry, serverDrive, "/home/r/ramosad/ProjetFinal/framework/ma_police.txt",remoteObjectName);
                serverDrive.setStoragePathFiles("/home/r/ramosad/ProjetFinal/Serveur/stockage/Files/");
            } catch (Exception e) {
                System.out.println("Serv drive err: " + e);
            }

        } catch (Exception e) {
            System.out.println("[System] Server drive failed: " + e);
        }
    }
}
