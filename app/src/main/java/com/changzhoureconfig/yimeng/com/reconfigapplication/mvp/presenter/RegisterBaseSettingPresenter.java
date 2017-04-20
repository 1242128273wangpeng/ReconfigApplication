package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.presenter;

import android.widget.TextView;

import com.changzhoureconfig.yimeng.com.reconfigapplication.MyApplication;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.BaseData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.BaseData_2;
import com.changzhoureconfig.yimeng.com.reconfigapplication.common.Constants;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BasePresenter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.ChangePassWordContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.RegisterBaseSettingContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.PrefsUtils;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.changzhoureconfig.yimeng.com.reconfigapplication.common.ErrorHandlers.displayErrorAction;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
@ActivityScope
public class RegisterBaseSettingPresenter extends BasePresenter<RegisterBaseSettingContract.Model, RegisterBaseSettingContract.View> {
    @Inject
    public RegisterBaseSettingPresenter(RegisterBaseSettingContract.Model model, RegisterBaseSettingContract.View rootView) {
        super(model, rootView);
    }

    //初始化文本内容
    public void initText() {
        mRootView.showInitData();
    }

    public void showDialog(TextView dateTimeTextEdite, int type) {
        Date date1 = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String timetext = dateTimeTextEdite.getText().toString();
        Calendar c = Calendar.getInstance();
        if (timetext != null && !"".equals(timetext)) {
            try {
                date1 = sdf1.parse(timetext);
                c.setTime(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {//时间没有，加当前时间加一个小时，
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            c.add(Calendar.DATE, 1);
        }
        mRootView.showDateTimePickerDialog(dateTimeTextEdite, type, c);
    }

    // 保存末次月经昵称到服务器
    public void saveData(String nickName, String pregnancy) {
        if(pregnancy != null && !"".equals(pregnancy) && nickName != null && !"".equals(nickName)){
            if (nickName.length() <= 8) {//限制昵称长度
                mModel.saveData(nickName, pregnancy).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                mRootView.showLoading("正在获取...");//显示上拉刷新的进度条
                            }
                        }).subscribeOn(AndroidSchedulers.mainThread()).
                        doAfterTerminate(new Action0() {
                            @Override
                            public void call() {
                                mRootView.hideLoading();//隐藏上拉刷新的进度条
                            }
                        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseData_2>() {
                    @Override
                    public void call(BaseData_2 baseData) {
                        if (baseData.getStatus() == 1) {
                            mRootView.showWarn("设置成功");
                            mRootView.launchActivity();
                        }
                    }
                },  displayErrorAction(MyApplication.getContext()));
            } else {
                mRootView.showWarn("用户名太长哦");
            }
        }else{
            mRootView.showWarn("昵称或未次月经\n不能为空");
        }
    }
}
