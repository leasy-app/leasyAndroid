package com.leasy.leasyAndroid.util;

import java.util.HashMap;
import java.util.Map;

public class Dates {
    private static Map<Integer, String> mNames = new HashMap<>();
    static{
        mNames.put(1,"Jan");
        mNames.put(2,"Feb");
        mNames.put(3,"Mar");
        mNames.put(4,"Apr");
        mNames.put(5,"May");
        mNames.put(6,"Jun");
        mNames.put(7,"Jul");
        mNames.put(8,"Aug");
        mNames.put(9,"Sep");
        mNames.put(10,"Oct");
        mNames.put(11,"Nov");
        mNames.put(12,"Dec");
    }
    public static String fixDate(String d){
        String f = d.split("T")[0];
        String[] x = f.split("-");
        String year = x[0];
        String month = x[1];
        String day = x[2];
        return mNames.get(Integer.parseInt(month)) + " " + day;
    }
}
