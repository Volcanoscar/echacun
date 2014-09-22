package com.exoo.android.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class HTTPServiceHelper {
	


	public void HttpService(){
		String result = "";
		//首先使用NameValuePair封装将要查询的年数和关键字绑定
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("year","1980"));
		 InputStream is = null;
		//使用HttpPost封装整个SQL语句
		//使用HttpClient发送HttpPost对象
		try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("https://p2p.fuyingdai.com/json/calculator?amount=1000&loan_term=12&repayment_method=1&interest_rate=0.12");
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost); 
		        HttpEntity entity = response.getEntity();
		       is = entity.getContent();
		}catch(Exception e){
		        Log.e("log_tag", "Error in http connection "+e.toString());
		        
		}
		//将HttpEntity转化为String
		try{
			
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		        StringBuilder sb = new StringBuilder();
		        String line = null;
		        while ((line = reader.readLine()) != null) {
		                sb.append(line + "/n");
		        }
		        is.close();
		 
		        result=sb.toString();
		        Log.d("resule数据：",result);
		}catch(Exception e){
		        Log.e("log_tag", "Error converting result "+e.toString());
		}
		 
		//将String通过JSONArray解析成最终结果
		try{
		        JSONArray jArray = new JSONArray(result);
		        for(int i=0;i<jArray.length();i++){
		                JSONObject json_data = jArray.getJSONObject(i);
		                Log.i("log_tag","id: "+json_data.getInt("id")+
		                        ", name: "+json_data.getString("name")+
		                        ", sex: "+json_data.getInt("sex")+
		                        ", birthyear: "+json_data.getInt("birthyear")
		                );
		        }
		
		}catch(JSONException e){
		        Log.e("log_tag", "Error parsing data "+e.toString());
		}
		
	}

}