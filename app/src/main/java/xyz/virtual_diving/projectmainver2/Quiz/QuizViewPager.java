package xyz.virtual_diving.projectmainver2.Quiz;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by b1014100 on 2016/06/21.
 * 参考にしたサイト
 * http://blackfox.cocolog-nifty.com/blog/2014/12/android-viewpag.html
 */
public class QuizViewPager extends ViewPager {

    public QuizViewPager(Context context) {
        super(context);
    }

    public QuizViewPager(Context context, AttributeSet attrs) {
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
