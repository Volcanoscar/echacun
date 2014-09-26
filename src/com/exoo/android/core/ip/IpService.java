package com.exoo.android.core.ip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.util.Log;

import com.exoo.android.common.APIURL;
import com.exoo.android.common.Common;
import com.exoo.android.core.RetreiveWifiTask;
import com.exoo.android.navigationdrawerexample.R;

public class IpService {
 
 
	/**
	 * 通过ip
	 * @param mobile_number 手机号
	 * @return List<Map<String, Object>> 结构数据
	 */
	public List<Map<String,String>> GetIpData(String ip) {
	 
		 String url = new APIURL().getIPURL(ip);
		
		
		  List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		  RetreiveWifiTask sa = new RetreiveWifiTask();
		  Map<String,String> map = null;
			try {
				String json = sa.execute(url).get().toString();
				Log.d("NAV", json);

				/**
				 * 这里需要分析服务器回传的json格式数据，
				 */
				JSONObject jsonObject = new JSONObject(json);

				String status = jsonObject.getString("status");
				String message = jsonObject.getString("message");
				
				
				if (status.equals("200")) {
					JSONObject jsonchild = jsonObject.getJSONObject("data");
					
//					System.out.println("baselist"+baselist);
//					for(int i = 0;i<baselist.size();i++){
//						System.out.println("baselist.get(i)"+baselist.get(i));
//						for (String key : baselist.get(i).keySet()) {
//						 Map<String, String> map = new TreeMap<String, String>();
//						 System.out.println("key"+key);
//						 map.put("title",jsonchild.getString(key));
//						 map.put("info", baselist.get(i).get(key));
//						 data.add(map);
//						   
//					 }
//					} 		List<Map<String,String>> baselist = new ArrayList<Map<String,String>>();
					
					/*
					 * "ip": "192.168.1.1", "num_ip": 3232235777, "location": "局域网 对方和您在同一内部网",
					 * "version": "2014年3月22日数据"
					 */
					 map =new HashMap<String, String>();
					  
					  map.put("title",jsonchild.getString("ip"));
					  map.put("info", "IP");
					  data.add(map);
					 
					  map =new HashMap<String, String>();
					  map.put("title",jsonchild.getString("num_ip"));
					  map.put("info","十进制IP");
					  data.add(map);
					  map =new HashMap<String, String>();
					  
					  map.put("title",jsonchild.getString("location"));
					  map.put("info","地址");
					  data.add(map);
					  map =new HashMap<String, String>();
					 
					  map.put("title",jsonchild.getString("version"));
					  map.put("info","更新时间");
					  data.add(map);
					  
					 
					 
						 
					
				 
//					map.put("省份：",jsonchild.getString("province"));
//					map.put("城市：",jsonchild.getString("city"));
//					map.put("城市区号：",jsonchild.getString("areaCode"));
//					map.put("城市邮编：",jsonchild.getString("postCode"));
//					map.put("运营商：",jsonchild.getString("corp"));
//					map.put("卡类型：",jsonchild.getString("card"));
//					data.add(map);
				}else if (status.equals("401")) {
					 map = new HashMap<String, String>();
						map.put("title",Common.context.getResources().getString(
								R.string.api_36wu_authkey_error));
						map.put("info", Common.context.getResources()
								.getString(R.string.error_title));
						data.add(map);
				} else {
					 map = new HashMap<String, String>();
						map.put("title",message);
						map.put("info", Common.context.getResources()
								.getString(R.string.error_title));
						data.add(map);
				} 
				 

				// getActivity().setContentView(listView);

				// express_text.setText(Html.fromHtml(res));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return data;
	}
	
	
	 
}
