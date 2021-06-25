package com.example.journey.adapter;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import com.example.journey.R;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;


public class IndicatorAdapter extends CommonNavigatorAdapter {
    private final String[] mTitles;
    private onIndicatorTabClickListener monTabClickListener;

    public IndicatorAdapter(Context context) {
        mTitles=context.getResources().getStringArray(R.array.indicator_title);
    }

    @Override
    public int getCount() {
        if (mTitles != null) {
            return mTitles.length;
        }
        return 0;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        //创建View
        ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
        //设置一般情况下的颜色为黑色
        colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
        //设置选中情况下的颜色为白色
        colorTransitionPagerTitleView.setSelectedColor(Color.WHITE);
        //设置字体大小单位sp
        colorTransitionPagerTitleView.setTextSize(18);
        //设置要显示的内容
        colorTransitionPagerTitleView.setText(mTitles[index]);
        //设置点击监听事件，点击title，对应的ViewPager切换
        colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换ViewPager的内容，如果index不一样的话
                if (monTabClickListener != null) {
                    monTabClickListener.onTabClick(index);
                }
            }
        });
        return colorTransitionPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
        linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
        linePagerIndicator.setColors(Color.WHITE);

        return linePagerIndicator;
    }

    public void setonIndicatorTabClickListener(onIndicatorTabClickListener listener){
        monTabClickListener = listener;
    }
    public interface onIndicatorTabClickListener{
       void onTabClick(int index);
    }
}
