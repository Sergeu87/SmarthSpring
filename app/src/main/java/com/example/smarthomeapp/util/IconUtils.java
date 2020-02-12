package com.example.smarthomeapp.util;

import com.example.smarthomeapp.R;

import java.util.HashMap;

import static com.example.smarthomeapp.util.Constants.DivisionIconType.*;
import static com.example.smarthomeapp.util.Constants.DeviceIconType.*;

/**
 * Created by Serhii Razovyi on 04-Nov-19.
 */
public class IconUtils {


    private static HashMap<String, Integer> divisionIconsMap = new HashMap<>();
    private static HashMap<String, Integer> devicesIconsMap = new HashMap<>();

    static {
        divisionIconsMap.put(BEDROOM, R.drawable.ic_syp);
        divisionIconsMap.put(KITCHEN, R.drawable.ic_kuch);
        divisionIconsMap.put(BATHROOM, R.drawable.ic_laz);
        divisionIconsMap.put(HALL, R.drawable.ic_bed);
        divisionIconsMap.put(ATTIC, R.drawable.ic_bed);
        divisionIconsMap.put(LIVING_ROOM, R.drawable.ic_gos);
        divisionIconsMap.put(GARDEN, R.drawable.ic_bed);

        devicesIconsMap.put(ADJUSTABLE_LIGHT, R.drawable.ic_filament);
        devicesIconsMap.put(TEMPERATURE_SENSOR, R.drawable.ic_temperature);
        devicesIconsMap.put(OVEN, R.drawable.ic_oven);
        devicesIconsMap.put(HUMIDITY_RATIO, R.drawable.ic_apps);
    }


    /**
     * Get divisions icons map hash map.
     *
     * @return the hash map
     */
    public static HashMap<String, Integer> getDivisionsIconsMap(){
        return divisionIconsMap;
    }

    /**
     * Get devices icons map hash map.
     *
     * @return the hash map
     */
    public static HashMap<String, Integer> getDevicesIconsMap(){
        return devicesIconsMap;
    }


}
