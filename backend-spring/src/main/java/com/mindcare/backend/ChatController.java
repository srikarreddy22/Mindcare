package com.mindcare.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // In production, restrict this
public class ChatController {

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private TwilioService twilioService;

    @Autowired
    private FirebaseService firebaseService;

    private final List<String> triggerWords = Arrays.asList(
        "i am done", "i give up", "suicidal", "kill myself", 
        "hopeless", "end my life", "depressed", "can't go on", 
        "worthless", "want to die", "i hate my life"
    );

    private boolean containsTriggerWord(String message) {
        String msg = message.toLowerCase();
        return triggerWords.stream().anyMatch(msg::contains);
    }

    private boolean isEmotionallyWeak(String userMessage) {
        if (containsTriggerWord(userMessage)) return true;
        
        String prompt = "Determine if the following text shows emotional distress or suicidal intent.\n" +
                        "Reply with only \"YES\" or \"NO\".\nText: \"" + userMessage + "\"";
        String response = geminiService.generateContent(prompt);
        if (response != null && response.trim().toUpperCase().contains("YES")) {
            return true;
        }
        return false;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        ChatResponse res = new ChatResponse();
        
        if (request.getUserMessage() == null || request.getUserMessage().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        boolean weak = isEmotionallyWeak(request.getUserMessage());

        if (weak) {
            String userId = request.getUserId();
            if (userId != null && !userId.isEmpty()) {
                String phone = firebaseService.getUserPhone(userId);
                if (phone != null) {
                    twilioService.makeCall(phone);
                }
            }
            res.setReply("🚨 We noticed signs of distress. We've alerted your emergency contact for support. 💙");
            res.setDistress(true);
            return ResponseEntity.ok(res);
        }

        String replyMessage = geminiService.generateContent(request.getUserMessage());
        res.setReply(replyMessage);
        res.setDistress(false);

        return ResponseEntity.ok(res);
    }
}
