package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base;

import android.content.Intent;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */

public interface BaseView {
    /**
     * 显示加载
     */
    void showLoading(String message);

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String message);

    /**
     * 提示信息
     */
    void showWarn(String message);

    /**
     * 跳转activity
     */
    void launchActivity();

    /**
     * 杀死自己
     */
    void killMyself();
}
