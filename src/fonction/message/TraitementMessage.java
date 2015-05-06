package fonction.message;

import java.io.File;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import fonction.fichier.Fichiers;

public class TraitementMessage extends UnicastRemoteObject implements TraitementMessageInterface {
    private static final long serialVersionUID = 2674880711467464646L;
    protected String name;
    protected TraitementMessageInterface client = null;
    protected ArrayList<TraitementMessageInterface> listClient;
    protected ArrayList<String> listMessage = new ArrayList<>();
    protected String listUser;
    protected String storagePath = "";

    /**
     * Constructeur de TraitementMessage
     * @param n nom du client
     * @throws RemoteException
     */
    public TraitementMessage(String n) throws RemoteException {
        this.name = n;
        listClient = new ArrayList<>();
    }

    /**
     * Retourne le nom du client
     * @return le nom du client
     * @throws RemoteException
     */
    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    /**
     * Ajoute le client dans la liste des clients connectes
     * @param c le client a ajouter
     */
    @Override
    public synchronized void addClient(TraitementMessageInterface c) {
        this.listClient.add(c);
    }

    /**
     * Retourne la liste des clients connectes
     * @return la liste des clients connectes
     */
    @Override
    public ArrayList<TraitementMessageInterface> getClient() {
        return listClient;
    }

    /**
     * Permet au client d'envoyer un message au serveur, de remplir la liste de messages et de creer/completer un fichier de log
     * @param s message envoye par le client
     * @throws RemoteException
     */
    @Override
    public synchronized void send(String s) throws RemoteException {
    	s+="\n";
    	Fichiers file = new Fichiers();
        if (!listClient.isEmpty()) {
        	listMessage.add(s.trim());
        }
        try {
            File directory = new File(storagePath);
            file.writeFile(directory, s.getBytes(), true);
        } catch (IOException ex) {
            Logger.getLogger(TraitementMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retourne la liste des messages recu par le serveur
     * @return la liste des messages recu par le serveur
     * @throws RemoteException
     */
    @Override
    public synchronized ArrayList<String> getServerMessage() throws RemoteException {
        return listMessage;
    }

    /**
     * Retourne la liste des utilisateurs connectes
     * @return la liste des utilisateurs connectes
     * @throws RemoteException
     */
    @Override
    public synchronized String userConnected() throws RemoteException {
    	listUser = "";
        for (int i = 0; i < listClient.size(); i++) {
        	listUser+=listClient.get(i).getName()+"\n";
        }
        return listUser;
    }

    /**
     * Deconnecte un utilisateur en mettant a jour la liste des utilisateurs et la liste des clients
     * @param objectName le client a supprimer de la liste des clients
     * @param nom du client a deconnecter
     * @throws RemoteException
     */
    @Override
    public void disconnectUser(TraitementMessageInterface objectName, String clientName) throws RemoteException {
    	listClient.remove(objectName);
        if(listUser.contains(clientName)){
        	listUser.replace(clientName, "");
        }
    }

    /**
     * Fixe le chemin de stockage des log
     * @throws RemoteException
     */
    @Override
    public void setStoragePathLog(String path) throws RemoteException {
        this.storagePath = path;
    }
}

