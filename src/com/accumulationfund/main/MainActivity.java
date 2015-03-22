package com.accumulationfund.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.main.lunbo.CircleFlowIndicator;
import com.accumulationfund.main.lunbo.ImageAdapter;
import com.accumulationfund.main.lunbo.ViewFlow;
import com.accumulationfund.otheractivity.MainActivity_AboutUS;
import com.accumulationfund.otheractivity.MainActivity_logo1;
import com.accumulationfund.otheractivity.MainActivity_logo10;
import com.accumulationfund.otheractivity.MainActivity_logo11;
import com.accumulationfund.otheractivity.MainActivity_logo12;
import com.accumulationfund.otheractivity.MainActivity_logo2;
import com.accumulationfund.otheractivity.MainActivity_logo3;
import com.accumulationfund.otheractivity.MainActivity_logo4;
import com.accumulationfund.otheractivity.MainActivity_logo5;
import com.accumulationfund.otheractivity.MainActivity_logo6;
import com.accumulationfund.otheractivity.MainActivity_logo7;
import com.accumulationfund.otheractivity.MainActivity_logo8;
import com.accumulationfund.otheractivity.MainActivity_logo9;
import com.accumulationfund.otheractivity.aboutus.AboutUS_Login;
import com.accumulationfund.util.JsonData;
import com.liangxiao.accumulationfund.R;

public class MainActivity extends Activity implements OnClickListener {
	private TextView tv_middle;
	// ÂÖ²¥²¿·Ö
	private ViewFlow viewFlow;
	private Activity activity;
	private static final int[] ids = { R.drawable.test1, R.drawable.test2,
			R.drawable.test3, R.drawable.test4 };

	private int[] urls = { R.drawable.test1, R.drawable.test2,
			R.drawable.test3, R.drawable.test4 };

