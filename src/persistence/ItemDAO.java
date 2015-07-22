package persistence;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.Task;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

public class ItemDAO {
	// a. Item class method: C R U D
	public static ArrayList<Item> getAllItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		DetachedCriteria dc = DetachedCriteria.forClass(Item.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			items.add((Item) o);
		}
		return items;
	}

	public static Item getItemById(long id) {
		return (Item) HibernateUtil.get(Item.class, id);
	}

	public static void addItem(Item item) {
		HibernateUtil.save(item);
	}

	public static void modifyItem(Item modifiedItem) {
		HibernateUtil.update(modifiedItem);
	}

	public static void deleteItem(Item item) {
		HibernateUtil.delete(item);
	}
	
	//features
	public static Item getItemByNfcTag(String nfcTag){
		Item item = null;
		Item tempItem = null;
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Item.class);
		
		detachedCriteria.add(Restrictions.eq(Key.NFCTAG, nfcTag));
		
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		
		for(Object o : list){
			tempItem = (Item)o;
			if(tempItem.getNfcTag().equals(nfcTag)){
				item = tempItem;
				break;
			}
		}
		return item;
	}

	public static ArrayList<Item> getItemsByTask(Task task) {
		ArrayList<Item> items = new ArrayList<Item>();
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Item.class);
		
		detachedCriteria.add(Restrictions.eq(Key.TASK, task));
		
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		
		for(Object o : list){
			items.add((Item) o);
		}
		
		return items;
	}
}
