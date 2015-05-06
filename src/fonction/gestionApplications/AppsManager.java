package fonction.gestionApplications;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.HashMap;

import base.user.User;

public class AppsManager {
	private Map<String, AppData> apps;

	public AppsManager() {
		apps = new HashMap<>();
	}

	public void addApp(ApplicationInterface app) throws RemoteException {
		AppData adata = new AppData(app);
		apps.put(app.getName(), adata);
	}

	public void removeApp(ApplicationInterface app) throws RemoteException {
		apps.remove(app.getName());
	}

	public ApplicationInterface getApp(String nomApp) {
		return (apps.get(nomApp) == null) ? null : apps.get(nomApp).getApp();
	}
	
	protected class AppData {
		private ApplicationInterface app = null;
		private Map<String, User> users = new HashMap<>();

		public AppData(ApplicationInterface app) {
			this.setApp(app);
		}

		public AppData(ApplicationInterface app, User... users) {
			this(app);
			for (User user : users) {
				this.users.put(user.getName(), user);
			}
		}

		public ApplicationInterface getApp() {
			return app;
		}

		public void setApp(ApplicationInterface app) {
			this.app = app;
		}

	}
}
