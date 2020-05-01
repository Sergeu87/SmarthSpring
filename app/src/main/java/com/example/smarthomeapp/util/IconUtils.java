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
        divisionIconsMap.put(BEDROOM, R.drawable.door);
        divisionIconsMap.put(KITCHEN, R.drawable.door);
        divisionIconsMap.put(BATHROOM, R.drawable.door);
        divisionIconsMap.put(HALL, R.drawable.door);
        divisionIconsMap.put(ATTIC, R.drawable.door);
        divisionIconsMap.put(LIVING_ROOM, R.drawable.door);
        divisionIconsMap.put(GARDEN, R.drawable.door);

        devicesIconsMap.put(ADJUSTABLE_LIGHT, R.drawable.filament);
        devicesIconsMap.put(TEMPERATURE_SENSOR, R.drawable.temperature);
        devicesIconsMap.put(OVEN, R.drawable.oven);
        devicesIconsMap.put(HUMIDITY_RATIO, R.drawable.conditioner);
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
