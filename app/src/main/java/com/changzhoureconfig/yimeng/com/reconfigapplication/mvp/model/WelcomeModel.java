package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model;

import android.support.v4.app.Fragment;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.common.Constants;
import com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope.ActivityScope;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.WelcomeContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.fragment.TranslateFragment;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.PrefsUtils;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
@ActivityScope
public class WelcomeModel implements WelcomeContract.Model {
    private boolean mIsFirst;
    private int[] layouts = {
            R.layout.guide_one,
            R.layout.guide_two,
            R.layout.guide_three,
            R.layout.guide_four,
            R.layout.guide_five
    };

    @Inject
    public WelcomeModel() {
    }

    @Override
    public boolean getIsFirst() {
        mIsFirst = PrefsUtils.getInstance().getBoolVFromKey(Constants.PREFERENCES_WELCOME_FALST);
        return mIsFirst;
    }

    @Override
    public ArrayList<Fragment> initFragment() {
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        for (int i = 0; i < layouts.length; i++) {
            fragments.add(TranslateFragment.getInstance(layouts[i]));
        }
        return fragments;
    }

}
