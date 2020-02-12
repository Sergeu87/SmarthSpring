package com.example.server.handlers;

import com.example.server.HouseControlServer;
import com.example.server.HttpUtil;
import com.example.utils.ServerConstants;
import com.example.utils.domain.User;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestMethod = httpExchange.getRequestMethod();
        URI requestedUri = httpExchange.getRequestURI();
        String path = requestedUri.getPath();
        String jsonString = "";
        Gson gson = new Gson();
        boolean successLogin = false;

        if (ServerConstants.CRUD.GET.equals(requestMethod)) {
            Map<String, Object> parameters = new HashMap<>();
            String query = httpExchange.getRequestURI().getRawQuery();
            HttpUtil.parseQuery(query, parameters);

            String login = (String) parameters.get("username");
            String password = (String) parameters.get("password");
            if (login == null || password == null) {
                jsonString = gson.toJson("Wrong login or password");
            } else {
                List<User> usersFromXml = HouseControlServer.getHomeConfigurationEntity().getUserList();
                for (User user : usersFromXml) {
                    if (user.getName().equals(login) && user.getPassword().equals(password)) {
                        jsonString = gson.toJson(user);
                        successLogin = true;
                    }
                }
            }
        }

        // See Response
        HttpUtil.printRequest(requestMethod, path, jsonString, null);

        // Send response
        String response = jsonString;
        if (successLogin) {
            httpExchange.sendResponseHeaders(200, response.length());
        } else {
            httpExchange.sendResponseHeaders(400, response.length());
        }
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
