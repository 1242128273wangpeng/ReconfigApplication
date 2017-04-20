package com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.component.AppComponent;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.common.WEActivity;

public class MainActivity extends WEActivity {

    @Override
    protected View initView() {
        TextView textView = new TextView(this);
        textView.setText("MainActivity");
        return textView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}
