package com.example.smarthomeapp.allcontrol;

import android.support.annotation.NonNull;

import com.example.smarthomeapp.app.SmartHomeApplication;
import com.example.smarthomeapp.devices.data.DevicesDataSource;
import com.example.smarthomeapp.devices.data.DevicesRepository;
import com.example.smarthomeapp.httpentities.DeviceState;
import com.example.smarthomeapp.model.Device;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Serhii Razovyi on 10-Nov-19.
 */
public class AllControlPresenter implements AllControlContract.Presenter {

    private final DevicesRepository mDevicesRepository;
    private final AllControlContract.View mAllControlView;

    private List<DeviceState> mDevicesStateResponsesList;
    private List<Device> mLoadedDevicesList;
    private List<Device> mDevicesList = new LinkedList<>();
    private List<DeviceState> mDevicesStateList = new LinkedList<>();


    /**
     * Instantiates a new All control presenter.
     *
     * @param deviceStateRespons the device state responses
     * @param devicesRepository    the devices repository
     * @param allControlView       the all control view
     */
    public AllControlPresenter(List<DeviceState> deviceStateRespons,
                               @NonNull DevicesRepository devicesRepository,
                               @NonNull AllControlContract.View allControlView){
        mDevicesStateResponsesList = deviceStateRespons;
        mLoadedDevicesList = SmartHomeApplication
                .getInstance()
                .getHomeConfiguration()
                .getDeviceList();
        mDevicesRepository = devicesRepository;
        mAllControlView = allControlView;
        mAllControlView.setPresenter(this);
    }

    @Override
    public void start() {
        loadAllDevices();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadAllDevices() {

        mAllControlView.setLoadingIndicator(true);

        mDevicesRepository.getAllDevices(new DevicesDataSource.LoadDevicesCallback() {
            @Override
            public void onDevicesLoaded(List<DeviceState> devicesState) {

                if (!mAllControlView.isActive()) {
                    return;
                }
                mAllControlView.setLoadingIndicator(false);
                List<Device> devicesList = SmartHomeApplication
                        .getInstance()
                        .getHomeConfiguration()
                        .getDeviceList();
                mAllControlView.showAllDevices(devicesList, devicesState);
            }

            @Override
            public void onDataNotAvailable() {
                mAllControlView.setLoadingIndicator(false);


                mAllControlView.showAllDevices(new ArrayList<Device>(), new ArrayList<DeviceState>());
            }
        });

        indexDevicesAndStates();
        mAllControlView.setLoadingIndicator(false);
        mAllControlView.showAllDevices(mDevicesList, mDevicesStateList);
    }

    private void indexDevicesAndStates(){

        for(DeviceState state : mDevicesStateResponsesList){
            String id = state.getDeviceId();
            mDevicesStateList.add(state);

            for(Device device : mLoadedDevicesList){
                if (device.getId().equals(id)){
                    mDevicesList.add(device);
                }
            }
        }
    }
}
