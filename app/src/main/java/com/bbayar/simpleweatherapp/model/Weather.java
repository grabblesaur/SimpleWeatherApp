package com.bbayar.simpleweatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bbayar.simpleweatherapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather implements Parcelable{
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("mMainInformation")
    @Expose
    public String main;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("icon")
    @Expose
    public String icon;

    public Weather(Long id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    protected Weather(Parcel in) {
        this.id = in.readLong();
        this.main = in.readString();
        this.description = in.readString();
        this.icon = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.main);
        parcel.writeString(this.description);
        parcel.writeString(this.icon);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getDrawableId() {
        switch (description) {
            case "clear-sky":
                return R.drawable.sunny;
            case "broken clouds":
                return R.drawable.very_cloudy;
            case "light rain":
                return R.drawable.light_rain;
            case "moderate rain":
                return R.drawable.light_rain;
            default:
                return R.drawable.not_available;
        }
    }
}
