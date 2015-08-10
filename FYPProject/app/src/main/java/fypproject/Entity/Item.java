package fypproject.Entity;

import java.util.Date;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Item{
    private int ID, taskID, objStatus;
    private double weight;
    private String desc, dimen, image, nfcTag, barcode, remark;
    private Date createDate;

    public Item(int ID, int taskID, int objStatus, double weight, String desc, String dimen, String image,
                String nfcTag, String barcode, String remarks, Date createDate) {
        this.ID = ID;
        this.taskID = taskID;
        this.objStatus = objStatus;
        this.weight = weight;
        this.desc = desc;
        this.dimen = dimen;
        this.image = image;
        this.nfcTag = nfcTag;
        this.barcode = barcode;
        this.remark = remarks;
        this.createDate = createDate;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
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

    public String getRemarks() {
        return remark;
    }

    public void setRemarks(String remarks) {
        this.remark = remarks;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
