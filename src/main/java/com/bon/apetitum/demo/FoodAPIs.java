package com.bon.apetitum.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class FoodAPIs {

    public static HashMap<String, FoodLabel> getFoodLabels() throws IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://tum-dev.github.io/eat-api/enums/labels.json"))
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
            var body = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            var labelList = objectMapper.readValue(body, new TypeReference<FoodLabel[]>() {});

            var map = new HashMap<String, FoodLabel>();
            for (FoodLabel label : labelList) {
                map.put(label.enum_name, label);
            }

            return map;
        } else {
            System.out.println("Error: " + response.statusCode());
            return null;
        }
    }

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

    public static FoodApiResponse getMenu(String responseBody) throws JsonProcessingException {
        FoodApiResponse response;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        response=objectMapper.readValue(responseBody, FoodApiResponse.class);

        return response;
    }
    public static FoodApiResponse getMenuDirectly(int week, int year, String menser) throws JsonProcessingException {
        FoodApiResponse response;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            response=objectMapper.readValue(new URL("https://tum-dev.github.io/eat-api/" + menser + "/" + year + "/" + week + ".json"), FoodApiResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
