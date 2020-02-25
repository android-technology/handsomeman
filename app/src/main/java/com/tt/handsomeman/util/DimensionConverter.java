package com.tt.handsomeman.util;

import android.content.Context;
import android.util.TypedValue;

public class DimensionConverter {
    public static int dpToPx(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float spToPx(float sp, Context context) {
        return sp / (context.getResources().getDisplayMetrics().scaledDensity);
    }
}
