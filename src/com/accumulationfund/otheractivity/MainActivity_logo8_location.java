package com.accumulationfund.otheractivity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.accumulationfund.main.animation.Constant;
import com.accumulationfund.main.animation.SwitchAnimationUtil;
import com.accumulationfund.main.animation.SwitchAnimationUtil.AnimationType;
import com.accumulationfund.orientationlistener.MyOrientationListener;
import com.accumulationfund.orientationlistener.MyOrientationListener.onOrientationListener;
import com.accumulationfund.otheractivity.bean.Bean_Logo8;
import com.accumulationfund.otheractivity.toast.myToast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.liangxiao.accumulationfund.R;

public class MainActivity_logo8_location extends Activity implements
		OnClickListener {
	// ����
	private LinearLayout ll_back;// ����
	private TextView tv_middle, tv_right;// title,tv_right
	private ImageButton ib_btn_right, ib_btn_left;// btn_right

	private Handler handler;
	private Message msg;
	// �ǹ���
	private MapView mMapView = null;
	private BaiduMap mBaiduMap;
	private ImageView iv_location;
	private Context mContext;

	// ��λ���
	private LocationClient mLocationClient;
	private MyLocationListener myLocationListener;
	private boolean isFirstIn = true;
	private BDLocation loc;// ��ȡ��ǰ�Ķ�λ��Ϣ
	// �Զ��嶨λͼ��
	private BitmapDescriptor mIconLocation;
	// ���򴫸���
	private MyOrientationListener myOrientationListener;
	private float mCurrentX;
	private LocationMode mLocationMode;
	@SuppressWarnings("unused")
	private String latitude;
	@SuppressWarnings("unused")
	private String longitude;
	// ������Marker
	private BitmapDescriptor mMarker;
	private LinearLayout mMarkerLy;// marker�Ĳ���
	@SuppressWarnings("unused")
	private String Accumulationfundlat;
	@SuppressWarnings("unused")
	private String Accumulationfundlon;

	private List<Bean_Logo8> infos = new ArrayList<Bean_Logo8>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_logo8_detail);
		// ��ʼ����������
		Constant.mType = AnimationType.FLIP_HORIZON;
		new SwitchAnimationUtil().startAnimation(getWindow().getDecorView(),
				Constant.mType);
		// ����
		// Intent intent = this.getIntent();
		// bean = intent.getParcelableExtra("Bean_Logo8");
		// Accumulationfundlat = intent.getStringExtra("Accumulationfundlat");
		// Accumulationfundlon = intent.getStringExtra("Accumulationfundlon");
		// ҵ������
		initView();
		initLocation();// ��ʼ����λ
		// ��Ӹ�����
		initSetMarker();
		// ��ͼ�ĵ��
		markeronClick();
	}

	/**
	 * ��ͼ���
	 */
	private void markeronClick() {
		// ��ʾ������ݲ���
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				// ��̬���ݲ���
				Bundle exBundle = marker.getExtraInfo();
				Bean_Logo8 bean = (Bean_Logo8) exBundle.getParcelable("info");
				ImageView iv = (ImageView) mMarkerLy
						.findViewById(R.id.id_info_img);
				TextView id_info_tv_name = (TextView) mMarkerLy
						.findViewById(R.id.id_info_tv_name);
				TextView id_info_distance = (TextView) mMarkerLy
						.findViewById(R.id.id_info_distance);
				TextView id_info_zan = (TextView) mMarkerLy
						.findViewById(R.id.tv_num);

				// ���ø�����ֵ
				iv.setImageResource(R.drawable.a01);// ͼƬ
				id_info_tv_name.setText(bean.getName());// name
				id_info_distance.setText(bean.getAddress());// ��ַ
				id_info_zan.setText(bean.getNum() + "");// ����
				System.out.println("����:" + bean.getLatitude() + ",γ��:"
						+ bean.getLongitude());
				mMarkerLy.setVisibility(View.VISIBLE);
				// ��ʾ�����򲿷�
				InfoWindow infoWindow;
				TextView tv = new TextView(MainActivity_logo8_location.this);
				tv.setBackgroundResource(R.drawable.location_tips);
				tv.setPadding(30, 20, 30, 50);
				tv.setText(bean.getName());// ����name
				tv.setTextColor(getResources().getColor(R.color.white));

				LatLng latLng = marker.getPosition();
				Point p = mBaiduMap.getProjection().toScreenLocation(latLng);
				p.y -= 47;
				LatLng ll = mBaiduMap.getProjection().fromScreenLocation(p);

				infoWindow = new InfoWindow(tv, ll,
						new OnInfoWindowClickListener() {

							@Override
							public void onInfoWindowClick() {
								mBaiduMap.hideInfoWindow();
							}
						});
				mBaiduMap.showInfoWindow(infoWindow);// ��ʾ������
				mMarkerLy.setVisibility(View.VISIBLE);// ��ʾ������
				return true;
			}
		});
		// �ٴε����ʧ����
		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				return false;
			}

			@Override
			public void onMapClick(LatLng arg0) {
				mMarkerLy.setVisibility(View.GONE);
				mBaiduMap.hideInfoWindow();
			}
		});
	}

	private void initView() {
		handler = new myToast(MainActivity_logo8_location.this);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_middle.setText(getResources().getString(R.string.af_app_name));
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setVisibility(View.VISIBLE);
		ll_back.setOnClickListener(this);
		ib_btn_right = (ImageButton) findViewById(R.id.ib_btn_right);
		ib_btn_right.setVisibility(View.GONE);
		ib_btn_left = (ImageButton) findViewById(R.id.ib_btn_left);
		ib_btn_left.setOnClickListener(this);
		mContext = this;
		iv_location = (ImageView) findViewById(R.id.iv_location);
		iv_location.setOnClickListener(this);
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();

		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(12.0f);// ����500����ʾ15.0f
		mBaiduMap.setMapStatus(msu);
	}

	/**
	 * ��Ӹ�����
	 */
	private void initSetMarker() {
		mMarker = BitmapDescriptorFactory.fromResource(R.drawable.maker);// ����ͼ��
		// ��ֵ����
		// infos.add(bean);
		// 34.7555250000,113.6306910000
		infos.add(new Bean_Logo8(34.7555250000, 113.6306910000, R.drawable.a01,
				"֣�ݹ���������", "��ԭ·�ٻ�·11��3¥", "1"));
		mMarkerLy = (LinearLayout) findViewById(R.id.id_markerly);
		addOverLays(infos);
	}

	private void initLocation() {
		// ��ͨģʽ
		mLocationMode = LocationMode.NORMAL;
		mLocationClient = new LocationClient(this);
		myLocationListener = new MyLocationListener();
		// ע�ᶨλ
		mLocationClient.registerLocationListener(myLocationListener);

		LocationClientOption option = new LocationClientOption();
		option.setCoorType("bd09ll");
		option.setIsNeedAddress(true);
		option.setOpenGps(true);
		option.setScanSpan(1000);// ÿ������������1000=1second
		mLocationClient.setLocOption(option);
		// ��ʼ��ͼ��
		mIconLocation = BitmapDescriptorFactory
				.fromResource(R.drawable.navi_map_gps_locked);
		// ��ʼ�����򴫸���
		myOrientationListener = new MyOrientationListener(mContext);
		myOrientationListener
				.setmOrientationListener(new onOrientationListener() {

					@Override
					public void onOrientationChanged(float x) {
						mCurrentX = x;
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.id_map_common:
			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);// ������ͨ��ͼ
			break;
		case R.id.id_map_site:
			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);// �������ǵ�ͼ
			break;
		case R.id.id_map_traffic:
			if (mBaiduMap.isTrafficEnabled()) {
				mBaiduMap.setTrafficEnabled(false);
				item.setTitle("ʵʱ��ͨ(off)");
			} else {
				mBaiduMap.setTrafficEnabled(true);
				item.setTitle("ʵʱ��ͨ(on)");
			}
			break;
		case R.id.id_map_location:
			centerToMyLocation();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null) {
				return;
			}
			loc = location;
			MyLocationData data = new MyLocationData.Builder()//
					.direction(mCurrentX)// ���򴫸�����Xֵ
					.accuracy(location.getRadius())// ��λ����
					.latitude(location.getLatitude())// ��λ���ݷ���
					.longitude(location.getLongitude())//
					.build();

			mBaiduMap.setMyLocationData(data);
			// ����ͼ��
			MyLocationConfiguration config = new MyLocationConfiguration(
					mLocationMode, true, mIconLocation);
			mBaiduMap.setMyLocationConfigeration(config);
			if (isFirstIn) {
				centerToMyLocation();
				isFirstIn = false;

			} else {
				// wucaozuo
			}

		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		mBaiduMap.setMyLocationEnabled(true);
		if (!mLocationClient.isStarted()) {
			mLocationClient.start();
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
		mBaiduMap.setMyLocationEnabled(false);
		mLocationClient.stop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_location:

			showToast("λ��:" + loc.getAddrStr() + ",����:" + loc.getLatitude()
					+ ",γ��:" + loc.getLongitude());
			centerToMyLocation();
			// int r = mLocationClient.requestLocation();
			// switch (r) {
			// case 1:
			// showToast("����û������");
			// break;
			// case 4:
			// showToast("û�м�������");
			// break;
			// case 6:
			// showToast("����ʱ�����");
			// break;
			// default:
			// break;
			// }
			break;
		case R.id.ll_back:
			// ����
			finish();
			break;
		default:
			break;
		}
	}

	/**
	 * ��λ���ҵ�λ��
	 */
	private void centerToMyLocation() {
		LatLng latLng = new LatLng(loc.getLatitude(), loc.getLongitude());
		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
		// ����ת����λ
		mBaiduMap.animateMapStatus(msu);
	}

	/**
	 * ��Ӹ�����
	 * 
	 * @param infos
	 */
	private void addOverLays(List<Bean_Logo8> infos) {
		mBaiduMap.clear();
		LatLng latLng = null;
		Marker marker = null;
		OverlayOptions options;

		for (Bean_Logo8 info : infos) {
			// ��γ��
			latLng = new LatLng(info.getLatitude(), info.getLongitude());
			// ͼ��
			options = new MarkerOptions().position(latLng).icon(mMarker)
					.zIndex(5);
			marker = (Marker) mBaiduMap.addOverlay(options);
			Bundle bundle = new Bundle();
			bundle.putParcelable("info", info);
			marker.setExtraInfo(bundle);
		}

		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
		mBaiduMap.setMapStatus(msu);

	}

	private Toast mToast;

	private void showToast(String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
		}
		mToast.setText(msg);
		mToast.show();
	}

	@SuppressWarnings("unused")
	private void handMessage(int i) {
		msg = handler.obtainMessage();
		msg.arg1 = i;
		handler.sendMessage(msg);
	}
}
