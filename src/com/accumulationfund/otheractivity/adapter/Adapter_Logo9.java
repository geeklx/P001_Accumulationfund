package com.accumulationfund.otheractivity.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.accumulationfund.otheractivity.bean.Bean_Logo9;
import com.liangxiao.accumulationfund.R;

public class Adapter_Logo9 extends BaseAdapter {
	private List<Bean_Logo9> list;
	@SuppressWarnings("unused")
	private Context mContext;
	private LayoutInflater mInflater;

	public Adapter_Logo9(Context mContext) {
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
	public Bean_Logo9 getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addItem(Bean_Logo9 bean) {
		list.add(bean);
	}

	public void setData(List<Bean_Logo9> list) {
		this.list = list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = mInflater.inflate(R.layout.af_logo9_listview_item, null,
					false);
			holder = new ViewHolder();
			holder.tv_month = (TextView) view.findViewById(R.id.tv_month);
			holder.tv_money = (TextView) view.findViewById(R.id.tv_money);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		Bean_Logo9 bean = list.get(position);
		holder.tv_month.setText(bean.getMonth());
		holder.tv_money.setText(bean.getMoney());
		return view;
	}

	public class ViewHolder {
		private TextView tv_month;
		private TextView tv_money;

	}
}
