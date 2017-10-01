package com.eseba.jp.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by danielnguyen on 8/22/17.
 */

public class MyTextView extends AppCompatTextView {

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
//        if (this.isInEditMode()) return;
//
//        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EsebaTextView);
//        final String customFont = context.getString(R.string.eseba_text_view_font);
//
//        //Build a custom typeface-cache here!
//        this.setTypeface(
//            Typeface.createFromAsset(context.getAssets(), customFont)
//        );
    }
}
