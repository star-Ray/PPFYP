package fypproject.Entity;

import java.util.Date;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Officer {
    private int officerId, objStatus, companyId;
    private String name, username, passwordSALT, passwordHASH, remarks;
    private Date createDate;

    public Officer(int officerId, int objStatus, int companyId, String name, String username, String passwordSALT, String passwordHASH, String remarks, Date createDate) {
        this.officerId = officerId;
        this.objStatus = objStatus;
        this.companyId = companyId;
        this.name = name;
        this.username = username;
        this.passwordSALT = passwordSALT;
        this.passwordHASH = passwordHASH;
        this.remarks = remarks;
        this.createDate = createDate;
    }

    public int getOfficerId() {
        return officerId;
    }

    public void setOfficerId(int officerId) {
        this.officerId = officerId;
    }

    public int getObjStatus() {
        return objStatus;
    }

    public void setObjStatus(int objStatus) {
        this.objStatus = objStatus;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public String getPasswordSALT() {
        return passwordSALT;
    }

    public void setPasswordSALT(String passwordSALT) {
        this.passwordSALT = passwordSALT;
    }

    public String getPasswordHASH() {
        return passwordHASH;
    }

    public void setPasswordHASH(String passwordHASH) {
        this.passwordHASH = passwordHASH;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
