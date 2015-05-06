package base.user;

import java.rmi.RemoteException;

import base.connexion.ClientInterface;

public class User implements java.security.Principal {

	private String username;
	private ClientInterface client = null;

	protected User() throws RemoteException {
		super();
	}

	public User(String name) {
		this.username = name;
	}

	public String getName() {
		return username;
	}

	public void setClient(ClientInterface client) {
		this.client = client;
	}

	public ClientInterface getClient() {
		return client;
	}

}
