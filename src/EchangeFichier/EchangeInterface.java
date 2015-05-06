package EchangeFichier;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EchangeInterface extends Remote{
	
	public void listeFicher()throws RemoteException;
	public void envoieFichier(String pathFile)throws RemoteException;
	public void recuperationFichier(File nameFile, File pathStock) throws IOException, RemoteException;

	public byte[] readFile(File file) throws IOException;
	public void writeFile(File file, byte[] bytes) throws IOException, RemoteException;
}
