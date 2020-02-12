package com.example.smarthomeapp.httpentities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Serhii Razovyi on 06-Nov-19.
 */
public class PropertyValueResponse implements Parcelable {

    /**
     * The Property id.
     */
    public String propertyId;
    /**
     * The Property value.
     */
    public String propertyValue;

    /**
     * Instantiates a new Property value response.
     *
     * @param propertyId    the property id
     * @param propertyValue the property value
     */
    public PropertyValueResponse(String propertyId, String propertyValue) {
        this.propertyId = propertyId;
        this.propertyValue = propertyValue;
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
     * Gets property value.
     *
     * @return the property value
     */
    public String getPropertyValue() {
        return propertyValue;
    }

    /**
     * Sets property value.
     *
     * @param propertyValue the property value
     */
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.propertyId);
        dest.writeString(this.propertyValue);
    }

    /**
     * Instantiates a new Property value response.
     *
     * @param in the in
     */
    protected PropertyValueResponse(Parcel in) {
        this.propertyId = in.readString();
        this.propertyValue = in.readString();
    }

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<PropertyValueResponse> CREATOR = new Parcelable.Creator<PropertyValueResponse>() {
        @Override
        public PropertyValueResponse createFromParcel(Parcel source) {
            return new PropertyValueResponse(source);
        }

        @Override
        public PropertyValueResponse[] newArray(int size) {
            return new PropertyValueResponse[size];
        }
    };
}
