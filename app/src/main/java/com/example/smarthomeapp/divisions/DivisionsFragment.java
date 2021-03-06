package com.example.smarthomeapp.divisions;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smarthomeapp.MainActivity;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.BaseFragment;
import com.example.smarthomeapp.devices.DevicesFragment;
import com.example.smarthomeapp.httpentities.DeviceState;
import com.example.smarthomeapp.util.Injection;
import com.example.smarthomeapp.model.Division;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DivisionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DivisionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DivisionsFragment extends BaseFragment implements DivisionsContract.View{

    private DivisionsContract.Presenter mDivisionsPresenter;

    private DivisionsAdapter mDivisionsAdapter;


    /**
     * The Divisions grid view.
     */
    @BindView(R.id.divisions_grid_view)
    GridView divisionsGridView;

    /**
     * The M divisions view.
     */
    @BindView(R.id.divisions_view)
    View mDivisionsView;

    /**
     * The M no divisions view.
     */
    @BindView(R.id.empty_state_view)
    View mNoDivisionsView;
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
     * The M loader.
     */
    @BindView(R.id.divisions_loader_view)
    View mLoader;

    /**
     * The M container view.
     */
    @BindView(R.id.divisions_container_view)
    View mContainerView;

    private OnFragmentInteractionListener mListener;

    /**
     * Instantiates a new Divisions fragment.
     */
    public DivisionsFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DivisionsFragment.
     */
    public static DivisionsFragment newInstance() {
        DivisionsFragment fragment = new DivisionsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mDivisionsPresenter = new DivisionsPresenter(Injection.provideDivisionsRepository(), this);

        mDivisionsAdapter = new DivisionsAdapter(getContext(), mDivisionsPresenter, new ArrayList<Division>(0));
    }

    @Override
    public void onResume() {
        super.onResume();
        mDivisionsPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);


        View view = inflater.inflate(R.layout.fragment_divisions, container, false);
        ButterKnife.bind(this, view);

        divisionsGridView.setAdapter(mDivisionsAdapter);

        divisionsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                mDivisionsPresenter.openDevicesList(position);
//                Toast.makeText(getContext(), "Room", Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPresenter(@NonNull DivisionsContract.Presenter presenter) {
        mDivisionsPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mLoader.setVisibility(active ? View.VISIBLE : View.GONE);
        mContainerView.setVisibility(!active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showDivisions(List<Division> divisions) {

        mDivisionsAdapter.replaceData(divisions);

        mDivisionsView.setVisibility(View.VISIBLE);
        mNoDivisionsView.setVisibility(View.GONE);
    }

    @Override
    public void showDivisionDevicesUi(String divisionId, List<DeviceState> deviceStates) {
        ((MainActivity) getActivity()).replaceFragment(DevicesFragment.newInstance(divisionId, deviceStates), R.string.header_devices);
    }

    @Override
    public void showNoDivisions() {
        mDivisionsView.setVisibility(View.GONE);
        mNoDivisionsView.setVisibility(View.VISIBLE);
        mEmptyStateText.setText(getString(R.string.no_divisions_all));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
