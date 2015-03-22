package com.accumulationfund.otheractivity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.otheractivity.adapter.Adapter_Logo4;
import com.accumulationfund.otheractivity.bean.Bean_Logo4;
import com.accumulationfund.otheractivity.toast.myToast;
import com.accumulationfund.util.JsonData;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liangxiao.accumulationfund.R;

public class MainActivity_logo4 extends Activity implements OnClickListener {
	// 公共
	private LinearLayout ll_back;// 返回
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right, ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;
	private Context mContext;
	private ListView id_listView;
	private Adapter_Logo4 adapter_Logo4;
	private Dialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo44);
		// 初始化动画部分
		Constant.mType = AnimationType.HORIZON_CROSS;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		// 个人信息
		initView();
		getJsonVolley();
	}

	private void initView() {
		mContext = this;
		handler = new myToast(MainActivity_logo4.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_4));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_right.setVisibility(View.GONE);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		id_listView = (ListView) findViewById(R.id.id_listView);
		// 加载提示框
		progressDialog = new Dialog(MainActivity_logo4.this,
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

	// 获取json字符串
	public void getJsonVolley() {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		String JSONDateUrl = JsonData.SERVICE_URL
				+ "WebService.asmx/QueryDKXXBySFZH?SFZH="
				+ JsonData.getApplication().getMemCache("SFZH");
		// 根据给定的URL新建一个请求
		StringRequest stringRequest = new StringRequest(JSONDateUrl,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println("Response=" + arg0);
						// 加载数据
						try {
							JSONObject jsonObject = new JSONObject(arg0);
							List<Bean_Logo4> mList = new ArrayList<Bean_Logo4>();
							mList = getBean4(jsonObject);
							adapter_Logo4 = new Adapter_Logo4(mContext, mList);
							id_listView.setAdapter(adapter_Logo4);
							progressDialog.dismiss();
						} catch (JSONException e) {
							e.printStackTrace();
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

	private List<Bean_Logo4> getBean4(JSONObject result) {
		List<Bean_Logo4> list = new ArrayList<Bean_Logo4>();

		try {
			JSONArray jsonArray = result.getJSONArray("tables");
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			Bean_Logo4 bean = new Bean_Logo4();
			if (result != null) {
				bean.setNum1(jsonObject.getString("DKHTBH"));// 贷款合同编号
				bean.setNum2(jsonObject.getString("HKYHZH"));// 还款账号
				bean.setNum3(jsonObject.getString("GRXM"));// 借款人姓名
				bean.setNum4(jsonObject.getString("SFZH"));// 身份证号
				bean.setNum5(jsonObject.getString("DFXM"));// 配偶姓名
				bean.setNum6(jsonObject.getString("PSFZH"));// 配偶身份证
				bean.setNum7(jsonObject.getString("ZHMC"));// 储蓄还款账户
				bean.setNum8(jsonObject.getString("DKNX"));// 贷款年限
				bean.setNum9(jsonObject.getString("DKJE"));// 贷款金额
				String str = jsonObject.getString("YHXZRQ");
				str = str.substring(0, str.lastIndexOf("T"));
				bean.setNum10(str);// 放款日期
				bean.setNum11(jsonObject.getString("YJHKE"));// 月还款额
				bean.setNum12(jsonObject.getString("ZXLL"));// 贷款年利率
				bean.setNum13(jsonObject.getString("YHLXLJ"));// 已还利息
				bean.setNum14(jsonObject.getString("DKYE"));// 贷款余额
				bean.setNum15(jsonObject.getString("ZHZT"));// 还款状态
				bean.setNum16(jsonObject.getString("YHBJLJ"));// 已还本金
				bean.setNum17(jsonObject.getString("DQQS"));// 已还期数
				bean.setNum18(jsonObject.getString("HKYQS"));// 当前逾期期数
				bean.setNum19(jsonObject.getString("STYH"));// 受托银行
				bean.setNum20(jsonObject.getString("DBFS"));// 担保方式
				bean.setNum21(jsonObject.getString("HKFS"));// 还款方式
				list.add(bean);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return list;
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
