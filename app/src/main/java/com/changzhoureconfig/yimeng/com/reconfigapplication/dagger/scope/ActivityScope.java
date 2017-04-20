package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {}
