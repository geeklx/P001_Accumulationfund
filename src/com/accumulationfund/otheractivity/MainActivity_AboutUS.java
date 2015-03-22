package com.accumulationfund.otheractivity;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accumulationfund.main.update.UpdateManager;
import com.accumulationfund.otheractivity.aboutus.AboutUS_Helpinformation;
import com.accumulationfund.otheractivity.aboutus.AboutUS_Login;
import com.accumulationfund.otheractivity.aboutus.AboutUS_Onlinemessage;
import com.accumulationfund.otheractivity.aboutus.AboutUS_Privacystatement;
import com.accumulationfund.otheractivity.toast.myToast;
import com.accumulationfund.util.JsonData;
import com.liangxiao.accumulationfund.R;

@SuppressLint("WorldReadableFiles")
public class MainActivity_AboutUS extends Activity implements OnClickListener {
	private ImageButton ib_btn_right, ib_btn_left;
	private TextView tv_middle, tv_tel,tv_back;
	private LinearLayout ll_back;
	private Button btn_login;
	private Handler handler;
	private Message msg;
	private LinearLayout ll_privacystatement, ll_onlinemessage,
			ll_versionupdate, ll_servicehotline, ll_helpinformation;
	private Context mContext;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_aboutus);
		// 关于我们页面
		mContext = this;
		initView();
		handler = new myToast(MainActivity_AboutUS.this);
	}

	private void initView() {

		ll_privacystatement = (LinearLayout) findViewById(R.id.ll_privacystatement);
		ll_onlinemessage = (LinearLayout) findViewById(R.id.ll_onlinemessage);
		ll_versionupdate = (LinearLayout) findViewById(R.id.ll_versionupdate);
		ll_servicehotline = (LinearLayout) findViewById(R.id.ll_servicehotline);
		ll_helpinformation = (LinearLayout) findViewById(R.id.ll_helpinformation);
		tv_tel = (TextView) findViewById(R.id.tv_tel);
		tv_tel.setText(splitPhoneNumRe(tv_tel.getText().toString()));
		tv_back= (TextView) findViewById(R.id.tv_back);
		tv_back.setOnClickListener(this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setEnabled(true);
		btn_login.setBackgroundResource(R.drawable.btn_able);
		// 判断登录状态
		if (JsonData.getApplication().getMemCache("SFZH") == null) {
			JsonData.getApplication().setMemCache("SFZH", "");
		}
		if (JsonData.getApplication().getMemCache("SFZH") == "") {
			btn_login.setText(getResources().getString(
					R.string.af_aboutus_login));
		} else {
			btn_login.setText(getResources().getString(
					R.string.af_aboutus_login2));
		}

		tv_middle.setText(getResources().getString(R.string.af_aboutus));
		ll_back.setVisibility(View.VISIBLE);
		ib_btn_right.setVisibility(View.GONE);

		ll_back.setOnClickListener(this);
		ib_btn_left.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		ll_privacystatement.setOnClickListener(this);
		ll_onlinemessage.setOnClickListener(this);
		ll_versionupdate.setOnClickListener(this);
		ll_servicehotline.setOnClickListener(this);
		ll_helpinformation.setOnClickListener(this);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_back:
			finish();
			break;
		case R.id.btn_login:
			// 登录
			if (JsonData.getApplication().getMemCache("SFZH") == null) {
				Intent intent = new Intent(MainActivity_AboutUS.this,
						AboutUS_Login.class);
				startActivityForResult(intent, 2);

			} else {
				if (JsonData.getApplication().getMemCache("SFZH") == "") {
					Intent intent = new Intent(MainActivity_AboutUS.this,
							AboutUS_Login.class);
					startActivityForResult(intent, 2);
				} else {

					// 退出登录
					sp = this.getSharedPreferences("userInfo",
							Context.MODE_WORLD_READABLE);
					// sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
					sp.edit().remove("SFZH").commit();
					sp.edit().remove("key").commit();
					sp.edit().remove("AUTO_ISCHECK").commit();
					JsonData.getApplication().setMemCache("SFZH", "");
					JsonData.getApplication().setMemCache("key", "");
					JsonData.getApplication().setMemCache("loginpage", "");

					btn_login.setText(getResources().getString(
							R.string.af_aboutus_login));
				}
			}
			break;
		case R.id.ll_privacystatement:
			// 隐私声明
			Intent intent1 = new Intent(MainActivity_AboutUS.this,
					AboutUS_Privacystatement.class);
			startActivity(intent1);
			break;
		case R.id.ll_onlinemessage:
			// 在线留言
			Intent intent2 = new Intent(MainActivity_AboutUS.this,
					AboutUS_Onlinemessage.class);
			startActivity(intent2);
			break;
		case R.id.ll_versionupdate:
			// 版本更新
			versionupdate();
			break;
		case R.id.ll_servicehotline:
			// 服务热线
			servicehotline();
			break;
		case R.id.ll_helpinformation:
			// 帮助信息
			Intent intent3 = new Intent(MainActivity_AboutUS.this,
					AboutUS_Helpinformation.class);
			startActivity(intent3);
			break;
		default:
			break;
		}
	}

	// 回传的值
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// if (resultCode == Activity.RESULT_OK) {
		if (resultCode == 2) {

			btn_login.setText(getResources().getString(
					R.string.af_aboutus_login2));
		}
		// }
	}

	/**
	 * 服务热线
	 */
	private void servicehotline() {
		final String telPhone = splitPhoneNumRe(tv_tel.getText().toString());
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示框")
				.setIcon(R.drawable.ic_launcher)
				.setCancelable(false)
				.setMessage("拨打住房公积金电话:" + splitPhoneNumRe(telPhone))
				.setPositiveButton(R.string.af_ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intentphone = new Intent();
								// 系统默认的action，用来打开默认的电话界面
								intentphone.setAction(Intent.ACTION_CALL);
								// 需要拨打的号码
								intentphone.setData(Uri.parse("tel:"
										+ splitPhoneNumRe(tv_tel.getText()
												.toString())));
								MainActivity_AboutUS.this
										.startActivity(intentphone);
							}
						})
				.setNegativeButton(R.string.af_cancel,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	/**
	 * 更新部分
	 */
	private void versionupdate() {
		// 这里来检测版本是否需要更新
		UpdateManager manager = new UpdateManager(mContext,
				MainActivity_AboutUS.this);
		try {
//			if (JsonData.getApplication().getMemCache("versionCode") != null) {
//				manager.checkUpdate();
//			} else {
//				handMessage(12);
//			}
			manager.checkUpdate();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void handMessage(int i) {
		msg = handler.obtainMessage();
		msg.arg1 = i;
		handler.sendMessage(msg);
	}

	/**
	 * 手机号显示格式400-808-0688
	 * 
	 * @param phone
	 * @return
	 */
	@SuppressWarnings("unused")
	private String splitPhoneNum(String phone) {
		StringBuilder builder = new StringBuilder(phone);
		builder.reverse();
		for (int i = 4, len = builder.length(); i < len; i += 4) {
			builder.insert(i, '-');
		}

		builder.reverse();
		return builder.toString();

	}

	/**
	 * 手机号显示格式4008080688
	 * 
	 * @param phone
	 * @return
	 */
	private String splitPhoneNumRe(String phone) {
		phone = phone.replace("-", "");
		return phone.toString();

	}

}
