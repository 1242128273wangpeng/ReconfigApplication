package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model;

import com.changzhoureconfig.yimeng.com.reconfigapplication.api.CommonService;
import com.changzhoureconfig.yimeng.com.reconfigapplication.common.Constants;
import com.changzhoureconfig.yimeng.com.reconfigapplication.manage.ServiceManage;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BaseModel;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.RegisterBaseSettingContract;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.PrefsUtils;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class RegisterBaseSettingModel extends BaseModel implements RegisterBaseSettingContract.Model {
    @Inject
    ServiceManage serviceManage;
    @Inject
    public RegisterBaseSettingModel() {
    }


    @Override
    public Observable saveData(String nickName, String pregnancy) {
        // 保存到缓存中
        PrefsUtils.getInstance().setValue(Constants.PREGNANCY, pregnancy);
        // 保存到后台
        String id = PrefsUtils.getInstance().getValueFromKey(Constants.ID);
        return serviceManage.getCommonService().PostUserInfo(id, nickName, pregnancy);
    }
}
