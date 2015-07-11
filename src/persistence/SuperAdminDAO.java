package persistence;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.SuperAdmin;

import org.hibernate.criterion.DetachedCriteria;

public class SuperAdminDAO {
	// a. SuperAdmin class method: C R U D
	public static ArrayList<SuperAdmin> getAllSuperAdmins() {
		ArrayList<SuperAdmin> superAdmins = new ArrayList<SuperAdmin>();
		DetachedCriteria dc = DetachedCriteria.forClass(SuperAdmin.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			superAdmins.add((SuperAdmin) o);
		}
		return superAdmins;
	}

	public static SuperAdmin getSuperAdminById(long id) {
		return (SuperAdmin) HibernateUtil.get(SuperAdmin.class, id);
	}

	public static void addSuperAdmin(SuperAdmin superAdmin) {
		HibernateUtil.save(superAdmin);
	}

	public static void modifySuperAdmin(SuperAdmin modifiedSuperAdmin) {
		HibernateUtil.update(modifiedSuperAdmin);
	}

	public static void deleteSuperAdmin(SuperAdmin superAdmin) {
		HibernateUtil.delete(superAdmin);
	}
}
