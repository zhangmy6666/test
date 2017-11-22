package cn.zmy.util;

public class Constants {
	
	/** 用户对象放到Session中的键名称 */
    public static final String SYSTEMADMIN_PATH = "";
    
    public static final String ALL_PROVINCE = "all_province";
    public static final String PROVINCE_ = "province_";
    public static final String CITY_ = "city_";
    public static final String HOSPITAL = "hosp";
    public static final String NAME_="name_";
    public static final String PARENT_ID_="parent_id_";
    public static final String ALL_="all_";
    public static final String ALL_DEPARTMENTS="ALL_DEPARTMENTS";

    public static final int REGIST_TYPE_MOBILE = 1;
    public static final int REGIST_TYPE_MAIL = 2;
    
    /** 审核状态 */
    public static final int NOT_AUDIT_STATE = 1;
    public static final int PASS_AUDIT_STATE = 2;
    public static final int NOT_PASS_AUDIT_STATE = 3;
    
    /** 存在与否*/
    public static final String NOT_EXIST = "1";
    public static final String EXIST = "2";
    
    /** 类型 所有*/
    public static final String SEARCH_TYPE_ALL = "0";
    public static final String SEARCH_TYPE_MOBILE = "1";
    public static final String SEARCH_TYPE_EMAIL = "2";
    public static final String SEARCH_TYPE_NUBE = "3";
    
    /** 类型 所有*/
    public static final int PRAISE_CANCELL = 1;
    public static final int PRAISE = 2;
    
    /** 订阅 */
    public static final int SUBSCRIBE_CANCELL = 1;
    public static final int SUBSCRIBE = 2;
    
    /** 用户是否订阅 */
    public static final int NO_SUBSCRIBE = 1; // 为订阅
    public static final int SUBSCRIBED = 2;	// 已订阅
    
    /** 发布状态*/
    public static final int NOT_PUBLISH = 1;
    public static final int PUBLISHED = 2;
    
    
    /** 类型 所有*/
    public static final int PRAISE_COLLECT_CANCELL = 1; 	// 取消点赞/收藏
    public static final int PRAISE_COLLECT = 2;				// 点赞/收藏
    
    /** 用户-点赞-收藏 中间表类型  */
    public static final int USER_OPT_TYPE_PRAISE = 1; 	// 点赞
    public static final int USER_OPT_TYPE_COLLECT = 2;	// 收藏

    /** 账号种类 */
    public static final String ACCOUNT_TYPE_APP = "app_";
    public static final String ACCOUNT_TYPE_OFFACC = "offacc_";
    public static final String ACCOUNT_TYPE_SYSADMIN = "sysadmin_";
    
    /** 账号密码最多输入次数 */
    public static final int MAX_ERROR_COUNT = 5;
    
    /** 群和个人二维码类型 */
    public static final String QRCODE_TYPE_GROUP = "group";
    public static final String QRCODE_TYPE_PERSON = "person";
    
    /** 发送类型 */
    public static final int SEND_TYPE_NOW = 1;
    public static final int SEND_TYPE_SCHEDULE = 2;
    
    /** 用户模式 */
    public static final int ACCOUNT = 1;// 注册用户
    public static final int VISITOR = 2;// 游客
    
    /** 文章报名类型*/
    public static final int UNABLE_ENROLL = 1;// 不支持报名
    public static final int ABLE_ENROLL = 2;// 支持报名
    
    /** 支付状态 */
    public static final int UNPAID = 1;// 未支付
    public static final int PAYING = 2;// 支付中
    public static final int PAID = 3;// 已支付
    public static final int PAY_FAILED = 4;// 支付失败
    
    /** 报名状态*/
    public static final int NOT_SIGNUP = 1;// 未报名
    public static final int REGISTERED = 2;// 已报名

    public static final String ARTICLE_TYPE_TEXT = "1";
    public static final String ARTICLE_TYPE_VIDEO = "2";

    public static final String WIND_FARM = "wind_farm_";
}
