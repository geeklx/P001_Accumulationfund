package com.accumulationfund.otheractivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.otheractivity.toast.myToast;
import com.liangxiao.accumulationfund.R;

public class MainActivity_logo11 extends Activity implements OnClickListener {
	// 公共
	private LinearLayout ll_back;// 返回
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right, ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;
	private Button btn_msg;
	private RadioGroup rg_1;
	private RadioButton rb_1, rb_2;
	private RadioGroup rg_11;
	private RadioButton rb_11, rb_22;
	@SuppressWarnings("unused")
	private String rb_1_content, rb_2_content;
	private EditText af_logo_11_unit1,// 主贷人公积金余额
			af_logo_11_unit2,// 主贷人补充公积金余额
			af_logo_11_unit3,// 主贷人月缴交额
			af_logo_11_unit4,// 主贷人年龄
			af_logo_11_unit5,// 参贷人公积金余额
			af_logo_11_unit6,// 参贷人补充公积金余额
			af_logo_11_unit7,// 参贷人月缴交额
			af_logo_11_unit8;// 房屋价格

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo11);
		// 初始化动画部分
		Constant.mType = AnimationType.ROTATE;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		// 个人信息
		initView();
	}

	private void initView() {
		handler = new myToast(MainActivity_logo11.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_11));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_right.setVisibility(View.GONE);

		btn_msg = (Button) findViewById(R.id.btn_msg);
		btn_msg.setOnClickListener(this);

		af_logo_11_unit1 = (EditText) findViewById(R.id.af_logo_11_unit1);
		af_logo_11_unit2 = (EditText) findViewById(R.id.af_logo_11_unit2);
		af_logo_11_unit3 = (EditText) findViewById(R.id.af_logo_11_unit3);
		af_logo_11_unit4 = (EditText) findViewById(R.id.af_logo_11_unit4);
		af_logo_11_unit5 = (EditText) findViewById(R.id.af_logo_11_unit5);
		af_logo_11_unit6 = (EditText) findViewById(R.id.af_logo_11_unit6);
		af_logo_11_unit7 = (EditText) findViewById(R.id.af_logo_11_unit7);
		af_logo_11_unit8 = (EditText) findViewById(R.id.af_logo_11_unit8);

		// RadioButton部分
		rg_1 = (RadioGroup) findViewById(R.id.rg_1);
		rb_1 = (RadioButton) findViewById(R.id.rb_1);
		rb_2 = (RadioButton) findViewById(R.id.rb_2);
		rg_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int id = group.getCheckedRadioButtonId();
				if (id == R.id.rb_1) {
					rb_1_content = rb_1.getText().toString();
				}
				if (id == R.id.rb_2) {
					rb_1_content = rb_2.getText().toString();
				}
			}
		});
		rg_11 = (RadioGroup) findViewById(R.id.rg_11);
		rb_11 = (RadioButton) findViewById(R.id.rb_11);
		rb_22 = (RadioButton) findViewById(R.id.rb_22);
		rg_11.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int id = group.getCheckedRadioButtonId();
				if (id == R.id.rb_11) {
					rb_2_content = rb_11.getText().toString();
				}
				if (id == R.id.rb_2) {
					rb_2_content = rb_22.getText().toString();
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			// 返回
			finish();
			break;
		case R.id.btn_msg:
			// 计算
			int id1 = rg_1.getCheckedRadioButtonId();
			int id11 = rg_11.getCheckedRadioButtonId();

			if (af_logo_11_unit1.getText().toString().equals("")) {
				handMessage(24);
				return;
			}
			if (af_logo_11_unit2.getText().toString().equals("")) {
				handMessage(25);
				return;
			}
			if (af_logo_11_unit3.getText().toString().equals("")) {
				handMessage(26);
				return;
			}
			if (af_logo_11_unit4.getText().toString().equals("")) {
				handMessage(27);
				return;
			}
			if (id1 == -1) {
				handMessage(28);
				return;
			}
			if (af_logo_11_unit5.getText().toString().equals("")) {
				handMessage(29);
				return;
			}
			if (af_logo_11_unit6.getText().toString().equals("")) {
				handMessage(30);
				return;
			}
			if (af_logo_11_unit7.getText().toString().equals("")) {
				handMessage(31);
				return;
			}
			if (af_logo_11_unit8.getText().toString().equals("")) {
				handMessage(32);
				return;
			}
			if (id11 == -1) {
				handMessage(33);
				return;
			}

			Intent intent = new Intent(MainActivity_logo11.this,
					MainActivity_logo11_result.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void handMessage(int i) {
		msg = handler.obtainMessage();
		msg.arg1 = i;
		handler.sendMessage(msg);
	}
}
