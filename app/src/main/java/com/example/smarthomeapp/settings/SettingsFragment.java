package com.example.smarthomeapp.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smarthomeapp.BaseFragment;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.login.LoginActivity;
import com.example.smarthomeapp.util.Constants;
import com.example.smarthomeapp.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

    /**
     * The M log out button.
     */
    @BindView(R.id.log_out_selectable_area)
    View mLogOutButton;

    /**
     * Instantiates a new Settings fragment.
     */
    public SettingsFragment() {
    }

    /**
     * New instance settings fragment.
     *
     * @return the settings fragment
     */
// TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);

        mLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLoginData();

                Intent mainIntent = new Intent(getContext(), LoginActivity.class);

                SettingsFragment.this.startActivity(mainIntent);
                getActivity().finish();
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

    private void removeLoginData(){
        SharedPreferencesUtils.setStringPreference(getContext(), Constants.Login.USER_ID, null);
        SharedPreferencesUtils.setBooleanPreference(getContext(), Constants.Login.REMEMBER_ME, false);
    }
}
