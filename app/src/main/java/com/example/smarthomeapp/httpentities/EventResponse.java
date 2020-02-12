package com.example.smarthomeapp.httpentities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class EventResponse implements Parcelable {

    /**
     * The User id.
     */
    public String userId;
    /**
     * The Device id.
     */
    public String deviceId;
    /**
     * The Division id.
     */
    public String divisionId;
    /**
     * The Device value.
     */
    public String deviceValue;
    /**
     * The Property id.
     */
    public String propertyId;
    /**
     * The Time stamp.
     */
    public String timeStamp;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

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
     * Gets device value.
     *
     * @return the device value
     */
    public String getDeviceValue() {
        return deviceValue;
    }

    /**
     * Sets device value.
     *
     * @param deviceValue the device value
     */
    public void setDeviceValue(String deviceValue) {
        this.deviceValue = deviceValue;
    }

    /**
     * Gets time stamp.
     *
     * @return the time stamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets time stamp.
     *
     * @param timeStamp the time stamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets division id.
     *
     * @return the division id
     */
    public String getDivisionId() {
        return divisionId;
    }

    /**
     * Sets division id.
     *
     * @param divisionId the division id
     */
    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Gets property id.
     *
     * @return the property id
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * Sets property id.
     *
     * @param propertyId the property id
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * Instantiates a new Event response.
     */
    public EventResponse() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.deviceId);
        dest.writeString(this.divisionId);
        dest.writeString(this.deviceValue);
        dest.writeString(this.propertyId);
        dest.writeString(this.timeStamp);
    }

    /**
     * Instantiates a new Event response.
     *
     * @param in the in
     */
    protected EventResponse(Parcel in) {
        this.userId = in.readString();
        this.deviceId = in.readString();
        this.divisionId = in.readString();
        this.deviceValue = in.readString();
        this.propertyId = in.readString();
        this.timeStamp = in.readString();
    }

    /**
     * The constant CREATOR.
     */
    public static final Creator<EventResponse> CREATOR = new Creator<EventResponse>() {
        @Override
        public EventResponse createFromParcel(Parcel source) {
            return new EventResponse(source);
        }

        @Override
        public EventResponse[] newArray(int size) {
            return new EventResponse[size];
        }
    };
}
