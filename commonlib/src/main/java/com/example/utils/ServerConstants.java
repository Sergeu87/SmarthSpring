package com.example.utils;

/**
 * Created by Serhii Razovyi on 06-Nov-19.
 */
public interface ServerConstants {

    /*
     * RESTful API Stuff Constants
     */

    /**
     * The interface Handlers.
     */
    interface Handlers {
        /**
         * The constant EVENTS.
         */
        String EVENTS = "events";
        /**
         * The constant DIVISIONS.
         */
        String DIVISIONS = "divisions";
        /**
         * The constant DEVICES.
         */
        String DEVICES = "devices";
        /**
         * The constant OVERVIEW.
         */
        String OVERVIEW = "overview";
        /**
         * The constant BAR.
         */
        String BAR = "/";
    }

    /*
     * JSON responses Constants
     */

    /**
     * The interface Json keys.
     */
    interface JsonKeys {
        /**
         * The constant DEVICE_ID.
         */
        String DEVICE_ID = "deviceId";
        /**
         * The constant DEVICES_VALUES.
         */
        String DEVICES_VALUES = "deviceValues";
        /**
         * The constant VALUE.
         */
        String VALUE = "value";
    }

    /*
     * Command Constants
     */

    /**
     * The interface Command.
     */
    interface Command {
        /**
         * The constant GET.
         */
        String GET = "GET";
        /**
         * The constant INIT.
         */
        String INIT = "INIT";
        /**
         * The constant SET.
         */
        String SET = "SET";
    }

    /*
     * Command Constants
     */

    /**
     * The interface Crud.
     */
    interface CRUD {
        /**
         * The constant GET.
         */
        String GET = "GET";
        /**
         * The constant PUT.
         */
        String PUT = "PUT";
        /**
         * The constant POST.
         */
        String POST = "POST";
        /**
         * The constant DELETE.
         */
        String DELETE = "DELETE";
    }
}
