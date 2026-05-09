package com.mindcare.backend;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class TwilioService {

    @Value("${twilio.sid:}")
    private String twilioSid;

    @Value("${twilio.auth:}")
    private String twilioAuth;

    @Value("${twilio.number:}")
    private String twilioNumber;

    @PostConstruct
    public void init() {
        if (twilioSid != null && !twilioSid.isEmpty() && twilioAuth != null && !twilioAuth.isEmpty()) {
            try {
                Twilio.init(twilioSid, twilioAuth);
            } catch (Exception e) {
                System.err.println("Twilio init failed: " + e.getMessage());
            }
        }
    }

    public void makeCall(String toPhoneNumber) {
        if (twilioSid == null || twilioSid.isEmpty() || toPhoneNumber == null) {
            System.out.println("Twilio credentials not configured; skipping call to " + toPhoneNumber);
            return;
        }
        try {
            Call call = Call.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(twilioNumber),
                    new java.net.URI("http://demo.twilio.com/docs/voice.xml"))
                .create();
            System.out.println("📞 Call triggered to: " + toPhoneNumber + " SID: " + call.getSid());
        } catch (Exception e) {
            System.err.println("❌ Error making call: " + e.getMessage());
        }
    }
}
