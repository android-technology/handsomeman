package com.tt.handsomeman.util;

import android.content.Context;
import android.util.TypedValue;

public class DensityPixelToPixel {
    public static int dpToPx(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
