package controller;

import model.Company;
import model.Officer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.CompanyDAO;
import persistence.OfficerDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class CompanyCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createCompany(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			String name = (String) inputJson.get(Key.NAME);
			String userName = (String) inputJson.get(Key.USERNAME);
			String password = (String) inputJson.get(Key.PASSWORD);
			
			String passwordSalt = Encrypt.nextSalt();
			String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
			
			Company company = new Company(name, userName, passwordSalt, passwordHash);
			CompanyDAO.addCompany(company);
			
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, company.toJson());
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//Get company by id
	public static JSONObject getCompanyById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long companyId = (long)inputJson.get(Key.COMPANYID);
			Company company = CompanyDAO.getCompanyById(companyId);
			if(company != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, company.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.COMPANYNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all company
	public static JSONObject getAllCompanys(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray companyJArr = new JSONArray();
			for(Company a: CompanyDAO.getAllCompanys()){
				companyJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, companyJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateCompany(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Company company = CompanyDAO.getCompanyById((long) inputJson.get(Key.COMPANYID));
			
			if(company != null){
				String name = (String) inputJson.get(Key.NAME);
				String userName = (String) inputJson.get(Key.USERNAME);
				
				company.setName(name);
				company.setUsername(userName);
				
				CompanyDAO.modifyCompany(company);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, company.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.COMPANYNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteCompany(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Company company = CompanyDAO.getCompanyById((long) inputJson.get(Key.COMPANYID));
			
			if(company != null){
				company.setObjStatus(Value.DELETED);
				CompanyDAO.modifyCompany(company);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, company.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.COMPANYNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//features
	public static JSONObject loginUser (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			String username = (String) inputJson.get(Key.USERNAME);
			Company company = CompanyDAO.getCompanyByUsername(username);
			if(company != null){
				String password = (String)inputJson.get(Key.PASSWORD);
				String passwordSalt = company.getPasswordSalt();
				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
				String checkHash = company.getPasswordHash();
				if(checkHash.equals(passwordHash)){
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, company.toJson());
				}else{
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.WRONGCOMPANYPASSWORD);
				}
			}else{
				Officer officer = OfficerDAO.getOfficerByUsername(username);
				if(officer != null){
					String password = (String)inputJson.get(Key.PASSWORD);
					String passwordSalt = officer.getPasswordSalt();
					String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
					String checkHash = officer.getPasswordHash();
					if(checkHash.equals(passwordHash)){
						returnJson.put(Key.STATUS, Value.SUCCESS);
						returnJson.put(Key.MESSAGE, officer.toJson());
					}else{
						returnJson.put(Key.STATUS, Value.FAIL);
						returnJson.put(Key.MESSAGE, Message.WRONGOFFICERPASSWORD);
					}
				}else{
					returnJson.put(Key.STATUS, Value.FAIL)  ;
					returnJson.put(Key.MESSAGE, Message.USERNOTEXIST);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
