package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract;

import android.support.v4.app.Fragment;

import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BaseView;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.adapter.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public interface WelcomeContract {

    interface View extends BaseView {
        public void setViewAdapter(FragmentPagerAdapter fragmentPagerAdapter);
    }

    interface Model {
        public boolean getIsFirst();

        public ArrayList<Fragment> initFragment();
    }
}
