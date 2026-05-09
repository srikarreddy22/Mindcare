package com.mindcare.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // In production, restrict this
public class EmergencyController {

    @Autowired
    private TwilioService twilioService;

    @PostMapping("/emergency-sms")
    public ResponseEntity<Map<String, Object>> emergencySms(@RequestBody Map<String, Object> payload) {
        System.out.println("SMS requested: " + payload);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "SMS alert logged (simulation)");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/emergency-email")
    public ResponseEntity<Map<String, Object>> emergencyEmail(@RequestBody Map<String, Object> payload) {
        System.out.println("Email requested: " + payload);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Email alert logged (simulation)");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/emergency-call")
    public ResponseEntity<Map<String, Object>> emergencyCall(@RequestBody Map<String, Object> payload) {
        System.out.println("Emergency call requested: " + payload);
        String to = (String) payload.get("to");
        if (to != null && !to.isEmpty()) {
            twilioService.makeCall(to);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("ok", true);
        return ResponseEntity.ok(response);
    }
}
