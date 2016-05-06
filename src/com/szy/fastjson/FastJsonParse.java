package com.szy.fastjson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import android.util.Log;

public class FastJsonParse {

	private static FastJsonParse mFastJsonParse = null;

	public static FastJsonParse getInstance() {

		if (null == mFastJsonParse) {
			mFastJsonParse = new FastJsonParse();
		}
		return mFastJsonParse;
	}

	/* fastjson 生成json数据 */
	public void generateJsonString() {
		Student student = new Student(0, "Aaron", 24);
		Log.i("zyt", JSON.toJSONString(student));
	}

	/* fastjson生成json数组 */
	public void generateJsonArray() {
		List<Student> students = new ArrayList<Student>();
		for (int i = 0; i < 5; i++) {
			Student stu = new Student(i, "Student" + i, 18 + i);
			students.add(stu);
		}
		Log.i("zyt", JSON.toJSONString(students));
	}

	/* gson解析数据:通常结合google的volley进行使用 */
	public void parseUseGson(String jsonUrl) {
		Gson gson = new Gson();
		String jsonData = getJsonStringData(jsonUrl);
		// JSONObject jsonObj = JSON.parseObject(jsonData);
		Student student = gson.fromJson(jsonData, Student.class);

		Log.i("zyt", student.getName() + " " + student.getAge() + " " + student.getId());
	}

	/* fastjson 解析函数 */
	public void fastParseFunc(String jsonUrl) {
		String jsonData = getJsonStringData(jsonUrl);
		JSONObject jsonObj = JSON.parseObject(jsonData);
		JSONArray jsonArray = jsonObj.getJSONArray("test");
		List<Student> students = JSON.parseArray(jsonArray.toJSONString(), Student.class);

		Log.i("zyt", students.get(0).getName() + " " + students.get(0).getId() + " " + students.get(0).getAge());
		Log.i("zyt", students.get(4).getName() + " " + students.get(4).getId() + " " + students.get(4).getAge());
	}

	/* http访问 获取输入流 */
	public String getJsonStringData(String urlAddress) {

		String json = null;
		URL url;
		try {
			url = new URL(urlAddress);
			HttpURLConnection con = null;
			try {
				con = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con.setConnectTimeout(5 * 1000);// 单位是毫秒，设置超时时间为5秒
			// HttpURLConnection是通过HTTP协议请求path路径的，所以需要设置请求方式,可以不设置，因为默认为GET
			try {
				con.setRequestMethod("GET");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 判断请求码是否是200码，否则失败
			try {
				if (con.getResponseCode() == 200) {
					InputStream input = con.getInputStream();// 获取输入流
					byte[] data = readStream(input);// 把输入流转换为字符数组
					json = new String(data); // 把字符数组转换成字符串
					// Log.i("zyt", json);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 把输入流转换成字符数组
	 * 
	 * @param inputStream
	 *            输入流
	 * @return 字符数组
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream input) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = input.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		input.close();

		return bos.toByteArray();
	}
}
