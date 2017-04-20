package com.changzhoureconfig.yimeng.com.reconfigapplication.http;

import android.content.Context;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */

public interface ResponseErrorListener {
    void handleResponseError(Context context, Exception e);
}
