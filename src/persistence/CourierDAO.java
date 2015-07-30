package persistence;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.Courier;
import model.Courier;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

public class CourierDAO {
	// a. Courier class method: C R U D
	public static ArrayList<Courier> getAllCouriers() {
		ArrayList<Courier> couriers = new ArrayList<Courier>();
		DetachedCriteria dc = DetachedCriteria.forClass(Courier.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			couriers.add((Courier) o);
		}
		return couriers;
	}

	public static Courier getCourierById(long id) {
		return (Courier) HibernateUtil.get(Courier.class, id);
	}

	public static void addCourier(Courier courier) {
		HibernateUtil.save(courier);
	}

	public static void modifyCourier(Courier modifiedCourier) {
		HibernateUtil.update(modifiedCourier);
	}

	public static void deleteCourier(Courier courier) {
		HibernateUtil.delete(courier);
	}
	
	//features
	public static Courier getCourierByName(String name){
		Courier courier = null;
		Courier tempCourier = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Courier.class);
		detachedCriteria.add(Restrictions.eq(Key.NAME, name));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempCourier = (Courier)o;
			if(tempCourier.getName().equals(name)){
				courier = tempCourier;
				break;
			}
		}
		return courier;
	}
	
	public static Courier getCourierByUserName(String username){
		Courier courier = null;
		Courier tempCourier = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Courier.class);
		detachedCriteria.add(Restrictions.eq(Key.USERNAME, username));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tempCourier = (Courier)o;
			if(tempCourier.getName().equals(username)){
				courier = tempCourier;
				break;
			}
		}
		return courier;
	}
	
	public static ArrayList<Courier> getCouriersByCompany(Company company){
		ArrayList<Courier> tasks = new ArrayList<Courier>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Courier.class);
		detachedCriteria.add(Restrictions.eq(Key.COMPANY, company));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			tasks.add((Courier) o);
		}
		return tasks;
	}
}
