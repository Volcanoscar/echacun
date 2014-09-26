package com.exoo.android.core.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.exoo.android.common.APIURL;
import com.exoo.android.common.Common;
import com.exoo.android.core.RetreiveWifiTask;
import com.exoo.android.navigationdrawerexample.R;

public class MobileService {
	private Context context = null;
	
	public MobileService(Context con){
		this.context = con;
	}

	/**
	 * 通过手机号码查询手机号信心
	 * @param mobile_number 手机号
	 * @return List<Map<String, Object>> 结构数据
	 */
	public List<Map<String,String>> GetMobileData(String mobile_number) {
	 
		 
		String url = new APIURL().getMobileURL(mobile_number);
		
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
					 map =new HashMap<String, String>();
					  
					  map.put("title",jsonchild.getString("province"));
					  map.put("info", "省份");
					  data.add(map);
					 
					  map =new HashMap<String, String>();
					  map.put("title",jsonchild.getString("city"));
					  map.put("info","城市");
					  data.add(map);
					  map =new HashMap<String, String>();
					  
					  map.put("title",jsonchild.getString("areaCode"));
					  map.put("info","城市区号");
					  data.add(map);
					  map =new HashMap<String, String>();
					 
					  map.put("title",jsonchild.getString("postCode"));
					  map.put("info","城市邮编");
					  data.add(map);
					  
					  map =new HashMap<String, String>();
					  map.put("title",jsonchild.getString("corp"));
					  map.put("info","运营商");
					  data.add(map);
					  
					  map =new HashMap<String, String>();
					  map.put("title",jsonchild.getString("card"));
					  map.put("info","卡类型");
					  data.add(map);
					 
						 
					
				 
//					map.put("省份：",jsonchild.getString("province"));
//					map.put("城市：",jsonchild.getString("city"));
//					map.put("城市区号：",jsonchild.getString("areaCode"));
//					map.put("城市邮编：",jsonchild.getString("postCode"));
//					map.put("运营商：",jsonchild.getString("corp"));
//					map.put("卡类型：",jsonchild.getString("card"));
//					data.add(map);
				} else if (status.equals("401")) {
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
