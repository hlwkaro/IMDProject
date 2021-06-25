package com.example.journey.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.journey.DetailActivity;
import com.example.journey.R;
import com.example.journey.adapter.SceneryRecyclerViewAdapter;
import com.example.journey.util.Datas;
import com.example.journey.util.ItemEntity;
import com.example.journey.util.SwiperRes;
import com.example.journey.view.SwiperPager;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SceneryFragment extends BaseFragment {
    public static final String SCENERY_DATA = "com.example.journey.SCENERY_DATA";
    private View mrootView;
    private RecyclerView mRecyclerViewScenery;
    private SceneryRecyclerViewAdapter mSceneryAdapter;
    protected List<SwiperRes> mSwiperData = new ArrayList<>();
    protected SwiperPager mSwiperPager;
    protected List<ItemEntity> mDatas;

    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, @Nullable ViewGroup container) {
        mrootView = layoutInflater.inflate(R.layout.fragment_scenery,container,false);
        //设置数据
        initData();
        //设置布局
        initView();
        //设置监听事件
        initListener();
        return mrootView;
    }

    private void initView() {
        //使用轮播图
        mSwiperPager = mrootView.findViewById(R.id.swiper_page);
        mSwiperPager.setData(new SwiperPager.InnerAdapter() {

            @Override
            protected int getDataSize() {
                return 5;
            }

            @Override
            public View getSubView(ViewGroup container, int position) {
                ImageView iv = new ImageView(getContext());
                iv.setImageResource(mSwiperData.get(position).getPic());
                iv.setScaleType(ImageView.ScaleType.FIT_XY);

                return iv;
            }
        }, position -> mSwiperData.get(position).getTitle());


        //使用RecyclerView
        //1.找到控件
        mRecyclerViewScenery = mrootView.findViewById(R.id.recycle_view_scenery);
        //2.设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerViewScenery.setLayoutManager(linearLayoutManager);
        mRecyclerViewScenery.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
                outRect.bottom = UIUtil.dip2px(view.getContext(), 5);
                outRect.top = UIUtil.dip2px(view.getContext(), 5);
                outRect.left = UIUtil.dip2px(view.getContext(), 5);
                outRect.right = UIUtil.dip2px(view.getContext(), 5);
            }
        });
        //3.设置适配器
        mSceneryAdapter = new SceneryRecyclerViewAdapter(mDatas);
        mRecyclerViewScenery.setAdapter(mSceneryAdapter);
    }

    private void initListener() {
        mSceneryAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            ItemEntity message = mDatas.get(position);
            intent.putExtra(SCENERY_DATA,message);
            startActivity(intent);
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mDatas,from,to);
                mSceneryAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                mDatas.remove(viewHolder.getAdapterPosition());
                mSceneryAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(mRecyclerViewScenery);
    }

    private void initData() {
        mSwiperData.add(new SwiperRes("绚丽夺目的风光",R.mipmap.scenery_swiper_1));
        mSwiperData.add(new SwiperRes("清心悠远的世界",R.mipmap.scenery_swiper_2));
        mSwiperData.add(new SwiperRes("恢弘大气的古迹",R.mipmap.scenery_swiper_3));
        mSwiperData.add(new SwiperRes("宛如仙境的山水",R.mipmap.scenery_swiper_4));
        mSwiperData.add(new SwiperRes("苍茫奇险的峰岳",R.mipmap.scenery_swiper_5));
        mDatas = new ArrayList<>();
        for(int i = 0; i< Datas.getLength(3); i++){
            mDatas.add(Datas.getSceneryData(i));
        }
    }
}
