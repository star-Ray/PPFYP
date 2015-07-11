package testing;

import persistence.SuperAdminDAO;
import model.SuperAdmin;

public class Test {
	public static void main(String[] args){
		SuperAdmin sa = new SuperAdmin();
		SuperAdminDAO.addSuperAdmin(sa);
	}
}
