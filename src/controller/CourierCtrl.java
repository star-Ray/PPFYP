package controller;

import model.Courier;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.CourierDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class CourierCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createCourier(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			String name = (String) inputJson.get(Key.NAME);
			String contactNo = (String) inputJson.get(Key.CONTACTNO);
			String realTimeLocation = (String) inputJson.get(Key.REALTIMELOCATION);
			
			Courier courier = new Courier(name, contactNo, realTimeLocation);
			CourierDAO.addCourier(courier);
			
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, courier.toJson());
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//Get courier by id
	public static JSONObject getCourierById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long courierId = (long)inputJson.get(Key.COURIERID);
			Courier courier = CourierDAO.getCourierById(courierId);
			if(courier != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, courier.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.COURIERNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all courier
	public static JSONObject getAllCouriers(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray courierJArr = new JSONArray();
			for(Courier a: CourierDAO.getAllCouriers()){
				courierJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, courierJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateCourier(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Courier courier = CourierDAO.getCourierById((long) inputJson.get(Key.COURIERID));
			
			if(courier != null){
				String name = (String) inputJson.get(Key.NAME);
				String contactNo = (String) inputJson.get(Key.CONTACTNO);
				String realTimeLocation = (String) inputJson.get(Key.REALTIMELOCATION);
				
				courier.setName(name);
				courier.setContactNo(contactNo);
				courier.setRealTimeLocation(realTimeLocation);
				
				CourierDAO.modifyCourier(courier);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, courier.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.COURIERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteCourier(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Courier courier = CourierDAO.getCourierById((long) inputJson.get(Key.COURIERID));
			
			if(courier != null){
				courier.setObjStatus(Value.DELETED);
				CourierDAO.modifyCourier(courier);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, courier.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.COURIERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//features
	public static JSONObject updateCourierLocation(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Courier courier = CourierDAO.getCourierById((long) inputJson.get(Key.COURIERID));
			
			if(courier != null){
				String realTimeLocation = (String) inputJson.get(Key.REALTIMELOCATION);
				courier.setRealTimeLocation(realTimeLocation);
				CourierDAO.modifyCourier(courier);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, courier.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.COURIERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
}
