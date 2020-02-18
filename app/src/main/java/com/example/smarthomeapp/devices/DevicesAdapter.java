package com.example.smarthomeapp.devices;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.allcontrol.AllControlContract;
import com.example.smarthomeapp.app.SmartHomeApplication;
import com.example.smarthomeapp.httpentities.DeviceState;
import com.example.smarthomeapp.httpentities.PropertyValueResponse;
import com.example.smarthomeapp.util.IconUtils;
import com.example.smarthomeapp.model.Device;
import com.example.smarthomeapp.model.Division;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder> {

    private List<Device> mDevicesList;
    private List<DeviceState> mDevicesStateList;
    private DevicesContract.Presenter mDevicesPresenter;
    private AllControlContract.Presenter mAllControlPresenter;
    private Context mContext;
    private boolean _isByDivisions;

    /**
     * Instantiates a new Devices adapter.
     *
     * @param context         the context
     * @param presenter       the presenter
     * @param devicesMap      the devices map
     * @param devicesStateMap the devices state map
     */
    public DevicesAdapter(Context context,
                          DevicesContract.Presenter presenter,
                          List<Device> devicesMap,
                          List<DeviceState> devicesStateMap){
        this.mContext = context;
        this.mDevicesPresenter = presenter;
        this.mDevicesList = devicesMap;
        this.mDevicesStateList = devicesStateMap;
        this._isByDivisions = true;
    }

    /**
     * Instantiates a new Devices adapter.
     *
     * @param context         the context
     * @param presenter       the presenter
     * @param devicesMap      the devices map
     * @param devicesStateMap the devices state map
     */
    public DevicesAdapter(Context context,
                          AllControlContract.Presenter presenter,
                          List<Device> devicesMap,
                          List<DeviceState> devicesStateMap){
        this.mContext = context;
        this.mAllControlPresenter = presenter;
        this.mDevicesList = devicesMap;
        this.mDevicesStateList = devicesStateMap;
        this._isByDivisions = false;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_item, parent, false);

        final DeviceViewHolder deviceViewHolder = new DeviceViewHolder(view);
        return deviceViewHolder;
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {

        final DeviceState deviceState = mDevicesStateList.get(position);
        final Device device = mDevicesList.get(position);

        holder.deviceIcon.setImageResource(IconUtils.getDevicesIconsMap().get(device.getRefDeviceType()));
        holder.deviceName.setText(device.getName());

        if(!_isByDivisions){
            holder.deviceDivision.setVisibility(View.VISIBLE);
            Division division = SmartHomeApplication
                    .getInstance()
                    .getHomeConfiguration()
                    .getDivisionByID(device.getRefDivision());
            holder.deviceDivision.setText(division.getName());
        }
        holder.deviceName.setText(device.getName());

        holder.propertiesList.setLayoutManager(new LinearLayoutManager(mContext));
        holder.propertiesList.setAdapter(new DevicePropertiesAdapter(
                        mContext,
                        device.getId(),
                        device.getRefDeviceType(),
                        deviceState.getValues()
                )
        );

        holder.saveValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View propertyListView = ((LinearLayout) ((ViewGroup) view.getParent())
                        .getParent()).findViewById(R.id.property_list);
                final DevicePropertiesAdapter devicePropertiesAdapter = (DevicePropertiesAdapter) ((RecyclerView) propertyListView).getAdapter();

                final PropertyValueResponse currentDeviceValue = devicePropertiesAdapter.getmCurrentPropertyValueResponse();

                final DeviceState request = new DeviceState();
                request.deviceId = devicePropertiesAdapter.getmDeviceId();
                final ArrayList<PropertyValueResponse> values = new ArrayList<>();
                values.add(currentDeviceValue);
                request.values = values;
                mDevicesPresenter.saveDeviceValue(request);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDevicesStateList.size();
    }

    /**
     * Replace data.
     *
     * @param devices      the devices
     * @param devicesState the devices state
     */
    public void replaceData(List<Device> devices, List<DeviceState> devicesState) {
        setDevicesStateList(devices, devicesState);
        this.notifyDataSetChanged();
    }

    private void setDevicesStateList(List<Device> devices, List<DeviceState> devicesState) {
        mDevicesList.clear();
        mDevicesList.addAll(devices);
        mDevicesStateList.clear();
        mDevicesStateList.addAll(devicesState);
    }

    /**
     * The type Device view holder.
     */
    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Device icon.
         */
        @BindView(R.id.device_icon)
        ImageView deviceIcon;
        /**
         * The Device name.
         */
        @BindView(R.id.device_name)
        TextView deviceName;
        /**
         * The Device division.
         */
        @BindView(R.id.device_division)
        TextView deviceDivision;
        /**
         * The Properties list.
         */
        @BindView(R.id.property_list)
        RecyclerView propertiesList;
        /**
         * The Save value button.
         */
        @BindView(R.id.save_value_button)
        Button saveValueButton;

        /**
         * Instantiates a new Device view holder.
         *
         * @param view the view
         */
        public DeviceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
