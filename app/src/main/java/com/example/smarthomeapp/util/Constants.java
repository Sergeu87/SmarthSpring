package com.example.smarthomeapp.util;

import com.example.smarthomeapp.R;

/**
 * Created by Serhii Razovyi on 04-Nov-19.
 */
public class Constants {

    /**
     * The constant XML_FILE_TO_LOAD.
     */
    public static int XML_FILE_TO_LOAD = R.raw.basic_config_1;

    /**
     * The interface Division icon type.
     */
    interface DivisionIconType {
        /**
         * The constant BEDROOM.
         */
        String BEDROOM = "1";
        /**
         * The constant KITCHEN.
         */
        String KITCHEN = "2";
        /**
         * The constant BATHROOM.
         */
        String BATHROOM = "3";
        /**
         * The constant HALL.
         */
        String HALL = "4";
        /**
         * The constant ATTIC.
         */
        String ATTIC = "5";
        /**
         * The constant LIVING_ROOM.
         */
        String LIVING_ROOM = "6";
        /**
         * The constant GARDEN.
         */
        String GARDEN = "7";
    }

    /**
     * The interface Device icon type.
     */
    interface DeviceIconType {
        /**
         * The constant ADJUSTABLE_LIGHT.
         */
        String ADJUSTABLE_LIGHT = "1";
        /**
         * The constant TEMPERATURE_SENSOR.
         */
        String TEMPERATURE_SENSOR = "2";
        /**
         * The constant OVEN.
         */
        String OVEN = "4";
        /**
         * The constant HUMIDITY_RATIO.
         */
        String HUMIDITY_RATIO = "5";
    }

    /**
     * The interface Login.
     */
    public interface Login {
        /**
         * The constant USERNAME.
         */
        String USERNAME = "USERNAME";
        /**
         * The constant USER_ID.
         */
        String USER_ID = "USER_ID";
        /**
         * The constant REMEMBER_ME.
         */
        String REMEMBER_ME = "REMEMBER_ME";
    }
}
