package org.leafframework.data.dao.orm;

// Generated 2016-2-3 11:24:16 by Hibernate Tools 3.4.0.CR1

/**
 * Apartmentmgr generated by hbm2java
 */
public class Apartmentmgr implements java.io.Serializable {

	private int id;
	private String name;
	private String phone;
	private String description;

	public Apartmentmgr() {
	}

	public Apartmentmgr(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Apartmentmgr(int id, String name, String phone, String description) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.description = description;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
