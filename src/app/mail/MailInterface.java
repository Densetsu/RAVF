package app.mail;

import java.rmi.*;
import java.util.ArrayList;

public interface MailInterface extends Remote{
	public void send(String contenu, String nom, ArrayList<String> dest, String sujet) throws RemoteException;
	
	//test pour implements serializable
	Mail f(Mail x) throws RemoteException;
}
