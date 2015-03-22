package com.accumulationfund.otheractivity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.otheractivity.adapter.Adapter_Logo9;
import com.accumulationfund.otheractivity.bean.Bean_Logo9;
import com.accumulationfund.otheractivity.toast.myToast;
import com.liangxiao.accumulationfund.R;

public class MainActivity_logo9_fragment2Detail2 extends Activity implements
		OnClickListener {
	// ����
	private LinearLayout ll_back;// ����
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right, ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;

	// �ǹ���
	private Button btn_msg;
	private Object dkje,// ����������ܶ�
			dknx,// �������������
			nlv;// ���������������
	private ListView id_listview;
	private Context mContext;
	private Dialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo9_fragment2detail_detail);
		// ��ʼ����������
		Constant.mType = AnimationType.ALPHA;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		initLayout();
		Intent intent = getIntent();
		dkje = intent.getStringExtra("dkje");// ����������ܶ�
		dknx = intent.getStringExtra("dknx");// �������������
		nlv = intent.getStringExtra("nlv");// ���������������
		new MyTask1(dkje.toString(), dknx.toString(), nlv.toString(), null)
				.execute();
	}

	public class MyTask1 extends AsyncTask<Void, String, List<Bean_Logo9>> {
		private Adapter_Logo9 ftadapter;
		private String dkje;
		private String dknx;
		private String nlv;

		public MyTask1(String dkje, String dknx, String nlv,
				Adapter_Logo9 adapter) {
			if (adapter == null) {
				ftadapter = new Adapter_Logo9(mContext);
			} else {
				ftadapter = adapter;
			}
			this.dkje = dkje;
			this.dknx = dknx;
			this.nlv = nlv;
		}

		@Override
		protected void onPostExecute(List<Bean_Logo9> result) {
			super.onPostExecute(result);
			ftadapter.setData(result);
			id_listview.setAdapter(ftadapter);
			progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected List<Bean_Logo9> doInBackground(Void... params) {
			try {
				int gjj_month = Integer.valueOf(dknx) * 12;// �ܹ�������120=12*10year
				// ��N��=(dkje- dkje/ (dknx* 12) * (N - 1)) * ylv
				List<Bean_Logo9> list = new ArrayList<Bean_Logo9>();
				for (int i = 0; i < gjj_month; i++) {
					// ��N�µ��»���Ϣ
					Double a = Double.valueOf(dkje);
					Double b = Double.valueOf(dknx);
					Double c = Double.valueOf(nlv);
					Double month_pay = (a - a / (b * 12) * (i + 1 - 1)) * c
							/ 12;
					Double month_pay2 = Math.round(month_pay * 10) / 10d;
					Bean_Logo9 bean = new Bean_Logo9();
					bean.setMonth("��" + (i + 1) + "��");
					bean.setMoney(month_pay2.toString() + "Ԫ");
					list.add(bean);
				}
				return list;
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

	private void initLayout() {
		mContext = this;
		handler = new myToast(MainActivity_logo9_fragment2Detail2.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_9_detail));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		ib_btn_right.setVisibility(View.GONE);
		//
		btn_msg = (Button) findViewById(R.id.btn_msg);
		btn_msg.setOnClickListener(this);

		id_listview = (ListView) findViewById(R.id.id_listview);
		// ������ʾ��
		progressDialog = new Dialog(MainActivity_logo9_fragment2Detail2.this,
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			// ����
			finish();
			break;
		case R.id.btn_msg:
			// ����鿴
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
