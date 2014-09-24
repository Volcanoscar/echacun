package com.exoo.android.core.idcard;

public class IdCardData {

	/*
	 * "idcard": "21030319630118474X", "sex": "女", "birthday": "1963年1月18日",
	 * "address": "辽宁省鞍山市铁西区"
	 */
	private String idcard;
	private String sex;
	private String birthday;
	private String address;

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
