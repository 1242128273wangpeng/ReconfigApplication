package com.changzhoureconfig.yimeng.com.reconfigapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.changzhoureconfig.yimeng.com.reconfigapplication.common.Constants;

public class PrefsUtils {

    private final SharedPreferences mPrefs;

    private static PrefsUtils mInstance;


    private PrefsUtils(Context context) {
        mPrefs = context.getSharedPreferences(Constants.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    /**
     * 初始化
     *
     * @param context Context
     */
    public static synchronized void initializeInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PrefsUtils(context);
        }
    }

    /**
     * 获取实例
     * @return sharedPreference 实例
     */
    public static synchronized PrefsUtils getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException(PrefsUtils.class.getSimpleName() + "is not initialized, aboutcall initializeInstance() method first");
        }
        return mInstance;
    }


    /**
     * 获取key的 String value
     * key 可以在 Constants.PREFERENCES_XXX 获得
     *
     * @param key String
     * @return String Value
     */
    public String getValueFromKey(String key) {
        return mPrefs.getString(key, "");
    }

    /**
     * 获取key的 boolean value
     *
     * @param key String
     * @return boolean value
     */
    public boolean getBoolVFromKey(String key) {
        return mPrefs.getBoolean(key, false);
    }

    /**
     * 保存 String value
     *
     * @param key   String
     * @param value String
     */
    public void setValue(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    /**
     * 保存 boolean value
     *
     * @param key   String
     * @param value boolean
     */
    public void setValue(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    /**
     * 保存 String value
     * @param ctx
     * @param key
     * @param value
     */
    public static void setString(Context ctx, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    /**
     *
     * @param ctx
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context ctx, String key, String defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

}
