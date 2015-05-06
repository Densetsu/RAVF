package chat;

import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	
    public static void connexionServer(int port, Remote r, String cheminPolice, String nom) throws RemoteException{
    	System.setProperty("java.security.policy",cheminPolice);
    	Registry registry = null;
        if ((registry = LocateRegistry.getRegistry(port)) == null) {
            registry = LocateRegistry.createRegistry(port);
        }
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
    	registry.rebind(nom, r);
    }
}
