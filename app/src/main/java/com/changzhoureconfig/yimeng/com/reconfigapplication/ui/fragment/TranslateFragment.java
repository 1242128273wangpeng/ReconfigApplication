package com.changzhoureconfig.yimeng.com.reconfigapplication.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.common.Constants;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.activity.LoginActivity;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.PrefsUtils;

public class TranslateFragment extends Fragment {
    public static final String VIEW_LAYOUT="viewLayout";

    public TranslateFragment() {

    }

    public static final TranslateFragment getInstance(int layoutParams) {
        TranslateFragment translateFragment = new TranslateFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(VIEW_LAYOUT,layoutParams);
        translateFragment.setArguments(bundle);
        return translateFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(getArguments()==null){
            return null;
        }
        int layout= getArguments().getInt(VIEW_LAYOUT);
        View view = inflater.inflate(layout,null);
        if(layout==R.layout.guide_five){
            ImageView mStartImageButton = (ImageView) view.findViewById(R.id.button_view);
            if (null != mStartImageButton) {
                mStartImageButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setGuided();
                        goLogin();
                    }
                });
            }
        }
        return view;
    }

    /**
     * 因为首次进来必定是没有任何登陆信息的，所以跳到LoginActivity页面
     */
    private void goLogin() {
        Intent intent = new Intent(this.getActivity(), LoginActivity.class);
        this.startActivity(intent);
        this.getActivity().finish();
    }

    /**
     * 设置已经引导过了，下次启动不用再次引导
     */
    private void setGuided() {
        PrefsUtils.getInstance().setValue(Constants.PREFERENCES_WELCOME_FALST, true);
    }

}
