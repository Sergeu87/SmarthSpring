package com.example.server.handlers;

import com.example.server.HouseControlServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * The type Root handler.
 */

public class RootHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        String response = "<h1>Server startServer success if you see this message</h1>" + "<h1>Port: " + HouseControlServer.PORT + "</h1>";
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
