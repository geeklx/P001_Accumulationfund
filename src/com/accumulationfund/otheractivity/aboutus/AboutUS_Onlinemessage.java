package com.accumulationfund.otheractivity.aboutus;

import org.json.JSONArray;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liangxiao.accumulationfund.R;

public class AboutUS_Onlinemessage extends Activity implements OnClickListener {
	private ImageButton ib_btn_right,ib_btn_left;
	private LinearLayout ll_back;
	private TextView tv_middle;
	private Button btn_msg;
	private EditText ed_aboutus_onlinemessage_name,
			ed_aboutus_onlinemessage_emails, ed_aboutus_onlinemessage_tel,
			ed_aboutus_onlinemessage_msg;
	private Handler handler;
	private Message msg;
	private Context mContext;
	private Dialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_aboutus_onlinemessage);
		// 初始化动画部分
		Constant.mType = AnimationType.FLIP_VERTICAL;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		handler = new myToast(AboutUS_Onlinemessage.this);
		initView();
	}

	private void initView() {
		mContext = this;
		ed_aboutus_onlinemessage_name = (EditText) findViewById(R.id.ed_aboutus_onlinemessage_name);
		ed_aboutus_onlinemessage_emails = (EditText) findViewById(R.id.ed_aboutus_onlinemessage_emails);
		ed_aboutus_onlinemessage_tel = (EditText) findViewById(R.id.ed_aboutus_onlinemessage_tel);
		ed_aboutus_onlinemessage_msg = (EditText) findViewById(R.id.ed_aboutus_onlinemessage_msg);

		tv_middle = (TextView) findViewById(R.id.tv_middle);
		btn_msg = (Button) findViewById(R.id.btn_msg);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		tv_middle.setText(getResources().getString(
				R.string.af_aboutus_onlinemessage));
		ll_back.setVisibility(View.VISIBLE);
		ib_btn_right.setVisibility(View.GONE);

		ll_back.setOnClickListener(this);
		btn_msg.setOnClickListener(this);

		// 加载提示框
		progressDialog = new Dialog(AboutUS_Onlinemessage.this,
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
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_btn_left:
			finish();
			break;
		case R.id.btn_msg:
			if (ed_aboutus_onlinemessage_name.getText().length() <= 0) {
				handMessage(13);
				return;
			}
			if (ed_aboutus_onlinemessage_emails.getText().length() <= 0) {
				handMessage(14);
				return;
			}
			if (ed_aboutus_onlinemessage_tel.getText().length() <= 0) {
				handMessage(15);
				return;
			}
			if (ed_aboutus_onlinemessage_tel.getText().length() < 11) {
				handMessage(155);
				return;
			}
			if (ed_aboutus_onlinemessage_msg.getText().length() <= 0) {
				handMessage(16);
				return;
			}
			progressDialog.show();
			// 接口部分http://192.168.15.86:8002/WebService.asmx/GuestBook
			// http://192.168.15.86:8002/WebService.asmx/GuestBook?name=geek&content=test&phone=11111111111&email=1@163.com
			getJsonVolley();

			break;
		default:
			break;
		}
	}

	// 获取json字符串
	public void getJsonVolley() {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		String JSONDateUrl = "http://192.168.15.86:8002/WebService.asmx/GuestBook?name="
				+ ed_aboutus_onlinemessage_name.getText().toString()
				+ "&content="
				+ ed_aboutus_onlinemessage_msg.getText().toString()
				+ "&email="
				+ ed_aboutus_onlinemessage_emails.getText().toString()
				+ "&phone=" + ed_aboutus_onlinemessage_tel.getText().toString();
		// 根据给定的URL新建一个请求
		StringRequest stringRequest = new StringRequest(JSONDateUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println("Response=" + arg0);
						// 加载数据
						if (arg0.equals("1")) {
							msg = handler.obtainMessage();
							msg.arg1 = 3;
							handler.sendMessage(msg);
							progressDialog.dismiss();
							// 格式化文本框
							clearText();
						}
						if (arg0.equals("2")) {
							msg = handler.obtainMessage();
							msg.arg1 = 7;
							handler.sendMessage(msg);
							progressDialog.dismiss();
							// 格式化文本框
							clearText();
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

	private void clearText() {
		ed_aboutus_onlinemessage_name.getText().clear();
		ed_aboutus_onlinemessage_msg.getText().clear();
		ed_aboutus_onlinemessage_emails.getText().clear();
		ed_aboutus_onlinemessage_tel.getText().clear();
	}

	public class MyTask1 extends AsyncTask<Void, String, JSONArray> {
		private String string;

		public MyTask1(String string) {
			this.string = string;
		}

		@Override
		protected void onPostExecute(JSONArray result) {
			super.onPostExecute(result);
			// String a = result.getJSONArray("status");
			// if(){
			//
			// }
			progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			// 加载提示框
			// dialog = new
			// ProgressDialog(Huishenghuo_Huijingxuan_Activity.this);
			// dialog.setTitle("提示");
			// dialog.setMessage("正在下载，请稍后....");
			// dialog.show();

		}

		@Override
		protected JSONArray doInBackground(Void... params) {
			try {
				JSONArray result = new JSONArray(string);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.toString());
				msg = handler.obtainMessage();
				msg.arg1 = 5;
				handler.sendMessage(msg);
			}
			return null;
		}
	}

	private void handMessage(int i) {
		msg = handler.obtainMessage();
		msg.arg1 = i;
		handler.sendMessage(msg);
	}
}
