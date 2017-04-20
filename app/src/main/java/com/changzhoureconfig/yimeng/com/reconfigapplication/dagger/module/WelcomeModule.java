package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module;

import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.WelcomeContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model.WelcomeModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
@Module
public class WelcomeModule {
    private WelcomeContract.View view;
    /**
     * 构建SplashModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public WelcomeModule(WelcomeContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    WelcomeContract.View provideWelcomeView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    WelcomeContract.Model provideWelcomeModel(WelcomeModel welcomeModel){
        return welcomeModel;
    }

}
