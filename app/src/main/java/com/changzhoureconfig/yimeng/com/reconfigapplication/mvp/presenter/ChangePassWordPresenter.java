package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.presenter;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.changzhoureconfig.yimeng.com.reconfigapplication.MyApplication;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.BaseData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BasePresenter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.ChangePassWordContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.MD5Utils;
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
 * Created by Administrator on 2017/4/15 0015.
 */
@ActivityScope
public class ChangePassWordPresenter extends BasePresenter<ChangePassWordContract.Model, ChangePassWordContract.View> {
    private boolean ispwdshow = true;
    private ChangePassWordPresenter.TimeCount time;//计时器

    @Inject
    public ChangePassWordPresenter(ChangePassWordContract.Model model, ChangePassWordContract.View rootView) {
        super(model, rootView);
        time = new ChangePassWordPresenter.TimeCount(60000, 1000);
    }

    // 请求验证码
    public void requestCode(String phone) {
        mModel.requestCode(phone)
                .subscribeOn(Schedulers.io())
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
                }).subscribeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<BaseData>() {
                    @Override
                    public void call(BaseData baseData) {
                        switch (baseData.getResultCode()) {
                            case "0"://成功
                                mRootView.showWarn("验证码已发送，请注意查收!");
                                time.start();// 开始计时
                                break;
                            case "005"://验证码不正确
                                mRootView.showWarn("验证码不正确或已失效");
                                break;
                        }
                    }
                }, displayErrorAction(MyApplication.getContext()));
    }

    // 请求跟换密码
    public void requestChangePassword(String phone, String code, String password) {
        password = MD5Utils.MD5(password);
        mModel.requestChangePassword(phone, code, password)
                .subscribeOn(Schedulers.io())
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
                }).subscribeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<BaseData>() {
                    @Override
                    public void call(BaseData baseData) {
                        switch (baseData.getResultCode()) {
                            case "0"://修改成功
                                mRootView.showMessage("修改密码成功");
                                break;
                            case "005"://验证码失效
                                mRootView.showMessage("验证码失效");
                                break;
                            case "008"://邀请人不存在
                                mRootView.showWarn("邀请人不存在");
                                break;
                            case "999"://系统异常
                                mRootView.showWarn("系统异常");
                                break;
                        }
                    }
                }, displayErrorAction(MyApplication.getContext()));
    }

    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            this.cancel();
            mRootView.restTimeCount();
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            mRootView.startTimeCount(millisUntilFinished);
        }
    }


    // 验证手机号
    public boolean checkoutPhone(String phone) {
        if (!"".equals(phone) && Tools.checkIsPhoneNumber(phone)) {
            return true;
        } else {
            mRootView.showWarn("请输入正确的手机号");
            return false;
        }
    }


    // validate验证注册的输入信息
    public boolean validate(String phone, String code, String password, String password2) {
        if ("".equals(phone) || phone == null) {
            mRootView.showWarn("请输入手机号或昵称");
            return false;
        }
        if ("".equals(code) || code == null) {
            mRootView.showWarn("请输入验证码");
            return false;
        }
        if ("".equals(password) || password == null) {
            mRootView.showWarn("请输入密码");
            return false;
        }
        if (password.length() < 6) {
            mRootView.showWarn("密码位数不小于六位");
            return false;
        }
        if ("".equals(password2) || password2 == null) {
            mRootView.showWarn("请确认密码");
            return false;
        }
        if (!password.equals(password2)) {
            mRootView.showWarn("两次密码输入不一致，请重新输入");
            return false;
        }
        return true;
    }

    // 密码的睁眼闭眼
    public void pwdShowOrNot() {
        mRootView.pwdShowOrNot(ispwdshow);
        ispwdshow = !ispwdshow;
    }

    // 确认密码的睁眼闭眼
    public void pwdConfirmShowOrNot() {
        mRootView.confirmPwdShowOrNot(ispwdshow);
        ispwdshow = !ispwdshow;
    }

    // 关闭当前Activity
    public void finishActivity() {
        time.cancel();
        mRootView.launchActivity();
    }

}
