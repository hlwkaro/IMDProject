package com.example.journey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journey.fragment.ArtFragment;
import com.example.journey.fragment.FoodFragment;
import com.example.journey.fragment.SceneryFragment;
import com.example.journey.util.ItemEntity;

public class DetailActivity extends AppCompatActivity {
    private ImageView mdetail_pic;
    private TextView mdetail_name;
    private TextView mdetail_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        ItemEntity mData=null;

        while(true) {
            if ((ItemEntity) intent.getSerializableExtra(FoodFragment.ITEM_DATA) != null) {
                mData = (ItemEntity) intent.getSerializableExtra(FoodFragment.ITEM_DATA);
                break;
            }else if((ItemEntity) intent.getSerializableExtra(ArtFragment.ART_DATA) != null){
                mData = (ItemEntity) intent.getSerializableExtra(ArtFragment.ART_DATA);
                break;
            }else if((ItemEntity) intent.getSerializableExtra(SceneryFragment.SCENERY_DATA) != null){
                mData = (ItemEntity) intent.getSerializableExtra(SceneryFragment.SCENERY_DATA);
                break;
            }
        }
        initView();
        setData(mData);
    }

    private void setData(ItemEntity item) {
        mdetail_pic.setImageResource(item.icon);
        mdetail_name.setText(item.name);
        mdetail_content.setText(item.the_content);
    }

    private void initView() {
        mdetail_pic = this.findViewById(R.id.food_detail_pic);
        mdetail_name = this.findViewById(R.id.food_detail_name);
        mdetail_content = this.findViewById(R.id.food_detail_content);
    }

}