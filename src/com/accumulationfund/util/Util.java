package com.accumulationfund.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.accumulationfund.model.IAlertDialogButtonListener;
import com.liangxiao.accumulationfund.R;

public class Util {

	private static Dialog mDialog;

	// ����gridview�е�item��xml����
	public static View getView(Context context, int layoutId) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(layoutId, null);
		return layout;
	}

	/**
	 * ��ʾ�Զ���Ի���
	 * 
	 * @param context
	 * @param message
	 * @param listener
	 */
	public static void showDialog(final Context context, String message,
			final IAlertDialogButtonListener listener) {
		View dialogView = null;
		// Dialog.Builder builder = new Builder(context,
		// R.style.Theme_Transparent);
		mDialog = new Dialog(context, R.style.Theme_Transparent);

		dialogView = getView(context, R.layout.af_dialog_view);
		Button btn_ok = (Button) dialogView.findViewById(R.id.btn_ok);
		Button btn_cancel = (Button) dialogView.findViewById(R.id.btn_cancel);
		TextView txt_dailog_message = (TextView) dialogView
				.findViewById(R.id.txt_dailog_message);
		txt_dailog_message.setText(message);
		btn_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// �ر�dialog
				if (mDialog != null) {
					mDialog.cancel();
				}
				// �¼��ص�
				if (listener != null) {
					listener.onClick();
				}

				// ������Ч
				// MyPlayer.playTone(context, MyPlayer.INDEX_STONE_ENTER);
			}
		});
		btn_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// �ر�dialog
				if (mDialog != null) {
					mDialog.cancel();
				}
				// ������Ч
				// MyPlayer.playTone(context, MyPlayer.INDEX_STONE_CANCEL);
			}
		});
		// Ϊdialog����View
		// builder.setView(dialogView);
		mDialog.setContentView(dialogView);
		// mDialog = builder.create();
		mDialog.show();
	}
}
