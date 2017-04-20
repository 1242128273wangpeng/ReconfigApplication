package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract;

import android.widget.EditText;
import android.widget.TextView;

import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BaseView;

import java.util.Calendar;

import rx.Observable;


/**
 * Created by Administrator on 2017/4/18 0018.
 */

public interface RegisterBaseSettingContract {
    interface View extends BaseView {
        void showInitData();

        void showDateTimePickerDialog(TextView textView, int type, Calendar calendar);
    }

    interface Model {
        Observable saveData(String nickName, String pregnancy);
    }
}
