package com.tcl.frun.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by weiminding on 3/1/17.
 */

public final class UserData implements Parcelable {


    public long distance;
    public long duration;
    public long pace;
    public long cals;

    public UserData() {

    }

    private UserData(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(distance);
        parcel.writeLong(duration);
        parcel.writeLong(pace);
        parcel.writeLong(cals);
    }


    public void readFromParcel(Parcel in) {

        distance = in.readLong();
        duration = in.readLong();
        pace = in.readLong();
        cals = in.readLong();
    }

    @Override
    public String toString() {
        return "distance - "+distance +" duration - "+duration+" pace - "+pace+ " cals - "+cals;
    }


}
