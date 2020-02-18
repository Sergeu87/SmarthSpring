package com.example.smarthomeapp.util;

import com.example.smarthomeapp.devices.data.DevicesRepository;
import com.example.smarthomeapp.devices.data.remote.DevicesRemoteDataSource;
import com.example.smarthomeapp.divisions.data.DivisionsRepository;
import com.example.smarthomeapp.divisions.data.remote.DivisionsRemoteDataSource;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class Injection {

    /**
     * Provide divisions repository divisions repository.
     *
     * @return the divisions repository
     */
//    public static DivisionsRepository provideDivisionsRepository(@NonNull Context context) {
    public static DivisionsRepository provideDivisionsRepository() {
        return DivisionsRepository.getInstance(DivisionsRemoteDataSource.getInstance());
    }

    /**
     * Provide devices repository devices repository.
     *
     * @return the devices repository
     */
    public static DevicesRepository provideDevicesRepository() {
        return DevicesRepository.getInstance(DevicesRemoteDataSource.getInstance());
    }

//    public static AllControlRepository provideAllControlRepository(@NonNull Context context) {
//        return AllControlRepository.getInstance(AllControlRemoteDataSource.getInstance());
//    }
}
