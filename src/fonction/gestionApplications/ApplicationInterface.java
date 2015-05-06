package fonction.gestionApplications;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ApplicationInterface extends Remote {

	public String getName() throws RemoteException;
}
