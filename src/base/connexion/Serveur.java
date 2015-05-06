package base.connexion;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

public class Serveur {
	private static String IPadress="";
	
	/**
	 * Permet la connexion au serveur
	 * @param port, port utilisé pour la création du registre
	 * @param r, objet distant
	 * @param policyPath, chemin de la police utilisé par le SecurityManager
	 * @param name, nom de l'objet distant permettant au client de se connecter
	 * @throws RemoteException
	 * @throws SocketException
	 * @throws MalformedURLException
	 */
    public static void serverConnection(int port, Remote r, String policyPath, String name)  throws RemoteException, SocketException, MalformedURLException{
    	String url="";    	
    	Registry registry;
    	System.setProperty("java.security.policy",policyPath);

        if ((registry = LocateRegistry.getRegistry(port)) == null) {
            registry = LocateRegistry.createRegistry(port);
        }

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        setIPAdressServer();
        url="rmi://" + IPadress + "/" + name;
    	Naming.rebind(url, r);
    }
    
    /**
     * Fonction permettant d'avoir l'adresse IP de la machine serveur
     * @throws SocketException
     */
    public static void setIPAdressServer() throws SocketException{
        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); 
        while (e.hasMoreElements()){ 
          Enumeration<InetAddress> i = e.nextElement().getInetAddresses(); 
          while (i.hasMoreElements()){ 
            InetAddress a = i.nextElement();
            if(!a.isLoopbackAddress() && !a.isSiteLocalAddress() && !(a instanceof Inet6Address)){
            	IPadress=a.getHostName();
            }
          }
        }
    }
}
