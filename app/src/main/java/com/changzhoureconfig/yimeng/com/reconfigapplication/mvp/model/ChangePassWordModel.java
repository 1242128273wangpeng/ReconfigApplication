package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model;

import com.changzhoureconfig.yimeng.com.reconfigapplication.api.CommonService;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.BaseData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.manage.ServiceManage;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BaseModel;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.ChangePassWordContract;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/15 0015.
 */

public class ChangePassWordModel extends BaseModel implements ChangePassWordContract.Model {
    @Inject
    ServiceManage serviceManage;
    @Inject
    public ChangePassWordModel() {
    }

    @Override
    public Observable<BaseData> requestCode(String phone) {
        return serviceManage.getCommonService().AccessCode(phone, false);
    }

    @Override
    public Observable<BaseData> requestChangePassword(String phone, String code, String password) {
        return serviceManage.getCommonService().PostNewPasswordInfo(phone, code, password);
    }
}
