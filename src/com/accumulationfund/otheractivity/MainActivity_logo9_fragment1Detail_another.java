package com.accumulationfund.otheractivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.otheractivity.toast.myToast;
import com.accumulationfund.util.JsonData;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liangxiao.accumulationfund.R;

@SuppressLint("ShowToast")
public class MainActivity_logo9_fragment1Detail_another extends Activity
		implements OnClickListener {
	// ����
	private LinearLayout ll_back;// ����
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right, ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;

	private EditText ed_logo9d_1,// �����ܼ�
			ed_logo9d_2,// �׸���
			ed_logo9d_3,// �����ܶ�
			ed_logo9d_4,// �������������
			ed_logo9d_5, // ��ҵ��������
			ed_logo9d_6, // �����ܶ�
			ed_logo9d_7, // ֧����Ϣ��
			ed_logo9d_8,// ��������
			ed_logo9d_9;// �¾�����
	@SuppressWarnings("unused")
	private Object a, b, d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo9_fragment1detail);
		// ��ʼ����������
		Constant.mType = AnimationType.ALPHA;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		initLayout();
		Intent intent = getIntent();
		a = intent.getStringExtra("a");// ���������
		b = intent.getStringExtra("b");// �����ܶ�
		d = intent.getStringExtra("d");// ��������
		// ��ȡ���
		getJsonVolley();
	}

	private void getResult(String arg0) {
		ed_logo9d_1.setText("0.0");// �����ܼ�
		ed_logo9d_2.setText("0.0");// �׸���
		Double d2 = Double.valueOf(b.toString()) * 10000;
		ed_logo9d_3.setText(d2.toString());// �����ܶ�
		ed_logo9d_4.setText(arg0 + "%");// �������������
		ed_logo9d_5.setText("0");// ��ҵ��������
		Double d44 = Double.valueOf(arg0) / 100 / 12;// ���������������
		Double dd = Double.valueOf(d.toString());
		Double d6 = d2 * d44 * Math.pow((1 + d44), (dd * 12))
				/ (Math.pow((1 + d44), (dd * 12)) - 1) * dd * 12;
		Double d66 = Math.round(d6 * 10) / 10d;
		ed_logo9d_6.setText(d66.toString());// �����ܶ�
		Double d7 = d6 - d2;
		Double d77 = Math.round(d7 * 10) / 10d;
		ed_logo9d_7.setText(d77.toString());// ֧����Ϣ��
		Double d8 = dd * 12;// ��������
		int d88 = (int) Math.round(d8);
		ed_logo9d_8.setText(d88 + "");// ��������
		Double d9 = d6 / d8;
		Double d90 = Math.round(d9 * 10) / 10d;
		ed_logo9d_9.setText(d90.toString());// �»���
	}

	// http://192.168.15.86:8002/WebService.asmx/dknll?dknx=1
	// ��ȡjson�ַ���
	public void getJsonVolley() {
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		String JSONDateUrl = JsonData.SERVICE_URL
				+ "WebService.asmx/dknll?dknx=" + d.toString();
		// ���ݸ�����URL�½�һ������
		StringRequest stringRequest = new StringRequest(JSONDateUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println("Response=" + arg0);
						// ��������
						getResult(arg0);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						System.out.println("error=" + arg0);
					}
				});

		requestQueue.add(stringRequest);
	}

	private void initLayout() {
		handler = new myToast(MainActivity_logo9_fragment1Detail_another.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_9_detail));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		ll_back.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_right.setVisibility(View.GONE);

		ed_logo9d_1 = (EditText) findViewById(R.id.ed_logo9d_1);
		ed_logo9d_2 = (EditText) findViewById(R.id.ed_logo9d_2);
		ed_logo9d_3 = (EditText) findViewById(R.id.ed_logo9d_3);
		ed_logo9d_4 = (EditText) findViewById(R.id.ed_logo9d_4);
		ed_logo9d_5 = (EditText) findViewById(R.id.ed_logo9d_5);
		ed_logo9d_6 = (EditText) findViewById(R.id.ed_logo9d_6);
		ed_logo9d_7 = (EditText) findViewById(R.id.ed_logo9d_7);
		ed_logo9d_8 = (EditText) findViewById(R.id.ed_logo9d_8);
		ed_logo9d_9 = (EditText) findViewById(R.id.ed_logo9d_9);
		ed_logo9d_1.setEnabled(false);
		ed_logo9d_2.setEnabled(false);
		ed_logo9d_3.setEnabled(false);
		ed_logo9d_4.setEnabled(false);
		ed_logo9d_5.setEnabled(false);
		ed_logo9d_6.setEnabled(false);
		ed_logo9d_7.setEnabled(false);
		ed_logo9d_8.setEnabled(false);
		ed_logo9d_9.setEnabled(false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			// ����
			finish();
			break;

		default:
			break;
		}
	}

	@SuppressWarnings("unused")
	private void handMessage(int i) {
		msg = handler.obtainMessage();
		msg.arg1 = i;
		handler.sendMessage(msg);
	}
}
