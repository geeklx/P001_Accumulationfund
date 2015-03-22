package com.accumulationfund.otheractivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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

@SuppressLint("CutPasteId")
public class MainActivity_logo9_fragment2Detail extends Activity implements
		OnClickListener {
	// ����
	private LinearLayout ll_back;// ����
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right, ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;

	// �ǹ���
	private Button btn_msg;
	private EditText ed_logo9d_1,// �����ܼ�
			ed_logo9d_2,// �׸���
			ed_logo9d_3,// �����ܶ�
			ed_logo9d_4,// �������������
			ed_logo9d_5, // ��ҵ��������
			ed_logo9d_6, // �����ܶ�
			ed_logo9d_7, // ֧����Ϣ��
			ed_logo9d_8,// ��������
			ed_logo9d_10,// ���»���
			ed_logo9d_11;// ĩ�»���
	@SuppressWarnings("unused")
	private Object a, b, c, d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo9_fragment2detail);
		// ��ʼ����������
		Constant.mType = AnimationType.ALPHA;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		initLayout();
		Intent intent = getIntent();
		a = intent.getStringExtra("a");// ���������
		b = intent.getStringExtra("b");// �����ܼ�
		c = intent.getStringExtra("c");// ���ҳ���
		d = intent.getStringExtra("d");// ��������
		// ��ȡ���
		getJsonVolley();
	}

	private void initLayout() {
		handler = new myToast(MainActivity_logo9_fragment2Detail.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_9_detail));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_right.setVisibility(View.GONE);

		btn_msg = (Button) findViewById(R.id.btn_msg_d2);
		btn_msg.setOnClickListener(this);

		ed_logo9d_1 = (EditText) findViewById(R.id.ed_logo9d2_1);
		ed_logo9d_2 = (EditText) findViewById(R.id.ed_logo9d2_2);
		ed_logo9d_3 = (EditText) findViewById(R.id.ed_logo9d2_3);
		ed_logo9d_4 = (EditText) findViewById(R.id.ed_logo9d2_4);
		ed_logo9d_5 = (EditText) findViewById(R.id.ed_logo9d2_5);
		ed_logo9d_6 = (EditText) findViewById(R.id.ed_logo9d2_6);
		ed_logo9d_7 = (EditText) findViewById(R.id.ed_logo9d2_7);
		ed_logo9d_8 = (EditText) findViewById(R.id.ed_logo9d2_8);
		ed_logo9d_10 = (EditText) findViewById(R.id.ed_logo9d2_10);
		ed_logo9d_11 = (EditText) findViewById(R.id.ed_logo9d2_11);
		ed_logo9d_1.setEnabled(false);
		ed_logo9d_2.setEnabled(false);
		ed_logo9d_3.setEnabled(false);
		ed_logo9d_4.setEnabled(false);
		ed_logo9d_5.setEnabled(false);
		ed_logo9d_6.setEnabled(false);
		ed_logo9d_7.setEnabled(false);
		ed_logo9d_8.setEnabled(false);
		ed_logo9d_10.setEnabled(false);
		ed_logo9d_11.setEnabled(false);
	}

	private void getResult(String arg0) {
		Double d1 = Double.valueOf(b.toString()) * (10000);// �����ܼ�
		Double cc = Double.valueOf(c.toString()) / 10;
		Double d2 = d1 * Double.valueOf(cc.toString());// �����ܶ�
		Double d22 = d1 - d2;// �׸���
		ed_logo9d_1.setText(d1.toString());// �����ܼ�
		ed_logo9d_2.setText(d22.toString());// �׸���
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
		Double d9 = d6 / d8;// �¾�����
		Double d101 = d2 * d44;
		Double d10 = d9 + d101;
		Double d100 = Math.round(d10 * 10) / 10d;
		ed_logo9d_10.setText(d100.toString());// ���»���
		Double d111 = (d2 - d2 / (dd * 12) * (dd * 12 - 1)) * d44;
		Double d11 = d9 + d111;
		Double d1111 = Math.round(d11 * 10) / 10d;
		ed_logo9d_11.setText(d1111.toString());// ĩ�»���
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			// ����
			finish();
			break;
		case R.id.btn_msg_d2:
			// ����鿴
			Intent intent = new Intent(MainActivity_logo9_fragment2Detail.this,
					MainActivity_logo9_fragment2Detail2.class);
			String a = ed_logo9d_4.getText().toString();
			a = a.replace("%", "");
			intent.putExtra("nlv", a);// ������������
			intent.putExtra("dkje", ed_logo9d_3.getText().toString());// �����ܶ�
			intent.putExtra("dknx", d.toString());// ��������
			Log.i("ÿ����ϸ->",
					"������������->" + a + ",�����ܶ�->"
							+ ed_logo9d_3.getText().toString() + ",��������->"
							+ d.toString());
			startActivity(intent);
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
