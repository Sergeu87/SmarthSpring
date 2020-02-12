package com.example.server.handlers;

import com.example.server.HouseControlServer;
import com.example.server.HttpUtil;
import com.example.server.httpentities.DeviceStateResponse;
import com.example.utils.ServerConstants;
import com.example.utils.Utils;
import com.example.utils.domain.Device;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * The type Devices handler.
 */
public class DevicesHandler implements HttpHandler {

    private String[] requestPathParameters;
    private String requestString;
    private String responseString;
    private Gson gson = new Gson();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("DEVICES HANDLER REQUEST");
        String requestMethod = httpExchange.getRequestMethod();
        URI requestedUri = httpExchange.getRequestURI();
        String path = requestedUri.getPath();

        requestPathParameters = Utils.stripStringIntoV(path.substring(1), ServerConstants.Handlers.BAR);

        switch (requestMethod) {
            case ServerConstants.CRUD.GET:
                handleGet();
                break;
            case ServerConstants.CRUD.POST:
                handlePost(httpExchange);
                break;
        }

        HttpUtil.printRequest(requestMethod, path, requestString, responseString);

        httpExchange.sendResponseHeaders(200, responseString.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(responseString.getBytes());
        outputStream.close();
    }

    private void handleGet() {
        if (requestPathParameters.length == 1) { // /devices -> show all devices
            responseString = gson.toJson(HouseControlServer.deviceValuesToDevicesResponse());
            System.out.println(requestString);
        } else {
            String deviceId = requestPathParameters[1];
            DeviceStateResponse deviceState = HouseControlServer.getDevicesValues().get(deviceId);
            responseString = gson.toJson(deviceState);
        }
    }

    private void handlePost(HttpExchange httpExchange) {
        if (httpExchange.getResponseBody() != null) {
            ByteArrayOutputStream responseBodyByteArrayOutputStream =
                    (ByteArrayOutputStream) httpExchange.getResponseBody();
            byte[] responseBodyByteArray = responseBodyByteArrayOutputStream.toByteArray();
            String jsonWithDevice = new String(responseBodyByteArray, StandardCharsets.UTF_8);

            Device updatedDevice = gson.fromJson(jsonWithDevice, Device.class);

            // save / update to xml

            //onDeviceValueUpdated
        }
    }

}
