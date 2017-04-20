package com.changzhoureconfig.yimeng.com.reconfigapplication.ui.common;

import com.changzhoureconfig.yimeng.com.reconfigapplication.MyApplication;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.AppComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BasePresenter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity.BaseActivity;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public abstract class WEActivity<P extends BasePresenter>  extends BaseActivity<P>{
    protected MyApplication mWeApplication;
    @Override
    protected void ComponentInject() {
        mWeApplication = (MyApplication) getApplication();
        setupActivityComponent(mWeApplication.getAppComponent());
    }
    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);
}
