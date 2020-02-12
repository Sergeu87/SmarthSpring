package com.example.smarthomeapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smarthomeapp.allcontrol.AllControlFragment;
import com.example.smarthomeapp.divisions.DivisionsFragment;
import com.example.smarthomeapp.events.EventsFragment;
import com.example.smarthomeapp.settings.SettingsFragment;
import com.example.smarthomeapp.util.ActivityUtils;

import butterknife.BindView;

/**
 * The type Main activity.
 */
public class MainActivity extends BaseActivity implements BaseFragment.OnFragmentInteractionListener {

    /**
     * The Bottom navigation view.
     */
    @BindView(R.id.bottom_navigation_bar)
    LinearLayout bottomNavigationView;

    /**
     * The Overview menu option view.
     */
    @BindView(R.id.action_overview)
    LinearLayout overviewMenuOptionView;

    /**
     * The Divisions menu option view.
     */
    @BindView(R.id.action_divisions)
    LinearLayout divisionsMenuOptionView;

    /**
     * The Events menu option view.
     */
    @BindView(R.id.action_events)
    LinearLayout eventsMenuOptionView;

    /**
     * The Control menu option view.
     */
    @BindView(R.id.action_control)
    LinearLayout controlMenuOptionView;

    /**
     * The Settings menu option view.
     */
    @BindView(R.id.action_settings)
    LinearLayout settingsMenuOptionView;

    /**
     * The Fragment container.
     */
    @BindView(R.id.content_frame)
    View fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListeners();

//        if (savedInstanceState == null) {
//            Fragment newFragment = DivisionsFragment.newInstance(null, null);
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.add(CONTENT_VIEW_ID, newFragment).commit();
//        }
    }

    @Override
    protected int getContentViewId(){
        return R.layout.activity_main;
    }

    /**
     * Set listeners.
     */
    public void setListeners(){

        overviewMenuOptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                replaceFragment(DivisionsFragment.newInstance(), R.string.menu_overview);
            }
        });
        divisionsMenuOptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(DivisionsFragment.newInstance(), R.string.menu_divisions);
            }
        });
        eventsMenuOptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(EventsFragment.newInstance(), R.string.menu_events);
            }
        });
        controlMenuOptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(AllControlFragment.newInstance(), R.string.menu_control);
            }
        });
        settingsMenuOptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(SettingsFragment.newInstance(), R.string.menu_settings);
            }
        });
    }

    /**
     * Replace fragment.
     *
     * @param fragmentToReplace the fragment to replace
     * @param titleResId        the title res id
     */
    public void replaceFragment(BaseFragment fragmentToReplace, int titleResId){

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(titleResId);
        }
        if (fragmentToReplace != null) {
            ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), fragmentToReplace, R.id.content_frame);
        }
    }

    /**
     * Add fragment.
     *
     * @param fragmentToReplace the fragment to replace
     * @param titleResId        the title res id
     */
    public void addFragment(BaseFragment fragmentToReplace, int titleResId){

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {
            actionBar.setTitle(titleResId);
        }
        if (fragmentToReplace != null) {
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragmentToReplace, R.id.content_frame);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
