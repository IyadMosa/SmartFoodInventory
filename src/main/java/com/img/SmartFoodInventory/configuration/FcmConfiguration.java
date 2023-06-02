package com.img.SmartFoodInventory.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FcmConfiguration {

    private static final String SERVICE_ACCOUNT_KEY_PATH = "src/main/resources/fcm_config.json";

    @PostConstruct
    public void initializeFirebaseApp() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(SERVICE_ACCOUNT_KEY_PATH);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseApp firebaseApp() {
        return FirebaseApp.getInstance();
    }
}
