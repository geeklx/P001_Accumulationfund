package com.accumulationfund.otheractivity.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Bean_Logo9 implements Parcelable {
	private String month;
	private String money;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(month);
		arg0.writeString(money);
	}

	public static final Parcelable.Creator<Bean_Logo9> CREATOR = new Creator<Bean_Logo9>() {

		@Override
		public Bean_Logo9[] newArray(int arg0) {
			return null;
		}

		@Override
		public Bean_Logo9 createFromParcel(Parcel arg0) {
			Bean_Logo9 bean = new Bean_Logo9();
			bean.month = arg0.readString();
			bean.money = arg0.readString();
			return bean;
		}
	};

}
