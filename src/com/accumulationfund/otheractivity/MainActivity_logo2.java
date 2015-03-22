package com.accumulationfund.otheractivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
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

public class MainActivity_logo2 extends Activity implements OnClickListener {
	// ����
	private LinearLayout ll_back;// ����
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right, ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;
	// �ǹ���
	@SuppressWarnings("unused")
	private ImageButton iv_refresh;
	private Button btn_msg;// ������λ
	private TextView tv_name, tv_time;// ��,ʱ��
	private EditText af_logo_2_11,// �˻�״̬
			af_logo_2_22, // �˻����
			af_logo_2_33,// �½ɴ��
			af_logo_2_44,// ĩ�νɴ���
			af_logo_2_55, // �������˺�
			af_logo_2_66;// ��������
	private PullToRefreshScrollView mPullPullToRefreshScrollView;
	@SuppressWarnings("unused")
	private ScrollView mScrollView;
	private Context mContext;
	private Dialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo2);
		// ��ʼ����������
		Constant.mType = AnimationType.HORIZION_LEFT;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		// ������Ϣ
		initView();
		// ˢ��
		refresh();
	}

	private void initView() {
		mContext = this;
		handler = new myToast(MainActivity_logo2.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_2_title));
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
		af_logo_2_11 = (EditText) findViewById(R.id.af_logo_2_11);
		af_logo_2_22 = (EditText) findViewById(R.id.af_logo_2_22);
		af_logo_2_33 = (EditText) findViewById(R.id.af_logo_2_33);
		af_logo_2_44 = (EditText) findViewById(R.id.af_logo_2_44);
		af_logo_2_55 = (EditText) findViewById(R.id.af_logo_2_55);
		af_logo_2_66 = (EditText) findViewById(R.id.af_logo_2_66);
		af_logo_2_11.setEnabled(false);
		af_logo_2_22.setEnabled(false);
		af_logo_2_33.setEnabled(false);
		af_logo_2_44.setEnabled(false);
		af_logo_2_55.setEnabled(false);
		af_logo_2_66.setEnabled(false);
		btn_msg = (Button) findViewById(R.id.btn_msg_logo2);

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

		// ������ʾ��
		progressDialog = new Dialog(MainActivity_logo2.this,
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
			// ����
			finish();
			break;
		case R.id.iv_refresh:
			// ˢ��
			refresh();
			break;
		default:
			break;
		}
	}

	private void refresh() {
		getJsonVolley();
	}

	// ��ȡjson�ַ���
	public void getJsonVolley() {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		String JSONDateUrl = JsonData.SERVICE_URL
				+ "WebService.asmx/QueryGRXXBySFZH?SFZH="
				+ JsonData.getApplication().getMemCache("SFZH");
		// ���ݸ�����URL�½�һ������
		StringRequest stringRequest = new StringRequest(JSONDateUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println("Response=" + arg0);
						// ��������
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

	@SuppressLint("SimpleDateFormat")
	public class MyTask1 extends AsyncTask<Void, String, JSONObject> {
		private String string;

		public MyTask1(String string) {
			this.string = string;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);
			String a = "";
			System.out.println(a);
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
			// String dataf = sdf.format(result.getString("GRQ_KHRQ"));
			try {
				tv_name.setText(result.getString("GRQ_XM"));// ����
				tv_time.setText("�����ݽ�ֹ����:" + result.getString("JZYF"));// ĩ�νɴ���
				af_logo_2_11.setText(result.getString("ZHZT"));// �˻�״̬
				af_logo_2_22.setText(result.getString("ZHYE"));// �˻����
				af_logo_2_33.setText(result.getString("GRQ_YJE"));// �½ɴ��
				af_logo_2_44.setText(result.getString("JZYF"));// ĩ�νɴ���
				af_logo_2_55.setText(result.getString("GRQ_BH"));// �������˺�
				btn_msg.setText(result.getString("DWMC"));// ������λ
				String str = result.getString("GRQ_KHRQ");
				str = str.substring(0, str.lastIndexOf("T"));
				af_logo_2_66.setText(str);// ��������

			} catch (JSONException e) {
				e.printStackTrace();
			}
			progressDialog.dismiss();
			CloseScrollView();
		}

		@Override
		protected void onPreExecute() {
			// ������ʾ��
			// dialog = new
			// ProgressDialog(Huishenghuo_Huijingxuan_Activity.this);
			// dialog.setTitle("��ʾ");
			// dialog.setMessage("�������أ����Ժ�....");
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
