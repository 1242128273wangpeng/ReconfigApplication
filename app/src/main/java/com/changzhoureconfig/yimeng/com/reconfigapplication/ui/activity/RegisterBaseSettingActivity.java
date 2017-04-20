package com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.AppComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.DaggerRegisterBaseSettingComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.RegisterBaseSettingModule;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.RegisterBaseSettingContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.presenter.RegisterBaseSettingPresenter;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.common.WEActivity;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.views.DateTimePickerDialog;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.views.MyEditText;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class RegisterBaseSettingActivity extends WEActivity<RegisterBaseSettingPresenter> implements MyEditText.OnDoOtherThing, RegisterBaseSettingContract.View {
    @BindView(R.id.nickname)
    MyEditText nickname;
    @BindView(R.id.llCalendar)
    RelativeLayout llCalendar;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.tv_lastdata)
    TextView tvLastdata;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRegisterBaseSettingComponent.builder().appComponent(appComponent).registerBaseSettingModule(new RegisterBaseSettingModule(this)).build().inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_registerbasesetting, null, false);
    }

    @Override
    protected void initData() {
        mPresenter.initText();
        nickname.setOnDoOtherThing(this);
        tvLastdata.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim() != null && !"".equals(s.toString().trim())) {
                    Date date = null;
                    try {
                        date = sdf.parse(s.toString().trim());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //孕产期时间不能为当前时间之后
                    if (date != null) {
                        Calendar calendar1 = Calendar.getInstance();
                        Calendar c1 = Calendar.getInstance();//当前时间
                        c1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                        Calendar c2 = Calendar.getInstance();
                        c2.setTime(date);
                        int ii = Tools.daysBetween(c1, c2);
                        if (ii >= -15) {
                            Tools.showToastLong(RegisterBaseSettingActivity.this, "您末次月经时间需设置在半个月之前");
                            tvLastdata.setText("");
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.llCalendar, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llCalendar:
                // 1 为显示年月日的对话框
                mPresenter.showDialog(tvLastdata, 1);
                break;
            case R.id.register:
                String nickName = nickname.getText().toString();
                String tvData = tvLastdata.getText().toString();
                mPresenter.saveData(nickName, tvData);
                break;
        }
    }

    @Override
    public void doshowthing(View view) {
        if (view.getId() == R.id.nickname) {
            nickname.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void dohindthing(View view) {
        nickname.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading(String message) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showWarn(String message) {
        Tools.showHintToast(RegisterBaseSettingActivity.this, message);
    }

    @Override
    public void launchActivity() {
        startActivity(new Intent(RegisterBaseSettingActivity.this, MainActivity.class));
    }

    @Override
    public void killMyself() {

    }


    @Override
    public void showInitData() {
        String nicknameStr = getIntent().getStringExtra("nickname");
        nickname.setText(nicknameStr);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -15);
        date = calendar.getTime();
        String t = format.format(date);
        tvLastdata.setText(t);
    }

    @Override
    public void showDateTimePickerDialog(TextView dateTimeTextEdite, int type, Calendar c) {
        DateTimePickerDialog dateTimePicKDialog = new DateTimePickerDialog(RegisterBaseSettingActivity.this);
        dateTimePicKDialog.dateTimePicKDialog(dateTimeTextEdite, 1, c);
    }

}
