package com.tt.handsomeman.util;

public class DecimalFormat {
    public static String format(double doubleValue) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
        return df.format(doubleValue);
    }
}
