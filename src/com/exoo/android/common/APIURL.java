package com.exoo.android.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.exoo.android.core.RetreiveWifiTask;
import com.exoo.android.navigationdrawerexample.R;

import android.content.Context;
import android.content.res.Resources.NotFoundException;

public class APIURL {
	 
	
	private String OUTPUT = "json";
	private String AUTHKEY = "";
	

	public APIURL() {
		RetreiveWifiTask sa = new RetreiveWifiTask();
			try {
				AUTHKEY = sa.execute(Common.context.getResources().getString(R.string.authkey_url)).get().toString();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}

	/**
	 * 获取查询物流详情的URL 
	 * @param exp_id 物流号
	 * @param exp_com  物流公司代号
	 * @return 可查询的URL
	 */
	public String getExpressURL(String exp_id, String exp_com) {
//		context.getResources().getString(R.string.express_url)+"&postid="+exp_id+"&com="+exp_com;
		//output=json&authkey=fa8764c43b9f4f83aa65f6dcebe23683
		String baseurl = Common.context.getResources().getString(R.string.express_url);
						 
		String url = baseurl+"?output="+OUTPUT+"&authkey="+AUTHKEY+"&postid="+exp_id+"&com="+exp_com;
		return url;
	}
	
	/**
	 * @return 可查询的URL
	 */
	public String getExpressCOMURL() {
//		context.getResources().getString(R.string.express_url)+"&postid="+exp_id+"&com="+exp_com;
		//output=json&authkey=fa8764c43b9f4f83aa65f6dcebe23683
		String baseurl = Common.context.getResources().getString(R.string.express_com_url);
						 
		String url = baseurl+"?output="+OUTPUT+"&authkey="+AUTHKEY;
		return url;
	}
	
	
	 
	/**
	 * 获取查询IDCARD的URL
	 * @param idcardnumber 身份证号码
	 * @return
	 */
	public String getIdCardURL(String idcardnumber) {
//		context.getResources().getString(R.string.express_url)+"&postid="+exp_id+"&com="+exp_com;
		//output=json&authkey=fa8764c43b9f4f83aa65f6dcebe23683
		String baseurl = Common.context.getResources().getString(R.string.idcard_url);
						 
		String url = baseurl+"?output="+OUTPUT+"&authkey="+AUTHKEY+"&idcard="+idcardnumber;
		return url;
	}
	
	
	/**
	 * 获取查询phonenumber的URL
	 * @param idcardnumber 身份证号码
	 * @return
	 */
	public String getMobileURL(String phonenumber) {
//		context.getResources().getString(R.string.express_url)+"&postid="+exp_id+"&com="+exp_com;
		//output=json&authkey=fa8764c43b9f4f83aa65f6dcebe23683
		String baseurl = Common.context.getResources().getString(R.string.mobile_url);
						 
		String url = baseurl+"?output="+OUTPUT+"&authkey="+AUTHKEY+"&mobile="+phonenumber;
		return url;
	}
	
	/**
	 * 获取查询phonenumber的URL
	 * @param idcardnumber 身份证号码
	 * @return
	 */
	public String getIPURL(String ip) {
//		context.getResources().getString(R.string.express_url)+"&postid="+exp_id+"&com="+exp_com;
		//output=json&authkey=fa8764c43b9f4f83aa65f6dcebe23683
		String baseurl = Common.context.getResources().getString(R.string.ip_url);
						 
		String url = baseurl+"?output="+OUTPUT+"&authkey="+AUTHKEY+"&ip="+ip;
		return url;
	}

}
