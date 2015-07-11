package controller;

import model.Item;
import model.Task;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.ItemDAO;
import persistence.TaskDAO;
import system.Key;
import system.Message;
import system.Value;

public class ItemCtrl {
	
	/**
	 * CRUD
	 * */
	public static JSONObject createItem(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Task task = TaskDAO.getTaskById((long) inputJson.get(Key.TASKID));
			if(task != null){	
				String description = (String) inputJson.get(Key.DESCRIPTION);
				double weight = Double.valueOf((String) inputJson.get(Key.WEIGHT));
				String dimension = (String) inputJson.get(Key.DIMENSION);
				
				//String image = (String) inputJson.get(Key.IMAGE);
				//String nfcTag = (String) inputJson.get(Key.NFCTAG);
				//String barCode = (String) inputJson.get(Key.BARCODE);
				
				Item item = new Item(task, description, weight, dimension);
				ItemDAO.addItem(item);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, item.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TASKNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	//Get item by id
	public static JSONObject getItemById (JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long itemId = (long)inputJson.get(Key.ITEMID);
			Item item = ItemDAO.getItemById(itemId);
			if(item != null){
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, item.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.ITEMNOTEXIST);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	//Get all item
	public static JSONObject getAllItems(){
		JSONObject returnJson = new JSONObject();
		try{
			JSONArray itemJArr = new JSONArray();
			for(Item a: ItemDAO.getAllItems()){
				itemJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS)  ;
			returnJson.put(Key.MESSAGE, itemJArr);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject updateItem(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Item item = ItemDAO.getItemById((long) inputJson.get(Key.ITEMID));
			
			if(item != null){
				String description = (String) inputJson.get(Key.DESCRIPTION);
				double weight = Double.valueOf((String) inputJson.get(Key.WEIGHT));
				String dimension = (String) inputJson.get(Key.DIMENSION);
				String image = (String) inputJson.get(Key.IMAGE);
				String nfcTag = (String) inputJson.get(Key.NFCTAG);
				String barCode = (String) inputJson.get(Key.BARCODE);
				
				item.setDescription(description);
				item.setWeight(weight);
				item.setDimension(dimension);
				item.setImage(image);
				item.setNfcTag(nfcTag);
				item.setBarCode(barCode);
				
				ItemDAO.modifyItem(item);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, item.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.ITEMNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		
		return returnJson;
	}
	
	public static JSONObject deleteItem(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Item item = ItemDAO.getItemById((long) inputJson.get(Key.ITEMID));
			
			if(item != null){
				item.setObjStatus(Value.DELETED);
				ItemDAO.modifyItem(item);
				
				returnJson.put(Key.STATUS, Value.SUCCESS)  ;
				returnJson.put(Key.MESSAGE, item.toJson());
			}else{
				returnJson.put(Key.STATUS, Value.FAIL)  ;
				returnJson.put(Key.MESSAGE, Message.ITEMNOTEXIST);
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
