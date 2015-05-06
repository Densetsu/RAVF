package app.testApp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import base.connexion.ClientImplementation;
import base.connexion.ServerInterface;

public class Client extends ClientImplementation {

	private ServerInterface serveur;

	protected Client() throws RemoteException {
		super("C:\\Users\\Subaru\\git\\RAVF\\RAVF\\etc\\ma_police.txt");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7624703590232118537L;

	public static void main(String[] args) {
		System.out.println("Client lancé");
		Client client = null;
		try {
			client = new Client();
			client.connect("localhost");
			
			System.out.println("Nom de l'utilisateur connecté : " + client.serveur.connectUser(client, "Moi", "pass"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void connect(String string) {
		try {
			serveur = (ServerInterface) Naming.lookup("//localhost/" + "serv");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
