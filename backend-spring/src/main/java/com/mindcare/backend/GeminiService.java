package com.mindcare.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.HashMap;

@Service
public class GeminiService {

    @Value("${gemini.api.key:}")
    private String apiKey;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateContent(String prompt) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "Gemini API key is not configured.";
        }
        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;
            
            Map<String, Object> body = new HashMap<>();
            body.put("contents", new Object[] {
                Map.of("parts", new Object[] {
                    Map.of("text", prompt)
                })
            });

            String requestBody = objectMapper.writeValueAsString(body);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                JsonNode root = objectMapper.readTree(response.body());
                return root.path("candidates").get(0)
                           .path("content").path("parts").get(0)
                           .path("text").asText();
            } else {
                return "Error from Gemini API: " + response.statusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to communicate with Gemini API.";
        }
    }
}
