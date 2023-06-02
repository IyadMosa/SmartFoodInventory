package com.img.SmartFoodInventory.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FcmService {

    private static final String FCM_API_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String FCM_SERVER_KEY = "AAAAlP9sCas:APA91bGTuaOESVo3hRqEWQEPxfbPKeOVNKxF6j2vK5fZ-bADWriFTA3269CltW8VgHSBxwvTIiiYfhKcEvL-QfO8YQznhUvrEsOrN2qEZhOvVeS7e1Cwlcjt4dNMZzk2FiadiJfqvk-C";

    public void sendNotification(String deviceToken, String title, String message) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "key=" + FCM_SERVER_KEY);

        // Create the notification payload
        String payload = buildNotificationPayload(deviceToken, title, message);

        // Send the notification request
        HttpEntity<String> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<String> response = restTemplate.exchange(FCM_API_URL, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Notification sent successfully");
        } else {
            System.out.println("Failed to send notification. Response: " + response.getBody());
        }
    }

    private String buildNotificationPayload(String deviceToken, String title, String message) {
        // Create the JSON payload for the notification
        String payload = String.format(
                "{ \"to\": \"%s\", \"notification\": { \"title\": \"%s\", \"body\": \"%s\" } }",
                deviceToken,
                title,
                message
        );

        return payload;
    }
}
