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

public class ArtRecyclerViewAdapter extends RecyclerView.Adapter<ArtRecyclerViewAdapter.InnerHolder> {

    private final List<ItemEntity> mData;
    private View mItemView;
    private OnItemClickListener mOnItemClickListener;

    public ArtRecyclerViewAdapter(List<ItemEntity> Data){
        mData = Data;
    }
    @NonNull
    @NotNull
    @Override
    public ArtRecyclerViewAdapter.InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_art,parent,false);
        return new InnerHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ArtRecyclerViewAdapter.InnerHolder holder, int position) {
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

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }
    public class InnerHolder extends RecyclerView.ViewHolder {

        private final ImageView micon;
        private final TextView mtitle;
        private final TextView mtip;

        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            micon = itemView.findViewById(R.id.art_cover);
            mtitle = itemView.findViewById(R.id.art_title);
            mtip = itemView.findViewById(R.id.art_tip);
            itemView.setOnClickListener( v -> {
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
