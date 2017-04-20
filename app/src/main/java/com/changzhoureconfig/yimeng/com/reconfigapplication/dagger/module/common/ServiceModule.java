package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.common;



import com.changzhoureconfig.yimeng.com.reconfigapplication.api.CommonService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ServiceModule {

    @Singleton
    @Provides
    CommonService provideCommonService(Retrofit retrofit) {
        return retrofit.create(CommonService.class);
    }

}
