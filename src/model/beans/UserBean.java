package model.beans;

import java.io.Serializable;

public class UserBean implements Serializable {
	private int id;
	private String name;
	private String pass;
	private String role;
	public UserBean() {
		
	}
	public UserBean(int id, String name,String pass,String role) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
