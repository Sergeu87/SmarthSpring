package com.example.smarthomeapp.app;

import android.app.Application;

import com.example.utils.domain.HomeConfigEntity;
import com.example.utils.domain.User;

/**
 * Created by Serhii Razovyi on 5-Nov-19.
 */
public class SmartHomeApplication extends Application {

    private static final SmartHomeApplication ourInstance = new SmartHomeApplication();

    private HomeConfigEntity homeConfigEntity;
    private User userEntity;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static SmartHomeApplication getInstance() {
        return ourInstance;
    }

    /**
     * Instantiates a new Smart home application.
     */
    public SmartHomeApplication() {
    }

    /**
     * Get home configuration home config entity.
     *
     * @return the home config entity
     */
    public HomeConfigEntity getHomeConfiguration(){
        return homeConfigEntity;
    }

    /**
     * Get user entity user.
     *
     * @return the user
     */
    public User getUserEntity(){
        return userEntity;
    }

    /**
     * Set home configuration.
     *
     * @param homeConfig the home config
     */
    public void setHomeConfiguration(HomeConfigEntity homeConfig){
        homeConfigEntity = homeConfig;
    }

    /**
     * Set user entity.
     *
     * @param user the user
     */
    public void setUserEntity(User user){
        userEntity = user;
    }

}
