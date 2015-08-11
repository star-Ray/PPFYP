package fypproject.Entity;

import java.util.Date;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Item {
    private int itemId, taskId, objStatus;
    private double weight;
    private String desc, dimen, image, nfcTag, barcode, remark;
    private Date createDate;

    public Item(int itemId, int taskId, int objStatus, double weight, String desc, String dimen, String image, String nfcTag, String barcode, String remark, Date createDate) {
        this.itemId = itemId;
        this.taskId = taskId;
        this.objStatus = objStatus;
        this.weight = weight;
        this.desc = desc;
        this.dimen = dimen;
        this.image = image;
        this.nfcTag = nfcTag;
        this.barcode = barcode;
        this.remark = remark;
        this.createDate = createDate;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getObjStatus() {
        return objStatus;
    }

    public void setObjStatus(int objStatus) {
        this.objStatus = objStatus;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDimen() {
        return dimen;
    }

    public void setDimen(String dimen) {
        this.dimen = dimen;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}