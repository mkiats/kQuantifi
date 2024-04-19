package com.mkiats.services;

import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RetrievalService {

    @Value("${api.url.alphavantage}")
    private String baseApiUrl;
    @Value("${api.key.alphavantage}")
    private String apiKey;

    // Dependency injected
    private final RestTemplate restTemplate;

    public String fetchTickerData(String theTimeframe, String theSymbol) {

        String requestTimeframe;
        String requestTickerSymbol;

        // Handles API requests
        if (theTimeframe != null) {
            requestTimeframe = "TIME_SERIES_MONTHLY_ADJUSTED";
        } else {
            requestTimeframe = "TIME_SERIES_WEEKLY_ADJUSTED";
        }
        requestTickerSymbol = Objects.requireNonNullElse(theSymbol, "SPY");


        String finalisedApiUrl = String.format("%sfunction=%s&symbol=%s&apikey=%s",baseApiUrl,requestTimeframe, requestTickerSymbol,apiKey);


        ResponseEntity<String> responseEntity = restTemplate.getForEntity(finalisedApiUrl, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            System.out.println(responseBody);
            return responseBody;
        } else {
            return "Error: " + responseEntity.getStatusCode();
        }
    }

}
