package com.accumulationfund.otheractivity.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accumulationfund.otheractivity.MainActivity_logo1Detail;
import com.accumulationfund.otheractivity.bean.Bean_Logo1;
import com.liangxiao.accumulationfund.R;

public class Adapter_Logo1 extends BaseAdapter {
	private List<Bean_Logo1> list;
	@SuppressWarnings("unused")
	private Context mContext;
	private Activity activity;
	private LayoutInflater mInflater;

	public Adapter_Logo1(Context mContext, Activity activity) {
		this.mContext = mContext;
		mInflater = LayoutInflater.from(mContext);
		this.activity = activity;
	}

	@Override
	public int getCount() {
		if (list == null)
			return 0;
		return list.size();
	}

	@Override
	public Bean_Logo1 getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addItem(Bean_Logo1 bean) {
		list.add(bean);
	}

	public void setData(List<Bean_Logo1> list) {
		this.list = list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = mInflater.inflate(R.layout.af_logo1_listview_item, null,
					false);
			holder = new ViewHolder();
			holder.tv_name = (TextView) view.findViewById(R.id.tv_item_name);
			holder.tv_time = (TextView) view.findViewById(R.id.tv_item_time);
			holder.ll_click = (LinearLayout) view.findViewById(R.id.ll_click);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		Bean_Logo1 bean = list.get(position);
		holder.tv_name.setText(bean.getName());
		holder.tv_time.setText(bean.getTime());
		holder.ll_click.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(activity,
						MainActivity_logo1Detail.class);
				Bundle bundle = new Bundle();
				bundle.putParcelable("info", list.get(position));
				intent.putExtras(bundle);
				activity.startActivity(intent);
			}
		});

		return view;
	}

	public class ViewHolder {
		private TextView tv_name;
		private TextView tv_time;
		private LinearLayout ll_click;

	}
}
