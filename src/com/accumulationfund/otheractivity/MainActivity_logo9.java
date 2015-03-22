package com.accumulationfund.otheractivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.otheractivity.logo9.fragment.MyFrament1;
import com.accumulationfund.otheractivity.logo9.fragment.MyFrament2;
import com.accumulationfund.otheractivity.toast.myToast;
import com.liangxiao.accumulationfund.R;

public class MainActivity_logo9 extends FragmentActivity implements
		OnClickListener {
	// 公共
	private LinearLayout ll_back;// 返回
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right,ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;

	// 非公共
	private RelativeLayout RelativeLayout1, RelativeLayout2;
	private TextView textView1, textView2;
	private ViewPager viewPager;
	private int[] selectList;
	private TextView[] textViewList;
	private int selectID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo9);
		// 初始化动画部分
		Constant.mType = AnimationType.ALPHA;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		initLayout();
		initData();
	}

	private void initLayout() {
		handler = new myToast(MainActivity_logo9.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_logo_9));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		tv_right.setText(getResources().getString(R.string.af_logo_9));
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_right.setVisibility(View.GONE);
		ib_btn_left= (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		RelativeLayout1 = (RelativeLayout) findViewById(R.id.RelativeLayout1);
		RelativeLayout2 = (RelativeLayout) findViewById(R.id.RelativeLayout2);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		viewPager = (ViewPager) findViewById(R.id.viewPager);

	}

	private void initData() {
		selectList = new int[] { 0, 1 };// 0表示选中，1表示未选中(默认第一个选中)
		textViewList = new TextView[] { textView1, textView2 };
		RelativeLayout1.setOnClickListener(this);
		RelativeLayout2.setOnClickListener(this);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(changeListener);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			// 返回
			finish();
			break;
		case R.id.RelativeLayout1:
			if (selectID == 0) {
				return;
			} else {
				setSelectedTitle(0);
				viewPager.setCurrentItem(0);
			}
			break;
		case R.id.RelativeLayout2:
			if (selectID == 1) {
				return;
			} else {
				setSelectedTitle(1);
				viewPager.setCurrentItem(1);
			}
			break;
		default:
			break;
		}
	}

	private FragmentPagerAdapter adapter = new FragmentPagerAdapter(
			getSupportFragmentManager()) {

		public int getCount() {
			return selectList.length;
		}

		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = new MyFrament1();
				break;
			case 1:
				fragment = new MyFrament2();
				break;
			}
			return fragment;
		}
	};

	private SimpleOnPageChangeListener changeListener = new SimpleOnPageChangeListener() {
		public void onPageSelected(int position) {
			setSelectedTitle(position);
		}
	};

	/**
	 * 当前UI改变时，修改TITLE选中项
	 * 
	 * @param position
	 *            0 1
	 */
	private void setSelectedTitle(int position) {
		for (int i = 0; i < selectList.length; i++) {
			if (selectList[i] == 0) {
				selectList[i] = 1;
				textViewList[i].setVisibility(View.INVISIBLE);
			}
		}
		selectList[position] = 0;
		textViewList[position].setVisibility(View.VISIBLE);
		selectID = position;
	}

	@SuppressWarnings("unused")
	private void handMessage(int i) {
		msg = handler.obtainMessage();
		msg.arg1 = i;
		handler.sendMessage(msg);
	}
}
