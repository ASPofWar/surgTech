package com.sianware.surgtech;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Aaron on 7/2/13.
 */
public class SomewhatSwipeableViewPager extends ViewPager
{
    public SomewhatSwipeableViewPager(Context context) {
        super(context);
    }

    public SomewhatSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }

}
