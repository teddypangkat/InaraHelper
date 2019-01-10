package com.inarahelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by teddy on 1/16/17.
 */

public class InaraRegexHelper {

    public static final String ALPHABET_NUMBER = "^[a-zA-Z0-9 _]*[a-zA-Z0-9][a-zA-Z0-9 _]*$";
    public static final String ALPHABET = "^[a-zA-Z _]*[a-zA-Z][a-zA-Z _]*$";
    public static final String NUMBER = "^[0-9]*[0-9][0-9]*$";



    public static boolean alphabet_number(String value){
        Matcher m = Pattern.compile(ALPHABET_NUMBER).matcher(value);
        return m.find();
    }


    public static boolean alphabet(String value){
        Matcher m = Pattern.compile(ALPHABET).matcher(value);
        return m.find();
    }


    public static boolean number(String value){
        Matcher m = Pattern.compile(NUMBER).matcher(value);
        return m.find();
    }

    /**
     * uri email macther
     */
    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
