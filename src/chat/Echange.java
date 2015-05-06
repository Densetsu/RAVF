package chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Echange extends UnicastRemoteObject implements EchangeInterface  {
	
	public String nomFichier;
	private static final long serialVersionUID = 2674880711467464646L;
	public Echange(String c) throws RemoteException{
		//super();
		nomFichier = c;
	}

	@Override
	public void listeFicher() throws RemoteException{
		// TODO Auto-generated method stub
	}

	@Override
	public void envoieFichier(String cheminFichier) throws RemoteException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recuperationFichier(File nomFichier, File cheminStockage) throws IOException ,RemoteException{
		byte[] contenuFichier = lectureBytesFromFile(nomFichier);
		ecritureBytesToFile(cheminStockage, contenuFichier);
	}
	     /** 
	     * Lecture des byte d'un fichier dans une liste de byte.
	     * 
	     * @param fichier le fichier à lire.
	     * @return une liste byte[] contenant le contenu du fichier.
	     * @throws IOException 
	     */
	    public byte[] lectureBytesFromFile(File fichier) throws IOException ,RemoteException{
	      InputStream is = new FileInputStream(fichier);
	      
	      long length = fichier.length();
	  
	      if (length > Integer.MAX_VALUE) {
	        throw new IOException("Ne peut pas lire le fichier complétement" + fichier.getName() + " qui est trop gros (" + length + " bytes, supportées maximum : " + Integer.MAX_VALUE + ")");
	      }
	  
	      byte[] bytes = new byte[(int)length];
	  
	      int offset = 0;
	      int numRead = 0;
	      while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	          offset += numRead;
	      }
	  
	      if (offset < bytes.length) {
	          throw new IOException("Ne peut pas lire le fichier complétement " + fichier.getName());
	      }
	  
	      is.close();
	      return bytes;
	  }
	    
	    /**
	     * Ecrit les byte[] spécifiés dans le fichier spécifié.
	     * 
	     * @param leFichier fichier objet representant le chemin où écrire.
	     * @param bytes données à écrire dans le fichier.
	     * @throws IOException Thrown si il y a un problème de création ou d'écriture du fichier.
	     */
	    public void ecritureBytesToFile(File leFichier, byte[] bytes) throws IOException ,RemoteException{
	      BufferedOutputStream bos = null;
	      FileOutputStream fos;
	      
	      try {
	            if (!leFichier.exists()) {
	                leFichier.createNewFile();
	                fos = new FileOutputStream(leFichier);
	                bos = new BufferedOutputStream(fos);
	                bos.write(bytes);
	            } else {
	                DataOutputStream out = new DataOutputStream(new FileOutputStream(leFichier , true));
	                bos = new BufferedOutputStream(out);
	                bos.write(bytes);
	            }
	    }catch(Exception e){
        	System.out.println("[System] Ecriture fichier failed: " + e);
	    }finally {
	      if(bos != null) {
	        try  {
	          bos.flush();
	          bos.close();
	        } catch(Exception e){
	        	System.out.println("[System] Ecriture fichier failed: " + e);
	        }
	      }
	    }
	    }
	}
