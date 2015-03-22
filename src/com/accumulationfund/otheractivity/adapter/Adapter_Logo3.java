package com.accumulationfund.otheractivity.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.accumulationfund.otheractivity.bean.Bean_Logo3;
import com.liangxiao.accumulationfund.R;

public class Adapter_Logo3 extends BaseAdapter {
	private List<Bean_Logo3> list;
	@SuppressWarnings("unused")
	private Context mContext;
	private LayoutInflater mInflater;

	public Adapter_Logo3(Context mContext) {
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
	public Bean_Logo3 getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addItem(Bean_Logo3 bean) {
		list.add(bean);
	}

	public void setData(List<Bean_Logo3> list) {
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = mInflater.inflate(R.layout.af_logo3_listview_item, null,
					false);
			holder = new ViewHolder();
			holder.tv_name = (TextView) view.findViewById(R.id.tv_item_name);
			holder.tv_money = (TextView) view.findViewById(R.id.tv_item_money);
			holder.tv_time = (TextView) view.findViewById(R.id.tv_item_time);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		Bean_Logo3 bean = list.get(position);
		holder.tv_name.setText(bean.getName());
		holder.tv_money.setText("¥" + bean.getMoney());
		holder.tv_time.setText(bean.getTime());
		return view;
	}

	public class ViewHolder {
		private TextView tv_name;
		private TextView tv_money;
		private TextView tv_time;
	}
}
