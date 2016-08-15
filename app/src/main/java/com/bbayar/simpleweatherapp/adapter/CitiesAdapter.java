package com.bbayar.simpleweatherapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbayar.simpleweatherapp.R;
import com.bbayar.simpleweatherapp.activity.MainActivity;
import com.bbayar.simpleweatherapp.activity.MainFragment;
import com.bbayar.simpleweatherapp.model.City;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private Context mContext;
    private List<City> mCityList;
    private MainFragment.MainFragmentCallbackInterface mListener;

    public CitiesAdapter(Context context, List<City> cities,
                         MainFragment.MainFragmentCallbackInterface listener) {
        mListener = listener;
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
        holder.setUpViews(currentCity, position, mListener);
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_icon_weather)
        ImageView iconWeather;
        @BindView(R.id.list_item_city_name)
        TextView cityName;
        @BindView(R.id.list_item_current_temperature)
        TextView currentTemp;

        City mCurrentCity;
        int mPosition;
        MainFragment.MainFragmentCallbackInterface mListener;

        public CityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setUpViews(City currentCity, final int position, MainFragment.MainFragmentCallbackInterface listener) {
            mCurrentCity = currentCity;
            mPosition = position;
            mListener = listener;

            iconWeather.setImageResource(mCurrentCity.getWeather().get(0).getDrawableId());
            cityName.setText(mCurrentCity.getName());
            currentTemp.setText(String.valueOf(mCurrentCity.getMainInformation().getTemp()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(MainActivity.TAG, "CitiesAdapter.onClick(): ");
                    mListener.onItemSelected(position);
                }
            });

        }

        @OnClick(R.id.list_item_icon_delete) void deleteItem(View view) {
            mCityList.remove(mPosition);
            notifyItemRemoved(mPosition);
            notifyItemRangeChanged(mPosition, mCityList.size());
        }
    }
}
