package com.exoo.android.core;

import android.os.AsyncTask;

public class RetreiveWifiTask extends AsyncTask {

	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		  //HTTPServiceHelper hsh = new HTTPServiceHelper();
	        
	       // hsh.HttpService();
		System.out.println(params[0].toString());
		return new CallWebService().getRemoteInfo(params[0].toString());
		//return null;
	}
}


