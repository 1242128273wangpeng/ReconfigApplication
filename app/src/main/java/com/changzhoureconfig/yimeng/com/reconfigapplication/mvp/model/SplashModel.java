package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.api.CommonService;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.AdvertImageData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.manage.ServiceManage;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BaseModel;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.SplashContract;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class SplashModel extends BaseModel implements SplashContract.Model {
    ServiceManage serviceManage;

    public SplashModel(ServiceManage serviceManage) {
        this.serviceManage = serviceManage;
    }

    @Override
    public Animation getBackgroundImageAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.splash);
    }

    @Override
    public Observable<AdvertImageData> requestAdvert() {
        return serviceManage.getCommonService().AccessAdvert();
    }

    @Override
    public Observable<LoginData> requestLogin(String phone, String password, String registrationId) {
        return serviceManage.getCommonService().AccessLogin(phone, password, registrationId);
    }
}
