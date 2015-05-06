package app.testApp;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

import fonction.gestionApplications.ApplicationInterface;
import base.connexion.ClientInterface;
import base.connexion.ServerImplementation;

public final class Serveur extends ServerImplementation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2182658555736650449L;

	public Serveur() throws NoSuchAlgorithmException, RemoteException {
		super("C:\\Users\\Subaru\\git\\RAVF\\RAVF\\etc\\ma_police.txt");
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws RemoteException {
		System.out.println("Serveur lancé");
		Serveur serveur = null;
		try {
			serveur = new Serveur();
			serveur.bind();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serveur.getApplication("chat");
		serveur.createUser("Moi", "pass");
	}

	@Override
	public void startApp(ClientInterface user, ApplicationInterface clientApp)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
