package fonction.bdd;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class TraitementConnexion extends UnicastRemoteObject implements TraitementConnexionInterface {
	private static final long serialVersionUID = 2674880711467464646L;
	private String bddServer;

	/**
	 * Constructeur de TraitementConnexion
	 * @throws RemoteException
	 */
	public TraitementConnexion() throws RemoteException {
		super();
	}

	/**
	 * Retourne le chemin du fichier de BDD comportant les noms d'utilisateurs et leurs mot de passe
	 */
	public String getBddServer() {
		return bddServer;
	}

	/**
	 * Fixe le chemin du fichier de BDD comportant les noms d'utilisateurs et leurs mot de passe
	 */
	public void setBddServer(String f) {
		this.bddServer = f;
	}

	/**
	 * Verifie la correspondance du login et du mot de passe par rapport a la BDD lors de la connexion
	 * @param filename nom du fichier contenant les login et password
	 * @param loginUser login de l'utilisateur
	 * @param passwordUser password de l'utilisateur
	 * @return Booleen qui vaut vrai si le login et le mot de passe correspondent
	 * @throws RemoteException
	 */
	public boolean getUsers(String filename, String loginUser, char[] passwordUser) throws RemoteException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		String login="";
		String myPassword="";
		for(int i=0; i<passwordUser.length;i++){
			myPassword+=passwordUser[i];
		}
		boolean connexionOk = false;
		String password="";
		try {
			File file = new File(filename);
			XMLStreamReader reader = factory
					.createXMLStreamReader(new FileReader(file));
			while (reader.hasNext()) {
				int type = reader.next();

				switch (type) {
				// Si c'est un début d'elément, on garde son type
				case XMLStreamReader.START_ELEMENT:
					if (reader.getLocalName().equals("login")) {
						login = reader.getElementText();
					} else if (reader.getLocalName().equals("password")) {
						password = reader.getElementText();
					}
					break;
				case XMLStreamReader.CHARACTERS:

					break;
				case XMLStreamReader.END_ELEMENT:
					if (reader.getLocalName().equals("utilisateur")) {
						if(login.equals(loginUser) && password.equals(myPassword)){
							connexionOk=true;
						}
					}
					break;
				}
			}
		} catch (IOException e) {
		} catch (XMLStreamException ex) {
		}
		return connexionOk;
	}
	
}
