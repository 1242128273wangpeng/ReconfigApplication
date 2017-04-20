package com.changzhoureconfig.yimeng.com.reconfigapplication.api;


import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.AdvertImageData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.BaseData;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.BaseData_2;
import com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user.LoginData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface CommonService {

    @GET("app/login.do")
    Observable<LoginData> AccessLogin(@Query("phone") String phone, @Query("password") String password, @Query("registrationId") String registrationId);

    /**
     * @param phone
     * @param password  MD5加密后的密码
     * @param code      验证码
     * @param pregnancy 末次
     * @return
     */
    @GET("app/register.do")
    Observable<BaseData> AccessRegister(@Query("phone") String phone, @Query("password") String password, @Query("code") String code, @Query("pregnancy") String pregnancy);

    @GET("app/imageList.do")
    Observable<AdvertImageData> AccessAdvert();

    @GET("/app/smsSend.do")
    Observable<BaseData> AccessCode(@Query("phone") String phone, @Query("type") boolean type);

    @GET("/app/firstModify.do")
    Observable<BaseData_2> PostUserInfo(@Query("id") String id, @Query("nickname") String nickname, @Query("pregnancy") String pregnancy);

    @GET("/app/modifyPD.do")
    Observable<BaseData> PostNewPasswordInfo(@Query("phone") String phone, @Query("code") String code, @Query("password") String password);
}
