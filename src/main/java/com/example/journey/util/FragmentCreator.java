package com.example.journey.util;

import com.example.journey.fragment.ArtFragment;
import com.example.journey.fragment.BaseFragment;
import com.example.journey.fragment.FoodFragment;
import com.example.journey.fragment.SceneryFragment;

import java.util.HashMap;
import java.util.Map;


public class FragmentCreator {
    public static final int INDEX_SCENERY = 1;
    public static final int INDEX_ART = 2;
    public static final int INDEX_FOOD = 0;
    public static final int PAGE_COUNT= 3;
    private static Map<Integer, BaseFragment> sCache = new HashMap<>();

    public static BaseFragment getFragment(int index){
        BaseFragment baseFragment = sCache.get(index);

        if (baseFragment != null) {
            return baseFragment;
        }
        switch (index){
            case INDEX_SCENERY:
                baseFragment = new SceneryFragment();
                break;
            case INDEX_ART:
                baseFragment = new ArtFragment();
                break;
            case INDEX_FOOD:
                baseFragment = new FoodFragment();
                break;

        }
        sCache.put(index,baseFragment);
        return baseFragment;
    }
}
