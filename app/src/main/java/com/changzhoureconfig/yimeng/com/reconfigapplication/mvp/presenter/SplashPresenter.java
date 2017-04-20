package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.presenter;

import android.app.ActionBar;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.changzhoureconfig.yimeng.com.reconfigapplication.MyApplication;
import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.AdvertImageData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginResultData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.common.Constants;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.imageloader.ImageLoader;
import com.changzhoureconfig.yimeng.com.reconfigapplication.imageloader.glide.GlideImageConfig;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BasePresenter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.SplashContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.adapter.ImagePaperAdapter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.PrefsUtils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.changzhoureconfig.yimeng.com.reconfigapplication.common.ErrorHandlers.displayErrorAction;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    private ImagePaperAdapter imagePaperAdapter;
    private ArrayList<ImageView> imageViewList = new ArrayList<>();
    private ImageLoader ImageLoadProxy;
    private MyApplication myApplication;
    private Animation animation;
    private boolean animationBeCancel = false;
    private Subscription intervalSubscription;

    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View view) {
        super(model, view);
        myApplication = (MyApplication) (MyApplication.getContext().getApplicationContext());
        ImageLoadProxy = myApplication.getAppComponent().imageLoader();
    }

    public void StartBackgroundImageAnimationRequestAdvert(Context context) {
        animation = mModel.getBackgroundImageAnimation(context);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mModel.requestAdvert().subscribeOn(Schedulers.io())
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
                        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<AdvertImageData>() {
                    @Override
                    public void call(AdvertImageData advertImageData) {
                        if (advertImageData != null && advertImageData.status == 1) {
                            if (advertImageData.data.size() > 0) {
                                imageViewList = createImageList(advertImageData);
                                imagePaperAdapter = new ImagePaperAdapter(imageViewList);
                            }
                        }
                    }
                }, displayErrorAction(MyApplication.getContext()));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!animationBeCancel) {
                    mRootView.setBannerAdapter(imagePaperAdapter);
                    intervalSubscription = Observable.interval(2, imageViewList.size(), TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            if (aLong < imageViewList.size()) {
                                mRootView.setAutoInterval(new Long(aLong).intValue());
                            } else if (aLong == imageViewList.size()) {
                                String phone = PrefsUtils.getInstance().getValueFromKey(Constants.PHONE);
                                String password = PrefsUtils.getInstance().getValueFromKey(Constants.PASSWORD);
                                String registrationId = PrefsUtils.getInstance().getValueFromKey(Constants.PREFERENCES_PUSHTOKEN);
                                mModel.requestLogin(phone, password, registrationId).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
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
                                        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<LoginData>() {
                                    @Override
                                    public void call(LoginData loginData) {
                                        switch (loginData.getResultCode()) {
                                            case "001"://登录成功
                                                if ("0".equals(loginData.getResultMap().user.getRegisterFlag())) {
                                                    mRootView.toBaseSetting(loginData.getResultMap().user);
                                                } else {
                                                    mRootView.toMain();
                                                }
                                                break;
                                            case "002"://账号或密码错误
                                                mRootView.showWarn("账号或密码错误");
                                                mRootView.toLogin();
                                                break;
                                            case "006"://账号不存在
                                                mRootView.showWarn("账号不存在");
                                                mRootView.toLogin();
                                                break;
                                            case "997"://参数值为空
//                                              mRootView.showWarn("参数值为空");
                                                mRootView.toLogin();
                                                break;
                                            case "999"://系统异常
                                                mRootView.showWarn("系统异常");
                                                mRootView.toLogin();
                                                break;
                                        }
                                    }
                                },  displayErrorAction(MyApplication.getContext()));
                                intervalSubscription.unsubscribe();
                            }
                        }
                    });
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mRootView.animateBackgroundImage(animation);
    }

    public void requestLoginOrToLogin() {
        if (animation != null) {
            animation.cancel();
            animation = null;
            animationBeCancel = true;
        }
        if (intervalSubscription != null) {
            intervalSubscription.unsubscribe();
        }
        String phone = PrefsUtils.getInstance().getValueFromKey(Constants.PHONE);
        String password = PrefsUtils.getInstance().getValueFromKey(Constants.PASSWORD);
        if (!phone.equals("") && !password.equals("")) { // 登录过，直接请求网络登录
            String registrationId = PrefsUtils.getInstance().getValueFromKey(Constants.PREFERENCES_PUSHTOKEN);
            mModel.requestLogin(phone, password, registrationId).subscribeOn(Schedulers.io())
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
                    }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<LoginData>() {
                @Override
                public void call(LoginData loginData) {
                    switch (loginData.getResultCode()) {
                        case "001"://登录成功
                            if ("0".equals(loginData.getResultMap().user.getRegisterFlag())) {
                                mRootView.toBaseSetting(loginData.getResultMap().user);
                            } else {
                                mRootView.toMain();
                            }
                            break;
                        case "002"://账号或密码错误
                            mRootView.showWarn("账号或密码错误");
                            mRootView.toLogin();
                            break;
                        case "006"://账号不存在
                            mRootView.showWarn("账号不存在");
                            mRootView.toLogin();
                            break;
                        case "997"://参数值为空
//                          mRootView.showWarn("参数值为空");
                            mRootView.toLogin();
                            break;
                        case "999"://系统异常
                            mRootView.showWarn("系统异常");
                            mRootView.toLogin();
                            break;
                    }
                }
            },  displayErrorAction(MyApplication.getContext()));
        } else { // 没有登录过，跳转到登录界面
            mRootView.toLogin();
        }
    }


    private ArrayList<ImageView> createImageList(AdvertImageData mAddImageData) {
        ArrayList<ImageView> list = new ArrayList();
        for (int i = 0; i < mAddImageData.data.size(); i++) {
            AdvertImageData.DataBean dataBean = mAddImageData.data.get(i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ImageView iv = (ImageView) LayoutInflater.from(MyApplication.getContext().getApplicationContext()).inflate(R.layout.scroll_vew_item, null);
            ImageLoadProxy.loadImage(myApplication, GlideImageConfig
                    .builder()
                    .url(dataBean.imageUrl)
                    .imagerView(iv)
                    .build());
            list.add(iv);
        }
        return list;
    }

}
