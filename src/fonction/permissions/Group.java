package fonction.permissions;

import java.security.Principal;
import java.util.Enumeration;

public class Group implements java.security.acl.Group {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addMember(Principal arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMember(Principal arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Enumeration<? extends Principal> members() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeMember(Principal arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
