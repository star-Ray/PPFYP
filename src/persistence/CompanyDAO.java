package persistence;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.SuperAdmin;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;

public class CompanyDAO {
	// a. Company class method: C R U D
	public static ArrayList<Company> getAllCompanys() {
		ArrayList<Company> companys = new ArrayList<Company>();
		DetachedCriteria dc = DetachedCriteria.forClass(Company.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			companys.add((Company) o);
		}
		return companys;
	}

	public static Company getCompanyById(long id) {
		return (Company) HibernateUtil.get(Company.class, id);
	}

	public static void addCompany(Company company) {
		HibernateUtil.save(company);
	}

	public static void modifyCompany(Company modifiedCompany) {
		HibernateUtil.update(modifiedCompany);
	}

	public static void deleteCompany(Company company) {
		HibernateUtil.delete(company);
	}
	
	//features
	public static Company getCompanyByName(String name){
		Company company = null;
		Company tempCompany = null;
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Company.class);
		
		detachedCriteria.add(Restrictions.eq(Key.NAME, name));
		
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		
		for(Object o : list){
			tempCompany = (Company)o;
			if(tempCompany.getName().equals(name)){
				company = tempCompany;
				break;
			}
		}
		return company;
	}
	
	public static ArrayList<Company> getCompanysBySuperAdmin(SuperAdmin superAdmin) {
		ArrayList<Company> companys = new ArrayList<Company>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Company.class);
		detachedCriteria.add(Restrictions.eq(Key.SUPERADMIN, superAdmin));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			companys.add((Company) o);
		}
		return companys;
	}
}
