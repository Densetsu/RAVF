package base.connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;

import fonction.gestionApplications.ApplicationInterface;
import base.user.User;

public interface ServerInterface extends Remote {
	public String connectUser(ClientInterface user, String userName, String clearPassword) throws RemoteException;
	public void startApp(ClientInterface user, ApplicationInterface clientApp) throws RemoteException;
}
