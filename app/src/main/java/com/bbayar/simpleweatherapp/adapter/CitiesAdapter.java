package com.bbayar.simpleweatherapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bbayar.simpleweatherapp.R;
import com.bbayar.simpleweatherapp.model.City;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private Context mContext;
    private List<City> mCityList;

    public CitiesAdapter(Context context, List<City> cities) {
        mContext = context;
        mCityList = cities;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        City currentCity = mCityList.get(position);
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {

        public CityViewHolder(View itemView) {
            super(itemView);
        }
    }
}
