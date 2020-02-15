package com.example.smarthomeapp.httpentities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii Razovyi on 06-Nov-19.
 */
public class DeviceState implements Parcelable {

    /**
     * The Device id.
     */
    public String deviceId;
    /**
     * The Values.
     */
    public List<PropertyValueResponse> values;

    /**
     * Gets device id.
     *
     * @return the device id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets device id.
     *
     * @param deviceId the device id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Gets values.
     *
     * @return the values
     */
    public List<PropertyValueResponse> getValues() {
        return values;
    }

    /**
     * Sets values.
     *
     * @param values the values
     */
    public void setValues(List<PropertyValueResponse> values) {
        this.values = values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.deviceId);
        dest.writeList(this.values);
    }

    /**
     * Instantiates a new Device state response.
     */
    public DeviceState() {
    }

    /**
     * Instantiates a new Device state response.
     *
     * @param in the in
     */
    protected DeviceState(Parcel in) {
        this.deviceId = in.readString();
        this.values = new ArrayList<PropertyValueResponse>();
        in.readList(this.values, PropertyValueResponse.class.getClassLoader());
    }

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<DeviceState> CREATOR = new Parcelable.Creator<DeviceState>() {
        @Override
        public DeviceState createFromParcel(Parcel source) {
            return new DeviceState(source);
        }

        @Override
        public DeviceState[] newArray(int size) {
            return new DeviceState[size];
        }
    };
}
