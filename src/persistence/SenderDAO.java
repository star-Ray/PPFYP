package persistence;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Sender;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

public class SenderDAO {
	// a. Sender class method: C R U D
	public static ArrayList<Sender> getAllSenders() {
		ArrayList<Sender> senders = new ArrayList<Sender>();
		DetachedCriteria dc = DetachedCriteria.forClass(Sender.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			senders.add((Sender) o);
		}
		return senders;
	}

	public static Sender getSenderById(long id) {
		return (Sender) HibernateUtil.get(Sender.class, id);
	}

	public static void addSender(Sender sender) {
		HibernateUtil.save(sender);
	}

	public static void modifySender(Sender modifiedSender) {
		HibernateUtil.update(modifiedSender);
	}

	public static void deleteSender(Sender sender) {
		HibernateUtil.delete(sender);
	}
	
	//features
	public static Sender getSenderByName(String name){
		Sender sender = null;
		Sender tempSender = null;
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Sender.class);
		
		detachedCriteria.add(Restrictions.eq(Key.NAME, name));
		
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		
		for(Object o : list){
			tempSender = (Sender)o;
			if(tempSender.getName().equals(name)){
				sender = tempSender;
				break;
			}
		}
		return sender;
	}
}
