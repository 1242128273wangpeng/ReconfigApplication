package com.changzhoureconfig.yimeng.com.reconfigapplication.utils;

import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * 数据检查工具
 * @author yynie
 * 
 */
public class CheckUtils {
	public static final Pattern PHONE = Pattern.compile("13\\d{9}||14\\d{9}||15\\d{9}||17\\d{9}||18\\d{9}$");
    public static final String EXP_a_z = "[a-z]*";//匹配所有的小写字母
    public static final String EXP_A_Z = "[A-Z]*";//匹配所有的大写字母
    public static final String EXP_a_z_A_Z = "[a-zA-Z]*";//匹配所有的字母
    public static final String EXP_0_9 = "[0-9]*";//匹配所有的数字
    public static final String EXP_0_9_a_z = "[0-9a-z]*";
    public static final String EXP_0_9_a_z_A_Z = "[0-9a-zA-Z]*";
    public static final String EXP_0_9_a_z__ = "[0-9a-z_]*";
    public static final String EXP_EMAIL = "^([a-z0-9A-Z_]+[_|\\-|\\.]?)+[a-z0-9A-Z_]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";//EMAIL
    public static final String EXP_PRICE = "^([1-9]\\d+|[1-9])(\\.\\d\\d?)*$";//金额，2位小数
    public static final String EXP_MOBILE = "[0-9]{11}";//11位数的手机号码
    public static final String EXP_POSTALCODE = "[0-9]{6}";//6位数的邮编
    public static final String EXP_TEL = "[0-9]{3,4}[-]{1}[0-9]{7,8}";//电话号码：( 如021-12345678 or 0516-12345678 )
    public static final String EXP_IP = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";//匹配IP地址
    public static final String EXP_CHINESE = "[\u4e00-\u9fa5]*";//匹配中文
    public static final String EXP_0_9_a_z_A_Z_CHINESE = "[0-9a-zA-Z\u4e00-\u9fa5]*";//匹配中文,数字,小写字母,大写字母

    public static final String EXP_URL = "^[a-zA-z]+://[^><\"' ]+";

    public static final String EXP_DATE = "[0-9]{4}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}";
    public static final String EXP_DATETIME = "[0-9]{4}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}[ ]{1}[0-9]{1,2}[:]{1}[0-9]{1,2}";
    public static final String EXP_DATETIMESECOND = "[0-9]{4}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}[ ]{1}[0-9]{1,2}[:]{1}[0-9]{1,2}[:]{1}[0-9]{1,2}";

    public static final String DATESTRING_TAIL = "000000000";
    
    private static final HashSet<Class<?>> clsSet;
    static {
    	clsSet = new HashSet<Class<?>>();
    	clsSet.add(void.class);
    	clsSet.add(boolean.class);
    	clsSet.add(byte.class);
    	clsSet.add(char.class);
    	clsSet.add(int.class);
    	clsSet.add(long.class);
    	clsSet.add(short.class);
    	clsSet.add(double.class);
    	clsSet.add(float.class);
    	clsSet.add(Void.class);
    	clsSet.add(Boolean.class);
    	clsSet.add(Byte.class);
    	clsSet.add(Character.class);
    	clsSet.add(Integer.class);
    	clsSet.add(Long.class);
    	clsSet.add(Short.class);
    	clsSet.add(Double.class);
    	clsSet.add(Float.class);
    	clsSet.add(String.class);
	}

	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str)) ? true : false;
	}
	//阿拉伯数字转中文小写？
	public static String transition(String s) {
		String[] hanziArr = new String[]{"个", "十", "百", "千", "万", "十", "百", "千", "亿"};
		String[] numberArr = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "点"};
		StringBuilder result = new StringBuilder();
		int pointIndex = s.indexOf(".");
		String integer;
		String decimal = null;
		if (-1 == pointIndex) {
			integer = s;
		} else {
			integer = s.substring(0, pointIndex);
			decimal = s.substring(pointIndex + 1);
		}
		for (int i = 0; i < integer.length(); i++) {
			char c = integer.charAt(i);
			if (c == '0') {
				result.append(numberArr[0]);
			} else {
				result.append(numberArr[Integer.parseInt(String.valueOf(c))]);
				if (i != integer.length() - 1)
					result.append(hanziArr[integer.length() - 1 - i]);
			}
		}
		return result.toString();
	}

}
