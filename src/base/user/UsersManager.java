package base.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;

public class UsersManager {
	private Map<String, UserData> users;

	public UsersManager() throws NoSuchAlgorithmException {
		super();
		users = new HashMap<>();
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(UsersManager.class.getName()).log(Level.WARNING,
					"SHA-2 non disponible, hashage des mots de passe en MD5",
					ex);
			digest = MessageDigest.getInstance("MD5");
		}
	}

	public void addUser(User user, String clearPassword) {
		UserData udata = new UserData(user, hashString(clearPassword));
		users.put(user.getName(), udata);
	}

	public void addHashedUser(User user, String hashedPassword) {
		UserData udata = new UserData(user, hashedPassword);
		users.put(user.getName(), udata);
	}

	public void deleteUser(User user) {
		users.remove(user.getName());
	}

	public User getUser(String userName, String clearPassword) {
		User user = null;
		if (users.get(userName).getPassword().equals(hashString(clearPassword))) {
			user = users.get(userName).getUser();
		}
		return user;
	}

	public User getHashedUser(String userName, String hashedPassword) {
		return (users.get(userName).getPassword().equals(hashedPassword)) ? users.get(userName).getUser() : null;
	}

	private static MessageDigest digest;

	public static String hashString(String clearPassword) {
		byte[] inputBytes = clearPassword.getBytes();
		byte[] hashBytes = digest.digest(inputBytes);
		return DatatypeConverter.printHexBinary(hashBytes);
	}

	protected class UserData {
		private User user;
		private String password;

		// private Map<String, Group> groups;

		public UserData(User user, String password) {
			this.user = user;
			this.password = password;
			// this.groups = new HashMap<>();
		}

		/*
		 * public UserData(User user, String password, Group... groups) {
		 * this(user, pass); for (User group : groups) {
		 * this.groups.put(group.getName(), group); } }
		 */
		public User getUser() {
			return user;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String hashedPassword) {
			this.password = hashedPassword;
		}
	}
}
