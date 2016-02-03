package org.leafframework.data.dao.orm;

// Generated 2016-2-3 11:24:16 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Staff generated by hbm2java
 */
public class Staff implements java.io.Serializable {

	private int id;
	private String name;
	private String sex;
	private String nation;
	private String idcard;
	private Date birthday;
	private String address;
	private String birthplace;
	private String diploma;
	private String graduation;
	private String special;
	private String marriage;
	private String apartment;
	private String post;
	private Date createtime;
	private Date updatetime;

	public Staff() {
	}

	public Staff(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Staff(int id, String name, String sex, String nation, String idcard,
			Date birthday, String address, String birthplace, String diploma,
			String graduation, String special, String marriage,
			String apartment, String post, Date createtime, Date updatetime) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.nation = nation;
		this.idcard = idcard;
		this.birthday = birthday;
		this.address = address;
		this.birthplace = birthplace;
		this.diploma = diploma;
		this.graduation = graduation;
		this.special = special;
		this.marriage = marriage;
		this.apartment = apartment;
		this.post = post;
		this.createtime = createtime;
		this.updatetime = updatetime;
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

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getDiploma() {
		return this.diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public String getGraduation() {
		return this.graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public String getSpecial() {
		return this.special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getApartment() {
		return this.apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
