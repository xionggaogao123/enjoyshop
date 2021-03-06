package com.enjoyshop;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import com.enjoyshop.bean.User;
import com.enjoyshop.service.LocationService;
import com.enjoyshop.utils.UserLocalData;
import com.enjoyshop.utils.Utils;
import com.mob.MobApplication;
import com.mob.MobSDK;


/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     time   : 2017/08/05
 *     desc   : 整个app的管理
 *     version: 1.0
 * </pre>
 */


public class EnjoyshopApplication extends MobApplication {

    //    mob信息    app key:201f8a7a91c30      App Secret:  c63ec5c1eeac1f873ec78c1365120431

    // ping ++    Ping++ 系统中标识你的应用标识     app_mjTmHS94izPOSWLK

    //百度地图的 ak   zbqExff1uz8XyUVn5GbyylomCa0rOkmP

    private User user;

    public         LocationService locationService;
    public         Vibrator        mVibrator;


    //整个app的上下文
    public static Context sContext;

    private static EnjoyshopApplication mInstance;


    public static EnjoyshopApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        // 通过代码注册你的AppKey和AppSecret
        MobSDK.init(this, "201f8a7a91c30", "c63ec5c1eeac1f873ec78c1365120431");

        sContext = getApplicationContext();
        initUser();
        Utils.init(this);

        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);

    }



    private void initUser() {
        this.user = UserLocalData.getUser(this);
    }


    public User getUser() {
        return user;
    }


    public void putUser(User user, String token) {
        this.user = user;
        UserLocalData.putUser(this, user);
        UserLocalData.putToken(this, token);
    }

    public void clearUser() {
        this.user = null;
        UserLocalData.clearUser(this);
        UserLocalData.clearToken(this);
    }


    public String getToken() {
        return UserLocalData.getToken(this);
    }

    private Intent intent;

    public void putIntent(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }

    public void jumpToTargetActivity(Context context) {
        context.startActivity(intent);
        this.intent = null;
    }


    public static EnjoyshopApplication getApplication() {
        return mInstance;
    }

}
