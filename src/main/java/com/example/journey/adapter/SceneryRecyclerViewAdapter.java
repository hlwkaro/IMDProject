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

public class SceneryRecyclerViewAdapter extends RecyclerView.Adapter<SceneryRecyclerViewAdapter.InnerHolder>{
    private final List<ItemEntity> mData;
    private View mItemView;
    private SceneryRecyclerViewAdapter.OnItemClickListener mOnItemClickListener;
    public SceneryRecyclerViewAdapter(List<ItemEntity> Data){
        mData = Data;
    }
    @NonNull
    @NotNull
    @Override
    public SceneryRecyclerViewAdapter.InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scenery,parent,false);
        return new SceneryRecyclerViewAdapter.InnerHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SceneryRecyclerViewAdapter.InnerHolder holder, int position) {
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
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(SceneryRecyclerViewAdapter.OnItemClickListener listener) {
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
            micon = itemView.findViewById(R.id.scenery_cover);
            mtitle = itemView.findViewById(R.id.scenery_title);
            mtip = itemView.findViewById(R.id.scenery_tip);
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
