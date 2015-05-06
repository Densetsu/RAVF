package fonction.fichier;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TraitementFichierInterface extends Remote{
	public void listFiles()throws RemoteException;
	public byte[] readFile(File file) throws IOException, RemoteException;
	public void writeFile(File file, byte[] bytes, boolean append) throws IOException, RemoteException;
	public String getStoragePathFiles() throws RemoteException;
	public void setStoragePathFiles(String f) throws RemoteException;
	public void createDir(String dirName) throws RemoteException;
}