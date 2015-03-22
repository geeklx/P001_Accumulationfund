package com.accumulationfund.otheractivity.logo9.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.TextView;

import com.accumulationfund.otheractivity.MainActivity_logo9_fragment1Detail;
import com.accumulationfund.otheractivity.MainActivity_logo9_fragment2Detail;
import com.accumulationfund.otheractivity.toast.myToast;
import com.accumulationfund.util.JsonData;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liangxiao.accumulationfund.R;

public class MyFrament1 extends Fragment implements TextWatcher {
	private Handler handler;
	private Message msg;
	private RadioGroup rg_1;
	private RadioButton rb_1, rb_2;
	private RadioGroup rg_2;
	private RadioButton rb_3, rb_4, rb_5;
	@SuppressWarnings("unused")
	private String rb_1_content, rb_2_content;
	private Button btn_count;
	private EditText ed_logo9_one, ed_logo9_1, ed_logo9_2, ed_logo9_3;
	private LinearLayout ll_onetwo;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.af_logo9_fragment1, container, false);
		// myTextView = (TextView) v.findViewById(R.id.myTextView);
		// myTextView.setOnClickListener(listener);
		handler = new myToast(getActivity());
		ed_logo9_one = (EditText) v.findViewById(R.id.ed_logo9_one);
		ed_logo9_1 = (EditText) v.findViewById(R.id.ed_logo9_1);
		ed_logo9_2 = (EditText) v.findViewById(R.id.ed_logo9_2);
		ed_logo9_3 = (EditText) v.findViewById(R.id.ed_logo9_3);
		tv_watcher = (TextView) v.findViewById(R.id.tv_watcher);
		ed_logo9_3.addTextChangedListener(this);
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
					handMessage(22);
					return;
				}
				if (ed_logo9_2.getText().toString().equals("")) {
					handMessage(23);
					return;
				}
				int a = Integer.valueOf(ed_logo9_2.getText().toString());
				if (a < 1 || a > 100) {
					handMessage(942);
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
							MainActivity_logo9_fragment1Detail.class);
					intent.putExtra("a", ed_logo9_one.getText().toString());// 公积金贷款
					intent.putExtra("b", ed_logo9_1.getText().toString());// 房屋总价
					intent.putExtra("c", ed_logo9_2.getText().toString());// 按揭成数
					intent.putExtra("d", ed_logo9_3.getText().toString());// 按揭年数
					startActivity(intent);
				}
				if (id1 == R.id.rb_2) {
					Intent intent = new Intent(getActivity(),
							MainActivity_logo9_fragment2Detail.class);
					intent.putExtra("a", ed_logo9_one.getText().toString());// 公积金贷款
					intent.putExtra("b", ed_logo9_1.getText().toString());// 房屋总价
					intent.putExtra("c", ed_logo9_2.getText().toString());// 按揭成数
					intent.putExtra("d", ed_logo9_3.getText().toString());// 按揭年数
					startActivity(intent);
				}
			}
		});
		return v;
	}

	// private OnClickListener listener = new OnClickListener() {
	// public void onClick(View v) {
	// switch (v.getId()) {
	// case R.id.myTextView:
	// Toast.makeText(getActivity(), "界面一", Toast.LENGTH_SHORT).show();
	// break;
	// }
	// }
	// };
	private void handMessage(int i) {
		msg = handler.obtainMessage();
		msg.arg1 = i;
		handler.sendMessage(msg);
	}

	// http://192.168.15.86:8002/WebService.asmx/dknll?dknx=1
	// 获取json字符串
	public void getJsonVolley(String s) {
		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		String JSONDateUrl = JsonData.SERVICE_URL
				+ "WebService.asmx/dknll?dknx=" + s;
		// 根据给定的URL新建一个请求
		StringRequest stringRequest = new StringRequest(JSONDateUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println("Response=" + arg0);
						tv_watcher.setText("公积金：" + arg0 + "% 商业：5.600%");
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						System.out.println("error=" + arg0);
					}
				});

		requestQueue.add(stringRequest);
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	private TextView tv_watcher;

	@Override
	public void onTextChanged(CharSequence s, int start, int after, int count) {
		// 公积金：3.750% 商业：5.600%
		if (s.length() > 0) {
			getJsonVolley(s.toString());
		}
	}
}
