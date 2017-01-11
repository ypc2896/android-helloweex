package com.android.helloweex;

import android.app.Application;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import java.io.File;

/**
 * Created by apple on 17/1/10.
 */

public class WxApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();


    File cacheDir = StorageUtils.getCacheDirectory(this);
    ImageLoaderConfiguration configuration =
        new ImageLoaderConfiguration.Builder(this).memoryCacheExtraOptions(480,
            800) // default = device screen dimensions
            .diskCacheExtraOptions(480, 800, null)
            //.taskExecutor(...)
            //.taskExecutorForCachedImages(...)
            //.threadPoolSize(3) // default
            //.threadPriority(Thread.NORM_PRIORITY - 2) // default
            //.tasksProcessingOrder(QueueProcessingType.FIFO) // default
            .denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2 * 1024 * 1024))
            //.memoryCacheSize(2 * 1024 * 1024)
            //.memoryCacheSizePercentage(13) // default
            .diskCache(new UnlimitedDiskCache(cacheDir)) // default
            .diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(100)
            .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
            //.imageDownloader(new BaseImageDownloader(context)) // default
            .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 5 * 1000))
            .build();

    ImageLoader.getInstance().init(configuration);

    InitConfig config=new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
    WXSDKEngine.initialize(this,config);
    try {
      WXSDKEngine.registerModule("weex_event", WxEventModel.class);
    }catch (WXException e){
      e.printStackTrace();
    }
  }
}
