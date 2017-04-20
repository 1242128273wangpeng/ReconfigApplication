package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.animation.Animation;

import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.AdvertImageData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginResultData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BaseView;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.adapter.ImagePaperAdapter;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public interface SplashContract {
    interface View extends BaseView {
        public void animateBackgroundImage(Animation animation);

        public void toLogin();

        public void toMain();

        public void toBaseSetting(LoginResultData.user user);

        public void setBannerAdapter(ImagePaperAdapter pagerAdapter);

        public void setAutoInterval(int currentPage);
    }

    interface Model {
        public Animation getBackgroundImageAnimation(Context context);

        public Observable<AdvertImageData> requestAdvert();

        public Observable<LoginData> requestLogin(String phone, String password, String registrationId);
    }
}
