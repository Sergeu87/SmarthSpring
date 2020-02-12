package com.example.smarthomeapp.events;

import com.example.smarthomeapp.BasePresenter;
import com.example.smarthomeapp.BaseView;
import com.example.smarthomeapp.httpentities.EventResponse;

import java.util.List;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class EventsContract {

    /**
     * The interface View.
     */
    interface View extends BaseView<EventsContract.Presenter> {

        /**
         * Sets loading indicator.
         *
         * @param active the active
         */
        void setLoadingIndicator(boolean active);

        /**
         * Show events.
         *
         * @param events the events
         */
        void showEvents(List<EventResponse> events);

        /**
         * Show events ui.
         *
         * @param events the events
         */
        void showEventsUi(List<EventResponse> events);

        /**
         * Show no events.
         */
        void showNoEvents();

        /**
         * Is active boolean.
         *
         * @return the boolean
         */
        boolean isActive();

    }

    /**
     * The interface Presenter.
     */
    interface Presenter extends BasePresenter {

        /**
         * Result.
         *
         * @param requestCode the request code
         * @param resultCode  the result code
         */
        void result(int requestCode, int resultCode);

        /**
         * Load events.
         */
        void loadEvents();

        /**
         * Gets dummy events.
         *
         * @return the dummy events
         */
        List<EventResponse> getDummyEvents();
    }


}
