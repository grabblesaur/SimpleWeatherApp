package com.bbayar.simpleweatherapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bbayar.simpleweatherapp.R;
import com.bbayar.simpleweatherapp.adapter.CitiesAdapter;
import com.bbayar.simpleweatherapp.model.City;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<City> mCityList;
    private CitiesAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        Log.d(MainActivity.TAG, "MainFragment.onCreateView(): ");

        mCityList = getArguments().getParcelableArrayList(MainActivity.KEY_LIST);
        mAdapter = new CitiesAdapter(getContext(), mCityList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public CitiesAdapter getAdapter() {
        return mAdapter;
    }
}
