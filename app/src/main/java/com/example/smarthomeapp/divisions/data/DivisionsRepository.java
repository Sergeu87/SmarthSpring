package com.example.smarthomeapp.divisions.data;

import android.support.annotation.NonNull;

import com.example.smarthomeapp.httpentities.DeviceStateResponse;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class DivisionsRepository implements DivisionsDataSource {

    private static DivisionsRepository INSTANCE = null;

    private final DivisionsDataSource mDivisionsRemoteDataSource;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    private DivisionsRepository(@NonNull DivisionsDataSource divisionsRemoteDataSource){
        mDivisionsRemoteDataSource = checkNotNull(divisionsRemoteDataSource);
    }

    /**
     * Gets instance.
     *
     * @param tasksRemoteDataSource the tasks remote data source
     * @return the instance
     */
    public static DivisionsRepository getInstance(DivisionsDataSource tasksRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DivisionsRepository(tasksRemoteDataSource);
        }
        return INSTANCE;
    }

    /**
     * Destroy instance.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getDevices(String divisionId, @NonNull final LoadDevicesCallback callback) {
        checkNotNull(callback);

        mDivisionsRemoteDataSource.getDevices(divisionId, new LoadDevicesCallback() {
            @Override
            public void onDevicesLoaded(List<DeviceStateResponse> deviceStateResponses) {
                callback.onDevicesLoaded(deviceStateResponses);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
