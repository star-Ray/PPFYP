package model;

import java.util.Set;

import org.json.simple.JSONObject;

public class SuperAdmin {
	private long superAdminId;
	private String name;
	private String passwordSalt;
	private String passwordHash;
	
	private Set<Company> companys;
	
	public SuperAdmin(){}

	public SuperAdmin(String name, String passwordSalt, String passwordHash) {
		super();
		this.name = name;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
	}

	public long getSuperAdminId() {
		return superAdminId;
	}

	public void setSuperAdminId(long superAdminId) {
		this.superAdminId = superAdminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Set<Company> getCompanys() {
		return companys;
	}

	public void setCompanys(Set<Company> companys) {
		this.companys = companys;
	}
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		return returnJson;
	}
}
