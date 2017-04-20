package com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.model;

import com.changzhoureconfig.yimeng.com.reconfigapplication.api.CommonService;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.BaseData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.manage.ServiceManage;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.base.BaseModel;
import com.changzhoureconfig.yimeng.com.reconfigapplication.mvp.contract.RegisterContract;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
 // 在Module中不是dagger2提供注入的，是new 出来的所以不要添加这个注解  @ActivityScope
public class RegisterModel extends BaseModel  implements RegisterContract.Model {
    @Inject
    ServiceManage serviceManage;
    @Inject
    public RegisterModel() {
    }

    @Override
    public Observable<BaseData> requestCode(String phone) {
        return serviceManage.getCommonService().AccessCode(phone,true);
    }

    @Override
    public Observable<BaseData> requestRegister(String phone, String password, String code, String pregnancy) {
        return serviceManage.getCommonService().AccessRegister(phone, password, code, pregnancy);
    }
}
