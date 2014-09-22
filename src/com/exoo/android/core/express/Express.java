package com.exoo.android.core.express;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.android.navigationdrawerexample.R;
import com.exoo.android.core.RetreiveWifiTask;

public class Express extends Fragment {
	private Spinner spinner; // 下拉框
	private ArrayAdapter<String> adapter; // 下拉框的数据类型
	private EditText express_id; //订单号
	private Button btn_search;
	 private ListView listView; // 结果
	
	private String exp_com = "0"; //当前选择的  快递商

	public Express() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.express_planet, container,
				false);

		int i = getArguments().getInt("planet_number");
		String planet = getResources().getStringArray(R.array.planets_array)[i];
		
		express_id = (EditText) rootView.findViewById(R.id.express_id);
		btn_search = (Button) rootView.findViewById(R.id.btn_search);
		listView = (ListView)rootView.findViewById(R.id.listView1);

		spinner = (Spinner) rootView.findViewById(R.id.Spinner01);
		// 建立数据源
		String[] mItems = getResources().getStringArray(
				R.array.express_com_array);
		// 建立Adapter并且绑定数据源
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, mItems);
		// 绑定 Adapter到控件
		spinner.setAdapter(adapter);
		//express_id.setText(planet);

		spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				/* 将所选mySpinner 的值带入myTextView 中 */
				exp_com =  adapter.getItem(arg2);

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		btn_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String exp_id = express_id.getText().toString();
				 
			
				
				String url = "http://api.36wu.com/Express/GetExpressInfo?postid="+exp_id+"&output=json&authkey=8aad55d6a4a74540b79d7e511654e08f"; 
				
				RetreiveWifiTask sa = new RetreiveWifiTask();
				
				try {
					String json= sa.execute(url).get().toString();
					Log.d("NAV",json);
					
					 /** 
                     * 这里需要分析服务器回传的json格式数据， 
                     */ 
					List<ExpressData> expressDatas = new ArrayList<ExpressData>();
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("data"); 
                    for(int i=0;i<jsonArray.length();i++){ 
                        JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i); 
                        ExpressData expressData = new ExpressData(); 
                        expressData.setRemark(jsonObject2.getString("remark")); 
                        expressData.setAcceptTime(jsonObject2.getString("acceptTime")); 
                        expressDatas.add(expressData); 
                    } 
					
					 
                    List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
					
					
					
                    for (int j = 0; j < expressDatas.size(); j++) {
//						System.out.println("当前："+expressDatas.get(j).getRemark()+"==时间："+expressDatas.get(j).getAcceptTime());
                    	Map<String, Object> map = new HashMap<String, Object>();
                    	map.put(expressDatas.get(j).getRemark(),expressDatas.get(j).getAcceptTime());
						map.put("title", expressDatas.get(j).getRemark());
						map.put("info", expressDatas.get(j).getAcceptTime());
						data.add(map);
					}
                   
                    SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, android.R.layout.simple_list_item_2, new String[]{"title","info"}, new int[]{android.R.id.text1,android.R.id.text2});
                    listView.setAdapter(adapter);
                    //getActivity().setContentView(listView);
                    
                    
                    
					//express_text.setText(Html.fromHtml(res));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				
				
			}
		});

		return rootView;
	}

}
