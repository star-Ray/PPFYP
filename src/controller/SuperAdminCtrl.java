package controller;

import model.SuperAdmin;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.SuperAdminDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class SuperAdminCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createSuperAdmin(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			String name = (String) inputJson.get(Key.NAME);
			String password = (String) inputJson.get(Key.PASSWORD);
			
			String passwordSalt = Encrypt.nextSalt();
			String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
			
			SuperAdmin superAdmin = new SuperAdmin(name, passwordSalt, passwordHash);
			SuperAdminDAO.addSuperAdmin(superAdmin);
			
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, superAdmin.toJson());
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//Get superAdmin by id
	public static JSONObject getSuperAdminById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long superAdminId = (long)inputJson.get(Key.SUPERADMINID);
			SuperAdmin superAdmin = SuperAdminDAO.getSuperAdminById(superAdminId);
			if(superAdmin != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, superAdmin.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.SUPERADMINNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateSuperAdmin(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			SuperAdmin superAdmin = SuperAdminDAO.getSuperAdminById((long) inputJson.get(Key.SUPERADMINID));
			
			if(superAdmin != null){
				String name = (String) inputJson.get(Key.NAME);
				String email = (String) inputJson.get(Key.EMAIL);
				String userName = (String) inputJson.get(Key.USERNAME);
				
				superAdmin.setName(name);
				
				SuperAdminDAO.modifySuperAdmin(superAdmin);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, superAdmin.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.SUPERADMINNOTEXIST);
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
