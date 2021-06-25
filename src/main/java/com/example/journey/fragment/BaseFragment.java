package com.example.journey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.journey.util.ItemEntity;
import com.example.journey.util.SwiperRes;
import com.example.journey.view.SwiperPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseFragment extends Fragment {
    private View mrootView;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        mrootView = onSubViewLoaded(inflater,container);
        return mrootView;
    }
    @Nullable
    @org.jetbrains.annotations.Nullable
    protected abstract View onSubViewLoaded(LayoutInflater layoutInflater, @org.jetbrains.annotations.Nullable ViewGroup container);
}
