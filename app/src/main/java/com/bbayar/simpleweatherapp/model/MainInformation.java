package com.bbayar.simpleweatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainInformation implements Parcelable {
    @SerializedName("temp")
    @Expose
    public Double temp;
    @SerializedName("pressure")
    @Expose
    public Double pressure;
    @SerializedName("humidity")
    @Expose
    public Long humidity;
    @SerializedName("temp_min")
    @Expose
    public Double tempMin;
    @SerializedName("temp_max")
    @Expose
    public Double tempMax;

    public MainInformation(Double temp, Double pressure, Long humidity,
                           Double tempMin, Double tempMax) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    protected MainInformation(Parcel in) {
        this.temp = in.readDouble();
        this.pressure = in.readDouble();
        this.humidity = in.readLong();
        this.tempMin = in.readDouble();
        this.tempMax = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.temp);
        parcel.writeDouble(this.pressure);
        parcel.writeLong(this.humidity);
        parcel.writeDouble(this.tempMin);
        parcel.writeDouble(this.tempMax);
    }

    public static final Creator<MainInformation> CREATOR = new Creator<MainInformation>() {
        @Override
        public MainInformation createFromParcel(Parcel in) {
            return new MainInformation(in);
        }

        @Override
        public MainInformation[] newArray(int size) {
            return new MainInformation[size];
        }
    };

    public Double getTemp() {
        return temp;
    }

    public String getFormattedTemperature() {
        return String.valueOf(Math.round(temp));
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }
}
