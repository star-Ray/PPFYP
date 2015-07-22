package model;

import java.util.Date;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

public class Item {
	private long itemId;
	private String description;
	private double weight;
	private String dimension;
	private String image;
	private String nfcTag;
	private String barCode;

	private long objStatus;
	private Date createDate;
	private String remark;

	private Task task;

	public Item() {
	}

	public Item(Task task, String description, double weight, String dimension) {
		super();
		this.setTask(task);
		this.description = description;
		this.setWeight(weight);
		this.setDimension(dimension);
		this.setImage("");
		this.setNfcTag("");
		this.setBarCode("");
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNfcTag() {
		return nfcTag;
	}

	public void setNfcTag(String nfcTag) {
		this.nfcTag = nfcTag;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public long getObjStatus() {
		return objStatus;
	}

	public void setObjStatus(long objStatus) {
		this.objStatus = objStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.ITEMID, this.itemId);
		returnJson.put(Key.DESCRIPTION, this.description);
		returnJson.put(Key.WEIGHT, this.weight);
		returnJson.put(Key.DIMENSION, this.dimension);
		returnJson.put(Key.IMAGE, this.image);
		returnJson.put(Key.NFCTAG, this.nfcTag);
		returnJson.put(Key.BARCODE, this.barCode);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		returnJson.put(Key.TASK, this.task.toJson());

		return returnJson;
	}

}
