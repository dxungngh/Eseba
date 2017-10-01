package com.eseba.jp.util;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by danielnguyen on 8/25/17.
 */

public class ApplicationUtil {
    private static final String TAG = ApplicationUtil.class.getSimpleName();

    public static int getHeightOfScreen(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        return height;
    }

    public static int getWidthOfScreen(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        return width;
    }
}
