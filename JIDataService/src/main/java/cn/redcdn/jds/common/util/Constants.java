package cn.redcdn.jds.common.util;

public class Constants {

	/** 搜索类型 所有 */
	public static final int SEARCH_TYPE_ALL = 0;
	public static final int SEARCH_TYPE_MOBILE = 1;
	public static final int SEARCH_TYPE_EMAIL = 2;
	public static final int SEARCH_TYPE_NUBE = 3;
	public static final int SEARCH_TYPE_NAME = 4;

	/** 账号种类 */
	public static final String ACCOUNT_TYPE_APP = "app_";
	public static final String ACCOUNT_TYPE_SYSADMIN = "sysadmin_";

	/** 账号密码最多输入次数 */
	public static final int MAX_ERROR_COUNT = 5;

	/** 用户类型 */
	public static final int VIP = 1; // VIP会员
	public static final int VIP_PRO = 2; // VIP试用
	public static final int NORMAL = 3; // 普通会员
	
	/** 设置vip试用模式 */
	public static final int VIP_PRO_DAY = 1; // 试用天数
	public static final int VIP_PRO_DATE = 2; // 试用日期

}
