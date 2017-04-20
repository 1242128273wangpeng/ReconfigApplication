package com.changzhoureconfig.yimeng.com.reconfigapplication.manage;

import com.changzhoureconfig.yimeng.com.reconfigapplication.api.CommonService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2017/4/20 0020.
 */
@Singleton
public class ServiceManage {
    private CommonService commonService;

    @Inject
    public ServiceManage(CommonService commonService) {
        this.commonService = commonService;
    }

    public CommonService getCommonService() {
        return commonService;
    }
}
