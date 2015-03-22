package com.accumulationfund.otheractivity.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.accumulationfund.otheractivity.bean.Bean_Logo6;
import com.liangxiao.accumulationfund.R;

public class Adapter_Logo6 extends BaseAdapter {
	private List<Bean_Logo6> list;
	@SuppressWarnings("unused")
	private Context mContext;
	private LayoutInflater mInflater;

	public Adapter_Logo6(Context mContext) {
		this.mContext = mContext;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		if (list == null)
			return 0;
		return list.size();
	}

	@Override
	public Bean_Logo6 getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addItem(Bean_Logo6 bean) {
		list.add(bean);
	}

	public void setData(List<Bean_Logo6> list) {
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = mInflater.inflate(R.layout.af_logo6_item2, null, false);
			holder = new ViewHolder();
			holder.tv_data1 = (TextView) view.findViewById(R.id.tv_data1);
			holder.tv_data2 = (TextView) view.findViewById(R.id.tv_data2);
			holder.tv_data3 = (TextView) view.findViewById(R.id.tv_data3);
			holder.tv_data4 = (TextView) view.findViewById(R.id.tv_data4);
			holder.tv_data5 = (TextView) view.findViewById(R.id.tv_data5);
			holder.tv_data6 = (TextView) view.findViewById(R.id.tv_data6);
			holder.tv_data7 = (TextView) view.findViewById(R.id.tv_data7);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		Bean_Logo6 bean = list.get(position);
		holder.tv_data1.setText(bean.getData1());
		holder.tv_data2.setText(bean.getData2());
		holder.tv_data3.setText(bean.getData3());
		holder.tv_data4.setText(bean.getData4());
		holder.tv_data5.setText(bean.getData5());
		holder.tv_data6.setText(bean.getData6());
		holder.tv_data7.setText(bean.getData7());

		return view;
	}

	public class ViewHolder {
		private TextView tv_data1;
		private TextView tv_data2;
		private TextView tv_data3;
		private TextView tv_data4;
		private TextView tv_data5;
		private TextView tv_data6;
		private TextView tv_data7;
	}
}
