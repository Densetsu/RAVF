package app.mail;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.Inet6Address;
import java.util.Enumeration;
import java.net.NetworkInterface;

public class MailServer {

	public static void main(String[] args) {
		String ip = "";
		String nom = "";
		String url = "";
		int port = 1099;
		Registry registry = null;
		if (args.length != 2) {
			System.out.println("Server <nom générique des objets distants> <port>");
			System.exit(1);
		}
		try {
			port = Integer.parseInt(args[1]);
			if ((registry = LocateRegistry.getRegistry(port)) == null) {
				registry = LocateRegistry.createRegistry(port);
			}
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			try {
				nom = args[0];
			} catch (Exception e) {
				System.out.println("Server <nom générique des objets distants> <port registry>");
				System.exit(1);
			}
			try {
				registry.rebind(nom, new TraitementMail());
				System.out.println("Serveur de mail prêt, serveur connecté sur : "+ url);
			} catch (Exception e) {
				System.out.println("MailServ err: " + e);
			}
		} catch (Exception e) {
			System.out.println("[System] Server failed: " + e);
		}
	}

	public String getMessageServer(String nom) throws IOException {
		return null;
	}
}
