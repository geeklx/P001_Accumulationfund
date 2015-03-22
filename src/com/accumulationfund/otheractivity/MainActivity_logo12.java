package com.accumulationfund.otheractivity;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accumulationfund.otheractivity.toast.myToast;
import com.accumulationfund.util.JsonData;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liangxiao.accumulationfund.R;

public class MainActivity_logo12 extends Activity implements OnClickListener,
		TextWatcher {
	private ImageButton ib_btn_right,ib_btn_left;
	private TextView tv_middle, tv_right;
	private LinearLayout ll_back;
	private Button btn_finish;
	private EditText ed_original_pwd, ed_new_pwd, ed_confirm_pwd;
	private Handler handler;
	private Message msg;
	private Context mContext;
	private Dialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo12);
		// 缴存计算页面
		initView();
		handler = new myToast(MainActivity_logo12.this);
		if (ed_original_pwd.getText().length() > 0) {
			btn_finish.setEnabled(true);
			btn_finish.setBackgroundResource(R.drawable.btn_able);
		}
	}

	private void initView() {
		mContext = this;
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_right = (TextView) findViewById(R.id.tv_right);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);

		ed_original_pwd = (EditText) findViewById(R.id.ed_original_pwd);
		ed_original_pwd.addTextChangedListener(this);
		ed_new_pwd = (EditText) findViewById(R.id.ed_new_pwd);
		ed_confirm_pwd = (EditText) findViewById(R.id.ed_confirm_pwd);

		btn_finish = (Button) findViewById(R.id.btn_finish);
		btn_finish.setEnabled(false);
		btn_finish.setBackgroundResource(R.drawable.btn_disenable);

		tv_middle.setText(getResources().getString(R.string.af_logo_12));
		tv_right.setVisibility(View.GONE);
		ll_back.setVisibility(View.VISIBLE);
		ib_btn_right.setVisibility(View.GONE);
		ib_btn_left= (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		ll_back.setOnClickListener(this);
		btn_finish.setOnClickListener(this);

		// 加载提示框
		progressDialog = new Dialog(MainActivity_logo12.this,
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

	private void handMessage(int i) {
		msg = handler.obtainMessage();
		msg.arg1 = i;
		handler.sendMessage(msg);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_finish:
			if (ed_original_pwd.getText().length() <= 0) {
				handMessage(8);
				return;
			}
			if (ed_new_pwd.getText().length() <= 0) {
				handMessage(9);
				return;
			}
			if (ed_new_pwd.getText().length() < 6) {
				handMessage(94);
				return;
			}
			if (ed_confirm_pwd.getText().length() < 6) {
				handMessage(94);
				return;
			}
			if (ed_confirm_pwd.getText().length() <= 0) {
				handMessage(10);
				return;
			}
			if (!(ed_new_pwd.getText().toString().trim()).equals(ed_confirm_pwd
					.getText().toString().trim())) {
				handMessage(99);
				return;
			}
			progressDialog.show();
			// 接口部分
			getJsonVolley();
			break;
		case R.id.ll_back:
			finish();
			break;
		default:
			break;
		}
	}

	// 获取json字符串
	public void getJsonVolley() {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		String JSONDateUrl =  JsonData.SERVICE_URL+"WebService.asmx/XGMMBySFZH?SFZH="
				+ JsonData.getApplication().getMemCache("SFZH")
				+ "&MM="
				+ ed_original_pwd.getText().toString()
				+ "&newMM="
				+ ed_new_pwd.getText().toString();
		// 根据给定的URL新建一个请求
		StringRequest stringRequest = new StringRequest(JSONDateUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println("Response=" + arg0);
						// 加载数据
						arg0 = arg0.replaceAll("\"", "");
						System.out.println("Response=" + arg0);
						if (arg0.equals("1")) {
							msg = handler.obtainMessage();
							msg.arg1 = 91;
							handler.sendMessage(msg);
							progressDialog.dismiss();
						}
						if (arg0.equals("0")) {
							msg = handler.obtainMessage();
							msg.arg1 = 91;
							handler.sendMessage(msg);
							progressDialog.dismiss();
						}
						if (arg0.equals("2")) {
							msg = handler.obtainMessage();
							msg.arg1 = 91;
							handler.sendMessage(msg);
							progressDialog.dismiss();
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

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// 判断输入框的状态显示部分
		if (s.length() > 0) {
			btn_finish.setEnabled(true);
			btn_finish.setBackgroundResource(R.drawable.btn_able);
		} else {
			btn_finish.setEnabled(false);
			btn_finish.setBackgroundResource(R.drawable.btn_disenable);
		}
	}
}
