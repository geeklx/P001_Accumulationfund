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
			showInfo("登录成功！");
			break;
		case 121:
			showInfo("密码错误！");
			break;
		case 122:
			showInfo("登录失败，用户不存在！");
			break;
		case 112:
			showInfo("用户不能为空！");
			break;
		case 1122:
			showInfo("用户格式错误！");
			break;
		case 113:
			showInfo("密码不能为空！");
			break;
		case 1133:
			showInfo("密码格式错误！");
			break;
		case 2:
			showInfo("手机号或者验证码不能为空!");
			break;
		case 3:
			showInfo("发送成功!");
			break;
		case 4:
			showInfo("网络未连接!");
			break;
		case 5:
			showInfo("暂无数据!");
			break;
		case 6:
			showInfo("请填写手机号码");
			break;
		case 7:
			showInfo("发送失败!");
			break;
		case 8:
			showInfo("原密码不能为空!");
			break;
		case 9:
			showInfo("新密码不能为空!");
			break;
		case 91:
			showInfo("修改成功!");
			break;
		case 92:
			showInfo("修改失败!");
			break;
		case 93:
			showInfo("原密码错误!");
			break;
		case 94:
			showInfo("长度6-15位!");
			break;
		case 941:
			showInfo("数字应为5-20区间!");
			break;
		case 942:
			showInfo("贷款比例应为1-100区间!");
			break;
		case 943:
			showInfo("贷款年数应为1-30区间!");
			break;
		case 99:
			showInfo("新密码输入不一致，请重试!");
			break;
		case 10:
			showInfo("确认密码不能为空!");
			break;
		case 11:
			showInfo("不能为空!");
			break;
		case 12:
			showInfo("已是最新版本");
			break;
		case 13:
			showInfo("姓名不能为空!");
			break;
		case 14:
			showInfo("邮箱不能为空!");
			break;
		case 15:
			showInfo("手机号不能为空!");
			break;
		case 155:
			showInfo("手机号格式错误!");
			break;
		case 16:
			showInfo("留言不能为空!");
			break;
		case 17:
			showInfo("工资收入不能为空!");
			break;
		case 18:
			showInfo("公积金缴存比例不能为空!");
			break;
		case 19:
			showInfo("补充公积金缴存比例不能为空!");
			break;
		case 20:
			showInfo("等额本息或等额本金不能为空!");
			break;
		case 21:
			showInfo("贷款不能为空!");
			break;
		case 211:
			showInfo("贷款总额不能为空!");
			break;
		case 222:
			showInfo("公积金贷款不能为空!");
			break;
		case 22:
			showInfo("房屋总价不能为空!");
			break;
		case 23:
			showInfo("按揭成数不能为空!");
			break;
		case 233:
			showInfo("按揭年数不能为空!");
			break;
		case 24:
			showInfo("主贷人公积金余额不能为空!");
			break;
		case 25:
			showInfo("主贷人补充公积金余额不能为空!");
			break;
		case 26:
			showInfo("主贷人月缴交额不能为空!");
			break;
		case 27:
			showInfo("主贷人年龄不能为空!");
			break;
		case 28:
			showInfo("主贷人性别不能为空!");
			break;
		case 29:
			showInfo("参贷人公积金余额为空!");
			break;
		case 30:
			showInfo("参贷人补充公积金余额不能为空!");
			break;
		case 31:
			showInfo("参贷人月缴交额不能为空!");
			break;
		case 32:
			showInfo("房屋价格不能为空!");
			break;
		case 33:
			showInfo("房屋类型不能为空!");
			break;
		default:
			break;
		}
		super.handleMessage(msg);
	}

	/**
	 * 显示提示信息
	 * 
	 * @param info
	 */
	public void showInfo(String info) {
		Toast.makeText(activity.getApplicationContext(), info,
				Toast.LENGTH_SHORT).show();
	}
}
