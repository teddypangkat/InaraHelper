package com.inarahelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by teddy on 2/3/17.
 */

public class InaraActivityHelper {

    public static void clearStackAndStartNewActivity(Context cxt, Class<?> cls) {
        Intent intent = new Intent(cxt, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        cxt.startActivity(intent);
    }

    public static void finishToParent(Activity activity, boolean shouldUpRecreateTask) {
        if(activity == null) return;
        Intent upIntent = NavUtils.getParentActivityIntent(activity);
        if(upIntent != null) {
            if(NavUtils.shouldUpRecreateTask(activity, upIntent) || shouldUpRecreateTask) {
                // This activity is NOT part of this app's task, so
                // create a
                // new task
                // when navigating up, with a synthesized back stack.
                TaskStackBuilder.create(activity)
                        // Add all of this activity's parents to the back stack
                        .addNextIntentWithParentStack(upIntent)
                        // Navigate up to the closest parent
                        .startActivities();
                activity.finish();
            }
            else {
                // This activity is part of this app's task, so simply
                // navigate up to the logical parent activity.
                NavUtils.navigateUpTo(activity, upIntent);
            }
        }
        else {
            activity.finish();
        }
    }

    public static void setActivityToFullScreen(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void ActivityKeepScreenOn(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


}
