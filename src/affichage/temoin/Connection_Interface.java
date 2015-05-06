package affichage.temoin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;

import app.temoin.Connection_Client;
import fonction.bdd.*;
import fonction.fichier.*;
import fonction.message.*;

public class Connection_Interface extends javax.swing.JFrame {
    private static final long serialVersionUID = 2674880711467464646L;
    private String pathToFilesServer;
    private String activePath;
    protected static TraitementFichierInterface leDriveServer;
    protected static TraitementFichierInterface leDriveClient;
    protected FileSystemModel treeModel;
    protected File path;
    protected static TraitementConnexionInterface laConnexionServer;
    protected static TraitementConnexionInterface laConnexionClient;
    protected static TraitementFichierInterface bddServer;
    protected static Connection_Client connexionClient;
    protected static String myFile;
    protected static TraitementMessageInterface ChatServer;
    protected static TraitementMessageInterface ChatClient;
    protected String listeUser = "";

    /**
     * Instancie la connexion du client vers le serveur et initialise des composants de l'interface
     * @throws RemoteException
     */
    public Connection_Interface() throws RemoteException {
    	connexionClient = new Connection_Client("127.0.0.1", "connexionRMI");
    	laConnexionServer=connexionClient.getServerConnection();
    	laConnexionClient=connexionClient.getClientConnection();
    	myFile = laConnexionServer.getBddServer();
        initComponents();
    }                       
   
