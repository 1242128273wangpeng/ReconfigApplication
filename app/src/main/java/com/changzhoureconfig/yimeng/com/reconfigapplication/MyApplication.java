package com.changzhoureconfig.yimeng.com.reconfigapplication;

import android.content.Context;
import android.widget.Toast;

import com.changzhoureconfig.yimeng.com.reconfigapplication.common.Constants;
import com.changzhoureconfig.yimeng.com.reconfigapplication.base.BaseApplication;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.AppComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.DaggerAppComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.common.ServiceModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.http.GlobeHttpHandler;
import com.changzhoureconfig.yimeng.com.reconfigapplication.http.ResponseErrorListener;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.PrefsUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.jpush.android.api.JPushInterface;
import timber.log.Timber;

/**
 * @author wangdong
 */
public class MyApplication extends BaseApplication {
    private AppComponent mAppComponent;
    private RefWatcher mRefWatcher;//leakCanary观察器

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(getAppModule())//baseApplication提供
                .clientModule(getClientModule())//baseApplication提供
                .imageModule(getImageModule())//baseApplication提供
                .serviceModule(new ServiceModule())//需自行创建
                .build();
        if (BuildConfig.LOG_DEBUG) {//Timber日志打印
            Timber.plant(new Timber.DebugTree());
        }
        installLeakCanary();//leakCanary内存泄露检查
        PrefsUtils.initializeInstance(getApplicationContext());
        JPushInterface.init(getApplicationContext());
        JPushInterface.setDebugMode(true);
    }
    /**
     * 安装leakCanary检测内存泄露
     */
    protected void installLeakCanary() {
        this.mRefWatcher = BuildConfig.USE_CANARY ? LeakCanary.install(this) : RefWatcher.DISABLED;
    }
    /**
     * 这里可以提供一个全局处理http响应结果的处理类,
     * 这里可以比客户端提前一步拿到服务器返回的结果,可以做一些操作,比如token超时,重新获取
     * 默认不实现,如果有需求可以重写此方法
     *
     * @return
     */
    @Override
    public GlobeHttpHandler getHttpHandler() {
        return null;
    }
    /**
     * 获得leakCanary观察器
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
       MyApplication application = (MyApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

    // 多个后台的基础路径（正式服务器/测试服务器）
    @Override
    protected String getBaseUrl() {
        return Constants.BASEURL;
    }

    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例, 在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    protected ResponseErrorListener getResponseErrorListener() {
        return new ResponseErrorListener() {
            @Override
            public void handleResponseError(Context context, Exception e) {
                Timber.tag(TAG).w("------------>" + e.getMessage());
                Toast.makeText(context, "handleResponseError", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
