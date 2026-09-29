package com.example.packup.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {

    @Value("${greenapi.idInstance}")
    private String idInstance;

    @Value("${greenapi.apiTokenInstance}")
    private String apiTokenInstance;

    @Value("${greenapi.apiUrl}")
    private String apiUrl;

    public void sendWhatsApp(String toPhone, String message){
        try {
            String formattedPhone = "966" + toPhone.substring(1);
            String chatId = formattedPhone + "@c.us";

            String url = apiUrl + "/waInstance" + idInstance + "/sendMessage/" + apiTokenInstance;

            Map<String, String> body = new HashMap<>();
            body.put("chatId", chatId);
            body.put("message", message);

            ObjectMapper mapper = new ObjectMapper();
            String jsonBody = mapper.writeValueAsString(body);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, request, String.class);

        } catch (Exception e){
            System.out.println("Failed to send WhatsApp message: " + e.getMessage());
        }
    }
}