package com.exoo.android.core.lllegal;

public class LllegalData {

	/*
	 * "ip": "192.168.1.1", "num_ip": 3232235777, "location": "局域网 对方和您在同一内部网",
	 * "version": "2014年3月22日数据"
	 */
	private String ip;
	private String num_ip;
	private String location;
	private String version;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNum_ip() {
		return num_ip;
	}

	public void setNum_ip(String num_ip) {
		this.num_ip = num_ip;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
