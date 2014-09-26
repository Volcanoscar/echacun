package com.exoo.android.core.idcard;

import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.exoo.android.navigationdrawerexample.R;

public class IdCardFragment extends Fragment {

	private IdCardService idcardservice = null;

	private EditText number;
	private Button search;
	private ListView listview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.idcard_planet, container,
				false);
		idcardservice = new IdCardService();

		number = (EditText) rootView.findViewById(R.id.idcard_number);
		search = (Button) rootView.findViewById(R.id.idcard_search);
		listview = (ListView) rootView.findViewById(R.id.idcard_listview);
		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String idcard_number = number.getText().toString();

//				List<String> data = mobileservice
//						.GetMobileData(mobile_number);
//				
//				ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,data);
//				 String url = getResources().getString(R.string.idcard_url)+"&idcard="+idcard_number;
				List<Map<String, String>> data = idcardservice.GetIdCardData(idcard_number);
					SimpleAdapter adapter = new SimpleAdapter(getActivity(),
							data, android.R.layout.simple_list_item_2,
							new String[] { "title", "info" }, new int[] {
									android.R.id.text1, android.R.id.text2 });
				listview.setAdapter(adapter);

			}
		});

		 

		return rootView;
	}

}
