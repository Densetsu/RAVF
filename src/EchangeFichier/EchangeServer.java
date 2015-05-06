package EchangeFichier;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.Inet6Address; 
import java.util.Enumeration; 
import java.net.NetworkInterface;

public class EchangeServer {
	public static void main(String[] args) {
		String chemin = "";
		String nom = "";
		String url="";
		int port = 1099;
		Registry registry = null;
		if (args.length != 3) {
			System.out.println("Server <chemin du fichier> <nom générique des objets distants> <port>");
			System.exit(1);
		}
		try {
			port = Integer.parseInt(args[2]);
			if ((registry = LocateRegistry.getRegistry(port)) == null) {
				registry = LocateRegistry.createRegistry(port);
			}
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			
			try {
				chemin = args[0];
				nom = args[1];
			} catch (Exception e) {
				System.out.println("Server <chemin du fichier> nom générique des objets distants <port registry>");
				System.exit(1);
			}
			try {
				 registry.rebind(nom, new Echange(chemin));
				 System.out.println("Serveur d'échange de fichier prêt, serveur connecté sur : " +url);
			} catch (Exception e) {
				System.out.println("EchangServ err: " + e);
			}
		} catch (Exception e) {
			System.out.println("[System] Server failed: " + e);
		}
	}
}