package com.accumulationfund.otheractivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.otheractivity.toast.myToast;
import com.accumulationfund.util.JsonData;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liangxiao.accumulationfund.R;
import com.liangxiao.swiperefresh.PullToRefreshBase;
import com.liangxiao.swiperefresh.PullToRefreshBase.OnRefreshListener;
import com.liangxiao.swiperefresh.PullToRefreshScrollView;

public class MainActivity_logo5 extends Activity implements OnClickListener {
	// 公共
	private LinearLayout ll_back;// 返回
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right, ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;
	// 非公共
	@SuppressWarnings("unused")
	private ImageButton iv_refresh;
	private Button btn_msg;// 所属单位
	private TextView tv_name, tv_time;// 人,时间
	private EditText ed_logo5_1,// 还款日期
			ed_logo5_2, // 扣银行卡金额
			ed_logo5_3,// 后公积金账户金额
			ed_logo5_4,// 贷款余额
			ed_logo5_5, // 应还金额合计
			ed_logo5_6,// 本次还本金
			ed_logo5_7,// 本次还利息
			ed_logo5_8,// 已还金额合计
			ed_logo5_9,// 欠本息合计
			ed_logo5_10;// 欠滞纳金

	private PullToRefreshScrollView mPullPullToRefreshScrollView;
	@SuppressWarnings("unused")
	private ScrollView mScrollView;
	private Context mContext;
	private Dialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo52);
		// 初始化动画部分
		Constant.mType = AnimationType.ROTATE;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		// 个人信息
		initView();
		// 刷新
		refresh();
	}

	private void initView() {
		mContext = this;
		handler = new myToast(MainActivity_logo5.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_5));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_right.setVisibility(View.GONE);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		tv_time = (TextView) findViewById(R.id.tv_time);
		tv_name = (TextView) findViewById(R.id.tv_name);
		iv_refresh = (ImageButton) findViewById(R.id.iv_refresh);
		ed_logo5_1 = (EditText) findViewById(R.id.ed_logo5_1);
		ed_logo5_2 = (EditText) findViewById(R.id.ed_logo5_2);
		ed_logo5_3 = (EditText) findViewById(R.id.ed_logo5_3);
		ed_logo5_4 = (EditText) findViewById(R.id.ed_logo5_4);
		ed_logo5_5 = (EditText) findViewById(R.id.ed_logo5_5);
		ed_logo5_6 = (EditText) findViewById(R.id.ed_logo5_6);
		ed_logo5_7 = (EditText) findViewById(R.id.ed_logo5_7);
		ed_logo5_8 = (EditText) findViewById(R.id.ed_logo5_8);
		ed_logo5_9 = (EditText) findViewById(R.id.ed_logo5_9);
		ed_logo5_10 = (EditText) findViewById(R.id.ed_logo5_10);
		ed_logo5_1.setEnabled(false);
		ed_logo5_2.setEnabled(false);
		ed_logo5_3.setEnabled(false);
		ed_logo5_4.setEnabled(false);
		ed_logo5_5.setEnabled(false);
		ed_logo5_6.setEnabled(false);
		ed_logo5_7.setEnabled(false);
		ed_logo5_8.setEnabled(false);
		ed_logo5_9.setEnabled(false);
		ed_logo5_10.setEnabled(false);
		btn_msg = (Button) findViewById(R.id.btn_msg);

		mPullPullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_refresh_scrollview);
		mPullPullToRefreshScrollView
				.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ScrollView> refreshView) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								refresh();
							}
						}).start();
					}

				});

		mScrollView = mPullPullToRefreshScrollView.getRefreshableView();

		// 加载提示框
		progressDialog = new Dialog(MainActivity_logo5.this,
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

	private void CloseScrollView() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				mPullPullToRefreshScrollView.onRefreshComplete();
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
		case R.id.iv_refresh:
			// 刷新
			refresh();
			break;
		default:
			break;
		}
	}

	private void refresh() {
		getJsonVolley();
	}

	// 获取json字符串
	public void getJsonVolley() {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		String JSONDateUrl = JsonData.SERVICE_URL
				+ "WebService.asmx/QueryHKXXBySFZH_New?SFZH="
				+ JsonData.getApplication().getMemCache("SFZH");
		// 根据给定的URL新建一个请求
		StringRequest stringRequest = new StringRequest(JSONDateUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println("Response=" + arg0);
						// 加载数据
						new MyTask1(arg0).execute();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						System.out.println("error=" + arg0);
					}
				});

		requestQueue.add(stringRequest);
	}

	public class MyTask1 extends AsyncTask<Void, String, JSONObject> {
		private String string;

		public MyTask1(String string) {
			this.string = string;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);
			try {

				tv_name.setText(result.getString("GRXM"));// 姓名
				String str = result.getString("XTRQ");
				str = str.substring(0, str.lastIndexOf("T"));
				tv_time.setText("本数据截止日期:" + str);// 末次缴存年
				String str2 = result.getString("KKRQ");
				str2 = str2.substring(0, str2.lastIndexOf("T"));
				ed_logo5_1.setText(str2);// 还款日期
				ed_logo5_2.setText(result.getString("BCZHJE") + "元");// 扣银行卡金额
				ed_logo5_3.setText(result.getString("BCQYJE") + "元");// 扣公积金账户金额
				ed_logo5_4.setText(result.getString("DKYE") + "元");// 贷款余额
				ed_logo5_5.setText(result.getString("BXHJ") + "元");// 应还金额合计
				ed_logo5_6.setText(result.getString("BJHJ") + "元");// 本次还本金
				ed_logo5_7.setText(result.getString("LXHJ") + "元");// 本次还利息
				ed_logo5_8.setText(result.getString("YHBXHJ") + "元");// 已还金额合计
				ed_logo5_9.setText(result.getString("QBXHJ") + "元");// 欠本息合计
				ed_logo5_10.setText(result.getString("QZNJ") + "元");// 欠滞纳金
				btn_msg.setText(result.getString("SSDW"));// 所属单位
			} catch (JSONException e) {
				e.printStackTrace();
			}
			progressDialog.dismiss();
			CloseScrollView();
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
		protected JSONObject doInBackground(Void... params) {
			try {
				JSONObject jsonObject = new JSONObject(string);
				JSONArray jsonArray = jsonObject.getJSONArray("tables");
				JSONObject result = jsonArray.getJSONObject(0);
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

	@SuppressWarnings("unused")
	private void handMessage(int i) {
		msg = handler.obtainMessage();
		msg.arg1 = i;
		handler.sendMessage(msg);
	}
}
