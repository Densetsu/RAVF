package base.communication;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GenericInterface<T extends Serializable> extends Remote {

	public void send(T item) throws RemoteException;
	public void send(T... items) throws RemoteException;
	public void send(String code, T item) throws RemoteException;
	public void send(String code, T... item) throws RemoteException;
}
