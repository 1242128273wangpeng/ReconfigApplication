package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract;

import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginResultData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BaseView;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public interface LoginContract {
    interface View extends BaseView {
        void pwdShowOrNot(boolean show);

        void gotoBaseSetting(LoginResultData.user user);
    }

    interface Model {
        Observable<LoginData> requestLogin(String phone, String password, String registrationId);

        String getRegistrationId();
    }
}
