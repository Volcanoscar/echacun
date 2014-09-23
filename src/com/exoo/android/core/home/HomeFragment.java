package com.exoo.android.core.home;

import java.util.List;
import java.util.Map;

import com.exoo.android.core.DBHelper;
import com.exoo.android.core.express.ExpressCom;
import com.exoo.android.core.express.ExpressService;
import com.exoo.android.navigationdrawerexample.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class HomeFragment  extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.home_planet, container,
				false);

	 
	 

		return rootView;
	}


}
