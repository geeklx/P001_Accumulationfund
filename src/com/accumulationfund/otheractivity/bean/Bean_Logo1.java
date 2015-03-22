package com.accumulationfund.otheractivity.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Bean_Logo1 implements Parcelable {
	private String name;
	private String time;
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(content);
		arg0.writeString(name);
	}

	public static final Parcelable.Creator<Bean_Logo1> CREATOR = new Creator<Bean_Logo1>() {

		@Override
		public Bean_Logo1[] newArray(int arg0) {
			return null;
		}

		@Override
		public Bean_Logo1 createFromParcel(Parcel arg0) {
			Bean_Logo1 bean = new Bean_Logo1();
			bean.content = arg0.readString();
			bean.name = arg0.readString();
			return bean;
		}
	};

}
