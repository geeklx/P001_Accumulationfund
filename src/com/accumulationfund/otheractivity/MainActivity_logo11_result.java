package com.accumulationfund.otheractivity;

import android.app.Activity;
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
import com.liangxiao.accumulationfund.R;

public class MainActivity_logo11_result extends Activity implements
		OnClickListener {
	// 公共
	private LinearLayout ll_back;// 返回
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right,ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;

	private EditText af_logo_11_r5,// 公积金可贷款额度
			af_logo_11_r6, // 补充公积金可贷款额度
			af_logo_11_r7,// 总贷款额度
			af_logo_11_r8;// 可贷款年限范围

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo11_result);
		// 初始化动画部分
		Constant.mType = AnimationType.SCALE;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		// 初始化
		initView();
	}

	private void initView() {
		handler = new myToast(MainActivity_logo11_result.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_11_r));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		ib_btn_right.setVisibility(View.GONE);

		af_logo_11_r5 = (EditText) findViewById(R.id.af_logo_11_r5);
		af_logo_11_r6 = (EditText) findViewById(R.id.af_logo_11_r6);
		af_logo_11_r7 = (EditText) findViewById(R.id.af_logo_11_r7);
		af_logo_11_r8 = (EditText) findViewById(R.id.af_logo_11_r8);

		af_logo_11_r5.setEnabled(false);
		af_logo_11_r6.setEnabled(false);
		af_logo_11_r7.setEnabled(false);
		af_logo_11_r8.setEnabled(false);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			// 返回
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
