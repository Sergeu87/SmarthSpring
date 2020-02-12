package com.example.server.handlers;

import com.example.server.HouseControlServer;
import com.example.server.HttpUtil;
import com.example.utils.ServerConstants;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class ConfigHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestMethod = httpExchange.getRequestMethod();
        URI requestedUri = httpExchange.getRequestURI();
        String path = requestedUri.getPath();
        String jsonString = "";
        Gson gson = new Gson();

        if (ServerConstants.CRUD.GET.equals(requestMethod)) {
            jsonString = gson.toJson(HouseControlServer.getHomeConfigurationEntity());
        }

        // See Response
        HttpUtil.printRequest(requestMethod, path, jsonString, null);

        // Send response
        String response = jsonString;
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
