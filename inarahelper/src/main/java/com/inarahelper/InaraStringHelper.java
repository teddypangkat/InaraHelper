package com.inarahelper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.TypedValue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class InaraStringHelper {

    /**
     * cast String to MD5
     */
    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * encode String to base64
     */
    public static String encodeBase64(String value) {
        String encodePref = Base64.encodeToString(value.getBytes(), Base64.DEFAULT);
        return encodePref;
    }


    /**
     * decode base64
     */
    public static String decodeBase64(String value) {
        String decodeStr = null;
        if (value != null) {
            byte[] decodePref = Base64.decode(value, Base64.DEFAULT);
            decodeStr = new String(decodePref);
            return decodeStr;
        } else {
            //return null
            return "";
        }
    }


    // ================ FORMAT CURRENCY ======================

    /**
     * format mata uang RP
     *
     * @param value sample 200000
     * @return 200.000
     */
    public static String formatRupiahWithoutSymbol(double value) {
        Locale id = new Locale("in", "ID");
        return NumberFormat.getInstance(id).format(value);
    }


    public static String currency(String s) {
        String cc;
        try {
            Locale locale = new Locale("en", "UK");
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
            symbols.setDecimalSeparator(',');
            symbols.setGroupingSeparator('.');

            String pattern = "###,###.###";
            DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);

            cc = decimalFormat.format(Double.parseDouble(s)) + "";

        } catch (Exception e) {
            cc = "0";
        }

        return cc;
    }


    /**
     * format dp and sp
     */
    public static int dpToPx(Context context, float dp) {
        Resources r = context.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
        return px;
    }

    public static int pxToDp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int spToPx(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    public static int pxToSp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * share link/URL
     */
    public static void shareTextUrl(Context context, String url, String title) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, title);
        share.putExtra(Intent.EXTRA_TEXT, url);
        context.startActivity(Intent.createChooser(share, "Share link!"));
    }

    /**
     * cek connection device
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null) {
                return true;
            }
        }

        return false;
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }


    public static boolean is3gConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }


    public static boolean isGpsEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> accessibleProviders = lm.getProviders(true);
        for (String name : accessibleProviders) {
            if ("gps".equals(name)) {
                return true;
            }
        }
        return false;
    }
}

