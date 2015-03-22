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
	public static final int pageSize = 10;// ���ص�ҳ��

	public static JsonData jsonData = null;

	public Hashtable<String, Object> memCacheRegion = new Hashtable<String, Object>();

	public static JsonData getApplication() {
		if (jsonData == null)
			jsonData = new JsonData();
		return jsonData;
	}

	/**
	 * �����󱣴浽�ڴ滺����
	 * 
	 * @param key
	 * @param value
	 */
	public void setMemCache(String key, Object value) {
		memCacheRegion.put(key, value);
	}

	/**
	 * ���ڴ滺���л�ȡ����
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
	 * @param ���ڸ�Ȧ�ӵĻ����б�
	 *            4) ������topicByRing(String ringId,String titleNum,String
	 *            totals,String thePage) 5) ����˵�� ringId Ȧ��ID titleNum ���� totals
	 *            ÿ�ζ�ȡ���� thePage ҳ�� 6) ���ؽ��˵������ �����б�
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
	 * �ӿ�ִ�й�������
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
				// ��ȡ���صĽ��
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
