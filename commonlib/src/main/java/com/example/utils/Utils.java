package com.example.utils;

/**
 * Created by Serhii Razovyi on 05-Nov-19.
 */
public class Utils {

    /**
     * Strip string into v string [ ].
     *
     * @param strList   the str list
     * @param separator the separator
     * @return the string [ ]
     */
    public static String[] stripStringIntoV(String strList, String separator){
        String[] separatedValues = strList.split(separator);
        return separatedValues;
    }

}
