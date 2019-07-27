package me.star.emoji.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;


public class DisplayUtils {
    private static Context context = AppUtils.application;
    private float picHHeight;

    /**
     * dp2px
     */
    public static int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px2dp
     */
    public static int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据设备信息获取当前分辨率下指定单位对应的像素大小；
     * px,dip,sp -> px
     */
    public static float getRawSize(float size) {
        Resources r;
        if (context == null) {
            r = Resources.getSystem();
        } else {
            r = context.getResources();
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, size, r.getDisplayMetrics());
    }

    public static int getWindowWidth() {
        int width;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        return width;
    }

    public static int getWindowHeight() {
        int height;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        height = metrics.heightPixels;
        return height;
    }

    /**
     * px转换sp
     *
     * @param pxValue
     * px数值
     * @return sp数值
     */
    private static DisplayMetrics mDisplayMetrics;

    public static int px2sp(float pxValue) {
        mDisplayMetrics = getDisplayMetrics();
        return (int) (pxValue / mDisplayMetrics.scaledDensity + 0.5f);
    }

    /**
     * 获取系统显示材质
     ***/
    public static DisplayMetrics getDisplayMetrics() {
        WindowManager windowMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics DisplayMetrics = new DisplayMetrics();
        windowMgr.getDefaultDisplay().getMetrics(DisplayMetrics);
        return DisplayMetrics;
    }

    public static int getWith(int count) {
        float screen_width = DisplayUtils.getWindowWidth();
        float item_area_width = screen_width - DisplayUtils.dip2px(32f);
        float picHHeight = (float) (item_area_width * 0.98 / count);
        return (int) picHHeight;
    }

}
