package base.communication;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class GenericImplementation<T extends Serializable> extends UnicastRemoteObject implements GenericInterface<T> {

	protected GenericImplementation() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465307928267315451L;

	@Override
	public void send(T item) {
		process(item);
	}

	@Override
	public void send(T... items) {
		process(items);
	}
	
	@Override
	public void send(String code, T item) {
		process(item);
	}
	
	@Override
	public void send(String code, T... items) {
		process(items);
	}
	
	public abstract void process(T item);
	public void process(T... items) {
		for (T item : items) {
			process(item);
		}
	}

}
