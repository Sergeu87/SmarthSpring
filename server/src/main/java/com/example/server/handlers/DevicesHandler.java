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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * The type Devices handler.
 */
public class DevicesHandler implements HttpHandler {

//    private String[] requestPathParameters;
//    private String requestString;
//    private String responseString;
    private Gson gson = new Gson();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("DEVICES HANDLER REQUEST");
        String requestMethod = httpExchange.getRequestMethod();
        URI requestedUri = httpExchange.getRequestURI();
        String requestedUriPath = requestedUri.getPath();

        String[] requestPathParameters = Utils.stripStringIntoV(requestedUriPath.substring(1), ServerConstants.Handlers.BAR);
        String responseString = "";

        switch (requestMethod) {
            case ServerConstants.CRUD.GET:
                responseString = handleGet(requestPathParameters);
                break;
            case ServerConstants.CRUD.POST:
                handlePost(httpExchange);
                break;
        }

        HttpUtil.printRequest(requestMethod, requestedUriPath, getBodyAsString(httpExchange.getRequestBody()), responseString);

        httpExchange.sendResponseHeaders(200, responseString.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(responseString.getBytes());
        outputStream.close();
    }

    private String handleGet(String[] requestPathParameters) {
        String responseString;
        if (requestPathParameters.length == 1) { // /devices -> show all devices
            final List<DeviceStateResponse> deviceStateResponses = HouseControlServer.deviceValuesToDevicesResponse();
            responseString = gson.toJson(deviceStateResponses);
        } else {
            String deviceId = requestPathParameters[1];
            DeviceStateResponse deviceState = HouseControlServer.getDevicesValues().get(deviceId);
            responseString = gson.toJson(deviceState);
        }
        return responseString;
    }

    private void handlePost(HttpExchange httpExchange) {
        if (httpExchange.getResponseBody() != null) {
            String responseBody = getBodyAsString(httpExchange.getRequestBody());

            Device updatedDevice = gson.fromJson(responseBody, Device.class);

            // save / update to xml

            //onDeviceValueUpdated
        }
    }

    private String getBodyAsString(InputStream body) {
//        ByteArrayOutputStream responseBodyByteArrayOutputStream = (ByteArrayOutputStream) body;
//        byte[] responseBodyByteArray = responseBodyByteArrayOutputStream.toByteArray();
//        return new String(responseBodyByteArray, StandardCharsets.UTF_8);
        try {
            InputStreamReader isr = new InputStreamReader(body, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String responseBody = br.readLine();
            return responseBody;
        } catch (Exception e) {
            return null;
        }
    }

}
