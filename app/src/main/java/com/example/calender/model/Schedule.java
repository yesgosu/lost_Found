package com.example.calender.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Schedule implements Parcelable {

    public int numberId = 0;
    public final String title;
    public final String content;
    public final String ga;


    public Schedule(int numberId, String title, String content, String ga) {
        this.numberId = numberId;
        this.title = title;
        this.content = content;
        this.ga = ga;
    }

    public Schedule(String ga, String title, String content) {
        this.ga = ga;
        this.title = title;
        this.content = content;
    }

    public Schedule(JSONObject object) throws JSONException {
        this.numberId = object.getInt("numberid");
        this.title = object.getString("title");
        this.content = object.getString("content");
        this.ga = object.getString("ga");
    }

    protected Schedule(Parcel in) {
        numberId = in.readInt();
        title = in.readString();
        content = in.readString();
        ga = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numberId);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(ga);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };
}
