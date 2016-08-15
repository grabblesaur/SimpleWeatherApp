package com.bbayar.simpleweatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind implements Parcelable{
    @SerializedName("speed")
    @Expose
    public Double speed;
    @SerializedName("deg")
    @Expose
    public Double deg;
    @SerializedName("gust")
    @Expose
    public Double gust;

    public Wind(Double speed, Double deg, Double gust) {
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.speed);
        parcel.writeDouble(this.deg);
        parcel.writeDouble(this.gust);
    }

    protected Wind(Parcel in) {
        this.speed = in.readDouble();
        this.deg = in.readDouble();
        this.gust = in.readDouble();
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    public Double getGust() {
        return gust;
    }

    public void setGust(Double gust) {
        this.gust = gust;
    }

    public StringBuilder getFormattedWind() {
        StringBuilder windSpeed = new StringBuilder();
        windSpeed.append(speed);
        windSpeed.append(" m/s");
        return windSpeed;
    }
}
