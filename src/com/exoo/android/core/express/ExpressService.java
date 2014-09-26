package com.exoo.android.core.express;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.exoo.android.common.APIURL;
import com.exoo.android.common.Common;
import com.exoo.android.core.RetreiveWifiTask;
import com.exoo.android.navigationdrawerexample.R;

public class ExpressService {

	/**
	 * 通过订单号跟物流商代码查询改订单的物流信息
	 * 
	 * @param exp_id
	 * @param exp_com
	 * @return List<Map<String, Object>> 结构数据
	 */
	public List<Map<String, Object>> GetExpressData(String exp_id,
			String exp_com) {

		String url = new APIURL().getExpressURL(exp_id, exp_com);

		RetreiveWifiTask sa = new RetreiveWifiTask();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		try {
			String json = sa.execute(url).get().toString();
			Log.d("NAV", json);

			/**
			 * 这里需要分析服务器回传的json格式数据，
			 */
			List<ExpressData> expressDatas = new ArrayList<ExpressData>();
			JSONObject jsonObject = new JSONObject(json);

			String status = jsonObject.getString("status");
			String message = jsonObject.getString("message");
			if (status.equals("200")) {
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
					ExpressData expressData = new ExpressData();
					expressData.setRemark(jsonObject2.getString("remark"));
					expressData.setAcceptTime(jsonObject2
							.getString("acceptTime"));
					expressDatas.add(expressData);
				}
			} else if (status.equals("401")) {
				ExpressData expressData = new ExpressData();
				expressData.setRemark(Common.context.getResources().getString(
						R.string.api_36wu_authkey_error));
				expressData.setAcceptTime(Common.context.getResources()
						.getString(R.string.error_title));
				expressDatas.add(expressData);
			} else {
				ExpressData expressData = new ExpressData();

				expressData.setRemark(message);
				expressData.setAcceptTime(Common.context.getResources()
						.getString(R.string.error_title));
				expressDatas.add(expressData);
			} 
			for (int j = 0; j < expressDatas.size(); j++) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put(expressDatas.get(j).getRemark(), expressDatas.get(j)
						.getAcceptTime());
				map.put("title", expressDatas.get(j).getRemark());
				map.put("info", expressDatas.get(j).getAcceptTime());
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

	public List<ExpressCom> GetExpressCom() {
		String url = new APIURL().getExpressCOMURL();

		RetreiveWifiTask sa = new RetreiveWifiTask();
		List<ExpressCom> data = new ArrayList<ExpressCom>();
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
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);

					ExpressCom expresscom = new ExpressCom();
					expresscom.setID(jsonObject2.getString("com"));

					expresscom.setValue(jsonObject2.getString("name"));

					data.add(expresscom);

				}
			} else {
				return null;
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
