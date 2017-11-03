package com.example.dell.sqllightdemo.Activity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tej710 on 06-01-2017.
 */

public class Appinfo implements Parcelable {
    private int ID;
    private String APP_NAME;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAPP_NAME() {
        return APP_NAME;
    }

    public void setAPP_NAME(String APP_NAME) {
        this.APP_NAME = APP_NAME;
    }

    public Appinfo(int ID, String APP_NAME) {

        this.ID = ID;
        this.APP_NAME = APP_NAME;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ID);
        dest.writeString(this.APP_NAME);
    }

    protected Appinfo(Parcel in) {
        this.ID = in.readInt();
        this.APP_NAME = in.readString();
    }

    public static final Parcelable.Creator<Appinfo> CREATOR = new Parcelable.Creator<Appinfo>() {
        @Override
        public Appinfo createFromParcel(Parcel source) {
            return new Appinfo(source);
        }

        @Override
        public Appinfo[] newArray(int size) {
            return new Appinfo[size];
        }
    };
}
