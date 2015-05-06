package base.connexion;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import fonction.gestionApplications.ApplicationInterface;

public abstract class ClientImplementation extends UnicastRemoteObject
		implements ClientInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7300439078243306025L;
	private ServerInterface server;

	private Registry registry;

	protected ClientImplementation(String cheminPolice) throws RemoteException {
		super();
		System.setProperty("java.security.policy", cheminPolice);
		if ((this.registry = LocateRegistry.getRegistry()) == null) {
			this.registry = LocateRegistry.createRegistry(1099);
		}
		// this.registry = LocateRegistry.createRegistry(port);
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
	}

}
