package com.example.smarthomeapp;

/**
 * Created by Serhii Razovyi on 02-Nov-19.
 *
 * @param <T> the type parameter
 */
public interface BaseView<T extends BasePresenter> {

    /**
     * Sets presenter.
     *
     * @param presenter the presenter
     */
    void setPresenter(T presenter);

}
