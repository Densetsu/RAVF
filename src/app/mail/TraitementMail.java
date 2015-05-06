package app.mail;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class TraitementMail extends UnicastRemoteObject implements MailInterface{
	private static final long serialVersionUID = 2674880711467464646L;
	protected TraitementMail() throws RemoteException {
		super();
	}

	public void send(String contenu, String nom, ArrayList<String> dest, String sujet) throws RemoteException{
		new Mail(contenu, nom, dest, sujet);
	}
	
	//test pour implements serializable
	public Mail f(Mail v) throws RemoteException {
		return new Mail(v.getValue() + 1);
	}
}
