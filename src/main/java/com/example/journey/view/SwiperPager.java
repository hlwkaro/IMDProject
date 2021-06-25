package com.example.journey.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.journey.R;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import org.jetbrains.annotations.NotNull;

public class SwiperPager extends LinearLayout {

    private ViewPager mViewPager;
    private TextView mTextView;
    private LinearLayout mLinearLayout;
    private bindTitleListener mTitleSetListener = null;
    private InnerAdapter mInnerAdapter = null;

    public SwiperPager(Context context) {
        this(context,null);
    }

    public SwiperPager(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SwiperPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.swiper_page_layout,this,true);
        init();
    }
    private void init() {
        initView();
        initEvent();
    }
    private void initEvent() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //切换的回调
            }

            @Override
            public void onPageSelected(int position) {
                //切换停下来的回调
                if (mInnerAdapter != null) {
                    int realPosition = position%mInnerAdapter.getDataSize();

                    if (mTitleSetListener != null) {
                        mTextView.setText(mTitleSetListener.getTitle(realPosition));
                    }
                    //切换指示器焦点
                    setUpIndicatorPoint();

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //状态的改变
            }
        });
    }


    public interface bindTitleListener{
        String getTitle(int position);
    }

    public void setData(InnerAdapter innerAdapter,bindTitleListener listener){
        this.mTitleSetListener = listener;
        mViewPager.setAdapter(innerAdapter);
        mViewPager.setCurrentItem(Integer.MAX_VALUE/2+2);
        this.mInnerAdapter = innerAdapter;
        if (listener != null) {
            mTextView.setText(listener.getTitle(mViewPager.getCurrentItem()%mInnerAdapter.getDataSize()));
        }
        //可以拿到数据个数，根据个数创建圆点
        setUpIndicatorPoint();

    }

    public abstract static class InnerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
            container.removeView((View) object);
        }
        @NonNull
        @NotNull
        @Override
        public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
            int realPosition = position % getDataSize();
            View view = getSubView(container,realPosition);
            container.addView(view);
            return view;
        }

        protected abstract int getDataSize();

        public abstract View getSubView(ViewGroup container, int position);
    }

    private void setUpIndicatorPoint() {
        if (mInnerAdapter != null&&mTitleSetListener!=null) {
            int count = 5;
            mLinearLayout.removeAllViews();
            for (int i = 0; i < count; i++) {
                View pointer = new View(getContext());
                if(mViewPager.getCurrentItem()%mInnerAdapter.getDataSize()==i){
                    pointer.setBackground(getResources().getDrawable(R.drawable.shape_circle_red));

                }else{
                    pointer.setBackground(getResources().getDrawable(R.drawable.shape_circle_white));

                }

                //设置大小
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtil.dip2px(getContext(),8),UIUtil.dip2px(getContext(),8));
                layoutParams.setMargins(UIUtil.dip2px(getContext(),10),0,0,0);
                pointer.setLayoutParams(layoutParams);

                //添加到容器里去
                mLinearLayout.addView(pointer);

            }
        }
    }

    private void initView() {
        mViewPager = this.findViewById(R.id.swiper_pager_vp);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageMargin(UIUtil.dip2px(getContext(),20));
        mTextView = this.findViewById(R.id.swiper_pager_title);
        mLinearLayout = this.findViewById(R.id.swiper_point_container);
    }
}
