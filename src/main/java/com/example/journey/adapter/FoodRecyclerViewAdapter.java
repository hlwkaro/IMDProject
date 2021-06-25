package com.example.journey.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.journey.R;
import com.example.journey.util.ItemEntity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.InnerHolder> {

    private final List<ItemEntity> mData;
    private View mItemView;
    private OnItemClickListener mOnItemClickListener;

    public FoodRecyclerViewAdapter(List<ItemEntity> Data){
            mData = Data;
    }
    @NonNull
    @NotNull
    @Override
    public FoodRecyclerViewAdapter.InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,parent,false);
        return new InnerHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FoodRecyclerViewAdapter.InnerHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.setData(mData.get(position));
    }
    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }


    /**
     * 编写回调的步骤
     * 1.创建接口
     * 2.定义接口内部的方法
     * 3.提供设置接口的方法
     * 4.接口方法的调用
     */

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        //设置一个监听，其实就是要设置一个接口，一个回调的接口
        mOnItemClickListener = listener;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private final ImageView micon;
        private final TextView mtitle;
        private final TextView mtip;

        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //找到控件
            micon = itemView.findViewById(R.id.food_cover);
            mtitle = itemView.findViewById(R.id.food_title);
            mtip = itemView.findViewById(R.id.food_tip);
            itemView.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick((Integer) itemView.getTag());
                }
            });
        }

        public void setData(ItemEntity itemEntity) {
            micon.setImageResource(itemEntity.icon);
            mtitle.setText(itemEntity.name);
            mtip.setText(itemEntity.tip);
        }
    }
}
