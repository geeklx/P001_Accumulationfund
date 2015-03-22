package com.accumulationfund.main.update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.accumulationfund.util.JsonData;
import com.liangxiao.accumulationfund.R;

@SuppressLint("HandlerLeak")
public class UpdateManager {
	public static final String BaoName = "com.liangxiao.accumulationfund";// ���ð���
	public static final String ApkURL = "http://211.142.200.10:8082/upload/accumulationfund/version.xml";// ���÷��������°���ַ
	/* ������ */
	private static final int DOWNLOAD = 1;
	/* ���ؽ��� */
	private static final int DOWNLOAD_FINISH = 2;
	/* ���������XML��Ϣ */
	HashMap<String, String> mHashMap;
	/* ���ر���·�� */
	private String mSavePath;
	/* ��¼���������� */
	private int progress;
	/* �Ƿ�ȡ������ */
	private boolean cancelUpdate = false;

	private Context mContext;
	/* ���½����� */
	private ProgressBar mProgress;
	private Dialog mDownloadDialog;
	private String versions;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// ��������
			case DOWNLOAD:
				// ���ý�����λ��
				mProgress.setProgress(progress);
				break;
			case DOWNLOAD_FINISH:
				// ��װ�ļ�
				installApk();
				break;
			default:
				break;
			}
		};
	};
	@SuppressWarnings("unused")
	private Activity activity;

	public UpdateManager(Context context, Activity activity) {
		this.mContext = context;
		this.activity = activity;
	}

	/**
	 * ����������
	 * 
	 * @throws IOException
	 * @throws NotFoundException
	 */
	public void checkUpdate() throws NotFoundException, IOException {
		new Thread(new Runnable() {

			@SuppressLint("ShowToast")
			@Override
			public void run() {

				try {
					if (isUpdate() != null) {
						// ��ʾ��ʾ�Ի���
						Looper.prepare();
						showNoticeDialog(isUpdate().toString());
						Looper.loop();
						Log.d("��Ϣ", "���°汾");
					} else {
						Log.d("��Ϣ", "�������°汾");
						Looper.prepare();
						Toast.makeText(mContext, "�������°汾", 2).show();
						Looper.loop();
					}
				} catch (NotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

	/**
	 * �������Ƿ��и��°汾
	 * 
	 * @return
	 * @throws IOException
	 */
	public String isUpdate() throws IOException {
		// ��ȡ��ǰ����汾
		int versionCode = getVersionCode(mContext);
		// ��version.xml�ŵ������ϣ�Ȼ���ȡ�ļ���Ϣ

		// InputStream inStream = ParseXmlService.class.getClassLoader()
		// .getResourceAsStream("version.xml");
		URL url = new URL(ApkURL);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		InputStream inStream = urlConn.getInputStream();
		// ����XML�ļ��� ����XML�ļ��Ƚ�С�����ʹ��DOM��ʽ���н���
		ParseXmlService service = new ParseXmlService();
		try {
			mHashMap = service.parseXml(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != mHashMap) {
			int serviceCode = Integer.valueOf(mHashMap.get("version"));// String.valueOf("serviceCode"+"---"+serviceCode)
			versions = mHashMap.get("content").toString();
			// Toast.makeText(UpdateManager.this,'', Toast.LENGTH_SHORT).show();
			Log.i("-----serviceCode", "" + serviceCode);
			Log.i("-----versionCode", "" + versionCode);

			// �汾�ж�
			if (serviceCode > versionCode) {
				JsonData.getApplication().setMemCache("versionCode", "1");
				JsonData.getApplication().setMemCache("versions", "11");
				return versions;
			}
		} else {
			Log.i("-----null == mHashMap", "null == mHashMap");
		}

		return null;
	}

	/**
	 * ��ȡ����汾��
	 * 
	 * @param context
	 * @return
	 */
	private int getVersionCode(Context context) {
		int versionCode = 0;
		try {
			// ��ȡ����汾�ţ���ӦAndroidManifest.xml��android:versionCode
			versionCode = context.getPackageManager()
					.getPackageInfo(BaoName, 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * ��ʾ������¶Ի���
	 */
	private void showNoticeDialog(String version) {
		// ����Ի���
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle(R.string.soft_update_title);
		version = version.replace("\\n", "\n");
		builder.setMessage(version);
		Log.i("versions->", version);
		// ����
		builder.setPositiveButton(R.string.soft_update_updatebtn,
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// ��ʾ���ضԻ���
						showDownloadDialog();
					}
				});
		// �Ժ����
		builder.setNegativeButton(R.string.soft_update_later,
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		Dialog noticeDialog = builder.create();
		noticeDialog.show();
	}

	/**
	 * ��ʾ������ضԻ���
	 */
	private void showDownloadDialog() {
		// ����������ضԻ���
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle(R.string.soft_updating);
		// �����ضԻ������ӽ�����
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.af_sotfupdate_progress, null);
		mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
		builder.setView(v);
		// ȡ������
		builder.setNegativeButton(R.string.soft_update_cancel,
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// ����ȡ��״̬
						cancelUpdate = true;
					}
				});
		mDownloadDialog = builder.create();
		mDownloadDialog.show();
		// �����ļ�
		downloadApk();
	}

	/**
	 * ����apk�ļ�
	 */
	private void downloadApk() {
		// �������߳��������
		new downloadApkThread().start();
	}

	/**
	 * �����ļ��߳�
	 */
	private class downloadApkThread extends Thread {
		@Override
		public void run() {
			try {
				// �ж�SD���Ƿ���ڣ������Ƿ���ж�дȨ��
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					// ��ô洢����·��
					String sdpath = Environment.getExternalStorageDirectory()
							+ "/";
					mSavePath = sdpath + "download";
					URL url = new URL(mHashMap.get("url"));
					// ��������
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.connect();
					// ��ȡ�ļ���С
					int length = conn.getContentLength();
					// ����������
					InputStream is = conn.getInputStream();

					File file = new File(mSavePath);
					// �ж��ļ�Ŀ¼�Ƿ����
					if (!file.exists()) {
						file.mkdir();
					}
					File apkFile = new File(mSavePath, mHashMap.get("name"));
					FileOutputStream fos = new FileOutputStream(apkFile);
					int count = 0;
					// ����
					byte buf[] = new byte[1024];
					// д�뵽�ļ���
					do {
						int numread = is.read(buf);
						count += numread;
						// ���������λ��
						progress = (int) (((float) count / length) * 100);
						// ���½���
						mHandler.sendEmptyMessage(DOWNLOAD);
						if (numread <= 0) {
							// �������
							mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
							break;
						}
						// д���ļ�
						fos.write(buf, 0, numread);
					} while (!cancelUpdate);// ���ȡ����ֹͣ����.
					fos.close();
					is.close();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// ȡ�����ضԻ�����ʾ
			mDownloadDialog.dismiss();
		}
	};

	/**
	 * ��װAPK�ļ�
	 */
	private void installApk() {
		File apkfile = new File(mSavePath, mHashMap.get("name"));
		if (!apkfile.exists()) {
			return;
		}
		// ͨ��Intent��װAPK�ļ�
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
	}
}
