package com.example.smarthomeapp.devices;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smarthomeapp.BaseFragment;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.httpentities.DeviceState;
import com.example.smarthomeapp.util.Injection;
import com.example.smarthomeapp.model.Device;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DevicesFragment extends BaseFragment implements DevicesContract.View{

    private DevicesContract.Presenter mPresenter;

    private DevicesAdapter mAdapter;

    private String mDivisionId;

    /**
     * The constant DEVICES_STATES_ARG.
     */
    public static String DEVICES_STATES_ARG = "DEVICES_STATES_ARG";
    /**
     * The constant DIVISION_ID_ARG.
     */
    public static String DIVISION_ID_ARG = "DIVISION_ID_ARG";

    /**
     * The M loader.
     */
    @BindView(R.id.devices_loader_view)
    View mLoader;

    /**
     * The M no devices view.
     */
    @BindView(R.id.empty_state_view)
    View mNoDevicesView;
    /**
     * The M empty state icon.
     */
    @BindView(R.id.empty_state_icon)
    ImageView mEmptyStateIcon;
    /**
     * The M empty state text.
     */
    @BindView(R.id.empty_state_text)
    TextView mEmptyStateText;

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
     * @param divisionId           the division id
     * @param deviceStates the device state responses
     * @return A new instance of fragment DivisionsFragment.
     */
    public static DevicesFragment newInstance(String divisionId, List<DeviceState> deviceStates) {
        DevicesFragment fragment = new DevicesFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(DEVICES_STATES_ARG, new ArrayList<Parcelable>(deviceStates));
        args.putString(DIVISION_ID_ARG, divisionId);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Instantiates a new Devices fragment.
     */
    public DevicesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<DeviceState> devicesStates = new ArrayList<>();
        if (getArguments() != null) {
            devicesStates = getArguments().getParcelableArrayList(DEVICES_STATES_ARG);
            mDivisionId = getArguments().getString(DIVISION_ID_ARG);
        }


        mPresenter = new DevicesPresenter(
                devicesStates,
                Injection.provideDevicesRepository(),
                this,
                mDivisionId
        );

        mAdapter = new DevicesAdapter(
                getContext(),
                mPresenter,
                new LinkedList<Device>(),
                new LinkedList<DeviceState>()
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_devices, container, false);
        ButterKnife.bind(this, view);

        mDevicesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mDevicesRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void setPresenter(DevicesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mLoader.setVisibility(active ? View.VISIBLE : GONE);
        mContainerView.setVisibility(!active ? View.VISIBLE : GONE);
    }

    @Override
    public void showDevices(List<Device> devices, List<DeviceState> deviceStateRespons) {
//        Toast.makeText(getContext(), "HALO  " + deviceStateRespons.size(), Toast.LENGTH_LONG).show();
        mAdapter.replaceData(devices, deviceStateRespons);
    }

    @Override
    public void showNoDevices() {
        mContainerView.setVisibility(GONE);
        mNoDevicesView.setVisibility(View.VISIBLE);
        mEmptyStateText.setText(getString(R.string.no_devices_all));
    }

    @Override
    public boolean isActive() {
        return isActive();
    }

    @Override
    public void showFailedUpdate() {

    }

    @Override
    public void showSuccessfulUpdate() {

    }
}
