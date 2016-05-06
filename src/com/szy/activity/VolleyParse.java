package com.szy.activity;

import org.json.JSONArray;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import android.util.Log;

public class VolleyParse {

	private static VolleyParse mVolleyParse = null;
	private VolleyTool mVolleyTool;
	private RequestQueue mReQueue;

	public static VolleyParse getInstance() {

		if (null == mVolleyParse) {
			mVolleyParse = new VolleyParse();
		}
		return mVolleyParse;
	}
	
	/*Volley解析json*/
	public VolleyParse() {
		mVolleyTool = VolleyTool.getInstance();
		mReQueue = mVolleyTool.getRequestQueue();
	}

	public void parseJsonVisaGeny(String jsonUrl) {
		mReQueue.cancelAll("program");
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, jsonUrl, null,
				new Response.Listener<org.json.JSONObject>() {

					@Override
					public void onResponse(org.json.JSONObject arg0) {
						// TODO Auto-generated method stub
						Log.i("zyt", "playurl-json object-arg0" + arg0);

					}
				}, errorListener);
		jsonObjectRequest.setTag("program");// 设置tag,cancelAll的时候使用
		mReQueue.add(jsonObjectRequest);

	}
	
	public void parseJsonArray(String jsonUrl){
		
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(jsonUrl, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray arg0) {
				// TODO Auto-generated method stub
				Log.i("zyt", "playurl-json object-arg0" + arg0);
			}
		}, errorListener);
		mReQueue.add(jsonArrayRequest);
	}
	
	private Response.ErrorListener errorListener = new Response.ErrorListener() {
		@Override
		public void onErrorResponse(VolleyError arg0) {
			// TODO Auto-generated method stub
			Log.i("zyt", "PlayVideo=error：" + arg0);
		}
	};
}
