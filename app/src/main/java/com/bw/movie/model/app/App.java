package com.bw.movie.model.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * @author hyy
 * @date 2018/11/2
 */
public class App extends Application {

    public static Context context;

    public static IWXAPI mWxApi;

    public static String appid= "wxb3852e6a6b7d9516";
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = getApplicationContext();
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("images")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        //Fresco.initialize(this,Fresco_ImagePipelineConfigUtil.getDefaultImagePipelineConfig(this));

        registerToWX();
    }

    public static Context getApp() {
        return context;
    }

    private void registerToWX() {
        mWxApi = WXAPIFactory.createWXAPI(this, appid, false);
        // 将该app注册到微信
        mWxApi.registerApp(appid);
    }
}
