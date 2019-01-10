package com.inarahelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

public class InaraDrawableHelper {


    public static int getResourceId(@NonNull Context context, String resName) {
        try {
            return context.getResources().getIdentifier(
                    resName, "drawable", context.getPackageName());
        } catch (Exception ignored) {
        }
        return -1;
    }

    @NonNull
    public static Drawable get(@NonNull Context context, @DrawableRes int resId) {
        Drawable drawable = ContextCompat.getDrawable(context, resId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }
        return drawable;
    }

    @Nullable
    public static Drawable getTintedDrawable(@NonNull Context context, @DrawableRes int resId, @ColorInt int color) {
        Drawable drawable = get(context, resId);
        return getTintedDrawable(drawable, color);
    }

    @Nullable
    public static Drawable getTintedDrawable(@NonNull Drawable drawable, @ColorInt int color) {
        try {
            DrawableCompat.setTint(drawable, color);
            return drawable.mutate();
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    @Nullable
    public static Drawable getResizedDrawable(@NonNull Context context, @NonNull Drawable drawable, float sizeInDp) {
        try {
            int size = Math.round(InaraStringHelper.dpToPx(context, sizeInDp));

            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);

            return new BitmapDrawable(context.getResources(),
                    Bitmap.createScaledBitmap(bitmap, size, size, true));
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    @Nullable
    public static Drawable toDrawable(@NonNull Context context, @NonNull Bitmap bitmap) {
        try {
            return new BitmapDrawable(context.getResources(), bitmap);
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}
