package com.accumulationfund.otheractivity.toast;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class myToast extends Handler {
	private Activity activity;

	public myToast(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void handleMessage(Message msg) {

		switch (msg.arg1) {
		case 1:
			showInfo("��¼�ɹ���");
			break;
		case 121:
			showInfo("�������");
			break;
		case 122:
			showInfo("��¼ʧ�ܣ��û������ڣ�");
			break;
		case 112:
			showInfo("�û�����Ϊ�գ�");
			break;
		case 1122:
			showInfo("�û���ʽ����");
			break;
		case 113:
			showInfo("���벻��Ϊ�գ�");
			break;
		case 1133:
			showInfo("�����ʽ����");
			break;
		case 2:
			showInfo("�ֻ��Ż�����֤�벻��Ϊ��!");
			break;
		case 3:
			showInfo("���ͳɹ�!");
			break;
		case 4:
			showInfo("����δ����!");
			break;
		case 5:
			showInfo("��������!");
			break;
		case 6:
			showInfo("����д�ֻ�����");
			break;
		case 7:
			showInfo("����ʧ��!");
			break;
		case 8:
			showInfo("ԭ���벻��Ϊ��!");
			break;
		case 9:
			showInfo("�����벻��Ϊ��!");
			break;
		case 91:
			showInfo("�޸ĳɹ�!");
			break;
		case 92:
			showInfo("�޸�ʧ��!");
			break;
		case 93:
			showInfo("ԭ�������!");
			break;
		case 94:
			showInfo("����6-15λ!");
			break;
		case 941:
			showInfo("����ӦΪ5-20����!");
			break;
		case 942:
			showInfo("�������ӦΪ1-100����!");
			break;
		case 943:
			showInfo("��������ӦΪ1-30����!");
			break;
		case 99:
			showInfo("���������벻һ�£�������!");
			break;
		case 10:
			showInfo("ȷ�����벻��Ϊ��!");
			break;
		case 11:
			showInfo("����Ϊ��!");
			break;
		case 12:
			showInfo("�������°汾");
			break;
		case 13:
			showInfo("��������Ϊ��!");
			break;
		case 14:
			showInfo("���䲻��Ϊ��!");
			break;
		case 15:
			showInfo("�ֻ��Ų���Ϊ��!");
			break;
		case 155:
			showInfo("�ֻ��Ÿ�ʽ����!");
			break;
		case 16:
			showInfo("���Բ���Ϊ��!");
			break;
		case 17:
			showInfo("�������벻��Ϊ��!");
			break;
		case 18:
			showInfo("������ɴ��������Ϊ��!");
			break;
		case 19:
			showInfo("���乫����ɴ��������Ϊ��!");
			break;
		case 20:
			showInfo("�ȶϢ��ȶ����Ϊ��!");
			break;
		case 21:
			showInfo("�����Ϊ��!");
			break;
		case 211:
			showInfo("�����ܶ��Ϊ��!");
			break;
		case 222:
			showInfo("����������Ϊ��!");
			break;
		case 22:
			showInfo("�����ܼ۲���Ϊ��!");
			break;
		case 23:
			showInfo("���ҳ�������Ϊ��!");
			break;
		case 233:
			showInfo("������������Ϊ��!");
			break;
		case 24:
			showInfo("�����˹���������Ϊ��!");
			break;
		case 25:
			showInfo("�����˲��乫��������Ϊ��!");
			break;
		case 26:
			showInfo("�������½ɽ����Ϊ��!");
			break;
		case 27:
			showInfo("���������䲻��Ϊ��!");
			break;
		case 28:
			showInfo("�������Ա���Ϊ��!");
			break;
		case 29:
			showInfo("�δ��˹��������Ϊ��!");
			break;
		case 30:
			showInfo("�δ��˲��乫��������Ϊ��!");
			break;
		case 31:
			showInfo("�δ����½ɽ����Ϊ��!");
			break;
		case 32:
			showInfo("���ݼ۸���Ϊ��!");
			break;
		case 33:
			showInfo("�������Ͳ���Ϊ��!");
			break;
		default:
			break;
		}
		super.handleMessage(msg);
	}

	/**
	 * ��ʾ��ʾ��Ϣ
	 * 
	 * @param info
	 */
	public void showInfo(String info) {
		Toast.makeText(activity.getApplicationContext(), info,
				Toast.LENGTH_SHORT).show();
	}
}
