package fonction.gestionApplications;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class ApplicationImplementation extends UnicastRemoteObject implements ApplicationInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7947835728089367942L;
	String name;
	
	public ApplicationImplementation(String name) throws RemoteException {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
