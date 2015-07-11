package controller;

import model.Officer;
import model.Company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.OfficerDAO;
import persistence.CompanyDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class OfficerCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createOfficer(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Company company = CompanyDAO.getCompanyById((long) inputJson.get(Key.COMPANYID));
			if(company != null){
				
				String name = (String) inputJson.get(Key.NAME);
				String userName = (String) inputJson.get(Key.USERNAME);
				String password = (String) inputJson.get(Key.PASSWORD);
				
				String passwordSalt = Encrypt.nextSalt();
				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
				
				Officer officer = new Officer(company, name, userName, passwordSalt, passwordHash);
				OfficerDAO.addOfficer(officer);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, officer.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.OFFICERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//Get officer by id
	public static JSONObject getOfficerById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long officerId = (long)inputJson.get(Key.OFFICERID);
			Officer officer = OfficerDAO.getOfficerById(officerId);
			if(officer != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, officer.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.OFFICERNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all officer
	public static JSONObject getAllOfficers(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray officerJArr = new JSONArray();
			for(Officer a: OfficerDAO.getAllOfficers()){
				officerJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, officerJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateOfficer(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Officer officer = OfficerDAO.getOfficerById((long) inputJson.get(Key.OFFICERID));
			
			if(officer != null){
				String name = (String) inputJson.get(Key.NAME);
				String userName = (String) inputJson.get(Key.USERNAME);
				
				officer.setName(name);
				officer.setUsername(userName);
				
				OfficerDAO.modifyOfficer(officer);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, officer.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.OFFICERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteOfficer(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Officer officer = OfficerDAO.getOfficerById((long) inputJson.get(Key.OFFICERID));
			
			if(officer != null){
				officer.setObjStatus(Value.DELETED);
				OfficerDAO.modifyOfficer(officer);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, officer.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.OFFICERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//features
	
}
