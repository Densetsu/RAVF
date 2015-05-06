package app.mail;


import java.io.Serializable;
import java.rmi.*;
import java.util.ArrayList;
import java.util.Date;

 
public class Mail implements Serializable{
	
	private static final long serialVersionUID = 2674880711467464646L;
	private String expediteur;
	private ArrayList<String> destinataire = new ArrayList<>();
	private String contenuMail;
	private String sujetMail;
	private Date dateMail;
	public MailInterface client=null;
 
	public Mail(String contenu, String nom, ArrayList<String> dest, String sujet)  throws RemoteException { 
		this.expediteur=nom;
		this.contenuMail=contenu;
		this.destinataire=dest;
		this.sujetMail=sujet;
		this.dateMail= new Date();
	}
	public String getExp() {
		return this.expediteur;
	}
	public ArrayList<String> getDest() {
		return this.destinataire;
	}
	
	public String getSujet() {
		return this.sujetMail;
	}
	public String getContenuMail() {
		return this.contenuMail;
	}
	public Date getDateMail() {
		return this.dateMail;
	}
 
	public MailInterface getClient(){
		return client;
	}
	
	//test nouvelle methode pour implements serializable (voir traitementMail et mailInterface)
	private int value;
	
	public Mail(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

}