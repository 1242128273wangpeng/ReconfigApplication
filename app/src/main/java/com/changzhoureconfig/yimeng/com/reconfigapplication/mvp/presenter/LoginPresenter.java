package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.presenter;

import android.content.Intent;

import com.changzhoureconfig.yimeng.com.reconfigapplication.MyApplication;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginResultData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.common.Constants;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BasePresenter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.LoginContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity.LoginActivity;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity.RegisterBaseSettingActivity;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.MD5Utils;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.PrefsUtils;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.changzhoureconfig.yimeng.com.reconfigapplication.common.ErrorHandlers.displayErrorAction;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private boolean ispwdshow = true;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View view) {
        super(model, view);
    }

    // 请求登录
    public void requestLogin(String phone, String password) {
        String registrationId = mModel.getRegistrationId();
        String MD5password = MD5Utils.MD5(password);
        mModel.requestLogin(phone, MD5password, registrationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mRootView.showLoading("正在获取...");
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).
                doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mRootView.hideLoading();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LoginData>() {
                    @Override
                    public void call(LoginData loginData) {
                        switch (loginData.getResultCode()) {
                            case "001"://登录成功
                                processData(loginData);
                                if ("0".equals(loginData.getResultMap().user.getRegisterFlag())) {
                                    gotoBaseSetting(loginData.getResultMap().user);
                                } else {
                                    gotoMain();
                                }
                                break;
                            case "002"://账号或密码错误
                                mRootView.showWarn("账号或密码错误");
                                break;
                            case "006"://账号不存在
                                mRootView.showWarn("账号不存在");
                                break;
                            case "997"://参数值为空
//                                mRootView.showWarn("参数值为空");
                                break;
                            case "999"://系统异常
                                mRootView.showWarn("系统异常");
                                break;
                        }
                    }
                }, displayErrorAction(MyApplication.getContext()));
    }

    // 密码的睁眼闭眼
    public void pwdShowOrNot() {
        mRootView.pwdShowOrNot(ispwdshow);
        ispwdshow = !ispwdshow;
    }

    // 进入主界面
    public void gotoMain() {
        mRootView.launchActivity();
    }

    /**
     * 跳到设置界面
     */
    private void gotoBaseSetting(LoginResultData.user user) {
        mRootView.gotoBaseSetting(user);
    }

    /**
     * 数据的保存
     */
    private void processData(LoginData loginData) {
        LoginResultData.user loguser = loginData.getResultMap().getUser();
        if (loguser.getName() == null || "".equals(loguser.getName())) {
            PrefsUtils.getInstance().setValue(Constants.NAME, loguser.getNickname());
        } else {
            PrefsUtils.getInstance().setValue(Constants.NAME, loguser.getName());
        }
        PrefsUtils.getInstance().setValue(Constants.UID, loguser.getUid());
        PrefsUtils.getInstance().setValue(Constants.REMINDCOUNT, loginData.getResultMap().remindCount);
        PrefsUtils.getInstance().setValue(Constants.FLAG, loguser.getFlag());
        PrefsUtils.getInstance().setValue(Constants.BIRTHDAY, loguser.getBirthday());
        PrefsUtils.getInstance().setValue(Constants.AGE, loguser.getAge());
        PrefsUtils.getInstance().setValue(Constants.PREGNANCY, loguser.getPregnancy());
        PrefsUtils.getInstance().setValue(Constants.SYSPREGNANCY, loguser.getSysPregnancy());
        PrefsUtils.getInstance().setValue(Constants.DUEDATE, loguser.getDueDate());
        PrefsUtils.getInstance().setValue(Constants.ID, loguser.getId() + "");
        PrefsUtils.getInstance().setValue(Constants.PHONE, loguser.getPhone());
        PrefsUtils.getInstance().setValue(Constants.NICKNAME, loguser.getNickname());
        PrefsUtils.getInstance().setValue(Constants.STATUSCASE, loguser.getStatusCase() + "");
        PrefsUtils.getInstance().setValue(Constants.STATUS, String.valueOf(loginData.getResultMap().getStatus()));
        PrefsUtils.getInstance().setValue(Constants.PASSWORD, loguser.getPassword());
        PrefsUtils.getInstance().setValue(Constants.HEADURL, loguser.getHeadurl());
        PrefsUtils.getInstance().setValue(Constants.QQ, loguser.getQq());
        PrefsUtils.getInstance().setValue(Constants.ADDRESS, loguser.getAddress());
        PrefsUtils.getInstance().setValue(Constants.SEX, loguser.getSex());
        PrefsUtils.getInstance().setValue(Constants.SHARE, loguser.getShare());
        PrefsUtils.getInstance().setValue(Constants.PERFECT, loguser.getPerfect());
        PrefsUtils.getInstance().setValue(Constants.RECOM_SCORE, loguser.getRecom_score());
        PrefsUtils.getInstance().setValue(Constants.SUM_SCORE, loguser.getSum_score());
        PrefsUtils.getInstance().setValue(Constants.ONLY_SCORE, loguser.getOnly_score());
        PrefsUtils.getInstance().setValue(Constants.CHECK_SUM, loguser.getCheck_sum());
        PrefsUtils.getInstance().setValue(Constants.CHECK_TIME, loguser.getCheck_time());
        PrefsUtils.getInstance().setValue(Constants.WELFARETIME, loginData.getResultMap().getWelfareTime());
        PrefsUtils.getInstance().setValue(Constants.IS_LOGIN, "1");
    }

}
