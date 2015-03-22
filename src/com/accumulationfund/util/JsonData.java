package com.accumulationfund.util;

import java.io.IOException;
import java.util.Hashtable;

import org.apache.http.client.HttpResponseException;
import org.json.JSONArray;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Application;

public class JsonData extends Application {
	public static final String SERVICE_NS = "http://tempuri.org/";
	public static final String SERVICE_URL = "http://211.142.200.66:8085/";
	public static final int pageSize = 10;// 加载的页数

	public static JsonData jsonData = null;

	public Hashtable<String, Object> memCacheRegion = new Hashtable<String, Object>();

	public static JsonData getApplication() {
		if (jsonData == null)
			jsonData = new JsonData();
		return jsonData;
	}

	/**
	 * 将对象保存到内存缓存中
	 * 
	 * @param key
	 * @param value
	 */
	public void setMemCache(String key, Object value) {
		memCacheRegion.put(key, value);
	}

	/**
	 * 从内存缓存中获取对象
	 * 
	 * @param key
	 * @return
	 */
	public Object getMemCache(String key) {
		return memCacheRegion.get(key);
	}

	public JSONArray NewMessage(String thePage) {
		String methodName = "topicByRing";
		SoapObject soap = new SoapObject(SERVICE_NS, methodName);

		soap.addProperty("thePage", thePage + "");
		return callinfo(methodName, soap);
	}

	/**
	 * @param 属于该圈子的话题列表
	 *            4) 方法：topicByRing(String ringId,String titleNum,String
	 *            totals,String thePage) 5) 参数说明 ringId 圈子ID titleNum 字数 totals
	 *            每次读取条数 thePage 页码 6) 返回结果说明参照 话题列表
	 */
	public JSONArray topicByRing(String ringId, String titleNum, String totals,
			String thePage) {
		String methodName = "topicByRing";
		SoapObject soap = new SoapObject(SERVICE_NS, methodName);
		soap.addProperty("ringId", ringId + "");
		soap.addProperty("titleNum", titleNum + "");
		soap.addProperty("totals", totals + "");
		soap.addProperty("thePage", thePage + "");
		return callinfo(methodName, soap);
	}

	/**
	 * 接口执行公共方法
	 **/
	public JSONArray callinfo(String methodName, SoapObject soap) {
		HttpTransportSE httpSe = new HttpTransportSE(SERVICE_URL);
		httpSe.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = soap;
		envelope.setOutputSoapObject(soap);
		try {
			httpSe.call(SERVICE_NS + methodName, envelope);

			if (envelope.getResponse() != null) {
				String result = envelope.getResponse().toString();
				// 获取返回的结果
				try {
					JSONArray json = new JSONArray(result);
					return json;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (HttpResponseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}
}
