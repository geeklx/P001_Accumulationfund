package com.accumulationfund.otheractivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accumulationfund.model.IAlertDialogButtonListener;
import com.accumulationfund.otheractivity.toast.myToast;
import com.accumulationfund.util.Util;
import com.liangxiao.accumulationfund.R;

public class MainActivity_logo10 extends Activity implements OnClickListener {
	private ImageButton ib_btn_right, ib_btn_left;
	private TextView tv_middle, tv_right;
	private LinearLayout ll_back;
	private Button btn_count;
	private EditText af_logo_10_11, af_logo_10_22, af_logo_10_33,
			af_logo_10_44, af_logo_10_55;
	private Handler handler;
	private Message msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo10);
		// 缴存计算页面
		initView();
		handler = new myToast(MainActivity_logo10.this);
	}

	private void initView() {
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_right = (TextView) findViewById(R.id.tv_right);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		af_logo_10_11 = (EditText) findViewById(R.id.af_logo_10_11);
		af_logo_10_22 = (EditText) findViewById(R.id.af_logo_10_22);
		af_logo_10_33 = (EditText) findViewById(R.id.af_logo_10_33);
		af_logo_10_44 = (EditText) findViewById(R.id.af_logo_10_44);
		af_logo_10_44.setEnabled(false);
		af_logo_10_55 = (EditText) findViewById(R.id.af_logo_10_55);
		af_logo_10_55.setEnabled(false);
		btn_count = (Button) findViewById(R.id.btn_count);

		tv_middle.setText(getResources().getString(R.string.af_logo_10));
		tv_right.setVisibility(View.VISIBLE);
		tv_right.setText(getResources().getString(
				R.string.af_logo_10_title_right));

		ll_back.setVisibility(View.VISIBLE);
		ib_btn_right.setVisibility(View.GONE);
		ib_btn_left.setOnClickListener(this);
		ll_back.setOnClickListener(this);
		btn_count.setOnClickListener(this);
		tv_right.setOnClickListener(this);

		// handMessage(i);
	}

	private void handMessage(int i) {
		msg = handler.obtainMessage();
		msg.arg1 = i;
		handler.sendMessage(msg);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_count:
			if (af_logo_10_11.getText().length() <= 0) {
				handMessage(17);
				return;
			}
			if (af_logo_10_22.getText().length() <= 0) {
				handMessage(18);
				return;
			}
			if (af_logo_10_33.getText().length() <= 0) {
				handMessage(19);
				return;
			}
			int aa = Integer.valueOf(af_logo_10_22.getText().toString());
			int ab = Integer.valueOf(af_logo_10_33.getText().toString());
			if (aa < 5 || aa > 20) {
				handMessage(941);
				return;
			}

			if (ab < 5 || ab > 20) {
				handMessage(941);
				return;
			}
			// 接口部分af_logo_10_44 af_logo_10_55
			Double a = (Double.valueOf(af_logo_10_11.getText().toString()))
					* (0.01)
					* (Double.valueOf(af_logo_10_22.getText().toString()) + Double
							.valueOf(af_logo_10_33.getText().toString()));
			af_logo_10_44.setText(a.toString());
			Double b = (Double.valueOf(af_logo_10_11.getText().toString()))
					* (0.01)
					* Double.valueOf(af_logo_10_33.getText().toString());
			af_logo_10_55.setText(b.toString());
			/** 隐藏软键盘 **/
			View view = getWindow().peekDecorView();
			if (view != null) {
				InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
			}
			break;
		case R.id.ll_back:
			finish();
			break;
		case R.id.tv_right:
			// 显示dialog
			Util.showDialog(MainActivity_logo10.this,
					getResources()
							.getString(R.string.af_logo_10_dialog_content),
					mBtnOkDeleteWordListener);
			break;
		default:
			break;
		}
	}

	/**
	 * 自定义的AlertDialog事件响应 查看操作
	 */
	private IAlertDialogButtonListener mBtnOkDeleteWordListener = new IAlertDialogButtonListener() {

		@Override
		public void onClick() {
			// 查看

		}
	};

}
