package com.accumulationfund.otheractivity.logo9.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.accumulationfund.otheractivity.MainActivity_logo9_fragment1Detail_another;
import com.accumulationfund.otheractivity.MainActivity_logo9_fragment2Detail_another;
import com.accumulationfund.otheractivity.toast.myToast;
import com.liangxiao.accumulationfund.R;

public class MyFrament2 extends Fragment {
	private Handler handler;
	private Message msg;
	private RadioGroup rg_1;
	private RadioButton rb_1, rb_2;
	private RadioGroup rg_2;
	private RadioButton rb_3, rb_4, rb_5;
	@SuppressWarnings("unused")
	private String rb_1_content, rb_2_content;
	private Button btn_count;
	private EditText ed_logo9_one, ed_logo9_1, ed_logo9_3;
	private LinearLayout ll_onetwo;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.af_logo9_fragment2, container, false);
		handler = new myToast(getActivity());
		ed_logo9_one = (EditText) v.findViewById(R.id.ed_logo9_one);
		ed_logo9_1 = (EditText) v.findViewById(R.id.ed_logo9_1);
		ed_logo9_3 = (EditText) v.findViewById(R.id.ed_logo9_3);
		ll_onetwo = (LinearLayout) v.findViewById(R.id.ll_onetwo);
		// RadioButton部分
		rg_1 = (RadioGroup) v.findViewById(R.id.rg_1);
		rb_1 = (RadioButton) v.findViewById(R.id.rb_1);
		rb_2 = (RadioButton) v.findViewById(R.id.rb_2);
		rg_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int id = group.getCheckedRadioButtonId();
				if (id == R.id.rb_1) {
					rb_1_content = rb_1.getText().toString();
				}
				if (id == R.id.rb_2) {
					rb_1_content = rb_2.getText().toString();
				}
			}
		});
		rg_2 = (RadioGroup) v.findViewById(R.id.rg_2);
		rb_3 = (RadioButton) v.findViewById(R.id.rb_3);
		rb_4 = (RadioButton) v.findViewById(R.id.rb_4);
		rb_5 = (RadioButton) v.findViewById(R.id.rb_5);
		rg_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int id = group.getCheckedRadioButtonId();
				if (id == R.id.rb_3) {
					rb_2_content = rb_3.getText().toString();
					ll_onetwo.setVisibility(View.GONE);
				}
				if (id == R.id.rb_4) {
					rb_2_content = rb_4.getText().toString();
					ll_onetwo.setVisibility(View.GONE);
				}
				if (id == R.id.rb_5) {
					rb_2_content = rb_5.getText().toString();
					ll_onetwo.setVisibility(View.VISIBLE);
				}
			}
		});
		// 计算部分
		btn_count = (Button) v.findViewById(R.id.btn_count);
		btn_count.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				int id1 = rg_1.getCheckedRadioButtonId();
				int id2 = rg_2.getCheckedRadioButtonId();
				if (id1 == -1) {
					handMessage(20);
					return;
				}
				if (id2 == -1) {
					handMessage(21);
					return;
				}
				int isVisibel = ll_onetwo.getVisibility();
				if (isVisibel == View.VISIBLE) {
					if (ed_logo9_one.getText().toString().equals("")) {
						handMessage(222);
						return;
					}
				}
				if (ed_logo9_1.getText().toString().equals("")) {
					handMessage(211);
					return;
				}

				if (ed_logo9_3.getText().toString().equals("")) {
					handMessage(233);
					return;
				}
				int b = Integer.valueOf(ed_logo9_3.getText().toString());
				if (b < 1 || b > 30) {
					handMessage(943);
					return;
				}
				if (id1 == R.id.rb_1) {
					Intent intent = new Intent(getActivity(),
							MainActivity_logo9_fragment1Detail_another.class);
					intent.putExtra("a", ed_logo9_one.getText().toString());// 公积金贷款
					intent.putExtra("b", ed_logo9_1.getText().toString());// 贷款总额
					intent.putExtra("d", ed_logo9_3.getText().toString());// 按揭年数
					startActivity(intent);
				}
				if (id1 == R.id.rb_2) {
					Intent intent = new Intent(getActivity(),
							MainActivity_logo9_fragment2Detail_another.class);
					intent.putExtra("a", ed_logo9_one.getText().toString());// 公积金贷款
					intent.putExtra("b", ed_logo9_1.getText().toString());// 贷款总额
					intent.putExtra("d", ed_logo9_3.getText().toString());// 按揭年数
					startActivity(intent);
				}
			}
		});
		return v;
	}

	private void handMessage(int i) {
		msg = handler.obtainMessage();
		msg.arg1 = i;
		handler.sendMessage(msg);
	}
}
