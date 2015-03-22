package com.accumulationfund.otheractivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.otheractivity.bean.Bean_Logo1;
import com.accumulationfund.otheractivity.toast.myToast;
import com.liangxiao.accumulationfund.R;

public class MainActivity_logo1Detail extends Activity implements
		OnClickListener {
	public static final int THE_PAGE = 1;
	public static final String TAG1 = "MainActivity_logo1Detail_Activity_json";
	// 公共
	private LinearLayout ll_back;// 返回
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right,ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;
	// 非公共
	private Bean_Logo1 bean;
	private TextView tv_info;
	private TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo1_detail);
		// 初始化动画部分
		Constant.mType = AnimationType.ALPHA;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		// 新闻详情
		initView();
		// 加载信息
		setText();
	}

	private void setText() {
		bean = getIntent().getParcelableExtra("info");
		tv_info.setText(Html.fromHtml(bean.getContent()));
		tv_title.setText(bean.getName());
	}

	private void initView() {
		handler = new myToast(MainActivity_logo1Detail.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_1));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_left= (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_right.setVisibility(View.GONE);
		// 非公共
		tv_info = (TextView) findViewById(R.id.tv_info);
		tv_title = (TextView) findViewById(R.id.tv_title);

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
