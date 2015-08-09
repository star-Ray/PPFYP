package fypproject.Entity;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Courier {
    private int courierId, objStatus;
    private String name, username, contactNo, realTimeLocation, remark, company;
    private Date createDate;

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String TAG = "arnono/Courier";

    public Courier(int courierId, int objStatus, String name, String username, String contactNo,
                   String realTimeLocation, String remark, String company, String strCreateDate) {
        Log.d(TAG, "Creating new courier.");
        this.courierId = courierId;
        this.objStatus = objStatus;
        this.name = name;
        this.username = username;
        this.contactNo = contactNo;
        this.realTimeLocation = realTimeLocation;
        this.remark = remark;
        this.company = company;
        try {
            this.createDate = new SimpleDateFormat(DATE_FORMAT).parse(strCreateDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public int getObjStatus() {
        return objStatus;
    }

    public void setObjStatus(int objStatus) {
        this.objStatus = objStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getRealTimeLocation() {
        return realTimeLocation;
    }

    public void setRealTimeLocation(String realTimeLocation) {
        this.realTimeLocation = realTimeLocation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}