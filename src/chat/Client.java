package chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class Client {
    public static Remote connexionClient(String hostname, String nomObjetDistant, String cheminPolice) throws MalformedURLException, RemoteException, NotBoundException{
    	System.setProperty("java.security.policy",cheminPolice);
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        return Naming.lookup("rmi://"+hostname+"/"+nomObjetDistant);
    }
}
