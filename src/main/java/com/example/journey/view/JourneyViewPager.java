package com.example.journey.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;



public class JourneyViewPager extends ViewPager {
    private final long delayTime = 2500;

    public JourneyViewPager(@NonNull @NotNull Context context) {
        this(context,null);
    }

    public JourneyViewPager(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener((v, event) -> {
           int action = event.getAction();
           switch (action){
               case MotionEvent.ACTION_DOWN:
                   stopLooper();
                   break;
               case MotionEvent.ACTION_CANCEL:
               case MotionEvent.ACTION_UP:
                   startLooper();
                   break;

           }
            return false;
        });


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startLooper();
    }

    private void startLooper() {
        postDelayed(mTask,delayTime);
    }
    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            int currentItem = getCurrentItem();
            currentItem++;
            setCurrentItem(currentItem);
            postDelayed(this,delayTime);
        }
    };
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLooper();
    }

    private void stopLooper() {
        removeCallbacks(mTask);
    }
}
