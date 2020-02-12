package com.example.server;

import com.example.server.httpentities.DeviceStateResponse;
import com.example.utils.ServerConstants;
import com.example.utils.Utils;
import com.example.utils.domain.Device;
import com.example.utils.domain.User;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Inspired by Source code from Andy Feng
 * https://www.codeproject.com/Tips/1040097/Create-a-Simple-Web-Server-in-Java-HTTP-Server
 */
public class Handlers {

    /**
     * The type Root handler.
     */
    public static class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            String response = "<h1>Server startServer success if you see this message</h1>" + "<h1>Port: " + HouseControlServer.PORT + "</h1>";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    /**
     * The type Devices handler.
     */
    public static class DevicesHandler implements HttpHandler {

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

            printRequest(requestMethod, path, requestString, responseString);

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

    /**
     * The type Divisions handler.
     */
    public static class DivisionsHandler implements HttpHandler {

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
            printRequest(requestMethod, path, jsonString, null);

            // Send response
            String response = jsonString;
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    /**
     * The type Events handler.
     */
    public static class EventsHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {

        }
    }

    /**
     * The type Overview handler.
     */
    public static class OverviewHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {

        }
    }

    /**
     * For test purposes
     */
    public static class EchoGetJSONHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            // parse request
            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();
            parseQuery(query, parameters);

            String jsonString = new JSONObject()
                    .put("JSON1", "Hello World!")
                    .put("QUERY", query)
                    .put("JSON3", "Hello my World!")
                    .put("JSON4", new JSONObject()
                            .put("key1", "value1")).toString();

            System.out.println(jsonString);
            System.out.println(requestedUri.getPath());
            System.out.println(he.getRequestMethod());

            // send response
            String response = jsonString;
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    /**
     * The type Echo header handler.
     */
    public static class EchoHeaderHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            Headers headers = he.getRequestHeaders();
            Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
            String response = "";
            for (Map.Entry<String, List<String>> entry : entries)
                response += entry.toString() + "\n";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    /**
     * The type Echo get handler.
     */
    public static class EchoGetHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            // parse request
            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();
            parseQuery(query, parameters);
            // send response
            String response = "";
            for (String key : parameters.keySet())
                response += key + " = " + parameters.get(key) + "\n";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }

    }

    /**
     * The type Echo post handler.
     */
    public static class EchoPostHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println("Served by /echoPost handler...");
            // parse request
            Map<String, Object> parameters = new HashMap<String, Object>();
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();
            parseQuery(query, parameters);
            // send response
            String response = "";
            for (String key : parameters.keySet())
                response += key + " = " + parameters.get(key) + "\n";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();

        }
    }

    public static class ConfigHandler implements HttpHandler {

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
            printRequest(requestMethod, path, jsonString, null);

            // Send response
            String response = jsonString;
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static class UsersHandler implements HttpHandler {

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
                parseQuery(query, parameters);

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
            printRequest(requestMethod, path, jsonString, null);

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

    /**
     * Parse query.
     *
     * @param query      the query
     * @param parameters the parameters
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    @SuppressWarnings("unchecked")
    public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

        if (query != null) {
            String pairs[] = query.split("[&]");

            for (String pair : pairs) {
                String param[] = pair.split("[=]");

                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);
                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<String>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }

    /**
     * Print request.
     *
     * @param reqMethod    the req method
     * @param path         the path
     * @param requestJson  the request json
     * @param responseJson the response json
     */
    public static void printRequest(String reqMethod, String path, String requestJson, String responseJson) {
        System.out.println("Request: " + reqMethod + " " + path + " " + requestJson);
        System.out.println("Response: " + responseJson);
    }
}
