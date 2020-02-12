package com.example.smarthomeapp.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.smarthomeapp.BaseActivity;
import com.example.smarthomeapp.MainActivity;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.app.SmartHomeApplication;
import com.example.smarthomeapp.login.LoginActivity;
import com.example.smarthomeapp.util.Constants;
import com.example.smarthomeapp.util.SharedPreferencesUtils;
import com.example.utils.domain.HomeConfigEntity;
import com.example.utils.domain.User;

import butterknife.BindView;

/**
 * The type Splash screen activity.
 */
public class SplashScreenActivity extends BaseActivity implements SplashScreenContract.View{

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private SplashScreenContract.Presenter mSplashPresenter;

    /**
     * The House loader.
     */
    @BindView(R.id.splash_progress_bar)
    ProgressBar houseLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        mSplashPresenter = new SplashPresenter(this);


        if (isRememberMe()) {
            setLoadingIndicator(true);
        } else {
            enterMainScreen(SplashScreenContract.LOGIN);
        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    public void setPresenter(SplashScreenContract.Presenter presenter) {
        mSplashPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        houseLoader.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showHouseConfigResult(HomeConfigEntity homeConfigEntity) {
        setLoadingIndicator(false);

        if (homeConfigEntity != null) {
            User user = getUserFromConfiguration(homeConfigEntity);

            if (user == null) {
                Toast.makeText(SplashScreenActivity.this, "The user does not exist :O", Toast.LENGTH_LONG).show();
            } else {
                SmartHomeApplication.getInstance().setHomeConfiguration(homeConfigEntity);
                SmartHomeApplication.getInstance().setUserEntity(user);
                enterMainScreen(SplashScreenContract.MAIN_SCREEN);
                return;
            }
        }
        enterMainScreen(SplashScreenContract.LOGIN);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void enterMainScreen(final int screenToGoTo) {

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent;

                if (screenToGoTo == SplashScreenContract.MAIN_SCREEN) {
                    mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                } else {
                    mainIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                }

                SplashScreenActivity.this.startActivity(mainIntent);

                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private User getUserFromConfiguration(HomeConfigEntity homeConfiguration){

        String userId = SharedPreferencesUtils.getStringPreference(getBaseContext(), Constants.Login.USER_ID);
        return homeConfiguration.getUserByID(userId);
    }

    private boolean isRememberMe(){
        return SharedPreferencesUtils.getBooleanPreference(getBaseContext(), Constants.Login.REMEMBER_ME, false);
    }
}
