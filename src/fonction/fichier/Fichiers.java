package fonction.fichier;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Fichiers extends UnicastRemoteObject implements TraitementFichierInterface {
	
	private static final long serialVersionUID = 2674880711467464646L;
	private String storagePathServer;

	/**
	 * Constructeur de TraitementFichier
	 * @throws RemoteException
	 */
	public Fichiers() throws RemoteException {
		super();
	}

	/**
	 * Retourne la liste de tout les fichiers disponibles sur le serveur
	 * @throws RemoteException
	 */
	@Override
	public void listFiles() throws RemoteException {
		// TODO Auto-generated method stub
	}

	/**
	 * Lit les octets d'un fichier et retourne le contenu dans un tableau byte[]
	 * @param fichier le fichier a lire
	 * @return un tableau byte[] avec le contenu du fichier
	 * @throws IOException Leve si il y a un probleme de lecture du fichier
	 * @throws RemoteException
	 */
	public byte[] readFile(File f) throws IOException, RemoteException {
		InputStream is = new FileInputStream(f);

		long length = f.length();

		if (length > Integer.MAX_VALUE) {
			extracted(f, length);
		}

		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			extracted(f);
		}

		is.close();
		return bytes;
	}

	/**
	 * @param f un descripteur de fichier
	 * @throws IOException
	 */
	private void extracted(File f) throws IOException {
		throw new IOException("Ne peut pas lire le fichier completement "+ f.getName());
	}

	/**
	 * @param f un descripteur de fichier
	 * @param length taille du fichier
	 * @throws IOException
	 */
	private void extracted(File f, long length) throws IOException {
		throw new IOException("Ne peut pas lire le fichier completement"
				+ f.getName() + " qui est trop gros (" + length
				+ " bytes, supportees maximum : " + Integer.MAX_VALUE + ")");
	}

	/**
	 * Ecrit les octets specifies dans le fichier specifie
	 * @param f fichier objet representant le chemin a ecrire
	 * @param bytes donnees a ecrire dans le fichier
	 * @param append booleen permettant de verifier si il faut creer le fichier ou non
	 * @throws IOException Leve si il y a un probleme de creation ou d'ecriture du fichier
	 * @throws RemoteException
	 */
	public void writeFile(File f, byte[] bytes, boolean append) throws IOException, RemoteException {
		BufferedOutputStream bos = null;
		FileOutputStream fos;
		try {
			if (!f.exists() || !append) {
				f.createNewFile();
				fos = new FileOutputStream(f);
				bos = new BufferedOutputStream(fos);
				bos.write(bytes);
			} else {
				DataOutputStream out = new DataOutputStream(
						new FileOutputStream(f, true));
				bos = new BufferedOutputStream(out);
				bos.write(bytes);
			}
		} catch (Exception e) {
			System.out.println("[System] Ecriture fichier failed: " + e);
		} finally {
			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (Exception e) {
					System.out
							.println("[System] Ecriture fichier failed: " + e);
				}
			}
		}
	}

	/**
	 * Retourne le chemin de stockage des fichiers du serveur
	 * @return le chemin de stockage des fichiers du serveur
	 */
	public String getStoragePathFiles() {
		return storagePathServer;
	}

	/**
	 * Fixe le chemin de stockage des fichiers pour le serveur
	 * @param f chemin de stockage
	 */
	public void setStoragePathFiles(String f) {
		this.storagePathServer = f;
	}

	/**
	 * Cree un dossier de stockage pour les fichiers du serveur
	 * @param dirName nom du dossier a creer
	 * @throws RemoteException
	 */
	public void createDir(String dirName) throws RemoteException {
		new File(storagePathServer + dirName).mkdir();
	}
}
