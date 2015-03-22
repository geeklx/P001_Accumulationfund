package com.accumulationfund.otheractivity.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.accumulationfund.otheractivity.MainActivity_logo8_location;
import com.accumulationfund.otheractivity.bean.Bean_Logo8;
import com.liangxiao.accumulationfund.R;

public class Adapter_Logo8 extends BaseAdapter {
	private List<Bean_Logo8> list;
	private Context mContext;
	private LayoutInflater mInflater;

	public Adapter_Logo8(Context mContext) {
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
	public Bean_Logo8 getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addItem(Bean_Logo8 bean) {
		list.add(bean);
	}

	public void setData(List<Bean_Logo8> list) {
		this.list = list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = mInflater.inflate(R.layout.af_logo8_listview_item, null,
					false);
			holder = new ViewHolder();
			holder.tv_name = (TextView) view.findViewById(R.id.tv_item_name);
			holder.tv_address = (TextView) view
					.findViewById(R.id.tv_item_address);
			holder.tv_num = (TextView) view.findViewById(R.id.tv_item_num);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		Bean_Logo8 bean = list.get(position);
		holder.tv_name.setText(bean.getName());
		holder.tv_address.setText(bean.getAddress());
		holder.tv_num.setText("当前人数：" + bean.getNum());
		// 点击
		view.findViewById(R.id.ll_item).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// Intent intentRoute = new Intent(mContext,
						// RouteActivity.class);
						// intentRoute.putExtra("Petrollat", s.getLat());//
						// 加油站的经度位置
						// intentRoute.putExtra("Petrollon", s.getLon());//
						// 加油站的纬度位置
						// intentRoute.putExtra("locationlat", getIntent()
						// .getDoubleExtra("loclat", 0));// 当前位置的经度
						// intentRoute.putExtra("locationlon", getIntent()
						// .getDoubleExtra("loclon", 0));// 当前位置的纬度
						// startActivity(intentRoute);
						// 传值跳转
						Intent intent = new Intent();
						Bundle bundle = new Bundle();
						bundle.putParcelable("Bean_Logo8", list.get(position));
						intent.putExtras(bundle);
						intent.putExtra("Accumulationfundlat", "1");// 公积金的经度位置
						intent.putExtra("Accumulationfundlon", "2");// 公积金的纬度位置
						intent.setClass(mContext,
								MainActivity_logo8_location.class);
						mContext.startActivity(intent);
					}
				});
		return view;
	}

	public class ViewHolder {
		private TextView tv_name;
		private TextView tv_address;
		private TextView tv_num;
	}
}
