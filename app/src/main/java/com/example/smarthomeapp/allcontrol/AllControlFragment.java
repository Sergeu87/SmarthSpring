package com.example.smarthomeapp.allcontrol;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smarthomeapp.BaseFragment;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.devices.DevicesAdapter;
import com.example.smarthomeapp.httpentities.DeviceState;
import com.example.smarthomeapp.util.Injection;
import com.example.smarthomeapp.model.Device;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Serhii Razovyi on 10-Nov-19.
 */
public class AllControlFragment extends BaseFragment implements AllControlContract.View{

    private AllControlContract.Presenter mPresenter;
    private DevicesAdapter mAdapter;
    private List<DeviceState> _devicesStateList;

    /**
     * The M loader.
     */
    @BindView(R.id.devices_loader_view)
    View mLoader;

    /**
     * The M container view.
     */
    @BindView(R.id.devices_container_view)
    View mContainerView;

    /**
     * The M devices recycler view.
     */
    @BindView(R.id.devices_list)
    RecyclerView mDevicesRecyclerView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AllControlFragment.
     */
    public static AllControlFragment newInstance() {
        AllControlFragment fragment = new AllControlFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<DeviceState> devicesStateList = new ArrayList<>();


        mPresenter = new AllControlPresenter(
                devicesStateList,
                Injection.provideDevicesRepository(),
                this
        );

        mAdapter = new DevicesAdapter(
                getContext(),
                mPresenter,
                new LinkedList<Device>(),
                new LinkedList<DeviceState>()
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        View view = inflater.inflate(R.layout.fragment_devices, container, false);
        ButterKnife.bind(this, view);

        mDevicesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mDevicesRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void setPresenter(@NonNull AllControlContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mLoader.setVisibility(active ? View.VISIBLE : View.GONE);
        mContainerView.setVisibility(!active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showAllDevices(List<Device> devices, List<DeviceState> deviceStateRespons) {
       Toast.makeText(getContext(), "HALO  " + deviceStateRespons.size(), Toast.LENGTH_LONG).show();
        mAdapter.replaceData(devices, deviceStateRespons);
    }

    @Override
    public void showNoDevices() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
