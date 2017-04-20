package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract;

import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.BaseData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BaseView;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public interface RegisterContract {
    interface View extends BaseView {
        void pwdShowOrNot(boolean show);

        void confirmPwdShowOrNot(boolean show);

        void restTimeCount();

        void startTimeCount(long millisUntilFinished);
    }

    interface Model {
        Observable<BaseData> requestCode(String phone);

        Observable<BaseData> requestRegister(String phone, String password, String code, String pregnancy);
    }
}
