package com.example.smarthomeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.smarthomeapp.R;

import butterknife.ButterKnife;

/**
 * The type Base activity.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
    }

    /**
     * Gets content view id.
     *
     * @return the content view id
     */
    protected abstract int getContentViewId();

}
