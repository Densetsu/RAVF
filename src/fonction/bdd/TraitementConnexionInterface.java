package fonction.bdd;
import java.rmi.*;

public interface TraitementConnexionInterface extends Remote{
	public String getBddServer() throws RemoteException;
	public void setBddServer(String f) throws RemoteException;
	boolean getUsers(String filename, String loginUser, char[] passwordUser) throws RemoteException;
}
