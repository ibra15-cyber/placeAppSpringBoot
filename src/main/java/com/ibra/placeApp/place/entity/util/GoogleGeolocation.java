package com.ibra.placeApp.place.entity.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GoogleGeolocation {

    @Value("${google.api.key}")
    private String googleApiKey; // API Key should be injected from application.properties

    private final WebClient webClient;

    public GoogleGeolocation(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://maps.googleapis.com/maps/api/geocode").build();
    }

    public Mono<JSONObject> getCoordinatesFromAddress(String address) {
        String url = String.format("/json?address=%s&key=%s",
                address,
                googleApiKey);

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    JSONObject responseJson = null;
                    try {
                        responseJson = new JSONObject(response);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        if (responseJson.getJSONArray("results").length() > 0) {
                            JSONObject location = responseJson.getJSONArray("results")
                                    .getJSONObject(0)
                                    .getJSONObject("geometry")
                                    .getJSONObject("location");
                            return location;
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                });
    }
}

