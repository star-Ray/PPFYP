package persistence;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Sender;
import model.Task;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

public class TaskDAO {
	// a. Task class method: C R U D
	public static ArrayList<Task> getAllTasks() {
		ArrayList<Task> tasks = new ArrayList<Task>();
		DetachedCriteria dc = DetachedCriteria.forClass(Task.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			tasks.add((Task) o);
		}
		return tasks;
	}

	public static Task getTaskById(long id) {
		return (Task) HibernateUtil.get(Task.class, id);
	}

	public static void addTask(Task task) {
		HibernateUtil.save(task);
	}

	public static void modifyTask(Task modifiedTask) {
		HibernateUtil.update(modifiedTask);
	}

	public static void deleteTask(Task task) {
		HibernateUtil.delete(task);
	}
	
	//features
	public static ArrayList<Task> getTasksBySender(Sender sender){
		ArrayList<Task> tasks = new ArrayList<Task>();
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Task.class);
		
		detachedCriteria.add(Restrictions.eq(Key.SENDER, sender));
		
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		
		for(Object o : list){
			tasks.add((Task) o);
		}
		
		return tasks;
	}
}
