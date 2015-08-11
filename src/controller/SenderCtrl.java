package controller;

import model.Sender;
import model.Company;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.SenderDAO;
import persistence.CompanyDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class SenderCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createSender(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Company company = CompanyDAO.getCompanyById((long) inputJson.get(Key.COMPANYID));
			if(company != null){
				
				String name = (String) inputJson.get(Key.NAME);
				String contactNo = (String) inputJson.get(Key.CONTACTNO);
				String userName = (String) inputJson.get(Key.USERNAME);
				String password = (String) inputJson.get(Key.PASSWORD);
				
				String passwordSalt = Encrypt.nextSalt();
				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
				
				Sender sender = new Sender(company, name, contactNo, userName, passwordSalt, passwordHash);
				SenderDAO.addSender(sender);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, sender.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.SENDERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//Get sender by id
	public static JSONObject getSenderById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long senderId = (long)inputJson.get(Key.SENDERID);
			Sender sender = SenderDAO.getSenderById(senderId);
			if(sender != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, sender.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.SENDERNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all sender
	public static JSONObject getAllSenders(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray senderJArr = new JSONArray();
			for(Sender a: SenderDAO.getAllSenders()){
				senderJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, senderJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateSender(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Sender sender = SenderDAO.getSenderById((long) inputJson.get(Key.SENDERID));
			
			if(sender != null){
				String name = (String) inputJson.get(Key.NAME);
				String contactNo = (String) inputJson.get(Key.CONTACTNO);
				String userName = (String) inputJson.get(Key.USERNAME);
				
				sender.setName(name);
				sender.setContactNo(contactNo);
				sender.setUsername(userName);
				
				SenderDAO.modifySender(sender);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, sender.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.SENDERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteSender(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Sender sender = SenderDAO.getSenderById((long) inputJson.get(Key.SENDERID));
			
			if(sender != null){
				sender.setObjStatus(Value.DELETED);
				SenderDAO.modifySender(sender);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, sender.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.SENDERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//features
	public static JSONObject getSendersByCompany(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Company company = CompanyDAO.getCompanyById((long) inputJson.get(Key.COMPANYID));
			if(company != null){
				JSONArray senderJArr = new JSONArray();
				for(Sender s: SenderDAO.getSendersByCompany(company)){
					senderJArr.add(s.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, senderJArr);
			}else{
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.COMPANYNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject changeSenderPassword(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Sender sender = SenderDAO.getSenderById((long) inputJson.get(Key.SENDERID));
			String password = (String) inputJson.get(Key.PASSWORD);
			String passwordSalt = Encrypt.nextSalt();
			String passwordHash = Encrypt.generateSaltedHash(password,passwordSalt);
			sender.setPasswordSalt(passwordSalt);
			sender.setPasswordHash(passwordHash);
			SenderDAO.modifySender(sender);
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, sender.toJson());
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