	/**
	 * Initialise les composants de l'interface
	 */
    private void initComponents() {
    	jTextFieldDossier = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jScrollPane10.setVisible(false);
        textAreaChat = new javax.swing.JTextArea();
        textAreaChat.setVisible(false);
        jLabel10 = new javax.swing.JLabel();
        jLabel10.setVisible(false);
        textFieldClientChat = new javax.swing.JTextField();
        textFieldClientChat.setVisible(false);
        jButton10 = new javax.swing.JButton();
        jButton10.setVisible(false);
        jButtonDriveOnChat = new javax.swing.JButton();
        jButtonDriveOnChat.setVisible(false);
        jButtonChatOnChat = new javax.swing.JButton();
        jButtonChatOnChat.setVisible(false);
        jScrollPane30 = new javax.swing.JScrollPane();
        jScrollPane30.setVisible(false);
        textAreaUserChat = new javax.swing.JTextArea();
        textAreaUserChat.setVisible(false);
        jButtonDriveOnDrive = new javax.swing.JButton();
        jButtonChatOnDrive = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jButton9 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                	connexionClient.disconnectClient();
                	ChatServer.disconnectUser(ChatClient, ChatClient.getName());
                    connexionClient=null;
                	leDriveClient=null;
                	ChatClient=null;
                	System.out.println("D�connexion r�ussie");
                } catch (Exception e) {
                    System.out.println("EXIT_ON_CLOSE " + e);
                }
            }
        });
        
        textAreaChat.setColumns(20);
        textAreaChat.setRows(5);
        jScrollPane10.setViewportView(textAreaChat);

        jLabel10.setText("Utilisateurs connect�s");

        jButton10.setText("Ok");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButtonDriveOnChat.setText("Drive");
        jButtonDriveOnDrive.setText("Drive");
        
        jButtonChatOnChat.setText("Chat");

        textAreaUserChat.setColumns(10);
        textAreaUserChat.setRows(5);
        jScrollPane30.setViewportView(textAreaUserChat);

        jButton1.setText("Ok");
        
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            	jButton1ActionPerformed(evt);
            }
        });
        
        jLabel1.setText("Connectez-vous :");

        jLabel2.setText("Pseudo");

        jLabel3.setText("Mot de passe");

        jButton11.setVisible(false);
        jButton12.setVisible(false);
        jButton13.setVisible(false);
        jButtonDriveOnDrive.setVisible(false);
        jButtonChatOnDrive.setVisible(false);
        jButton9.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jScrollPane1.setVisible(false);
        jTextFieldDossier.setVisible(false);
        jTextField2.setVisible(false);
        jTree1.setVisible(false);
        jButtonDriveOnChat.setText("Drive");
        
        jButtonDriveOnChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButtonDriveOnChatActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        setMinimumSize(new java.awt.Dimension(1000, 800));
        setPreferredSize(new java.awt.Dimension(1000, 800));
        
        jButtonChatOnDrive.setText("Chat");
        jButtonChatOnDrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChatOnDriveActionPerformed(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        jButton9.setText("T�l�charger");
        
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton9ActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        jLabel4.setText("S�l�ctionnez le fichier � t�l�charger ou un dossier � uploader");
        jLabel5.setText("Voulez-vous cr�er un nouveau dossier ?");
        jButton11.setText("Ok");
        
        jLabel7.setText("Drive");
        
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton11ActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jLabel6.setText("Nom du dossier");
        jLabel7.setText("Quel fichier voulez-vous uploader ?");
        jButton12.setText("Parcourir");
        
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton13ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        jButton13.setText("Upload");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(jButtonDriveOnChat, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(63, 63, 63)
                            .addComponent(jButtonChatOnChat, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                            .addComponent(textFieldClientChat)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton10)
                    .addGap(6, 6, 6))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(65, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton12)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jButtonDriveOnDrive, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldDossier, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton11))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addComponent(jButtonChatOnDrive, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(205, 205, 205)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(397, 397, 397))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(36, 36, 36)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addContainerGap(30, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDriveOnChat)
                    .addComponent(jButtonChatOnChat))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldClientChat, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDriveOnDrive)
                    .addComponent(jButtonChatOnDrive))
                .addGap(78, 78, 78)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12))
                        .addGap(18, 18, 18)
                        .addComponent(jButton13))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDossier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jButton11))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }     
    
    /**
     * Masque les composants de l'interface drive et rend visible ceux de l'interface chat
     * @param evt
     */
    public void jButtonChatOnDriveActionPerformed(java.awt.event.ActionEvent evt){
        jButton11.setVisible(false);
        jButton12.setVisible(false);
        jButton13.setVisible(false);
        jButtonChatOnDrive.setVisible(false);
        jButtonDriveOnDrive.setVisible(false);
        jButton9.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jScrollPane1.setVisible(false);
        jTextFieldDossier.setVisible(false);
        jTextField2.setVisible(false);
        jTree1.setVisible(false);
        jScrollPane10.setVisible(true);
        textAreaChat.setVisible(true);
        jLabel10.setVisible(true);
        textFieldClientChat.setVisible(true);
        jButton10.setVisible(true);
        jButtonChatOnChat.setVisible(true);
        jButtonDriveOnChat.setVisible(true);
        jScrollPane30.setVisible(true);
        textAreaUserChat.setVisible(true);
    }
    
    /**
     * Masque les composants de l'interface chat et rend visible ceux de l'interface drive, instancie la connexion du client vers le serveur drive
     * @param evt
     */
    private void jButtonDriveOnChatActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException{
        jButton11.setVisible(true);
        jButton12.setVisible(true);
        jButton13.setVisible(true);
        jButtonDriveOnDrive.setVisible(true);
        jButtonChatOnDrive.setVisible(true);
        jButton9.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jLabel6.setVisible(true);
        jLabel7.setVisible(true);
        jScrollPane1.setVisible(true);
        jTextFieldDossier.setVisible(true);
        jTextField2.setVisible(true);
        jTree1.setVisible(true);
        jScrollPane10.setVisible(false);
        textAreaChat.setVisible(false);
        jLabel10.setVisible(false);
        textFieldClientChat.setVisible(false);
        jButton10.setVisible(false);
        jButtonDriveOnChat.setVisible(false);
        jButtonChatOnChat.setVisible(false);
        jScrollPane30.setVisible(false);
        textAreaUserChat.setVisible(false);
        connexionClient.Drive_Client("127.0.0.1", "driveRMI");
        leDriveServer = connexionClient.getServerDrive();
    	leDriveClient=connexionClient.getClientDrive();
    	pathToFilesServer=leDriveServer.getStoragePathFiles();
    	activePath = pathToFilesServer;
        path = new File(pathToFilesServer);
        treeModel = new FileSystemModel(path);
        jTree1.setModel(treeModel);
    }
    
    /**
     * Envoie le message ecrit dans le jTextField vers le serveur
     * @param evt
     */
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String message = textFieldClientChat.getText();
        try {
            connexionClient.getClientMessage(message);
        } catch (Exception e) {
            System.out.println("Bouton Ok " + e);
        }
    }                                        
    
    /**
     * Rafraichit l'interface, les utilisateurs connectes et les message recus
     */
        private void printLog() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (true) {

                    try {
                        Thread.sleep(10);

                        if(!ChatServer.getServerMessage().isEmpty() && i<ChatServer.getServerMessage().size()){
                            textAreaChat.append(ChatServer.getServerMessage().get(i)+"\n");
                            i++;
                        }

                        if(!ChatServer.userConnected().isEmpty()){
                            textAreaUserChat.setText(ChatServer.userConnected());
                        }
                        
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        Logger.getLogger(Connection_Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread.start();
    }
        
    /**
     * Affiche l'interface de chat et instancie la connexion du client vers le serveur de chat    
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String login = jTextField1.getText();
        char[] password = jPasswordField1.getPassword();
        try {
        	if(laConnexionClient.getUsers(myFile, login, password)){
        		System.out.println("Connexion r�ussie");
        		//this.setVisible(false);
                jScrollPane10.setVisible(true);
                textAreaChat.setVisible(true);
                jLabel10.setVisible(true);
                textFieldClientChat.setVisible(true);
                jButton10.setVisible(true);
                jButtonDriveOnChat.setVisible(true);
                jButtonChatOnChat.setVisible(true);
                jScrollPane30.setVisible(true);
                textAreaUserChat.setVisible(true);
                jTextField1.setVisible(false);
                jPasswordField1.setVisible(false);
                jButton1.setVisible(false);
                jLabel1.setVisible(false);
                jLabel2.setVisible(false);
                jLabel3.setVisible(false);
                connexionClient.Chat_Client("127.0.0.1", "chatRMI", login);
                ChatServer = connexionClient.getServerChat();
                ChatClient = connexionClient.getClientChat();
                printLog();
        	}else{
        		System.out.println("Erreur de pseudo ou de mot de passe");
        	}
        } catch (Exception e) {
            System.out.println("Bouton Ok " + e);
        }
    }   
    
    /**
     * Telecharge un fichier a l'emplacement desire
     * @param evt
     * @throws IOException
     */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {  
    	String pathClient="";
    	File file = new File(activePath);
    	if(!file.isDirectory()){
    		byte[] content = leDriveClient.readFile(file);
        	JFileChooser jFileChooser1 = new JFileChooser();
        	jFileChooser1.setSelectedFile(new File(jFileChooser1.getCurrentDirectory(),file.getName()));
        	int status = jFileChooser1.showSaveDialog(null);
        	if(status ==JFileChooser.APPROVE_OPTION){
        		File f = jFileChooser1.getSelectedFile();
        		pathClient = f.getAbsolutePath();
            	File fileClient = new File(pathClient);
        		leDriveClient.writeFile(fileClient, content, false);
        	}
    	}else{
    		JOptionPane.showMessageDialog(null,"S�l�ctionnez un fichier et non un dossier !","Erreur",JOptionPane.ERROR_MESSAGE);
    	}
    }  
    
    /**
     * Cree un dossier avec le nom donne
     * @param evt
     * @throws RemoteException
     */
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {                                          
        String nomDossier = jTextFieldDossier.getText();
        leDriveServer.createDir(nomDossier);
        treeModel = new FileSystemModel(path);
    	jTree1.setModel(treeModel);
    }  

    /**
     * Parcoure la liste de fichier de l'utilisateur afin de recuperer le chemin du fichier a uploader
     * @param evt
     */
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        JFileChooser jFileChooser1 = new JFileChooser();
        int status = jFileChooser1.showOpenDialog(null);
        if(status ==JFileChooser.APPROVE_OPTION){
        	File f = jFileChooser1.getSelectedFile();
            String pathServer = f.getAbsolutePath();
            jTextField2.setText(pathServer);
        }  
    }      
    
    /**
     * Upload le fichier correspondant au chemin specifie
     * @param evt
     * @throws IOException
     */
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                          
        String filePath = jTextField2.getText();
        File myFile = new File(filePath);
        String fileName = myFile.getName();
        byte[] content = leDriveClient.readFile(myFile);
        File serverPath = new File(activePath+ "/" + fileName);
        leDriveServer.writeFile(serverPath, content, false);
        treeModel = new FileSystemModel(path);
        jTree1.setModel(treeModel);
    }
    
    /**
     * Recupere le chemin du dossier du serveur selectionne dans l'arbre
     * @param evt
     */
    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {
    	TreePath selectionPath = jTree1.getSelectionPath();
    	if (selectionPath != null) {
    		File last = (File) selectionPath.getPathComponent(selectionPath.getPathCount()-1);
    		activePath=last.getPath();
    	}
    }    
    /**
     * @param args les arguments de la ligne de commande
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Connection_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Connection_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Connection_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Connection_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					new Connection_Interface().setVisible(true);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
            }
        });
    }
               
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButtonDriveOnChat;
    private javax.swing.JButton jButtonChatOnChat;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane30;
    private static javax.swing.JTextArea textAreaChat;
    private javax.swing.JTextArea textAreaUserChat;
    private javax.swing.JTextField textFieldClientChat;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButtonDriveOnDrive;
    private javax.swing.JButton jButtonChatOnDrive;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldDossier;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTree jTree1;                  
}
