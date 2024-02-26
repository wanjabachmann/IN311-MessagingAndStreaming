package ch.wanja;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TextValidationService {
    /*
     * public record ValidationRequest(long id, String text) {
     * }
     * 
     * public record ValidationResponse(long id, boolean valid) {
     * }
     */

    @Transactional
    @Incoming("blogs-in")
    @Outgoing("validation-result")
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