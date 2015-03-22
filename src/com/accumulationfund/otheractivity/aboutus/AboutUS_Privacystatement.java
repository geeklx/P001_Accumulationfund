package com.accumulationfund.otheractivity.aboutus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.liangxiao.accumulationfund.R;

public class AboutUS_Privacystatement extends Activity implements
		OnClickListener {
	private ImageButton ib_btn_right,ib_btn_left;
	private TextView tv_middle, tv_aboutus_title;
	private LinearLayout ll_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_aboutus_privacystatement);
		// 初始化动画部分
		Constant.mType = AnimationType.FLIP_HORIZON;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		initView();
	}

	private void initView() {
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_aboutus_title = (TextView) findViewById(R.id.tv_aboutus_title);
		tv_aboutus_title.setText(getResources().getString(
				R.string.af_aboutus_privacystatement_content1));
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_left= (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		tv_middle.setText(getResources().getString(
				R.string.af_aboutus_privacystatement));
		ll_back.setVisibility(View.VISIBLE);
		ib_btn_right.setVisibility(View.GONE);

		ll_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_btn_left:
			finish();
			break;
		default:
			break;
		}
	}
}
