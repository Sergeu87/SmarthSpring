package com.example.utils.domain;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
public class DeviceService {

    /**
     * Attributes
     */
    private String refService;

    /**
     * Constructor
     *
     * @param refService the ref service
     */
    public DeviceService(String refService) {
        this.refService = refService;
    }

    /**
     * Gets ref service.
     *
     * @return the ref service
     */
    public String getRefService() {
        return refService;
    }

    /**
     * Sets ref service.
     *
     * @param refService the ref service
     */
    public void setRefService(String refService) {
        this.refService = refService;
    }
}
