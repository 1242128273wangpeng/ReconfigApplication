package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model;

import com.changzhoureconfig.yimeng.com.reconfigapplication.api.CommonService;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.common.Constants;
import com.changzhoureconfig.yimeng.com.reconfigapplication.manage.ServiceManage;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BaseModel;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.LoginContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.PrefsUtils;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class LoginModel extends BaseModel implements LoginContract.Model {
    @Inject
    ServiceManage serviceManage;
    @Inject
    public LoginModel() {

    }

    @Override
    public Observable<LoginData> requestLogin(String phone, String password, String registrationId) {
        return serviceManage.getCommonService().AccessLogin(phone, password, registrationId);
    }

    @Override
    public String getRegistrationId() {
        return PrefsUtils.getInstance().getValueFromKey(Constants.PREFERENCES_PUSHTOKEN);
    }
}
