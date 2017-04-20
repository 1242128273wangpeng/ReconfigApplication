package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component;

import android.app.Application;

import com.changzhoureconfig.yimeng.com.reconfigapplication.api.CommonService;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.common.AppModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.common.ClientModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.common.ImageModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.common.ServiceModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.imageloader.ImageLoader;
import com.changzhoureconfig.yimeng.com.reconfigapplication.manage.ServiceManage;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class, ImageModule.class, ServiceModule.class})
public interface AppComponent {
    Application Application();

    //服务管理器,retrofitApi
    ServiceManage serviceManager();

    OkHttpClient okHttpClient();

    //图片管理器,用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    ImageLoader imageLoader();

    //Gson
    Gson gson();

}
