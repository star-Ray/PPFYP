package controller;

import java.util.Date;

import model.Company;
import model.Courier;
import model.Sender;
import model.Task;
import model.Officer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.CompanyDAO;
import persistence.CourierDAO;
import persistence.SenderDAO;
import persistence.TaskDAO;
import persistence.OfficerDAO;
import system.Config;
import system.Key;
import system.Message;
import system.Value;

public class TaskCtrl {

	/**
	 * CRUD
	 * */
	public static JSONObject createTask(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Officer officer = OfficerDAO.getOfficerById((long) inputJson.get(Key.OFFICERID));
			Courier courier = CourierDAO.getCourierById((long) inputJson.get(Key.COURIERID));
			Sender sender = SenderDAO.getSenderById((long) inputJson.get(Key.SENDERID));
			if (sender != null && courier != null && sender != null) {
				String receiverName = (String) inputJson.get(Key.RECEIVERNAME);
				String receiverContact = (String) inputJson.get(Key.RECEIVERCONTACT);
				
				Date planStartTime = Config.SDF.parse((String) inputJson.get(Key.PLANSTARTTIME));
				Date planEndTime = Config.SDF.parse((String) inputJson.get(Key.PLANENDTIME));
				Date actualStartTime = Config.SDF.parse((String) inputJson.get(Key.ACTUALSTARTTIME));
				Date actualEndTime = Config.SDF.parse((String) inputJson.get(Key.ACTUALENDTIME));

				String startAddress = (String) inputJson.get(Key.STARTADDRESS);
				double startLon = Double.valueOf((String) inputJson.get(Key.STARTLON));
				double startLat = Double.valueOf((String) inputJson.get(Key.STARTLAT));
				long startPostalCode = (long) inputJson.get(Key.STARTPOSTALCODE);
				String endAddress = (String) inputJson.get(Key.ENDADDRESS);
				double endLon = Double.valueOf((String) inputJson.get(Key.ENDLON));
				double endLat = Double.valueOf((String) inputJson.get(Key.ENDLAT));
				long endPostalCode = (long) inputJson.get(Key.ENDPOSTALCODE);

				int random = (int) (Math.random() * 9000) + 1000;
				String pin = String.valueOf(random);

				String taskStatus = "feed in a status for different situation";

				Task task = new Task(officer, courier, sender, receiverName, receiverContact,
						planStartTime, planEndTime, actualStartTime, actualEndTime,
						startAddress, startLon, startLat, startPostalCode,
						endAddress, endLon, endLat, endPostalCode, pin, taskStatus);

				TaskDAO.addTask(task);
				/*
				 * if(officer == null && courier == null && sender != null){
				 * taskStatus = Value.DRAFT; }else if(officer != null && sender
				 * != null && courier == null){ taskStatus = Value.APPROVE;
				 * }else{
				 * 
				 * }
				 */

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, task.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TASKNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// Get task by id
	public static JSONObject getTaskById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			long taskId = (long) inputJson.get(Key.TASKID);
			Task task = TaskDAO.getTaskById(taskId);
			if (task != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, task.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TASKNOTEXIST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all task
	public static JSONObject getAllTasks() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray taskJArr = new JSONArray();
			for (Task a : TaskDAO.getAllTasks()) {
				taskJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, taskJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateTask(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Task task = TaskDAO.getTaskById((long) inputJson.get(Key.TASKID));
			if (task != null) {
				String receiverName = (String) inputJson.get(Key.RECEIVERNAME);
				String receiverContact = (String) inputJson.get(Key.RECEIVERCONTACT);

				Date planStartTime = Config.SDF.parse((String) inputJson.get(Key.PLANSTARTTIME));
				Date planEndTime = Config.SDF.parse((String) inputJson.get(Key.PLANENDTIME));
				Date actualStartTime = Config.SDF.parse((String) inputJson.get(Key.ACTUALSTARTTIME));
				Date actualEndTime = Config.SDF.parse((String) inputJson.get(Key.ACTUALENDTIME));

				String startAddress = (String) inputJson.get(Key.STARTADDRESS);
				double startLon = Double.valueOf((String) inputJson.get(Key.STARTLON));
				double startLat = Double.valueOf((String) inputJson.get(Key.STARTLAT));
				long startPostalCode = (long) inputJson.get(Key.STARTPOSTALCODE);
				String endAddress = (String) inputJson.get(Key.ENDADDRESS);
				double endLon = Double.valueOf((String) inputJson.get(Key.ENDLON));
				double endLat = Double.valueOf((String) inputJson.get(Key.ENDLAT));
				long endPostalCode = (long) inputJson.get(Key.ENDPOSTALCODE);

				String taskStatus = (String) inputJson.get(Key.TASKSTATUS);

				task.setReceiverName(receiverName);
				task.setReceiverContact(receiverContact);
				task.setPlanStartTime(planStartTime);
				task.setPlanEndTime(planEndTime);
				task.setActualStartTime(actualStartTime);
				task.setActualEndTime(actualEndTime);
				
				task.setStartAddress(startAddress);
				task.setStartLon(startLon);
				task.setStartLat(startLat);
				task.setStartPostalCode(startPostalCode);
				task.setEndAddress(endAddress);
				task.setEndLon(endLon);
				task.setEndLat(endLat);
				task.setEndPostalCode(endPostalCode);
				
				task.setTaskStatus(taskStatus);

				TaskDAO.modifyTask(task);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, task.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TASKNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	public static JSONObject deleteTask(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Task task = TaskDAO.getTaskById((long) inputJson.get(Key.TASKID));

			if (task != null) {
				task.setObjStatus(Value.DELETED);
				TaskDAO.modifyTask(task);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, task.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TASKNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// features
	public static JSONObject getTasksByCompany(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Company company = CompanyDAO.getCompanyById((long) inputJson
					.get(Key.COMPANYID));
			if (company != null) {
				JSONArray taskJArr = new JSONArray();
				for (Task t : TaskDAO.getTasksByCompany(company)) {
					taskJArr.add(t.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, taskJArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.COMPANYNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
