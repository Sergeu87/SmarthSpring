package com.example.server;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

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
