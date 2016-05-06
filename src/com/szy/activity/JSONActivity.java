package com.szy.activity;

import java.util.List;
import java.util.Map;

import com.szy.fastjson.FastJsonParse;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JSONActivity extends Activity {
	private Button button1;
	private Button button2;
	private Button button3;
	private String URL_JSONARRAY = "http://192.168.76.2:8080/Music/aa.json";
	private String URL_JSONOBJECT = "http://192.168.76.2:8080/Music/bb.json";
	private String URL_JSON = "http://192.168.76.2:8080/Music/cc.json";
	private String URL_JSONARRAY_ALI = "http://192.168.76.2:8080/Music/alii.json";
	private String URL_JSONARRAY_ALII = "http://192.168.76.2:8080/Music/aliii.json";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
		setListeners();// Android自带解析json数据

		// startVolleyParse(); //volley解析json数据
		startFastJsonParse();
	}

	public void startFastJsonParse() {
		// Student student = new Student(0, "Aaron", 24);
		// System.out.println((student));
		// Log.i("zyt", student.toString());
		// FastJsonParse.getInstance().generateJsonString();
		// FastJsonParse.getInstance().generateJsonArray();
		// FastJsonParse.getInstance().fastParseFunc(URL_JSONARRAY_ALI);//
		// fastjson 解析数据
		FastJsonParse.getInstance().parseUseGson(URL_JSONARRAY_ALII);
	}

	/* volley解析json */
	public void startVolleyParse() {
		// VolleyParse.getInstance().parseJsonVisaGeny(URL_JSONOBJECT);
		VolleyParse.getInstance().parseJsonArray(URL_JSONARRAY);
	}

	private void findViews() {
		button1 = (Button) this.findViewById(R.id.button1);
		button2 = (Button) this.findViewById(R.id.button2);
		button3 = (Button) this.findViewById(R.id.button3);
	}

	private void setListeners() {
		button1.setOnClickListener(onClickListener);
		button2.setOnClickListener(onClickListener);
		button3.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.button1:
				try {
					// Android 原生解析json方式
					showResult(JSONutil.getJSONArray(URL_JSONARRAY));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case R.id.button2:
				try {
					showResult(JSONutil.getJSONObject(URL_JSONOBJECT));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case R.id.button3:
				try {
					showResultNew(JSONutil.getJSON(URL_JSON));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	};

	private void showResult(List<Map<String, String>> list) {
		StringBuffer sb = new StringBuffer();
		try {
			for (Map<String, String> map : list) {
				String id = map.get("id");
				String name = map.get("name");
				sb.append(id).append("--").append(name);
			}
			new AlertDialog.Builder(this).setTitle("json").setMessage(sb.toString()).setPositiveButton("ok", null)
					.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showResultNew(List<Map<String, String>> list) {
		StringBuffer sb = new StringBuffer();
		try {
			for (Map<String, String> map : list) {
				String quest = map.get("question");
				String answer = map.get("answer");
				sb.append(quest).append("--").append(answer);
			}
			new AlertDialog.Builder(this).setTitle("json").setMessage(sb.toString()).setPositiveButton("ok", null)
					.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}