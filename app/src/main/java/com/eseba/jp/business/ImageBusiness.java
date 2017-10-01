package com.eseba.jp.business;

import android.content.Context;
import android.graphics.BitmapFactory;

/**
 * Created by danielnguyen on 8/25/17.
 */

public class ImageBusiness {
    public static final String TAG = ImageBusiness.class.getSimpleName();

    private Context context;

    public ImageBusiness(Context context) {
        this.context = context;
    }

//    public Bitmap[] createImageResourcesForViewPager(Activity activity, List<Banner> bannerList) {
//        Bitmap[] imageResources = new Bitmap[bannerList.size()];
//        for (int i = 0; i < bannerList.size(); i++) {
//            Bitmap image = this.decodeSampledBitmapFromResource(this.context.getResources(),
//                bannerList.get(i).getImageUrl(),
//                ApplicationUtil.getWidthOfScreen(activity),
//                ApplicationUtil.getWidthOfScreen(activity));
//            imageResources[i] = image;
//        }
//        return imageResources;
//    }
//
//    private Bitmap decodeSampledBitmapFromResource(Resources res,
//                                                   String imageUrl,
//                                                   int reqWidth,
//                                                   int reqHeight) {
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(res, resId, options);
//        options.inSampleSize = this.calculateInSampleSize(options, reqWidth, reqHeight);
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeResource(res, resId, options);
//    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        while (true) {
            if (width <= reqWidth && height <= reqHeight) {
                break;
            } else {
                inSampleSize *= 2;
                width = width / 2;
                height = height / 2;
            }
        }
        return inSampleSize;
    }
}
