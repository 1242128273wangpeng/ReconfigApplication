package com.changzhoureconfig.yimeng.com.reconfigapplication.common;

/**
 * 静态常量值的存储类
 */
public class Constants {
    // 服务器的根路径
    public static String BASEURL = "http://jyhfz.shanghaiyimeng.com/";
    // APP_ID 微信开发者平台的appId，用于分享
    public static final String APP_ID = "wxde0d43c865c41e1c";
    // 更新下载时创建的目录，用于apk存放的位置
    public static final String dir = "YunMa";
    /**
     * 取数据线程mssage.
     */
    public static final int MSG_NOTIFY_UPDATE = 0;
    public static final int MSG_NET_ERROR = 1;
    public static final int MSG_EXCEPTION = 2;
    public static final int MSG_NOTIFY_RELOAD = 3;
    public static final int MSG_NET_NOT_CONNECTED = 4;
    public static final int MSG_NOTIFY_UPDATE_CONTINUE = 5;
    public static final int UPLOAD_IMG_SUCCESS = 6;
    public static final int UPLOAD_IMG_FAIL = 7;

    public static final int DLWL_BJ = 1000;

    /**
     * 数据返回结果
     */
    public static String DATA_OK = "SUCCESS!";


    public static String DATA_ERR = "服务器数据异常";
    public static String CONN_TIMEOUT = "连接超时";
    public static String UNKNOWN_HOST = "未知的域名";
    //    public static String IO_ERR = "IO异常,请检查网络状态";
    public static String IO_ERR = "网络异常,请检查当前网络是否已连接";
    public static String EXCEPTION_ERR = "未知的异常";
    public static String NET_NOT_CONNECTED = "网络未连接，请连接网络";
    /**
     * 获取数据成功
     */
    public static final int HAND_GET_DATA_SUCCESS = 1001;

    /**
     * 获取数据失败
     */
    public static final int HAND_GET_DATA_FAIL = 1002;

    /**
     * SharedPreference
     */
    public static final String PREFERENCES_PUSHTOKEN = "com.dayou.logistics.pushToken";
    public static final String USER_SHARED_PREFERENCES = "com.dayou.order.userSession";
    public static final String IS_LOGIN = "com.dayou.logistics.login";//根据字符串判断是否登录  1已登录 0 未登录
    public static final String PREFERENCES_WELCOME_FALST = "com.dayou.logistics.iswelcome";//是否需要引导页
    public static final String PREFERENCES_UPDATE_VERSION = "com.dayou.logistics.updateVersion";
    public static final String PREFERENCES_UPDATE_VERSION_URL = "com.dayou.logistics.updateVersionUrl";
    public static final String PREFERENCES_UPDATE_VERSION_NAME = "com.dayou.logistics.updateVersionName";
    public static final String PREFERENCES_SHOWUPDATED = "com.dayou.logistics.showUpdated";
    /**
     * 用户信息
     */
    public static final String ID = "android.dayou.com.id";//用户ID
    public static final String PHONE = "android.dayou.com.phone";//用户手机号
    public static final String BROADCAST_QUITE = "android.dayou.com.quite";
    public static final String REFRESH = "android.dayou.com.refresh";//我的界面刷新通知
    public static final String UPDATEPREGNANT = "android.dayou.com.updatepregnant.refresh";//首界面进度小球刷新通知
    public static final String TYPE = "android.dayou.com.type";//type
    public static final String NICKNAME = "android.dayou.com.nickname";//用户昵称
    public static final String NAME = "android.dayou.com.name";//用户姓名
    public static final String PASSWORD = "android.dayou.com.password";//用户密码
    public static final String HEADURL = "android.dayou.com.headurl";//用户头像地址
    public static final String QQ = "android.dayou.com.qq";//用户qq
    public static final String UID = "android.dayou.com.uid";//唯一识别号
    public static final String REMINDCOUNT = "android.dayou.com.remindcount";//唯一识别号
    public static final String FLAG = "android.dayou.com.flag";//孕妇状态 1:孕前 2：孕期中 3：产后
    public static final String BIRTHDAY = "android.dayou.com.birthday";//用户qq
    public static final String AGE = "android.dayou.com.age";//用户qq
    public static final String PREGNANCY = "android.dayou.com.pregnancy";// 用户设置末次月经
    public static final String SYSPREGNANCY = "android.dayou.com.sysPregnancy";//  医生设置末次月经
    public static final String DUEDATE = "android.dayou.com.duedate";//预产期
    public static final String STATUS = "android.dayou.com.status";//
    public static final String STATUSCASE = "android.dayou.com.statusCase";//为0 跳验证界面 为1不跳
    public static final String KEFUQQ = "android.dayou.com.kefuqq";//客服QQ
    public static final String WELFARETIME = "android.dayou.com.welfareTime";//享受福利时间
    public static final String ADDRESS = "android.dayou.com.address";//地址
    public static final String SEX = "android.dayou.com.sex";//性别
    public static final String SHARE = "android.dayou.com.share";//0为显示 分享加积分
    public static final String PERFECT = "android.dayou.com.perfect";//0为显示 完善资料

    public static final String ONLY_SCORE = "android.dayou.com.only_score";//个人获得积分
    public static final String RECOM_SCORE = "android.dayou.com.recom_score";//邀请获得积分
    public static final String SUM_SCORE = "android.dayou.com.sum_score";// 总积分
    public static final String CHECK_SUM = "android.dayou.com.check_sum";//签到次数
    public static final String CHECK_TIME = "android.dayou.com.check_time";//上次签到时间
    public static final String CHECK_STATUS = "android.dayou.com.check_status";//签到状态
    public static final String MOOD_CONTENT_TEMP = "android.dayou.com.mood_content_temp"; //   说说暂时保存的内容
    public static final String MOOD_IMAGE_TEMP = "android.dayou.com.mood_image_temp"; //   说说暂时保存的图片
    public static final String MOOD_QUANZI_TEMP = "android.dayou.com.mood_quanzi_temp"; // 说说的圈子类型
    public static final String ARTICLE_TITLE_TEMP = "android.dayou.com.article_title_temp"; //  文章暂时保存的内容
    public static final String ARTICLE_CONTENT_TEMP = "android.dayou.com.article_content_temp"; //  文章暂时保存的内容
    public static final String ARTICLE_IMAGE_TEMP = "android.dayou.com.article_image_temp"; //  文章暂时保存的图片
    public static final String ARTICLE_QUANZI_TEMP = "android.dayou.com.article_quanzi_temp"; // 文章的圈子类型
    // 默认的收货信息
    public static final String RECEIVERPERSON = "android.dayou.com.receiverperson"; // 默认收货人
    public static final String RECEIVERPHONE = "android.dayou.com.receiverphone"; // 默认的收货电话
    public static final String RECEIVERAREA = "android.dayou.com.receiverpersonarea"; // 默认的收货地区
    public static final String RECEIVERADDRESS = "android.dayou.com.receiverpersonaddress"; // 默认的收货地址
}
