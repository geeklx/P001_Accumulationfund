package com.accumulationfund.otheractivity.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Bean_Logo8 implements Parcelable {
	private String name;
	private String address;
	private String num;
	private double latitude;// Î³¶È
	private double longitude;// ¾­¶È
	private int imgId;// Í¼Æ¬

	public Bean_Logo8() {

	}

	public Bean_Logo8(double latitude, double longitude, int imgId,
			String name, String address, String num) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.imgId = imgId;
		this.name = name;
		this.address = address;
		this.num = num;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(address);
		arg0.writeString(name);
		arg0.writeString(num);
	}

	public static final Parcelable.Creator<Bean_Logo8> CREATOR = new Creator<Bean_Logo8>() {

		@Override
		public Bean_Logo8[] newArray(int arg0) {
			return null;
		}

		@Override
		public Bean_Logo8 createFromParcel(Parcel arg0) {
			Bean_Logo8 bean = new Bean_Logo8();
			bean.name = arg0.readString();
			bean.address = arg0.readString();
			bean.num = arg0.readString();
			return bean;
		}
	};

}
