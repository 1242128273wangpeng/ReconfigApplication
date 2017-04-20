package com.changzhoureconfig.yimeng.com.reconfigapplication.dagger.module.common;


import com.changzhoureconfig.yimeng.com.reconfigapplication.imageloader.BaseImageLoaderStrategy;
import com.changzhoureconfig.yimeng.com.reconfigapplication.imageloader.ImageLoader;
import com.changzhoureconfig.yimeng.com.reconfigapplication.imageloader.glide.GlideImageLoaderStrategy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author wangpeng
 * @time 2017/4/5 0005
 */
@Module
public class ImageModule  {
    @Singleton
    @Provides
    public BaseImageLoaderStrategy provideImageLoaderStrategy() {
        return new GlideImageLoaderStrategy();
    }

    @Singleton
    @Provides
    public ImageLoader provideImageLoader(BaseImageLoaderStrategy strategy) {
        return new ImageLoader(strategy);
    }
}
