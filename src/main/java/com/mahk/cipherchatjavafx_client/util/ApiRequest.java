package com.mahk.cipherchatjavafx_client.util;

import java.net.URI;
import java.net.http.*;
import java.util.Map;

public class ApiRequest {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String baseUrl = "http://localhost:8080/";

    public static String sendGet(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static String sendPost(String url, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
