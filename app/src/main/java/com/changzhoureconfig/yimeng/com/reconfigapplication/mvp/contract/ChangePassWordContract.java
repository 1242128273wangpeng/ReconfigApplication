package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract;

import android.widget.TextView;

import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.BaseData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BaseView;

import java.util.Calendar;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/15 0015.
 */

public interface ChangePassWordContract {
    interface View extends BaseView {
        void pwdShowOrNot(boolean show);

        void confirmPwdShowOrNot(boolean show);

        void restTimeCount();

        void startTimeCount(long millisUntilFinished);
    }

    interface Model {
        Observable<BaseData> requestCode(String phone);

        Observable<BaseData> requestChangePassword(String phone,String code,  String password);
    }
}
