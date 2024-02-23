package ch.wanja;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextValidationService {

    @Incoming("blogs-in")
    @Outgoing("validation-result-out")
    public String validateText(String content) {
        String validationResult;
        if (content.contains("0")) {
            validationResult = "Blog post is not valid";
        } else {
            validationResult = "Blog post is valid";
        }
        System.out.println("TextValidationService ==> " + validationResult);
        return validationResult;
    }
}