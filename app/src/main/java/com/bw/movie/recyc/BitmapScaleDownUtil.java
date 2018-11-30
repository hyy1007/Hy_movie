package com.bw.movie.recyc;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Display;

public class BitmapScaleDownUtil {
    /* 数据段begin */
    private final String TAG = "BitmapScaleDownUtil";
    /* 数据段end */
    /* 函数段begin */

    /**
     * 21      * @function 获取屏幕大小
     * 22      * @param display
     * 23      * @return 屏幕宽高
     * 24
     */
    public static int[] getScreenDimension(Display display) {
        int[] dimension = new int[2];
        dimension[0] = display.getWidth();
        dimension[1] = display.getHeight();

        return dimension;
    }

    /**
     * 35      * @function 以取样方式加载Bitmap
     * 36      * @param res
     * 37      * @param resId
     * 38      * @param reqWidth
     * 39      * @param reqHeight
     * 40      * @return 取样后的Bitmap
     * 41
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // step1，将inJustDecodeBounds置为true，以解析Bitmap真实尺寸
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // step2，计算Bitmap取样比例
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // step3，将inJustDecodeBounds置为false，以取样比列解析Bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 58      * @function 计算Bitmap取样比例
     * 59      * @param options
     * 60      * @param reqWidth
     * 61      * @param reqHeight
     * 62      * @return 取样比例
     * 63
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 默认取样比例为1:1
        int inSampleSize = 1;
        // Bitmap原始尺寸
        final int width = options.outWidth;
        final int height = options.outHeight;

        // 取最大取样比例
        if (height > reqHeight || width > reqWidth) {
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            final int heightRatio = Math.round((float) height / (float) reqHeight);

            // 取样比例为X:1，其中X>=1
            inSampleSize = Math.max(widthRatio, heightRatio);
        }
        return inSampleSize;
    }
    /* 函数段end */
}
