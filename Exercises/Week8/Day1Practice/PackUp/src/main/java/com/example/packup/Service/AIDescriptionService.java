package com.example.packup.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AIDescriptionService {

    @Value("${gemini.apiKey}")
    private String apiKey;

    @Value("${gemini.apiUrl}")
    private String apiUrl;

    public String generateDescription(String title, String city, String address, Double pricePerDay, Double spaceSize){
        try {
            String prompt = "Write a short attractive marketing description for a storage space rental, in BOTH Arabic and English. " +
                    "Write the Arabic version first, then the English version below it separated by a line of dashes (———). " +
                    "The total combined length of both versions must not exceed 650 characters. " +
                    "Details - Title: " + title + ", City: " + city + ", Address: " + address +
                    ", Price per day: " + pricePerDay + " SAR, Size: " + spaceSize + " square meters. " +
                    "Only output the description text itself, no extra headings, notes, or quotation marks.";

            Map<String, Object> part = new HashMap<>();
            part.put("text", prompt);

            Map<String, Object> content = new HashMap<>();
            content.put("parts", List.of(part));

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("contents", List.of(content));

            ObjectMapper mapper = new ObjectMapper();
            String jsonBody = mapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-goog-api-key", apiKey);
            HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject(apiUrl, request, String.class);

            JsonNode root = mapper.readTree(response);
            String generatedText = root.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();

            return generatedText.trim();

        } catch (Exception e){
            System.out.println("Failed to generate description: " + e.getMessage());
            return null;
        }
    }
}