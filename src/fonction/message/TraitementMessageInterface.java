package fonction.message;

import java.rmi.*;
import java.util.ArrayList;

import fonction.gestionApplications.ApplicationInterface;

public interface TraitementMessageInterface extends ApplicationInterface{
	public String getName() throws RemoteException;
	public void send(String msg) throws RemoteException;
	public void addClient(TraitementMessageInterface c)throws RemoteException;
	public ArrayList<TraitementMessageInterface> getClient() throws RemoteException;
    public String userConnected() throws RemoteException;
    public void disconnectUser(TraitementMessageInterface objectName, String clientName) throws RemoteException;
    public ArrayList<String> getServerMessage() throws RemoteException;
    public void setStoragePathLog(String path) throws RemoteException;
}
