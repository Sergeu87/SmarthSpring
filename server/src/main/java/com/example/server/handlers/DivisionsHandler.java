package com.example.server.handlers;

import com.example.server.HouseControlServer;
import com.example.server.HttpUtil;
import com.example.server.httpentities.DeviceStateResponse;
import com.example.utils.ServerConstants;
import com.example.utils.Utils;
import com.example.smarthomeapp.model.Device;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Divisions handler.
 */
public class DivisionsHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        String requestMethod = he.getRequestMethod();
        URI requestedUri = he.getRequestURI();
        String path = requestedUri.getPath();
        String jsonString = "";
        Gson gson = new Gson();

        // ["devices", ... ]
        String[] pathParameters = Utils.stripStringIntoV(path.substring(1), ServerConstants.Handlers.BAR);

        switch (requestMethod) {

            case ServerConstants.CRUD.GET:

                if (pathParameters.length == 1) { // /divisions -> show all devices

                } else {
                    String divisionId = pathParameters[1];
                    String option = pathParameters[2];

                    if (ServerConstants.Handlers.DEVICES.equals(option)) {

                        List<Device> devices = HouseControlServer.getHomeConfigurationEntity().getDevicesByDivisionID(divisionId);
                        List<DeviceStateResponse> myDevicesState = new ArrayList<>();

                        for (Device d : devices) {
                            myDevicesState.add(HouseControlServer.getDevicesValues().get(d.getId()));
                        }

                        jsonString = gson.toJson(myDevicesState);
                    }
                }

                break;
        }

        // See Response
        HttpUtil.printRequest(requestMethod, path, jsonString, null);

        // Send response
        String response = jsonString;
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
