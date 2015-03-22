package com.accumulationfund.otheractivity.aboutus;

import org.json.JSONArray;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.otheractivity.MainActivity_AboutUS;
import com.accumulationfund.otheractivity.MainActivity_logo12;
import com.accumulationfund.otheractivity.MainActivity_logo2;
import com.accumulationfund.otheractivity.MainActivity_logo3;
import com.accumulationfund.otheractivity.MainActivity_logo4;
import com.accumulationfund.otheractivity.MainActivity_logo5;
import com.accumulationfund.otheractivity.MainActivity_logo6;
import com.accumulationfund.otheractivity.toast.VerificationActivity;
import com.accumulationfund.otheractivity.toast.myToast;
import com.accumulationfund.util.JsonData;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liangxiao.accumulationfund.R;

public class AboutUS_Login extends Activity implements OnClickListener {
	// 公共
	private ImageButton ib_btn_right, ib_btn_left;
	private TextView tv_middle;
	private LinearLayout ll_back;
	private Handler handler;
	private Message msg;

	// 非公共
	private Context mContext;
	private EditText et_uName, et_uPwd;
	private CheckBox checkbox;
	private Button btn_login;
	private SharedPreferences sp;
	private Dialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_aboutus_login);
		// 初始化动画部分
		Constant.mType = AnimationType.ALPHA;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		initView();
	}

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	private void initView() {
		mContext = this;
		handler = new myToast(AboutUS_Login.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);

		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);

		tv_middle.setText(getResources().getString(R.string.af_aboutus_login));
		ll_back.setVisibility(View.VISIBLE);
		ib_btn_right.setVisibility(View.GONE);

		ll_back.setOnClickListener(this);
		// 加载提示框
		progressDialog = new Dialog(AboutUS_Login.this, R.style.progress_dialog);
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
		// 非公共
		et_uName = (EditText) findViewById(R.id.uName);// 请输入用户名
		et_uPwd = (EditText) findViewById(R.id.uPwd);// 请输入密码
		checkbox = (CheckBox) findViewById(R.id.checkbox);// 记住密码
		sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
		et_uName.setText(sp.getString("SFZH", ""));
		et_uPwd.setText(sp.getString("key", ""));
		if (sp.getBoolean("AUTO_ISCHECK", false)) {
			checkbox.setChecked(true);
		} else {
			checkbox.setChecked(false);
		}
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (checkbox.isChecked()) {
					// 410526196601080064
					sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
					System.out.println("自动登录已选中->" + "true->"
							+ sp.getBoolean("AUTO_ISCHECK", true) + ",false->"
							+ sp.getBoolean("AUTO_ISCHECK", false));

				} else {
					sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
					System.out.println("自动登录没有选中->" + "true->"
							+ sp.getBoolean("AUTO_ISCHECK", true) + ",false->"
							+ sp.getBoolean("AUTO_ISCHECK", false));
				}
			}
		});
		btn_login = (Button) findViewById(R.id.btn_login);// 登录
		btn_login.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			finish();
			break;
		case R.id.btn_login:
			// 登录部分
			if (et_uName.getText().length() <= 0) {
				handMessage(112);
				return;
			}
			if (et_uPwd.getText().length() <= 0) {
				handMessage(113);
				return;
			}
			if (!VerificationActivity
					.checkIdCard(et_uName.getText().toString())) {
				handMessage(1122);
				return;
			}
			if (et_uPwd.getText().length() < 6) {
				handMessage(1133);
				return;
			}
			// 接口部分
			getJsonVolley(et_uName.getText().toString(), et_uPwd.getText()
					.toString());
			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent2 = new Intent(AboutUS_Login.this,
				MainActivity_AboutUS.class);
		setResult(1,intent2);
		super.onBackPressed();
	}

	// 获取json字符串
	public void getJsonVolley(final String name, final String key) {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		String JSONDateUrl = JsonData.SERVICE_URL
				+ "WebService.asmx/UserLogin?SFZH=" + name + "&MM=" + key;
		System.out.println("URL=" + JSONDateUrl);
		// 根据给定的URL新建一个请求
		StringRequest stringRequest = new StringRequest(JSONDateUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println("Response=" + arg0);
						// 加载数据
						if (arg0.equals("1")) {
							// 判断自动登录部分
							if (sp.getBoolean("AUTO_ISCHECK", false)) {
								Editor editor = sp.edit();
								editor.putString("SFZH", name);
								editor.putString("key", key);
								editor.commit();
							}
							JsonData.getApplication().setMemCache("SFZH", name);
							JsonData.getApplication().setMemCache("key", key);
							// 判断登录跳转页面
							if (JsonData.getApplication().getMemCache(
									"loginpage") == null) {
								Intent intent2 = new Intent(AboutUS_Login.this,
										MainActivity_AboutUS.class);
								setResult(2, intent2);
								finish();
							} else {
								if (JsonData.getApplication().getMemCache(
										"loginpage") == "") {
									Intent intent22 = new Intent(
											AboutUS_Login.this,
											MainActivity_AboutUS.class);
									setResult(2, intent22);
									finish();
								} else {
									if (JsonData.getApplication()
											.getMemCache("loginpage")
											.equals("MainActivity_logo2")) {
										Intent intentlogo2 = new Intent(
												AboutUS_Login.this,
												MainActivity_logo2.class);
										startActivity(intentlogo2);
										finish();
									} else if (JsonData.getApplication()
											.getMemCache("loginpage")
											.equals("MainActivity_logo3")) {
										Intent intentlogo3 = new Intent(
												AboutUS_Login.this,
												MainActivity_logo3.class);
										startActivity(intentlogo3);
										finish();
									} else if (JsonData.getApplication()
											.getMemCache("loginpage")
											.equals("MainActivity_logo4")) {
										Intent intentlogo4 = new Intent(
												AboutUS_Login.this,
												MainActivity_logo4.class);
										startActivity(intentlogo4);
										finish();
									} else if (JsonData.getApplication()
											.getMemCache("loginpage")
											.equals("MainActivity_logo5")) {
										Intent intentlogo5 = new Intent(
												AboutUS_Login.this,
												MainActivity_logo5.class);
										startActivity(intentlogo5);
										finish();
									} else if (JsonData.getApplication()
											.getMemCache("loginpage")
											.equals("MainActivity_logo6")) {
										Intent intentlogo6 = new Intent(
												AboutUS_Login.this,
												MainActivity_logo6.class);
										startActivity(intentlogo6);
										finish();
									} else if (JsonData.getApplication()
											.getMemCache("loginpage")
											.equals("MainActivity_logo12")) {
										Intent intentlogo12 = new Intent(
												AboutUS_Login.this,
												MainActivity_logo12.class);
										startActivity(intentlogo12);
										finish();
									}
								}
							}
							msg = handler.obtainMessage();
							msg.arg1 = 1;
							handler.sendMessage(msg);
							progressDialog.dismiss();
						}
						if (arg0.equals("0")) {
							msg = handler.obtainMessage();
							msg.arg1 = 122;
							handler.sendMessage(msg);
							progressDialog.dismiss();
						}
						if (arg0.equals("2")) {
							msg = handler.obtainMessage();
							msg.arg1 = 121;
							handler.sendMessage(msg);
							progressDialog.dismiss();
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						System.out.println("error=" + arg0);
					}
				});

		requestQueue.add(stringRequest);
	}

	public class MyTask1 extends AsyncTask<Void, String, JSONArray> {
		private String string;

		public MyTask1(String string) {
			this.string = string;
		}

		@Override
		protected void onPostExecute(JSONArray result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {

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
