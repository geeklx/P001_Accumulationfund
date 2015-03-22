package com.accumulationfund.otheractivity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.otheractivity.adapter.Adapter_Logo4;
import com.accumulationfund.otheractivity.bean.Bean_Logo4;
import com.accumulationfund.otheractivity.toast.myToast;
import com.accumulationfund.util.JsonData;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liangxiao.accumulationfund.R;

public class MainActivity_logo4 extends Activity implements OnClickListener {
	// ����
	private LinearLayout ll_back;// ����
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right, ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;
	private Context mContext;
	private ListView id_listView;
	private Adapter_Logo4 adapter_Logo4;
	private Dialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo44);
		// ��ʼ����������
		Constant.mType = AnimationType.HORIZON_CROSS;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		// ������Ϣ
		initView();
		getJsonVolley();
	}

	private void initView() {
		mContext = this;
		handler = new myToast(MainActivity_logo4.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_4));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_right.setVisibility(View.GONE);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		id_listView = (ListView) findViewById(R.id.id_listView);
		// ������ʾ��
		progressDialog = new Dialog(MainActivity_logo4.this,
				R.style.progress_dialog);
		progressDialog.setContentView(R.layout.af_loading_dialog);
		progressDialog.setCancelable(false);
		progressDialog.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					progressDialog.dismiss();
				}
				return false;
			}
		});
		progressDialog.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		TextView msg = (TextView) progressDialog
				.findViewById(R.id.id_tv_loadingmsg);
		msg.setText(getResources().getString(R.string.af_loading));
		progressDialog.show();
	}

	// ��ȡjson�ַ���
	public void getJsonVolley() {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		String JSONDateUrl = JsonData.SERVICE_URL
				+ "WebService.asmx/QueryDKXXBySFZH?SFZH="
				+ JsonData.getApplication().getMemCache("SFZH");
		// ���ݸ�����URL�½�һ������
		StringRequest stringRequest = new StringRequest(JSONDateUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println("Response=" + arg0);
						// ��������
						try {
							JSONObject jsonObject = new JSONObject(arg0);
							List<Bean_Logo4> mList = new ArrayList<Bean_Logo4>();
							mList = getBean4(jsonObject);
							adapter_Logo4 = new Adapter_Logo4(mContext, mList);
							id_listView.setAdapter(adapter_Logo4);
							progressDialog.dismiss();
						} catch (JSONException e) {
							e.printStackTrace();
						}
						// new MyTask1(arg0).execute();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						System.out.println("error=" + arg0);
					}
				});

		requestQueue.add(stringRequest);
	}

	private List<Bean_Logo4> getBean4(JSONObject result) {
		List<Bean_Logo4> list = new ArrayList<Bean_Logo4>();

		try {
			JSONArray jsonArray = result.getJSONArray("tables");
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			Bean_Logo4 bean = new Bean_Logo4();
			if (result != null) {
				bean.setNum1(jsonObject.getString("DKHTBH"));// �����ͬ���
				bean.setNum2(jsonObject.getString("HKYHZH"));// �����˺�
				bean.setNum3(jsonObject.getString("GRXM"));// ���������
				bean.setNum4(jsonObject.getString("SFZH"));// ���֤��
				bean.setNum5(jsonObject.getString("DFXM"));// ��ż����
				bean.setNum6(jsonObject.getString("PSFZH"));// ��ż���֤
				bean.setNum7(jsonObject.getString("ZHMC"));// ������˻�
				bean.setNum8(jsonObject.getString("DKNX"));// ��������
				bean.setNum9(jsonObject.getString("DKJE"));// ������
				String str = jsonObject.getString("YHXZRQ");
				str = str.substring(0, str.lastIndexOf("T"));
				bean.setNum10(str);// �ſ�����
				bean.setNum11(jsonObject.getString("YJHKE"));// �»����
				bean.setNum12(jsonObject.getString("ZXLL"));// ����������
				bean.setNum13(jsonObject.getString("YHLXLJ"));// �ѻ���Ϣ
				bean.setNum14(jsonObject.getString("DKYE"));// �������
				bean.setNum15(jsonObject.getString("ZHZT"));// ����״̬
				bean.setNum16(jsonObject.getString("YHBJLJ"));// �ѻ�����
				bean.setNum17(jsonObject.getString("DQQS"));// �ѻ�����
				bean.setNum18(jsonObject.getString("HKYQS"));// ��ǰ��������
				bean.setNum19(jsonObject.getString("STYH"));// ��������
				bean.setNum20(jsonObject.getString("DBFS"));// ������ʽ
				bean.setNum21(jsonObject.getString("HKFS"));// ���ʽ
				list.add(bean);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return list;
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
