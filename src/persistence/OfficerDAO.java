package persistence;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.Officer;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

public class OfficerDAO {
	// a. Officer class method: C R U D
	public static ArrayList<Officer> getAllOfficers() {
		ArrayList<Officer> officers = new ArrayList<Officer>();
		DetachedCriteria dc = DetachedCriteria.forClass(Officer.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			officers.add((Officer) o);
		}
		return officers;
	}

	public static Officer getOfficerById(long id) {
		return (Officer) HibernateUtil.get(Officer.class, id);
	}

	public static void addOfficer(Officer officer) {
		HibernateUtil.save(officer);
	}

	public static void modifyOfficer(Officer modifiedOfficer) {
		HibernateUtil.update(modifiedOfficer);
	}

	public static void deleteOfficer(Officer officer) {
		HibernateUtil.delete(officer);
	}
	
	//features
	public static Officer getOfficerByUsername(String username){
		Officer officer = null;
		Officer tempOfficer = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Officer.class);
		detachedCriteria.add(Restrictions.eq(Key.USERNAME, username));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempOfficer = (Officer)o;
			if(tempOfficer.getName().equals(username)){
				officer = tempOfficer;
				break;
			}
		}
		return officer;
	}
	
	public static ArrayList<Officer> getOfficersByCompany(Company company){
		ArrayList<Officer> tasks = new ArrayList<Officer>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Officer.class);
		detachedCriteria.add(Restrictions.eq(Key.COMPANY, company));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tasks.add((Officer) o);
		}
		return tasks;
	}
}
