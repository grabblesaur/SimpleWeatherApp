package com.bbayar.simpleweatherapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbayar.simpleweatherapp.R;
import com.bbayar.simpleweatherapp.model.City;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment{

    @BindView(R.id.detail_city_name)
    TextView cityName;
    @BindView(R.id.detail_date)
    TextView currentDate;
    @BindView(R.id.detail_description)
    TextView description;
    @BindView(R.id.detail_temp)
    TextView temperature;
    @BindView(R.id.detail_weather_icon)
    ImageView weatherIcon;
    @BindView(R.id.detail_wind)
    TextView wind;
    @BindView(R.id.detail_cloudy)
    TextView cloudy;
    @BindView(R.id.detail_humidity)
    TextView humidity;
    @BindView(R.id.detail_pressure)
    TextView pressure;
    @BindView(R.id.sunrise)
    TextView sunrise;
    @BindView(R.id.sunset)
    TextView sunset;

    public DetailFragment() {
    }

    public static DetailFragment newInstance(City city) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("city", city);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        City city = getArguments().getParcelable("city");
        if (city != null) {
            cityName.setText(city.getName());
            currentDate.setText(city.getDate());
            description.setText(city.getWeather().get(0).getDescription());
            temperature.setText(city.getMainInformation().getFormattedTemperature());
            weatherIcon.setImageResource(city.getWeather().get(0).getDrawableId());
            wind.setText(city.getWind().getFormattedWind());
            cloudy.setText(String.valueOf(city.getClouds().getAll()));
            humidity.setText(String.valueOf(city.getMainInformation().getHumidity()));
            pressure.setText(String.valueOf(city.getMainInformation().getPressure()));
            sunrise.setText(city.getTime());
            sunset.setText(city.getTime());
        }
    }
}