	// µã»÷Í¼Æ¬
	private ImageButton ib_btn_right, iv_logo_1, iv_logo_2, iv_logo_3,
			iv_logo_4, iv_logo_5, iv_logo_6, iv_logo_7, iv_logo_8, iv_logo_9,
			iv_logo_10, iv_logo_11, iv_logo_12;
	private LinearLayout ll_back;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_main);
		// ³õÊ¼»¯¶¯»­²¿·Ö
		Constant.mType = AnimationType.FLIP_HORIZON;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		initView();
		// Í¼Æ¬ÂÖ²¥²¿·Ö
		lunbo();
	}

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	private void initView() {
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_right.setVisibility(View.VISIBLE);
		ib_btn_right.setOnClickListener(this);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.GONE);
		iv_logo_1 = (ImageButton) findViewById(R.id.iv_logo1);
		iv_logo_2 = (ImageButton) findViewById(R.id.iv_logo2);
		iv_logo_3 = (ImageButton) findViewById(R.id.iv_logo3);
		iv_logo_4 = (ImageButton) findViewById(R.id.iv_logo4);
		iv_logo_5 = (ImageButton) findViewById(R.id.iv_logo5);
		iv_logo_6 = (ImageButton) findViewById(R.id.iv_logo6);
		iv_logo_7 = (ImageButton) findViewById(R.id.iv_logo7);
		iv_logo_8 = (ImageButton) findViewById(R.id.iv_logo8);
		iv_logo_9 = (ImageButton) findViewById(R.id.iv_logo9);
		iv_logo_10 = (ImageButton) findViewById(R.id.iv_logo10);
		iv_logo_11 = (ImageButton) findViewById(R.id.iv_logo11);
		iv_logo_12 = (ImageButton) findViewById(R.id.iv_logo12);
		iv_logo_1.setOnClickListener(this);
		iv_logo_2.setOnClickListener(this);
		iv_logo_3.setOnClickListener(this);
		iv_logo_4.setOnClickListener(this);
		iv_logo_5.setOnClickListener(this);
		iv_logo_6.setOnClickListener(this);
		iv_logo_7.setOnClickListener(this);
		iv_logo_8.setOnClickListener(this);
		iv_logo_9.setOnClickListener(this);
		iv_logo_10.setOnClickListener(this);
		iv_logo_11.setOnClickListener(this);
		iv_logo_12.setOnClickListener(this);
		sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
		System.out.println("AUTO_ISCHECK->" + "true->"
				+ sp.getBoolean("AUTO_ISCHECK", true) + ",false->"
				+ sp.getBoolean("AUTO_ISCHECK", false));
		if (sp.getBoolean("AUTO_ISCHECK", true)) {
			JsonData.getApplication().setMemCache("SFZH",
					sp.getString("SFZH", ""));
		}
		System.out.println("»º´æ->"
				+ JsonData.getApplication().getMemCache("SFZH"));
	}

	/**
	 * Í¼ÏñÂÖ²¥
	 */
	private void lunbo() {
		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		viewFlow.setAdapter(new ImageAdapter(this, urls, activity));
		viewFlow.setmSideBuffer(ids.length); //
		CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(2000);
		viewFlow.setSelection(3 * 1000); //
		viewFlow.startAutoFlowTimer(); //
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_middle:
			Intent intent_logo = new Intent(MainActivity.this,
					AboutUS_Login.class);
			startActivity(intent_logo);
			break;
		case R.id.ib_btn_right:
			Intent intent_us = new Intent(MainActivity.this,
					MainActivity_AboutUS.class);
			startActivity(intent_us);
			break;
		case R.id.iv_logo1:
			Intent intent1 = new Intent(MainActivity.this,
					MainActivity_logo1.class);
			startActivity(intent1);

			break;
		case R.id.iv_logo2:
			// ÅÐ¶ÏÊÇ·ñµÇÂ¼
			if (JsonData.getApplication().getMemCache("SFZH") == null) {
				JsonData.getApplication().setMemCache("loginpage",
						"MainActivity_logo2");
				Intent intent = new Intent(MainActivity.this,
						AboutUS_Login.class);
				startActivity(intent);

			} else {
				if (JsonData.getApplication().getMemCache("SFZH") == "") {
					JsonData.getApplication().setMemCache("loginpage",
							"MainActivity_logo2");
					Intent intent = new Intent(MainActivity.this,
							AboutUS_Login.class);
					startActivity(intent);
				} else {
					Intent intent2 = new Intent(MainActivity.this,
							MainActivity_logo2.class);
					startActivity(intent2);
				}
			}
			break;
		case R.id.iv_logo3:
			// ÅÐ¶ÏÊÇ·ñµÇÂ¼
			if (JsonData.getApplication().getMemCache("SFZH") == null) {
				JsonData.getApplication().setMemCache("loginpage",
						"MainActivity_logo3");
				Intent intent = new Intent(MainActivity.this,
						AboutUS_Login.class);
				startActivity(intent);
			} else {
				if (JsonData.getApplication().getMemCache("SFZH") == "") {
					JsonData.getApplication().setMemCache("loginpage",
							"MainActivity_logo3");
					Intent intent = new Intent(MainActivity.this,
							AboutUS_Login.class);
					startActivity(intent);
				} else {
					Intent intent3 = new Intent(MainActivity.this,
							MainActivity_logo3.class);
					startActivity(intent3);
				}
			}

			break;
		case R.id.iv_logo4:
			// ÅÐ¶ÏÊÇ·ñµÇÂ¼
			if (JsonData.getApplication().getMemCache("SFZH") == null) {
				JsonData.getApplication().setMemCache("loginpage",
						"MainActivity_logo4");
				Intent intent = new Intent(MainActivity.this,
						AboutUS_Login.class);
				startActivity(intent);
			} else {
				if (JsonData.getApplication().getMemCache("SFZH") == "") {
					JsonData.getApplication().setMemCache("loginpage",
							"MainActivity_logo4");
					Intent intent = new Intent(MainActivity.this,
							AboutUS_Login.class);
					startActivity(intent);
				} else {
					Intent intent4 = new Intent(MainActivity.this,
							MainActivity_logo4.class);
					startActivity(intent4);
				}
			}
			break;
		case R.id.iv_logo5:
			// ÅÐ¶ÏÊÇ·ñµÇÂ¼
			if (JsonData.getApplication().getMemCache("SFZH") == null) {
				JsonData.getApplication().setMemCache("loginpage",
						"MainActivity_logo5");
				Intent intent = new Intent(MainActivity.this,
						AboutUS_Login.class);
				startActivity(intent);
			} else {
				if (JsonData.getApplication().getMemCache("SFZH") == "") {
					JsonData.getApplication().setMemCache("loginpage",
							"MainActivity_logo5");
					Intent intent = new Intent(MainActivity.this,
							AboutUS_Login.class);
					startActivity(intent);
				} else {
					Intent intent5 = new Intent(MainActivity.this,
							MainActivity_logo5.class);
					startActivity(intent5);
				}
			}
			break;
		case R.id.iv_logo6:
			// ÅÐ¶ÏÊÇ·ñµÇÂ¼
			if (JsonData.getApplication().getMemCache("SFZH") == null) {
				JsonData.getApplication().setMemCache("loginpage",
						"MainActivity_logo6");
				Intent intent = new Intent(MainActivity.this,
						AboutUS_Login.class);
				startActivity(intent);
			} else {
				if (JsonData.getApplication().getMemCache("SFZH") == "") {
					JsonData.getApplication().setMemCache("loginpage",
							"MainActivity_logo6");
					Intent intent = new Intent(MainActivity.this,
							AboutUS_Login.class);
					startActivity(intent);
				} else {
					Intent intent6 = new Intent(MainActivity.this,
							MainActivity_logo6.class);
					startActivity(intent6);
				}
			}

			break;
		case R.id.iv_logo7:
			Intent intent7 = new Intent(MainActivity.this,
					MainActivity_logo7.class);
			startActivity(intent7);
			break;
		case R.id.iv_logo8:
			Intent intent8 = new Intent(MainActivity.this,
					MainActivity_logo8.class);
			startActivity(intent8);
			break;
		case R.id.iv_logo9:
			Intent intent9 = new Intent(MainActivity.this,
					MainActivity_logo9.class);
			startActivity(intent9);
			break;
		case R.id.iv_logo10:
			Intent intent10 = new Intent(MainActivity.this,
					MainActivity_logo10.class);
			startActivity(intent10);
			break;
		case R.id.iv_logo11:
			Intent intent11 = new Intent(MainActivity.this,
					MainActivity_logo11.class);
			startActivity(intent11);
			break;
		case R.id.iv_logo12:
			// ÅÐ¶ÏÊÇ·ñµÇÂ¼
			if (JsonData.getApplication().getMemCache("SFZH") == null) {
				JsonData.getApplication().setMemCache("loginpage",
						"MainActivity_logo12");
				Intent intent = new Intent(MainActivity.this,
						AboutUS_Login.class);
				startActivity(intent);
			} else {
				if (JsonData.getApplication().getMemCache("SFZH") == "") {
					JsonData.getApplication().setMemCache("loginpage",
							"MainActivity_logo12");
					Intent intent = new Intent(MainActivity.this,
							AboutUS_Login.class);
					startActivity(intent);
				} else {
					Intent intent12 = new Intent(MainActivity.this,
							MainActivity_logo12.class);
					startActivity(intent12);
				}
			}
			break;
		default:
			break;
		}
	}

	private long firstime = 0;

	/**
	 * (non-Javadoc) back¼ü²¿·Ö
	 * 
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			long secondtime = System.currentTimeMillis();
			if (secondtime - firstime > 1000) {
				Toast.makeText(MainActivity.this, "ÔÙ°´Ò»´ÎÍË³ö", Toast.LENGTH_SHORT)
						.show();
				firstime = System.currentTimeMillis();
				return true;
			} else {
				MainActivity.this.finish();
				System.exit(0);
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
