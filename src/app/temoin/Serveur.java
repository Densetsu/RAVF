package app.temoin;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import fonction.gestionApplications.ApplicationInterface;
import fonction.message.TraitementMessage;
import base.connexion.ClientInterface;
import base.connexion.ServerImplementation;

public class Serveur extends ServerImplementation {
	private static final long serialVersionUID = -2736556788590994612L;
	
	public Serveur(int port, String cheminPolice)
			throws NoSuchAlgorithmException, RemoteException {
		super(port, cheminPolice);
		// TODO Auto-generated constructor stub
	}

	private static String IPadress="";
	
	/**
	 * Permet la connexion au serveur
	 * @param port port utilise pour la creation du registre
	 * @param r objet distant
	 * @param policyPath chemin de la police utilise par le SecurityManager
	 * @param name nom de l'objet distant permettant au client de se connecter
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
        
        url="rmi://" + IPadress + "/" + name;
    	Naming.rebind(url, r);
    }
    
    /**
     * Recupere l'adresse IP de la machine serveur
     * @throws SocketException
     */
    public String getIPAdressServer() throws SocketException{
    	String iPAdress = "localhost";
        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); 
        while (e.hasMoreElements()){ 
          Enumeration<InetAddress> i = e.nextElement().getInetAddresses(); 
          while (i.hasMoreElements()){ 
            InetAddress a = i.nextElement();
            if(!a.isLoopbackAddress() && !a.isSiteLocalAddress() && !(a instanceof Inet6Address)){
            	iPAdress = a.getHostName();
            }
          }
        }
        return iPAdress;
    }

	@Override
	public void startApp(ClientInterface user, String nomApp)
			throws RemoteException {
			aManager.addUser(user,nomApp);
	}

	@Override
	public ApplicationInterface getApplication(String nomApp, String param) throws AccessException, RemoteException {
			ApplicationInterface a = null;
			String p = param.isEmpty() ? nomApp : param;
			
				switch (nomApp) {
				case "chat":
					if ((a = aManager.getApp("chat")) == null) {
						try {
							TraitementMessage chat = new TraitementMessage("chat");
					        chat.setStoragePathLog("/home/r/ramosad/ProjetFinal/Serveur/stockage/chat/log");
					        try {
					            Serveur.serverConnection(portRegistry, serverChat, "/home/r/ramosad/ProjetFinal/framework/ma_police.txt",remoteObjectName);
					            serverChat.setStoragePathLog("/home/r/ramosad/ProjetFinal/Serveur/stockage/chat/log");
					        } catch (Exception e) {
					            System.out.println("Serv chat err: " + e);
					        }
					    } catch (Exception e) {
					        System.out.println("[System] Server chat failed: " + e);
					    }
						try {
							registry.bind("chat", a);
						} catch (AlreadyBoundException e1) {
							// Shouldn't be able to both already be and not be bound...
							e1.printStackTrace();
						}
					}
					break;
				case "drive":
					try {
						a = (ApplicationInterface) registry.lookup("drive");
					} catch (NotBoundException e) {
						//a = new Drive_Server();
						try {
							registry.bind("drive", a);
						} catch (AlreadyBoundException e1) {
							// Shouldn't be able to both already be and not be bound...
							e1.printStackTrace();
						}
					}
					break;
				}
				return a;
			}
	}
}
