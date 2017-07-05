package com.safetyhuge.chanlian;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.safetyhuge.chanlian.safetyhuge.IM.common.CCPAppManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.dialog.ECAlertDialog;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.CrashHandler;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECNotificationManager;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferenceSettings;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.ECPreferences;
import com.safetyhuge.chanlian.safetyhuge.IM.common.utils.FileAccessor;
import com.safetyhuge.chanlian.safetyhuge.IM.ui.SDKCoreHelper;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.yuntongxun.ecsdk.ECDevice;

import java.io.File;
import java.io.InvalidClassException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import cn.smssdk.SMSSDK;

/**
 * @author BSJ
 * @ClassName: FHandler
 * @Description:
 * @email:xxxx@xxx.xxx
 * @date 2016-6-12
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class ECApplication extends TinkerApplication {
    private static List<Activity> activityList = new LinkedList<Activity>();
    public static Context context;
    public static Handler mainHandler;//主线程的handler
    private static ECApplication instance;

    protected ECApplication(int tinkerFlags) {
        super(tinkerFlags);
    }

    protected ECApplication(int tinkerFlags, String delegateClassName, String loaderClassName, boolean tinkerLoadVerifyFlag) {
        super(tinkerFlags, delegateClassName, loaderClassName, tinkerLoadVerifyFlag);
    }

    protected ECApplication(int tinkerFlags, String delegateClassName) {
        super(tinkerFlags, delegateClassName);
    }
    public ECApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.safetyhuge.chanlian.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
    /**
     * 单例，返回一个实例
     * @return
     */
    public static ECApplication getInstance() {
        if (instance == null) {
            KLog.e("[ECApplication] instance is null.");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //1.初始化Context,Android三大Context:Application,Activity,Service
        context = this;
        //存储quanquan
        CCPAppManager.setContext(instance);
        FileAccessor.initFileAccess();
        setChattingContactId();
        initImageLoader();
        CrashHandler.getInstance().init(this);
        //必须调用初始化
        OkGo.init(this);
        //初始化bugly
        Beta.initDelay = 6 * 1000;
        Bugly.init(getApplicationContext(), "3a9c8204aa", true);
        //1.初始化Context,Android三大Context:Application,Activity,Service
        //2.初始化mainHandler
        mainHandler = new Handler();
        //初始化短信
        SMSSDK.initSDK(this, "1cdd078476fb7", "29be3231631008c5163b2d763f9d360c");

        //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
        OkGo.getInstance()

                // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                .debug("OkGo", Level.INFO, true)

                //如果使用默认的 60秒,以下三行也不需要传
                .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                .setCacheMode(CacheMode.NO_CACHE)

                //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                .setRetryCount(3)

                //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
//              .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
                .setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效

                //可以设置https的证书,以下几种方案根据需要自己设置
                .setCertificates();                                  //方法一：信任所有证书,不安全有风险
    }

    // 添加Activity到容器中
    public static void addActivity(Activity activity) {
        KLog.e("添加");
        activityList.add(activity);
    }

    // 遍历所有Activity并finish
    public static void exit() {
        if (activityList != null && activityList.size() > 0) {
            for (Activity activity : activityList) {
                activity.finish();
            }
        }
        System.exit(0);
    }
    private void initImageLoader() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "ECSDK_Demo/image");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .threadPoolSize(1)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(new WeakMemoryCache())
                // .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(CCPAppManager.md5FileNameGenerator)
                // 将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCache(new UnlimitedDiskCache(cacheDir ,null ,CCPAppManager.md5FileNameGenerator))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                // .writeDebugLogs() // Remove for release app
                .build();//开始构建
        ImageLoader.getInstance().init(config);
       // //判断SDK是否已经初始化
       // if(ECDevice.isInitialized()==true) {
        /*    registerReceiver(new String[] {
                    IMChattingHelper.INTENT_ACTION_SYNC_MESSAGE,
                    SDKCoreHelper.ACTION_SDK_CONNECT,
                    SDKCoreHelper.ACTION_KICK_OFF
            });*/
       // }
    }

    //异地登录
    public void handlerKickOff(String kickoffText) {
        ECAlertDialog buildAlert = ECAlertDialog.buildAlert(this, kickoffText,
                getString(R.string.dialog_btn_confim),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ECNotificationManager.getInstance()
                                .forceCancelNotification();
                        restartAPP();
                    }
                });
        buildAlert.setTitle("异地登陆");
        buildAlert.setCanceledOnTouchOutside(false);
        buildAlert.setCancelable(false);
        buildAlert.show();
    }
    public void restartAPP() {
        ECDevice.unInitial();
        KLog.e("whb", "restartAPP");
        SharedPrefsUtil.clear(ECApplication.getInstance());
        SDKCoreHelper.logout(false);
    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
    /**
     * 保存当前的聊天界面所对应的联系人、方便来消息屏蔽通知
     */
    private void setChattingContactId() {
        try {
            ECPreferences.savePreference(ECPreferenceSettings.SETTING_CHATTING_CONTACTID, "", true);
        } catch (InvalidClassException e) {
            e.printStackTrace();
        }
    }

}
  