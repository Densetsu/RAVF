package EchangeFichier;

import java.io.File;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class EchangeClient {
	public static void main(String[] args) {
		String nom = "", host = "", chemin="";
		String nomFichier ="";
		// récupération des arguments
		if (args.length != 2) {
			System.out.println("Client <hostname> <nom générique des objets distants>");
			System.exit(1);
		}
		try {
			host = args[0];
			nom = args[1];
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			//Registry registry = LocateRegistry.getRegistry(host);
/*	    	Scanner s1=new Scanner(System.in);
	    	System.out.println("1 pour importer et 2 pour exporter :");
	    	int reponse=s1.nextInt();
	    	if(reponse==2){
		    	Scanner s2=new Scanner(System.in);
		    	System.out.println("Nom du fichier à exporter :");
		    	String nom2 = s2.nextLine();
	    		chemin="/home/r/ramosad/ProjetFinal/framework/src/stockage/"+nom2;
	    		chemin.trim();
	    	}else{
*/
		    System.out.println("Entrez le nom du fichier à copier :");
		    Scanner s4=new Scanner(System.in);
		    nomFichier = s4.nextLine();
		    	
		    Scanner s=new Scanner(System.in);
		    System.out.println("Entrez le chemin où vous voulez stocker le fichier:");
		    chemin=s.nextLine().trim();	
//	    	}
	    	EchangeInterface server = (EchangeInterface)Naming.lookup("rmi://"+host+"/"+nom);;
	    	System.out.println("L'objet distant est lié");
	    	
	    	File fichier = new File(nomFichier);
	    	File cheminStockage = new File(chemin);
	    	server.recuperationFichier(fichier, cheminStockage);
	    	System.out.println("Fichier téléchargé");
		} catch (Exception e) {
			System.out.println("[System] Server failed: " + e);
		}
	}
}