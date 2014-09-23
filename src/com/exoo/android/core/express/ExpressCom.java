package com.exoo.android.core.express;

import java.util.List;

public class ExpressCom {

	private String ID = "";
	private String Value = "";

	public ExpressCom() {
		ID = "";
		Value = "";
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setValue(String value) {
		Value = value;
	}

	public ExpressCom(String _ID, String _Value) {
		ID = _ID;
		Value = _Value;
	}

	@Override
	public String toString() { // 为什么要重写toString()呢？因为适配器在显示数据的时候，如果传入适配器的对象不是字符串的情况下，直接就使用对象.toString()
		// TODO Auto-generated method stub
		return Value;
	}

	public String GetID() {
		return ID;
	}

	public String GetValue() {
		return Value;
	}
	
	
	
	
	
	
	

}
