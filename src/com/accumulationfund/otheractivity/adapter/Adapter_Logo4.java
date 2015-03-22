package com.accumulationfund.otheractivity.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.accumulationfund.otheractivity.bean.Bean_Logo4;
import com.liangxiao.accumulationfund.R;

public class Adapter_Logo4 extends BaseAdapter {
	// private List<Bean_Logo4> list;
	public static String[] nameList = new String[] { "贷款合同编号", "还款账号", "借款人姓名",
			"身份证号", "配偶姓名", "配偶身份证", "储蓄还款账户", "贷款年限", "贷款金额", "放款日期", "月还款额",
			"贷款年利率", "已还利息", "贷款余额", "还款状态", "已还本金", "已还期数", "当前逾期期数", "受托银行",
			"担保方式", "还款方式" };
	@SuppressWarnings("unused")
	private Context mContext;
	private LayoutInflater mInflater;
	private List<Bean_Logo4> list = new ArrayList<Bean_Logo4>();

	public Adapter_Logo4(Context mContext, List<Bean_Logo4> list) {
		this.mContext = mContext;
		mInflater = LayoutInflater.from(mContext);
		this.list = list;
	}

	@Override
	public int getCount() {
		// if (Bean_Logo4.name.length == null)
		// return 0;
		return nameList.length;
	}

	@Override
	public Object getItem(int position) {
		// return list.get(position);
		return nameList[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// public void addItem(Bean_Logo4 bean) {
	// list.add(bean);
	// }
	//
	// public void setData(List<Bean_Logo4> list) {
	// this.list = list;
	// }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = mInflater.inflate(R.layout.af_logo44_item, null, false);
			holder = new ViewHolder();
			holder.tv_logo4 = (TextView) view.findViewById(R.id.tv_logo4);
			holder.ed_logo4 = (TextView) view.findViewById(R.id.ed_logo4);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		Bean_Logo4 bean = list.get(0);
		holder.tv_logo4.setText(Bean_Logo4.name[position] + "：");
		// holder.ed_logo4.setText(bean.getNum());
		// for (int i = 0; i < Bean_Logo4.name.length; i++) {
		//
		// }
		if (position == 0) {
			holder.ed_logo4.setText(bean.getNum1());
		}
		if (position == 1) {
			holder.ed_logo4.setText(bean.getNum2());
		}
		if (position == 2) {
			holder.ed_logo4.setText(bean.getNum3());
		}
		if (position == 3) {
			holder.ed_logo4.setText(bean.getNum4());
		}
		if (position == 4) {
			holder.ed_logo4.setText(bean.getNum5());
		}
		if (position == 5) {
			holder.ed_logo4.setText(bean.getNum6());
		}
		if (position == 6) {
			holder.ed_logo4.setText(bean.getNum7());
		}
		if (position == 7) {
			holder.ed_logo4.setText(bean.getNum8());
		}
		if (position == 8) {
			holder.ed_logo4.setText(bean.getNum9());
		}
		if (position == 9) {
			holder.ed_logo4.setText(bean.getNum10());
		}
		if (position == 10) {
			holder.ed_logo4.setText(bean.getNum11());
		}
		if (position == 11) {
			holder.ed_logo4.setText(bean.getNum12());
		}
		if (position == 12) {
			holder.ed_logo4.setText(bean.getNum13());
		}
		if (position == 13) {
			holder.ed_logo4.setText(bean.getNum14());
		}
		if (position == 14) {
			holder.ed_logo4.setText(bean.getNum15());
		}
		if (position == 15) {
			holder.ed_logo4.setText(bean.getNum16());
		}
		if (position == 16) {
			holder.ed_logo4.setText(bean.getNum17());
		}
		if (position == 17) {
			holder.ed_logo4.setText(bean.getNum18());
		}
		if (position == 18) {
			holder.ed_logo4.setText(bean.getNum19());
		}
		if (position == 19) {
			holder.ed_logo4.setText(bean.getNum20());
		}
		if (position == 20) {
			holder.ed_logo4.setText(bean.getNum21());
		}
		return view;
	}

	public class ViewHolder {
		private TextView tv_logo4;
		private TextView ed_logo4;
	}
}
