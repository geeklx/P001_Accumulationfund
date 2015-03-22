package com.accumulationfund.otheractivity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.otheractivity.adapter.Adapter_Logo6;
import com.accumulationfund.otheractivity.bean.Bean_Logo6;
import com.accumulationfund.otheractivity.toast.myToast;
import com.accumulationfund.util.JsonData;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liangxiao.accumulationfund.R;

public class MainActivity_logo6 extends Activity implements OnClickListener {
	public static final int THE_PAGE = 1;
	public static final int THE_PAGESIZE = 10;
	public static final int TITLE_COUNT = 30;
	public static final String TAG1 = "MainActivity_logo6_Activity_json";
	// 公共
	private LinearLayout ll_back;// 返回
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right, ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;

	// 非公共
	private ListView id_listView;
	private Context mContext;
	private boolean load_page_size;
	private View footView;
	private int which_page;
	private Dialog progressDialog;
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo6);
		// 初始化动画部分
		Constant.mType = AnimationType.SCALE;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		// 还款明细
		initView();
		// 加载数据
		getJsonVolley(null);
	}

	// 获取json字符串
	public void getJsonVolley(final Adapter_Logo6 adapter_Logo6) {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		String JSONDateUrl = JsonData.SERVICE_URL
				+ "WebService.asmx/QueryHKXXBySFZH?SFZH="
				+ JsonData.getApplication().getMemCache("SFZH") + "&pageindex="
				+ which_page + "" + "&pagecount=" + THE_PAGESIZE;
		StringRequest stringRequest = new StringRequest(JSONDateUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println("Response=" + arg0);
						// 加载数据
						if (which_page == 1) {
							new MyTask1(arg0, null).execute();
						} else if (which_page > 1) {
							new MyTask1(arg0, adapter_Logo6).execute();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						System.out.println("error=" + arg0);
					}
				});

		requestQueue.add(stringRequest);

	}

	public class MyTask1 extends AsyncTask<Void, String, List<Bean_Logo6>> {
		private Adapter_Logo6 ftadapter;
		private String string;

		public MyTask1(String string, Adapter_Logo6 adapter) {
			if (adapter == null) {
				ftadapter = new Adapter_Logo6(mContext);
			} else {
				ftadapter = adapter;
			}
			this.string = string;
		}

		@Override
		protected void onPostExecute(List<Bean_Logo6> result) {
			super.onPostExecute(result);
			if (which_page == 1) {
				ftadapter.setData(result);
				id_listView.setAdapter(ftadapter);
			} else {
				ftadapter.notifyDataSetChanged();
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
		protected List<Bean_Logo6> doInBackground(Void... params) {
			try {
				// 获取json部分
				JSONObject jsonObject = new JSONObject(string);
				JSONArray data = jsonObject.getJSONArray("V_HKMXB");
				Log.i(TAG1, data.toString());
				// 判断进入条件
				if (data != null || data.toString() != "") {
					List<Bean_Logo6> list = new ArrayList<Bean_Logo6>();
					if (data.length() < JsonData.pageSize) {
						load_page_size = false;
						footView.setVisibility(View.GONE);
					}
					for (int i = 0; i < data.length(); i++) {
						JSONObject jsonObj = data.getJSONObject(i);
						Bean_Logo6 bean = new Bean_Logo6();
						String str = jsonObj.getString("KKRQ");
						str = str.substring(0, str.lastIndexOf("T"));
						bean.setData1(str);
						bean.setData2(jsonObj.getString("KKQS"));
						bean.setData3(jsonObj.getString("ZNJHJ"));
						bean.setData4(jsonObj.getString("BJHJ"));
						bean.setData5(jsonObj.getString("BXHJ"));
						bean.setData6(jsonObj.getString("LXHJ"));
						bean.setData7(jsonObj.getString("DKYE"));
						list.add(bean);

						if (which_page > 1) {
							ftadapter.addItem(bean);
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
		mContext = this;
		handler = new myToast(MainActivity_logo6.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_6));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_right.setVisibility(View.GONE);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		// 非公共
		inflater = LayoutInflater.from(mContext);
		id_listView = (ListView) findViewById(R.id.id_listView);
		footView = inflater.inflate(R.layout.af_activity_listview_footer, null);
		id_listView.addFooterView(footView, null, false);
		id_listView.setOnScrollListener(new MyOnItemScrollingListen());
		which_page = THE_PAGE;
		load_page_size = true;
		// 加载提示框
		progressDialog = new Dialog(MainActivity_logo6.this,
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
		System.out.println("没有Dialog啊!");
		progressDialog.show();

	}

	/**
	 * 滚动事件
	 */
	private class MyOnItemScrollingListen implements OnScrollListener {
		private int lastItemIndex;// 当前ListView中最后一个Item的索引

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// ListView 的FooterView也会算到visibleItemCount中去，所以要再减去一
			lastItemIndex = firstVisibleItem + visibleItemCount - 1 - 1;
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			HeaderViewListAdapter adapter6 = (HeaderViewListAdapter) id_listView
					.getAdapter();
			Adapter_Logo6 adapter_Logo6 = (Adapter_Logo6) adapter6
					.getWrappedAdapter();
			if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
				if (lastItemIndex == adapter6.getCount() - 1 - 1) {
					if (load_page_size) {
						which_page++;
						getJsonVolley(adapter_Logo6);
					} else {
						footView.setVisibility(View.GONE);
					}
				}
			}
		}
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
