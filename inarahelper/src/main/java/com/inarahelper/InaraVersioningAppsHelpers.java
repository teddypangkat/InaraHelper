package com.inarahelper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;

/**
 * Created by teddy on 1/13/17.
 */

public class InaraVersioningAppsHelpers {

    public static InaraVersioningAppsHelpers instance;
    PackageInfo pInfo;
    Context context;

    public InaraVersioningAppsHelpers(Context context) {
        this.context = context;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            pInfo = null;
        }
    }

    public static InaraVersioningAppsHelpers getInstance(Context context) {
        if (instance == null) {
            instance = new InaraVersioningAppsHelpers(context);
        }
        return instance;
    }

    public String versionNameApps(){
        if (pInfo!=null){
            return pInfo.versionName;
        }
        return "";
    }
    public int versionCodeApps(){
        if (pInfo!=null){
            return pInfo.versionCode;
        }
        return 0;
    }
    public String versionPublicApps(){
        if (pInfo!=null){
            return pInfo.versionName+"."+pInfo.versionCode;
//            return pInfo.versionName+".9";
        }
        return "";
    }
    public String versionOS(){
        return  Build.VERSION.RELEASE;
    }
    public String deviceID(){
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public String getScreeSizeInch(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        int dens=dm.densityDpi;
        double wi=(double)width/(double)dens;
        double hi=(double)height/(double)dens;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
        double screenInches = Math.sqrt(x+y);
        return new DecimalFormat("##.#").format(screenInches)+"inch";
    }

    public String versionManufacture(){
        return versionModel()+" "+Build.MANUFACTURER;
    }
    public String versionModel(){
        return Build.MODEL;
    }

}
