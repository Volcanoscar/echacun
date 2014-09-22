package com.exoo.android.core;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
public class CallWebService {

	
	/**
	 * 手机号段归属地查询
	 * 
	 * @param phoneSec 手机号段
	 */
	public String getRemoteInfo(String url) {
		  HttpClient client = new DefaultHttpClient();  
	        StringBuilder builder = new StringBuilder();  
	        JSONArray jsonArray = null;  
	        HttpGet get = new HttpGet(url);  
	        try {  
	            HttpResponse response = client.execute(get);  
	            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));  
	            for (String s = reader.readLine(); s != null; s = reader.readLine()) {  
	                builder.append(s);  
	            }  
	            
	            
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	       
	            return builder.toString();  
	        
	}
	
}
