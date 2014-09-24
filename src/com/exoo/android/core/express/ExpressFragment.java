package com.exoo.android.core.express;

import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.os.Bundle;
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

import com.exoo.android.core.DBHelper;
import com.exoo.android.navigationdrawerexample.R;

public class ExpressFragment extends Fragment {
	private Spinner spinner; // 下拉框
	private ArrayAdapter<ExpressCom> adapter; // 下拉框的数据类型
	private EditText express_id; // 订单号
	private Button btn_search;
	private ListView listView; // 结果

	private String exp_com = "0"; // 当前选择的 快递商

	public ExpressFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.express_planet, container,
				false);

		int i = getArguments().getInt("planet_number");
		String planet = getResources().getStringArray(R.array.planets_array)[i];

		express_id = (EditText) rootView.findViewById(R.id.idcard_number);
		btn_search = (Button) rootView.findViewById(R.id.btn_search);
		listView = (ListView) rootView.findViewById(R.id.listView1);

		spinner = (Spinner) rootView.findViewById(R.id.Spinner01);
		// 建立数据源
		String[] mItems = getResources().getStringArray(
				R.array.express_com_array);
		// 建立Adapter并且绑定数据源
		//adapter = new ArrayAdapter<String>(getActivity(),
				//android.R.layout.simple_spinner_item, mItems);
		List<ExpressCom> lst =   new DBHelper(getActivity().getApplicationContext()).getExpressCom();
		 
		adapter = new ArrayAdapter<ExpressCom>(getActivity(), android.R.layout.simple_spinner_dropdown_item, lst); 
		
		// 绑定 Adapter到控件
		spinner.setAdapter(adapter);
		// express_id.setText(planet);

		spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				/* 将所选mySpinner 的值带入myTextView 中 */
				
				exp_com = adapter.getItem(arg2).GetID();  
	              

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
				//?postid="+ exp_id + "&com="+exp_com+"&
				String url = getResources().getString(R.string.express_url)+"&postid="+exp_id+"&com="+exp_com;
				
				 List<Map<String, Object>> data = new ExpressService().GetExpressData(url);
				SimpleAdapter adapter = new SimpleAdapter(getActivity(),
						data, android.R.layout.simple_list_item_2,
						new String[] { "title", "info" }, new int[] {
								android.R.id.text1, android.R.id.text2 });
				listView.setAdapter(adapter);

			}
		});

		return rootView;
	}

}
