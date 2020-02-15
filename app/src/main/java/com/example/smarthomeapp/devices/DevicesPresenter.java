package com.example.smarthomeapp.devices;

import android.support.annotation.NonNull;

import com.example.smarthomeapp.app.SmartHomeApplication;
import com.example.smarthomeapp.devices.data.DevicesDataSource;
import com.example.smarthomeapp.devices.data.DevicesRepository;
import com.example.smarthomeapp.httpentities.DeviceState;
import com.example.smarthomeapp.model.Device;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class DevicesPresenter implements DevicesContract.Presenter{

    private final DevicesRepository mDevicesRepository;
    private final DevicesContract.View mDevicesView;

    private Map<String, String> mDevicesValuesToSave;

    private List<DeviceState> mDevicesStateResponsesList;
    private List<Device> mLoadedDevicesList;
    private List<Device> mDevicesList = new LinkedList<>();
    private List<DeviceState> mDevicesStateList = new LinkedList<>();

    /**
     * Instantiates a new Devices presenter.
     *
     * @param deviceStateRespons the device state responses
     * @param devicesRepository    the devices repository
     * @param devicesView          the devices view
     * @param divisionId           the division id
     */
    public DevicesPresenter(List<DeviceState> deviceStateRespons,
                            @NonNull DevicesRepository devicesRepository,
                            @NonNull DevicesContract.View devicesView,
                            String divisionId) {

        mDevicesStateResponsesList = deviceStateRespons;
        mLoadedDevicesList = SmartHomeApplication
                .getInstance()
                .getHomeConfiguration()
                .getDevicesByDivisionID(divisionId);
        mDevicesRepository = devicesRepository;
        mDevicesView = devicesView;
        mDevicesView.setPresenter(this);
    }

    @Override
    public void start() {
        loadDevices();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void saveDeviceValue(Map<String,String> devicesValues) {
        mDevicesView.setLoadingIndicator(true);

        mDevicesRepository.updateDeviceValue(devicesValues, new DevicesDataSource.UpdateDeviceValueCallback(){

            @Override
            public void onDeviceValueUpdated(boolean isUpdated) {

                mDevicesView.setLoadingIndicator(false);
                mDevicesView.showSuccessfulUpdate();
            }

            @Override
            public void onDataNotAvailable() {

                mDevicesView.setLoadingIndicator(false);
                mDevicesView.showFailedUpdate();
            }
        });
    }

    @Override
    public void loadDevices() {

        indexDevicesAndStates();
        mDevicesView.setLoadingIndicator(false);
        if (mDevicesStateList.size() != 0) {
            mDevicesView.showDevices(mDevicesList, mDevicesStateList);
        } else {
            mDevicesView.showNoDevices();
        }
    }

    @Override
    public Map<String,String> getDeviceValuesToSave() {
        return mDevicesValuesToSave;
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
