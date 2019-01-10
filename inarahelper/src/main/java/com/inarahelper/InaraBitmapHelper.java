package com.inarahelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class InaraBitmapHelper {


    @Nullable
    public static byte[] getByteArray(@NonNull Bitmap bitmap) {
        return getByteArray(bitmap, 50);
    }

    @Nullable
    public static byte[] getByteArray(@NonNull Bitmap bitmap, @IntRange(from = 0, to = 100) int quality) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, stream);
            return stream.toByteArray();
        } catch (OutOfMemoryError e) {
            return null;
        }
    }


    @Nullable
    public static Bitmap toBitmap(@NonNull Drawable drawable) {
        try {
            return ((BitmapDrawable) drawable).getBitmap();
        } catch (OutOfMemoryError e) {
            return null;
        }
    }


    @Nullable
    public static Bitmap getTintedBitmap(@NonNull Context context, @DrawableRes int resId, @ColorInt int color) {
        Drawable drawable = InaraDrawableHelper.get(context, resId);
        return getTintedBitmap(drawable, color);
    }

    @Nullable
    public static Bitmap getTintedBitmap(@NonNull Drawable drawable, @ColorInt int color) {
        Drawable tintedDrawable = InaraDrawableHelper.getTintedDrawable(drawable, color);
        if (tintedDrawable != null) return toBitmap(drawable);
        return null;
    }

    @Nullable
    public static Bitmap getTintedBitmap(@NonNull Bitmap bitmap, @ColorInt int color) {
        try {
            Paint paint = new Paint();
            paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            Bitmap tintedBitmap = Bitmap.createBitmap(
                    bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(tintedBitmap);
            canvas.drawBitmap(bitmap, 0, 0, paint);
            return tintedBitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    @Nullable
    public static Bitmap toBitmap(@NonNull Context context, @DrawableRes int resId) {
        Drawable drawable = InaraDrawableHelper.get(context, resId);
        return toBitmap(drawable);
    }

}
