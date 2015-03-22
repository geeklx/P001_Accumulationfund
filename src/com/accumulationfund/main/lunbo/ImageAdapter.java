package com.accumulationfund.main.lunbo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.liangxiao.accumulationfund.R;

public class ImageAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context context;
	private LayoutInflater mInflater;
	AQuery aQuery;
	// private static final int[] ids = { R.drawable.test1, R.drawable.test2,
	// R.drawable.test3 };
	private int[] ids;
	@SuppressWarnings("unused")
	private Activity activity;

	// public ImageAdapter(Context context, Activity activity) {
	// mContext = context;
	// this.activity = activity;
	// aQuery = new AQuery(context);
	// mInflater = (LayoutInflater) context
	// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// }

	public ImageAdapter(Context context, int[] ids, Activity activity) {
		this.context = context;
		aQuery = new AQuery(context);
		this.activity = activity;
		this.ids = ids;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE; //
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.af_main_lunbo_item, null);
		}

		aQuery.id(((ImageView) convertView.findViewById(R.id.imgView))).image(
				ids[position % ids.length]);

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Intent intent = new Intent(mContext,DetailActivity.class);
				// Bundle bundle = new Bundle();
				// bundle.putInt("image_id", ids[position%ids.length]);
				// intent.putExtras(bundle);
				// mContext.startActivity(intent);
				int a = position % ids.length + 1;
				if (a == 1) {
					// Intent intent = new Intent(context, MainActivity.class);
					// activity.startActivity(intent);
				} else if (a == 2) {
					// Intent intent = new Intent(context, MainActivity.class);
					// activity.startActivity(intent);
				} else if (a == 3) {
					// Intent intent = new Intent(context, MainActivity.class);
					// activity.startActivity(intent);
				} else if (a == 4) {
					// Intent intent = new Intent(context, MainActivity.class);
					// activity.startActivity(intent);
				}
				// Toast.makeText(mContext,
				// "点击了第 " + (position % ids.length + 1) + " 张图�?,
				// Toast.LENGTH_SHORT).show();

			}
		});
		return convertView;
	}
}
