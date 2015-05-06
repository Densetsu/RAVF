package base.connexion;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import fonction.gestionApplications.*;
import base.user.*;

public abstract class ServerImplementation extends UnicastRemoteObject implements ServerInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5568981834872237720L;
	private HashMap<User, ClientInterface> clients;
	protected UsersManager uManager;
	protected AppsManager aManager;
	private Registry registry;

	
	public ServerImplementation(int port, String cheminPolice) throws NoSuchAlgorithmException, RemoteException {
		super(port);
		this.clients = new HashMap<>();
		this.uManager = new UsersManager();
		this.aManager = new AppsManager();
		System.setProperty("java.security.policy", cheminPolice);
		if ((this.registry = LocateRegistry.getRegistry(port)) == null) {
			this.registry = LocateRegistry.createRegistry(port);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
	}

	public ServerImplementation(String cheminPolice) throws NoSuchAlgorithmException, RemoteException {
		this(1099, cheminPolice);
	}
	
	public abstract ApplicationInterface getApplication(String nomApp, String param) throws AccessException, RemoteException;

	public void bind() throws AccessException, RemoteException {
		try {
			this.registry.bind("serv", this);
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createUser(String userName, String clearPassword) throws RemoteException {
		this.uManager.addUser(new User(userName), clearPassword);
	}
	
	
	// RMI-accessible methods
	@Override
	public String connectUser(ClientInterface user, String userName, String clearPassword) throws RemoteException {
		User tmpUser;
		if ((tmpUser = this.uManager.getUser(userName, clearPassword)) != null) {
			this.clients.put(tmpUser, user);
		}

		return (tmpUser == null)? "" : tmpUser.getName();
	}

}
