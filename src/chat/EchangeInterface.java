package chat;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EchangeInterface extends Remote{
	
	public void listeFicher()throws RemoteException;
	public void envoieFichier(String cheminFichier)throws RemoteException;
	public void recuperationFichier(File nomFichier, File cheminStockage) throws IOException, RemoteException;

	public byte[] lectureBytesFromFile(File fichier) throws IOException;
	public void ecritureBytesToFile(File leFichier, byte[] bytes) throws IOException, RemoteException;
}
