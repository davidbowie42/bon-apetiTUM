package com.bon.apetitum.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FoodAPIs {

    public static String getWeeklyFoodFromAPI(int week, int year, String menser) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://tum-dev.github.io/eat-api/" + menser + "/" + year + "/" + week + ".json"))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to send request - IO " + e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to send request - Interrupted " + e);
        }

        if (response.statusCode() == 200) {
            return response.body();
            //System.out.println("Response: " + responseBody);
        } else {
            System.out.println("Error: " + response.statusCode());
            return null;
        }
        //return responseBody;
    }

    public static Response getMenu(String responseBody) throws JsonProcessingException {
        Response response;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        response=objectMapper.readValue(responseBody, Response.class);

        return response;
    }
    public static Response getMenuDirectly(int week, int year, String menser) throws JsonProcessingException {
        Response response;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            response=objectMapper.readValue(new URL("https://tum-dev.github.io/eat-api/" + menser + "/" + year + "/" + week + ".json"), Response.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
