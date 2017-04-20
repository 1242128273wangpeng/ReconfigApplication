package com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.AppComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.DaggerRegisterComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.RegisterModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.RegisterContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.presenter.RegisterPresenter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.common.WEActivity;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.views.MyEditText;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.views.dialog.ShapeLoadingDialog;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.Tools;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends WEActivity<RegisterPresenter> implements MyEditText.OnDoOtherThing, RegisterContract.View {
    private String phone;
    private String password;
    private String password2;
    private String code;
    private ShapeLoadingDialog shapeLoadingDialog;

    @BindView(R.id.return_out)
    TextView returnOut;
    @BindView(R.id.keep)
    TextView keep;
    @BindView(R.id.fenxiang)
    ImageView fenxiang;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tell)
    MyEditText tell;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.set_password)
    MyEditText setPassword;
    @BindView(R.id.password_seeit)
    ImageView passwordSeeit;
    @BindView(R.id.confirm_password)
    MyEditText confirmPassword;
    @BindView(R.id.confirm_seeit)
    ImageView confirmSeeit;
    @BindView(R.id.register)
    TextView register;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_register, null);
    }

    @Override
    protected void initData() {
        String titleName = getIntent().getStringExtra("titleName");
        if (titleName != null && !"".equals(titleName)) {
            title.setText(titleName);
        }
        setPassword.setOnDoOtherThing(this);
        confirmPassword.setOnDoOtherThing(this);
        shapeLoadingDialog = new ShapeLoadingDialog(RegisterActivity.this);
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
        UiUtils.showDialogWithMethod(message, this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                mPresenter.finishActivity();
            }
        });
    }

    @Override
    public void showWarn(String message) {
        Tools.showHintToast(RegisterActivity.this, message);
    }

    @Override
    public void launchActivity() {
        this.finish();
    }

    @Override
    public void killMyself() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRegisterComponent.builder().appComponent(appComponent).registerModule(new RegisterModule(this)).build().inject(this);
    }

    @Override
    public void pwdShowOrNot(boolean show) {
        if (show) {
            passwordSeeit.setImageDrawable(getResources().getDrawable(R.drawable.close));
            setPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            setPassword.setSelection(setPassword.length());
        } else {
            passwordSeeit.setImageDrawable(getResources().getDrawable(R.drawable.open));
            setPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            setPassword.setSelection(setPassword.length());
        }
    }

    @Override
    public void confirmPwdShowOrNot(boolean show) {
        if (show) {
            confirmSeeit.setImageDrawable(getResources().getDrawable(R.drawable.close));
            confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            confirmPassword.setSelection(confirmPassword.length());
        } else {
            confirmSeeit.setImageDrawable(getResources().getDrawable(R.drawable.open));
            confirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            confirmPassword.setSelection(confirmPassword.length());
        }
    }

    @Override
    public void restTimeCount() {
        getcode.setBackgroundResource(R.drawable.anniu1);
        getcode.setText("获取验证码");
        getcode.setTextColor(getResources().getColor(R.color.white));
        getcode.setClickable(true);
    }

    @Override
    public void startTimeCount(long millisUntilFinished) {
        getcode.setBackgroundResource(R.drawable.anniu1);
        getcode.setTextColor(getResources().getColor(R.color.white));
        getcode.setText(millisUntilFinished / 1000 + "秒后重发");
        getcode.setClickable(false);
    }

    @Override
    public void doshowthing(View view) {
        if (view.getId() == R.id.set_password) {
            passwordSeeit.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.confirm_password) {
            confirmSeeit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void dohindthing(View view) {
        if (view.getId() == R.id.set_password) {
            passwordSeeit.setVisibility(View.INVISIBLE);
        } else if (view.getId() == R.id.confirm_password) {
            confirmSeeit.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick({R.id.getcode, R.id.register, R.id.password_seeit, R.id.confirm_seeit, R.id.return_out})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getcode:
                phone = tell.getText().toString();
                if (mPresenter.checkoutPhone(phone)) {
                    mPresenter.requestCode(phone);
                }
                break;
            case R.id.register:
                phone = tell.getText().toString();
                code = verification.getText().toString();
                password = setPassword.getText().toString();
                password2 = confirmPassword.getText().toString();
                if (mPresenter.validate(phone, code, password, password2)) {
                    mPresenter.requestRegister(phone, password, code, null); // 注册时末次月经允许为空
                }
                break;
            case R.id.password_seeit:
                mPresenter.pwdShowOrNot();
                break;
            case R.id.confirm_seeit:
                mPresenter.pwdConfirmShowOrNot();
                break;
            case R.id.return_out:
                mPresenter.finishActivity();
                break;
        }
    }
}
