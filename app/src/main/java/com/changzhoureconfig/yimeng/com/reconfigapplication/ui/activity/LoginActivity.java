package com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity;

import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginResultData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.AppComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.DaggerLoginComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.LoginModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.LoginContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.presenter.LoginPresenter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.common.WEActivity;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.views.MyEditText;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.views.dialog.ShapeLoadingDialog;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.Tools;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends WEActivity<LoginPresenter> implements LoginContract.View, MyEditText.OnDoOtherThing {
    @BindView(R.id.seeit)
    ImageView seeit;
    @BindView(R.id.logintv)
    TextView logintv;
    @BindView(R.id.editarea)
    LinearLayout editarea;
    @BindView(R.id.forget_password)
    TextView forgetPassword;
    @BindView(R.id.registertv)
    TextView registertv;
    @BindView(R.id.tell)
    MyEditText tell;
    @BindView(R.id.password)
    MyEditText password;
    private ShapeLoadingDialog shapeLoadingDialog;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_login, null, false);
    }

    @Override
    protected void initData() {
        password.setOnDoOtherThing(this);
        shapeLoadingDialog = new ShapeLoadingDialog(LoginActivity.this);
        mPresenter.requestLogin(tell.getText().toString(),password.getText().toString());
    }

    @Override
    public void showLoading(String message) {
        shapeLoadingDialog.setLoadingText(message);
        shapeLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showWarn(String message) {
        Tools.showHintToast(LoginActivity.this, message);
    }

    @Override
    public void launchActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void killMyself() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder().appComponent(appComponent).loginModule(new LoginModule(this)).build().inject(this);
    }

    @Override
    public void pwdShowOrNot(boolean show) {
        if (show) {
            seeit.setImageDrawable(getResources().getDrawable(R.drawable.close));
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            password.setSelection(password.length());
        } else {
            seeit.setImageDrawable(getResources().getDrawable(R.drawable.open));
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            password.setSelection(password.length());
        }
    }

    @Override
    public void gotoBaseSetting(LoginResultData.user user) {
        Intent intent = new Intent(LoginActivity.this, RegisterBaseSettingActivity.class);
        intent.putExtra("nickname", user.getNickname());
        startActivity(intent);
    }


    @OnClick({R.id.seeit, R.id.logintv, R.id.forget_password, R.id.registertv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.seeit:
                mPresenter.pwdShowOrNot();
                break;
            case R.id.logintv:
                String phoneStr = tell.getText().toString();
                String passwordStr = password.getText().toString();
                mPresenter.requestLogin(phoneStr, passwordStr);
                break;
            case R.id.forget_password:
                Intent intent2 = new Intent();
                intent2.putExtra("titleName", "找回密码");
                intent2.setClass(LoginActivity.this, ChangePassWordActivity.class);
                startActivity(intent2);
                break;
            case R.id.registertv:
                Intent intent = new Intent();
                intent.putExtra("titleName", "注册");
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void doshowthing(View view) {
        if (view.getId() == R.id.password) {
            seeit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void dohindthing(View view) {
        seeit.setVisibility(View.INVISIBLE);
    }
}
