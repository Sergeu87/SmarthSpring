package com.example.smarthomeapp.divisions;

import android.support.annotation.NonNull;

import com.example.smarthomeapp.app.SmartHomeApplication;
import com.example.smarthomeapp.divisions.data.DivisionsDataSource;
import com.example.smarthomeapp.divisions.data.DivisionsRepository;
import com.example.smarthomeapp.httpentities.DeviceState;
import com.example.smarthomeapp.model.Division;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii Razovyi on 19-Oct-19.
 */
public class DivisionsPresenter implements DivisionsContract.Presenter {

    private final DivisionsRepository mDivisionsRepository;
    private final DivisionsContract.View mDivisionsView;
    private List<Division> mDivisionsList;

    /**
     * Instantiates a new Divisions presenter.
     *
     * @param divisionsRepository the divisions repository
     * @param divisionsView       the divisions view
     */
    public DivisionsPresenter(@NonNull DivisionsRepository divisionsRepository,
                              @NonNull DivisionsContract.View divisionsView) {
        mDivisionsRepository = divisionsRepository;
        mDivisionsView = divisionsView;
        mDivisionsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadDivisions();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void openDevicesList(int divisionPosition) {

        mDivisionsView.setLoadingIndicator(true);

        final String divisionId = mDivisionsList.get(divisionPosition).getId();

        mDivisionsRepository.getDevices(divisionId, new DivisionsDataSource.LoadDevicesCallback() {
            @Override
            public void onDevicesLoaded(List<DeviceState> deviceStates) {

                if (!mDivisionsView.isActive()) {
                    return;
                }
                mDivisionsView.setLoadingIndicator(false);
                mDivisionsView.showDivisionDevicesUi(divisionId, deviceStates);
            }

            @Override
            public void onDataNotAvailable() {
                mDivisionsView.setLoadingIndicator(false);


                mDivisionsView.showDivisionDevicesUi(divisionId, new ArrayList<DeviceState>());
            }
        });
    }

    @Override
    public void loadDivisions() {

        mDivisionsList = SmartHomeApplication
                .getInstance()
                .getHomeConfiguration()
                .getDivisionList();

        mDivisionsView.showDivisions(mDivisionsList);
    }
}
