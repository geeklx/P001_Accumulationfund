package com.accumulationfund.otheractivity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.otheractivity.adapter.Adapter_Logo3;
import com.accumulationfund.otheractivity.bean.Bean_Logo3;
import com.accumulationfund.otheractivity.toast.myToast;
import com.accumulationfund.util.JsonData;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liangxiao.accumulationfund.R;

@SuppressLint("WorldReadableFiles")
public class MainActivity_logo3 extends Activity implements OnClickListener {
	public static final int THE_PAGE = 1;
	public static final String TAG1 = "MainActivity_logo3_Activity_json";
	// 公共
	private LinearLayout ll_back;// 返回
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right, ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;

	// 非公共
	@SuppressWarnings("unused")
	private TextView tv_company;
	private ListView id_listView;
	// private Adapter_Logo3 adapter;
	private Context mContext;
	@SuppressWarnings("unused")
	private boolean load_page_size;
	private View footView;
	private int which_page;
	private Dialog progressDialog;
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo3);
		// 初始化动画部分
		Constant.mType = AnimationType.HORIZION_RIGHT;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		// 个人信息
		initView();
		// 加载数据
		getJsonVolley();
	}

	// 获取json字符串
	public void getJsonVolley() {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		String JSONDateUrl = JsonData.SERVICE_URL
				+ "WebService.asmx/QueryGRJCMXBySFZH?SFZH="
				+ JsonData.getApplication().getMemCache("SFZH");
		StringRequest stringRequest = new StringRequest(JSONDateUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println("Response=" + arg0);
						// 加载数据
						new MyTask1(arg0, null).execute();
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
	public class MyTask1 extends AsyncTask<Void, String, List<Bean_Logo3>> {
		private Adapter_Logo3 adapter_Logo3;
		private String string;

		public MyTask1(String string, Adapter_Logo3 adapter) {
			if (adapter == null) {
				adapter_Logo3 = new Adapter_Logo3(mContext);
			} else {
				adapter_Logo3 = adapter;
			}
			this.string = string;
		}

		@Override
		protected void onPostExecute(List<Bean_Logo3> result) {
			super.onPostExecute(result);
			if (which_page == 1) {
				adapter_Logo3.setData(result);
				id_listView.setAdapter(adapter_Logo3);
				footView.setVisibility(View.GONE);
			} else {
				adapter_Logo3.notifyDataSetChanged();
			}
			if (result != null) {
				if (result.size() == 0) {
					load_page_size = false;
					footView.setVisibility(View.GONE);
				}
			} else {
				load_page_size = false;
				footView.setVisibility(View.GONE);
			}

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
		protected List<Bean_Logo3> doInBackground(Void... params) {
			try {
				// 获取json部分
				JSONObject jsonObject = new JSONObject(string);
				JSONArray data = jsonObject.getJSONArray("tables");
				Log.i(TAG1, data.toString());
				// 判断进入条件
				if (data != null || data.toString() != "") {
					List<Bean_Logo3> list = new ArrayList<Bean_Logo3>();
					if (data.length() < JsonData.pageSize) {
						load_page_size = false;
						footView.setVisibility(View.GONE);
					}
					for (int i = 0; i < data.length(); i++) {
						JSONObject jsonObj = data.getJSONObject(i);
						Bean_Logo3 bean = new Bean_Logo3();
						bean.setName(jsonObj.getString("GRM_DWMC"));
						bean.setMoney(jsonObj.getString("GRM_FSJE"));
						bean.setTime(jsonObj.getString("JCNY"));
						list.add(bean);

						if (which_page > 1) {
							adapter_Logo3.addItem(bean);
						}
					}
					return list;
				}
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

	private void initView() {
		// 公共
		handler = new myToast(MainActivity_logo3.this);
		mContext = this;
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_3_title));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		ib_btn_right.setVisibility(View.GONE);
		// 非公共
		inflater = LayoutInflater.from(mContext);
		tv_company = (TextView) findViewById(R.id.tv_company);
		id_listView = (ListView) findViewById(R.id.id_listView);
		footView = inflater.inflate(R.layout.af_activity_listview_footer, null);
		id_listView.addFooterView(footView, null, false);
		which_page = THE_PAGE;
		load_page_size = true;
		// 加载提示框
		progressDialog = new Dialog(MainActivity_logo3.this,
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
