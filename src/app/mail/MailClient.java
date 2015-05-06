package app.mail;

import java.rmi.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class MailClient {
	public static void lanceClientMail (String hostname, String nomObjetDistant) {
		String nom = hostname, host = nomObjetDistant;
		Date dateActuelle = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-mm-yyyy hh24:mi");
		try {			
				if (System.getSecurityManager() == null) {
					System.setSecurityManager(new RMISecurityManager());
				}
		    	
		    	MailInterface server = (MailInterface)Naming.lookup("rmi://" + host + "/" + nom);
		    	
		    	System.out.println("[System] Mail Remote Object is ready:");
		    	Scanner s1=new Scanner(System.in);
		    	System.out.println("Entrez votre nom:");
		    	String nomExp=s1.nextLine().trim();
		    	
		    	Scanner s2=new Scanner(System.in);
		    	System.out.println("Entrez le ou les destinataires:");
		    	ArrayList<String> nomDest = new ArrayList<>();
		    	while(s2.hasNextLine()){
		    		String dest = s2.nextLine().trim();
		    		nomDest.add(dest);
		    	}
		    	
		    	Scanner s3=new Scanner(System.in);
		    	System.out.println("Entrez le sujet du mail:");
		    	String sujet=s1.nextLine().trim();
		    	
		    	String msg="";
		    	String maDate = ft.format(dateActuelle);
		    	while(true){
		    		msg=s1.nextLine().trim();
		    		msg=nomExp+msg+"\n envoyé le "+maDate;		    		
	    			server.send(msg, nomExp, nomDest, sujet);
		    	}
		    	
	    	}catch (Exception e) {
	    		System.out.println("[System] Server failed: " + e);
	    	}
		}
}