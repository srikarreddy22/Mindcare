package com.mindcare.backend;

import org.springframework.stereotype.Service;
// Use mock logic if firebase isn't configured, since serviceAccountKey.json is missing

@Service
public class FirebaseService {

    public String getUserPhone(String userId) {
        System.out.println("Firebase lookups are mocked. Returning dummy phone number for userId: " + userId);
        // Returning a dummy or null to test Twilio fallback
        return null;
    }
}
