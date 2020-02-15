package com.example.smarthomeapp.httpentities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Serhii Razovyi on 06-Nov-19.
 */
public class DevicesResponse implements Parcelable {

    /**
     * The Devices values.
     */
    public List<DeviceState> devicesValues;

    /**
     * Instantiates a new Devices response.
     *
     * @param devicesValues the devices values
     */
    public DevicesResponse(List<DeviceState> devicesValues) {
        this.devicesValues = devicesValues;
    }

    /**
     * Gets devices values.
     *
     * @return the devices values
     */
    public List<DeviceState> getDevicesValues() {
        return devicesValues;
    }

    /**
     * Sets devices values.
     *
     * @param devicesValues the devices values
     */
    public void setDevicesValues(List<DeviceState> devicesValues) {
        this.devicesValues = devicesValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.devicesValues);
    }

    /**
     * Instantiates a new Devices response.
     *
     * @param in the in
     */
    protected DevicesResponse(Parcel in) {
        this.devicesValues = in.createTypedArrayList(DeviceState.CREATOR);
    }

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<DevicesResponse> CREATOR = new Parcelable.Creator<DevicesResponse>() {
        @Override
        public DevicesResponse createFromParcel(Parcel source) {
            return new DevicesResponse(source);
        }

        @Override
        public DevicesResponse[] newArray(int size) {
            return new DevicesResponse[size];
        }
    };
}
