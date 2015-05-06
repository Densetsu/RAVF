package app.temoin;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	
	/**
	 * Permet au client de se connecter au serveur
	 * @param hostname adresse IP de la machine serveur
	 * @param remoteObjectName nom de l'objet distant du serveur
	 * @param policyPath chemin de la police utilis�e par le SecurityManager
	 * @return la connexion distante a l'objet du serveur
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
    public static Remote clientConnection(String hostname, String remoteObjectName, String policyPath) throws MalformedURLException, RemoteException, NotBoundException{
    	System.setProperty("java.security.policy",policyPath);
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        Registry registry = LocateRegistry.getRegistry(hostname);
        return registry.lookup(remoteObjectName);
    }
}
